package openhouse.score.dto.club;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import openhouse.score.domain.club.Club;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubDto {

    private Long id;
    private String name;
    private String password;
    private Integer visitors = 0;
    private Integer webConnectNum = 0;
    private Integer score = 0;

    public static ClubDto fromClub(Club club) {

        if (club == null) return null;

        ClubDto dto = new ClubDto();

        dto.setId(club.getId());
        dto.setName(club.getName());
        dto.setPassword(club.getPassword());
        dto.setWebConnectNum(club.getWebConnectNum());
        dto.setScore(club.getScore());
        dto.setVisitors(club.getVisitors());

        return dto;
    }
}
