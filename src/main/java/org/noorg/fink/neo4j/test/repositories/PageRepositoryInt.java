package org.noorg.fink.neo4j.test.repositories;

import org.noorg.fink.neo4j.test.entities.Page;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepositoryInt extends GraphRepository<Page>,
		NamedIndexRepository<Page> {
}
