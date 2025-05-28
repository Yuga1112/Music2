package com.example.demo.service;

import java.net.URI;
import java.net.http.HttpClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.config.SpotifyConfig;

import com.example.demo.dto.MusicDTO;


@Service
public class MusicServiceImpl implements MusicService{
	
	@Autowired
	SpotifyConfig config;
	 public MusicServiceImpl(WebClient webClient) {
	        this.webClient = webClient;
	    }
	 
	WebClient webClient;

	   
	   
	
	 public String getAccessToken() throws Exception {
		    String clientId = config.clientId;
		    String clientSecret = config.clientSecret;
		        String auth = clientId + ":" + clientSecret;
		        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

		        HttpRequest request = HttpRequest.newBuilder()
		                .uri(new URI("https://accounts.spotify.com/api/token"))
		                .header("Authorization", "Basic " + encodedAuth)
		                .header("Content-Type", "application/x-www-form-urlencoded")
		                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
		                .build();

		        HttpClient client = HttpClient.newHttpClient();
		        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		        if (response.statusCode() != 200) {
		            throw new RuntimeException("Spotify 토큰 발급 실패: " + response.body());
		        }

		        JSONObject json = new JSONObject(response.body());
		        return json.getString("access_token");
		    }
		


	 @Override
	 public List<MusicDTO> getPlaylistTracks(String playlistId) throws Exception {
	     String token = getAccessToken();

	     HttpRequest request = HttpRequest.newBuilder()
	             .uri(new URI("https://api.spotify.com/v1/playlists/" + playlistId))
	             .header("Authorization", "Bearer " + token)
	             .GET()
	             .build();

	     HttpClient client = HttpClient.newHttpClient();
	     HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	     JSONObject json = new JSONObject(response.body());
	     JSONArray items = json.getJSONObject("tracks").getJSONArray("items");

	     List<MusicDTO> trackList = new ArrayList<>();

	     for (int i = 0; i < items.length(); i++) {
	         JSONObject trackItem = items.getJSONObject(i).getJSONObject("track");
	         String name = trackItem.getString("name");
	         String artistName = trackItem.getJSONArray("artists").getJSONObject(0).getString("name");

	         MusicDTO dto = MusicDTO.builder()
	                 .name(name)
	                 .artist(artistName)
	                 .build();

	         trackList.add(dto);
	     }

	     return trackList;
	 }


	@Override
	public MusicDTO getTrackFromPlaylist(String playlistId, int index) throws Exception {
	    String token = getAccessToken();

	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(new URI("https://api.spotify.com/v1/playlists/" + playlistId))
	            .header("Authorization", "Bearer " + token)
	            .GET()
	            .build();

	    HttpClient client = HttpClient.newHttpClient();
	    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	    JSONObject trackItem = new JSONObject(response.body())
	            .getJSONObject("tracks")
	            .getJSONArray("items")
	            .getJSONObject(index)
	            .getJSONObject("track");

	    String name = trackItem.getString("name");
	    String artistName = trackItem.getJSONArray("artists")
	            .getJSONObject(0)
	            .getString("name");
	    String uri = trackItem.getString("uri");
	    String spotifyUrl = trackItem.getJSONObject("external_urls").getString("spotify");
	    String previewUrl = trackItem.optString("preview_url", null); // 없으면 null
	    String imageUrl = trackItem.getJSONObject("album")
	            .getJSONArray("images")
	            .getJSONObject(1)
	            .getString("url");

	    return MusicDTO.builder()
	            .name(name)
	            .artist(artistName)
	            .uri(uri)
	            .spotifyUrl(spotifyUrl)
	            .previewUrl(previewUrl)
	            .imageUrl(imageUrl)
	            .build();
	}


	@Override
	public String search(String query, String type, String market, int limit, int offset, boolean includeExternal)
			throws Exception {

		String accessToken = getAccessToken();

		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/v1/search").queryParam("q", query).queryParam("type", type)
						.queryParam("market", market).queryParam("limit", limit).queryParam("offset", offset)
						.queryParam("include_external", includeExternal ? "audio" : null).build())
				.headers(headers -> headers.setBearerAuth(accessToken))
				.retrieve().bodyToMono(String.class).block();
	}



	
	}



