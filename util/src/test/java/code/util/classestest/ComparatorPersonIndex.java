package code.util.classestest;
import code.util.ints.Comparing;

import code.util.Numbers;

public class ComparatorPersonIndex implements Comparing<SortingPerson> {

    @Override
    public int compare(SortingPerson _o1, SortingPerson _o2) {
        return Numbers.compare(_o1.getIndex(), _o2.getIndex());
    }

}
