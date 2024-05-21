package openhouse.score.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import openhouse.score.domain.club.Club;
import openhouse.score.repository.club.ClubRepository;
import openhouse.score.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * Client 들에게 보이는 Main View를 위한 컨트롤러
 *  URL Mapping : "/"
 *  
 */

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
