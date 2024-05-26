package openhouse.score.service.login;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openhouse.score.domain.club.Club;
import openhouse.score.dto.club.ClubDto;
import openhouse.score.repository.club.ClubRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    //repository
    private final ClubRepository clubRepository;

    private String password = "202111240";



    /**
     *
     * @param name : 로그인 하려는 id
     * @return : 이름이 일치 한다면 true를 반환. 찾지 못하면 null 반환
     * @comment : 이름과 비밀번호 입력 오류를 나누기 위해 메소드 분리
     */
    public boolean verifyName(String name){

        Club byName = clubRepository.findByName(name);

        if(byName != null){
            log.info("-login- name verified : input = {}", name);

            return true;
        }
        else {

            log.warn("-login- name error : input = {}", name);

            return false;
        }


    }

    /**
     * 
     * @param password : 로그인 하려는 pw
     * @return : 비밀번호가 일치하면 Club객체를 반환, 아니라면 null 반환
     */
    public boolean verifyPassword(String password) {

        if(password.equals(this.password)) {

            log.info("-login- success login");
            return true;

        }
        else {
            log.warn("-login- password error : desire={}, input={}", this.password, password);
            return false;
        }

    }


}
