/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cadastrovendas.controller;

import br.cadastrovendas.model.Venda;
import br.cadastrovendas.service.VendaDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author vmora2
 */
public class VisualizarVendasController extends AbstractController {

    
    private VendaDAO vendaDAO;

    public void setVendaDAO(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }
 
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        Venda venda = vendaDAO.findVenda(1);
        
        ModelAndView modelAndView = new ModelAndView("visualizarVendas", "venda", venda);
 
        return modelAndView;
    }
}
