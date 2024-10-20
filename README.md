# AUTO CONFIG OLT's ITBS

Este programa serve para fazer gerar scripts para as olts 8820i, G08, G16, AN5k e AN6k da intelbras.

Utilizando do mesmo script da ferramenta de auto-config feita pelo @felipegcoutinho.
E com alguns outros incrementos para os produtos AN5k e AN6k.

## COMO FUNCIONA

Este programa funciona solicitando algumas informações referentes a configuração da olt do cliente, e ao final, ela irá preencher um script, e usando de conexão via socket telnet, irá aplicar tudo na olt automáticamente.

## COMO USAR

Basta baixar a nova release presente e executar conforme o arquivo LEIAME.txt

### FEATURES

- [X] Interface CLI
- [X] Interface Gráfica
- [X] Acesso Telnet
- [X] Compatibilidade Java8
- [X] Compatibilidade JDK 21
- [X] Auto-config uma vlan por pon
- [X] Auto-config uma vlan para todas as pon
- [X] Auto-config uma vlan por pon untagged
- [X] Compativel com grupo de vlan
- [X] Compativel com OLT G16 Intelbras
- [X] Compativel com OLT G08 Intelbras
- [X] Compativel com OLt 8820i Intelbras
- [X] Compatível com OLT AN5k
- [X] Compatível com OLT AN6k
- [X] Migração de OLTs 8820i para AN5k
- [X] Migração de OLTs 8820i para AN6k
- [X] Migração de OLTs G08/G16 para AN5k
- [X] Migração de OLTs G08/G16 para AN6k
