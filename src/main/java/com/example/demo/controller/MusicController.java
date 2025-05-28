package com.example.demo.controller;

import com.example.demo.dto.MusicDTO;
import com.example.demo.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class MusicController {

    @Autowired
    private MusicService service;

    // 재생목록 전체 트랙 정보 (노래 제목과 아티스트명만)
    @GetMapping("/post")
    public List<MusicDTO> getPlaylistTracks(@PathVariable String playlistId) throws Exception {
        return service.getPlaylistTracks(playlistId);
    }

    // 재생목록에서 특정 인덱스 트랙 상세 정보 (모든 필드)
    @GetMapping("/playlist/{playlistId}/track/{index}")
    public MusicDTO getTrackFromPlaylist(@PathVariable String playlistId, @PathVariable int index) throws Exception {
        return service.getTrackFromPlaylist(playlistId, index);
    }
}
