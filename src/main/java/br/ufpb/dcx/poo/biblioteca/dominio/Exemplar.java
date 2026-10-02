package br.ufpb.dcx.poo.biblioteca.dominio;

import br.ufpb.dcx.poo.biblioteca.contrato.StatusExemplar;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.DadosInvalidosException;

/** Exemplar físico de um item, com tombo imutável e estado controlado. */
public final class Exemplar {
    private final String tombo;
    private final Item item;
    private StatusExemplar status;

    public Exemplar(String tombo, Item item) {
        if (tombo == null || tombo.isBlank()) {
            throw new DadosInvalidosException("O tombo é obrigatório.");
        }
        if (item == null) {
            throw new DadosInvalidosException("O item do exemplar é obrigatório.");
        }
        this.tombo = tombo;
        this.item = item;
        this.status = StatusExemplar.DISPONIVEL;
    }

    public String getTombo() { return tombo; }
    public Item getItem() { return item; }
    public StatusExemplar getStatus() { return status; }

    public void setStatus(StatusExemplar status) {
        if (status == null) {
            throw new DadosInvalidosException("O status do exemplar é obrigatório.");
        }
        this.status = status;
    }
}
