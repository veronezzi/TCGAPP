# 📋 Como Usar os Templates do GitHub

## ✅ Funcionamento Automático

Os templates que criamos funcionam **automaticamente**! Você não precisa fazer nada além de commitá-los no repositório.

## 📁 Arquivos Criados

```
.github/
├── pull_request_template.md      # Template de Pull Request
└── ISSUE_TEMPLATE/
    ├── bug_report.md             # Template para reportar bugs
    ├── feature_request.md        # Template para solicitar funcionalidades
    └── config.yml                # Configuração dos templates
```

## 🚀 Como Funciona

### Pull Request Template

1. **Após fazer push** dos arquivos para o GitHub
2. Quando você criar um **novo Pull Request**
3. O GitHub **automaticamente** preencherá o template no corpo do PR
4. Você só precisa **preencher os campos** com suas informações

### Issue Templates

1. Quando alguém for criar uma **nova Issue**
2. O GitHub mostrará **opções** para escolher:
   - 🐛 Bug Report
   - ✨ Feature Request
3. Ao escolher, o template correspondente será carregado automaticamente

## ⚠️ Importante

- Os templates precisam estar na **branch padrão** (geralmente `main` ou `master`)
- Você precisa fazer **commit e push** desses arquivos para o GitHub
- O GitHub reconhece automaticamente arquivos em `.github/`

## 📝 Próximos Passos

1. Faça commit dos arquivos:
```bash
git add .github/
git commit -m "feat: adicionar templates de PR e Issues"
git push
```

2. Teste criando um PR - o template aparecerá automaticamente!

## 🔍 Verificação

Para verificar se está funcionando:
1. Vá até o repositório no GitHub
2. Tente criar um novo Pull Request
3. O template deve aparecer automaticamente no campo de descrição

---

**Nota**: Se você ainda não tem um repositório Git inicializado, você pode criar um ou os templates ficarão prontos para quando você criar!

