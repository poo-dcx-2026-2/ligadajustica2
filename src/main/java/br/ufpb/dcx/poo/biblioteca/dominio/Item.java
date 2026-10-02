package br.ufpb.dcx.poo.biblioteca.dominio;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dcx.poo.biblioteca.contrato.StatusExemplar;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.DadosInvalidosException;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.RecursoDuplicadoException;

/** Item do acervo, identificado por um código que não muda após sua criação. */
public final class Item {
    private final String codigo;
    private final String titulo;
    private final String autoria;
    private final String categoria;
    private final int ano;
    private final List<Exemplar> exemplares = new ArrayList<>();

    public Item(String codigo, String titulo, String autoria, String categoria, int ano) {
        exigirTexto(codigo, "codigo");
        exigirTexto(titulo, "titulo");
        this.codigo = codigo;
        this.titulo = titulo;
        this.autoria = autoria;
        this.categoria = categoria;
        this.ano = ano;
    }

    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getAutoria() { return autoria; }
    public String getCategoria() { return categoria; }
    public int getAno() { return ano; }

    public List<Exemplar> getExemplares() { return List.copyOf(exemplares); }

    public int totalDeExemplares() { return exemplares.size(); }

    public int exemplaresDisponiveis() {
        int disponiveis = 0;
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getStatus() == StatusExemplar.DISPONIVEL) {
                disponiveis++;
            }
        }
        return disponiveis;
    }

    public void adicionarExemplar(Exemplar exemplar) throws RecursoDuplicadoException {
        if (exemplar == null || exemplar.getItem() != this) {
            throw new DadosInvalidosException("O exemplar deve pertencer a este item.");
        }
        for (Exemplar cadastrado : exemplares) {
            if (cadastrado.getTombo().equals(exemplar.getTombo())) {
                throw new RecursoDuplicadoException(
                        "Já existe exemplar com o tombo " + exemplar.getTombo());
            }
        }
        exemplares.add(exemplar);
    }

    private static void exigirTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new DadosInvalidosException("O campo " + campo + " é obrigatório.");
        }
    }
}
