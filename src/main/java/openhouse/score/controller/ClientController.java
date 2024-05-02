package openhouse.score.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import openhouse.score.domain.club.Club;
import openhouse.score.repository.club.ClubRepository;
import openhouse.score.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/")
    public String showMainView(Model model){

        model.addAttribute("clubs", clientService.getSortedClubs());

        return "user/index";
    }



}
