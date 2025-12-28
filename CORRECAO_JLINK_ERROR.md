# Correção: Erro jlink.exe

## Problema
Erro ao executar `jlink.exe` durante a compilação. Isso geralmente acontece quando:
1. Cache do Gradle corrompido
2. Problemas com o JDK toolchain
3. Configuração incorreta do Java Home

## Soluções

### 1. ✅ Limpar Cache do Gradle
```bash
# No terminal:
./gradlew clean --no-daemon
# Ou manualmente:
rm -rf .gradle
rm -rf ~/.gradle/caches
```

### 2. ✅ No Android Studio
1. **File > Invalidate Caches... > Invalidate and Restart**
2. **Build > Clean Project**
3. **Build > Rebuild Project**

### 3. ✅ Verificar JDK Configuration
No Android Studio:
- **File > Project Structure > SDK Location**
- Certifique-se de que o **JDK location** está correto
- Use JDK 11 ou JDK 17 (recomendado: JDK 11)

- **File > Settings > Build, Execution, Deployment > Build Tools > Gradle**
- **Gradle JDK**: Selecione um JDK válido (11 ou 17)

### 4. ✅ Limpar Cache Manualmente
```bash
# Windows PowerShell:
Remove-Item -Recurse -Force $env:USERPROFILE\.gradle\caches
Remove-Item -Recurse -Force .gradle

# Depois:
./gradlew clean
./gradlew assembleDebug
```

### 5. ✅ Se ainda não funcionar
Tente definir o Java Home explicitamente no `gradle.properties`:
```properties
org.gradle.java.home=C:\\Program Files\\Android\\Android Studio\\jbr
```
(Substitua pelo caminho do seu JDK)

## Próximos Passos

1. Limpar cache do Gradle
2. Invalidar caches no Android Studio
3. Rebuild o projeto

Isso geralmente resolve o problema do jlink!

