package org.innovonto.greenhouse.api.backend.client.icv;

import org.innovonto.greenhouse.api.backend.common.idea.Idea;
import org.innovonto.greenhouse.api.backend.common.idea.IdeaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/api/annotation")
public class AnnotationController {
    private final IdeaRepository ideaRepository;
    private final IdeaAnnotationResultRepository ideaAnnotationResultRepository;


    @Value("${innovonto.icv.backend.server:http://localhost:4000}")
    private String server = "http://localhost:4000";

    private final String confidence = "0.1";
    private final String endpoint = "/api/candidates?confidence=" + confidence + "&backend=all&text=";

    private RestTemplate restClient;

    public AnnotationController(IdeaRepository ideaRepository, IdeaAnnotationResultRepository ideaAnnotationResultRepository) {
        this.ideaRepository = ideaRepository;
        this.ideaAnnotationResultRepository = ideaAnnotationResultRepository;
        this.restClient = new RestTemplate();
    }


    @GetMapping(value = "/annotate", produces = MediaType.APPLICATION_JSON_VALUE)
    public String annotate(@RequestParam(name = "text") String text, @RequestParam(name = "timerValue", required = false) Long timerValue, HttpSession session) throws IOException {
        ResponseEntity<String> responseEntity = restClient.getForEntity(server + endpoint + text, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Idea> submitIdea(@RequestBody AnnotationSubmitDTO submitData, HttpSession session) {
        Idea savedIdea = ideaRepository.save(submitData.getIdea());
        ICVResult icvResult = submitData.getIcvResult();
        ideaAnnotationResultRepository.save(new IdeaAnnotation(savedIdea.getId(), icvResult.getText(), icvResult.getAnnotations()));
        return new ResponseEntity<>(savedIdea, HttpStatus.OK);
    }
}
