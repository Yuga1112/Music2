package com.example.demo.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.SpotifyConfig;

@Service
public class MusicServiceImpl implements MusicService{
	
	@Autowired
	SpotifyConfig config;

	 public String getAccessToken() throws Exception {
		    String clientId = config.clientId;
		    String clientSecret = config.clientSecret;

		    String auth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

		    HttpRequest request = HttpRequest.newBuilder()
		            .uri(URI.create("https://accounts.spotify.com/api/token"))
		            .header("Authorization", "Basic " + auth)
		            .header("Content-Type", "application/x-www-form-urlencoded")
		            .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
		            .build();

		    HttpClient client = HttpClient.newHttpClient();
		    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		    String responseBody = response.body();
		    System.out.println("Token Response:\n" + responseBody);

		    JSONObject json = new JSONObject(responseBody);
		    if (!json.has("access_token")) {
		        throw new RuntimeException("❌ Access token을 가져올 수 없습니다.");
		    }

		    return json.getString("access_token");
		}


	@Override
	public String getPlaylistTracks(String playlistId) throws Exception {
		
		String token = getAccessToken();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/playlists/" + playlistId))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

       return response.body();
	}

	@Override
	public String getFirstTrackFromPlaylist(String playlistId) throws Exception {
		
		String token = getAccessToken();

	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(new URI("https://api.spotify.com/v1/playlists/" + playlistId))
	            .header("Authorization", "Bearer " + token)
	            .GET()
	            .build();

	    HttpClient client = HttpClient.newHttpClient();
	    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	    String responseBody = response.body();
	    JSONObject json = new JSONObject(responseBody);

	    // 첫 번째 트랙 추출
	    JSONObject firstTrackItem = json
	            .getJSONObject("tracks")
	            .getJSONArray("items")
	            .getJSONObject(0)
	            .getJSONObject("track");

	    String trackName = firstTrackItem.getString("name");
	    String artistName = firstTrackItem
	            .getJSONArray("artists")
	            .getJSONObject(0)
	            .getString("name");

	    return trackName + " - " + artistName;

	}

}
