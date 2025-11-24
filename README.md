Canes — Sistema de Gestão (Java Swing + DAO)

Este projeto é uma aplicação desktop desenvolvida em Java Swing, utilizando o padrão DAO (Data Access Object) para persistência de dados, além de uma estrutura organizada em camadas para facilitar manutenção, testes e expansão do sistema.

O sistema inclui módulos de Clientes, Produtos, Usuários e Telefones, além de utilitários de confirmação e permissões.

 Funcionalidades Principais

Cadastro de Clientes
Cadastro de Produtos
Gerenciamento de Usuários (com permissões)
Cadastro de Telefones
Interface em Java Swing
Padrão DAO com listas em memória
Confirmação de ações antes de excluir
Validações diversas na camada View


 Tecnologias Utilizadas

Java 17+
Swing (GUI Desktop)
Maven
Padrão DAO
POO (Herança, encapsulamento, polimorfismo)


Como Executar o Projeto
1. Clonar o repositório

git clone https://github.com/Carlos-Borges71/canes-javaSwinge-Maven

2. Abrir no IntelliJ, Eclipse ou NetBeans

O projeto é Maven, então qualquer IDE reconhecerá automaticamente.

3. Executar a classe principal

src/main/java/com/app/canes/Canes.java

 Camadas do Sistema

Model
Contém as classes de entidades como:
Cliente
ClienteContato
Usuário
Produto
Telefone
DAO
Gerencia as operações de CRUD: 
save()
update()
delete()
findAll()
findById()
View
Telas desenvolvidas em Java Swing:
Cadastro
Listagens
Edição
Ações de CRUD
Util

Ferramentas auxiliares:
Classe	Função
ConfirmUtil	Exibe diálogos de confirmação para exclusão e ações perigosas
PermissaoUtil	Verifica e aplica permissões de acesso por usuário

Sistema de Permissões

O projeto possui uma estrutura simples para controle de acesso:

Gerência (todas permussões)
Administrador
Usuário comum
A classe PermissaoUtil controla quais botões/ações cada usuário pode acessar.


Persistência

(MySQL/PostgreSQL)
JDBC
JPA/Hibernate

Autor

Carlos Borges
Projeto criado para fins de estudo e desenvolvimento de habilidades em Java.
