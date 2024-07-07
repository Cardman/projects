package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.IntParentRetriever;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class ReflectFieldContent {
    private boolean initClass;
    private final IntParentRetriever intParentRetriever;
    private final FieldMetaInfo metaInfo;
    private final int ancestor;
    public ReflectFieldContent(IntParentRetriever _i, int _anc, FieldMetaInfo _metaInfo) {
        intParentRetriever = _i;
        ancestor = _anc;
        metaInfo = _metaInfo;
    }
    public boolean hasToExit(ContextEl _context, StackCall _stack) {
        if (!initClass) {
            initClass = true;
            return metaInfo.isStaticField() && _context.getExiting().hasToExit(_stack, metaInfo.getFormatted().getRootBlock());
        }
        return false;
    }
    public boolean koParent(ContextEl _context, StackCall _stack) {
        return !intParentRetriever.retrieve(_context, _stack);
    }

    public IntParentRetriever getIntParentRetriever() {
        return intParentRetriever;
    }

    public Struct getOriginalInstance() {
        return intParentRetriever.getOriginalInstance();
    }
    public int getAncestor(){
        return ancestor;
    }
    public FieldMetaInfo getMetaInfo() {
        return metaInfo;
    }
}
