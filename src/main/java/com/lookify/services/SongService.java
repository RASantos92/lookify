package com.lookify.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lookify.models.Comment;
import com.lookify.models.Genre;
import com.lookify.models.Playlist;
import com.lookify.models.Song;
import com.lookify.repositorys.CommentRepository;
import com.lookify.repositorys.GenreRepository;
import com.lookify.repositorys.PlaylistRepository;
import com.lookify.repositorys.SongRepository;

@Service
public class SongService {
	private static SongRepository songRepo;
	private static GenreRepository genreRepo;
	private static CommentRepository comRepo;
	private static PlaylistRepository playRepo;

	public SongService(SongRepository songRepo, GenreRepository genreRepo, CommentRepository comRepo,
			PlaylistRepository playRepo) {
		this.songRepo = songRepo;
		this.genreRepo = genreRepo;
		this.comRepo = comRepo;
		this.playRepo = playRepo;
	}

	public Song Create(Song newSong) {
		return songRepo.save(newSong);
	}

	public List<Song> getAll() {
		return (List<Song>) songRepo.findAll();
	}

	public List<Playlist> getAllPlaylist() {
		return (List<Playlist>) playRepo.findAll();
	}

	public Song getOne(Long id) {
		Optional<Song> song = songRepo.findById(id);
		return song.isPresent() ? song.get() : null;
	}

	public Song update(Song toUpdate, Long id) {
		return songRepo.save(toUpdate);
	}

	public void destory(Song toDelete, Long id) {
		songRepo.delete(toDelete);
	}

	public Song saveSong(Song song) {
		return songRepo.save(song);
	}

	public List<Song> search(String search) {
		return songRepo.findByTitleContaining(search);
	}

	public List<Song> top10Songs() {
		return songRepo.top10Songs();
	}

	public Genre createOrRetrieve(String genreName) {
		Optional<Genre> mightExist = genreRepo.findGenreByName(genreName);
		if (mightExist.isPresent()) {
			return mightExist.get();
		} else {
			return genreRepo.save(new Genre(genreName));
		}
	}

	public Song createSongWithGenres(Song newSongPlus) {
		List<Genre> genres = new ArrayList<Genre>();
		for (String genreName : newSongPlus.getGenresInput().split(",")) {
			genres.add(createOrRetrieve(genreName));
		}
		newSongPlus.setGenres(genres);
		return songRepo.save(newSongPlus);
	}

	public void addSongToPlaylist(Song song, Playlist playlist) {
		List<Playlist> play = new ArrayList<Playlist>();

		return null;
	}

	public List<Song> songsInGenre(String genre) {
		Optional<Genre> g = genreRepo.findGenreByName(genre);
		return g.isPresent() ? g.get().getSongs() : new ArrayList<Song>();
	}

	public Comment create(Comment newComment) {
		List<Comment> matchingComments = comRepo.matchingReviews(newComment.getUser().getId(),
				newComment.getSong().getId());
		if (matchingComments.size() > 0) {
			return null;
		}
		newComment.setId(null);
		return comRepo.save(newComment);
	}

	public Playlist create(Playlist newPlaylist) {
		return playRepo.save(newPlaylist);
	}
}
