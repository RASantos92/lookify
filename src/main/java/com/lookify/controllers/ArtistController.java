package com.lookify.controllers;

import org.springframework.stereotype.Controller;

import com.lookify.services.ArtistService;
import com.lookify.services.SongService;
import com.lookify.services.UserService;

@Controller
public class ArtistController {
	private ArtistService artistserv;

	public ArtistController(SongService songserv, ArtistService artistserv, UserService userServ) {
		this.songserv = songserv;
		this.artistserv = artistserv;
		this.userServ = userServ;
	}

}
