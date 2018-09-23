package code.expressionlanguage.methods;
import code.expressionlanguage.methods.util.ClassEdge;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorClassEdge implements Comparing<ClassEdge> {

    @Override
    public int compare(ClassEdge _o1, ClassEdge _o2) {
        return Numbers.compare(_o1.getOrder(), _o2.getOrder());
    }

}
