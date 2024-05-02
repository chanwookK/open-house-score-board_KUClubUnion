package openhouse.score.service.score;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openhouse.score.domain.club.Club;
import openhouse.score.dto.club.ClubDto;
import openhouse.score.repository.club.ClubRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreHandleService {

    private final ClubRepository rep;

    @Transactional
    @CacheEvict(value = "myCache", allEntries = true)
    public boolean scoreUp(Long clubId){

        Club byId = rep.findById(clubId);
        byId.plus();


        log.info("club : {}  score : {}  visitor : {} -- by up", byId.getName(), byId.getScore(), byId.getVisitors());
        return true;
    }


    @Transactional
    @CacheEvict(value = "myCache", allEntries = true)
    public boolean scoreDown(Long clubId){

        Club byId = rep.findById(clubId);
        byId.minus();

        log.info("club : {}  score : {}  visitor : {} -- by down", byId.getName(), byId.getScore(), byId.getVisitors());
        return true;
    }

    public ClubDto getClubById(Long clubId){
        return ClubDto.fromClub(rep.findById(clubId));
    }

}
