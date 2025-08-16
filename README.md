# 📚 ForumHub API

API REST desenvolvida em **Java 17** com **Spring Boot**, que implementa autenticação com **JWT** e permite a criação e gerenciamento de tópicos em um fórum.

Usuários podem se registrar, autenticar e criar tópicos relacionados a cursos. Cada tópico é associado automaticamente ao **usuário autenticado** que o criou.

---

## ⚙️ Tecnologias utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **Hibernate**
- **Flyway (migrações de banco de dados)**
- **MySQL**
- **Maven**

---

## 📖 Funcionalidades

- **Cadastro e autenticação de usuários**
    - Registro de novos usuários
    - Login com email e senha
    - Geração de token JWT para autenticação
    - Controle de permissões por **roles** (ex: `ADMIN`, `COSTUMER`)

- **Gerenciamento de tópicos**
    - Criar tópicos (apenas usuários autenticados)
    - Listar tópicos
    - Atualizar informações de um tópico
    - Relacionamento de tópico com **autor (usuário)** e **curso**
    - Filtrar tópicos por nome do curso via query param

- **Cursos**
    - Associação de tópicos com cursos
    - Busca de tópicos por curso

---

## 🔑 Autenticação

- Autenticação baseada em **JWT**.
- O token deve ser enviado no header de todas as requisições autenticadas:


---

## 📌 Endpoints principais

### Usuários
| Método | Endpoint     | Descrição                |
|--------|-------------|--------------------------|
| POST   | `/register` | Registrar novo usuário   |
| POST   | `/login`    | Autenticar e obter token |

### Tópicos
| Método | Endpoint                | Descrição                          |
|--------|-------------------------|------------------------------------|
| POST   | `/topicos/create`       | Criar novo tópico                  |
| GET    | `/topicos`              | Listar todos os tópicos            |
| GET    | `/topicos?curso=HTML+CSS` | Buscar tópicos pelo nome do curso |
| PUT    | `/topicos/{id}`         | Atualizar informações de um tópico |
| DELETE | `/topicos/{id}`         | Remover um tópico                  |

---

## 🛠️ Configuração do projeto

### Pré-requisitos
- **Java 17**
- **Maven**
- **MySQL**

### Configuração do banco
O projeto utiliza **Flyway** para versionamento do banco.  
Basta configurar o `application.yml` com os dados do MySQL:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/forumhub
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
  flyway:
    enabled: true

````
🚀 Executando o projeto

- OBS: É necessário colocar as variáveis de ambiente na hora de rodar o projeto

````
mvn spring-boot:run
````

📝 Exemplo de requisição
Criar tópico

Request
````text
POST /topicos/create
Authorization: Bearer <token>
Content-Type: application/json
````

````json
{
  "titulo": "Dúvidas com CSS",
  "mensagem": "Como usar o display flex?",
  "nomeCurso": "HTML e CSS",
  "categoria": "FRONTEND"
}
````
Response
````json
{
  "id": 11,
  "titulo": "Dúvidas com CSS",
  "mensagem": "Como usar o display flex?",
  "autor": "gui",
  "curso": "HTML e CSS",
  "createdAt": "2025-08-15T14:29:13",
  "status": "NAO_RESPONDIDO"
}
````
📌 Próximos passos
- Implementar sistema de respostas nos tópicos
- Criar testes unitários e de integração
- 
📝 License

MIT License © 2025 João Victor(jonhvtr)
