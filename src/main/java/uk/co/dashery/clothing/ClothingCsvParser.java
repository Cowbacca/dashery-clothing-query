package uk.co.dashery.clothing;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.stereotype.Component;
import uk.co.dashery.clothing.Clothing;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component
public class ClothingCsvParser {
    public List<Clothing> parse(Reader csvReader) throws IOException {
        BeanListProcessor<Clothing> rowProcessor = new BeanListProcessor<>(Clothing.class);

        CsvParser parser = createCsvParser(rowProcessor);

        parser.parse(csvReader);

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


}
