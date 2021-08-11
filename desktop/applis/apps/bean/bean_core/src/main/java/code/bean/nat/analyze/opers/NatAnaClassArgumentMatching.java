package code.bean.nat.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.NumParsers;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatAnaClassArgumentMatching {

    private final StringList className = new StringList();

    public NatAnaClassArgumentMatching(String _className) {
        className.add(_className);
    }

    public NatAnaClassArgumentMatching(StringList _className) {
        className.addAllElts(_className);
    }

    public static NatAnaClassArgumentMatching copy(NatAnaClassArgumentMatching _copy) {
        return new NatAnaClassArgumentMatching(_copy.className);
    }

    public boolean matchVoid(AnalyzedPageEl _page) {
        StringList l_ = new StringList(_page.getAliasVoid());
        return StringUtil.equalsSet(className, l_);
    }

    public String getSingleNameOrEmpty() {
        return NumParsers.getSingleNameOrEmpty(className);
    }

    public StringList getNames() {
        return className;
    }

}
