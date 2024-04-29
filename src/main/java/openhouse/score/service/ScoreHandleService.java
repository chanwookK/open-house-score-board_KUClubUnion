package openhouse.score.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openhouse.score.domain.Club;
import openhouse.score.repository.club.ClubRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreHandleService {

    private final ClubRepository rep;

    public Club scoreUp(Long clubId){

        Club byId = rep.findById(clubId);
        byId.plus();

        log.info("club : {}  score : {}  visitor : {} -- by up", byId.getName(), byId.getScore(), byId.getVisitors());
        return byId;
    }


    public Club scoreDown(Long clubId){

        Club byId = rep.findById(clubId);
        byId.minus();

        log.info("club : {}  score : {}  visitor : {} -- by down", byId.getName(), byId.getScore(), byId.getVisitors());
        return byId;
    }

}
