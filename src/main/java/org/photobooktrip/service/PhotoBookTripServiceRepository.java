package org.photobooktrip.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "photo", path = "photo")
public interface PhotoBookTripServiceRepository extends PagingAndSortingRepository<Photo, Integer> {


}