package com.almikhaylov.presentablemongorest.repository;

import com.almikhaylov.presentablemongorest.models.PptxSlide;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends MongoRepository<PptxSlide, String> {
    List<PptxSlide> findAllByUsername(String username);
}
