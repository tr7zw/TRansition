package dev.tr7zw.transition.sentry;

import java.util.*;

public interface SentryDataProvider {
    boolean userConsentedToCrashReports();

    String getMinecraftVersion();

    String getModloader();

    Collection<String> getLoadedMods();

    String getModVersion();

    String getLevelType();

    boolean hasPlayer();

    String getScreen();
}
