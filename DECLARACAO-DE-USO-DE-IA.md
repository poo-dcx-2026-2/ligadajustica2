# Declaração de uso de ferramentas de IA

Preencha e mantenha atualizado até a entrega final. A ausência da declaração, ou uma
declaração que não corresponde ao histórico do repositório, é tratada como problema de
autoria.

Usar IA não reduz a nota. Não conseguir explicar, testar e adaptar o que foi entregue,
sim.

## Equipe

| Nome | Matrícula |
|---|---|
| Preencher nome completo | Preencher matrícula |

Inclua uma linha para cada integrante da equipe.

## Uso declarado

Uma linha por uso relevante. Se não houve uso, escreva "Não houve uso de ferramentas de IA".

| Data | Ferramenta | Finalidade | Arquivos/trechos afetados | O que foi revisado e alterado por vocês |
|---|---|---|---|---|
| 2026-09-30 | OpenAI Codex | Ler o guia da Entrega 1 e explicar o que começa no passo 5 e quais etapas vêm depois | `guia-entrega-1.pdf`; nenhuma alteração de código | Foi feita análise do guia nesta conversa. A equipe ainda precisa conferir escopo e decisões antes da entrega. |
| 2026-10-01 | OpenAI Codex | Retomar pelo passo 6 após a equipe confirmar que o passo 5 já estava concluído; verificar se o projeto estava na pasta de trabalho | Nenhum arquivo do projeto; inspeção da pasta local | Foi constatado que o clone ainda não estava na pasta de trabalho. A equipe deve confirmar o repositório correto e revisar as etapas de configuração local. |
| 2026-10-02 | OpenAI Codex | Clonar e analisar o repositório; implementar cadastro, busca, ordenação e listagem de itens e exemplares; reproduzir e corrigir a comparação incorreta de códigos | `src/main/java/br/ufpb/dcx/poo/biblioteca/inicial/AcervoEmMemoria.java`; `src/main/java/br/ufpb/dcx/poo/biblioteca/dominio/Item.java`; `src/main/java/br/ufpb/dcx/poo/biblioteca/dominio/Exemplar.java`; `src/test/java/br/ufpb/dcx/poo/biblioteca/AcervoTest.java` | Codex reproduziu o defeito usando duas Strings com o mesmo conteúdo, confirmou a falha do teste e depois o sucesso com a correção. Revisão e adaptação da equipe ainda pendentes. |
| 2026-10-02 | OpenAI Codex | Modelar usuários por matrícula, implementar cadastro, busca e listagem, e ampliar testes de isolamento e casos inválidos | `src/main/java/br/ufpb/dcx/poo/biblioteca/dominio/Usuario.java`; `src/main/java/br/ufpb/dcx/poo/biblioteca/inicial/UsuariosEmMemoria.java`; `src/test/java/br/ufpb/dcx/poo/biblioteca/UsuarioTest.java`; `src/test/java/br/ufpb/dcx/poo/biblioteca/FabricaTest.java` | Codex executou `mvn -B verify`: 29 testes aprovados, sem falhas, erros ou testes ignorados. A equipe ainda deve revisar e adaptar o código e confirmar que consegue explicá-lo. |
| 2026-10-02 | OpenAI Codex | Atualizar README, registrar o uso de IA e produzir o diagrama UML e sua imagem | `README.md`; `DECLARACAO-DE-USO-DE-IA.md`; `docs/modelo.puml`; `docs/modelo.png` | A equipe ainda deve confirmar a extensão autoral e os dados dos integrantes, revisar a documentação e completar as assinaturas. |

Este histórico registra o uso do OpenAI Codex nesta conversa. Não há atividade do Codex registrada aqui nos dias 28 e 29/09; outros usos de IA pelos integrantes devem ser acrescentados pela equipe.

## Compromisso

Ao entregar, a equipe declara que:

- entende cada trecho do código entregue e consegue explicá-lo oralmente;
- testou o que foi gerado, e não apenas verificou que compila;
- adaptou o que foi gerado ao contrato e às decisões de design do projeto;
- está ciente de que cada integrante fará uma alteração individual em sala, sem consulta,
  na defesa da Entrega 3.

Assinaturas (preencher por cada integrante, após revisão; nome, data e assinatura):
