# Backend - documentation

# Back-end of the Yolo case challenge

An application capable of: loading initial data from another API as a micro service; creating new users in the database, reading existing users in the database, updating existing user’s information in the database and deleting an existing user in the database and filtering existing users by their user type.

---

## Index

1. Prerequisites
2. Installation
3. Available scripts
4. Structure of the project
5. Contribuitions
6. Technologies used
7. Contact

---

## 1 - Prerequisites

List of the tools needed to run this project:

- Java 17
- Maven
- Compatible IDE - IntelliJ IDEA is recommended

---

## 2 - Installation

Step-by-step guide to clone this repository and configure its local environment:

```tsx
# Clone the repository
git clone https://github.com/felipealvesdev/YoloBackend.git

# Navigate into the directory of this project
cd YoloBackend

# Install your dependencies from pom.xml with maven
mvn install

# Run BackendApplication
```

Fill up your application.properties file:

```tsx
# Go to application.properties, copy, and use your data:
spring.application.name=backend
spring.datasource.url=jdbc:mysql://localhost:3306/yolobackend
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

## 3 - Available scripts

Commands which may be used in the terminal of the project:

- `mvn spring-boot:run`  - Starts the project in develop environment.
- `mvn test`  - Runs unit testing.

---

## 4 - Structure of the project

Quick description about the main files and folders:

```java
src/
	├── main/
		├── java/
			├── com.yolo.backend/
				├── config/              # App config
					├── AppConfig
					├── CorsConfiguration
					├── SwaggerConfig
				├── controllers/          # endpoints
					├── UserController
				├── domain/
					├── converters/
						├── UserTypeConverter
					├── enums/
						├── UserType
					├── ApiResponse
					├── ApiResponseData
					├── User
				├── dtos/
					├── UserDTO
					├── UserDTOInitialDb
				├── repositories/
					├── UserRepository
				├── runner/                # Initializes data
					├── DataLoader
				├── services/
					├── ApiIntegrationService  # Connects to initial data micro-service
					├── UserService
				├── BackendApplication  # Starts app
		├── resources/
			├── static/
			├── templates/
			├── application.properties  # Connect your db here
	├── test/                         # Unit testing
		├── java/
			├── com.yolo.backend/
				├── controllers/
					├── UserControllerTest
				├── services/
					├── UserServiceTest
				├── BackendApplicationTests # Starts unit testing

```

---

## 5 - Technologies used

List of the main technologies and libraries in the project:

- Java 17
- Spring boot
- Maven
- Lombok
- Spring validation
- Swagger
- J Unit
- Mockito

---

## 6 - Contact

Information for getting in touch with me or any support:

- Author: Felipe Alves
- Email: felipealexandre1408@gmail.com

### This is the end of the documentation in English.

---

# Back-end do case de desafio da Yolo

Uma aplicação capaz de: carregar dados iniciais de outra API como um microsserviço; criar novos usuários no banco de dados, ler os usuários já existentes no banco de dados, atualizar informações existentes de usuários no banco de dados e deletar um usuário existente do banco de dados e filtrar usuários existentes através de seus tipos de usuários.

---

## Índice

1. Pré-requisitos
2. Instalação
3. Scripts disponíveis
4. Estrutura do projeto
5. Contribuição
6. Tecnologias utilizadas
7. Contato

---

## 1 - Pré-requisitos

Lista de ferramentas necessárias para rodar o projeto:

- Java 17
- Maven
- IDE compatível - IntelliJ IDEA é recomendado

---

## 2 - Instalação

Passos para clonar o repositório e configurar o ambiente local:

```tsx
# Clone o repositório
git clone https://github.com/felipealvesdev/YoloBackend.git

# Navegue para o diretório deste projeto
cd YoloBackend

# Instale suas dependências do pom.xml com maven
mvn install

# Inicie BackendApplication

```

Preencha seu arquivo  application.properties:

```tsx
# Vá em application.properties, copie, and use seus dados:
spring.application.name=backend
spring.datasource.url=jdbc:mysql://localhost:3306/yolobackend
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

## 3 - Scripts disponíveis

Comandos que podem ser executados no terminal do projeto:

- `mvn spring-boot:run` - Inicia o projeto em ambiente de desenvolvimento
- `mvn test` - Inicia testes unitários

---

## 4 - Estrutura do projeto

Descrição básica das principais pastas e arquivos:

```java
src/
	├── main/
		├── java/
			├── com.yolo.backend/
				├── config/              # App config
					├── AppConfig
					├── CorsConfiguration
					├── SwaggerConfig
				├── controllers/          # endpoints
					├── UserController
				├── domain/
					├── converters/
						├── UserTypeConverter
					├── enums/
						├── UserType
					├── ApiResponse
					├── ApiResponseData
					├── User
				├── dtos/
					├── UserDTO
					├── UserDTOInitialDb
				├── repositories/
					├── UserRepository
				├── runner/                # Inicia os dados
					├── DataLoader
				├── services/
					├── ApiIntegrationService  # Conecta ao microsserviço inicial de dados
					├── UserService
				├── BackendApplication  # Inicia o app
		├── resources/
			├── static/
			├── templates/
			├── application.properties  # Conecte seu banco de dados aqui
	├── test/                         # Testes unitários
		├── java/
			├── com.yolo.backend/
				├── controllers/
					├── UserControllerTest
				├── services/
					├── UserServiceTest
				├── BackendApplicationTests # Inicia testes unitários

```

---

## 5 - Tecnologias utilizadas

Lista de principais tecnologias e bibliotecas no projeto:

- Java 17
- Spring boot
- Maven
- Lombok
- Spring validation
- Swagger
- J Unit
- Mockito

---

## Contato

Informações para entrar em contato ou buscar suporte:

- Autor: Felipe Alves
- E-mail: felipealexandre1408@gmail.com

### Fim da documentação em pt-BR