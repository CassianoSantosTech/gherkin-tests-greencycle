# language: pt
@regressivo
Funcionalidade: Cadastro de Coleta Extra
  Como usuário da API
  Quero registrar uma coleta extra
  Para que a coleta seja cadastrada no sistema

  Cenário: Sucesso ao cadastrar coleta extra
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

  Cenário: Validar contrato de resposta para cadastro de coleta extra
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
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido de coleta extra"
    Então a resposta da requisicao de coleta extra deve estar em conformidade com o contrato selecionado
