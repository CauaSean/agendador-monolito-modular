📅 Agendador de Tarefas
API RESTful para agendamento e gerenciamento de tarefas, desenvolvida com Java e Spring Boot.

🚀 Tecnologias

Java 17+
Spring Boot
Gradle
Docker


📋 Pré-requisitos

Java 17+
Gradle (ou use o wrapper ./gradlew)
Docker e Docker Compose (opcional)


⚙️ Como rodar o projeto
Localmente
bash# Clone o repositório
git clone https://github.com/seu-usuario/seu-repositorio.git

# Build do projeto
./gradlew build

# Rode a aplicação
./gradlew bootRun
A API estará disponível em: http://localhost:9000

🐳 Com Docker
bash# Build da imagem
docker build -t tarefas.

# Rode o container
docker run -p 9000:9000 agendador
Ou com Docker Compose:
bashdocker-compose up --build

🔧 Variáveis de Ambiente
VariávelDescriçãoPadrãoSERVER_PORTPorta da aplicação8080DB_URLURL de conexão com o banco(pendente)DB_USERNAMEUsuário do banco de dados(pendente)DB_PASSWORDSenha do banco de dados(pendente)

⚠️ Banco de dados: A configuração do banco ainda está em andamento. Atualizaremos este README quando for definida.


📌 Endpoints

Documentação completa disponível via Swagger em: http://localhost:9000/swagger-ui.html

MétodoRotaDescriçãoGET/tasksLista todas as tarefasGET/tasks/{id}Busca uma tarefa por IDPOST/tasksCria uma nova tarefaPUT/tasks/{id}Atualiza uma tarefa existenteDELETE/tasks/{id}Remove uma tarefa

📁 Estrutura do Projeto
src/
├── main/
│   ├── java/
│   │   └── com/caua/agendador/
│   │       ├── business/
│   │       ├── controller/
│   │       └── infrastructure/
│   └── resources/
│       └── application.properties
└── test/

🧪 Testes
bash./gradlew test
