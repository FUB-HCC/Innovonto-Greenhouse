package org.innovonto.greenhouse.api.backend.common.inspiration.images;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/images")
public class InspirationImages {
    private static final Logger log = LoggerFactory.getLogger(InspirationImages.class);

    private final InspirationImageService imageService;

    @Autowired
    public InspirationImages(InspirationImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/")
    public ResponseEntity<String> saveInspirationImage(@RequestBody InspirationImageRequest imageRequest,
                                                       HttpServletRequest request) throws IllegalAccessException, IOException {
        try {
            //TODO input is in format: data:image/png;base64,{data}
            String data = imageRequest.getData();
            String header = data.substring(0, 25);
            if (header.startsWith("data:image/png;base64,")) {
                byte[] imageByte = Base64.getDecoder().decode(data.substring(22));
                String imagePath = imageService.saveImageAndReturnIconPath(imageRequest.getInspirationId(), imageByte);
                return new ResponseEntity<>(imagePath, HttpStatus.CREATED);
            } else {
                if (header.length() > 24) {
                    throw new IllegalArgumentException("Badly formatted input, input start is: " + data.substring(0, 25));
                } else {
                    throw new IllegalArgumentException("Badly formatted input, size is: " + data.length());
                }
            }
        } catch (Exception e) {
            log.error("Error decoding Image!", e);
            throw new RuntimeException(e);
        }
    }

}
