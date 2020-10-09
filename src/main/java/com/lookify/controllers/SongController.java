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

import com.lookify.models.Song;
import com.lookify.services.SongService;

@Controller
public class SongController {
	private static SongService songserv;

	public SongController(SongService songserv) {
		this.songserv = songserv;
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

	@PostMapping("/create/song")
	public String create(@Valid @ModelAttribute("newSong") Song newSong, BindingResult result, Model model) {
		if (result.hasFieldErrors()) {
			model.addAttribute("song", songserv.getAll());
			return "addSong.jsp";
		} else {
			songserv.Create(newSong);
			return "redirect:/dashboard";
		}
	}

	@GetMapping("/edit/song/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("singleSong", songserv.getOne(id));
		return "editSong.jsp";
	}

	@GetMapping("/add/song")
	public String addPage(Model model) {
		model.addAttribute("newSong", new Song());
		return "addSong.jsp";
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

	@GetMapping("/song/destroy/{id}")
	public String destroy(@PathVariable("id") Long id, @Valid @ModelAttribute("singleSong") Song singleSong,
			BindingResult result) {
		songserv.destory(singleSong, id);
		return "redirect:/";
	}

	@GetMapping("/song/show/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		model.addAttribute("song", songserv.getOne(id));
		return "songInfo.jsp";
	}

	@GetMapping("/songs/top/10")
	public String top10Songs(Model model) {
		model.addAttribute("songs", songserv.top10Songs());
		return "topCharts.jsp";
	}
}
