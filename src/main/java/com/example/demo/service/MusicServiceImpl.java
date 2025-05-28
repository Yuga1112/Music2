package com.example.demo.service;

import java.net.URI;
import java.net.http.HttpClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.config.SpotifyConfig;
import com.example.demo.dto.MusicDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MusicServiceImpl implements MusicService{
	
	@Autowired
	SpotifyConfig config;
	ObjectMapper objectMapper;
	
	
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
    public List<MusicDTO> searchTracks(String query) throws Exception {
        String token = getAccessToken();

        String url = UriComponentsBuilder
                .fromUriString("https://api.spotify.com/v1/search")
                .queryParam("q", query)
                .queryParam("type", "track")
                .queryParam("limit", 10)
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        return parseTrackResults(response.getBody());
    }

    private List<MusicDTO> parseTrackResults(String json) throws Exception {
        List<MusicDTO> tracks = new ArrayList<>();
        JsonNode items = objectMapper.readTree(json).path("tracks").path("items");

        for (JsonNode item : items) {
            String name = item.path("name").asText();
            String artist = item.path("artists").get(0).path("name").asText();
            String imageUrl = item.path("album").path("images").get(0).path("url").asText();

            MusicDTO dto = MusicDTO.builder()
            	    .name(name)
            	    .artist(artist)
            	    .imageUrl(imageUrl)
            	    .build();
        }

        return tracks;
    }
    
	}



