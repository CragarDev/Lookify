package com.cragardev.lookify.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cragardev.lookify.models.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

    // this method retrieves all the songs from the database
    List<Song> findAll();
    //

    // this method retrieves all the songs from the database
    // sorts them by ratings in descending order
    @Query("SELECT song FROM Song song ORDER BY song.rating DESC")
    List<Song> findAllSortedByRating();
    //

    // this method finds songs by artists containing the search string in the artist
    // field
    @Query("SELECT song FROM Song song WHERE song.artist LIKE %?1%")
    List<Song> findByArtist(String artist);

    // // this method counts how many titles contain a certain string
    // Long countByTitleContaining(String search);
    //
    // // this method deletes a book that starts with a specific title
    // Long deleteByTitleStartingWith(String search);

}
