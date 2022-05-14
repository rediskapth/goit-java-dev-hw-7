package ua.goit.dao;

import org.hibernate.Session;
import ua.goit.model.Project;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProjectDao extends AbstractDao<Project> {
    private static ProjectDao projectDao;

    public ProjectDao() {
    }

    @Override
    public Optional<Project> findById(int id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Project p WHERE p.id=:id", Project.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Project> findByIds(Set<Integer> id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Project p WHERE p.id IN :ids", Project.class)
                    .setParameter("ids", id)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Project> findAll() {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Project", Project.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static ProjectDao getInstance() {
        if (projectDao == null) {
            projectDao = new ProjectDao();
        }
        return projectDao;
    }
}
