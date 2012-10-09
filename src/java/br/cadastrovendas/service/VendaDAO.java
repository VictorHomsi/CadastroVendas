/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cadastrovendas.service;

import br.cadastrovendas.model.Venda;
import br.cadastrovendas.service.exceptions.IllegalOrphanException;
import br.cadastrovendas.service.exceptions.NonexistentEntityException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vmora2
 */
@Repository
@Transactional
public class VendaDAO {

    public VendaDAO() {
    }
    private SessionFactory sessionFactory;
    private VendaProdutoDAO vendaProdutoDAO;
    private UsuarioDAO usuarioDAO;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setVendaProdutoDAO(VendaProdutoDAO vendaProdutoDAO) {
        this.vendaProdutoDAO = vendaProdutoDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public int create(Venda venda) {
        return (Integer) sessionFactory.getCurrentSession().save(venda);
    }

    public void edit(Venda venda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        sessionFactory.getCurrentSession().merge(venda);
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        Venda venda = findVenda(id);
        sessionFactory.getCurrentSession().delete(venda);

    }

    public List<Venda> findVendaEntities() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Venda.class);
        List<Venda> vendas = criteria.list();
        for (Venda venda : vendas) {
            venda.setProdutos(vendaProdutoDAO.findProdutos(venda.getId_Venda()));
            venda.setNome_usuario(usuarioDAO.findUsuario(venda.getId_Usuario()).getNomeUsuario());
        }
        return vendas;
    }

    public Venda findVenda(Integer id) {
        return (Venda) sessionFactory.getCurrentSession().get(Venda.class, id);
    }
}
