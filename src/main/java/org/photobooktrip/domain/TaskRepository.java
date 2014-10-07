package org.photobooktrip.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tasks", path = "tasks")
public interface TaskRepository extends CrudRepository<Task, Integer>, TaskRepositoryCustom {

	List<Task> findByTaskArchived(@Param("archivedfalse") int taskArchived);

	List<Task> findByTaskStatus(@Param("status") String taskStatus);

}