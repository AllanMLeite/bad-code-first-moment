# Bad Code - Relatório

## Primeiro momento
Possuímos uma impressão do relatório de pessoas e alguns ajustes foram detectados
nesse primeiro momento para atender algumas demandas dos nossos cliente. 
Abaixo foram listados o cenários de melhoria da nossa impressão do Relatório de Pessoas.

* **Cenário 1: Corrigir erro na impressão do relatório de pessoas. Está aparecendo "ll"**<br>
Dado que seja realizado uma impressão de relatório<br> 
Quando o relatório for impresso<br>
Então não deve aparecer 'll'<br>

* **Cenário 2: Telefone com menos de 10 dígitos**<br>
Dado que seja realizado uma impressão de relatório <br>
 E a pessoa tenha telefone celular<br> 
Quando for imprimir os dados da pessoa<br> 
 E o números de dígitos do telefone é diferente de 10 dígitos<br>
Então deve dar a mensagem "Telefone invalido!"<br>
 E não deve imprimir a pessoa no relatório<br>

* **Cenário 3: Telefone com 10 dígitos**<br>
Dado que seja realizado uma impressão de relatório <br>
 E a pessoa tenha telefone celular<br> 
Quando for imprimir os dados da pessoa<br> 
 E o telefone tem 10 dígitos<br>
Então deve ser possível imprimir a pessoa no relatório<br>
 
* **Cenário 4: Introduzir validação a CPF invalido**<br>
Dado que seja realizado uma impressão de relatório<br>
 E a pessoa tenha um CPF<br>
Quando for imprimir os dados da pessoa <br>
 E o CPF é invalido<br>
Então deve dar a mensagem "CPF invalido!"<br>
 E não deve imprimir a pessoa no relatório<br>
 
* **Cenário 5: Introduzir validação a CPF valido**<br>
Dado que seja realizado uma impressão de relatório<br>
 E a pessoa tenha um CPF<br>
Quando for imprimir os dados da pessoa <br>
 E o CPF é valido<br>
Então deve ser possível imprimir a pessoa no relatório<br>

* **Cenário 6: Formato de impressão**<br> 
Dado que seja realizado uma impressão de relatório<br> 
 E a pessoa tenha telefone celular valido<br>
 E a pessoa tenha um CPF valido<br>
Quando for imprimir os dados da pessoa <br>
Então o formado de impressão deve ser:<br>
*Nome: Fulano da Silva<br>
Fone: XXXXXXXXX<br>
CPF: XXXXXXXXXXX<br>*

## Segundo momento
* Com base nas necessidades e regras de negócio existentes, reeescreva do zero a aplicação
* Cadastrar Pessoas com as seguintes informações (nome, sobrenome, telefone residencial, telefone celular, cpf)
* Validar as seguintes informações (CPF - telefone celular com mínimo de 10 digitos, nome)
* Utilize um timebox para realizar o exercício (Ex.: 3 horas) e avalie o que consegues desenvolver.

