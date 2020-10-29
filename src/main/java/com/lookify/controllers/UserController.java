package com.lookify.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lookify.models.Artist;
import com.lookify.models.Comment;
import com.lookify.models.LoginUser;
import com.lookify.models.Song;
import com.lookify.models.User;
import com.lookify.services.ArtistService;
import com.lookify.services.SongService;
import com.lookify.services.UserService;

@Controller
public class UserController {

	private static UserService userServ;
	private static SongService songServ;
	private static ArtistService artServ;

	public UserController(UserService userServ, SongService songServ, ArtistService artServ) {
		this.userServ = userServ;
		this.songServ = songServ;
		this.artServ = artServ;
	}

	@GetMapping("/")
	public String signIn(Model model) {
		model.addAttribute("registerringUser", new User());
		model.addAttribute("loginUser", new LoginUser());
		return "login.jsp";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registerringUser") User registerringUser, BindingResult result,
			Model model, HttpSession session) {
		if (!registerringUser.getPassword().equals(registerringUser.getConfirm())) {
			result.rejectValue("confirm", "Match", "Confirm Password must match Password!");
		}
		if (userServ.getUser(registerringUser.getEmail()) != null) {
			result.rejectValue("email", "Unique", "Email already in use!");
		}

		if (result.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "login.jsp";
		} else {
			session.setAttribute("user", userServ.create(registerringUser));
			return "redirect:/artist/dashboard";
		}
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result, Model model,
			HttpSession session) {
		User loggingInUser = userServ.login(loginUser, result);
		if (result.hasErrors()) {
			model.addAttribute("registerringUser", new User());
			return "login.jsp";
		} else {
			session.setAttribute("user", loggingInUser);
			return "redirect:/artist/dashboard";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}

	@GetMapping("/user")
	public String userProfile(HttpSession session, Model model) {
		User loggedInUser = (User) session.getAttribute("user");
		if (loggedInUser == null) {
			return "redirect:/";
		}
		User userFromDB = userServ.getUser(loggedInUser.getId());
		model.addAttribute("user", userFromDB);
		List<Song> otherSongs = songServ.getAll();
		List<Artist> otherArtist = artServ.getAll();
		for (Comment r : userFromDB.getComments()) {
			otherSongs.remove(r.getSong());
		}
		model.addAttribute("mainsToReview", otherSongs);
		return "userProfile.jsp";
	}

}
