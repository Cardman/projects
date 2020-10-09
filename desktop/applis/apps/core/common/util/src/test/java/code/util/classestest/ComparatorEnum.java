package code.util.classestest;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class ComparatorEnum implements Comparing<MyEnum> {

    @Override
    public int compare(MyEnum _e1, MyEnum _e2) {
        return NumberUtil.compareLg(_e1.ordinal(), _e2.ordinal());
    }

}
