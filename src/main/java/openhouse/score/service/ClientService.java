package openhouse.score.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openhouse.score.domain.club.Club;
import openhouse.score.dto.club.ClubDto;
import openhouse.score.repository.club.ClubRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final ClubRepository clubRepository;

    @Cacheable("myCache")
    public List<ClubDto> getSortedClubs() {

        log.info("getSortedClubs");

        List<Club> clubs = clubRepository.findAll();
        Collections.sort(clubs);

        List<ClubDto> clubsDto = new ArrayList<>();
        clubs.forEach(club -> clubsDto.add(ClubDto.fromClub(club)));

        return clubsDto;
    }


    @PostConstruct
    public void construct(){

        clubRepository.save(new Club("우주탐구회","307"));
        clubRepository.save(new Club("유스호스텔","308"));
        clubRepository.save(new Club("HAM","311"));
        clubRepository.save(new Club("EDGE","402-1"));
        clubRepository.save(new Club("눈꽃","402-2"));
        clubRepository.save(new Club("MUSE","403-1"));
        clubRepository.save(new Club("건국호우회","405"));
        clubRepository.save(new Club("진상회","601"));
        clubRepository.save(new Club("건국검사회","102"));
        clubRepository.save(new Club("공간미","106"));
        clubRepository.save(new Club("태연","107"));
        clubRepository.save(new Club("IMOK","208"));
        clubRepository.save(new Club("청백","215"));
        clubRepository.save(new Club("예술평론회","216"));
        clubRepository.save(new Club("IF","222"));
        clubRepository.save(new Club("서울지역대학인권연합동아리","223"));
        clubRepository.save(new Club("산악부","224"));
        clubRepository.save(new Club("다정회","226"));
        clubRepository.save(new Club("KU:GG","B106"));
        clubRepository.save(new Club("건대극장","B111"));
        clubRepository.save(new Club("KUPHIL","B117"));
        clubRepository.save(new Club("AQUI","B124"));
        clubRepository.save(new Club("TAK\'KU","B133"));

    }

}
