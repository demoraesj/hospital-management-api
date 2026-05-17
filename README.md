# 🏥 Hospital Management API

API REST desenvolvida com **Java 21** e **Spring Boot** para gerenciamento hospitalar.

O projeto foi construído com foco em:

* arquitetura em camadas
* boas práticas REST
* organização profissional de backend
* validações
* tratamento global de exceções
* relacionamento entre entidades
* IoC (Inversion of Control)
* Injeção de Dependência

---

# 🚀 Funcionalidades

## 👤 Pacientes

* Cadastro de pacientes
* Atualização de informações
* Busca por ID
* Listagem completa
* Remoção de pacientes

## 👨‍⚕️ Médicos

* Cadastro de médicos
* Especialidades
* Relacionamento com consultas

## 📅 Consultas

* Cadastro de consultas
* Relacionamento com:

    * paciente
    * médico
    * convênio
    * receita
* Controle de data e horário

## 📋 Prontuários

* Registro de informações médicas
* Tipo sanguíneo
* Alergias
* Observações

## 💊 Receitas

* Cadastro de medicamentos
* Dosagem
* Duração do tratamento

## 🏥 Convênios

* Cadastro de convênios
* Relacionamento com consultas

---

# 🧱 Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Validation
* Hibernate
* H2 Database
* Lombok
* Maven
* Jackson
* Swagger / OpenAPI

---

# 🏗️ Arquitetura do Projeto

O projeto segue arquitetura em camadas:

```text
src/main/java/com/sistema/hospitalapi
│
├── config
├── controller
├── dto
├── exception
├── model
├── repository
└── service
```

## 📁 Responsabilidades

| Camada     | Responsabilidade           |
| ---------- | -------------------------- |
| controller | Endpoints REST             |
| service    | Regras de negócio          |
| repository | Acesso ao banco de dados   |
| dto        | Transferência de dados     |
| model      | Entidades JPA              |
| exception  | Tratamento global de erros |
| config     | Configurações e Beans      |

---

# 🔄 Relacionamentos JPA

O sistema utiliza relacionamentos entre entidades com JPA/Hibernate:

* `Paciente -> Consulta`
* `Paciente -> Prontuario`
* `Consulta -> Medico`
* `Consulta -> Convenio`
* `Consulta -> Receita`

Também foram aplicadas estratégias para evitar:

* recursão infinita JSON
* serialização circular
* loops bidirecionais

Utilizando:

````java
@JsonIgnore
```

---

# ⚠️ Tratamento Global de Exceções

A API possui tratamento global utilizando:

```java
@RestControllerAdvice
```

Incluindo:

* validações automáticas
* erros de regra de negócio
* respostas padronizadas
* tratamento de exceções genéricas

## ✅ Exemplo de erro

```json
{
  "errors": [
    "Nome é obrigatório",
    "CPF é obrigatório"
  ]
}
```

---

# ✅ Validações

Foram aplicadas validações utilizando:

```java
@Valid
@NotBlank
```

Garantindo:

* integridade dos dados
* validação automática de requests
* respostas padronizadas de erro

---

# 🧠 IoC e Dependency Injection

O projeto também demonstra conceitos do Spring Framework como:

* IoC (Inversion of Control)
* Dependency Injection
* Beans
* Spring Context

Utilizando:

```java
@Bean
@Configuration
```

Além de injeção por construtor:

```java
private final Service service;
```

---

# 🌱 BeanController

Foi implementado um controlador de demonstração utilizando Beans gerenciados pelo Spring.

Endpoint:

```http
GET /bean
```

Objetivo:

* demonstrar IoC
* demonstrar Beans
* demonstrar injeção de dependência
* demonstrar composição de objetos complexos

---

# 📬 Testes da API

Collection do Postman disponível em:

```text
/postman/hospital.postman_collection.json
```

---

# ▶️ Como Executar o Projeto

## 1️⃣ Clonar repositório

```bash
git clone https://github.com/demoraesj/hospital-management-api.git
```

---

## 2️⃣ Entrar na pasta

```bash
cd hospital-management-api
```

---

## 3️⃣ Executar aplicação

```bash
mvn spring-boot:run
```

---

# 🔌 Endpoints Principais

| Método | Endpoint    |
| ------ | ----------- |
| GET    | /paciente   |
| POST   | /paciente   |
| GET    | /medico     |
| POST   | /medico     |
| GET    | /consulta   |
| POST   | /consulta   |
| GET    | /receita    |
| POST   | /receita    |
| GET    | /prontuario |
| POST   | /prontuario |
| GET    | /convenio   |
| POST   | /convenio   |
| GET    | /bean       |

---

# 📖 Documentação Swagger

A API possui documentação interativa utilizando Swagger/OpenAPI.

Após iniciar o projeto, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

Através do Swagger é possível:

* visualizar endpoints
* testar requisições
* visualizar parâmetros
* visualizar responses
* testar a API diretamente pelo navegador

---

# 📌 Melhorias Futuras

* Autenticação JWT
* PostgreSQL
* Docker
* Testes automatizados
* Deploy em nuvem

---

# 👨‍💻 Autor

Desenvolvido por João Pedro Moraes.

* GitHub: [https://github.com/demoraesj](https://github.com/demoraesj)
* LinkedIn: [https://www.linkedin.com](https://www.linkedin.com)
