package dev.tr7zw.transition.mc.extending;

public interface ExtensionHolder {

    public void setExtension(Object key, Object value);

    public <T> T getExtension(Object key, Class<T> type);

}
