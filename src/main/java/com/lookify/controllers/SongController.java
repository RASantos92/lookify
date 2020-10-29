package com.lookify.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lookify.models.Artist;
import com.lookify.models.Comment;
import com.lookify.models.Playlist;
import com.lookify.models.Song;
import com.lookify.models.User;
import com.lookify.services.ArtistService;
import com.lookify.services.SongService;
import com.lookify.services.UserService;

@Controller
public class SongController {
	private static SongService songserv;
	private static ArtistService artistserv;
	private static UserService userServ;

	public SongController(SongService songserv, ArtistService artistserv, UserService userServ) {
		this.songserv = songserv;
		this.artistserv = artistserv;
		this.userServ = userServ;
	}

	@GetMapping("/dashboard")
	public String dashboard(@RequestParam(value = "search", required = false) String search, Model model,
			HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if (search == null) {
			model.addAttribute("song", songserv.getAll());
			model.addAttribute("user", loggedInUser);
		} else {
			model.addAttribute("song", songserv.search(search));
			model.addAttribute("user", loggedInUser);
		}
		return "dashboard.jsp";
	}

	@GetMapping("/artist/dashboard")
	public String artistDashboard(@RequestParam(value = "search", required = false) String search, Model model,
			HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if (search == null) {
			model.addAttribute("artists", artistserv.getAll());
			model.addAttribute("user", loggedInUser);
		} else {
			model.addAttribute("artists", artistserv.search(search));
			model.addAttribute("user", loggedInUser);
		}
		return "artistDashboard.jsp";
	}

	@PostMapping("/create/song")
	public String create(@Valid @ModelAttribute("newSongPlus") Song newSongPlus, BindingResult result, Model model,
			@RequestParam("artistId") Long artistId, HttpSession session) {
		if (result.hasFieldErrors()) {
			model.addAttribute("artist", artistserv.getOne(artistId));
			model.addAttribute("song", songserv.getAll());
			return "artistDisplay.jsp";
		} else {
			User loggedInUser = (User) session.getAttribute("user");
			newSongPlus.setUser(loggedInUser);
			songserv.Create(newSongPlus);
			return "redirect:/artist/show/" + artistId;
		}
	}

	@PostMapping("/create/artist")
	public String createArtist(@Valid @ModelAttribute("newArtist") Artist newArtist, BindingResult result, Model model,
			HttpSession session) {
		if (result.hasFieldErrors()) {
			model.addAttribute("artist", artistserv.getAll());
			return "addArtist.jsp";
		} else {
			artistserv.create(newArtist);
			return "redirect:/artist/dashboard";
		}
	}

	@GetMapping("/edit/song/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		model.addAttribute("singleSong", songserv.getOne(id));
		return "editSong.jsp";
	}

	@GetMapping("/edit/artist/{id}")
	public String editArtist(@PathVariable("id") Long id, Model model, HttpSession session) {
		model.addAttribute("singleArtist", artistserv.getOne(id));
		return "editArtist.jsp";
	}

	@GetMapping("/add/song")
	public String addPage(Model model, HttpSession session) {
		model.addAttribute("newSong", new Song());
		return "addSong.jsp";
	}

	@GetMapping("/add/artist")
	public String addArtist(Model model, HttpSession session) {
		model.addAttribute("newArtist", new Artist());
		return "addArtist.jsp";
	}

	@PostMapping("/song/update/{id}")
	public String updateSong(@PathVariable("id") Long id, @Valid @ModelAttribute("singleSong") Song singleSong,
			BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "editSong.jsp";
		} else {
			songserv.update(singleSong, id);
			return "redirect:/dashboard";
		}
	}

	@PostMapping("/artist/update/{id}")
	public String updateArtist(@PathVariable("id") Long id, @Valid @ModelAttribute("singleArtist") Artist singleArtist,
			BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "editArtist.jsp";
		} else {
			artistserv.update(singleArtist, id);
			return "redirect:/artist/show/" + id;
		}
	}

	@GetMapping("/song/destroy/{id}")
	public String destroy(@PathVariable("id") Long id, @Valid @ModelAttribute("singleSong") Song singleSong,
			BindingResult result, HttpSession session) {
		songserv.destory(singleSong, id);
		return "redirect:/dashboard";
	}

	@GetMapping("/artist/destroy/{id}")
	public String destroyArtist(@PathVariable("id") Long id, @Valid @ModelAttribute("singleArtist") Song singleArtist,
			BindingResult result, HttpSession session) {
		songserv.destory(singleArtist, id);
		return "redirect:/artist/dashboard";
	}

	@GetMapping("/song/show/{id}")
	public String show(@PathVariable("id") Long id, Model model, HttpSession session) {
		model.addAttribute("newComment", new Comment());
		model.addAttribute("song", songserv.getOne(id));
		return "songInfo.jsp";
	}

	@GetMapping("/artist/show/{id}")
	public String showArtist(@PathVariable("id") Long id, Model model, HttpSession session) {
		model.addAttribute("artist", artistserv.getOne(id));
		model.addAttribute("newSong", new Song());
		return "artistDisplay.jsp";
	}

	@GetMapping("/songs/top/10")
	public String top10Songs(Model model, HttpSession session) {
		model.addAttribute("songs", songserv.top10Songs());
		return "topCharts.jsp";
	}

	@PostMapping("/song/{id}/comment")
	public String commentSong(@Valid @ModelAttribute("newComment") Comment newComment, BindingResult result,
			@PathVariable("id") Long id, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if (result.hasErrors()) {
			model.addAttribute("song", songserv.getOne(id));
			return "songInfo.jsp";
		}
		newComment.setSong(songserv.getOne(id));
		newComment.setUser(loggedInUser);
		Comment c = songserv.create(newComment);
		if (c == null) {
			result.rejectValue("rating", "unique", "You have already reviewed this Show!");
			model.addAttribute("someShow", songserv.getOne(id));
			return "songInfo.jsp";
		}
		return "redirect:/song/show/" + id;
	}

	@PostMapping("/user/{id}/playlist")
	public String createPlaylist(@Valid @ModelAttribute("newPlaylist") Playlist newPlaylist, BindingResult result,
			@PathVariable("id") Long id, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if (result.hasErrors()) {
			model.addAttribute("user", loggedInUser);
			return "playlist.jsp";
		}
		newPlaylist.setUser(loggedInUser);
		songserv.create(newPlaylist);
		return "redirect:/playlist";
	}

	@GetMapping("/playlist/{id}")
	public String playlist(@PathVariable("id") Long id, HttpSession session, Model model) {
		User loggedInUser = (User) session.getAttribute("user");
		if (loggedInUser == null) {
			return "redirect:/";
		}
		model.addAttribute("user", userServ.getUser(id));
		model.addAttribute("newPlaylist", new Playlist());
		return "playlist.jsp";
	}
}
