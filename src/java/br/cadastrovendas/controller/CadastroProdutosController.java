/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cadastrovendas.controller;

import br.cadastrovendas.model.Produto;
import br.cadastrovendas.service.ProdutoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author vmora2
 */
public class CadastroProdutosController extends AbstractController {

    private ProdutoDAO produtoDAO;

    public void setProdutoDAO(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String nome = request.getParameter("desc");
        String valor = request.getParameter("valor");

        if (produtoDAO.findProduto(nome) != null) {
            ModelAndView modelAndView = new ModelAndView("index");
            return modelAndView;
        }
        Produto produto = new Produto(nome);
        produto.setValor_produto(Double.parseDouble(valor));
        produtoDAO.create(produto);

        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }
}