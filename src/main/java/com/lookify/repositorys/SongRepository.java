package com.lookify.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lookify.models.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

	@Query(value = "SELECT * FROM songs ORDER BY rate DESC LIMIT 10", nativeQuery = true)
	List<Song> top10Songs();

	List<Song> findByTitleContaining(String search);

}
