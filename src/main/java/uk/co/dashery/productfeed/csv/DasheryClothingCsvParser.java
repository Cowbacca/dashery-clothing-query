package uk.co.dashery.productfeed.csv;

import com.univocity.parsers.common.processor.BeanListProcessor;
import org.springframework.stereotype.Component;
import uk.co.dashery.clothing.Clothing;

import java.util.List;

@Component
public class DasheryClothingCsvParser extends ClothingCsvParser<BeanListProcessor<Clothing>> {

    @Override
    protected BeanListProcessor<Clothing> getRowProcessor() {
        return new BeanListProcessor<>(Clothing.class);
    }


    @Override
    protected List<Clothing> getClothing(BeanListProcessor<Clothing> rowProcessor) {
        return rowProcessor.getBeans();
    }

}
