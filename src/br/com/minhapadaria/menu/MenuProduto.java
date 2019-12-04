package br.com.minhapadaria.menu;

import br.com.minhapadaria.exception.DAOException;
import br.com.minhapadaria.facade.Facade;
import br.com.minhapadaria.model.Produto;
import java.util.Scanner;

public class MenuProduto {
    
    private Scanner scanner;
    private Facade facade;
    
    private final int CADASTRAR = 1;
    private final int EDITAR = 2;
    private final int REMOVER = 3;
    private final int LISTAR = 4;
    
    
    public MenuProduto(Scanner scanner) {
        this.scanner = scanner;
        this.facade = new Facade();
    }
    
    public void iniciar(Integer opcaoMenu)  throws DAOException {
        System.out.println("Opcao:" + opcaoMenu);
        switch(opcaoMenu) {
            case CADASTRAR:
                cadastrar();
                break;
            case EDITAR:
                editar();
                break;
            case REMOVER:
                remover();
                break;
            case LISTAR:
                listar();
                break;
        }
    }
    
    private void cadastrar()  throws DAOException {
        Produto produto = new Produto();
        System.out.println("##################### PRODUTO ######################");
        System.out.println("###                  CADASTRAR                   ###");
        System.out.print("$ DESCRICAO: ");
        String descrica = scanner.next();
        produto.setDescricao(descrica);
        System.out.print("$ VALOR (Ex.: 1,99): ");
        produto.setValor(Double.valueOf(scanner.next().replaceAll(",", ".")));
        facade.salvarProduto(produto);
        System.out.println("###                   SUCESSO                    ###");
    }
    
    private void editar()  throws DAOException {
        System.out.println("##################### PRODUTO ######################");
        System.out.println("###                    EDITAR                    ###");
        System.out.print("$ ID: ");
        Long id = Long.valueOf(scanner.next());
        Produto produto = facade.getProdutoById(id);
        System.out.print("$ DESCRICAO: ");
        produto.setDescricao(scanner.next());
        System.out.print("$ VALOR (Ex.: 1,99): ");
        produto.setValor(Double.valueOf(scanner.next().replaceAll(",", ".")));
        facade.editarProduto(produto);
        System.out.println("###                   SUCESSO                    ###");
    }
    
    private void remover()  throws DAOException {
        System.out.println("##################### PRODUTO ######################");
        System.out.println("###                   REMOVER                    ###");
        System.out.print("$ ID DO PRODUTO A SER REMOVIDO: ");
        Long id = Long.valueOf(scanner.next());
        Produto produto = facade.getProdutoById(id);
        if(produto == null) {
            System.out.println("### PRODUTO NAO ENCONTRADO!                      ###\n");
        } else {
            listarProduto(produto);
            System.out.print("$ DESEJA MESMO REMOVER ESSE PRODUTO(s - SIM/n - NAO)? ");
            if(scanner.next().toUpperCase().equals("S")){
                facade.removerProduto(produto);
                System.out.println("### PRODUTO REMOVIDO COM SUCESSO!                ###\n");
            } else {
                System.out.println("### OPERACAO CANCELADA                           ###\n");
            }
        }
    }
    
    private void listar()  throws DAOException {
        System.out.println("##################### PRODUTO ######################");
        System.out.println("###                 LISTAR TODOS                 ###");
        for(Produto produto: facade.listarProdutos()) {
            listarProduto(produto);
            System.out.println("\n");
        }
    }
    
    private void listarProduto(Produto produto) {
        System.out.println("### ID: " + produto.getId());
        System.out.println("### DESCRICAO: " + produto.getDescricao());
        System.out.println("### VALOR: " + String.valueOf(produto.getValor()).replaceAll("\\.", ",") + " R$");
    }
    
}
