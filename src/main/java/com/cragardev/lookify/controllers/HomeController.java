package com.cragardev.lookify.controllers;

import java.util.List;

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

import com.cragardev.lookify.models.Song;
import com.cragardev.lookify.services.SongService;

@Controller
public class HomeController {
	
	//
	// Injecting the Song Service
	//
	private final SongService songService;
	
	
	public HomeController(SongService songService) {
		super();
		this.songService = songService;
	}

	//
	// ========= /, Landing, home, index page 1 ===========
	//
	@GetMapping("/")
	public String index() {
		return "index.jsp";
	}
	
	//
	// ========= /, Landing, home, index page 2 ===========
	//
	@GetMapping("/lookify")
	public String home() {
		return "index.jsp";
	}
	
	//
	// ========= Dashboard page ===========
	//
	@GetMapping("/lookify/dashboard")
	public String dashboard(Model model,
			@ModelAttribute("song") Song song) {
		
		List<Song> songs = songService.allSongs();
		model.addAttribute("songs", songs);
		
		
		return "dashboard.jsp";
	}
	//
	// ========= Create New Song page ===========
	//
	@GetMapping("/lookify/newSong")
	public String newSong(@ModelAttribute("song") Song song, Model model) {
		
		
		
		return "newSong.jsp";
	}
	
	//
	// ========= Create New Song PROCESS ===========
	//
	@PostMapping("/lookify/create")
	public String createSong(
			@Valid
			@ModelAttribute("song") Song song,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			
			model.addAttribute("song", song);
			return "newSong.jsp";
		} else {
			songService.createSong(song);
			return "redirect:/lookify/dashboard";
		}
	}
	
	//
	// ========= Show One Song Details ===========
	//
	@GetMapping("/lookify/showSong/{id}")
	public String showOneSong(Model model,@PathVariable(value="id") Long id) {
		
		Song song = songService.findSong(id);
		model.addAttribute("song", song);
		
		return "showSong.jsp";
	}

	
	//
	// ========= Top Ten page ===========
	//
	@GetMapping("/lookify/topTen")
	public String topTen(Model model, @ModelAttribute("song") Song song) {
		
		List<Song> songs = songService.findTop10ByRating();
		model.addAttribute("songs", songs);
		
		return "topTen.jsp";
	}
	
	//
	// ========= Search Results page ===========
	//
	@GetMapping("/lookify/searchArtist")
	public String searchArtist(
			Model model, 
			@ModelAttribute("song") Song song,
			HttpSession session) {
			
//			System.out.println("============= 03 - redirected =====================");
		
			String searchQuery = (String) session.getAttribute("searchQuery");
			
//			System.out.println("============= 04 =====================");
//			System.out.println(searchQuery);
			
			List<Song> songs = songService.findByArtistContaining(searchQuery);
			
//			System.out.println("============= 05 - From Service =====================");
//			System.out.println(songs);
			
			model.addAttribute("songs", songs);
			model.addAttribute("searchQuery", searchQuery);
		
		return "searchArtist.jsp";
	}
	
	
	//
	// ========= Search Results Processing ===========
	//
	@PostMapping("/lookify/searchArtist")
	public String searchArtistProcess(
			Model model, 
			@ModelAttribute("song") Song song,
			@RequestParam(value="searchQuery") String searchArtist,
			HttpSession session) {
		
//		System.out.println("============= 01 =====================");
//		System.out.println(searchArtist);
		
		session.setAttribute("searchQuery", searchArtist);
		
		// check that session has the searchQuery value
		
//		System.out.println("============= 02 =====================");
//		System.out.println(session.getAttribute("searchQuery"));
		
		
		return "redirect:/lookify/searchArtist";
	}
	
	
	//
	// ========= Delete Song PROCESS ===========
	//
	@GetMapping("/lookify/delete/{id}")
	private String deleteSong(@PathVariable("id") Long id) {
		songService.deleteSong(id);
		return "redirect:/lookify/dashboard";

	}
	
	
	
}
