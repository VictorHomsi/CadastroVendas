/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cadastrovendas.controller;

import br.cadastrovendas.model.Usuario;
import br.cadastrovendas.service.UsuarioDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author vmora2
 */
public class CadastroUsuariosController extends AbstractController {

    private UsuarioDAO usuarioDAO;

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String nome = request.getParameter("nome");

        if (usuarioDAO.findUsuario(nome) != null) {
            ModelAndView modelAndView = new ModelAndView("index");
            return modelAndView;
        }
        Usuario produto = new Usuario(nome);
        usuarioDAO.create(produto);
        
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }
}