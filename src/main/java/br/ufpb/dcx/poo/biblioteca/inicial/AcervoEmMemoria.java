package br.ufpb.dcx.poo.biblioteca.inicial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.ufpb.dcx.poo.biblioteca.contrato.AcervoService;
import br.ufpb.dcx.poo.biblioteca.contrato.ExemplarView;
import br.ufpb.dcx.poo.biblioteca.contrato.ItemView;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.DadosInvalidosException;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.OperacaoNaoPermitidaException;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.RecursoDuplicadoException;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.RecursoNaoEncontradoException;
import br.ufpb.dcx.poo.biblioteca.dominio.Exemplar;
import br.ufpb.dcx.poo.biblioteca.dominio.Item;

/** Implementação em memória do cadastro de itens e exemplares. */
public class AcervoEmMemoria implements AcervoService {

    private final Map<String, Item> itensPorCodigo = new HashMap<>();
    private final Map<String, Exemplar> exemplaresPorTombo = new HashMap<>();

    /**
     * Cadastra um item após validar os campos obrigatórios e a unicidade do código.
     * O estado do acervo não muda quando os dados são inválidos ou o código já existe.
     * @throws DadosInvalidosException se código ou título forem nulos ou em branco
     * @throws RecursoDuplicadoException se o código já estiver cadastrado
     */
    @Override
    public void cadastrarItem(String codigo, String titulo, String autoria,
                              String categoria, int ano)
            throws RecursoDuplicadoException {
        exigirTexto(codigo, "codigo");
        exigirTexto(titulo, "titulo");
        if (itensPorCodigo.containsKey(codigo)) {
            throw new RecursoDuplicadoException("Já existe item com o código " + codigo);
        }

        Item item = new Item(codigo, titulo, autoria, categoria, ano);
        itensPorCodigo.put(item.getCodigo(), item);
    }

    /** Busca um item cadastrado pelo seu código. */
    @Override
    public ItemView buscarItem(String codigo) throws RecursoNaoEncontradoException {
        exigirTexto(codigo, "codigo");
        return paraView(exigirItem(codigo));
    }

    /** Devolve uma cópia dos itens, ordenada pelo título sem distinguir caixa. */
    @Override
    public List<ItemView> listarItens() {
        List<ItemView> resultado = new ArrayList<>();
        for (Item item : itensPorCodigo.values()) {
            resultado.add(paraView(item));
        }
        resultado.sort(Comparator.comparing(ItemView::titulo,
                String.CASE_INSENSITIVE_ORDER));
        return resultado;
    }

    /** Busca títulos que contenham o trecho informado, sem distinguir caixa. */
    @Override
    public List<ItemView> buscarPorTitulo(String trecho) {
        exigirTexto(trecho, "trecho");
        String consulta = trecho.toLowerCase(Locale.ROOT);
        List<ItemView> resultado = new ArrayList<>();
        for (Item item : itensPorCodigo.values()) {
            if (item.getTitulo().toLowerCase(Locale.ROOT).contains(consulta)) {
                resultado.add(paraView(item));
            }
        }
        resultado.sort(Comparator.comparing(ItemView::titulo,
                String.CASE_INSENSITIVE_ORDER));
        return resultado;
    }

    @Override
    public List<ItemView> buscarPorCategoria(String categoria) {
        throw new UnsupportedOperationException("Entrega 2: implementar buscarPorCategoria");
    }

    /**
     * Adiciona ao item um exemplar disponível, usando um tombo único no acervo.
     * @throws DadosInvalidosException se tombo ou código do item forem nulos ou em branco
     * @throws RecursoNaoEncontradoException se o item não existir
     * @throws RecursoDuplicadoException se o tombo já estiver em uso
     */
    @Override
    public void adicionarExemplar(String codigoDoItem, String tombo)
            throws RecursoNaoEncontradoException, RecursoDuplicadoException {
        exigirTexto(tombo, "tombo");
        exigirTexto(codigoDoItem, "codigoDoItem");
        Item item = exigirItem(codigoDoItem);
        if (exemplaresPorTombo.containsKey(tombo)) {
            throw new RecursoDuplicadoException("Já existe exemplar com o tombo " + tombo);
        }

        Exemplar exemplar = new Exemplar(tombo, item);
        item.adicionarExemplar(exemplar);
        exemplaresPorTombo.put(exemplar.getTombo(), exemplar);
    }

    /** Devolve uma cópia dos exemplares do item, em ordem de tombo. */
    @Override
    public List<ExemplarView> listarExemplares(String codigoDoItem)
            throws RecursoNaoEncontradoException {
        exigirTexto(codigoDoItem, "codigoDoItem");
        Item item = exigirItem(codigoDoItem);
        List<ExemplarView> resultado = new ArrayList<>();
        for (Exemplar exemplar : item.getExemplares()) {
            resultado.add(new ExemplarView(exemplar.getTombo(), item.getCodigo(),
                    exemplar.getStatus()));
        }
        resultado.sort(Comparator.comparing(ExemplarView::tombo));
        return resultado;
    }

    @Override
    public void baixarExemplar(String tombo)
            throws RecursoNaoEncontradoException, OperacaoNaoPermitidaException {
        throw new UnsupportedOperationException("Entrega 2: implementar baixarExemplar");
    }

    private Item exigirItem(String codigo) throws RecursoNaoEncontradoException {
        Item item = itensPorCodigo.get(codigo);
        if (item == null) {
            throw new RecursoNaoEncontradoException("Item não encontrado: " + codigo);
        }
        return item;
    }

    private ItemView paraView(Item item) {
        return new ItemView(item.getCodigo(), item.getTitulo(), item.getAutoria(),
                item.getCategoria(), item.getAno(), item.totalDeExemplares(),
                item.exemplaresDisponiveis());
    }

    private static void exigirTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new DadosInvalidosException("O campo " + campo + " é obrigatório.");
        }
    }
}
