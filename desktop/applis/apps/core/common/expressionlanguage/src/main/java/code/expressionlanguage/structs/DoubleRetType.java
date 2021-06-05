package code.expressionlanguage.structs;

import code.util.core.StringUtil;

public final class DoubleRetType implements AbsRetType {
    private final String ret;
    private final String gene;
    public DoubleRetType(String _ret,String _gene){
        ret = StringUtil.nullToEmpty(_ret);
        gene = StringUtil.nullToEmpty(_gene);
    }
    @Override
    public String retType() {
        return ret;
    }

    @Override
    public String retTypeGene() {
        return gene;
    }
}
