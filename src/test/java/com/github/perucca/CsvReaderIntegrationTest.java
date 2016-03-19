package com.github.perucca;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvReaderIntegrationTest {

    CsvReader csvReader;

    @Before
    public void readCsv() throws IOException {
        csvReader = new CsvReader(new InputStreamReader(new ClassPathResource("data.csv").getInputStream()));
    }

    @Test
    public void should_get_file_lines() {
        List<String> lineStream = csvReader.getLines();

        assertThat(lineStream).hasSize(3);
    }
}
