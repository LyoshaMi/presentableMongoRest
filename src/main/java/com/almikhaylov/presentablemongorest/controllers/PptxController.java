package controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.PptxSlide;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import repository.SlideRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class PptxController {

    private final SlideRepository slideRepository;

    @GetMapping("/health")
    public String healthCheck(){
        return "controller already alive";
    }

    @PostMapping("/slide")
    public PptxSlide saveSlide(@RequestBody JSONObject jsonObject){
        log.info("i`m here");
        PptxSlide pptxSlide = new PptxSlide("aleksey", jsonObject);
        return slideRepository.save(pptxSlide);
    }

    @GetMapping("/slide")
    public List<PptxSlide> getSlides(){
        return slideRepository.findAll();
    }
}
