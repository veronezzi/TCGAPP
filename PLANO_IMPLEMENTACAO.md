# Plano de Implementação - Pokemon Card Scanner

## Objetivo
Criar app Android similar ao Shiny (TCG Card Value Scanner) que:
- Escaneia cartas Pokémon usando câmera
- Identifica cartas via OCR (número + set)
- Busca preços de mercado
- Gerencia coleção

## Arquitetura (seguindo .cursor/rules/android.mdc)

### Stack Tecnológica
- Kotlin
- XML + Fragments (não Compose, conforme regras)
- Material 3
- ViewBinding
- Navigation Component
- MVVM/MVI Pattern
- Clean Architecture
- Room Database
- Retrofit + OkHttp
- CameraX
- ML Kit Text Recognition
- Hilt (DI)

### Estrutura de Pastas
```
app/src/main/java/com/seuapp/pokescanner/
├── data/
│   ├── local/
│   │   ├── entity/
│   │   ├── dao/
│   │   └── database/
│   ├── remote/
│   │   ├── api/
│   │   └── dto/
│   └── repository/
├── domain/
│   ├── model/
│   └── usecase/
├── ui/
│   ├── scanner/
│   ├── collection/
│   ├── detail/
│   └── main/
├── core/
│   ├── camera/
│   ├── ocr/
│   └── crop/
└── di/
```

### Features Principais

1. **Scanner Screen**
   - CameraX preview
   - Botão de captura
   - Overlay com guia de posicionamento
   - Feedback visual

2. **Card Detail Screen**
   - Imagem da carta
   - Nome, set, número
   - Preços de mercado (TCGPlayer, etc)
   - Botão para adicionar à coleção

3. **Collection Screen**
   - Lista de cartas escaneadas
   - Filtros e busca
   - Valor total da coleção

4. **Main Activity**
   - BottomNavigationView
   - Tabs: Scanner, Collection, Settings

### APIs a Integrar
- Pokemon TCG API (https://pokemontcg.io/) - dados das cartas
- TCGPlayer API - preços de mercado (se disponível)
- Alternativa: Scryfall API para preços

### Implementação em Etapas

1. ✅ Configurar projeto base (Gradle, dependências)
2. ⏳ Criar estrutura de pastas e modelos de domínio
3. ⏳ Implementar CameraX provider
4. ⏳ Implementar OCR (ML Kit)
5. ⏳ Criar repositório e integração com APIs
6. ⏳ Criar UI (Fragments + XML)
7. ⏳ Implementar Room para cache e coleção
8. ⏳ Integrar tudo e testar

