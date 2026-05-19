# 💎 Projeto Diamante 03 - API de Gestão de Jogos Online

![Java](https://img.shields.io/badge/Java-17+-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-brightgreen)
![Oracle DB](https://img.shields.io/badge/Oracle-Database-red)

## 📌 Sobre o Projeto
[cite_start]Esta API RESTful foi desenvolvida como requisito de avaliação do **Projeto Diamante 03** da disciplina de **Java Advanced** (Primeiro Semestre)[cite: 1, 2, 3]. 

[cite_start]O objetivo do projeto é fornecer um sistema de back-end robusto para gerenciar uma plataforma de jogos online, permitindo o cadastro de jogadores, manutenção de um catálogo de jogos e o registro de partidas. A aplicação aplica as melhores práticas do ecossistema Spring, garantindo performance, segurança e auto-documentação.

## 🗂️ Entidades e Funcionamento Básico
[cite_start]O sistema é composto pelo CRUD completo e relacionado de três entidades principais[cite: 10, 25]:

1. **Jogador**: Representa o usuário da plataforma. Armazena dados como `nickname`, `email` e `nível`.
2. **Jogo**: Representa os títulos do catálogo. Armazena o `título`, `gênero` (validado via Enum) e a `desenvolvedora`.
3. **Partida**: Entidade de relacionamento (N:M) que vincula um Jogador a um Jogo, registrando a `data/hora` e a `pontuação` obtida na sessão.

## 🚀 Requisitos e Tecnologias Implementadas
[cite_start]O projeto atende a 100% dos requisitos solicitados[cite: 8]:

- [cite_start]**API REST com Spring Boot**: Definição e implementação correta dos endpoints seguindo as boas práticas do protocolo HTTP e uso de DTOs (Data Transfer Objects)[cite: 9, 10].
- [cite_start]**Persistência de Dados**: Relacionamento de tabelas e persistência utilizando **Spring Data JPA** conectado ao banco de dados relacional **Oracle**[cite: 10].
- [cite_start]**Projections e Paginação**: Otimização de consultas `GET` utilizando Paginação, Ordenação e Projections (retornando apenas dados resumidos ao invés de objetos inteiros)[cite: 10].
- [cite_start]**Swagger / OpenAPI**: Documentação interativa e automática da API, testável via navegador[cite: 14, 17].
- [cite_start]**Spring Cache**: Aumento de performance com cache nas operações de busca (`@Cacheable`) e invalidação automática ao criar/atualizar dados (`@CacheEvict`)[cite: 15, 18].
- [cite_start]**Spring Actuator**: Exposição dos endpoints de monitoramento de saúde (`health`), informações (`info`) e métricas (`metrics`) da aplicação[cite: 16, 19].
- [cite_start]**HATEOAS**: Implementação de links de navegação (`_links`) nas respostas JSON, permitindo a descoberta de recursos de forma dinâmica[cite: 21, 22].
- **Tratamento de Exceções e Validação**: Uso de `@RestControllerAdvice` para padronização de erros e `Bean Validation` (`@Valid`, `@NotBlank`) para garantir a integridade dos dados inseridos.

## ⚙️ Como Executar o Projeto

### Pré-requisitos
* Java 17 ou superior
* Maven
* Banco de Dados Oracle (Local XE ou Cloud)

### Passos
1. Clone o repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git](https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git)

2. Abra o arquivo src/main/resources/application.properties e insira as credenciais do seu banco de dados Oracle:

Properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

3. Execute o projeto pela sua IDE (Eclipse, IntelliJ, VSCode) ou via terminal:

Bash
mvn spring-boot:run
📖 Documentação e Testes (Swagger)
Com a aplicação em execução, acesse o navegador para visualizar a documentação interativa e testar todos os endpoints:
👉 http://localhost:8080/swagger-ui.html

🛠️ Endpoints de Monitoramento (Actuator)
Integridade do sistema: http://localhost:8080/actuator/health

Informações gerais: http://localhost:8080/actuator/info

Métricas detalhadas: http://localhost:8080/actuator/metrics
