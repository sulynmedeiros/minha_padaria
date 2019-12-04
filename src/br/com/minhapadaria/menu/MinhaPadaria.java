package br.com.minhapadaria.menu;

import br.com.minhapadaria.dao.PersistenceUtil;
import br.com.minhapadaria.exception.DAOException;
import java.util.Scanner;

public class MinhaPadaria {

    public static void main(String[] args)  throws DAOException {
        PersistenceUtil.getEntityManager();
        Scanner leitor = new Scanner(System.in);
        Integer opcaoMenu = 0;
        MenuProduto menuProduto = new MenuProduto(leitor);
        do{
            iniciar();
            opcaoMenu = Integer.valueOf(leitor.next());
            if(opcaoMenu>=1 && opcaoMenu<=4){
                menuProduto.iniciar(opcaoMenu);
            } else if (opcaoMenu>=5 && opcaoMenu <= 7){
            } else if (opcaoMenu>=8 && opcaoMenu <=10) {
            } else if (opcaoMenu != 0) {
                System.out.println("\nEntrada invalida, selecione um numero de 0 a 10!\n");
            }
        }while(opcaoMenu != 0);
        System.out.println("\nObrigado por usar o nosso sistema!");
    }
    
    public static void iniciar() {
        System.out.println("################ PROGRAMA  INICIADO ################");
        System.out.println("###                     MENU                     ###");
        System.out.println("### PRODUTOS  -----------------------------------###");
        System.out.println("### 1 - CADASTRAR PRODUTO                        ###");
        System.out.println("### 2 - EDITAR PRODUTO                           ###");
        System.out.println("### 3 - REMOVER PRODUTO                          ###");
        System.out.println("### 4 - LISTAR PRODUTOS CADASTRADOS              ###");
        System.out.println("### ESTOQUE  ------------------------------------###");
        System.out.println("### 5 - ADICIONAR ESTOQUE                        ###");
        System.out.println("### 6 - EDITAR ESTOQUE                           ###");
        System.out.println("### 7 - LISTAR ESTOQUE                           ###");
        System.out.println("### VENDAS --------------------------------------###");
        System.out.println("### 8 - REALIZAR VENDA                           ###");
        System.out.println("### 9 - CANCELAR VENDA                           ###");
        System.out.println("### 10- LISTAR VENDAS                            ###");
        System.out.println("###                                              ###");
        System.out.println("### 0 - SAIR ------------------------------------###");
        System.out.println("### ############################################ ###");
        System.out.print("$ OPÇÃO: ");
    }
    
    
    
}
