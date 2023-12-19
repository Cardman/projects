package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.types.SimpleSegType;

public abstract class AbsSrcFileLocationType extends AbsSrcFileLocation {
    private final SimpleSegType offset;

    protected AbsSrcFileLocationType(SimpleSegType _o) {
        this.offset = _o;
    }

    public int begin() {
        return getOffset().getBegin();
    }

    public SimpleSegType getOffset() {
        return offset;
    }
}
