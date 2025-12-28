# Solução para Erro Kapt com Java 17+

## Problema
Erro: `IllegalAccessError: superclass access check failed` ao usar Kapt com Java 17+

## Soluções Aplicadas

### 1. ✅ Java 11 (Recomendado)
Mudei `compileOptions` e `kotlinOptions` para Java 11:
```kotlin
compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlinOptions {
    jvmTarget = "11"
}
```

### 2. ✅ Flags JVM no gradle.properties
Adicionei flags `--add-opens` para permitir acesso aos módulos do Java compiler.

### 3. ✅ Configuração Kapt
Adicionei `javacOptions` no bloco `kapt` com flags `--add-opens`.

## Próximos Passos

1. **No Android Studio**:
   - File > Project Structure > SDK Location
   - Certifique-se de usar **JDK 11** (não 17)
   - Se não tiver JDK 11, baixe em: https://adoptium.net/

2. **Limpar e Rebuild**:
   ```bash
   ./gradlew clean
   ./gradlew assembleDebug
   ```

3. **Se ainda não funcionar**:
   - Certifique-se de que o Gradle está usando JDK 11
   - File > Settings > Build, Execution, Deployment > Build Tools > Gradle
   - Gradle JDK: selecione JDK 11

**A mudança para Java 11 resolve o problema na maioria dos casos!**

