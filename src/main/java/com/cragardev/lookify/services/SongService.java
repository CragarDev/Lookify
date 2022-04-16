package com.cragardev.lookify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cragardev.lookify.models.Song;
import com.cragardev.lookify.repositories.SongRepository;

@Service
public class SongService {
	
	// Injects the repository
	private final SongRepository songRepository;

	public SongService(SongRepository songRepository) {
		this.songRepository = songRepository;
	}
	
	//
	// --------- returns all songs
	//
	public List<Song> allSongs() {
		return songRepository.findAll();
		
	}
	
	//
	// --------- returns all songs ordered by rating
	//
	public List<Song> findTop10ByRating() {
		return songRepository.findAllSortedByRating();
		
	}
	
	//
	// --------- returns all songs by searchArtist query 
	// --------- return a string containing "No artist by that name exist!" somehow... 
	//
	public List<Song> findByArtistContaining(String string) {
		
		System.out.println("============= 06 - Service =====================");
		System.out.println(string);
		return songRepository.findByArtist(string);
		
	}
	
	//
	// --------- creates a song
	//
	public Song createSong(Song song) {
		return songRepository.save(song);
		
	}
	
	//
	// --------- returns a song
	//
	public Song findSong(Long id) {
		Optional<Song> optionalSong = songRepository.findById(id);
		if(optionalSong.isPresent()) {
			return optionalSong.get();
		} else {
			return null;
		}
		
	}

	
	//
	// --------- updates a song
	//
	public Song updateSong(Song song) {
		return songRepository.save(song);
		
	}
	
	//
	// --------- deletes a song
	//
	public void deleteSong(Long id) {
		songRepository.deleteById(id);
		
	}
	
	

}
