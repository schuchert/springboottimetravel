package org.shoe.time;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class FileProcessor {
    private static final int LINES_TO_PROCESS = 100;

    @Async
    public void process(byte[] fileContents) {
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(fileContents));
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis, StandardCharsets.UTF_8));

        List<String> lines = new ArrayList<>(LINES_TO_PROCESS);
        String currentLine;
        while (true) {
            try {
                if ((currentLine = reader.readLine()) == null) {
                    break;
                }
                lines.add(currentLine);
                if (lines.size() >= LINES_TO_PROCESS) {
                    handleGroup(lines);
                    lines.clear();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!lines.isEmpty()) {
            handleGroup(lines);
        }
    }

    void handleGroup(List<String> lines) {
        lines.forEach(log::error);
    }
}
