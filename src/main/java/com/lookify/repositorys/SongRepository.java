package com.lookify.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lookify.models.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

}
