package br.com.minhapadaria.util;

import br.com.minhapadaria.model.EstoqueProduto;
import br.com.minhapadaria.model.Produto;
import br.com.minhapadaria.model.Venda;
import java.util.ArrayList;
import java.util.List;

public class DataBaseUtil {

    private List<Produto> produtos;
    private List<EstoqueProduto> estoques;
    private List<Venda> vendas;
    private long sequenciaProdutos;
    private long sequenciaEstoques;
    private long sequenciaVendas;
    private static DataBaseUtil instance;

    private DataBaseUtil() {
        this.produtos = new ArrayList<>();
        this.estoques = new ArrayList<>();
        this.vendas = new ArrayList<>();
        this.sequenciaProdutos = 1;
        this.sequenciaEstoques = 2;
        this.sequenciaVendas = 3;
    }

    public static synchronized DataBaseUtil getInstance() {
        if (instance == null) {
            DataBaseUtil.instance = new DataBaseUtil();
        }
        return instance;
    }

    public List<Produto> getProdutos() {
        return getInstance().produtos;
    }
    
    public List<EstoqueProduto> getEstoques() {
        return getInstance().estoques;
    }
    
    public List<Venda> getVendas() {
        return getInstance().vendas;
    }

    public long getSequenciaProdutos() {
        return getInstance().sequenciaProdutos;
    }
    
    public long getSequenciaEstoques() {
        return getInstance().sequenciaEstoques;
    }
    
    public long getSequenciaVendas() {
        return getInstance().sequenciaVendas;
    }

    public long getSequenciaProdutosEIncrementar() {
        long sequencia = getInstance().sequenciaProdutos;
        getInstance().sequenciaProdutos++;
        return sequencia;
    }
    
    public long getSequenciaEstoquesEIncrementar() {
        long sequencia = getInstance().sequenciaEstoques;
        getInstance().sequenciaEstoques++;
        return sequencia;
    }
    
    public long getSequenciaVendasEIncrementar() {
        long sequencia = getInstance().sequenciaVendas;
        getInstance().sequenciaVendas++;
        return sequencia;
    }

}
