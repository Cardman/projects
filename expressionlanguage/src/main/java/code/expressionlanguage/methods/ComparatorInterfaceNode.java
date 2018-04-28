package code.expressionlanguage.methods;
import code.util.ints.Comparing;

import code.util.Numbers;

public final class ComparatorInterfaceNode implements Comparing<InterfaceNode> {

    @Override
    public int compare(InterfaceNode _o1, InterfaceNode _o2) {
        return Numbers.compare(_o1.getOrder(), _o2.getOrder());
    }

}
