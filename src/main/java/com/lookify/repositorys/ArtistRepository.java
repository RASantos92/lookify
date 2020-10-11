package com.lookify.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lookify.models.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {

}
