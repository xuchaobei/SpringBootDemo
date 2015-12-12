package com.envisioncn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Greeting {

    private long id;
    private String content;

    public Greeting() {
	}
    
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

	public void setId(long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
}
