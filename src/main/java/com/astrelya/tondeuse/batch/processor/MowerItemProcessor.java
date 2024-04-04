package com.astrelya.tondeuse.batch.processor;
import com.astrelya.tondeuse.model.Mower;
import org.springframework.batch.item.ItemProcessor;

public class MowerItemProcessor implements ItemProcessor<Mower, Mower> {
    @Override
    public Mower process(Mower item) throws Exception {
        item.executeCommands();
        return item;
    }
}
