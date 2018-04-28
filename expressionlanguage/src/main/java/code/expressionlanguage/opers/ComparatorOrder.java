package code.expressionlanguage.opers;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorOrder implements Comparing<OperationNode> {

    @Override
    public int compare(OperationNode _o1, OperationNode _o2) {
        return Numbers.compare(_o1.getOrder(), _o2.getOrder());
    }

}
