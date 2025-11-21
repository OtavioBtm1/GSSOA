# Sprint 1
# Sistema de Cadastro de Usuários

## Descrição do Projeto

Este projeto é uma aplicação Java que permite o cadastro e gerenciamento de usuários. Ele possui um menu principal com opções para registrar novos usuários, realizar login e um modo administrador para operações avançadas, como listar todos os usuários, buscar usuários por ID e deletar usuários.

Descrição do Projeto

Este projeto faz parte de uma plataforma digital voltada para o futuro do trabalho, integrando tecnologia de IA e desenvolvimento humano. Ele tem como objetivo gerenciar o cadastro de usuários, armazenando informações essenciais para que o sistema de recomendação, trilhas de aprendizado e networking funcione de forma personalizada.

O sistema inclui:

Cadastro de usuários com dados pessoais e profissionais.

Armazenamento seguro em banco de dados.

Preparação de dados para integração com IA e sistema de matching futuro.

O projeto segue a metodologia Scrum, com backlog estruturado em Épicos, Features e PBIs, critérios de aceite claros e prioridade definida para entrega contínua de valor.

Funcionalidades

Criar usuário com dados de perfil (nome, email, habilidades, experiência).

Editar e atualizar informações do usuário.

Listar usuários cadastrados.

Validar campos obrigatórios no cadastro.

O sistema foi desenvolvido utilizando Java 17+, com arquitetura em camadas (Controller, Service, DAO) e comunicação via REST APIs.

---

## Funcionalidades

- Cadastro de novos usuários (nome, email e senha)
- Login de usuários com validação
- Modo administrador com:
  - Listagem de todos os usuários cadastrados
  - Busca de usuário por ID
  - Exclusão de usuários
- Comunicação via HTTP com API RESTful
- Validação das requisições com respostas HTTP apropriadas
- Interface gráfica simples para login

---

## Como Rodar o Projeto

### Requisitos

- Java 17 ou superior instalado
- Maven instalado
- IDE de sua preferência (IntelliJ, Eclipse, VSCode) ou terminal

### Passo a passo

1. **Clonar o repositório**

   ```bash
   git clone https://github.com/OtavioBtm1/SOASprint.git

2. **Entrar na pasta do projeto**
   
   ```bash
   cd demo
   
3. ** Build do projeto**
   
   ```bash
   mvn clean install

4. **Rodar o app**
   
   ```bash
   mvn spring-boot:run

5. **Acessar a aplicação**

- A API estará rodando em:  
  `http://localhost:8080`

- Para registrar um usuário, envie um POST para:  
  `http://localhost:8080/usuarios/register`

- Para fazer login, envie um POST para:  
  `http://localhost:8080/usuarios/login`

- Modo administrador: use os endpoints para listar, buscar e deletar usuários.

### Interface Web

- Para acessar a interface gráfica simples de login:  
  `http://localhost:8080/login.html`

- Nela, você pode realizar login e receberá sinal verde se for sucesso ou um X vermelho se falhar.

### Documentação da API

- Disponível em:  
  `http://localhost:8080/swagger-ui.html`

###W Equipe
- Maitê Savicius Menezes RM98435
- Murilo Henrique Obinata RM99855
- Otavio Vitoriano da Silva RM552012
- Eduardo Fedeli Souza RM550132
- Gabriel Torres Luiz RM98600

###Observações

- O projeto usa banco de dados H2 em memória para facilitar testes.

- Use Postman ou Insomnia para testar os endpoints REST.

- Spring Security já configurado para autenticação básica.

- A arquitetura segue boas práticas, usando DAO, Service e Controller.


