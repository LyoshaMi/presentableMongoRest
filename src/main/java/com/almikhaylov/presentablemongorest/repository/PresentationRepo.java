package com.almikhaylov.presentablemongorest.repository;

import com.almikhaylov.presentablemongorest.models.PptxPresentation;
import com.almikhaylov.presentablemongorest.models.PptxSlide;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresentationRepo extends MongoRepository<PptxPresentation, String> {
    List<PptxPresentation> findAllByUsername(String username);
    PptxPresentation findPptxPresentationByUsernameAndNameOfPresentation(String username, String nameOfPresentation);

    @Query(value = "{ 'username' : ?0}", fields = "{'nameOfPresentation' : 1}")
    List<String> findAllNamesOfPresentations(String username);

}
