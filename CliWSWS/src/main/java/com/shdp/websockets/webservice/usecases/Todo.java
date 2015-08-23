package com.shdp.websockets.webservice.usecases;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class Todo {

    private String id;
    private String title;
    private boolean done;
    private Date createdOn = new Date();

    public Todo(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.title = dbObject.getString("title");
        this.done = dbObject.getBoolean("done");
        this.createdOn = dbObject.getDate("createdOn");
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
    public String getId()
    {
    	return id ;
    }
}
