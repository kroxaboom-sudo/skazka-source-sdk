package com.kroxaboom.skazka.source;

/**
 * RU: Возможности, которые адаптер источника действительно умеет предоставлять.
 * EN: Capabilities that a source adapter can actually provide.
 */
public enum SourceCapability {
    SEARCH,
    CATALOG,
    METADATA,
    CHAPTER_LIST,
    CHAPTER_CONTENT,
    AUTH,
    BOOKMARK_SYNC,
    PROGRESS_SYNC,
    COVERS
}
