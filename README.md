# 📅 Agendador de Tarefas

API RESTful para agendamento e gerenciamento de tarefas, desenvolvida com **Java 17**, **Spring Boot** e persistência em **MongoDB**.

---

## 🚀 Tecnologias

O projeto utiliza as seguintes tecnologias:

* **Java 17**
* **Spring Boot 3.x**
* **Spring Data MongoDB** (Persistência de dados)
* **Gradle** (Gerenciador de dependências)
* **Docker & Docker Compose** (Containerização)
* **Swagger/OpenAPI** (Documentação da API)
* **Lombok** (Produtividade no código)

---

## 📋 Pré-requisitos

Antes de começar, você precisará ter instalado:
* [JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Docker & Docker Compose](https://www.docker.com/products/docker-desktop/) (opcional, para rodar via container)
* [Gradle](https://gradle.org/install/) (ou use o wrapper `./gradlew` incluso)

---

## ⚙️ Como rodar o projeto

### 1. Localmente
Primeiro, certifique-se de que o seu MongoDB está rodando em `localhost:27017`.

```bash
# Clone o repositório
git clone [https://github.com/CauaSean/agendador.git](https://github.com/CauaSean/agendador.git)

# Acesse o diretório
cd agendador

# Build do projeto
./gradlew build

# Rode a aplicação
./gradlew bootRun
```

### 🔧 Variáveis de Ambiente

| Variável | Descrição | Padrão      |
| :--- | :--- |:------------|
| `SERVER_PORT` | Porta da aplicação | `9000`      |
| `DB_URL` | Host do MongoDB | `localhost` |
| `DB_USUARIO` | Usuário do banco | `admin`     |
| `DB_PASSWORD` | Senha do banco | `...`       |

⚠️ Acesso ao Banco de Dados:

Host: localhost

Porta: 27017

Database: db_agendador


📌 Endpoints

Documentação completa disponível via Swagger em: http://localhost:9000/swagger-ui.html

| Método | Rota | Descrição | Parâmetros/Headers |
| :--- | :--- | :--- | :--- |
| **POST** | `/tarefas` | Cria uma nova tarefa | Body (JSON) + Auth Token |
| **GET** | `/tarefas` | Lista tarefas do usuário logado | Auth Token |
| **GET** | `/tarefas/eventos` | Busca por período | `dataInicial`, `dataFinal` |
| **PUT** | `/tarefas` | Atualiza dados da tarefa | `?id={id}` + Body |
| **PATCH** | `/tarefas` | Altera apenas o status | `?status={status}&id={id}` |
| **DELETE** | `/tarefas` | Remove uma tarefa | `?id={id}` |


🧪 Testes
```bash
./gradlew test
```