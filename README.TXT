------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE:
PURPOSE OF PROJECT:
VERSION or DATE:
HOW TO START THIS PROJECT:
AUTHORS:
USER INSTRUCTIONS:

ENUNCIADO: 

Nesta segunda parte vamos assumir novas caraterísticas da empresa de
transportes. 

A nossa empresa Tuber possui uma frota de veículos autónomos para
serviço de táxi. A comunicação entre clientes e a empresa está centralizada
numa app de telemóvel. Para requisitar um serviço um cliente
simplesmente regista o pedido na app que acede à sua geo-localização.

Cada cliente tem uma ficha registada na empresa com um nº de cliente,
nome, NIF e tipo de cliente (uma escala de assiduidade que dá direito a
descontos com pelo menos os valores A,B e C). A empresa regista cada
pedido usando o nº de cliente, um nº de serviço (sequencial), a localização
do cliente e a localização do destino. Para simplificação as geo-localizações
devem ser interpretadas como pontos num espaço em R2
. Um veículo é
representado pela sua matrícula, capacidade (em número de passageiros),
geo-localização e uma lista de serviços efetuados. A aplicação deve ter uma
lista com as geo-localizações dos destinos mais populares requisitados à
empresa, permitindo assim a descrição do local ser efetuada pelo nome em
vez de ser pelas suas coordenadas.

O sistema informático da empresa atribui um veículo para executar cada
pedido de táxi. Assim, existe um registo local de casa serviço executado e
dos veículos que estão disponíveis ou em trânsito.


Este processo de atribuição de veículo é feito de acordo com a geolocalização dos intervenientes. Deste modo é sempre verificada a
localização de cada veículo disponível, sendo atribuído ao serviço o veículo
com localização mais próxima.

Há dois tipos de veículos. Um capaz de transportar pessoas e refeições em
que o seu custo é calculado ao quilómetro percorrido. Um segundo que
transporta mercadorias onde o custo é calculado usando o peso
transportado por quilómetro percorrido.

Há vários tipos de serviços: Um com preço e trajeto fixo, outro para
transporte de pequenas mercadorias (tipo correio expresso) com o preço
indexado ao peso do transporte e ainda um para pedido de comida à porta
(Tuber-eats) com o preço dependente do valor da refeição. Finalmente, um
que permite fazer vários trajetos onde o veículo está disponibilizado ao
cliente por pelo menos um dia. O preço final deste serviço é calculado
pelos quilómetros percorridos. Cada serviço regista também a data e a
distância percorrida.

Desenvolva um programa em Java1.8. que permita representar as entidades
acima descritas e responda às solicitações dos seguintes requisitos:
 Definir uma entidade chamada Empresa. Esta entidade regista os
clientes da empresa, os veículos e todos os serviços realizados.
 Permitir adicionar um novo cliente, um novo veículo e um novo
serviço i.e. fazer uma reserva.
 Verificar estado de um veículo;
 Calcular o número de quilómetros que um veiculo já efetuou ao
serviço desta empresa;
 Para um dado cliente listar os serviços contratados por ele;
 Calcular o total faturado num dia;
 Criar uma lista com os carros que um cliente já usou;
 Identificar o serviço mais usado pelos clientes;
 Criar uma estrutura que relacione tipos de serviços com clientes;
 Cada veículo contém uma lista de serviços que deslocaram entidades
transportáveis. As entidades transportáveis são: os clientes,
mercadorias e comidas (representadas pelos seus serviços) e os
veículos que transportam somente pessoas e refeições. Esta noção de
transportáveis deve ser representada na forma de um tipo abstrato
(interface). Alguém transportável responde com o custo calculado
por esse transporte quando é fornecido o custo por Km e com um
número de transportável. Implemente um método na Empresa que
calcule o valor transportável por um determinado veículo.
 Identificar o veículo mais perto de um dado local (uma
georreferência)
 Gravar o sistema em ficheiro via ObjectStreams. No arranque do
programa deve ser lida do ficheiro o estado anterior do sistema
(empresa). Antes de terminar a sua execução, o programa grava o seu
estado em ficheiro;
 Identificar o tipo de serviço mais requisitado à empresa;
 Imprimir uma lista que associa intervalos de distâncias (de 10 em 10
Kms) com os serviços que correspondem a uma distância dentro
desses intervalos. Usar as distâncias registadas nos serviços.

O programa deve conter um conjunto de menus correspondentes aos
requisitos anteriormente descritos, permitindo ao utilizador navegar pelas
diferentes funcionalidades do programa. Deve ser dado especial cuidado às
estruturas de dados usadas para representar as entidades envolvidas neste
projeto.

2. Apresentação
A apresentação e entrega dos projectos práticos será efectuada com todos os
elementos do grupo presentes. É esperado que a execução do programa seja
efectuada por linha de comando i.e se o programa se chamar Tuber então o
comando seria:

C:\> java Tuber

Pela execução deste comando deve aparecer um menu de navegação para
permitir ao utilizador efectuar o conjunto de tarefas acima descritas.
