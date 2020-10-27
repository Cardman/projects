package code.util.comparators;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class NaturalComparator implements Comparing<String> {

    @Override
    public int compare(String _o1, String _o2) {
        return StringUtil.compareStrings(_o1,_o2);
    }

}
