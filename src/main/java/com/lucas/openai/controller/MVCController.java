package com.lucas.openai.controller;

import com.lucas.openai.configuration.ControllerConfig;
import com.lucas.openai.configuration.OpenAIConfig;
import com.lucas.openai.service.ChatCompletionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("${controller.path}")
public class MVCController {

    private final ChatCompletionService service;
    private final String model;
    private final String formEndpointPath;

    public MVCController(ChatCompletionService service,
                         OpenAIConfig openAIConfig,
                         ControllerConfig controllerConfig) {
        this.service = service;
        this.model = openAIConfig.getModel();
        this.formEndpointPath = controllerConfig.getPath().concat(controllerConfig.getFormPath());
    }

    @GetMapping("${controller.form-path}")
    public String showForm(@RequestParam(defaultValue = "") String query, Model model) {
        model.addAttribute("modelPlaceHolder", this.model);
        model.addAttribute("answerPlaceHolder", !"".equals(query) ? service.getGeneratedMessage(query) : "");
        model.addAttribute("url", this.formEndpointPath);
        return "form";
    }
}
