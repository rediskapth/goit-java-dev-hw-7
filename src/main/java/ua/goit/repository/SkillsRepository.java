package ua.goit.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.SkillsDao;

import java.util.List;
import java.util.Optional;

public class SkillsRepository implements Repository<SkillsDao> {

    private final DatabaseManager manager;

    public SkillsRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(SkillsDao skillsDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.persist(skillsDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<SkillsDao> findById(int id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM SkillsDao sd WHERE sd.skillId=:skillId", SkillsDao.class)
                    .setParameter("skillId", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<SkillsDao> findAll() {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM SkillsDao", SkillsDao.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void update(SkillsDao skillsDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.merge(skillsDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public void remove(SkillsDao skillsDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM DevelopersSkillsDao dsd WHERE dsd.skillId=:skillId")
                    .setParameter("skillId", skillsDao.getSkillId())
                    .executeUpdate();
            session.createQuery("DELETE FROM SkillsDao sd WHERE sd.skillId=:skillId")
                    .setParameter("skillId", skillsDao.getSkillId())
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
