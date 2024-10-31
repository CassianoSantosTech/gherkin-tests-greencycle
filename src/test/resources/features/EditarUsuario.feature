# language: pt
@regressivo
Funcionalidade: Editar usuário
  Como usuário da API
  Quero editar um usuário existente
  Para que os dados do usuário sejam atualizados corretamente no sistema

  Contexto: Cadastro bem-sucedido de usuario
    Dado que eu tenha os seguintes dados de usuario:
      | campo      | valor                      |
      | idUsuario  | 13                         |
      | nome       | Paulo Eduardo Oliveira     |
      | email      | pauloEdu@gmail.com         |
      | senha      | pauloedu@1234              |
      | role       | USER                       |
    Quando eu enviar a requisicao para o endpoint "/auth/users" de cadastro de usuario
    Então o status code da resposta deve ser 200

  Cenário: Edição bem-sucedida de usuário
    Dado que eu tenha os seguintes dados de usuario para edição:
      | campo      | valor                      |
      | idUsuario  | 13                         |
      | nome       | Paulo Eduardo Oliveira Passos |
      | email      | pauloEdu@gmail.com         |
      | senha      | novaSenha@1234             |
      | role       | USER                       |
    Quando eu enviar a requisicao para o endpoint "/api/user/66cbbc678af48e72548be147" de edição de usuario
    Então o status code da resposta deve ser 200

  Cenário: Falha ao editar usuário com dados inválidos
    Dado que eu tenha os seguintes dados de usuario para edição:
      | campo      | valor  |
      | nome       |        | # Nome vazio para erro 400
      | email      | pauloEdu@gmail.com |
      | senha      | novaSenha@1234     |
      | role       | OperadorNovo               |
    Quando eu enviar a requisicao para o endpoint "/api/user/66cbbc678af48e72548be147" de edição de usuario
    Então o status code da resposta deve ser 403

  Cenário: Validar contrato para edição bem-sucedida de usuário
    Dado que eu tenha os seguintes dados de usuario para edição:
      | campo      | valor                      |
      | idUsuario  | 13                         |
      | nome       | Paulo Eduardo Oliveira Passos |
      | email      | pauloEdu@gmail.com         |
      | senha      | novaSenha@1234             |
      | role       | USER                       |
    Quando eu enviar a requisicao para o endpoint "/api/user/66cbbc678af48e72548be147" de edição de usuario
    Então o status code da resposta deve ser 200
    E que o que o arquivo de contrato esperado é o "Edição bem-sucedida de usuario"
    Então a resposta da requisicao deve estar em conformidade com o contrato selecionado
