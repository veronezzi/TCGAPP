# Correção de Erro: Android Resource Linking Failed

## Problema
O erro "Android resource linking failed" geralmente ocorre quando:
1. Recursos faltando (cores, strings, drawables, etc)
2. Referências incorretas em XML
3. Namespace incorreto

## Correções Aplicadas

### 1. Ícones do Bottom Navigation
✅ Criei ícones personalizados (vector drawables):
- `ic_scanner.xml` - Ícone de câmera/scanner
- `ic_collection.xml` - Ícone de coleção
- `ic_settings.xml` - Ícone de configurações

Substituí os ícones do Android (`@android:drawable/...`) por ícones customizados.

### 2. Recursos Verificados
✅ Todos os recursos estão presentes:
- Colors: green_500, white, dark_background, etc.
- Strings: todas definidas em strings.xml
- Drawables: scanner_guide_frame, pill_background
- Layouts: todos os layouts criados

## Próximos Passos

1. **Limpar e Rebuild**:
   ```bash
   ./gradlew clean
   ./gradlew assembleDebug
   ```

2. **Se ainda houver erro**, verifique:
   - Mensagem de erro completa no log
   - Recursos específicos que estão faltando
   - Versão do compileSdk e targetSdk

3. **No Android Studio**:
   - Build > Clean Project
   - Build > Rebuild Project
   - Verificar a aba "Build" para erros específicos

O problema mais comum era os ícones do Android que podem não estar disponíveis em todas as versões. Agora usando ícones personalizados, deve funcionar!

