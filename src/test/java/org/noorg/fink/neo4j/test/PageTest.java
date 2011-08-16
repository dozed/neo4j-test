package org.noorg.fink.neo4j.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.noorg.fink.neo4j.test.entities.Page;
import org.noorg.fink.neo4j.test.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.GraphDatabaseContext;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "/spring/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PageTest {

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private GraphDatabaseContext graphDatabaseContext;

	@Rollback(false)
	@BeforeTransaction
	public void clearDatabase() {
		Neo4jHelper.cleanDb(graphDatabaseContext);
	}

	@Test
	public void shouldCreatePages() {
		pageRepository.createSomePages();
		assertEquals(3, pageRepository.countPages());
	}

	@Test
	public void shouldPersistPages() {
		pageRepository.createSomePages();
		Page p = pageRepository.findPageByName("root");
		assertNotNull(p);
	}

	@Test
	public void shouldContainSubPages() {
		pageRepository.createSomePages();
		Page p = pageRepository.findPageByName("root");
		assertNotNull(p);
		assertEquals(2, p.getSubPages().size());
	}
	
}
