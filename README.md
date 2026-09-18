# Skazka Source SDK

> RU — основной язык · EN — required second language

## RU

Публичные контракты для источников, Registry-клиента и адаптеров.

**Текущий статус:** репозиторий создан как целевая граница модуля. Рабочий код переносится из существующих проектов поэтапно, с тестами и без копирования project-specific зависимостей.

**Граница модуля:** generic source API and safe adapters; private routes, cookies, secrets and protected source logic stay out.

Перед первым стабильным релизом здесь появятся собственные versioning, тесты, changelog и лицензия. До выбора лицензии публикация кода не означает автоматическое разрешение на его повторное использование.

## EN

Public source contracts, Registry client, and adapter interfaces.

**Current status:** this repository is the target module boundary. Working code is being extracted from existing projects incrementally, with tests and without copying project-specific dependencies.

**Module boundary:** generic source API and safe adapters; private routes, cookies, secrets and protected source logic stay out.

Before the first stable release, this repository will get its own versioning, tests, changelog, and license. Until a license is selected, publishing the source does not automatically grant reuse rights.

## Development rules / Правила разработки

See [DEVELOPMENT_RULES.md](DEVELOPMENT_RULES.md).
