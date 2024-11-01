# Projeto de Testes Automatizados para API

Este projeto foi desenvolvido para automatizar os testes de uma API RESTful, com o objetivo de garantir a integridade e a conformidade dos endpoints da aplicação. A implementação utiliza **JUnit**, **Cucumber**, **RestAssured** e **JSON Schema Validation** para testes de integração e validação de contratos.

## Requisitos
- Java 11+
- Maven 3.6+
- Docker (opcional, caso a API esteja em um container)
- Internet para baixar dependências do Maven

## Instalação
1. Clone o repositório:
   ```bash
   git clone https://github.com/CassianoSantosTech/gherkin-tests-greencycle.git
   cd testes-automatizados
   
2. Instale as dependências usando Maven:
   mvn clean install

3. Execução dos Testes:
   mvn test

4. Se desejar gerar um relatório de testes, execute:
   mvn clean verify

5. Relatório é gerado pa pasta target em cucumber-reports.html
   
## Estrutura do Projeto

- src/test/java/steps: Contém as definições dos passos dos testes, onde cada passo corresponde a uma ação ou validação específica do teste.

- src/test/java/services: Contém as classes de serviço responsáveis pela interação com a API, configuração de parâmetros e chamadas REST.

- src/test/java/model: Modelos de dados usados para estruturar os objetos JSON enviados e recebidos da API.

- src/test/resources/features: Arquivos .feature contendo os cenários de teste escritos em Gherkin, que descrevem as funcionalidades e os casos de teste em linguagem natural.

- src/test/resources/schemas: Arquivos JSON que contêm os esquemas para validação dos contratos de resposta.
  
## Funcionalidades de Teste

- Os principais endpoints testados e suas funcionalidades incluem:

### Usuários:

Cadastro de usuários.
Edição de dados dos usuários.
Listagem e deleção de usuários.
Validação de contratos para respostas dos endpoints.
### Coleta Extra:

Cadastro e edição de registros de coleta extra.
Validação de contratos e status code para as operações.
### Login:

Cadastro de login e validação de autenticação.
Validação de campos obrigatórios e contratos de resposta.