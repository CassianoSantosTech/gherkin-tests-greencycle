# language: pt
@regressivo
Funcionalidade: Deletar usuario
  Contexto: Cadastro bem-sucedido de usuario
    Dado que eu tenha os seguintes dados de usuario:
      | campo      | valor              |
      | idUsuario  | 13                 |
      | nome       | Paulo Eduardo      |
      | email      | pauloEdu@gmail.com |
      | senha      | pauloedu@1234      |
      | role       | USER               |
    Quando eu enviar a requisicao para o endpoint "/auth/users" de cadastro de usuario
    Então o status code da resposta deve ser 200

  Cenário: Deve ser possivel deletar uma entrega
    Dado que eu recupere o id criado no contexto:
      | campo      | valor              |
      | idUsuario  | 13                 |
      | nome       | Paulo Eduardo      |
      | email      | pauloEdu@gmail.com |
      | senha      | pauloedu@1234      |
      | role       | USER               |
    Quando eu enviar a requisicao com o ID para o endpoint "/api/user" de delecao de usuario
    Então o status code da resposta deve ser 204
