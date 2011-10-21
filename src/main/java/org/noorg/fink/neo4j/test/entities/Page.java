package org.noorg.fink.neo4j.test.entities;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Page extends BaseItem {

	@RelatedTo(elementClass = Page.class, type = "IS_SUBPAGE")
	private Page parentPage;

	@RelatedTo(elementClass = Page.class, type = "IS_SUBPAGE", direction = Direction.INCOMING)
	private Set<Page> subPages = new HashSet<Page>();

	Page() {
	}

	public Page(String title) {
		setTitle(title);
	}

	public Set<Page> getSubPages() {
		return subPages;
	}

	public void setSubPages(Set<Page> subPages) {
		this.subPages = subPages;
	}

	public void addPage(Page page) {
		subPages.add(page);
	}

	public Page getParentPage() {
		return parentPage;
	}

	public void setParentPage(Page parentPage) {
		this.parentPage = parentPage;
	}

}
