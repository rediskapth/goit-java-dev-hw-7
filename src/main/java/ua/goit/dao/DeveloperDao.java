package ua.goit.dao;

import org.hibernate.Session;
import ua.goit.model.Developer;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DeveloperDao extends AbstractDao<Developer> {
    private static DeveloperDao developerDao;

    public DeveloperDao() {
    }

    @Override
    public Optional<Developer> findById(int id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Developer d WHERE d.id=:id", Developer.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Developer> findByIds(Set<Integer> id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Developer d WHERE d.id IN :ids", Developer.class)
                    .setParameter("ids", id)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Developer> findAll() {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Developer", Developer.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static DeveloperDao getInstance() {
        if (developerDao == null) {
            developerDao = new DeveloperDao();
        }
        return developerDao;
    }
}
