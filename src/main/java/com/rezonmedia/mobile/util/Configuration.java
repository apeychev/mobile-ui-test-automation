package com.rezonmedia.mobile.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public final class Configuration {

    public String getMobileUrl() {
        return get("mobile.url");
    }

    public int getWebDriverElementWaitTimeoutInSeconds() {
        return Integer.valueOf(get("mobile.browser.elementWaitTimeoutInSeconds"));
    }

    public String get(final String name) {
        String property = System.getProperty(name);
        if (property == null || property.isBlank()) {
            return defaults.getProperty(name);
        }
        return property;
    }

    private Properties defaults;

    public Configuration() throws IOException {
        defaults = new Properties();
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("runtime.properties")) {
            defaults.load(is);
        }
    }

    @Override
    public String toString() {
        Iterator<String> asIterator = (Iterator<String>) defaults.propertyNames().asIterator();
        StringBuilder sb = new StringBuilder();
        while (asIterator.hasNext()) {
            String name = asIterator.next();
            sb.append(String.format("%s=%s%n", name, get(name)));
        }
        return sb.toString();

    }

}
