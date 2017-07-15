package code.expressionlanguage.methods;
import java.util.Comparator;

import code.expressionlanguage.methods.util.ClassEdge;
import code.util.Numbers;

public class ComparatorClassEdge implements Comparator<ClassEdge> {

    @Override
    public int compare(ClassEdge _o1, ClassEdge _o2) {
        return Numbers.compare(_o1.getOrder(), _o2.getOrder());
    }

}
