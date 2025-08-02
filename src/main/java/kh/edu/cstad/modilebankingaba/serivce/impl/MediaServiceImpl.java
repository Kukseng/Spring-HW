package kh.edu.cstad.modilebankingaba.serivce.impl;

import kh.edu.cstad.modilebankingaba.domain.Media;
import kh.edu.cstad.modilebankingaba.dto.ResponseMedia;
import kh.edu.cstad.modilebankingaba.repository.MediaRepository;
import kh.edu.cstad.modilebankingaba.serivce.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Value("${media.server-path}")
    private String serverPath;

    @Value("${media.base-uri}")
    private String baseUrl;

    @Override
    public ResponseMedia uploadMedia(MultipartFile file) {

        //save file in path

        String name = UUID.randomUUID().toString();
        int lastIndex = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastIndex + 1);
        Path path = Paths.get(serverPath,String.format("%s.%s", name, extension));
        try{
            Files.copy(file.getInputStream(),path);

        }catch (Exception e){

            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Media upload failed"
            );
        }

            Media media = new Media();
        media.setName(name);
        media.setExtension(extension);
        media.setMimeTypeFile(file.getContentType());
        media.setIsDelete(false);
        mediaRepository.save(media);
    //from media to response media
        return ResponseMedia.builder()
                .name(media.getName())
                .extension(media.getExtension())
                .mineMediaType(media.getMimeTypeFile())
                .uri(baseUrl + String.format("%s.%s", name, extension))
                .size(file.getSize())
                .build();
    }

    @Override
    public List<ResponseMedia> uploadMultiple(MultipartFile[] files) {

        return Arrays.stream(files)
                .map(this::uploadMedia)
                .toList();
    }

    @Override
    public Resource downloadMedia(String filename) {
        Path filePath = Paths.get(serverPath).resolve(filename).normalize();
        File file = filePath.toFile();

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filename);
        }

        return new FileSystemResource(file);
    }

    @Override
    public void deleteMedia(String filename) {
        Path filePath = Path.of(serverPath + filename);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file: " + filename, e);
        }
    }
}
