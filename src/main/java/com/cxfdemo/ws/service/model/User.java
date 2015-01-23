
package com.cxfdemo.ws.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class User implements Serializable{

	private static final long serialVersionUID = 5841287096478855200L;
	private String id;
	private String name;
	private int sex;
    private List<String> collections = new ArrayList<String>();
    private Map<String, String> map = new HashMap<String, String>();

    public User(String id, String name, int sex, List<String> collections,
			Map<String, String> map) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.collections = collections;
		this.map = map;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public User(String id, String name, int sex, List<String> collections) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.collections = collections;
	}

	public List<String> getCollections() {
		return collections;
	}

	public void setCollections(List<String> collections) {
		this.collections = collections;
	}

	public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int value) {
        this.sex = value;
    }

	public User(String id, String name, int sex) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
	}
	
	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", collections=" + collections + ", map=" + map + "]";
	}
}