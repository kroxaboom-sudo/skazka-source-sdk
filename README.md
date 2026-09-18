# Skazka Source SDK

**RU:** Публичный контракт между приложениями Skazka и источниками контента.

**EN:** Public contract between Skazka applications and content sources.

## Что здесь будет / What belongs here

- Source API;
- стабильная identity-модель source/content;
- Registry client contracts;
- generic adapter interfaces;
- безопасные примеры;
- test fixtures и compatibility tests.

## Граница / Boundary

Закрытые адаптеры, credentials, антиабуз-логика, приватные зеркала и production routing не публикуются.

## Зачем это нужно / Why this exists

Клиент должен работать с логическим источником, а не быть намертво привязан к одному hostname. Домены и зеркала могут меняться, identity контента — нет.
