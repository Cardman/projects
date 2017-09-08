package code.expressionlanguage.methods;
import java.util.Comparator;

import code.util.Numbers;

public final class ComparatorBlockGroupOrder implements Comparator<BlockGroup> {

    @Override
    public int compare(BlockGroup _o1, BlockGroup _o2) {
        return Numbers.compare(_o1.getOrder(), _o2.getOrder());
    }

}
