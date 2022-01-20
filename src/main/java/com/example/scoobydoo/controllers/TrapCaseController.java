package com.example.scoobydoo.controllers;

import com.example.scoobydoo.entities.TrapCase;
import com.example.scoobydoo.services.TrapCaseService;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/trap")
public class TrapCaseController {

    private int page_counter = 0;

    @Autowired
    private TrapCaseService trapCaseService;

    @GetMapping()
    public String getAllTraps(Model model) {
        model.addAttribute("object", this);
        model.addAttribute("page_counter", page_counter);
        return "trap_list";
    }

    public List<TrapCase> getPage(int x) {
        return trapCaseService.getAllTraps(x, 50);
    }

    public void increment() {
        page_counter++;
    }
}
