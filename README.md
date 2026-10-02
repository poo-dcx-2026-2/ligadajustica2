# Sistema de Gestão de Biblioteca/Acervo

Projeto incremental da disciplina de **Programação Orientada a Objetos** — DCX/CCAE/UFPB.

Este projeto implementa os serviços de acervo e usuários previstos na Entrega 1. Empréstimos, relatórios, persistência e outras operações continuam como esqueletos para as entregas seguintes.

---

## Começando

Você precisa de **JDK 21** e **Maven**. No IntelliJ IDEA, abra a pasta do projeto e ele reconhece o `pom.xml` sozinho.

```bash
mvn -B verify      # compila e roda os testes
mvn -B test        # só os testes
```

Se tudo estiver certo, o Maven termina com `BUILD SUCCESS` e nenhum teste falhando.

---

## Mapa do projeto

```
src/main/java/br/ufpb/dcx/poo/biblioteca/
├── contrato/          ← CONGELADO. Não edite nada aqui.
│   ├── Biblioteca.java              ponto único de acesso aos serviços
│   ├── AcervoService.java           itens e exemplares
│   ├── UsuarioService.java          usuários
│   ├── EmprestimoService.java       empréstimos, devoluções e reservas
│   ├── RelatorioService.java        consultas e importação em lote
│   ├── *View.java                   o que as consultas devolvem
│   ├── Status*.java                 estados de exemplar e de empréstimo
│   └── excecoes/                    a hierarquia de erros do sistema
│
├── Fabrica.java       ← nome e assinatura congelados; o corpo é seu
│
├── dominio/           ← Item, Exemplar e Usuario; protegem identidade e invariantes
│
└── inicial/           ← serviços em memória e esqueletos das Entregas 2 e 3
    ├── AcervoEmMemoria.java
    ├── UsuariosEmMemoria.java
    ├── EmprestimosNaoImplementados.java
    ├── RelatoriosNaoImplementados.java
    └── BibliotecaInicial.java

src/test/java/…       ← seus testes; comece pelos que já estão aqui
dados/                ← arquivos de exemplo
.github/workflows/    ← a integração contínua, já configurada
```

---

## As duas regras

**1. O pacote `contrato` é congelado.** Não renomeie, não altere assinaturas, não acrescente nem remova métodos. Os testes de correção são escritos contra esses tipos: se você mudar qualquer coisa ali, eles não compilam e a entrega não pode ser avaliada.

**2. `Fabrica.novaBiblioteca()` precisa continuar funcionando.** É por esse método que os testes obtêm o seu sistema. Você vai trocar o que ele devolve — não troque o nome, o pacote nem a assinatura. Cada chamada precisa devolver uma instância nova e independente.

Fora isso, **tudo é seu**. O pacote `inicial` não é modelo: é matéria-prima. Você pode reescrevê-lo inteiro.

---

## O que já funciona e o que falta

| Serviço | Situação |
|---|---|
| `AcervoService` | Cadastro, busca/listagem por título e cadastro/listagem de exemplares implementados nesta entrega. Categoria e baixa de exemplar são da Entrega 2. |
| `UsuarioService` | Cadastro, busca e listagem implementados nesta entrega. `desativar`/`reativar` são da Entrega 2. |
| `EmprestimoService` | Esqueleto. Entrega 2. |
| `RelatorioService` | Esqueleto. Entrega 3. |
| `Biblioteca.salvar/carregar` | Esqueleto. Entrega 2. |

Métodos ainda não implementados lançam `UnsupportedOperationException` com a indicação da entrega. Cada mensagem diz o que fazer.

## Modelo de domínio

O fonte PlantUML está em [`docs/modelo.puml`](docs/modelo.puml), e a imagem correspondente está em [`docs/modelo.png`](docs/modelo.png).

---

## Justificativa das coleções

O acervo usa `Map<String, Item>` para localizar cada item pelo código sem percorrer todos os cadastros. Um segundo `Map<String, Exemplar>` indexa os tombos no acervo inteiro e permite recusar duplicidade entre itens diferentes. Os exemplares também pertencem ao seu `Item`, que mantém a relação e devolve uma cópia da lista.

Os usuários ficam em `Map<String, Usuario>`, porque a matrícula é sua identidade e as operações mais comuns buscam ou recusam duplicidade por matrícula. As consultas ordenadas constroem listas de `View` e não expõem os mapas internos.

## Relato do defeito encontrado

A busca inicial do item comparava códigos com `==`, que verifica se duas referências apontam para o mesmo objeto, em vez de comparar o texto. Reproduzimos o erro cadastrando o código `L1` e buscando com `new String("L1")`: apesar do mesmo conteúdo, a busca dizia que o item não existia. O teste de regressão `codigoComMesmoValorEmOutraInstancia` falhou antes da correção e passou depois que a busca passou a usar a chave do `Map`, que compara Strings pelo valor.

## Um aviso honesto

O ponto de partida continha decisões de design questionáveis e um defeito de comportamento não coberto pelos testes originais. O defeito já foi reproduzido, coberto por um teste de regressão e corrigido; o relato acima registra esse processo. A suíte verde continua sendo evidência dos cenários testados, não prova absoluta de ausência de defeitos.

---

## Extensão autoral

Sua equipe escolhe um acervo próprio: jogos, filmes, quadrinhos, instrumentos, obras locais, recursos de laboratório. A extensão vive **por fora** do contrato — novos tipos, novos serviços, novas regras — e precisa incluir ao menos **uma regra de negócio própria**, documentada abaixo, que os testes de correção não conhecem e que você demonstra na defesa.

### Nossa extensão

**Acervo:** quadrinhos da Liga da Justiça. **Regra própria:** um usuário só pode tomar emprestada uma edição se sua idade for igual ou maior que a classificação indicativa da edição. Isso acrescenta a classificação ao cadastro da edição e uma verificação ao fluxo de empréstimo; a regra e os tipos da extensão ficam fora do pacote `contrato`. Nesta Entrega 1, a regra fica declarada; sua implementação é prevista para a Entrega 2. *(Proposta inferida pelo nome do repositório; a equipe deve confirmar ou ajustar antes de entregar.)*

---

## Uso de ferramentas de IA

O uso é permitido como apoio, desde que declarado em [`DECLARACAO-DE-USO-DE-IA.md`](DECLARACAO-DE-USO-DE-IA.md). Você continua responsável por explicar, testar e adaptar todo o código entregue — inclusive em uma alteração feita presencialmente, sem consulta, na defesa da Entrega 3.

---

## Equipe

| Nome | Matrícula | GitHub |
|---|---|---|
| | | |
| | | |
