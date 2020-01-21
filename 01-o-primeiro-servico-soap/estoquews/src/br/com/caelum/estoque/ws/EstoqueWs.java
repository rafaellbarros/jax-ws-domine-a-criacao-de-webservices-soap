package br.com.caelum.estoque.ws;

import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class EstoqueWs {

    private ItemDao dao = new ItemDao();

    public List<Item> getItens() {
        System.out.println("Chamando getItens()");
        ArrayList<Item> lista = dao.todosItens();
        return lista;
    }



}
