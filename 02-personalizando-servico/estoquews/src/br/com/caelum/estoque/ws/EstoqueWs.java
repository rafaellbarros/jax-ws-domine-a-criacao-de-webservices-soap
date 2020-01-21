package br.com.caelum.estoque.ws;

import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class EstoqueWs {

    private ItemDao dao = new ItemDao();

    @WebMethod(operationName = "todosOsItens")
    @WebResult(name = "itens")
    public ListaItens getItens() {
        System.out.println("Chamando getItens()");
        ArrayList<Item> lista = dao.todosItens();
        return new ListaItens(lista);
    }



}
