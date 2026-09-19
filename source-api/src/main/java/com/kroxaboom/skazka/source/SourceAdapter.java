package com.kroxaboom.skazka.source;

import java.util.Set;

/**
 * RU: Минимальный стабильный контракт источника. Hostnames и parser-specific детали
 * не должны протекать в функциональные модули приложения.
 *
 * EN: Minimal stable source contract. Hostnames and parser-specific details
 * should not leak into application feature modules.
 */
public interface SourceAdapter {
    String id();

    Set<SourceCapability> capabilities();

    default boolean supports(SourceCapability capability) {
        return capability != null && capabilities().contains(capability);
    }
}
