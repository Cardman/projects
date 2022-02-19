package code.expressionlanguage.common;

import code.util.StringList;
import code.util.core.StringUtil;

public final class OthTypePairHash implements AbstractTypePairHash{

    @Override
    public boolean areTypePairs(Matching _m, Matching _n) {
        return StringUtil.quickEq(hcode(_m.getArg()),hcode(_n.getArg()))
                &&StringUtil.quickEq(hcode(_m.getParam()),hcode(_n.getParam()));
    }

    public static String hcode(String _type){
        StringList parts_ = StringExpUtil.getAllTypes(_type);
        int size_ = parts_.size();
        String id_ = parts_.first();
        StringList tr_ = new StringList();
        for (int i = 1; i < size_; i++) {
            tr_.add(StringExpUtil.getIdFromAllTypes(parts_.get(i)));
        }
        return id_+"<"+StringUtil.join(tr_,",")+">";
    }
}
