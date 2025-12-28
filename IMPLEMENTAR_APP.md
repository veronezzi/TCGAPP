# Instruções para Implementar o App

## Situação Atual
O projeto Android está completamente vazio. Todos os arquivos essenciais precisam ser criados do zero.

## Requisitos
- App similar ao Shiny (TCG Card Value Scanner)
- Escanear cartas Pokémon
- Identificar preços
- Seguir regras em `.cursor/rules/android.mdc`
- Usar XML + Fragments (não Compose, conforme regras)
- Material 3 + Dark Theme
- Clean Architecture + MVVM

## Próximos Passos

Preciso que você confirme:
1. O projeto está realmente vazio ou há algum arquivo de configuração que não estou vendo?
2. Você quer que eu crie TUDO do zero agora, ou prefere que eu crie apenas os arquivos essenciais primeiro?
3. As regras dizem para usar XML + Fragments, mas o projeto anterior usava Compose. Você quer seguir as regras (XML/Fragments) ou manter Compose?

## Recomendação
Sugiro criar os arquivos essenciais primeiro em etapas:
1. Configuração (Gradle, manifest)
2. Models e estrutura de dados
3. Core (Camera, OCR)
4. UI básica (MainActivity + Fragments)
5. Integração completa

Isso garantirá que tudo funcione passo a passo.

