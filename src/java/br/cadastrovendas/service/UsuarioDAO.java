/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cadastrovendas.service;

import br.cadastrovendas.model.Usuario;
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
public class UsuarioDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public UsuarioDAO() {
    }
    
    public int create(Usuario usuario) {
        return (Integer) sessionFactory.getCurrentSession().save(usuario);
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        sessionFactory.getCurrentSession().merge(usuario);
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        Usuario usuario = findUsuario(id);
        sessionFactory.getCurrentSession().delete(usuario);

    }

    public List<Usuario> findUsuarioEntities() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
        return criteria.list();
    }

    public Usuario findUsuario(Integer id) {
        return (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, id);
    }
    public Usuario findUsuario(String name) {       
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("nome_usuario", name));
        return (Usuario) criteria.uniqueResult();

    }
}
