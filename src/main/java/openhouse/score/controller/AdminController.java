package openhouse.score.controller;


import lombok.RequiredArgsConstructor;
import openhouse.score.dto.login.LoginInfoDto;
import openhouse.score.service.login.LoginService;
import openhouse.score.service.score.ScoreHandleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
    public String doLogin(@ModelAttribute LoginInfoDto loginInfo, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {

        //사용자가 입력한 id와 설정된 id가 같은지 검사
        boolean isVerifiedName = loginService.verifyName(loginInfo.getName());

        if(!isVerifiedName){
            return redirectLoginWithError("동아리명이 잘못 되었습니다.", redirectAttributes);
        }

        //사용자가 입력한 pw와 설정된 pw가 같은지 검사
        boolean isVerifiedClub = loginService.verifyPassword(loginInfo.getPassword());

        if(!isVerifiedClub){

            return redirectLoginWithError("비밀번호가 잘못 되었습니다.", redirectAttributes);

        }

        String encodedParam = URLEncoder.encode(loginInfo.getName(), "UTF-8");

        //로그인 성공 시 socre-board 화면으로 이동
        return "redirect:/admin/score-board/"+encodedParam+"/1";

    }

    public String redirectLoginWithError(String errorInfo, RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("isFail", true);
        redirectAttributes.addAttribute("errorMessage", errorInfo);

        return "redirect:/admin/login";
    }

    @GetMapping("/score-board/{clubName}/{clubId}")
    public String showScoreBoard(Model model, @PathVariable(value = "clubId") String clubId, @PathVariable("clubName") String clubName, @RequestParam(value = "status", defaultValue = "false") boolean status){

        if(status)
            model.addAttribute("clubId", clubId);

        model.addAttribute("clubs", scoreHandleService.getAllClub());
        model.addAttribute("clubName", clubName);

        return "/admin/scoreBoard";
    }


    @PostMapping("/score-board/success-game/{clubId}/{clubName}")
    public String successGame(@PathVariable Long clubId, @PathVariable("clubName") String visitClubName, RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("status", true);
        scoreHandleService.successGame(clubId, visitClubName);

        return "redirect:/admin/score-board/{clubName}/{clubId}";
    }

    @PostMapping("/score-board/fail-game/{clubId}/{clubName}")
    public String failGame(@PathVariable Long clubId, @PathVariable("clubName") String visitClubName, RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("status", true);
        scoreHandleService.failGame(clubId, visitClubName);


        return "redirect:/admin/score-board/{clubName}/{clubId}";
    }


}
