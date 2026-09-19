package com.kroxaboom.skazka.source.registry;

/**
 * RU: Trust и reachability разделены намеренно: временно недоступный verified endpoint
 * не перестаёт быть доверенным только из-за одного неудачного наблюдения.
 *
 * EN: Trust and reachability are deliberately separate: a temporarily unreachable verified
 * endpoint does not become untrusted because of one failed observation.
 */
public record SourceEndpoint(
        String sourceId,
        String host,
        Status status,
        Health health,
        long lastChecked,
        long lastOk,
        int confirmations,
        int independentPositive,
        int independentNegative,
        int survivalScore
) {
    public SourceEndpoint {
        sourceId = clean(sourceId);
        host = clean(host).toLowerCase(java.util.Locale.ROOT);
        status = status == null ? Status.CANDIDATE : status;
        health = health == null ? Health.UNKNOWN : health;
        lastChecked = Math.max(0, lastChecked);
        lastOk = Math.max(0, lastOk);
        confirmations = Math.max(0, confirmations);
        independentPositive = Math.max(0, independentPositive);
        independentNegative = Math.max(0, independentNegative);
        survivalScore = Math.max(0, Math.min(100, survivalScore));
    }

    public boolean trusted() {
        return status == Status.VERIFIED;
    }

    public int globalPriority() {
        int priority = survivalScore * 10 + Math.min(confirmations, 20);
        if (health == Health.HEALTHY) {
            priority += 250;
        } else if (health == Health.DEGRADED) {
            priority += 50;
        } else if (health == Health.UNREACHABLE) {
            priority -= 100;
        }
        return priority;
    }

    private static String clean(String value) {
        return value == null ? "" : value.trim();
    }

    public enum Status {
        CANDIDATE,
        VERIFIED,
        REJECTED,
        HISTORICAL
    }

    public enum Health {
        UNKNOWN,
        HEALTHY,
        DEGRADED,
        UNREACHABLE
    }
}
