package com.company;

import com.company.model.Artist;
import com.company.model.Datasource;
import com.company.model.SongArtist;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Cant open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtist(5);
        if(artists == null){
            System.out.println("No artists!");
            return;
        }

        for(Artist artist: artists){
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        List<String> albumsForArtists = datasource.queryAlbumsForArtist("Carole Knight", Datasource.ORDER_BY_ASC);
        for(String album : albumsForArtists){
            System.out.println(album);
        }

        List<SongArtist> songArtist = datasource.queryArtistsForSong("Heartless", Datasource.ORDER_BY_ASC);
        if(songArtist == null){
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for(SongArtist artist : songArtist){
            System.out.println("Artist name = " + artist.getArtistName() +
            " Album name = " + artist.getAlbumName() +
            " Track = " + artist.getTrack());
        }


        datasource.close();

    }
}








