package code.expressionlanguage.methods;
import java.util.Comparator;

import code.util.Numbers;

public class ComparatorBlockOrder implements Comparator<Block> {

    @Override
    public int compare(Block _o1, Block _o2) {
        return Numbers.compare(_o1.getOrder(), _o2.getOrder());
    }

}
