package com;

import java.io.Serializable;

/**
 * Created by szm on 2018/1/20.
 */
public class BaseModel implements Serializable {
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
