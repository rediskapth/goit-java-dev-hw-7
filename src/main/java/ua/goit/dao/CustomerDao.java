package ua.goit.dao;

import org.hibernate.Session;
import ua.goit.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CustomerDao extends AbstractDao<Customer> {
    private static CustomerDao customerDao;

    public CustomerDao() {
    }

    @Override
    public Optional<Customer> findById(int id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Customer c WHERE c.id=:id", Customer.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> findByIds(Set<Integer> id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Customer c WHERE c.id IN :ids", Customer.class)
                    .setParameter("ids", id)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Customer> findAll() {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Customer", Customer.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static CustomerDao getInstance() {
        if (customerDao == null) {
            customerDao = new CustomerDao();
        }
        return customerDao;
    }
}
