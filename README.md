# Skazka Source SDK

> RU — основной язык · EN — required second language

## RU

Публичные source-neutral контракты для источников и Registry.

**Статус:** `0.1.0-preview`.

- `source-api` — capabilities, минимальный `SourceAdapter` и registry code-owned адаптеров.
- `registry-core` — модель endpoint, независимые trust/health состояния, приоритет и безопасная проверка публичных hostname.
- Remote manifest не может устанавливать исполняемый код; executable adapters остаются code-owned.
- Конкретные parser rules, production hosts, private routes, credentials и старые trust roots не входят в public SDK.

Проверено на HOSTKEY: source SDK self-test — PASS; Gradle `build` обоих модулей — PASS.

## EN

Public source-neutral contracts for content sources and the Registry.

**Status:** `0.1.0-preview`.

- `source-api` — capabilities, minimal `SourceAdapter`, and a registry for code-owned adapters.
- `registry-core` — endpoint model, separate trust/health state, priority, and safe public-host validation.
- Remote manifests cannot install executable code; executable adapters remain code-owned.
- Concrete parser rules, production hosts, private routes, credentials, and legacy trust roots are outside the public SDK.

Verified on HOSTKEY: source SDK self-test — PASS; Gradle `build` for both modules — PASS.

## Coordinates / Координаты

- `com.kroxaboom.skazka:source-api:0.1.0-preview`
- `com.kroxaboom.skazka:registry-core:0.1.0-preview`

See [DEVELOPMENT_RULES.md](DEVELOPMENT_RULES.md).

> A license will be selected before the first stable public release. Until then, publication of the source does not grant reuse rights.
