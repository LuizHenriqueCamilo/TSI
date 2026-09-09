## Diagrama de casos

Sistema de Controle Bancário

# Visão geral

O sistema permite que um cliente utilize um caixa eletrônico para realizar depósitos, saques, consultas de saldo e emissão de extratos. A abertura e o encerramento de contas exigem a participação de um funcionário do banco. O funcionário também mantém o cadastro do cliente, podendo registrar um novo cliente ou alterar seus dados.

O modelo contempla três modalidades de conta: conta comum, conta especial e conta poupança.

1. Diagrama de casos de uso

O diagrama de casos de uso apresenta as funcionalidades do sistema na perspectiva dos atores externos.

O cliente pode solicitar a abertura ou o encerramento de uma conta e também realizar as operações bancárias de depósito, saque, consulta de saldo e emissão de extrato. Apesar de o cliente iniciar a solicitação de abertura ou encerramento, essas duas operações dependem do funcionário, pois exigem atendimento interno.

O funcionário do banco registra clientes, altera cadastros, abre contas e encerra contas. O caixa eletrônico é o canal pelo qual são executadas as operações de depósito, saque, consulta de saldo e emissão de extrato.

A validação de saldo e limite é necessária para o saque. Para uma conta comum, o saque não pode ultrapassar o saldo disponível. Para uma conta especial, o sistema permite utilizar o saldo acrescido do limite contratado. A aplicação de juros da conta poupança representa uma regra específica dessa modalidade.

2. Diagrama de classes

A classe abstrata Conta concentra os dados e comportamentos comuns a todas as contas: número, data de abertura, saldo, situação, depósito, saque, consulta, extrato e encerramento.

As classes ContaComum, ContaEspecial e ContaPoupanca especializam a classe Conta. A conta comum não possui crédito adicional. A conta especial possui o atributo limite, que determina o valor máximo de utilização além do saldo. A conta poupança possui taxaJuros e a data da última movimentação, permitindo calcular o rendimento quando o dinheiro permanece sem movimentação pelo período definido pela regra de negócio.

Um Cliente pode possuir zero ou várias contas, enquanto cada conta pertence a um cliente no modelo apresentado. Cada conta registra várias Movimentações, que armazenam tipo, valor, data e saldo após a operação. A classe Extrato representa a consulta organizada das movimentações em um período.

O Funcionário mantém o cadastro do cliente e administra abertura e encerramento de contas. As associações e as generalizações mostram, respectivamente, os relacionamentos entre as entidades e a herança das modalidades de conta.

3. Diagrama de atividades

# O fluxo começa com a autenticação do cliente no caixa eletrônico. Em seguida, o cliente seleciona uma operação.

No depósito, o cliente informa o valor, insere o numerário, e o sistema registra a operação e atualiza o saldo. No saque, o cliente informa o valor e o sistema verifica se o saldo disponível, somado ao limite quando aplicável, é suficiente. Caso não seja, o saque é recusado; caso seja, o caixa entrega o numerário, registra a movimentação e atualiza o saldo.

Na consulta de saldo, o sistema exibe o valor disponível. Na emissão de extrato, o cliente informa o período, o sistema busca as movimentações e emite o documento. Ao final de qualquer operação, o cliente pode realizar outra operação ou encerrar o atendimento, quando o cartão é devolvido.

4. Diagrama de sequência — abertura de conta

# O diagrama de sequência descreve a abertura de uma conta, que é uma operação assistida por funcionário.

Primeiro, o cliente solicita a abertura. O funcionário consulta o cadastro pelo CPF. Se o cliente não estiver cadastrado, seus dados são registrados. Se já estiver cadastrado, o funcionário pode atualizar as informações quando necessário.

Depois, o funcionário solicita ao banco a criação da conta e informa a modalidade desejada. O banco cria a conta correspondente. Para uma conta especial, define o limite de crédito; para uma conta poupança, define a taxa de juros. Ao final, um número de conta é gerado, a abertura é confirmada e os dados são entregues ao cliente.

Regras de negócio consideradas

1. A abertura e o encerramento de contas são realizados com participação de funcionário.

2. Depósito, saque, consulta de saldo e emissão de extrato podem ser realizados no caixa eletrônico.

3. Uma conta comum não permite saldo negativo.

4. Uma conta especial permite saldo negativo até o limite contratado.

5. Uma conta poupança rende juros conforme a taxa e a regra de permanência sem movimentação.

6. Toda operação financeira deve gerar uma movimentação para posterior consulta no extrato.

7. O encerramento deve ser condicionado às regras do banco, como inexistência de saldo pendente ou débitos, embora essa validação possa ser detalhada em uma versão futura do modelo.

##