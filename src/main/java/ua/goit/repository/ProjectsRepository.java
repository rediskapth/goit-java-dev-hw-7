package ua.goit.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.ProjectsDao;

import java.util.List;
import java.util.Optional;

public class ProjectsRepository implements Repository<ProjectsDao> {

    private final DatabaseManager manager;

    public ProjectsRepository(DatabaseManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(ProjectsDao projectsDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.persist(projectsDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<ProjectsDao> findById(int id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM ProjectsDao pd WHERE pd.projectId=:projectId", ProjectsDao.class)
                    .setParameter("projectId", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ProjectsDao> findAll() {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM ProjectsDao", ProjectsDao.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void update(ProjectsDao projectsDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.merge(projectsDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void remove(ProjectsDao projectsDao) {
        Transaction transaction = null;
        try (Session session = manager.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM CompaniesProjectsDao cpd WHERE cpd.projectId=:projectId")
                    .setParameter("projectId", projectsDao.getProjectId())
                    .executeUpdate();
            session.createQuery("DELETE FROM CustomersProjectsDao cpd WHERE cpd.projectId=:projectId")
                    .setParameter("projectId", projectsDao.getProjectId())
                    .executeUpdate();
            session.createQuery("DELETE FROM DevelopersProjectsDao dpd WHERE dpd.projectId=:projectId")
                    .setParameter("projectId", projectsDao.getProjectId())
                    .executeUpdate();
            session.createQuery("DELETE FROM ProjectsDao p WHERE p.projectId=:projectId")
                    .setParameter("projectId", projectsDao.getProjectId())
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
