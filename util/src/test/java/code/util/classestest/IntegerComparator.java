package code.util.classestest;
import code.util.Numbers;
import code.util.ints.Comparing;

public class IntegerComparator implements Comparing<Integer> {

    @Override
    public int compare(Integer _arg0, Integer _arg1) {
        return Numbers.compareLg(_arg0, _arg1);
    }

}
