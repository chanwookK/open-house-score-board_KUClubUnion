package openhouse.score.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import openhouse.score.domain.club.Club;
import openhouse.score.dto.club.ClubDto;
import openhouse.score.repository.MessageMemoryRepository;
import openhouse.score.repository.club.ClubRepository;
import openhouse.score.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


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
    private final MessageMemoryRepository messageRep;

    @GetMapping("/")
    public String showMainView(Model model) throws InterruptedException {

        List<ClubDto> clubs = clientService.getSortedClubs();

        model.addAttribute("clubRank1", clubs.get(0));
        model.addAttribute("clubRank2", clubs.get(1));
        model.addAttribute("clubRank3", clubs.get(2));
        model.addAttribute("clubsUnder4", clubs.subList(3,clubs.size()));
        model.addAttribute("message",messageRep.messageGet());
        return "user/ranking";
    }



}
