package dev.tr7zw.transition.sentry;

import io.sentry.*;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.config.*;

import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.util.concurrent.*;

public class SentryInstance {

    private static final SentryInstance INSTANCE = new SentryInstance();
    public static final Logger logger = LogManager.getLogger();

    public static void initialize(SentryDataProvider dataProvider) {
        INSTANCE.init(dataProvider);
    }

    private SentryDataProvider dataProvider;
    private Set<String> seenMessages = new HashSet<>();
    private boolean filterLoaded = false;

    private void init(SentryDataProvider dataProvider) {
        this.dataProvider = dataProvider;
        if (dataProvider == null || !dataProvider.userConsentedToCrashReports()) {
            // Do not enable Sentry
            return;
        }
        registerSentryFilter();
        Sentry.init(options -> {
            options.setDsn(
                    "https://edfbf3e57316f04a7e5d43ba16f097cb@o4510312664596480.ingest.de.sentry.io/4510312670036048");
            options.setRelease("transition@" + INSTANCE.dataProvider.getModVersion());
            options.setTag("minecraft_version", INSTANCE.dataProvider.getMinecraftVersion());
            options.setTag("loader", INSTANCE.dataProvider.getModloader());
            options.setEnvironment("production");
            options.setSendDefaultPii(false); // don't send personal info
            options.setBeforeSend((event, hint) -> {
                // Sentry captures unhandled exceptions from all mods, we only want ours
                if (event.getThrowable() != null && event.isCrashed()) {
                    boolean relatedToMod = false;
                    Throwable t = event.getThrowable();

                    while (t != null && !relatedToMod) {
                        relatedToMod = Arrays.stream(t.getStackTrace()).anyMatch(e -> e.getClassName().startsWith("dev.tr7zw"));
                        t = t.getCause();
                    }

                    if (!relatedToMod)
                        return null; // drop the event entirely
                }
                return event;
            });

        });
        logger.info("Setup Sentry for error reporting");
    }

    private void registerSentryFilter() {
        if (filterLoaded) {
            return;
        }
        filterLoaded = true;
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();

        // Create filter instance
        var filter = new SentryLogFilter();

        // Add filter to root logger
        LoggerConfig rootLoggerConfig = config.getRootLogger();
        rootLoggerConfig.addFilter(filter);

        // Update loggers so the change takes effect
        context.updateLoggers();
    }

    public static boolean maybeReport(String loggerName, String thread, String message, Throwable throwable) {
        if (INSTANCE.dataProvider == null || !INSTANCE.dataProvider.userConsentedToCrashReports())
            return false;

        boolean relatedToMod = false;
        Throwable t = throwable;

        while (t != null && !relatedToMod) {
            relatedToMod = Arrays.stream(t.getStackTrace()).anyMatch(e -> e.getClassName().startsWith("dev.tr7zw"));
            t = t.getCause();
        }

        if (!relatedToMod)
            return false;

        if (INSTANCE.seenMessages.contains(throwable.getMessage())) {
            return false;
        }

        INSTANCE.seenMessages.add(throwable.getMessage());

        // Attach context info
        Sentry.configureScope(scope -> {
            scope.setContexts("loaded_mods", INSTANCE.dataProvider.getLoadedMods());
            scope.setContexts("Message", message);
            scope.setTag("suspectMod", SentryTagResolver.findSuspectMod(throwable));
            scope.setTag("Logger", loggerName);
            scope.setTag("Thread", thread);
            scope.setTag("hasPlayer", Boolean.toString(INSTANCE.dataProvider.hasPlayer()));
            scope.setTag("LevelType", INSTANCE.dataProvider.getLevelType());
            scope.setTag("Screen", INSTANCE.dataProvider.getScreen());
            scope.setLevel("CrashReport".equals(loggerName) ? SentryLevel.FATAL : SentryLevel.ERROR);
        });

        var id = Sentry.captureException(throwable);
        logger.info("Logged to Sentry {}", id);
        return true;
    }

    public static void forceFlush() {
        if (INSTANCE.dataProvider == null || !INSTANCE.dataProvider.userConsentedToCrashReports())
            return;
        logger.info("Waiting for Sentry to flush...");
        Sentry.flush(3000);
    }

}
