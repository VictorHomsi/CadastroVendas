/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cadastrovendas.service;

import br.cadastrovendas.model.Produto;
import br.cadastrovendas.service.exceptions.IllegalOrphanException;
import br.cadastrovendas.service.exceptions.NonexistentEntityException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vmora2
 */
@Repository
@Transactional
public class ProdutoDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public ProdutoDAO() {
    }
    
    public int create(Produto usuario) {
        return (Integer) sessionFactory.getCurrentSession().save(usuario);
    }

    public void edit(Produto usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        sessionFactory.getCurrentSession().merge(usuario);
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        Produto usuario = findProduto(id);
        sessionFactory.getCurrentSession().delete(usuario);

    }

    public List<Produto> findProdutoEntities() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Produto.class);
        return criteria.list();
    }

    public Produto findProduto(Integer id) {
        return (Produto) sessionFactory.getCurrentSession().get(Produto.class, id);
    }
    
    public Produto findProduto(String produto) {       
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Produto.class);
        criteria.add(Restrictions.eq("desc_produto", produto));
        return (Produto) criteria.uniqueResult();
    }
}
