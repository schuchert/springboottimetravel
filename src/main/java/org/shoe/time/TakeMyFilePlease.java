package org.shoe.time;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class TakeMyFilePlease {
    private final FileProcessor fileProcessor;

    public TakeMyFilePlease(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    @PostMapping("/upload")
    public long upload(@RequestParam("file") MultipartFile file) throws IOException {
        log.error("Running on thread: {}", Thread.currentThread().getId());
        byte[] bytes = file.getBytes();
        fileProcessor.process(bytes);
        return bytes.length;
    }
}
