# Implementação Inicial - Pokemon Card Scanner

## Nota Importante
O projeto está completamente vazio. Preciso criar toda a estrutura do zero.

## Abordagem
Vou criar os arquivos essenciais primeiro para ter uma base funcional, seguindo:
- Regras do `.cursor/rules/android.mdc` (XML + Fragments)
- Design similar ao app Shiny (dark theme, Material 3)
- Clean Architecture
- MVVM/MVI Pattern

## Arquivos a Criar (Ordem de Prioridade)

### 1. Configuração Base
- [ ] build.gradle.kts (root e app)
- [ ] settings.gradle.kts
- [ ] AndroidManifest.xml
- [ ] gradle/libs.versions.toml

### 2. Models e Domain
- [ ] PokemonCard (domain model)
- [ ] CardPrice (domain model)
- [ ] Collection (domain model)

### 3. Data Layer
- [ ] PokemonTcgApi (Retrofit interface)
- [ ] CardDto (DTO)
- [ ] CardRepository
- [ ] Room entities e DAOs

### 4. Core
- [ ] CameraProvider (CameraX)
- [ ] CardOcr (ML Kit)
- [ ] ImageCropper

### 5. UI Layer
- [ ] MainActivity
- [ ] ScannerFragment + layout XML
- [ ] CollectionFragment + layout XML
- [ ] CardDetailFragment + layout XML
- [ ] ViewModels (MVVM)
- [ ] Navigation graph

### 6. DI
- [ ] AppModule (Hilt)

Vou começar criando esses arquivos na ordem acima.

