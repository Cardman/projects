package code.bean.nat;

import code.expressionlanguage.analyze.AbstractForEachFetch;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NativeForEachFetch implements AbstractForEachFetch {
    private final BeanNatLgNames stds;

    public NativeForEachFetch(BeanNatLgNames _stds) {
        this.stds = _stds;
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
        String type_ = StringUtil.concat(stds.getContent().getPredefTypes().getAliasIterableTable(), "<", _first, "," + _second + ">");
        return new IterableAnalysisResult(new StringList(type_));
    }

    private String getIterableFullTypeByStds(String _subType, String _first) {
        String it_ = stds.getIterables().getVal(_subType);
        it_ = unNulizz(it_, stds.getAliasObject());
        if (StringUtil.quickEq(it_, stds.getAliasObject())) {
            it_ = _first;
        }
        return StringUtil.concat(stds.getContent().getPredefTypes().getAliasIterable(),"<",it_,">");
    }

    public static String unNulizz(String _it, String _aliasObject) {
        String it_ = StringUtil.nullToEmpty(_it);
        if (it_.isEmpty()) {
            it_ = _aliasObject;
        }
        return it_;
    }
}
