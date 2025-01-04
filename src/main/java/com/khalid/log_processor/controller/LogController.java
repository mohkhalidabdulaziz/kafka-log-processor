package com.khalid.log_processor.controller;

import com.khalid.log_processor.repo.LogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogController {
    private final LogRepository logRepository;

    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping("/")
    public String getLogs(Model model) {
        model.addAttribute("logs", logRepository.findAll());
        return "logs";
    }
}
