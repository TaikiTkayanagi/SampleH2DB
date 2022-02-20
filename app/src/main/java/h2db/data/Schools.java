package h2db.data;

import lombok.Getter;
import lombok.Setter;

public class Schools {
    @Setter
    @Getter
    private String primarySchool;
    @Setter
    @Getter
    private String juniorHighSchool;
    @Setter
    @Getter
    private String highSchool;

    @Override
    public String toString(){
        return "小学校: " + this.primarySchool + " " + "中学校: " + this.juniorHighSchool + " " + "高校: " + this.highSchool;
    }

}
