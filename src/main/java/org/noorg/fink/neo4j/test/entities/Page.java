package org.noorg.fink.neo4j.test.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.core.Direction;

@NodeEntity
public class Page {

	@RelatedTo(elementClass = Page.class, type = "IS_SUBPAGE")
	private Page parentPage;

	@RelatedTo(elementClass = Page.class, type = "IS_SUBPAGE", direction = Direction.INCOMING)
	private Set<Page> subPages = new HashSet<Page>();

	@Indexed
	@GraphProperty
	private String name;

	Page() {}
	
	public Page(String name) {
		this.name = name;
	}

	public Set<Page> getSubPages() {
		return subPages;
	}

	public void setSubPages(Set<Page> subPages) {
		this.subPages = subPages;
	}

	public void addPage(Page page) {
		subPages.add(page);
//		page.persist();
//		this.relateTo(, "IS_SUBPAGE");
	}

	public Page getParentPage() {
		return parentPage;
	}

	public void setParentPage(Page parentPage) {
		this.parentPage = parentPage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
