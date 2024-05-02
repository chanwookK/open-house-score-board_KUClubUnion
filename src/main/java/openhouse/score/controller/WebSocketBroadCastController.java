package openhouse.score.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openhouse.score.domain.club.Club;
import openhouse.score.repository.club.ClubRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketBroadCastController {

    @MessageMapping("/broad-cast")
    @SendTo("/topic/")
    public boolean broadCast() {

        log.info("broad-cast");

        return true;
    }
}
