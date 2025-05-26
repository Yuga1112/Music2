package com.example.demo.service;

public interface MusicService {
	
	//토큰 발급
	String getAccessToken() throws Exception;
	
	
	//재생목록 가져오기
	String getPlaylistTracks(String playlistId);

	//재생목록에서 첫번째 반환
	String getFirstTrackPreviewUrl(String playlistId);
	
	
	}


