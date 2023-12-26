# AUTO-CONFIG G16

Este programa serve para fazer o auto-config da olt G16 da intelbras, tanto para uma vlan por pon, quanto para uma vlan para todas as pon.

Utilizando do mesmo script da ferramenta de auto-config feita pelo @felipegcoutinho.

## COMO FUNCIONA

Este programa funciona solicitando algumas informações referentes a configuração da olt do cliente, e ao final, ela irá preencher um script, e usando de conexão via socket telnet, irá aplicar tudo na olt automáticamente.

## COMO USAR

Para usar a ferramenta, será necessário apenas fazer o download do java8, o mesmo java que é utilizado para executar o resettools.
Mas nos primeiros releases, a ferramenta será compativel apenas com o JDK 21.

## PLANOS FUTUROS

O objetivo da aplicação é possuir uma interface gráfica simples, semelhante a um putty, usando o JOptionPane, mas que futuramente, seja possivel fornecer uma interface mais avançada, com opções de seleção, igual a um software convencional.

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
- [ ] Possibilidade de rollback
- [ ] Finalizado


/**
 * Esta classe implementa uma conexão Telnet para acessar um dispositivo de
 * rede,
 * como uma OLT (Optical Line Terminal).
 * 
 * <p>
 * Os atributos padrão são configurados para acesso a uma OLT, mas podem ser
 * personalizados conforme necessário.
 * </p>
 * 
 * <p>
 * Os comandos padrão executados na OLT incluem a ativação, exibição de
 * interfaces,
 * exibição de informações de IP e exibição de rotas IP.
 * </p>
 * 
 * <p>
 * O acesso Telnet é realizado por meio de um socket, e a leitura das respostas
 * é feita em uma thread separada para permitir a leitura em tempo real.
 * </p>
 * 
 * <p>
 * O usuário pode interagir inserindo comandos adicionais por meio de uma janela
 * de entrada de dados.
 * </p>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Telnet">Telnet - Wikipedia</a>
 */