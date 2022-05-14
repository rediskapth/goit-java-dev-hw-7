package ua.goit.dao;

import org.hibernate.Session;
import ua.goit.model.Skill;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SkillDao extends AbstractDao<Skill> {
    private static SkillDao skillDao;

    public SkillDao() {
    }

    @Override
    public Optional<Skill> findById(int id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Skill s WHERE s.id=:id", Skill.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Skill> findByIds(Set<Integer> id) {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Skill s WHERE s.id IN :ids", Skill.class)
                    .setParameter("ids", id)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Skill> findAll() {
        try (Session session = manager.getSession()) {
            return session.createQuery("FROM Skill", Skill.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static SkillDao getInstance() {
        if (skillDao == null) {
            skillDao = new SkillDao();
        }
        return skillDao;
    }
}
