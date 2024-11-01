# language: pt
@regressivo
Funcionalidade: Deletar coleta extra
Contexto: Sucesso ao cadastrar coleta extra
  Dado que eu tenha os seguintes dados de coleta extra:
    | campo       | valor                         |
    | dataColeta  | 2024-11-25                    |
    | idUsuario   | 13                            |
    | idQrCode    | 1283734427423418              |
    | status      | PENDENTE                      |
    | endereco    | Rua F, casa 100, José Walter  |
    | telefone    | 85999999999                   |
    | cpf         | 054.876.234-56                |
    | cnpj        | 12.345.678/0001-00            |
  Quando eu enviar a requisicao para o endpoint "/api/coletaextra" de cadastro de coleta extra
  Então o status code da resposta de coleta extra deve ser 201

Cenário: Deve ser possivel deletar uma coleta extra
  Dado que eu recupere o idColeta criado no contexto:
    | campo       | valor                         |
    | dataColeta  | 2024-11-25                    |
    | idUsuario   | 13                            |
    | idQrCode    | 1283734427423418              |
    | status      | PENDENTE                      |
    | endereco    | Rua F, casa 100, José Walter  |
    | telefone    | 85999999999                   |
    | cpf         | 054.876.234-56                |
    | cnpj        | 12.345.678/0001-00            |
    | idColetaE  | 6724073701fd8d138401e73e       |
  Quando eu enviar a requisicao com o ID para o endpoint "/api/coletaextra" de delecao de coleta extra
  Então o status code da resposta de delecao deve ser 204
