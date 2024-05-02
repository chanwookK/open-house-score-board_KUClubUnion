package openhouse.score.controller;


import lombok.RequiredArgsConstructor;
import openhouse.score.dto.club.ClubDto;
import openhouse.score.dto.login.LoginInfoDto;
import openhouse.score.service.login.LoginService;
import openhouse.score.service.score.ScoreHandleService;
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

    /**
     *
     * @return: 로그인 폼을 return
     */
    @GetMapping("/login")
    public String showLoginForm(){

        return "admin/loginForm";
    }


    /**
     *
     * @param loginInfo: form data로 전송 받은 로그인 이름과 비밀번호를 가지는 DTO,
     * @param redirectAttributes: redirect 할 url로 보내는 model
     * @return: 로그인 실패 시 login 창으로 이동. 성공 시 score board 로 이동.
     */
    @PostMapping("/login")
    public String doLogin(@ModelAttribute LoginInfoDto loginInfo, RedirectAttributes redirectAttributes){

        ClubDto verifiedNameClub = loginService.verifyName(loginInfo.getName());

        if(verifiedNameClub == null){
            return redirectLoginWithError("동아리명이 잘못 되었습니다.", redirectAttributes);
        }

        ClubDto verifiedClub = loginService.verifyPassword(loginInfo.getPassword(), verifiedNameClub);

        if(verifiedClub == null){
            return redirectLoginWithError("비밀번호가 잘 못 되었습니다.", redirectAttributes);
        }

        redirectAttributes.addFlashAttribute("clubId", verifiedClub.getId());

        return "redirect:/admin/score-board";

    }

    public String redirectLoginWithError(String errorInfo, RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("isFail", true);
        redirectAttributes.addAttribute("errorMessage", errorInfo);

        return "redirect:/admin/login";
    }

    @GetMapping("/score-board")
    public String showScoreBoard(Model model){

        Long clubId = (Long)model.getAttribute("clubId");
        model.addAttribute("club", scoreHandleService.getClubById(clubId));

        return "/admin/scoreBoard";
    }

    @PostMapping("/score-board/score-up/{clubId}")
    public String scoreUp(@PathVariable Long clubId, RedirectAttributes redirectAttributes){

        scoreHandleService.scoreUp(clubId);
        redirectAttributes.addFlashAttribute("clubId", clubId);

        return "redirect:/admin/score-board";
    }

    @PostMapping("/score-board/score-down/{clubId}")
    public String scoreDown(@PathVariable Long clubId, RedirectAttributes redirectAttributes){

        scoreHandleService.scoreDown(clubId);
        redirectAttributes.addFlashAttribute("clubId", clubId);

        return "redirect:/admin/score-board";
    }


}
