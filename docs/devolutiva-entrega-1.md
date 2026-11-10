# Devolutiva da Entrega 1 — Eduardo e Guilherme

## Nota: 9,0 de 10 (90 de 100 pontos)

Histórico curto, ausência de CI e pouca evidência de contribuição técnica dos dois integrantes.

Avaliei o último commit da branch `main` até 12/10/2026 às 23h59 (o prazo original, 05/10, mais uma semana de extensão), pela leitura do código, dos testes, do histórico de commits, dos pull requests e da documentação. 

## Pontos por critério

| Critério | O que é avaliado | Pontos | Nível |
|---|---|---|---|
| 4.1 | Correção funcional e aderência ao contrato | 20 de 20 | Excelente |
| 4.2 | Modelagem OO, responsabilidades, coesão e acoplamento | 24 de 25 | Excelente |
| 4.3 | Testes e evidências de depuração | 14 de 15 | Excelente |
| 4.4 | Robustez, exceções e casos-limite | 10 de 10 | Excelente |
| 4.5 | Evolução, refatoração e qualidade do código | 10 de 10 | Excelente |
| 4.6 | Git, build, pipeline e documentação | 6 de 10 | Parcial |
| 4.7 | Processo, checkpoints, explicação e autoria | 6 de 10 | Parcial |
| | **Total** | **90 de 100** | |

## Justificativa (onde tirei pontos)

- O histórico tem apenas 4 commits, um PR sem revisão de colega e nenhuma execução do GitHub Actions. A concentração das mudanças num único bloco prejudica o acompanhamento de evolução.

## Checklist de correções

- [ ] **Registrar a revisão de colega no pull request.** Pull request (PR) é o pedido para juntar uma *branch* (linha de trabalho separada) na `main`. A revisão só conta como evidência se aparecer no GitHub: na aba *Files changed* do PR, o colega que **não** escreveu o código clica em *Review changes*, escreve pelo menos um comentário sobre o que leu e escolhe *Approve* ou *Request changes*. Mensagem de commit dizendo "revisado" não substitui isso.
- [ ] **Confirmar que o pipeline roda e fica verde.** Pipeline (ou CI, integração contínua) é a verificação automática que o GitHub Actions executa a cada `push`: compila o projeto e roda os testes. Na aba *Actions* do repositório deve aparecer uma execução com o símbolo verde para o último commit. Se não aparece nenhuma execução, confiram se a pasta `.github/workflows/` do template está no repositório.
- [ ] **Dividir a implementação entre os integrantes.** Cada integrante precisa ter commits de código e de testes com o próprio usuário do GitHub. Uma divisão simples: um integrante cuida do acervo e o outro dos usuários, e cada um revisa o PR do outro. Combinem também que cada um consegue explicar a parte do colega, porque isso é perguntado no checkpoint.
