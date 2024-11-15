# Esportivai API 🎉

Este é um projeto de exemplo construído com **Java 23** e **Spring Boot**, que implementa uma aplicação de CRUD para usuários, eventos e participações. Utilizando **JPA** com **Hibernate** e o banco de dados **H2**, este projeto segue os princípios de **Clean Architecture** e **SOLID**, com testes orientados a TDD.

## 📋 Visão Geral do Projeto

A aplicação permite que usuários se cadastrem, criem eventos e gerenciem participações em eventos. Este projeto é útil para entender como aplicar conceitos avançados de arquitetura e boas práticas, além de explorar as funcionalidades de CRUD em uma aplicação Spring Boot com H2.

### Funcionalidades Principais

- **Gestão de Usuários**: Cadastro, consulta, atualização e remoção de usuários.
- **Gestão de Eventos**: Criação, listagem, atualização e cancelamento de eventos.
- **Gestão de Participações**: Inscrição de usuários em eventos e controle de status.

## 🛠️ Tecnologias Utilizadas

- **Java 23**
- **Spring Boot** (com Spring Data JPA)
- **H2 Database** (banco de dados em memória para testes e desenvolvimento rápido)
- **JUnit e Mockito** (para testes unitários e de integração)
- **Swagger/OpenAPI** (para documentação da API)
- **Lombok** (para reduzir o boilerplate de código)

## 🏛️ Arquitetura

O projeto segue uma estrutura de **Clean Architecture**:
- **Camada de Application**: Contém os casos de uso da aplicação (UseCases), onde estão as regras de negócio e serviços.
- **Camada de Domain**: Define as entidades do negócio e interfaces dos repositórios.
- **Camada de Infrastructure**: Contém as implementações de repositórios, configurações e adaptadores externos.

Esta arquitetura permite desacoplar regras de negócio das implementações, facilitando a manutenção, testabilidade e evolução da aplicação.

## 🚀 Executando o Projeto

### Pré-requisitos

- **JDK 23**
- **Maven** ou **Gradle**

### Passo a Passo para Execução

1. **Clone o repositório**:
    ```bash
    git clone https://github.com/seu-usuario/nome-do-repositorio.git
    cd nome-do-repositorio
    ```

2. **Compile o projeto**:
    ```bash
    ./mvnw clean install
    ```

3. **Execute o projeto**:
    ```bash
    ./mvnw spring-boot:run
    ```
   A aplicação estará disponível em `http://localhost:8080`.

4. **Acesse o Swagger**:
   Acesse `http://localhost:8080/swagger-ui.html` para visualizar a documentação e testar os endpoints da API.

## 🗄️ Estrutura das Entidades e Endpoints

### Usuários (User)
- **POST /api/auth/register**: Registro de usuário.
- **POST /api/auth/login**: Autenticação do usuário.
- **GET /api/users/{userId}**: Consulta de perfil do usuário.
- **PUT /api/users/{userId}/update-profile**: Atualização de perfil.

### Eventos (Event)
- **POST /api/events**: Criação de um evento.
- **GET /api/events**: Listagem de eventos com filtros.
- **GET /api/events/{eventId}**: Consulta detalhes de um evento.
- **PUT /api/events/{eventId}**: Atualização de um evento.
- **DELETE /api/events/{eventId}**: Cancelamento de um evento.

### Participações (Participation)
- **POST /api/events/{eventId}/join**: Inscrição em um evento.
- **DELETE /api/events/{eventId}/leave**: Cancelamento de participação.
- **GET /api/events/{eventId}/participants**: Lista de participantes.

## 🧪 Testes

Para rodar os testes, utilize o comando:
```bash
./mvnw test
```

### Testes Disponíveis
- **Testes de Serviço:** Testes unitários para UserService, EventService e ParticipationService, cobrindo as operações CRUD.
- **Testes de Integração:** Verificação do comportamento das camadas em conjunto (repositórios e serviços).

## 🎯 Princípios Aplicados
- **Clean Architecture:** Organização do projeto em camadas que respeitam o desacoplamento das regras de negócio.
- **SOLID:** Uso dos princípios para manter o código coeso e flexível.
- **TDD:** Testes unitários escritos para garantir a funcionalidade antes da implementação.

## 📝 Contribuindo
Se você gostaria de contribuir com o projeto:


Faça um fork do repositório.

Crie uma branch para sua feature (git checkout -b feature/nova-feature).

Commit suas mudanças (git commit -am 'Adiciona nova feature').

Faça push para a branch (git push origin feature/nova-feature).
Abra um Pull Request.

## 📄 Licença
Este projeto está sob a licença MIT. Sinta-se livre para usá-lo e modificá-lo conforme necessário.