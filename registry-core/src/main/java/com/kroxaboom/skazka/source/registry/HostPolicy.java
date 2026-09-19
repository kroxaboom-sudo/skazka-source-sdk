package com.kroxaboom.skazka.source.registry;

import java.net.URI;
import java.util.Locale;

/**
 * RU: Отсекает loopback/internal/IP-like адреса до того, как endpoint попадёт
 * в общий Registry или сетевой fallback.
 *
 * EN: Rejects loopback/internal/IP-like addresses before an endpoint enters
 * the shared Registry or network fallback.
 */
public final class HostPolicy {
    private HostPolicy() {}

    public static String httpsHost(String value) {
        try {
            URI uri = URI.create(value);
            if (!"https".equalsIgnoreCase(uri.getScheme())
                    || uri.getRawUserInfo() != null
                    || (uri.getPort() != -1 && uri.getPort() != 443)
                    || uri.getHost() == null) {
                return "";
            }
            return uri.getHost().toLowerCase(Locale.ROOT);
        } catch (Exception ignored) {
            return "";
        }
    }

    public static boolean safePublicHost(String host) {
        String value = host == null ? "" : host.trim().toLowerCase(Locale.ROOT);
        if (value.isEmpty()
                || value.length() > 253
                || value.contains("..")
                || !value.contains(".")) {
            return false;
        }

        if (value.matches("\\d{1,3}(?:\\.\\d{1,3}){3}")
                || value.contains(":")
                || value.equals("localhost")
                || value.equals("metadata.google.internal")) {
            return false;
        }

        for (String suffix : new String[]{".local", ".localhost", ".internal", ".home", ".lan"}) {
            if (value.endsWith(suffix)) {
                return false;
            }
        }

        return value.matches("[a-z0-9.-]+");
    }
}
