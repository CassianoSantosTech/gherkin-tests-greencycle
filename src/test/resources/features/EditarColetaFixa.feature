# language: pt
@regressivo
Funcionalidade: Atualizar coleta fixa
  Como usuário da API
  Quero atualizar uma coleta fixa existente
  Para que os dados da coleta sejam modificados corretamente no sistema

  Cenário: Sucesso ao atualizar coleta fixa
    Dado que eu tenha os seguintes dados de coleta fixa para atualização:
      | campo      | valor                    |
      | dataColeta | 2024-11-25               |
      | usuarioId  | 13                       |
      | qrCodeId   | 1283734427423418         |
      | status     | COLETADO                 |
      | endereco   | Rua F, casa 100, José Walter |
      | telefone   | 85999999999              |
      | cpf        | 054.876.234-56           |
      | cnpj       | 12.345.678/0001-00       |
    Quando eu enviar a requisicao para o endpoint "/api/coletafixa/66cc53802610b30a3df2a6bc" de atualização de coleta fixa
    Então o status code da resposta de atualização de coleta fixa deve ser 200
    E que o arquivo de contrato de coleta extra esperado é o "Edição bem-sucedida de coleta fixa"
    Então a resposta da requisicao de coleta fixa deve estar em conformidade com o contrato selecionado

  Cenário: Falha ao atualizar coleta fixa com dados inválidos
    Dado que eu tenha os seguintes dados de coleta fixa para atualização:
      | campo      | valor        |
      | dataColeta |              | # Data vazia para erro 400
      | usuarioId  | 13           |
      | qrCodeId   | 1283734427423418 |
      | status     |              |
      | endereco   | Rua F, casa 100, José Walter |
      | telefone   | 85999999999  |
      | cpf        | 054.876.234-56 |
      | cnpj       | 12.345.678/0001-00 |
    Quando eu enviar a requisicao para o endpoint "/api/coletafixa/" de atualização de coleta fixa
    Então o status code da resposta de atualização de coleta fixa deve ser 403
