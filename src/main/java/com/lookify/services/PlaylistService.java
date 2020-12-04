package com.lookify.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lookify.models.Playlist;
import com.lookify.repositorys.PlaylistRepository;

@Service
public class PlaylistService {
	private static PlaylistRepository playRepo;

	public PlaylistService(PlaylistRepository playRepo) {
		this.playRepo = playRepo;
	}

	public List<Playlist> getAllPlaylist() {
		return (List<Playlist>) playRepo.findAll();
	}

	public Playlist create(Playlist newPlaylist) {
		return playRepo.save(newPlaylist);
	}
}
