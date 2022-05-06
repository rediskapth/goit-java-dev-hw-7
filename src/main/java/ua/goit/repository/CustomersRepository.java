package ua.goit.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.CustomersDao;

import java.util.List;
import java.util.Optional;

public class CustomersRepository implements Repository<CustomersDao> {

    private final DatabaseManager manager;

    public CustomersRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(CustomersDao customersDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.persist(customersDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<CustomersDao> findById(int id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM CustomersDao cd WHERE cd.customerId=:customerId", CustomersDao.class)
                    .setParameter("customerId", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CustomersDao> findAll() {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM CustomersDao", CustomersDao.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void update(CustomersDao customersDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.merge(customersDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void remove(CustomersDao customersDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM CustomersProjectsDao cpd WHERE cpd.customerId=:customerId")
                    .setParameter("customerId", customersDao.getCustomerId())
                    .executeUpdate();
            session.createQuery("DELETE FROM CustomersDao cd WHERE cd.customerId=:customerId")
                    .setParameter("customerId", customersDao.getCustomerId())
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
