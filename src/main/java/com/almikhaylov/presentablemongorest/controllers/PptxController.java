package com.almikhaylov.presentablemongorest.controllers;

import com.almikhaylov.presentablemongorest.models.PptxSlide;
import com.almikhaylov.presentablemongorest.models.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.almikhaylov.presentablemongorest.models.PptxPresentation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.almikhaylov.presentablemongorest.repository.PresentationRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class PptxController {

    private final PresentationRepo presentationRepo;
    private final MongoTemplate mongoTemplate;

    @GetMapping("/health")
    public String healthCheck(){
        return "controller already alive";
    }


    @PostMapping("/createpresentation")
    public ResponseEntity<PptxPresentation> createPresentation(@RequestBody String data,
                                                               @AuthenticationPrincipal User user){
        PptxSlide slide = new PptxSlide(0, data);
        List<PptxSlide> slideList = new ArrayList<>();
        slideList.add(slide);
        PptxPresentation presentation =
                new PptxPresentation(user.getUsername(), "FIRST PRESENTATION", slideList);
        presentationRepo.save(presentation);
        return new ResponseEntity<>(presentation, HttpStatus.CREATED);
    }

    @PostMapping("/addSlideToPresentation/{presentation}/{index}")
    public ResponseEntity<PptxPresentation> addSlide(@RequestBody String data,
                                                     @PathVariable int index,
                                                     @AuthenticationPrincipal User user,
                                                     @PathVariable String nameOfPresentation){
        PptxSlide slide = new PptxSlide(index, data);
        String username = user.getUsername();
        Query query = new Query();
        query.addCriteria(Criteria.where("nameOfPresentation").is(nameOfPresentation));
        PptxPresentation presentation = presentationRepo.findPptxPresentationByUsernameAndNameOfPresentation(username, nameOfPresentation);
        List<PptxSlide> slides = presentation.getSlides();
        slides.add(slide);
        Update update = new Update();
        update.set("slides", slides);
        return new ResponseEntity<>(mongoTemplate.findAndModify(query, update, PptxPresentation.class), HttpStatus.OK);
    }

    @GetMapping("/slide")
    public List<PptxPresentation> getSlides(){
        List<PptxPresentation> allData = presentationRepo.findAll();
        return allData;
    }

    @GetMapping("/get-all-names/{username}")
    public List<String> getSlides(@PathVariable String username){
        List<String> names = presentationRepo.findAllNamesOfPresentations(username);
        return names;
    }
    @GetMapping("/{presentation}/first-slides/{username}")
    public List<DBObject> getAllPresentationsByUsername(@PathVariable String username,
                                                        @PathVariable String nameOfPresentation){
        Query query = new Query();
        query.addCriteria(Criteria.where("nameOfPresentation").is(nameOfPresentation));
        List<PptxPresentation> presentations = presentationRepo.findAllByUsername(username);
        List<String> slides = presentations.stream()
                .map(elem -> elem.getSlides().get(0))
                .map(el -> el.getJsonObject().toString())
                .map(el -> new JSONObject(el).getJSONArray("scenes").getJSONObject(0).toString())
                .collect(Collectors.toList());
        List<DBObject> dbObjects = new ArrayList<>();
        for (String jsonObject:
             slides) {
            Object o = BasicDBObject.parse(jsonObject);
            DBObject object = (DBObject) o;
            dbObjects.add(object);
        }
        return dbObjects;
    }

    @GetMapping("/all-slides/{presentation}")
    public List<DBObject> getAllSlidesOfPresentation(@PathVariable String presentation){
        PptxPresentation presentationPptx = presentationRepo.findPptxPresentationByUsernameAndNameOfPresentation("nikita",presentation);
        List<String> slides = presentationPptx.getSlides().stream()
                .map(el -> el.getJsonObject().toString())
                .map(el -> new JSONObject(el).toString())
                .collect(Collectors.toList());
        List<DBObject> dbObjects = new ArrayList<>();
        for (String jsonObject:
                slides) {
            Object o = BasicDBObject.parse(jsonObject);
            DBObject object = (DBObject) o;
            dbObjects.add(object);
        }
        return dbObjects;
    }
}
