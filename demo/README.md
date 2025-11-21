# 📦 PedidoApp - API REST para Gestão de Pedidos

# Nome dos integrantes
- **Otavio Vitoriano da Silva (RM552012)**
- **Maitê Savicius Menezes (RM98435)**
- **Murilo Henrique Obinata (RM99855)**


## 📖 Sobre o Projeto
O **PedidoApp** é uma API REST desenvolvida em **Spring Boot** para gerenciar pedidos, permitindo a criação, listagem, busca, atualização e remoção de pedidos.

## 🚀 Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database**
- **Maven**

## 📁 Estrutura do Projeto
```bash
src
│── main
│   ├── java
│   │   ├── com.fiap.checkpoint1
│   │   │   ├── controller
│   │   │   │   ├── PedidoController.java
│   │   │   ├── model
│   │   │   │   ├── Pedido.java
│   │   │   ├── repository
│   │   │   │   ├── PedidoRepository.java
│   │   │   ├── service
│   │   │   │   ├── PedidoService.java
│   │   │   ├── PedidoApp.java
│   │   │   ├── Application.java
│   ├── resources
│── test
│── pom.xml
│── README.md
```

## 🔧 Como Executar o Projeto

### ✅ Pré-requisitos
- **Java 17** ou superior
- **Maven** instalado

### ▶️ Rodando o Projeto
```bash
git clone https://github.com/OtavioBtm1/PedidoApp.git
cd PedidoApp
mvn spring-boot:run
```

Acesse a API em: `http://localhost:8080/pedidos`

## 📌 Endpoints da API

### 📝 Criar um Pedido  
**`POST /pedidos`**

#### 📥 Requisição:
```json
{
  "nomeCliente": "João Silva",
  "valorTotal": 150.75
}
```

#### 📤 Resposta (201 - Created):
```json
{
  "id": 1,
  "nomeCliente": "João Silva",
  "valorTotal": 150.75
}
```

### 📋 Listar Todos os Pedidos  
**`GET /pedidos`**

#### 📤 Resposta (200 - OK):
```json
[
  {
    "id": 1,
    "nomeCliente": "João Silva",
    "valorTotal": 150.75
  },
  {
    "id": 2,
    "nomeCliente": "Maria Souza",
    "valorTotal": 200.00
  }
]
```

### 🔎 Buscar Pedido por ID  
**`GET /pedidos/{id}`**

#### 📤 Resposta (200 - OK):
```json
{
  "id": 1,
  "nomeCliente": "João Silva",
  "valorTotal": 150.75
}
```

#### 📤 Resposta (404 - Not Found):
```json
{
  "erro": "Pedido não encontrado"
}
```

### ✏️ Atualizar um Pedido  
**`PUT /pedidos/{id}`**

#### 📥 Requisição:
```json
{
  "nomeCliente": "Carlos Almeida",
  "valorTotal": 180.00
}
```

#### 📤 Resposta (200 - OK):
```json
{
  "id": 1,
  "nomeCliente": "Carlos Almeida",
  "valorTotal": 180.00
}
```

#### 📤 Resposta (404 - Not Found):
```json
{
  "erro": "Pedido não encontrado"
}
```

### 🗑️ Deletar um Pedido  
**`DELETE /pedidos/{id}`**


#### 📤 Resposta (404 - Not Found):
```json
{
  "erro": "Pedido não encontrado"
}
```

## 🛠️ Configuração do Banco de Dados

A aplicação usa o banco de dados em memória **H2**. As configurações padrão são:

- **URL:** `jdbc:h2:mem:pedidodb`
- **Usuário:** `sa`
- **Senha:** _(vazia)_

Para acessar o banco via navegador:
- Acesse: `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:pedidodb`
- **Driver Class:** `org.h2.Driver`

## 🔗 Testando a API com Postman
Para testar a API no **Postman**, importe a seguinte coleção:
📥 [Baixar Coleção do Postman](https://www.getpostman.com/collections/seu-link)

## 📄 Licença
Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

### 💡 Dúvidas ou Sugestões?
Sinta-se à vontade para contribuir ou abrir uma issue no repositório! 🚀