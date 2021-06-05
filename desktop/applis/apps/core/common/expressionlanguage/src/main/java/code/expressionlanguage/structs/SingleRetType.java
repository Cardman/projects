package code.expressionlanguage.structs;

import code.util.core.StringUtil;

public final class SingleRetType implements AbsRetType {
    private final String single;
    public SingleRetType(String _single){
        single = StringUtil.nullToEmpty(_single);
    }
    @Override
    public String retType() {
        return single;
    }

    @Override
    public String retTypeGene() {
        return single;
    }
}
