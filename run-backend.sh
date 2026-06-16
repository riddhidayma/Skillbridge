#!/usr/bin/env bash
set -euo pipefail

export JAVA_HOME="${JAVA_HOME:-/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home}"
export PATH="$JAVA_HOME/bin:$PATH"

cd "$(dirname "$0")"
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
