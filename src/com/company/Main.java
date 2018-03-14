package com.company;

import com.company.model.Artist;
import com.company.model.Datasource;
import com.company.model.SongArtist;

import java.util.List;
import java.util.Scanner;

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

        datasource.querySongsMetadata();

        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);

        datasource.createViewForSongArtist();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title");
        String title = scanner.nextLine();

        songArtist = datasource.querySongInfoView("Go Your Own Way");
        if(songArtist.isEmpty()){
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for(SongArtist artist : songArtist){
            System.out.println("FROM VIEW - Artist name = " + artist.getArtistName() +
                   " Track number = " + artist.getTrack());
        }

        datasource.close();

    }
}








