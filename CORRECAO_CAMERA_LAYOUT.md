# Correção: Layout da Câmera

## Problema
A câmera estava aparecendo apenas em um pequeno trecho no canto superior da tela, não ocupando toda a área.

## ✅ Correção Aplicada

Ajustei as constraints do `PreviewView` para ocupar toda a tela:
- Antes: `app:layout_constraintBottom_toTopOf="@+id/guide_frame"` (limitava ao guide_frame)
- Agora: `app:layout_constraintBottom_toBottomOf="parent"` (ocupa toda a tela)

## Efeito Visual

Agora:
- ✅ A câmera ocupa **toda a tela** de fundo
- ✅ O `guide_frame` fica **sobreposto** como um overlay
- ✅ Adicionei overlays escuros ao redor do guide_frame para destacar a área de captura (similar ao TCGplayer)

## Resultado

A experiência visual agora é:
- Preview da câmera em tela cheia
- Frame verde indicando onde posicionar a carta
- Overlays escuros destacando a área de captura

A câmera agora deve ocupar toda a tela corretamente!

