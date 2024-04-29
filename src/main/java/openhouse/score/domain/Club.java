package openhouse.score.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Club implements Comparable<Club>{

    private Long id;
    private String name;
    private String password;
    private Integer visitors = 0;
    private Integer webConnectNum = 0;
    private Integer score = 0;

    public void plus(){
        this.score += 2;
        this.visitors++;
    }

    public void minus(){
        if(this.score == 0){
            ++this.visitors;
            return;
        }
        --this.score;
        ++this.visitors;
    }

    public Club(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public int compareTo(Club o) {
        if(o.getScore() > this.getScore())
            return 1;
        else if (o.getScore() < this.getScore())
            return -1;
        else
            return 0;
    }
}
