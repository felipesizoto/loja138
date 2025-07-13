Feature: Login no site MotionSeed

  Scenario Outline: Testar login com diferentes combinações de email e senha
  Given que o usuário está na página de login
  When ele insere o email "<email>" e a senha "<senha>"
  And clica no botão de login
  Then o resultado deve ser "<resultado>"

  Examples:
    | email                      | senha       | resultado |
    | test@test.com              | test1234    | sucesso   |
    | usuario_invalido@teste.com | test1234    | falha     |
    | test@test.com              | senhaerrada | falha     |
