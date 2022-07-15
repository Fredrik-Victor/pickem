package project.pickem.dto;

public class Week {

    private Long weekNumber;

    public Week(Long weekNumber) {
        this.weekNumber = weekNumber;
    }
    public Week(){}

    public Long getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Long weekNumber) {
        this.weekNumber = weekNumber;
    }
}
