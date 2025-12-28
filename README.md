# 🎴 Pokemon Card Scanner

<div align="center">

![Pokemon Card Scanner](https://img.shields.io/badge/Pokemon-TCG%20Scanner-red?style=for-the-badge&logo=pokemon)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)

**Aplicativo Android para escanear e identificar cartas Pokémon TCG usando OCR e câmera**

[Funcionalidades](#-funcionalidades) • [Tecnologias](#-tecnologias) • [Arquitetura](#-arquitetura) • [Como Usar](#-como-usar)

</div>

---

## 📱 Sobre o App

O **Pokemon Card Scanner** é um aplicativo Android que permite escanear cartas Pokémon TCG usando a câmera do dispositivo. Através de tecnologias de OCR (Optical Character Recognition) e processamento de imagem, o app identifica o número da carta e oferece opções para pesquisar informações detalhadas em diferentes plataformas.

### 🎯 Funcionalidades Principais

- 🔍 **Scanner Inteligente**: Escaneia cartas Pokémon usando a câmera do dispositivo
- 📸 **OCR Avançado**: Reconhecimento de texto com ML Kit para detectar números das cartas
- 🔧 **Pré-processamento de Imagem**: Melhora contraste, brilho e converte para escala de cinza para maior precisão
- 🎴 **Detecção Automática**: Identifica o número da carta no canto inferior esquerdo
- 🌐 **Integração Liga Pokemon**: Busca rápida de cartas na plataforma Liga Pokemon
- 🎨 **Interface Moderna**: Design dark theme inspirado no app Shiny com Material 3
- 📱 **Navegação Intuitiva**: Bottom navigation com Scanner, Coleção e Configurações

### 🔬 Como Funciona

1. **Captura**: O app captura uma imagem da carta usando CameraX
2. **Processamento**: A imagem é rotacionada, recortada na região do número (canto inferior esquerdo) e pré-processada
3. **OCR**: ML Kit Text Recognition extrai o número da carta (ex: `076/091`)
4. **Correção**: Corrige automaticamente erros comuns de OCR (ex: "o" → "0")
5. **Confirmação**: Diálogo pergunta se deseja pesquisar a carta detectada
6. **Busca**: Opções para pesquisar em diferentes plataformas (Liga Pokemon, etc.)

## 🛠 Tecnologias

### Core
- **Kotlin** - Linguagem principal
- **Android SDK** - API 26+ (Android 8.0+)

### UI/UX
- **Material 3** - Design system moderno
- **Jetpack Compose** (XML Layouts + Fragments)
- **ConstraintLayout** - Layouts flexíveis
- **ViewBinding** - Binding type-safe

### Camera & Image Processing
- **CameraX** - Biblioteca moderna de câmera
- **ML Kit Text Recognition** - OCR on-device
- **Bitmap Processing** - Rotação, crop e pré-processamento de imagens

### Arquitetura
- **MVVM** - Model-View-ViewModel pattern
- **Kotlin Flow** - Reactive programming
- **Hilt** - Dependency Injection
- **Navigation Component** - Navegação entre telas

### Networking
- **Retrofit** - HTTP client type-safe
- **OkHttp** - Cliente HTTP eficiente
- **Gson** - Serialização JSON

### Outras
- **Coil** - Carregamento de imagens
- **Coroutines** - Programação assíncrona

## 🏗 Arquitetura

O projeto segue os princípios de **Clean Architecture** com separação clara de responsabilidades:

```
app/
├── core/              # Funcionalidades core (camera, OCR, image processing)
│   ├── camera/       # CameraX provider
│   ├── crop/         # Recorte de imagem
│   ├── image/        # Pré-processamento e rotação
│   └── ocr/          # Reconhecimento de texto
├── data/             # Camada de dados
│   ├── remote/       # API e DTOs
│   └── repository/   # Repositórios
├── domain/           # Modelos de domínio
│   └── model/        # Entidades do domínio
└── ui/               # Interface do usuário
    ├── scanner/      # Tela de scanner
    ├── collection/   # Tela de coleção
    ├── detail/       # Detalhes da carta
    ├── settings/     # Configurações
    └── main/         # Activity principal
```

### Padrões de Design

- **Repository Pattern** - Abstraction da fonte de dados
- **MVVM** - Separação de lógica de apresentação
- **Dependency Injection** - Injeção de dependências com Hilt
- **Observer Pattern** - Kotlin Flow para reatividade

## 🚀 Como Usar

### Requisitos

- Android Studio Hedgehog (2023.1.1) ou superior
- JDK 17
- Android SDK 26+ (minSdk 26)
- Dispositivo Android com câmera

### Instalação

1. Clone o repositório:
```bash
git clone <repository-url>
cd TCGAPP
```

2. Abra o projeto no Android Studio

3. Sincronize o Gradle e aguarde o download das dependências

4. Execute o app em um dispositivo físico ou emulador

### Como Escanear uma Carta

1. Abra o app e permita acesso à câmera
2. Posicione a carta dentro do frame verde na tela
3. Toque no botão **"Escanear"**
4. Aguarde a detecção do número da carta
5. Quando detectado, escolha onde pesquisar (Liga Pokemon, etc.)

### Dicas para Melhor Detecção

- 💡 **Iluminação**: Use boa iluminação, evite sombras
- 📐 **Posicionamento**: Centralize a carta no frame verde
- 🎴 **Foco**: Certifique-se de que a carta está em foco
- 📏 **Distância**: Mantenha uma distância adequada (não muito perto nem muito longe)

## 📦 Estrutura do Projeto

```
TCGAPP/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/seuapp/pokescanner/
│   │   │   │   ├── core/          # Lógica core
│   │   │   │   ├── data/          # Camada de dados
│   │   │   │   ├── domain/        # Modelos de domínio
│   │   │   │   ├── di/            # Dependency Injection
│   │   │   │   └── ui/            # Interface do usuário
│   │   │   ├── res/               # Recursos (layouts, drawables, strings)
│   │   │   └── AndroidManifest.xml
│   │   └── test/                  # Testes unitários
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── README.md
```

## 🔧 Configuração

### Permissões

O app requer as seguintes permissões (já configuradas no `AndroidManifest.xml`):

- `CAMERA` - Para capturar imagens das cartas
- `INTERNET` - Para buscar informações na API

### API Keys

Atualmente, o app usa a [Pokemon TCG API](https://pokemontcg.io/) que é pública e não requer autenticação.

## 🎨 Screenshots

_(Adicione screenshots do app aqui quando disponíveis)_

## 🗺 Roadmap

- [ ] Integração com mais plataformas de pesquisa
- [ ] Banco de dados local (Room) para cache e coleção
- [ ] Histórico de cartas escaneadas
- [ ] Compartilhamento de cartas
- [ ] Suporte offline
- [ ] Melhorias na precisão do OCR

## 📄 Licença

Este projeto é de código aberto. Sinta-se livre para usar e modificar.

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

## 📞 Suporte

Para dúvidas ou problemas, abra uma issue no repositório.

---

<div align="center">

**Desenvolvido com ❤️ para a comunidade Pokémon TCG**

⭐ Se este projeto te ajudou, considere dar uma estrela!

</div>
