package org.innovonto.greenhouse.api.backend.common.inspiration.images;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Service
public class InspirationImageService {
    private static final Logger log = LoggerFactory.getLogger(InspirationImageService.class);
    private final Path mediaDirectory;

    @Autowired
    public InspirationImageService(@Value("${innovonto.brainstorming-app.media.mediaDirectory}") String mediaDirectory) throws IOException {
        this.mediaDirectory = Paths.get(mediaDirectory);
        if (Files.exists(this.mediaDirectory)) {
            log.info("Using existing media directory: " + this.mediaDirectory);
        } else {
            log.info("Media directory '" + this.mediaDirectory + "' doesn't exist. Creating it.");
            Files.createDirectories(this.mediaDirectory);
        }
    }

    public String saveImageAndReturnIconPath(UUID inspirationId, byte[] imageByte) throws IOException {

        final Path inspirationsBasePath = this.mediaDirectory.resolve("inspirations");
        Files.createDirectories(inspirationsBasePath);
        final Path iconPath = inspirationsBasePath.resolve(inspirationId + ".png");
        Files.write(iconPath, imageByte, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        return "/media/" + this.mediaDirectory.relativize(iconPath);
    }
}
