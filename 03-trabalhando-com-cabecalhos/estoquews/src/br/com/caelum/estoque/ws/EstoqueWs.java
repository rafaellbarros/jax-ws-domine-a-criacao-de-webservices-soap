package br.com.caelum.estoque.ws;

import br.com.caelum.estoque.modelo.exception.AutorizacaoException;
import br.com.caelum.estoque.modelo.item.*;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;
import br.com.caelum.estoque.modelo.usuario.Usuario;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public class EstoqueWs {

    private ItemDao dao = new ItemDao();

    @WebMethod(operationName = "TodosOsItens")
    @WebResult(name = "itens")
    public ListaItens getItens(@WebParam(name="filtros") Filtros filtros) {
        System.out.println("Chamando getItens()");
        List<Filtro> lista = filtros.getLista();
        List<Item> itensResultado = dao.todosItens(lista);
        return new ListaItens(itensResultado);
    }

    @WebMethod(operationName = "CadastrarItem")
    @WebResult(name = "item")
    public Item cadastrarItem(@WebParam(name = "tokenUsuario", header = true) TokenUsuario  token,
                              @WebParam(name = "item") Item item) throws AutorizacaoException {

        System.out.println("cadastrando item " + item + ", Token: " + token);

        boolean valido = new TokenDao().ehValido(token);

        if (!valido) {
            throw new AutorizacaoException("Autorização falhou");
        }

        //novo
        new ItemValidador(item).validate();

        this.dao.cadastrar(item);

        return item;
    }



}
