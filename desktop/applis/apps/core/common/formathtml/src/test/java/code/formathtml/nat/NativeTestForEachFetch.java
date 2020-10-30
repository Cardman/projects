package code.formathtml.nat;

import code.expressionlanguage.analyze.AbstractForEachFetch;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NativeTestForEachFetch implements AbstractForEachFetch {
    private final BeanTestNatLgNames stds;

    public NativeTestForEachFetch(BeanTestNatLgNames _stds) {
        this.stds = _stds;
    }

    @Override
    public IterableAnalysisResult getCustomType(StringList _names, String _first) {
        StringList out_ = new StringList();
        String type_ = getIterableFullTypeByStds(_first);
        out_.add(type_);
        return new IterableAnalysisResult(out_);
    }

    @Override
    public IterableAnalysisResult getCustomTableType(StringList _names, String _first, String _second) {
        String type_ = StringUtil.concat(stds.getContent().getPredefTypes().getAliasIterableTable(), "<", _first, "," + _second + ">");
        return new IterableAnalysisResult(new StringList(type_));
    }

    private String getIterableFullTypeByStds(String _first) {
        return StringUtil.concat(stds.getContent().getPredefTypes().getAliasIterable(),"<",_first,">");
    }
}
