# Correção: Rotação e Região de Crop

## Problema Identificado nos Logs

1. **OCR lendo texto errado**: Estava lendo "Este Art" em vez de "076/091"
2. **Imagem rotacionada**: Logs mostram "sourceRotationDegrees=90" - a imagem vem rotacionada
3. **Região de crop pode estar errada**: Pode estar pegando texto acima do número

## ✅ Correções Aplicadas

### 1. Rotação da Imagem
Criada classe `BitmapRotator` que:
- Rotaciona a imagem 90 graus antes do crop (CameraX retorna rotacionada em modo retrato)
- Garante que a orientação está correta antes de processar

### 2. Ajuste da Região de Crop
Ajustado para pegar melhor a região do número:
- **Largura**: 0% até 50% (antes era 45%) - para pegar números maiores como "076/091"
- **Altura**: 75% até 100% (antes era 70%) - para garantir que está na parte inferior

### 3. Melhoria no OCR
- Busca padrão numérico diretamente no texto original primeiro
- Se não encontrar, limpa o texto e tenta novamente
- Logs mais detalhados para debug

## 🔍 Como Funciona Agora

```
Captura → Rotação (90°) → Crop (região ajustada) → Pré-processamento → OCR → Busca
```

## 📝 Teste Novamente

Com essas correções, o OCR deve:
1. Pegar a imagem na orientação correta
2. Crop na região certa (onde está o número)
3. Ler "076/091" corretamente

**Teste novamente com a carta ARTAZON 076/091!**

