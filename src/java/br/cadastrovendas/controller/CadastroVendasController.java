/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cadastrovendas.controller;

import br.cadastrovendas.model.Produto;
import br.cadastrovendas.model.Usuario;
import br.cadastrovendas.model.Venda;
import br.cadastrovendas.model.VendaProduto;
import br.cadastrovendas.service.ProdutoDAO;
import br.cadastrovendas.service.UsuarioDAO;
import br.cadastrovendas.service.VendaDAO;
import br.cadastrovendas.service.VendaProdutoDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author vmora2
 */
public class CadastroVendasController extends AbstractController {

    private UsuarioDAO usuarioDAO;
    private ProdutoDAO produtoDAO;
    private VendaDAO vendaDAO;
    private VendaProdutoDAO vendaProdutoDAO;

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void setProdutoDAO(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void setVendaDAO(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public void setVendaProdutoDAO(VendaProdutoDAO vendaProdutoDAO) {
        this.vendaProdutoDAO = vendaProdutoDAO;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Usuario usuario;

        String nome = request.getParameter("nome");
        String[] lista = request.getParameterValues("listProdutos");

        usuario = usuarioDAO.findUsuario(nome);
        if (usuario == null) {
            usuario = new Usuario(nome);
            usuario.setId_Usuario(usuarioDAO.create(usuario));
        }
        
        List<Produto> produtos = new ArrayList<Produto>();
        for (String produto : lista) {   
            produtos.add(produtoDAO.findProduto(produto.substring(0, produto.indexOf("-") -1)));
        }
        
        Venda venda = new Venda();
        venda.setId_Usuario(usuario.getId_Usuario());
        venda.setData_venda(new Date());
        for (Produto p : produtos) {
            venda.setValor_venda(venda.getValor_venda() + p.getValor_produto());
        }
        venda.setId_Venda(vendaDAO.create(venda));
        
        VendaProduto vendaProduto = new VendaProduto();
        vendaProduto.setId_venda(venda.getId_Venda());
        for (Produto p : produtos) {   
            vendaProduto.setId_produto(p.getId_produto());
            vendaProdutoDAO.create(vendaProduto);
        }

        ModelAndView modelAndView = new ModelAndView("visualizarVendas", "vendas", vendaDAO.findVendaEntities());

        return modelAndView;
    }
}
