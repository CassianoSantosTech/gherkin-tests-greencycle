# language: pt
@regressivo
Funcionalidade: Listar usuários
  Como usuário da API
  Quero listar todos os usuários registrados
  Para que eu possa visualizar os dados deles no sistema

  Cenário: Sucesso ao listar usuários
    Quando eu enviar a requisição para o endpoint "/api/user" de listagem de usuários
    Então o status code da resposta deve ser 200
    E a resposta deve conter uma lista de usuários

  Cenário: Validar contrato de resposta de listagem de usuários
    Quando eu enviar a requisição para o endpoint "/api/user" de listagem de usuários
    E que o que o arquivo de contrato esperado é o "Listagem de usuários"
    Então a resposta da requisicao deve estar em conformidade com o contrato selecionado
