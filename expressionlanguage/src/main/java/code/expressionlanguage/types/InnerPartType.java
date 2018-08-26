package code.expressionlanguage.types;

import code.expressionlanguage.Templates;

public final class InnerPartType extends ParentPartType {

    private boolean removedBefore;

    public InnerPartType(ParentPartType _parent, int _index, int _indexInType, boolean _removedBefore) {
        super(_parent, _index, _indexInType);
        removedBefore = _removedBefore;
    }

    @Override
    public String getBegin() {
        if (removedBefore) {
            return Templates.INNER_TYPE;
        }
        return EMPTY_STRING;
    }

    @Override
    public String getSeparator(int _index) {
        return Templates.INNER_TYPE;
    }

    @Override
    public String getEnd() {
        return EMPTY_STRING;
    }

    public boolean isRemovedBefore() {
        return removedBefore;
    }
}
