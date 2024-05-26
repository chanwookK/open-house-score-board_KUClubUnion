package openhouse.score.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openhouse.score.repository.MessageMemoryRepository;
import openhouse.score.service.score.ScoreHandleService;
import openhouse.score.service.score.mScoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/master")
public class MasterController {

    private final mScoreService sv;
    private final MessageMemoryRepository messageRep;


    @GetMapping("/score-board")
    public String mScoreBoard(Model model){

        model.addAttribute("clubs", sv.getAllClub());

        return "/master/mScoreBoard";

    }

    @PostMapping("/score-board/score-up/{clubId}")
    public String scoreUp(@PathVariable Long clubId){


        sv.scoreUp(clubId);

        return "redirect:/master/score-board";
    }

    @PostMapping("/score-board/score-down/{clubId}")
    public String scoreDown(@PathVariable Long clubId){

        sv.scoreDown(clubId);

        return "redirect:/master/score-board";
    }
    @PostMapping("/score-board/visitor-up/{clubId}")
    public String visitorUp(@PathVariable Long clubId){


        sv.visitorUp(clubId);

        return "redirect:/master/score-board";
    }

    @PostMapping("/score-board/visitor-down/{clubId}")
    public String visitorDown(@PathVariable Long clubId){

        sv.visitorDown(clubId);

        return "redirect:/master/score-board";
    }

    @PostMapping("/message-broad-cast")
    public String messageSet(@RequestParam("message") String message){

        messageRep.messageSet(message);

        return "redirect:/master/score-board";
    }
}
