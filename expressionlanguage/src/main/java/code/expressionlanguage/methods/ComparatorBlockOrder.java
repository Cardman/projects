package code.expressionlanguage.methods;
import code.util.ints.Comparing;

import code.util.Numbers;

public final class ComparatorBlockOrder implements Comparing<Block> {

    @Override
    public int compare(Block _o1, Block _o2) {
        return Numbers.compare(_o1.getOrder(), _o2.getOrder());
    }

}
