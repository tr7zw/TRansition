package dev.tr7zw.transition.sentry;

import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.filter.*;

/**
 * Log4j filter to capture all exceptions that get logged for processing
 */
public class SentryLogFilter extends AbstractFilter {
    @Override
    public Result filter(LogEvent event) {
        if (event.getThrown() != null)
            SentryInstance.maybeReport(event.getLoggerName(), event.getThreadName(),
                    event.getMessage() != null ? event.getMessage().getFormattedMessage() : "Null", event.getThrown());
        return Result.NEUTRAL;
    }
}