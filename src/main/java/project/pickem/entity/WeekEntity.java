package project.pickem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WeekEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long weekNumber;

    public WeekEntity(Long weekNumber) {
        this.weekNumber = weekNumber;
    }

    public WeekEntity() {}

    public Long getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Long weekNumber) {
        this.weekNumber = weekNumber;
    }
}
