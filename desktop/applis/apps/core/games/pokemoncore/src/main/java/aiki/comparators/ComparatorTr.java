package aiki.comparators;

import code.util.AbsMap;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public class ComparatorTr<E> implements Comparing<E> {

    private final AbsMap<E,String> translator;

    public ComparatorTr(AbsMap<E,String> _translator) {
        translator = _translator;
    }

    @Override
    public int compare(E _e1, E _e2) {
        return StringUtil.compareStrings(tr(_e1),tr(_e2));
    }
    protected String tr(E _e) {
        return StringUtil.nullToEmpty(translator.getVal(_e));
    }
}
