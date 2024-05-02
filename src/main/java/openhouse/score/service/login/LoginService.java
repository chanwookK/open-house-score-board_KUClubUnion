package openhouse.score.service.login;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openhouse.score.dto.club.ClubDto;
import openhouse.score.repository.club.ClubRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    //repository
    private final ClubRepository clubRepository;



    /**
     *
     * @param name : 로그인 하려는 동아리 명
     * @return : 이름을 통해 찾은 Club객체를 반환. 찾지 못하면 null 반환
     * @comment : 이름과 비밀번호 입력 오류를 나누기 위해 메소드 분리
     */
    public ClubDto verifyName(String name){

        ClubDto clubSearchedByName = ClubDto.fromClub(clubRepository.findByName(name));

        if(clubSearchedByName == null) {

            log.info("failed verify name = {}", name);

            return null;
        }

        log.info("success verify name = {}", name);
        return clubSearchedByName;

    }

    /**
     * 
     * @param password : 로그인 하려는 동아리의 동아리방 호수
     * @param byName : verifyName 메소드에서 찾은 Club 객체
     * @return : 비밀번호가 일치하면 Club객체를 반환, 아니라면 null 반환
     */
    public ClubDto verifyPassword(String password, ClubDto byName) {

        if(byName.getPassword().equals(password)) {

            log.info("success login name = {}", byName.getName());
            return byName;

        }
        else {

            log.info("password error : desire={}, input={}", byName.getPassword(), password);
            return null;
        }
    }


}
