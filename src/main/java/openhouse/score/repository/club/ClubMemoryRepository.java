package openhouse.score.repository.club;

import lombok.extern.slf4j.Slf4j;
import openhouse.score.domain.Club;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Repository
@Slf4j
public class ClubMemoryRepository implements ClubRepository {

    private final static Map<Long, Club> store = new ConcurrentHashMap<>();
    private Long sequence = 0L;
    @Override
    public Club findById(Long id) {

        return store.get(id);

    }

    @Override
    public List<Club> findAll() {
        ArrayList<Club> values = new ArrayList<>(store.values());
        Collections.sort(values);

        return values;
    }

    @Override
    public Club findByName(String name) {
        Club club = null;

        for (Map.Entry<Long, Club> entry : store.entrySet()) {
            club = entry.getValue();
            if (club.getName().equals(name)) {

                log.info("loginTry -> {} ; class = {}", club.getName(), this.getClass());

                return club;
            }
        }

        return null;
    }

    @Override
    public Long save(Club club) {

        store.put(++sequence, club);
        club.setId(sequence);

        log.info("save -> {}", club.getName());

        return club.getId();
    }
}
