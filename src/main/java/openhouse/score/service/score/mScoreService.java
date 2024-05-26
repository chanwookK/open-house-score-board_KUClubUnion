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
public class mScoreService {

    private final ClubRepository rep;

    @Transactional
    @CacheEvict(value = {"myCache","myCache2"}, allEntries = true)
    public boolean scoreUp(Long clubId){

        Club byId = rep.findById(clubId);
        byId.plus();

        log.info("-master adjust(score up)- target club = {} , score = {}", byId.getName(), byId.getScore());
        return true;
    }


    @Transactional
    @CacheEvict(value = {"myCache","myCache2"}, allEntries = true)
    public boolean scoreDown(Long clubId){


        Club byId = rep.findById(clubId);
        byId.minus();

        log.info("-master adjust(score down)- target club = {} , score = {}", byId.getName(), byId.getScore());
        return true;
    }

    @Transactional
    @CacheEvict(value = {"myCache","myCache2"}, allEntries = true)
    public boolean visitorUp(Long clubId){

        Club byId = rep.findById(clubId);
        byId.visitorUp();

        log.info("-master adjust(visitorUp)- target club = {} , visitor = {}", byId.getName(), byId.getVisitors());
        return true;
    }


    @Transactional
    @CacheEvict(value = {"myCache","myCache2"}, allEntries = true)
    public boolean visitorDown(Long clubId){


        Club byId = rep.findById(clubId);
        byId.visitorDown();

        log.info("-master adjust(score up)- target club = {} , visitor = {}", byId.getName(), byId.getVisitors());
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
