package br.com.minhapadaria.dao;

import br.com.minhapadaria.exception.DAOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public abstract class DaoGenericImpl<T> {

    private Class classe;

    public DaoGenericImpl() {
        this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T save(T t) throws DAOException {
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            manager.persist(t);
            commitTransaction();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public T update(T t) throws DAOException {
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            manager.merge(t);
            commitTransaction();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public T remove(T t) throws DAOException {
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            t = manager.merge(t);
            manager.remove(t);
            commitTransaction();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public T merge(T t) throws DAOException {
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            t = manager.merge(t);
            commitTransaction();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public T getById(Long id) throws DAOException {
        T resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(classe);
            criteria.add(Restrictions.eq("id", id));
            resultado = (T) criteria.uniqueResult();
            commitTransaction();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public List<T> getAllOrderByAsc(String arg) throws DAOException {
        List<T> resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(classe);
            criteria.addOrder(Order.asc(arg));
            resultado = (List<T>) criteria.list();
            commitTransaction();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public List<T> getAllOrderByDesc(String arg) throws DAOException {
        List<T> resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(classe);
            criteria.addOrder(Order.desc(arg));
            resultado = (List<T>) criteria.list();
            commitTransaction();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public List<T> getAll() throws DAOException {
        List<T> resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(classe);
            criteria.addOrder(Order.desc("id"));
            resultado = (List<T>) criteria.list();
            commitTransaction();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public Criteria getCriteria() {
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        return ((Session) manager.getDelegate()).createCriteria(classe);
    }

    public Session getSession() {
        EntityManager manager = PersistenceUtil.getEntityManager();
        return ((Session) manager.getDelegate());
    }

    public T executeNativeQuerySingleResult(String sql) throws DAOException {
        T resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            Query query = manager.createNativeQuery(sql, classe);
            resultado = (T) query.getSingleResult();
            commitTransaction();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public List<T> executeNativeQueryMultiResult(String sql) throws DAOException {
        List<T> resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            Query query = manager.createNativeQuery(sql, classe);
            resultado = (List<T>) query.getResultList();
            commitTransaction();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public int count() throws DAOException {
        startTransaction();
        int count = ((Number) getCriteria().setProjection(Projections.rowCount()).uniqueResult()).intValue();
        commitTransaction();
        closeTransacation();
        return count;
    }

    public List<T> getFrom(int from, int max) throws DAOException {
        List<T> resultado = null;
        try {
            startTransaction();
            Criteria criteria = getCriteria();
            criteria.setFirstResult(from);
            criteria.setMaxResults(max);
            criteria.addOrder(Order.asc("id"));
            resultado = (List<T>) criteria.list();
            commitTransaction();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public List<T> getUltimos() throws DAOException {
        List<T> resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(classe);
            criteria.addOrder(Order.desc("dataCadastro"));
            criteria.setFirstResult(0).setMaxResults(10);
            resultado = (List<T>) criteria.list();
            commitTransaction();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }

    public List<T> getNull(String propriedade) throws DAOException {
        List<T> resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        startTransaction();
        try {
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(classe);
            criteria.add(Restrictions.isNull(propriedade));
            resultado = (List<T>) criteria.list();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DAOException(e);
        } finally {
            closeTransacation();
        }
    }
    
    
    private void startTransaction() {
        PersistenceUtil.getEntityManager().getTransaction().begin();
    }

    private void commitTransaction() {
        if (PersistenceUtil.getEntityManager().getTransaction().isActive()) {
            PersistenceUtil.getEntityManager().getTransaction().commit();
        }
    }

    private void rollbackTransaction() {
        PersistenceUtil.getEntityManager().getTransaction().rollback();
    }

    private void closeTransacation() {
        if (PersistenceUtil.getEntityManager().isOpen()) {
            PersistenceUtil.getEntityManager().close();
        }
    }

}
