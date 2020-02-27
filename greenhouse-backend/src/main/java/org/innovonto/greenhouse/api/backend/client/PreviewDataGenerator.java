package org.innovonto.greenhouse.api.backend.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PreviewDataGenerator {

    @GetMapping("/article")
    public ModelAndView displayArticle(Map<String, Object> model) {
        model.put("articles", "wohoo!");
        return new ModelAndView("app", model);
    }
}
