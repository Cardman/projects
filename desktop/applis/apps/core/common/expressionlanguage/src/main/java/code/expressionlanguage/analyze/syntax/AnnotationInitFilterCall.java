package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AbsBk;

public final class AnnotationInitFilterCall {
    private AbsBk block;
    private String init="";
    private SrcFileLocation calleeRef;
    private SrcFileLocation callerRef;

    public AbsBk getBlock() {
        return block;
    }

    public void setBlock(AbsBk _b) {
        this.block = _b;
    }

    public String getInit() {
        return init;
    }

    public void setInit(String _i) {
        this.init = _i;
    }

    public SrcFileLocation getCalleeRef() {
        return calleeRef;
    }

    public void setCalleeRef(SrcFileLocation _c) {
        this.calleeRef = _c;
    }

    public SrcFileLocation getCallerRef() {
        return callerRef;
    }

    public void setCallerRef(SrcFileLocation _c) {
        this.callerRef = _c;
    }
}
