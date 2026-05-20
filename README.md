# 💎 Projeto Diamante 03 - API de Gestão de Jogos Online

![Java](https://img.shields.io/badge/Java-21+-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-brightgreen)
![H2 Database](https://img.shields.io/badge/H2-Database-lightgrey)

## 📌 Sobre o Projeto
Esta API RESTful foi desenvolvida como requisito de avaliação do **Projeto Diamante 03** da disciplina de **Java Advanced** (Primeiro Semestre). 

O objetivo do projeto é fornecer um sistema de back-end robusto para gerenciar uma plataforma de jogos online, permitindo o cadastro de perfis de jogadores, manutenção de um catálogo de jogos e o registro de partidas. A aplicação aplica as melhores práticas do ecossistema Spring, garantindo performance, segurança e auto-documentação.

## 🗂️ Entidades e Funcionamento Básico
O sistema é composto pelo CRUD completo e relacionado de três entidades principais:

1. **Jogador**: Representa o usuário da plataforma. Armazena dados como `nickname`, `email` e `nível`.
2. **Jogo**: Representa os títulos do catálogo. Armazena o `título`, `gênero` (validado via Enum) e a `desenvolvedora`.
3. **Partida**: Entidade de relacionamento (N:M) que vincula um Jogador a um Jogo, registrando a `data/hora` e a `pontuação` obtida na sessão.

## 🚀 Requisitos e Tecnologias Implementadas
O projeto atende a 100% dos requisitos solicitados:

- [cite_start]**API REST com Spring Boot**: Definição e implementação correta dos endpoints seguindo as boas práticas do protocolo HTTP e uso de DTOs (Data Transfer Objects)[cite: 9, 10].
- **Persistência de Dados**: Relacionamento de tabelas e persistência utilizando **Spring Data JPA** conectado ao banco de dados relacional **H2 em memória**. [cite_start]O banco é populado automaticamente na inicialização via `data.sql`[cite: 10].
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

### Passos para Execução
1. Clone o repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git](https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git)
