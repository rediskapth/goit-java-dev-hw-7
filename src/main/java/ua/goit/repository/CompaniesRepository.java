package ua.goit.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.CompaniesDao;

import java.util.List;
import java.util.Optional;

public class CompaniesRepository implements Repository<CompaniesDao> {

    private final DatabaseManager manager;

    public CompaniesRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(CompaniesDao companiesDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.persist(companiesDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<CompaniesDao> findById(int id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM CompaniesDao cd WHERE cd.companyId=:companyId", CompaniesDao.class)
                    .setParameter("companyId", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CompaniesDao> findAll() {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM CompaniesDao", CompaniesDao.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void update(CompaniesDao companiesDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.merge(companiesDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void remove(CompaniesDao companiesDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM CompaniesProjectsDao cpd WHERE cpd.companyId=:companyId")
                    .setParameter("companyId", companiesDao.getCompanyId())
                    .executeUpdate();
            session.createQuery("DELETE FROM CompaniesDao cd WHERE cd.company_id=:companyId")
                    .setParameter("companyId", companiesDao.getCompanyId())
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
