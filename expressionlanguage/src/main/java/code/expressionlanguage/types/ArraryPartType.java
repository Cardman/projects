package code.expressionlanguage.types;

import code.expressionlanguage.Templates;

public final class ArraryPartType extends ParentPartType {

    public ArraryPartType(ParentPartType _parent, int _index, int _indexInType) {
        super(_parent, _index, _indexInType);
    }

    @Override
    public String getBegin() {
        return Templates.ARR_BEG_STRING;
    }

    @Override
    public String getSeparator(int _index) {
        return EMPTY_STRING;
    }

    @Override
    public String getEnd() {
        return EMPTY_STRING;
    }

}
