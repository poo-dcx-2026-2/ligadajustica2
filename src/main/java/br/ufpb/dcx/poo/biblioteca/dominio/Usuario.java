package br.ufpb.dcx.poo.biblioteca.dominio;

import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.DadosInvalidosException;

/** Usuário do sistema, identificado de forma estável pela matrícula. */
public final class Usuario {
    private final String matricula;
    private final String nome;
    private boolean ativo = true;
    private int emprestimosAtivos;

    public Usuario(String matricula, String nome) {
        exigirTexto(matricula, "matricula");
        exigirTexto(nome, "nome");
        this.matricula = matricula;
        this.nome = nome;
    }

    public String getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public boolean isAtivo() { return ativo; }
    public int getEmprestimosAtivos() { return emprestimosAtivos; }

    private static void exigirTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new DadosInvalidosException("O campo " + campo + " é obrigatório.");
        }
    }
}
