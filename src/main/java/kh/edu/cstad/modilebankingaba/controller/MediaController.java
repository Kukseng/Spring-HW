package kh.edu.cstad.modilebankingaba.controller;

import kh.edu.cstad.modilebankingaba.dto.ResponseMedia;
import kh.edu.cstad.modilebankingaba.serivce.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
//@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/medias")
    public  ResponseMedia uploadMedia(@RequestPart MultipartFile file) {

        return mediaService.uploadMedia(file);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/medias/upload-multiple")
    public List<ResponseMedia> uploadMediaMultiple(@RequestPart MultipartFile[] files) {
        return mediaService.uploadMultiple(files);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("media/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Resource resource = mediaService.downloadMedia(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


    @DeleteMapping("/api/v1/medias/delete/{filename:.+}")
    public ResponseEntity<String> deleteMedia(@PathVariable String filename) {
        mediaService.deleteMedia(filename);
        return ResponseEntity.ok("Image deleted successfully");
    }

}
