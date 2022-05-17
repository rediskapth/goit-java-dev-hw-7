package ua.goit.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.DevelopersDao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DevelopersRepository implements Repository<DevelopersDao> {

    private final DatabaseManager manager;

    public DevelopersRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(DevelopersDao developersDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.persist(developersDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<DevelopersDao> findById(int id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM DevelopersDao dd WHERE dd.id=:id", DevelopersDao.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<DevelopersDao> findByIds(Set<Integer> id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM DevelopersDao dd WHERE dd.id IN :ids", DevelopersDao.class)
                    .setParameter("ids", id)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<DevelopersDao> findAll() {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM DevelopersDao", DevelopersDao.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void update(DevelopersDao developersDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.merge(developersDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void remove(DevelopersDao developersDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.remove(developersDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
