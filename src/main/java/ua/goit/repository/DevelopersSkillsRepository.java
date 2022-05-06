package ua.goit.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.DevelopersSkillsDao;

import java.util.List;
import java.util.Optional;

public class DevelopersSkillsRepository {

    private final DatabaseManager manager;

    public DevelopersSkillsRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    public void save(DevelopersSkillsDao developersSkillsDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.persist(developersSkillsDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Optional<List<DevelopersSkillsDao>> findByDeveloperId(int id) {
        try (Session session = manager.getSession()) {
            return Optional.of(session.createQuery("FROM DevelopersSkillsDao dsd WHERE dsd.developerId=:developerId", DevelopersSkillsDao.class)
                    .setParameter("developerId", id)
                    .stream()
                    .toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<List<DevelopersSkillsDao>> findBySkillId(int id) {
        try (Session session = manager.getSession()) {
            return Optional.of(session.createQuery("FROM DevelopersSkillsDao dsd WHERE dsd.skillId=:skillId", DevelopersSkillsDao.class)
                    .setParameter("skillId", id)
                    .stream()
                    .toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(DevelopersSkillsDao developersSkillsDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.merge(developersSkillsDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void remove(DevelopersSkillsDao developersSkillsDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.remove(developersSkillsDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
