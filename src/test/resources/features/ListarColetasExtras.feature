# language: pt
@regressivo
Funcionalidade: Listar coletas extras
  Como usuário da API
  Quero listar todas as coletas extras registradas
  Para que eu possa visualizar os dados no sistema

  Cenário: Sucesso ao listar coletas extras
    Quando eu enviar a requisicao para o endpoint "/api/coletaextra" de listagem de coleta extra
    Então o status code da resposta de coleta extra deve ser 200
    E a resposta deve conter uma lista de coletas extras

  Cenário: Validar contrato de resposta de listagem de coletas extras
    Quando eu enviar a requisicao para o endpoint "/api/coletaextra" de listagem de coleta extra
    E o arquivo de contrato esperado é o "Listagem de coletas extras"
    Então a resposta da requisicao de coleta extra deve estar em conformidade com o contrato selecionado
