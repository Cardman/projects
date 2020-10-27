package code.util.comparators;
import code.util.core.StringUtil;
import code.util.ints.Comparing;
import code.util.ints.ListableEntries;

public final class ComparatorMapValue<T> implements Comparing<T> {

    private ListableEntries<T, String> map;

    public ComparatorMapValue(ListableEntries<T, String> _map) {
        map = _map;
    }

    @Override
    public int compare(T _o1, T _o2) {
        return StringUtil.compareStrings(map.getVal(_o1),map.getVal(_o2));
    }

}
