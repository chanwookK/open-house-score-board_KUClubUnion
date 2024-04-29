package openhouse.score.repository.club;

import openhouse.score.domain.Club;

import java.util.List;

public interface ClubRepository {

    public Club findById(Long id);
    public List<Club> findAll();

    public Club findByName(String name);
    public Long save(Club club);

}
