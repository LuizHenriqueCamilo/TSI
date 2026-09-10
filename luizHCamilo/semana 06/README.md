##  Estudo de Caso Integrado: App "IFBank" (Módulo Pix)
Objetivo de Aprendizagem: Integrar os conhecimentos de Elicitação de Requisitos (Casos de Uso), Comportamento (Sequência) e Estrutura (Classes) na modelagem de um aplicativo financeiro.

## 🏦 O Cenário
O IFBank é um banco digital que está redesenhando seu aplicativo. A diretoria pediu que sua equipe de análise modele a principal funcionalidade do sistema: a Área do Pix .

O aplicativo deve permitir que o cliente consulte seu saldo, cadastre suas chaves Pix e, o mais importante, realize transferências via Pix.

Para realizar um Pix, o sistema tem algumas regras de negócio estritas:

O cliente já deve estar logado no aplicativo.
O sistema deve validar se existe a chave Pix de destino (simular uma comunicação com o sistema central do Banco Central - BACEN).
O cliente deve ter saldo suficiente na conta.
Ao finalizar, o valor deve ser debitado da conta do cliente, uma transação deve ser salva no extrato e um comprovante deve ser gerado na tela.
Sua missão é criar um material como um "Dossiê de Análise" deste módulo passando por três etapas.

1. 📝 Etapa 1: O Levantamento (Casos de Uso)
Antes de pensar em código, precisamos mapear as funcionalidades e o roteiro exato de uso.

A. Diagrama de Casos de Uso: Desenhe um diagrama que inclua:

Atores: O Clienteeo Sistema BACEN.
Casos de Uso principais: Consultar Saldo , Cadastrar Chave Pix, Realizar Pixe Autenticar Usuário.
Relacionamento: Lembre-se de usar <<include>>onde for conveniente.
B. Descrição Textual: Faça a descrição textual detalhada apenas do caso de uso "Realizar Pix" .

Defina como Pré-condições (ex: ter saldo, estar logado).
Escreva o Fluxo Principal passo a passo (Cliente digita chave -> Sistema busca dados -> Cliente digita valor...).
Crie pelo menos dois Fluxos de Exceção (Ex: [FE-01] Saldo Insuficiente; [FE-02] Chave Pix Inválida).
📝 Etapa 2: A Interação (Diagrama de Sequência)
Agora vamos focar no comportamento interno. Como os objetos do sistema "conversam" para fazer o Pix acontecer?

Desenhe o Diagrama de Sequência para o Fluxo Principal do caso de uso "Realizar Pix".

Objetos sugeridos: :TelaPix , :ControladorPix, :Contae o próprio ator :Cliente. Você também pode representar o :SistemaBACENrecebimento de uma mensagem de validação.
O Fluxo: Mostre as chamadas de método. Exemplo: A tela pede para o driver validar a chave; o controlador pede para a conta verificar o saldo ( verificarSaldo(valor)); se tudo estiver certo, o driver pede para a conta debitar(valor).
Dica: Use um bloco alt / else(condição) para mostrar o que acontece se o saldo for aprovado vs.
📝 Etapa 3: A Estrutura (Diagrama de Classes)
Com base nos passos e ações que você definiu nas Etapas 1 e 2, construa a "estrutura" desse sistema bancário.

Desenhe o Diagrama de Classes focando no núcleo do sistema:

Aulas obrigatórias: Cliente , Conta, Transacao, ChavePix.
Atributos: Pense nas informações cruciais. A Conta tem saldo, numero, agencia. A Transação tem valor, data, tipo. Lembre-se de usar o encapsulamento (atributos privados -). A classe ChavePIXpode ter: tipo (é CPF, CNPJ, E-mail, Telefone ou Aleatória, poderia ser um Enum), valor: a string em si (ex: " joao@email.com "), dataCadastro (quando foi registrado), status (está ativo, inativo).
Métodos: Traga as ações que você usou no Diagrama de Sequência para cá. A classe Contarequer métodos públicos ( +) como debitar(valor)e creditar(valor).
Relacionamentos: Conecte as classes com as associações corretas e suas multiplicidades . (Ex: Um Cliente tem quantas Contas? 1 ou muitas? Uma Conta tem quantas Transações no extrato?).
📝 Entregas
Ao final, sua equipe deverá entregar um documento contendo:

Imagem do Diagrama de Casos de Uso.
Texto com a Descrição do "Realizar Pix".
Imagem do Diagrama de Sequência do Pix.
Imagem do Diagrama de Classes.

# diagrama 01
https://mermaid.ai/app/projects/360a7928-acd8-4fb2-aecc-6f0afe6b39f7/diagrams/9d9b229f-1819-489b-b778-6e74b7c5febd/share/invite/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkb2N1bWVudElEIjoiOWQ5YjIyOWYtMTgxOS00ODliLWI3NzgtNmU3NGI3YzVmZWJkIiwiYWNjZXNzIjoiRWRpdCIsImlhdCI6MTc4ODk5OTM5MX0.hTscJUA5BZCVM5gy2fnUmkM8Gqt5YjlwviBn-P4Kz00?entryPoint=share-modal

# diagrama 02
https://mermaid.ai/app/projects/b78f948f-051e-476f-bdbc-5fed5af6aee8/diagrams/86ba140f-f838-4ae3-8f15-28612cb5160b/version/v0.1/edit?shouldShowPopup=true&fromOnboarding=true

# diagrama 03
https://mermaid.ai/app/projects/b78f948f-051e-476f-bdbc-5fed5af6aee8/diagrams/522b9a0f-356a-4294-8e7e-95f8c392c4a4/version/v0.1/edit?shouldShowPopup=true&entryPoint=Dashboard