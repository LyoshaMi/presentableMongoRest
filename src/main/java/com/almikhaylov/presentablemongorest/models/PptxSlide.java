package com.almikhaylov.presentablemongorest.models;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class PptxSlide {
    private int indexOfSlide;
    private DBObject jsonObject;

    public PptxSlide(int indexOfSlide, String jsonObject) {
        Object o = BasicDBObject.parse(jsonObject);
        DBObject object = (DBObject) o;
        this.indexOfSlide = indexOfSlide;
        this.jsonObject = object;
    }
}
