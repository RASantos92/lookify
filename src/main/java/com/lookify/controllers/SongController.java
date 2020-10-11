package com.lookify.controllers;

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
import com.lookify.models.Song;
import com.lookify.services.ArtistService;
import com.lookify.services.SongService;

@Controller
public class SongController {
	private static SongService songserv;
	private static ArtistService artistserv;

	public SongController(SongService songserv, ArtistService artistserv) {
		this.songserv = songserv;
		this.artistserv = artistserv;
	}

	@GetMapping("/")
	public String index() {
		return "index.jsp";
	}

	@GetMapping("/dashboard")
	public String dashboard(@RequestParam(value = "search", required = false) String search, Model model) {
		if (search == null) {
			model.addAttribute("song", songserv.getAll());
		} else {
			model.addAttribute("song", songserv.search(search));
		}
		return "dashboard.jsp";
	}

	@GetMapping("/artist/dashboard")
	public String artistDashboard(@RequestParam(value = "search", required = false) String search, Model model) {
		if (search == null) {
			model.addAttribute("artists", artistserv.getAll());
		} else {
			model.addAttribute("artists", artistserv.search(search));
		}
		return "artistDashboard.jsp";
	}

	@PostMapping("/create/song")
	public String create(@Valid @ModelAttribute("newSong") Song newSong, BindingResult result, Model model,
			@RequestParam("artistId") Long artistId) {
		if (result.hasFieldErrors()) {
			model.addAttribute("song", songserv.getAll());
			return "artistDisplay.jsp";
		} else {
			songserv.Create(newSong);
			return "redirect:/artist/show/" + artistId;
		}
	}

	@PostMapping("/create/artist")
	public String createArtist(@Valid @ModelAttribute("newArtist") Artist newArtist, BindingResult result,
			Model model) {
		if (result.hasFieldErrors()) {
			model.addAttribute("artist", artistserv.getAll());
			return "addArtist.jsp";
		} else {
			artistserv.create(newArtist);
			return "redirect:/artist/dashboard";
		}
	}

	@GetMapping("/edit/song/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("singleSong", songserv.getOne(id));
		return "editSong.jsp";
	}

	@GetMapping("/edit/artist/{id}")
	public String editArtist(@PathVariable("id") Long id, Model model) {
		model.addAttribute("singleArtist", artistserv.getOne(id));
		return "editArtist.jsp";
	}

	@GetMapping("/add/song")
	public String addPage(Model model) {
		model.addAttribute("newSong", new Song());
		return "addSong.jsp";
	}

	@GetMapping("/add/artist")
	public String addArtist(Model model) {
		model.addAttribute("newArtist", new Artist());
		return "addArtist.jsp";
	}

	@PostMapping("/song/update/{id}")
	public String updateSong(@PathVariable("id") Long id, @Valid @ModelAttribute("singleSong") Song singleSong,
			BindingResult result) {
		if (result.hasErrors()) {
			return "editSong.jsp";
		} else {
			songserv.update(singleSong, id);
			return "redirect:/dashboard";
		}
	}

	@PostMapping("/artist/update/{id}")
	public String updateArtist(@PathVariable("id") Long id, @Valid @ModelAttribute("singleArtist") Artist singleArtist,
			BindingResult result) {
		if (result.hasErrors()) {
			return "editArtist.jsp";
		} else {
			artistserv.update(singleArtist, id);
			return "redirect:/artist/show/" + id;
		}
	}

	@GetMapping("/song/destroy/{id}")
	public String destroy(@PathVariable("id") Long id, @Valid @ModelAttribute("singleSong") Song singleSong,
			BindingResult result) {
		songserv.destory(singleSong, id);
		return "redirect:/dashboard";
	}

	@GetMapping("/artist/destroy/{id}")
	public String destroyArtist(@PathVariable("id") Long id, @Valid @ModelAttribute("singleArtist") Song singleArtist,
			BindingResult result) {
		songserv.destory(singleArtist, id);
		return "redirect:/artist/dashboard";
	}

	@GetMapping("/song/show/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		model.addAttribute("song", songserv.getOne(id));
		return "songInfo.jsp";
	}

	@GetMapping("/artist/show/{id}")
	public String showArtist(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artist", artistserv.getOne(id));
		model.addAttribute("newSong", new Song());
		return "artistDisplay.jsp";
	}

	@GetMapping("/songs/top/10")
	public String top10Songs(Model model) {
		model.addAttribute("songs", songserv.top10Songs());
		return "topCharts.jsp";
	}
}
