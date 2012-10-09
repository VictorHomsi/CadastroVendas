/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cadastrovendas.service;

import br.cadastrovendas.model.Produto;
import br.cadastrovendas.model.VendaProduto;
import br.cadastrovendas.service.exceptions.IllegalOrphanException;
import br.cadastrovendas.service.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Victor
 */
@Repository
@Transactional
public class VendaProdutoDAO {

    private SessionFactory sessionFactory;
    private ProdutoDAO produtoDAO;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setProdutoDAO(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public int create(VendaProduto vendaProduto) {
        return (Integer) sessionFactory.getCurrentSession().save(vendaProduto);
    }

    public void edit(VendaProduto vendaProduto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        sessionFactory.getCurrentSession().merge(vendaProduto);
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        VendaProduto vendaProduto = findVendaProduto(id);
        sessionFactory.getCurrentSession().delete(vendaProduto);

    }

    public List<VendaProduto> findVendaProdutoEntities() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VendaProduto.class);
        return criteria.list();
    }

    public VendaProduto findVendaProduto(Integer id) {
        return (VendaProduto) sessionFactory.getCurrentSession().get(VendaProduto.class, id);
    }

    public List<Produto> findProdutos(Integer idVenda) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VendaProduto.class);
        criteria.add(Restrictions.eq("id_venda", idVenda));
        List<VendaProduto> vendaProdutos = (List<VendaProduto>) criteria.list();
        List<Produto> produtos = new ArrayList<Produto>();
        for (VendaProduto vendaProduto : vendaProdutos) {
            produtos.add(produtoDAO.findProduto(vendaProduto.getId_produto()));
        }
        return produtos;
    }
}
