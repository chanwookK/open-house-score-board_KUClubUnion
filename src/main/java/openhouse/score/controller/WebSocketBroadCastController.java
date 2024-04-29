package openhouse.score.controller;


import lombok.RequiredArgsConstructor;
import openhouse.score.domain.Club;
import openhouse.score.repository.club.ClubRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebSocketBroadCastController {

    private final ClubRepository repos;

    @MessageMapping("/broad-cast")
    @SendTo("/provide")
    public List<Club> broadCast() {


        return repos.findAll();
    }
}
