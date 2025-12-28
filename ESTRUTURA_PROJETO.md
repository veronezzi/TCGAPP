# Estrutura do Projeto - Pokemon Card Scanner

## Status Atual
O projeto está vazio e precisa ser criado do zero.

## Decisões de Arquitetura

Baseado nas regras em `.cursor/rules/android.mdc` e no app Shiny como referência:

### Tecnologias
- **UI**: XML + Fragments (conforme regras, NÃO Compose)
- **ViewBinding**: Para binding de views
- **Material 3**: Design system
- **Navigation Component**: Navegação entre telas
- **CameraX**: Para captura de imagens
- **ML Kit**: Text Recognition para OCR
- **Room**: Banco de dados local
- **Retrofit**: Chamadas de API
- **Hilt**: Dependency Injection
- **Flow/LiveData**: Gerenciamento de estado
- **MVVM/MVI**: Padrão arquitetural

### Estrutura de Pastas
```
app/src/main/java/com/seuapp/pokescanner/
├── data/
│   ├── local/
│   │   ├── entity/          # Entidades Room
│   │   ├── dao/             # Data Access Objects
│   │   └── database/        # AppDatabase
│   ├── remote/
│   │   ├── api/             # Interfaces Retrofit
│   │   └── dto/             # Data Transfer Objects
│   └── repository/          # Implementação dos repositórios
├── domain/
│   ├── model/               # Modelos de domínio
│   └── usecase/             # Casos de uso
├── ui/
│   ├── scanner/             # Fragment + ViewModel do scanner
│   ├── collection/          # Fragment + ViewModel da coleção
│   ├── detail/              # Fragment + ViewModel de detalhes
│   └── main/                # MainActivity
├── core/
│   ├── camera/              # CameraX provider
│   ├── ocr/                 # OCR com ML Kit
│   └── crop/                # Crop de imagem
└── di/                      # Módulos Hilt
```

### Telas Principais (Fragmentos)
1. **ScannerFragment**: Escaneia cartas com câmera
2. **CollectionFragment**: Lista cartas na coleção
3. **CardDetailFragment**: Detalhes da carta + preços
4. **SettingsFragment**: Configurações

### MainActivity
- BottomNavigationView com 3 tabs: Scanner, Collection, Settings
- Navigation Component para navegação

### Próximos Passos
1. Criar arquivos de configuração (build.gradle.kts)
2. Criar modelos de domínio
3. Implementar camada de dados
4. Implementar UI (Fragments + XML)
5. Integrar tudo

