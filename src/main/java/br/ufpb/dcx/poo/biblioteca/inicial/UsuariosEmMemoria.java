package br.ufpb.dcx.poo.biblioteca.inicial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufpb.dcx.poo.biblioteca.contrato.UsuarioService;
import br.ufpb.dcx.poo.biblioteca.contrato.UsuarioView;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.DadosInvalidosException;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.OperacaoNaoPermitidaException;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.RecursoDuplicadoException;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.RecursoNaoEncontradoException;
import br.ufpb.dcx.poo.biblioteca.dominio.Usuario;

/** Cadastro em memória de usuários, indexado pela matrícula. */
public class UsuariosEmMemoria implements UsuarioService {

    private final Map<String, Usuario> usuariosPorMatricula = new HashMap<>();

    /**
     * Registra usuário ativo e sem empréstimos, identificado pela matrícula.
     * @throws DadosInvalidosException se matrícula ou nome forem nulos ou em branco
     * @throws RecursoDuplicadoException se a matrícula já estiver cadastrada
     */
    @Override
    public void cadastrarUsuario(String matricula, String nome)
            throws RecursoDuplicadoException {
        exigirTexto(matricula, "matricula");
        exigirTexto(nome, "nome");
        if (usuariosPorMatricula.containsKey(matricula)) {
            throw new RecursoDuplicadoException(
                    "Já existe usuário com a matrícula " + matricula);
        }
        Usuario usuario = new Usuario(matricula, nome);
        usuariosPorMatricula.put(usuario.getMatricula(), usuario);
    }

    /** Busca usuário pela matrícula, lançando exceção quando ela não existe. */
    @Override
    public UsuarioView buscarUsuario(String matricula) throws RecursoNaoEncontradoException {
        exigirTexto(matricula, "matricula");
        Usuario usuario = usuariosPorMatricula.get(matricula);
        if (usuario == null) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado: " + matricula);
        }
        return paraView(usuario);
    }

    /** Devolve uma cópia dos usuários ordenada pelo nome sem distinguir caixa. */
    @Override
    public List<UsuarioView> listarUsuarios() {
        List<UsuarioView> resultado = new ArrayList<>();
        for (Usuario usuario : usuariosPorMatricula.values()) {
            resultado.add(paraView(usuario));
        }
        resultado.sort(Comparator.comparing(UsuarioView::nome,
                String.CASE_INSENSITIVE_ORDER));
        return resultado;
    }

    @Override
    public void desativarUsuario(String matricula)
            throws RecursoNaoEncontradoException, OperacaoNaoPermitidaException {
        throw new UnsupportedOperationException("Entrega 2: implementar desativarUsuario");
    }

    @Override
    public void reativarUsuario(String matricula) throws RecursoNaoEncontradoException {
        throw new UnsupportedOperationException("Entrega 2: implementar reativarUsuario");
    }

    private UsuarioView paraView(Usuario usuario) {
        return new UsuarioView(usuario.getMatricula(), usuario.getNome(),
                usuario.isAtivo(), usuario.getEmprestimosAtivos());
    }

    private static void exigirTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new DadosInvalidosException("O campo " + campo + " é obrigatório.");
        }
    }
}
