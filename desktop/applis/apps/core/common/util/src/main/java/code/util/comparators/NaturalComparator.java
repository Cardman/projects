package code.util.comparators;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class NaturalComparator implements Comparing<String> {

    private final int mult;
    public NaturalComparator() {
        this(1);
    }
    public NaturalComparator(int _m) {
        mult = _m;
    }
    @Override
    public int compare(String _o1, String _o2) {
        return mult*StringUtil.compareStrings(_o1,_o2);
    }

}
