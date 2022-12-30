# LabSchool-Projeto2
Projeto final do 1° modelo BackEnd curso DevinHouse

DEVinHouse
Módulo 1 - Projeto Avaliativo 2

SUMÁRIO
1 INTRODUÇÃO	1
2 REQUISITOS DA APLICAÇÃO	1
3 ROTEIRO DA APLICAÇÃO	2
4 CRITÉRIOS DE AVALIAÇÃO	2
5 ENTREGA	2
6 PLANO DE PROJETO	3

1 INTRODUÇÃO
Considerando os assuntos estudados até o momento no módulo 1, vamos aplicar todos os conceitos estudados resolvendo o problema abordado no desenvolvimento do software solicitado pelo cliente Lab School.

2 REQUISITOS DA APLICAÇÃO
A aplicação que deverá ser realizada individualmente deve contemplar os seguintes requisitos:
O sistema deverá ser desenvolvido em Java;
O sistema deve usar como Banco de Dados o SGBD MariaDB;
O sistema deve seguir o Roteiro da Aplicação;
Desenvolvimento de API REST conforme especificação prevista neste documento.

3 ROTEIRO DA APLICAÇÃO
A escola de programação Lab School deseja automatizar todo o sistema de armazenamento de informações referentes aos alunos, pedagogos e professores.
O sistema via linha de comando desenvolvido no projeto 1 fez sucesso, e foi negociado que algumas operações sejam fornecidas como serviços numa API REST!
A API deve conter os serviços descritos nos requisitos deste documento.

Todos os cadastros devem ser derivados da classe “Pessoa”, que possui os seguintes atributos:

A superclasse Pessoa deve ter os atributos:
Nome   (String)
Telefone   (String)
Data de Nascimento  (LocalDate)
CPF  (Long)
Código  (deve ser gerado automaticamente pelo sistema, podendo ser usado  a funcionalidade de auto-incremento do banco de dados).

RF01 - Carregamento de Dados Iniciais
Deve ser utilizado como Sistema Gerenciador de Banco de Dados o MariaDB, e a aplicação deve usar como nome do banco de dados “labschoolbd”. 
Exemplo de configuração do banco de dados no arquivo application.properties:
spring.datasource.url=jdbc:mariadb://localhost:3306/labschoolbd
	Na inicialização do sistema, devem ser carregados os dados iniciais das pessoas listadas abaixo. Implementar estratégia para não haver problemas de inserção duplicada de dados ou falha de carregamento devido a dados anteriormente inseridos (usar condicional para verificar se já foi carregado ou usar a estratégia “create-drop” para recriar o Banco de Dados a cada execução do sistema - exemplo: spring.jpa.hibernate.ddl-auto=create-drop).
Dados iniciais para carregamento:

ALUNOS
código
nome
telefone
data de nascimento
cpf
Situação da Matrícula
Nota 
Qtd Atendimentos
auto-increment
Bart Simpson
11-11111-1212
2014-10-29
11839750073
IRREGULAR
3.5
0
auto-increment
Lisa Simpson
11-22222-2222
2012-10-29 
17158947076
ATIVO
10
0
auto-increment
Meggie Simpson
12-20002-2200
2019-10-29
63701210020
ATIVO
9
0
auto-increment
Milhouse Van Houten
11-33333-2222
2014-10-29
30119137062
ATIVO
8
0
auto-increment
Nelson Muntz
11-44333-4444
2007-10-29
95704094015
INATIVO
2
0


PROFESSORES
código
nome
telefone
data de nascimento
cpf
Estado
Experiência 
Formação Acadêmica
auto-increment
Walter White
14-22998-1882
1982-10-30


40539019011
ATIVO
FULL_STACK
MESTRADO
auto-increment
Jesse Pinkman
44-11111-1992
1997-10-30 
96107295097
ATIVO
BACK_END
GRADUACAO_INCOMPLETA
auto-increment
Hank Schrader
44-11111-1002
1984-10-30
70685977005
ATIVO
FULL_STACK
MESTRADO
auto-increment
Gustavo Fring
44-11001-1002
1977-10-30
57408927085
INATIVO
FRONT_END
GRADUACAO_COMPLETA
auto-increment
Saul Goodman
44-11998-1882
1980-10-30
86940162062
ATIVO
FULL_STACK
MESTRADO


PEDAGOGOS
código
nome
telefone
data de nascimento
cpf
Qtd Atendimentos
auto-increment
John Snow
11-67333-4454
2000-10-30
62316840086
0
auto-increment
Sansa Stark
22-22333-4454
2004-10-30 
49850253053
0
auto-increment
 Tyrion Lannister
 33-77333-4454
1990-10-30
39125106015
0
auto-increment
 Sandor Clegane
11-33333-2222
1995-10-30
89089606009
0



RF02 - Cadastro de Aluno
Serviço de cadastro de Aluno, cuja entidade deve herdar de Pessoa e ter mais os seguintes atributos:
Situação da Matrícula (String ou Enum, podendo assumir os valores: ATIVO, IRREGULAR, ATENDIMENTO_PEDAGOGICO, INATIVO)
Nota do Processo seletivo (Float, no intervalo de 0 a 10)
Total de atendimentos pedagógicos realizados (Integer, sendo um contador que inicia em zero no momento do cadastro. Sempre que um pedagogo realiza um atendimento este valor deve ser incrementado). 
Definição do endpoint:
Requisição: 
HTTP POST no path /api/alunos
No corpo da request, informar objeto json com os seguintes campos: nome, telefone, dataNascimento, cpf, situacao, nota, conforme exemplo abaixo. Usar exatamente esta nomenclatura de campos no json da requisição. 
Todos os campos devem ser validados como sendo de preenchimento obrigatório. O CPF deve ser único por aluno (validar se o CPF informado já foi cadastrado).
{
   "nome": "Krusty",
   "telefone": "99-99876-0001",
   "dataNascimento": "1970-01-02",
   "cpf": 18250669061,
   "situacao": "IRREGULAR",
   "nota": 7.5
}

Response: 
HTTP Status Code 201 (CREATED) em caso de sucesso, constando no corpo da resposta o código atribuído ao novo aluno cadastrado, além dos demais campos. No response, retornar os campos adicionais “codigo” e “atendimentos”, usando obrigatoriamente estes nomes para os campos.
Exemplo:
{
   "codigo": 8,
   "nome": "Krusty",
   "telefone": "99-99876-0001",
   "dataNascimento": "1970-01-31",
   "cpf": 18250669061,
   "situacao": "IRREGULAR",
   "nota": 7.5,
   "atendimentos": 0
}
HTTP Status Code 400 (Bad Request) em caso de requisição com dados inválidos, informando mensagem de erro explicativa no corpo do response. 
HTTP Status Code 409 (Conflict) em caso de CPF já cadastrado para outro aluno, informando mensagem de erro explicativa no corpo do response. 

RF03 - Atualização da Situação da Matrícula de Aluno
Serviço para alterar/atualizar a situação da matrícula de determinado aluno. 
O usuário do sistema poderá alterar esta situação sempre que necessário.
Definição do endpoint:
Requisição: 
HTTP PUT no path /api/alunos/{codigo}
No corpo da request, informar objeto json com campo: situacao, conforme exemplo abaixo. Usar exatamente esta nomenclatura de campo no json. 
O campo deve ser validado como sendo obrigatório e pertencente aos valores possíveis para este campo.
{
   "situacao": "IRREGULAR"
}

Response: 
HTTP Status Code 200 (OK) em caso de sucesso, constando no corpo da resposta os dados atualizados do aluno.
Exemplo:
{
   "codigo": 3,
   "nome": "Meggie Simpson",
   "telefone": "12-20002-2200",
   "dataNascimento": "2019-10-29",
   "cpf": 20011111111,
   "situacao": "IRREGULAR",
   "nota": 9.0,
   "atendimentos": 0
}
HTTP Status Code 400 (Bad Request) em caso de requisição com dados inválidos, informando mensagem de erro explicativa no corpo do response. 
HTTP Status Code 404 (Not Found) em caso de não ser encontrado registro com o código informado, retornando mensagem de erro explicativa no corpo do response. 

RF04 - Consulta de Alunos
Serviço de consulta de alunos cadastrados.
Definição do endpoint:
Requisição: 
HTTP GET no path /api/alunos
Não é necessário request body
Deve prever um query param opcional de filtrar o resultado da consulta pela Situação da Matrícula:
query param = “situacao”  (não obrigatório ser informado na request!)
valores possíveis para serem informados na requisição = ATIVO, IRREGULAR, ATENDIMENTO_PEDAGOGICO, INATIVO
Exemplo de path com o query param informado: 
 /api/alunos?situacao=ATIVO
Caso não seja informado o parâmetro de pesquisa, deve retornar todos os registros da base de dados.
Response: 
HTTP Status Code 200 (OK), com a lista de usuários.
Exemplo:
[
   {
       "codigo": 1,
       "nome": "Bart Simpson",
       "telefone": "11-11111-1212",
       "dataNascimento": "2014-10-29",
       "cpf": 11111111111,
       "situacao": "IRREGULAR",
       "nota": 3.5,
       "atendimentos": 0
   },
   {
       "codigo": 2,
       "nome": "Lisa Simpson",
       "telefone": "11-22222-2222",
       "dataNascimento": "2012-10-29",
       "cpf": 22211111111,
       "situacao": "ATIVO",
       "nota": 10.0,
       "atendimentos": 0
   }
]


RF05 - Consulta de Aluno pelo Código
Serviço de consulta de aluno pelo seu código identificador.
Definição do endpoint:
Requisição: 
HTTP GET no path /api/alunos/{codigo}
Não é necessário request body. 
Response: 
HTTP Status Code 200 (OK), com o dados do aluno..
Exemplo:
{
   "codigo": 1,
   "nome": "Bart Simpson",
   "telefone": "11-11111-1212",
   "dataNascimento": "2014-10-29",
   "cpf": 11111111111,
   "situacao": "IRREGULAR",
   "nota": 3.5,
   "atendimentos": 0
}
HTTP Status Code 404 (Not Found) em caso de não ser encontrado registro com o código informado, retornando mensagem de erro explicativa no corpo do response. 

RF06 - Exclusão de Aluno
Serviço para excluir um aluno pelo código. 
Definição do endpoint:
Requisição: 
HTTP DELETE no path /api/alunos/{codigo}
Não é necessário request body. 
Response: 
HTTP Status Code 204 (No Content) em caso de sucesso, sem necessidade de response body.
HTTP Status Code 404 (Not Found) em caso de requisição com código não existente na base de dados. 

RF07 - Consulta de Professores
Serviço de consulta de professores cadastrados, cuja entidade deve herdar de Pessoa e ter mais os seguintes atributos:
Formação Acadêmica (String ou Enum, podendo assumir os valores: GRADUACAO_INCOMPLETA, GRADUACAO_COMPLETA, MESTRADO, DOUTORADO)
Experiência em desenvolvimento (String ou Enum, podendo assumir os valores: FRONT_END, BACK_END, FULL_STACK)
Estado (String ou Enum, podendo assumir os valores: ATIVO, INATIVO).
Definição do endpoint:
Requisição: 
HTTP GET no path /api/professores
Não é necessário request body ou parâmetros.
Response: 
HTTP Status Code 200 (OK), com a lista de professores.
Exemplo:
[
   {
       "codigo": 4,
       "nome": "Gustavo Fring",
       "telefone": "44-11001-1002",
       "dataNascimento": "1977-10-29",
       "cpf": 57408927085,
       "formacao": "GRADUACAO_COMPLETA",
       "experiencia": "FRONT_END",
       "estado": "INATIVO"
   },
   {
       "codigo": 3,
       "nome": "Hank Schrader",
       "telefone": "44-11111-1002",
       "dataNascimento": "1984-10-29",
       "cpf": 70685977005,
       "formacao": "MESTRADO",
       "experiencia": "FULL_STACK",
       "estado": "ATIVO"
   }
]


RF08 - Consulta de Pedagogos
Serviço de consulta de pedagogos cadastrados, cuja entidade deve herdar de Pessoa e ter mais os seguintes atributos:
Total de Atendimentos Pedagógicos realizados (Integer, sendo um contador que inicia em zero no momento do cadastro. Sempre que um pedagogo realiza um atendimento este valor deve ser incrementado).
Definição do endpoint:
Requisição: 
HTTP GET no path /api/pedagogos
Não é necessário request body ou parâmetros.
Response: 
HTTP Status Code 200 (OK), com a lista de pedagogos (usar obrigatoriamente o nome dos campos conforme exemplo abaixo).
Exemplo:
[
   {
       "codigo": 1,
       "nome": "John Snow",
       "telefone": "11-67333-4454",
       "dataNascimento": "2000-10-29",
       "cpf": 62316840086,
       "atendimentos": 0
   },
   {
       "codigo": 2,
       "nome": "Sansa Stark",
       "telefone": "22-22333-4454",
       "dataNascimento": "2004-10-29",
       "cpf": 49850253053,
       "atendimentos": 0
   }
]


RF09 - Realização de Atendimento Pedagógico
Serviço de atendimento pedagógico, onde deve ser informado o código do aluno e código do pedagogo que participaram do atendimento.
Sempre que um atendimento pedagógico é realizado, devem ser incrementados os atributos de atendimento do aluno e pedagogo envolvidos.
Sempre que um atendimento pedagógico é realizado, a situação da matrícula do aluno deve ser alterada para “Atendimento Pedagógico” (valor = “ATENDIMENTO_PEDAGOGICO”). 
Definição do endpoint:
Requisição: 
HTTP PUT no path /api/atendimentos
No corpo da request, informar objeto json com os seguintes campos: codigoAluno, codigoPedagogo, conforme exemplo abaixo. Usar exatamente esta nomenclatura de campos no json da requisição. 
Ambos os campos devem ser validados como sendo de preenchimento obrigatório. 
{
   "codigoAluno": 1,
   "codigoPedagogo": 1
}
Response: 
HTTP Status Code 200 (OK) em caso de sucesso, constando no corpo da resposta todos os campos previstos para aluno e pedagogo, conforme exemplo abaixo (usar nomenclatura dos campos conforme exemplo).
Exemplo:
{
   "aluno": {
       "codigo": 1,
       "nome": "Bart Simpson",
       "telefone": "11-11111-1212",
       "dataNascimento": "2014-10-29",
       "cpf": 11839750073,
       "situacao": "ATENDIMENTO_PEDAGOGICO",
       "nota": 3.5,
       "atendimentos": 1
   },
   "pedagogo": {
       "codigo": 1,
       "nome": "John Snow",
       "telefone": "11-67333-4454",
       "dataNascimento": "2000-10-29",
       "cpf": 62316840086,
       "atendimentos": 1
   }
}
HTTP Status Code 400 (Bad Request) em caso de requisição com dados inválidos/faltantes, informando mensagem de erro explicativa no corpo do response. 
HTTP Status Code 404 (Not Found) em caso de Aluno ou Pedagogo não encontrado com o código informado, com mensagem de erro explicativa no corpo do response. 

4 CRITÉRIOS DE AVALIAÇÃO
A tabela abaixo apresenta os critérios que serão avaliados durante a correção do projeto. O mesmo possui variação de nota de 0 (zero) a 10 (dez) como nota mínima e máxima, e possui peso de 40% sobre a avaliação do módulo.
Serão desconsiderados e atribuída a nota 0 (zero) os projetos que apresentarem plágio de soluções encontradas na internet ou de outros colegas. Lembre-se: Você está livre para utilizar outras soluções como base, mas não é permitida a cópia.

Nº
Critério de Avaliação
0
0,5
1
1
Aluno implementou a Carga Inicial de dados na aplicação (RF01)
Aluno não implementou.
Aluno implementou parcialmente.
Aluno implementou corretamente.
2
Aluno implementou cadastro de alunos (RF02)
Aluno não implementou.
Aluno implementou parcialmente.
Aluno implementou corretamente.
3
Aluno implementou a  Atualização da Situação da Matrícula de Aluno (RF03)
Aluno não implementou.
Aluno implementou parcialmente.
Aluno implementou corretamente.
4
Aluno implementou a Consulta de Alunos (RF04)
Aluno não implementou.
Aluno implementou parcialmente.
Aluno implementou corretamente.
5
Aluno implementou a Consulta de Aluno pelo Código (RF05)
Aluno não implementou.
Aluno implementou parcialmente.
Aluno implementou corretamente.
6
Aluno implementou Exclusão de Alunos (RF06)
Aluno não implementou.
Aluno implementou parcialmente.
Aluno implementou corretamente.
7
Aluno implementou a Consulta de Professores (RF07)
Aluno não implementou.
Aluno implementou parcialmente.
Aluno implementou corretamente.
8
Aluno implementou a Consulta de Pedagogos (RF08)
Aluno não implementou.
Aluno implementou parcialmente.
Aluno implementou corretamente.
9
Aluno implementou a Realização de Atendimento Pedagógico
(RF09)
Aluno não implementou.
Aluno implementou parcialmente.
Aluno implementou corretamente.
10
Avaliação de Código:
Aluno seguiu convenções da linguagem e do framework; 
Deixou código organizado e de fácil entendimento.
Aluno não atendeu aos critérios.
Aluno atendeu parcialmente aos critérios.
Aluno atendeu completamente aos critérios.


5 ENTREGA
O código desenvolvido deverá ser submetido no Github, e o link deverá ser disponibilizado na tarefa Módulo 1 - Projeto Avaliativo 2, presente na semana 12 do AVA até o dia 11/12/2022 às 23h55.




Todos os alunos deverão entregar o link do seu repositório do Github na atividade do AVA! 





O repositório deverá ser privado, com as seguintes pessoas adicionadas:
Tiago Albuquerque - tiagoamp  (tiagoamp@gmail.com)
Fernando Puntel - fernando.puntel@edu.sc.senai.br

Não serão aceitos projetos submetidos após a data limite da atividade, e, ou alterados depois de entregues.

Importante:
Será considerado como data final de entrega a última atualização no repositório do projeto no Github. Lembre-se de não modificar o código até receber sua nota.
Não esqueça de submeter submeter o link no AVA.

6 PLANO DE PROJETO
Ao construir a aplicação proposta, o aluno estará colocando em prática os aprendizados em:
Programação Orientada a Objetos: Conceitos de POO, Classes e Objetos.
Modelagem: Criação de Classes e Abstração.
JAVA: Operadores e Classes da linguagem.
Spring: utilização do framework.
Banco de Dados e SQL: Uso de BD Relacional e SQL com e sem ORM.
API REST: Padrões teóricos e conceitos práticos de APIs REST. 


7 DICAS
O uso da biblioteca lombok é permitido, mas opcional.
Para mapear uma superclasse com JPA, a exemplo da classe Pessoa, gerando uma tabela para cada sub-classe, usamos anotações @MapperSuperclass desta forma: 

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa { … }

