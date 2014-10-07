package org.photobooktrip.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TaskRepositoryImpl implements TaskRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Task> findBySpecialTasks() {
		return Arrays.asList(new Task("my speacial!"));
	}

}
