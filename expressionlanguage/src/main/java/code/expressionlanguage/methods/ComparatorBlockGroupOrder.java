package code.expressionlanguage.methods;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorBlockGroupOrder implements Comparing<BlockGroup> {

    @Override
    public int compare(BlockGroup _o1, BlockGroup _o2) {
        return Numbers.compare(_o1.getOrder(), _o2.getOrder());
    }

}
