package aiki.comparators;

import code.util.AbsMap;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public class ComparatorTr<E,F> implements Comparing<F> {

    private final AbsMap<E,String> translator;
    private final IntRetriever<F,E> retriever;

    public ComparatorTr(AbsMap<E,String> _translator, IntRetriever<F,E> _ret) {
        translator = _translator;
        retriever = _ret;
    }

    @Override
    public int compare(F _e1, F _e2) {
        return StringUtil.compareStrings(tr(retriever.retrieve(_e1)),tr(retriever.retrieve(_e2)));
    }
    protected String tr(E _e) {
        return StringUtil.nullToEmpty(translator.getVal(_e));
    }
}
