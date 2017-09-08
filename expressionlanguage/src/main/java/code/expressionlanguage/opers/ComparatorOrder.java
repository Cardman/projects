package code.expressionlanguage.opers;
import java.util.Comparator;

import code.util.Numbers;

public final class ComparatorOrder implements Comparator<OperationNode> {

    @Override
    public int compare(OperationNode _o1, OperationNode _o2) {
        return Numbers.compare(_o1.getOrder(), _o2.getOrder());
    }

}
