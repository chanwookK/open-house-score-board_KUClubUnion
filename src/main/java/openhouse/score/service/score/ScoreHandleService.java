package openhouse.score.service.score;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openhouse.score.domain.club.Club;
import openhouse.score.dto.club.ClubDto;
import openhouse.score.repository.club.ClubRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreHandleService {

    private final ClubRepository rep;

    @Transactional
    @CacheEvict(value = {"myCache","myCache2"}, allEntries = true)
    public boolean successGame(Long clubId, String visitClubName){

        Club byId = rep.findById(clubId);
        byId.plus();

        Club byName = rep.findByName(visitClubName);
        byName.visitorUp();

        log.info("-admin name :{}: adjust(success game)- target club = {} , score = {}  admin club = {} , visitor = {}", visitClubName, byId.getName(), byId.getScore(), visitClubName, byName.getVisitors());
        return true;
    }


    @Transactional
    @CacheEvict(value = {"myCache","myCache2"}, allEntries = true)
    public boolean failGame(Long clubId, String visitClubName){


        Club byId = rep.findById(clubId);
       // byId.minus();


        Club byName = rep.findByName(visitClubName);
        byName.visitorUp();

        log.info("-admin name :{}: adjust(fail game)- target club = {} , score = {}  admin club = {} , visitor = {}", visitClubName, byId.getName(), byId.getScore(), visitClubName, byName.getVisitors());
        return true;
    }

    @Cacheable("myCache2")
    public List<ClubDto> getAllClub(){

        List<ClubDto> allClub = new ArrayList<>();

        rep.findAll().stream()
                .forEach(club -> allClub.add(ClubDto.fromClub(club)));


        return allClub;
    }

}
