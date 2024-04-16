# AUTO-CONFIG G16

Este programa serve para fazer o auto-config da olt G16 e G08 da intelbras, tanto para uma vlan por pon, quanto para uma vlan para todas as pon.

Utilizando do mesmo script da ferramenta de auto-config feita pelo @felipegcoutinho.

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
- [X] Compativel com OLT G16 Intelbras
- [X] Compativel com OLT G08 Intelbras
- [X] Compativel com OLt 8820i Intelbras
- [X] Compatibilidade com grupo de vlan separados por ","
- [-] Possibilidade de rollback
- [-] Finalizado
