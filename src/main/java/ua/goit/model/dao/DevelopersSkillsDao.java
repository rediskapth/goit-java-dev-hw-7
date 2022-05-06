package ua.goit.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "developers_skills")
public class DevelopersSkillsDao {
    private Integer developerId;
    private Integer skillId;

    public DevelopersSkillsDao(Integer developerId, Integer skillId) {
        this.developerId = developerId;
        this.skillId = skillId;
    }

    public DevelopersSkillsDao() {
    }

    @Id
    @Column(name = "developer_id")
    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    @Id
    @Column(name = "skill_id")
    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @Override
    public String toString() {
        return "DevelopersSkillsDao{" +
                "developerId=" + developerId +
                ", skillId=" + skillId +
                '}';
    }
}
