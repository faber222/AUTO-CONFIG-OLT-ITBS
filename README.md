# AUTO-CONFIG G16

Este programa serve para fazer o auto-config da olt G16 e G08 da intelbras, tanto para uma vlan por pon, quanto para uma vlan para todas as pon.

Utilizando do mesmo script da ferramenta de auto-config feita pelo @felipegcoutinho.

## COMO FUNCIONA

Este programa funciona solicitando algumas informações referentes a configuração da olt do cliente, e ao final, ela irá preencher um script, e usando de conexão via socket telnet, irá aplicar tudo na olt automáticamente.

## COMO USAR

Para usar a ferramenta, será necessário apenas fazer o download do java8, o mesmo java que é utilizado para executar o resettools.
Mas nos primeiros releases, a ferramenta será compativel apenas com o JDK 21.

## PLANOS FUTUROS

O objetivo da aplicação é possuir uma interface gráfica simples, semelhante a um putty, usando o JOptionPane, mas o objetivo é que futuramente, seja possivel fornecer uma interface mais avançada, com opções de seleção, igual a um software convencional.

Também terá a implementação de uma instalação unificada para windows, visando criar um executavel .exe na maquina do cliente.

### FEATURES

- [X] Interface CLI
- [X] Interface Gráfica
- [X] Acesso Telnet
- [ ] Compatibilidade Java8
- [X] Compatibilidade JDK 21
- [X] Auto-config uma vlan por pon
- [X] Auto-config uma vlan para todas as pon
- [X] Auto-config uma vlan por pon untagged
- [X] Compativel com OLT G16 Intelbras
- [X] Compativel com OLT G08 Intelbras
- [ ] Compativel com OLt 8820i Intelbras
- [ ] Possibilidade de rollback
- [ ] Finalizado
