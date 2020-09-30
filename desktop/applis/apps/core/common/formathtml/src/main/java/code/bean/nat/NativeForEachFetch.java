package code.bean.nat;

import code.expressionlanguage.analyze.AbstractForEachFetch;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.util.StringList;

public final class NativeForEachFetch implements AbstractForEachFetch {
    private final BeanNatLgNames stds;

    public NativeForEachFetch(BeanNatLgNames stds) {
        this.stds = stds;
    }

    @Override
    public IterableAnalysisResult getCustomType(StringList _names, String _first) {
        StringList out_ = new StringList();
        for (String f: _names) {
            String type_ = getIterableFullTypeByStds(f,_first);
            out_.add(type_);
        }
        return new IterableAnalysisResult(out_);
    }

    @Override
    public IterableAnalysisResult getCustomTableType(StringList _names, String _first, String _second) {
        String type_ = StringList.concat(stds.getAliasIterableTable(), "<", _first, "," + _second + ">");
        return new IterableAnalysisResult(new StringList(type_));
    }

    private String getIterableFullTypeByStds(String _subType, String _first) {
        String it_ = stds.getIterables().getVal(_subType);
        if (it_ == null) {
            it_ = stds.getAliasObject();
        }
        if (StringList.quickEq(it_, stds.getAliasObject())) {
            it_ = _first;
        }
        return StringList.concat(stds.getAliasIterable(),"<",it_,">");
    }
}
