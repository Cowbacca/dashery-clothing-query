package uk.co.dashery.service;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.stereotype.Component;
import uk.co.dashery.data.Clothing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;

@Component
public class ClothingCsvParser {
    public List<Clothing> parseFromUrl(String url) throws IOException {
        BeanListProcessor<Clothing> rowProcessor = new BeanListProcessor<>(Clothing.class);

        CsvParser parser = createCsvParser(rowProcessor);

        parser.parse(createReader(url));

        return rowProcessor.getBeans();
    }

    private CsvParser createCsvParser(BeanListProcessor<Clothing> rowProcessor) {
        CsvParserSettings parserSettings = createCsvParserSettings(rowProcessor);

        return new CsvParser(parserSettings);
    }

    private CsvParserSettings createCsvParserSettings(BeanListProcessor<Clothing> rowProcessor) {
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setRowProcessor(rowProcessor);
        parserSettings.setHeaderExtractionEnabled(true);
        return parserSettings;
    }

    private Reader createReader(String urlString) throws IOException {
        URL url = new URL(urlString);
        return new BufferedReader(new InputStreamReader(url.openStream()));
    }
}
