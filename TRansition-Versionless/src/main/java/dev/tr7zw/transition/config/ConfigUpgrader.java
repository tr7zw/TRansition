package dev.tr7zw.transition.config;

public interface ConfigUpgrader<T> {

    /**
     *
     * @param config
     * @return true when the config changed
     */
    boolean upgradeConfig(T config);

}
