package br.ufpb.dcx.poo.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.ufpb.dcx.poo.biblioteca.contrato.Biblioteca;
import br.ufpb.dcx.poo.biblioteca.contrato.UsuarioView;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.BibliotecaException;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.DadosInvalidosException;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.RecursoDuplicadoException;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.RecursoNaoEncontradoException;

/** Testes públicos dos usuários. */
class UsuarioTest {

    private Biblioteca biblioteca;

    @BeforeEach
    void criarBibliotecaVazia() {
        biblioteca = Fabrica.novaBiblioteca();
    }

    @Test
    @DisplayName("usuário cadastrado começa ativo e sem empréstimos")
    void cadastrarEBuscar() throws BibliotecaException {
        biblioteca.usuarios().cadastrarUsuario("2026001", "Ana Souza");

        UsuarioView usuario = biblioteca.usuarios().buscarUsuario("2026001");

        assertEquals("Ana Souza", usuario.nome());
        assertTrue(usuario.ativo());
        assertEquals(0, usuario.emprestimosAtivos());
    }

    @Test
    @DisplayName("matrícula repetida é recusada")
    void matriculaDuplicada() throws BibliotecaException {
        biblioteca.usuarios().cadastrarUsuario("2026001", "Ana Souza");

        assertThrows(RecursoDuplicadoException.class,
                () -> biblioteca.usuarios().cadastrarUsuario("2026001", "Outro Nome"));
        assertEquals(1, biblioteca.usuarios().listarUsuarios().size());
    }

    @Test
    @DisplayName("buscar usuário inexistente lança RecursoNaoEncontradoException")
    void buscarInexistente() {
        assertThrows(RecursoNaoEncontradoException.class,
                () -> biblioteca.usuarios().buscarUsuario("0000"));
    }

    @Test
    @DisplayName("nome em branco é entrada inválida")
    void nomeEmBranco() {
        assertThrows(DadosInvalidosException.class,
                () -> biblioteca.usuarios().cadastrarUsuario("2026001", "   "));
    }

    @Test
    @DisplayName("nome nulo é entrada inválida")
    void nomeNulo() {
        assertThrows(DadosInvalidosException.class,
                () -> biblioteca.usuarios().cadastrarUsuario("2026001", null));
    }

    @Test
    @DisplayName("matrícula em branco é entrada inválida")
    void matriculaEmBranco() {
        assertThrows(DadosInvalidosException.class,
                () -> biblioteca.usuarios().cadastrarUsuario("  ", "Ana Souza"));
    }

    @Test
    @DisplayName("nomes iguais são permitidos quando as matrículas diferem")
    void mesmoNomeComMatriculasDiferentes() throws BibliotecaException {
        biblioteca.usuarios().cadastrarUsuario("1", "Ana Souza");
        biblioteca.usuarios().cadastrarUsuario("2", "Ana Souza");

        assertEquals(2, biblioteca.usuarios().listarUsuarios().size());
    }

    @Test
    @DisplayName("a lista devolvida não permite alterar o cadastro interno")
    void listaDeUsuariosEhDefensiva() throws BibliotecaException {
        biblioteca.usuarios().cadastrarUsuario("1", "Ana Souza");

        biblioteca.usuarios().listarUsuarios().clear();

        assertEquals(1, biblioteca.usuarios().listarUsuarios().size());
    }

    @Test
    @DisplayName("listar devolve os usuários ordenados por nome")
    void listarOrdenado() throws BibliotecaException {
        biblioteca.usuarios().cadastrarUsuario("3", "carlos");
        biblioteca.usuarios().cadastrarUsuario("1", "ana");
        biblioteca.usuarios().cadastrarUsuario("2", "Bruno");

        assertEquals("ana", biblioteca.usuarios().listarUsuarios().get(0).nome());
        assertEquals("carlos", biblioteca.usuarios().listarUsuarios().get(2).nome());
    }

    @Test
    @DisplayName("cadastro vazio de usuários devolve lista vazia")
    void semUsuarios() {
        assertEquals(java.util.List.of(), biblioteca.usuarios().listarUsuarios());
    }
}
