package com.lookify.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lookify.models.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {

	List<Artist> findByNameContaining(String search);

}
