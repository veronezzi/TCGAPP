# Correção: Unresolved reference: R

## Problema
O erro "Unresolved reference: R" ocorre quando:
1. O arquivo R.java não foi gerado (geralmente por erros de recursos)
2. Imports incorretos ou faltando
3. Namespace incorreto

## Correções Aplicadas

### 1. ✅ Imports do R
Adicionei imports corretos em todos os arquivos:
```kotlin
import com.seuapp.pokescanner.R
```

### 2. ✅ Uso correto do R
Substituí referências completas por import:
- `com.seuapp.pokescanner.R.id.xxx` → `R.id.xxx`
- `com.seuapp.pokescanner.R.string.xxx` → `R.string.xxx`

## Arquivos Corrigidos

- ✅ ScannerFragment.kt
- ✅ CollectionFragment.kt  
- ✅ CardDetailFragment.kt
- ✅ MainActivity.kt (já estava correto)

## Próximos Passos

1. **Limpar e Rebuild**:
   ```bash
   ./gradlew clean
   ./gradlew assembleDebug
   ```

2. **No Android Studio**:
   - Build > Clean Project
   - Build > Rebuild Project

3. **Se ainda houver erro**, verifique:
   - Se há erros de recursos nos arquivos XML
   - Se o namespace está correto no AndroidManifest
   - Se há problemas de sintaxe nos layouts

O erro deve estar resolvido agora! Se persistir, pode ser necessário verificar se há erros de recursos que impedem a geração do arquivo R.

