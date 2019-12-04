package br.com.minhapadaria.facade;

import br.com.minhapadaria.dao.EstoqueProdutoDao;
import br.com.minhapadaria.dao.ProdutoDao;
import br.com.minhapadaria.dao.VendaDao;
import br.com.minhapadaria.exception.DAOException;
import br.com.minhapadaria.model.EstoqueProduto;
import br.com.minhapadaria.model.Produto;
import br.com.minhapadaria.model.Venda;
import java.util.List;

public class Facade {

    private EstoqueProdutoDao estoqueProdutoDao;
    private ProdutoDao produtoDao;
    private VendaDao vendaDao;

    public Facade() {
        this.estoqueProdutoDao = new EstoqueProdutoDao();
        this.produtoDao = new ProdutoDao();
        this.vendaDao = new VendaDao();
    }

    public Produto salvarProduto(Produto produto) throws DAOException {
        return this.produtoDao.save(produto);
    }

    public Produto editarProduto(Produto produto) throws DAOException {
        return this.produtoDao.update(produto);
    }

    public Produto removerProduto(Produto produto) throws DAOException {
        return this.produtoDao.remove(produto);
    }

    public Produto getProdutoById(Long id) throws DAOException {
        return this.produtoDao.getById(id);
    }

    public List<Produto> listarProdutos() throws DAOException {
        return this.produtoDao.getAll();
    }

    public EstoqueProduto salvarEstoqueProduto(EstoqueProduto estoqueProduto) throws DAOException {
        return this.estoqueProdutoDao.save(estoqueProduto);
    }

    public EstoqueProduto editarEstoqueProduto(EstoqueProduto estoqueProduto) throws DAOException {
        return this.estoqueProdutoDao.update(estoqueProduto);
    }

    public EstoqueProduto removerEstoqueProduto(EstoqueProduto estoqueProduto) throws DAOException {
        return this.estoqueProdutoDao.remove(estoqueProduto);
    }

    public EstoqueProduto getEstoqueProdutoById(Long id) throws DAOException {
        return this.estoqueProdutoDao.getById(id);
    }

    public List<EstoqueProduto> listarEstoquesProduto() throws DAOException {
        return this.estoqueProdutoDao.getAll();
    }

    public Venda salvarVenda(Venda venda) throws DAOException {
        return this.vendaDao.save(venda);
    }

    public Venda editarVenda(Venda venda) throws DAOException {
        return this.vendaDao.update(venda);
    }

    public Venda removerVenda(Venda venda) throws DAOException {
        return this.vendaDao.remove(venda);
    }

    public Venda getVendaById(Long id) throws DAOException {
        return this.vendaDao.getById(id);
    }

    public List<Venda> listarVenda() throws DAOException {
        return this.vendaDao.getAll();
    }

}
