# Melhorias na Identificação de Cartas

## Problema
O app escaneia mas não identifica a carta corretamente.

## ✅ Melhorias Aplicadas

### 1. Busca com Múltiplos Formatos
Agora o `PokemonCardRepository` tenta buscar a carta com diferentes formatos:
- Se detectou "025/198": tenta ["025/198", "25/198", "25", "025"]
- Se detectou "25": tenta ["25", "025"]

Isso aumenta as chances de encontrar a carta mesmo se o OCR não extrair o formato exato.

### 2. Normalização Melhorada
- Remove padding desnecessário quando possível
- Mantém o formato original quando funciona
- Tenta variações automaticamente

### 3. Logs Detalhados
Adicionei logs para facilitar debug:
- Log do número extraído pelo OCR
- Log de cada tentativa de busca
- Log do formato que funcionou

## 🔍 Como Debugar

Se ainda não estiver funcionando, verifique os logs do Logcat:
1. Filtre por tag: "CardNumberOcr" e "PokemonCardRepository"
2. Veja:
   - Qual texto foi extraído pelo OCR
   - Quais formatos foram tentados
   - Se houve resposta da API
   - Qual erro específico ocorreu

## 📝 Próximos Passos (se necessário)

Se ainda não funcionar, pode ser necessário:
1. Ajustar a região de crop (X: 0-45%, Y: 70-100%)
2. Melhorar a qualidade da imagem antes do OCR (contraste, brilho)
3. Tentar diferentes estratégias de OCR (ML Kit vs Tesseract)

Teste novamente e verifique os logs!

