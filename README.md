# Disciplinacrud

## Descrição

Disciplinacrud é uma aplicação CRUD (Create, Read, Update, Delete) para gerenciar estudantes, notas e disciplinas. A aplicação é construída usando Spring Boot, Spring Data JPA, Spring Security, e MySQL, e está configurada para ser implantada no Heroku.

## Funcionalidades

- **Gerenciamento de Estudantes**: Criação, leitura, atualização e exclusão de registros de estudantes.
- **Gerenciamento de Disciplinas**: Criação, leitura, atualização e exclusão de registros de disciplinas.
- **Gerenciamento de Notas**: Criação, leitura, atualização e exclusão de registros de notas.
- **Autenticação e Autorização**: Utiliza Spring Security para proteger os endpoints da API.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.0
- Spring Data JPA
- Spring Security
- MySQL
- Maven
- Heroku

## Configuração e Deploy

### Pré-requisitos

- JDK 17
- Maven
- Conta no Heroku
- MySQL (externo ou local)

### Configuração do Banco de Dados

Configure o MySQL externo adicionando as seguintes variáveis de ambiente no Heroku:

| Nome da Variável | Valor |
| ---------------- | ----- |
| `SPRING_DATASOURCE_URL` | `jdbc:mysql://sql10.freesqldatabase.com:3306/xxxxxxxx` |
| `SPRING_DATASOURCE_USERNAME` | `xxxxxxxxx` |
| `SPRING_DATASOURCE_PASSWORD` | `xxxxxxxxx` |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | `update` |
| `SPRING_JPA_SHOW_SQL` | `true` |
| `SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT` | `org.hibernate.dialect.MySQLDialect` |

### Deploy no Heroku

1. Faça login no Heroku e crie um novo app.
2. Conecte o app ao seu repositório no GitHub.
3. Adicione as variáveis de ambiente necessárias no Heroku.
4. Realize o deploy manual através da aba `Deploy`.

### Arquivo `application.properties`

Certifique-se de que seu arquivo `application.properties` esteja configurado para usar as variáveis de ambiente:

```properties
# DataSource settings
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}

# Hibernate settings
spring.jpa.hibernate.ddl-auto=${SPRING_JPA_HIBERNATE_DDL_AUTO}
spring.jpa.show-sql=${SPRING_JPA_SHOW_SQL}
spring.jpa.properties.hibernate.dialect=${SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT}
````

### Endpoints da API

A aplicação fornece os seguintes endpoints da API:

#### Estudantes

- **Criar um novo estudante**
  - **Endpoint**: `POST /api/students`
  - **Exemplo de Request Body**:
    ```json
    {
      "name": "Test Student",
      "cpf": "123.456.789-00",
      "email": "test@student.com",
      "phone": "1234567890",
      "address": "123 Test St"
    }
    ```
- **Listar todos os estudantes**
  - **Endpoint**: `GET /api/students`

#### Disciplinas

- **Criar uma nova disciplina**
  - **Endpoint**: `POST /api/subjects`
  - **Exemplo de Request Body**:
    ```json
    {
      "name": "Math",
      "code": "MTH101"
    }
    ```
- **Listar todas as disciplinas**
  - **Endpoint**: `GET /api/subjects`

#### Notas

- **Criar uma nova nota**
  - **Endpoint**: `POST /api/grades`
  - **Exemplo de Request Body**:
    ```json
    {
      "student": {"id": 1},
      "subject": {"id": 1},
      "value": 8.0
    }
    ```
- **Listar todas as notas aprovadas**
  - **Endpoint**: `GET /api/grades/approved`
  - **Parâmetros de Query**:
    - `subjectId`: ID da disciplina
- **Listar todas as notas reprovadas**
  - **Endpoint**: `GET /api/grades/failed`
  - **Parâmetros de Query**:
    - `subjectId`: ID da disciplina

