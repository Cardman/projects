package code.util.classestest;
import code.util.*;
import code.util.ints.Comparing;

public final class ComparatorEnum implements Comparing<MyEnum> {

    @Override
    public int compare(MyEnum _e1, MyEnum _e2) {
        return Numbers.compareLg(_e1.ordinal(), _e2.ordinal());
    }

}
