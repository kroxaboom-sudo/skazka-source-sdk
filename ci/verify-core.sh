#!/usr/bin/env bash
set -euo pipefail

rm -rf build/self-test
mkdir -p build/self-test

javac -encoding UTF-8 -d build/self-test \
  source-api/src/main/java/com/kroxaboom/skazka/source/*.java \
  registry-core/src/main/java/com/kroxaboom/skazka/source/registry/*.java \
  tests/SourceSdkSelfTest.java

java -cp build/self-test SourceSdkSelfTest
