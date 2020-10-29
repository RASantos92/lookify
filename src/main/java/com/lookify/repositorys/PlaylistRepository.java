package com.lookify.repositorys;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lookify.models.Playlist;

@Repository
public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
	Optional<Playlist> findPlaylistByName(String name);
}
