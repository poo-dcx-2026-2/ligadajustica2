package br.ufpb.dcx.poo.biblioteca;

import br.ufpb.dcx.poo.biblioteca.contrato.Biblioteca;
import br.ufpb.dcx.poo.biblioteca.inicial.BibliotecaInicial;


public final class Fabrica {

    private Fabrica() {
    }


    public static Biblioteca novaBiblioteca() {
        return new BibliotecaInicial();
    }
}
