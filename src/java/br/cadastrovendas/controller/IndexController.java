/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cadastrovendas.controller;

import br.cadastrovendas.model.Usuario;
import br.cadastrovendas.service.ProdutoDAO;
import br.cadastrovendas.service.UsuarioDAO;
import br.cadastrovendas.service.VendaDAO;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author vmora2
 */
public class IndexController extends AbstractController {

    private ProdutoDAO produtoDAO;
    private VendaDAO vendaDAO;
    private UsuarioDAO usuarioDAO;

    public void setProdutoDAO(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void setVendaDAO(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        final String CADASTRAR_VENDA = "Cadastrar Venda";
        final String VISUALIZAR_VENDAS = "Visualizar Vendas";
        final String CADASTRAR_PRODUTO = "Cadastrar Produto";
        final String CADASTRAR_USUARIO = "Cadastrar Usuario";

        String nome = request.getParameter("pageName");

        if (nome == null) {
            ModelAndView modelAndView = new ModelAndView("index");
            return modelAndView;
        }

        ModelAndView modelAndView = null;
        if (CADASTRAR_VENDA.equals(nome)) {
            modelAndView = new ModelAndView("cadastroVendas", "produtos", produtoDAO.findProdutoEntities());
            List<Usuario> usuarios = usuarioDAO.findUsuarioEntities();
            Collections.sort(usuarios, new Comparator<Usuario>() {
                @Override
                public int compare(Usuario o1, Usuario o2) {
                    return o1.getNomeUsuario().compareTo(o2.getNomeUsuario());

                }
            });
            modelAndView.addObject("usuarios", usuarios);
        } else if (VISUALIZAR_VENDAS.equals(nome)) {
            modelAndView = new ModelAndView("visualizarVendas", "vendas", vendaDAO.findVendaEntities());
        } else if (CADASTRAR_PRODUTO.equals(nome)) {
            modelAndView = new ModelAndView("cadastroProdutos", "produtos", produtoDAO.findProdutoEntities());
        } else if (CADASTRAR_USUARIO.equals(nome)) {
            modelAndView = new ModelAndView("cadastroUsuarios", "usuarios", usuarioDAO.findUsuarioEntities());
        }
        return modelAndView;
    }
}