# language: pt
@regressivo
Funcionalidade: Cadastro de Coleta Fixa
  Como usuário da API
  Quero registrar uma coleta fixa
  Para que a coleta seja cadastrada no sistema

  Cenário: Sucesso ao cadastrar coleta fixa
    Dado que eu tenha os seguintes dados de coleta fixa:
      | campo       | valor                         |
      | dataColeta  | 2024-11-25                    |
      | usuarioId   | 13                            |
      | qrCodeId    | 1283734427423418              |
      | status      | PENDENTE                      |
      | endereco    | Rua F, casa 100, José Walter  |
      | telefone    | 85999999999                   |
      | cpf         | 054.876.234-56                |
      | cnpj        | 12.345.678/0001-00            |
    Quando eu enviar a requisicao para o endpoint "/api/coletafixa" de cadastro de coleta fixa
    Então o status code da resposta de coleta fixa deve ser 201

  Cenário: Validar contrato de resposta para cadastro de coleta fixa
    Dado que eu tenha os seguintes dados de coleta fixa:
      | campo       | valor                         |
      | dataColeta  | 2024-11-25                    |
      | usuarioId   | 13                            |
      | qrCodeId    | 1283734427423418              |
      | status      | PENDENTE                      |
      | endereco    | Rua F, casa 100, José Walter  |
      | telefone    | 85999999999                   |
      | cpf         | 054.876.234-56                |
      | cnpj        | 12.345.678/0001-00            |
    Quando eu enviar a requisicao para o endpoint "/api/coletafixa" de cadastro de coleta fixa
    Então o status code da resposta de coleta fixa deve ser 201
    E que o arquivo de contrato de coleta extra esperado é o "Cadastro bem-sucedido de coleta fixa"
    Então a resposta da requisicao de coleta fixa deve estar em conformidade com o contrato selecionado
