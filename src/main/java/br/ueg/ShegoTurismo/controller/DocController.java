package br.ueg.ShegoTurismo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DocController {

    @RequestMapping("/docs")
    public String getDocumentationSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}