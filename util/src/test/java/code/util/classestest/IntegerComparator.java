package code.util.classestest;
import java.util.Comparator;

import code.util.Numbers;

public class IntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer _arg0, Integer _arg1) {
        return Numbers.compare(_arg0, _arg1);
    }

}
