package org.shoe.time;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ch.qos.logback.classic.Level;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {FileProcessor.class})
class FileProcessorTest {
    @SpyBean
    FileProcessor fileProcessor;

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 1",
            "100, 1",
            "101,2",
            "1001, 11"
    })
    void verifyBufferedCallCount(int count, int calls) {
        fileProcessor.process(createWithLineCount(count));
        verify(fileProcessor, times(calls)).handleGroup(any());
    }

    @ParameterizedTest
    @ValueSource(ints={1, 100, 101})
    void verifyTotalLinesProcessed(int count) {
        ByteArrayOutputStream logContent = LoggerUtil.getLogContent(Level.ERROR);
        fileProcessor.process(createWithLineCount(count));
        String[] split = logContent.toString().split(System.getProperty("line.separator"));

        assertThat(split.length).isEqualTo(count);
    }

    private byte[] createWithLineCount(int count) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            IntStream.range(0, count).forEach(i -> addLine(output, i));
            return output.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void addLine(ByteArrayOutputStream output, int i) {
        byte[] bytes = String.format("%09d,2020-%02d-%02dT12:01:02.333Z, col-%05d", i, (i%12 + 1), (i%28 + 1), i).getBytes(StandardCharsets.UTF_8);
        try {
            output.write(bytes);
            output.write("\n".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}