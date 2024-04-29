package openhouse.score.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import openhouse.score.domain.Club;
import openhouse.score.repository.club.ClubRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final ClubRepository repos;

    @GetMapping("/")
    public String mainView(Model model){

        model.addAttribute("clubs", repos.findAll());

        return "user/index";
    }

    @PostConstruct
    public void construct(){

        repos.save(new Club("우탐","123"));
        repos.save(new Club("수탐","124"));
        repos.save(new Club("동탐","125"));
        repos.save(new Club("동연","126"));
    }
}
