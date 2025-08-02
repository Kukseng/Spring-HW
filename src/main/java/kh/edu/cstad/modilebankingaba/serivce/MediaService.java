package kh.edu.cstad.modilebankingaba.serivce;

import kh.edu.cstad.modilebankingaba.dto.ResponseMedia;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    ResponseMedia uploadMedia(MultipartFile file);

    List<ResponseMedia> uploadMultiple(MultipartFile[] files);

    Resource downloadMedia(String filename);


    void deleteMedia(String filename);

}
