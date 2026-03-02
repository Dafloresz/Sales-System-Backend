# 🚀 Workshop Spring Boot + JPA

Projeto backend desenvolvido com **Java** e **Spring Boot**, utilizando **Spring Data JPA** para persistência de dados em banco relacional.

Este projeto tem como objetivo demonstrar a construção de uma **API RESTful** completa com operações de **CRUD**, utilizando boas práticas do ecossistema Spring.

---

## 🛠 Tecnologias Utilizadas

- ☕ **Java**
- 🌱 **Spring Boot**
- 🌐 **Spring Web (Spring MVC)** – construção de API REST
- 🗄 **Spring Data JPA** – persistência de dados
- 🐘 **PostgreSQL** – banco de dados relacional
- 🧪 **H2 Database** – banco em memória para testes
- 📦 **Maven** – gerenciamento de dependências e build

---

## 📌 Funcionalidades

- 📍 API RESTful
- 📂 Operações CRUD completas:
  - Criar
  - Buscar
  - Atualizar
  - Deletar
- 🔗 Integração com banco de dados relacional
- 🧠 Mapeamento objeto-relacional (ORM) com JPA/Hibernate
- 🧪 Ambiente de testes com banco H2

---

## 📁 Estrutura do Projeto
```bash
src
├── main
│ ├── java
│ │ ├── entities
│ │ ├── repositories
│ │ ├── services
│ │ └── resources (controllers)
│ └── resources
│ └── application.properties
└── test
```

---

## ▶️ Como Executar o Projeto

### ✅ Pré-requisitos

- Java 17+ (ou versão configurada no projeto)
- Maven
- PostgreSQL (opcional, caso não use H2)

### 🔧 Passos

```bash
# Clonar o repositório
git clone https://github.com/Dafloresz/workshop-springboot-jpa.git

# Entrar na pasta do projeto
cd workshop-springboot-jpa

# Executar a aplicação
mvn spring-boot:run

# A aplicação iniciará por padrão em:
http://localhost:8080

```

🎯 Objetivo do Projeto

- Este projeto foi desenvolvido com foco educacional para praticar:
- Criação de APIs REST com Spring Boot
- Uso do Spring Data JPA
- Relacionamentos entre entidades
- Configuração de múltiplos bancos de dados
- Estruturação de aplicações backend em camadas

📚 Aprendizados

- Injeção de dependência
- Padrão Repository
- Controle de exceções
- Configuração de ambiente (dev/test)


Desenvolvido para fins de estudo e prática com o ecossistema Spring.


