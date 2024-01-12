
#import "./article.typ": *

#show: doc => article(
  title: "TUTORIAL OLT-AUTO-CONFIG",
  subtitle: "",
  authors: ("",),
  date: "2024-1",
  doc,
)


#show raw: it => block(
  fill: rgb("#1d2433"),
  width: 100%,
  inset: 8pt,
  radius: 5pt,
  text(fill: rgb("#a2aabc"), it)
)

= Introdução
Esse tutorial tem como objetivo demonstrar o uso do programa de auto-config das olts. 
\

O programa OLT-AUTO-CONFIG serve para automatizar o procedimento de aplicação de configuração de auto-config das nossas olts, onde o mesmo gera o script de ativação e também aplica os comandos através de acesso telnet na olt. Apenas VIA TELNET. 
\

E fornece compatibilidade com as 3 OLTs INTELBRAS que temos hoje, desconsiderando as olts da parceria com a fiberhome e a 8820G. 
\
Recomenda-se fortemente utilizar o programa pelo terminal, a fim de ver os comandos serem aplicados dentro da OLT. 

#pagebreak()

= Guia de execução
Para poder executar o programa via terminal, basta seguir o passo-a-passo abaixo:

#rect[
  #figure(
  image("assets/Parte18.png", width: 100%),
  caption: [
    Pasta contendo o download do arquivo 
  ],
)
]
Basta acessar a pasta onde o programa se encontra.

#rect[
  #figure(
  image("assets/Parte20.png", width: 100%),
  caption: [
    Escrevendo CMD 
  ],
)
]
Digitar cmd na barra de navegação e teclar Enter.

#rect[
  #figure(
  image("assets/Parte21.png", width: 100%),
  caption: [
    Digitar o comando para executar arquivo java 
  ],
)
]
Digitar o comando para execução de programas java.

#rect[
  #figure(
  image("assets/Parte22.png", width: 100%),
  caption: [
    Usar programa pelo terminal 
  ],
)
]
Usar o programa e acompanhar o log via cmd.
#pagebreak()

= Configuração G16/G08
No primeiro acesso, a seguinte imagem aparecerá para o usuário, onde o mesmo deve optar por "Avancar". 
#set align(center)
#rect[
  #figure(
  image("assets/Parte1.png", width: 60%),
  caption: [
    Tela inicial 
  ],
)
]
#set align(left)
Após Avançar, será mostrado o menu de opções: 
#set align(center)
#rect[
  #figure(
  image("assets/Parte2.png", width: 60%),
  caption: [
    Menu de opções de OLTs 
  ],
)
]
#set align(left)
Cada opção leva a geração de um script único, onde cada produto apresenta sua particularidade. 
\
\
Sendo que, no final de cada configuração, terá na parta raiz do programa, um arquivo .txt com todos os scripts gerados para determinada olt, a fim de facilitar na hora de compartilhar no atendimento o que foi feito para o cliente. 

#pagebreak()
Caso seja escolhido para configurar a OLT G16 ou G08, o menu de opções será este: 
#set align(center)
#rect[
  #figure(
  image("assets/Parte4.png", width: 60%),
  caption: [
    Escolha da porta uplink da olt 
  ],
)
]
#set align(left)

#set align(center)
#rect[
  #figure(
  image("assets/Parte5.png", width: 60%),
  caption: [
   Escolha o modo do auto-config  
  ],
)
]
#set align(left)

#set align(center)
#rect[
  #figure(
  image("assets/Parte6.png", width: 60%),
  caption: [
   Escolha do padrão da CPE de terceiros  
  ],
)
]
#set align(left)

#pagebreak()
Na hora de definir qual o range vlan, deve ser usado o padrão de range inicio-fim ou o padrão de valores separados por vírgula, exemplo: 
\
_*10,20,30,40,50,60,70,80,...,160*_
\
Lembrando que, caso seja definido o range incorretamente, não conseguirá avançar. 

#set align(center)
#rect[
  #figure(
  image("assets/Parte7.png", width: 60%),
  caption: [
   Escolha do range vlan  
  ],
)
]
#set align(left)

#set align(center)
#rect[
  #figure(
  image("assets/Parte7.1.png", width: 60%),
  caption: [
   Escolha do grupo vlan   
  ],
)
]
#set align(left)

O mesmo padrão segue para o range do profile vlan.
\
Como o número de vlans é igual ao número de profiles vlan, o range poderá ter o mesmo tamanho, no caso 16. 
\
*E no caso da G08 será de apenas 8;*

#set align(center)
#rect[
  #figure(
  image("assets/Parte8.png", width: 60%),
  caption: [
   Escolha do range do profile vlan
  ],
)
]
#set align(left)

#set align(center)
#rect[
  #figure(
  image("assets/Parte8.1.png", width: 60%),
  caption: [
   Escolha do grupo do profile vlan 
  ],
)
]
#set align(left)

Ja para o range do profile line, sempre será o dobro do range de vlan, visto que, é necessário que sejam x lines para bridge e y lines para router. 
\
Ou seja, se for para a G16, teremos um range de 32 e na G08 teremos um range de 16. 

#pagebreak()

#set align(center)
#rect[
  #figure(
  image("assets/Parte9.png", width: 60%),
  caption: [
   Escolha do range do profile line 
  ],
)
]
#set align(left)

#set align(center)
#rect[
  #figure(
  image("assets/Parte9.1.png", width: 60%),
  caption: [
   Escolha do grupo do profile line 
  ],
)
]
#set align(left)

*O grupo segue respetivamente o número da porta pon ou do grupo vlan.*
\
\
Após definir todos os parâmetros, na pasta raiz, será gerado um .txt com o srcipt final: 
#set align(center)
#rect[
  #figure(
  image("assets/Parte10.png", width: 100%),
  caption: [
   Visualização do script gerado 
  ],
)
]
#set align(left)

Após isso, fica a critério do analista digitar ou não os dados de acesso da olt para que o programa aplique automaticamente os comandos do arquivo. 
\
Visto que o mesmo pode pegar o script e copiar e colar manualmente na olt. 
\
\
E o mesmo padrão se repete para a G08. 

#pagebreak()

= Configuração 8820I
Para a configuração da 8820i, o menu é desta forma: 
#set align(center)
#rect[
  #figure(
  image("assets/Parte11.png", width: 60%),
  caption: [
   Escolha porta uplink da olt 
  ],
)
]
#set align(left)

#set align(center)
#rect[
  #figure(
  image("assets/Parte12.png", width: 60%),
  caption: [
   Escolha do modo de auto-config da olt 
  ],
)
]
#set align(left)

#set align(center)
#rect[
  #figure(
  image("assets/Parte13.png", width: 60%),
  caption: [
   Escolha do padrão de CPE de terceiros 
  ],
)
]
#set align(left)

#set align(center)
#rect[
  #figure(
  image("assets/Parte14.png", width: 60%),
  caption: [
   Escolha do tipo de porta uplink da olt 
  ],
)
]
#set align(left)

#set align(center)
#rect[
  #figure(
  image("assets/Parte15.png", width: 60%),
  caption: [
   Escolha do modo da bridge uplink das CPEs 
  ],
)
]
#set align(left)

O range vlan segue o mesmo padrão da G16 e G08. 
#set align(center)
#rect[
  #figure(
  image("assets/Parte16.png", width: 60%),
  caption: [
   Escolha do range vlan 
  ],
)
]
#set align(left)

#set align(center)
#rect[
  #figure(
  image("assets/Parte16.1.png", width: 60%),
  caption: [
   Escolha do grupo vlan 
  ],
)
]
#set align(left)

O grupo vlan segue respectivamente o número da porta. 
\
Mas caso o cliente opte em alguns dos casos por apenas uma vlan, será solicitado apenas a vlan que o cliente deseja, sem a necessidade de colocar em formato de range. 
\
*_Por fim, basta colocar o ip, porta telnet, usuário e senha da olt para ele aplicar todos os comandos do script em questão._*