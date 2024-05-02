package openhouse.score.service;

import jakarta.annotation.PostConstruct;
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

        clubRepository.save(new Club("우탐","123"));
        clubRepository.save(new Club("수탐","124"));
        clubRepository.save(new Club("동탐","125"));
        clubRepository.save(new Club("동연","126"));

    }

}
