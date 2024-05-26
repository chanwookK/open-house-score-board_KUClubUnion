package openhouse.score.repository.club;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openhouse.score.domain.club.Club;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class ClubDataBaseRepository implements ClubRepository {

    private final EntityManager entityManager;

    @Override
    public Club findById(Long id) {

        try{

            return entityManager.find(Club.class, id);

        }catch (NoResultException e){

            return null;
        }

    }

    @Override
    public List<Club> findAll() {
        return entityManager.createQuery("SELECT c FROM Club c", Club.class).getResultList();
    }

    @Override
    public Club findByName(String name) {

        Club club;

        try {
            club = entityManager.createQuery("SELECT c FROM Club c WHERE c.name=:name", Club.class).setParameter("name", name).getSingleResult();

        }catch (NoResultException e){
            return null;
        }
        return club;
    }

    @Override
    public Long save(Club club) {

        entityManager.persist(club);

        return club.getId();
    }
}
