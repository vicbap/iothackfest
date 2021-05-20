package com.getronics.quarkus.api.sensors.model;

import javax.validation.constraints.NotNull;

public class SystemResponse {

	private String id;
	
	/**
	 * Get id
	 * 
	 * @return id
	 */
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
