package ua.goit.model.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class SkillsDao {
    private Integer skillId;
    private String language;
    private String skill;

    public SkillsDao(Integer skillId, String language, String skill) {
        this.skillId = skillId;
        this.language = language;
        this.skill = skill;
    }

    public SkillsDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "skill")
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "SkillsDao{" +
                "skillId=" + skillId +
                ", language='" + language + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
