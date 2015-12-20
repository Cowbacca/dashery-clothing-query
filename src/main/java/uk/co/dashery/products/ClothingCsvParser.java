package uk.co.dashery.products;

import com.univocity.parsers.common.processor.RowProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import uk.co.dashery.clothing.Clothing;

import java.io.IOException;
import java.util.List;

public abstract class ClothingCsvParser<T extends RowProcessor> {
    public List<Clothing> parse(Products products) throws IOException {
        T rowProcessor = getRowProcessor();

        CsvParser parser = createCsvParser(rowProcessor);

        parser.parse(products.generateReader());

        return getClothing(rowProcessor);
    }

    protected abstract T getRowProcessor();

    private CsvParser createCsvParser(RowProcessor rowProcessor) {
        CsvParserSettings parserSettings = createCsvParserSettings(rowProcessor);

        return new CsvParser(parserSettings);
    }

    private CsvParserSettings createCsvParserSettings(RowProcessor rowProcessor) {
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setRowProcessor(rowProcessor);
        parserSettings.setHeaderExtractionEnabled(true);
        parserSettings.setLineSeparatorDetectionEnabled(true);
        return parserSettings;
    }

    protected abstract List<Clothing> getClothing(T rowProcessor);
}