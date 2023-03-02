package com.almikhaylov.presentablemongorest.models;

import com.mongodb.BasicDBObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "slides")
@NoArgsConstructor
@AllArgsConstructor
public class PptxSlide {

    @Id
    private String id;
    @Indexed
    private String username;
    private int numberOfSlide;
    private String nameOfPresentation;
    private BasicDBObject jsonObject;

    public PptxSlide(String username, String jsonObject) {
        this.username = username;
        BasicDBObject object = BasicDBObject.parse(jsonObject);
        this.jsonObject = object;
    }

    public void setJsonObject(String string) {
        BasicDBObject object = BasicDBObject.parse(string);
        this.jsonObject = object;
    }
}
