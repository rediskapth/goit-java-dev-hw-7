package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.config.DatabaseManager;
import ua.goit.config.HibernateProvider;

public abstract class AbstractDao<T> implements Dao<T> {
    DatabaseManager manager = new HibernateProvider();

    @Override
    public void save(T entity) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void remove(T entity) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
