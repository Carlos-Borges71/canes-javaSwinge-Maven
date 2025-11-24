# LeiloesTDSat

Projeto Java para gerenciamento de produtos (CRUD) usando Swing/NetBeans e conexão com banco de dados via DAO/DTO.

## 📁 Estrutura do Projeto

```
LeiloesTDSat/
 ├── src/
 │   ├── cadastroVIEW.java
 │   ├── listagemVIEW.java
 │   ├── ProdutosDAO.java
 │   ├── ProdutosDTO.java
 │   ├── conectaDAO.java
 │   └── *.form
 ├── lib/
 ├── build.xml
 └── manifest.mf
```

## 🧱 Tecnologias Utilizadas
- Java SE
- Swing (interfaces .form)
- DAO/DTO
- NetBeans
- Banco de Dados (via `conectaDAO`)

## 🚀 Funcionalidades
- Cadastro de produtos
- Listagem de produtos
- Alteração
- Exclusão
- Conexão com banco e operações CRUD

## ▶️ Como Executar
1. Abra o projeto no NetBeans.
2. Configure o banco de dados conforme sua instalação.
3. Ajuste as credenciais dentro de `conectaDAO.java`.
4. Execute o projeto pelo arquivo principal.

## 🗄️ Classes Principais

### `ProdutosDTO.java`
Contém o modelo de dados do produto.

### `ProdutosDAO.java`
Realiza inserção, remoção, alteração e listagem no banco.

### `cadastroVIEW.java`
Tela para cadastrar/editar produtos.

### `listagemVIEW.java`
Tela que lista os produtos e permite excluir/alterar.

### `conectaDAO.java`
Realiza a conexão com o banco de dados.

## 📦 Build
O projeto utiliza `build.xml` (Ant) gerado pelo NetBeans.

## 📘 Licença
Projeto educacional desenvolvido para fins de estudo.
