package org.noorg.fink.neo4j.test.entities;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.Indexed.Level;

public abstract class BaseItem {

	@GraphId
	Long id;

	@Indexed(level=Level.INSTANCE)
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
