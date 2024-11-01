# language: pt
@regressivo
Funcionalidade: Atualizar coleta extra
  Como usuário da API
  Quero atualizar uma coleta extra existente
  Para que os dados da coleta sejam modificados corretamente no sistema

  Cenário: Sucesso ao atualizar coleta extra
    Dado que eu tenha os seguintes dados de coleta extra para atualização:
      | campo      | valor                    |
      | dataColeta | 2024-11-25               |
      | idUsuario  | 13                       |
      | idQrCode   | 1283734427423418         |
      | status     | COLETADO                 |
      | endereco   | Rua F, casa 100, José Walter |
      | telefone   | 85999999999              |
      | cpf        | 054.876.234-56           |
      | cnpj       | 12.345.678/0001-00       |
    Quando eu enviar a requisicao para o endpoint "/api/coletaextra/6724073701fd8d138401e73e" de atualização de coleta extra
    Então o status code da resposta de atualização de coleta extra deve ser 200
    E que o arquivo de contrato esperado é o "Edição bem-sucedida de coleta extra"
    Então a resposta da requisicao de coleta extra deve estar em conformidade com o contrato selecionado

  Cenário: Falha ao atualizar coleta extra com dados inválidos
    Dado que eu tenha os seguintes dados de coleta extra para atualização:
      | campo      | valor        |
      | dataColeta |              | # Data vazia para erro 400
      | idUsuario  | 13           |
      | idQrCode   | 1283734427423418 |
      | status     |              |
      | endereco   | Rua F, casa 100, José Walter |
      | telefone   | 85999999999  |
      | cpf        | 054.876.234-56 |
      | cnpj       | 12.345.678/0001-00 |
    Quando eu enviar a requisicao para o endpoint "/api/coletaextra/" de atualização de coleta extra
    Então o status code da resposta de atualização de coleta extra deve ser 403
