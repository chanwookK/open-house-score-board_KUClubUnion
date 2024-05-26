package openhouse.score.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class MessageMemoryRepository {

    private String message = "팔로우도 부탁드려요~";

    public void messageSet(String message){
        this.message = message;

    }

    public String messageGet(){
        return message;
    }
}
