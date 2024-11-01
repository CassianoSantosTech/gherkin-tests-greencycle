# language: pt
@regressivo
Funcionalidade: Cadastro de login
  Como usuário da API
  Quero cadastrar um login
  Para que as credenciais do usuário estejam registradas no sistema

  Cenário: Sucesso ao cadastrar login
    Dado que eu tenha os seguintes dados de login:
      | campo      | valor               |
      | role       | ADMIN               |
      | idUsuario  | 150                 |
      | emailLogin | user150@example.com |
      | senhaLogin | password150         |
    Quando eu enviar a requisicao para o endpoint "/api/login" de cadastro de login
    Então o status code da resposta de login deve ser 201

  Cenário: Falha ao cadastrar login com dados inválidos
    Dado que eu tenha os seguintes dados de login:
      | campo      | valor        |
      | role       | USER     |
      | idUsuario  | 150          |
      | emailLogin |              | # Email vazio para erro 403
      | senhaLogin | password150  |
    Quando eu enviar a requisicao para o endpoint "/api/login" de cadastro de login
    Então o status code da resposta de login deve ser 403
