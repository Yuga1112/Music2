package com.example.demo.service;

import java.util.List;
import reactor.core.publisher.Mono;

import com.example.demo.dto.MusicDTO;

public interface MusicService {
	
	//토큰 발급
	String getAccessToken() throws Exception;
	
	//재생목록 가져오기
	List<MusicDTO> getPlaylistTracks(String playlistId) throws Exception;

	//재생목록에서 첫번째 반환
	MusicDTO getTrackFromPlaylist(String playlistId, int index) throws Exception;

	
	    Mono<String> search(String query, String type, String market, int limit, int offset, boolean includeExternal);
	}




