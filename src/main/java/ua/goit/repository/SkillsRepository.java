package ua.goit.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.SkillsDao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
            return session.createQuery("FROM SkillsDao sd WHERE sd.id=:id", SkillsDao.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<SkillsDao> findByIds(Set<Integer> id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM SkillsDao sd WHERE sd.id IN :ids", SkillsDao.class)
                    .setParameter("ids", id)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
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
            session.remove(skillsDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
