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

	        String auth = clientId + ":" + clientSecret;
	        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

	        String body = "grant_type=client_credentials";

	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(new URI("https://accounts.spotify.com/api/token"))
	                .header("Authorization", "Basic " + encodedAuth)
	                .header("Content-Type", "application/x-www-form-urlencoded")
	                .POST(HttpRequest.BodyPublishers.ofString(body))
	                .build();

	        HttpClient client = HttpClient.newHttpClient();
	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	        JSONObject json = new JSONObject(response.body());
	        return json.getString("access_token");
	    }

	@Override
	public String getPlaylistTracks(String playlistId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstTrackPreviewUrl(String playlistId) {
		// TODO Auto-generated method stub
		return null;
	}

}
