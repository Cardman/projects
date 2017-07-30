package code.expressionlanguage.methods;
import java.util.Comparator;

import code.util.Numbers;

public class ComparatorInterfaceNode implements Comparator<InterfaceNode> {

    @Override
    public int compare(InterfaceNode _o1, InterfaceNode _o2) {
        return Numbers.compare(_o1.getOrder(), _o2.getOrder());
    }

}
