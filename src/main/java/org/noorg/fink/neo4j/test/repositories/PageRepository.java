package org.noorg.fink.neo4j.test.repositories;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.noorg.fink.neo4j.test.entities.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.AbstractGraphRepository;
import org.springframework.data.neo4j.repository.DirectGraphRepositoryFactory;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.GraphDatabaseContext;
import org.springframework.stereotype.Repository;

@Repository
public class PageRepository {

	private GraphRepository<Page> repository;

	private GraphDatabaseContext context;

	@Autowired
	public void setGraphRepositoryFactory(GraphDatabaseContext ctx,
			DirectGraphRepositoryFactory graphRepositoryFactory) {
		repository = graphRepositoryFactory.createGraphRepository(Page.class);
		context = ctx;
	}

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

	public Page findPageByTitle(String title) {
		return ((AbstractGraphRepository<Node, Page>)repository).findByPropertyValue("index_title", "title", title);
	}

	public Page findPageByTitleManually(String title) {
		Index<Node> index = context.getIndex(Page.class, "index_name");
		IndexHits<Node> hits = index.get("title", title);
		if (hits.hasNext()) {
			return context.createEntityFromState(hits.next(), Page.class);
		}
		return null;
	}

}
