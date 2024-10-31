# language: pt
@regressivo
Funcionalidade: Cadastro de novo usuário
  Como usuário da API
  Quero cadastrar uma nova entrega
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido de usuario
    Dado que eu tenha os seguintes dados de usuario:
      | campo          | valor        |
      | idUsuario   | 13            |
      | nome | Paulo Eduardo    |
      | email  | pauloEdu@gmail.com |
      | senha    | pauloedu@1234   |
      | role    | USER   |
    Quando eu enviar a requisicao para o endpoint "/auth/users" de cadastro de usuario
    Então o status code da resposta deve ser 200

  Cenário: Cadastro sem sucesso de usuario
    Dado que eu tenha os seguintes dados de usuario:
      | campo          | valor        |
      | idUsuario   | 13            |
      | nome | Paulo Eduardo    |
      | email  | pauloEdu@gmail.com |
      | senha    | pauloedu@1234   |
      | role    | operadorNovo   |
    Quando eu enviar a requisicao para o endpoint "/auth/users" de cadastro de usuario
    Então o status code da resposta deve ser 403

  Cenário: Validar contrato cadastro de usuario bem sucedido
    Dado que eu tenha os seguintes dados de usuario:
      | campo          | valor        |
      | idUsuario   | 13            |
      | nome | Paulo Eduardo    |
      | email  | pauloEdu@gmail.com |
      | senha    | pauloedu@1234   |
      | role    | USER   |
    Quando eu enviar a requisicao para o endpoint "/auth/users" de cadastro de usuario
    Então o status code da resposta deve ser 200
    E que o que o arquivo de contrato esperado é o "Cadastro bem-sucedido de usuario"
    Então a resposta da requisicao deve estar em conformidade com o contrato selecionado