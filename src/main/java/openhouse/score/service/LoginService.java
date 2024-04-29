package openhouse.score.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openhouse.score.domain.Club;
import openhouse.score.repository.club.ClubRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final ClubRepository rep;


    /**
     *
     * @param name : 로그인 하려는 동아리 명
     * @param password : 로그인 하려는 동아리의 동아리방 호수
     * @return : 이름을 통해 찾은 Club객체를 반환. 찾지 못하면 null 반환
     * @comment : 이름과 비밀번호 입력 오류를 나누기 위해 메소드 분리
     */
    public Club verifyName(String name, String password){

        Club byName = rep.findByName(name);

        if(byName == null)
            return null;

        log.info("success verify name = {}", name);
        return byName;

    }

    /**
     * 
     * @param password : 로그인 하려는 동아리의 동아리방 호수
     * @param byName : verifyName 메소드에서 찾은 Club 객체
     * @return : 비밀번호가 일치하면 Club객체를 반환, 아니라면 null 반환
     */
    public Club verifyPassword(String password, Club byName) {

        if(byName.getPassword().equals(password)) {

            log.info("success login name = {}", byName.getName());
            return byName;

        }
        else {

            log.info("password error : desire={}, input={}", byName.getPassword(),password);
            return null;
        }
    }


}
