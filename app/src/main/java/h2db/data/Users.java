package h2db.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Users {
    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private String fullName;

    @Setter
    @Getter
    private List<Schools> schools;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("id: " + this.id + " " + "fullName: " + this.fullName);

        for(Schools school : this.schools){
            sb.append(school.toString());
        }

        return sb.toString();
    }
}
