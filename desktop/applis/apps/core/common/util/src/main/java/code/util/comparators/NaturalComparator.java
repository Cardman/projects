package code.util.comparators;
import code.util.ints.Comparing;

public final class NaturalComparator implements Comparing<String> {

    @Override
    public int compare(String _o1, String _o2) {
        return _o1.compareTo(_o2);
    }

}
