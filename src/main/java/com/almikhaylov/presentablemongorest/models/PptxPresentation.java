package com.almikhaylov.presentablemongorest.models;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "presentations")
@NoArgsConstructor
public class PptxPresentation {

    @Id
    private String id;
    @Indexed
    private String username;
    private String nameOfPresentation;

    private List<PptxSlide> slides;

    public PptxPresentation(String username, String nameOfPresentation, List<PptxSlide> slides) {
        this.username = username;
        this.nameOfPresentation = nameOfPresentation;
        this.slides = slides;
    }
}
