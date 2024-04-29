package openhouse.score.controller;


import lombok.RequiredArgsConstructor;
import openhouse.score.domain.Club;
import openhouse.score.repository.club.ClubRepository;
import openhouse.score.service.LoginService;
import openhouse.score.service.ScoreHandleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final LoginService loginService;
    private final ScoreHandleService scoreHandleService;
    private final ClubRepository repos;


    @GetMapping("/login")
    public String loginForm(){

        return "admin/loginForm";
    }


    @PostMapping("/login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, RedirectAttributes redirectAttributes){

        Club findClub = loginService.verifyName(name, password);

        if(findClub == null){
            redirectAttributes.addAttribute("status", true);
            redirectAttributes.addAttribute("info","동아리명이 잘못 되었습니다." );
            return "redirect:/admin/login";
        }
        else{
            findClub = loginService.verifyPassword(password, findClub);

            if(findClub == null){
                redirectAttributes.addAttribute("status", true);
                redirectAttributes.addAttribute("info","비밀번호가 잘못 되었습니다." );
                return "redirect:/admin/login";
            }
        }

        redirectAttributes.addAttribute("clubId", findClub.getId());
        redirectAttributes.addFlashAttribute("clubss", findClub);

        return "redirect:/admin/score-board/"+ findClub.getId();

    }

    @GetMapping("/score-board/{clubId}")
    public String scoreBoard(@PathVariable Long clubId, Model model){
        model.addAttribute("clubs", repos.findById(clubId));
        //model.addAttribute("clubs",(Club)model.getAttribute("clubss"));

        return "admin/scoreBord";
    }

    @PostMapping("/score-board/score-up/{clubId}")
    public String scoreUp(@PathVariable Long clubId, RedirectAttributes redirectAttributes){

        Club findClub = scoreHandleService.scoreUp(clubId);

        return "redirect:/admin/score-board/{clubId}";
    }

    @PostMapping("/score-board/score-down/{clubId}")
    public String scoreDown(@PathVariable Long clubId, RedirectAttributes redirectAttributes){

        Club findClub = scoreHandleService.scoreDown(clubId);

        return "redirect:/admin/score-board/{clubId}";
    }


}
