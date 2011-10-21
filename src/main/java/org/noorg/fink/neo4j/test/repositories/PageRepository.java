package org.noorg.fink.neo4j.test.repositories;

import java.util.Set;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.noorg.fink.neo4j.test.entities.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableSet;

@Repository
public class PageRepository {
	
	@Autowired private PageRepositoryInt repository;
	
	@Autowired private Neo4jTemplate template;

	public void createSomePages() {
		Page p = new Page("root");
		p.addPage(new Page("sub1"));
		p.addPage(new Page("sub2"));
		repository.save(p);
	}

	public void save(Page p) {
		repository.save(p);
	}

	public long countPages() {
		return repository.count();
	}
	
	public Set<Page> findAll() {
		return ImmutableSet.copyOf(repository.findAll());
	}

	public Page findPageByTitle(String title) {
		return repository.findByPropertyValue("title", title);
	}

	public Page findPageByTitleManually(String title) {
		Index<Node> index = template.getIndex(Page.class, "index_name");
		IndexHits<Node> hits = index.get("title", title);
		if (hits.hasNext()) {
			return template.createEntityFromState(hits.next(), Page.class);
		}
		return null;
	}

}
