package ua.goit.dao;

import org.hibernate.Session;
import ua.goit.model.Company;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CompanyDao extends AbstractDao<Company> {
    private static CompanyDao companyDao;

    public CompanyDao() {
    }

    @Override
    public Optional<Company> findById(int id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Company c WHERE c.id=:id", Company.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Company> findByIds(Set<Integer> id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Company c WHERE c.id IN :ids", Company.class)
                    .setParameter("ids", id)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Company> findAll() {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Company", Company.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static CompanyDao getInstance() {
        if (companyDao == null) {
            companyDao = new CompanyDao();
        }
        return companyDao;
    }
}
