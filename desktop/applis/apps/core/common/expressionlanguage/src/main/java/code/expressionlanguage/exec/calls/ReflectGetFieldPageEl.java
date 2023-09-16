package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.IntParentRetriever;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class ReflectGetFieldPageEl extends AbstractLambdaVariable {

    private boolean initClass;
    private final IntParentRetriever intParentRetriever;
    private final FieldMetaInfo metaInfo;
    private final int ancestor;

    public ReflectGetFieldPageEl(IntParentRetriever _i, int _anc, FieldMetaInfo _metaInfo, boolean _lambda) {
        super(_lambda);
        ancestor = _anc;
        intParentRetriever = _i;
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
    }

    @Override
    boolean hasToExit(ContextEl _context, StackCall _stack) {
        if (!initClass) {
            initClass = true;
            return metaInfo.isStaticField() && _context.getExiting().hasToExit(_stack, metaInfo.getFormatted().getRootBlock());
        }
        return false;
    }

    @Override
    Argument calculate(ContextEl _context, StackCall _stack) {
        String baseClass_ = metaInfo.getFormatted().getFormatted();
        baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
        LgNames stds_ = _context.getStandards();
        if (stds_.getStandards().contains(baseClass_)) {
            String name_ =metaInfo.getName();
            ClassField id_ = new ClassField(baseClass_, name_);
            return new Argument(stds_.getSimpleResult(id_));
        }
        Argument arg_ = ExecFieldTemplates.getField(metaInfo, ArgumentListCall.toStr(intParentRetriever.getParent()), _context, _stack);
        if (_context.callsOrException(_stack)) {
            return Argument.createVoid();
        }
        return arg_;
    }

    @Override
    protected boolean koParent(ContextEl _context, StackCall _stack) {
        return !intParentRetriever.retrieve(_context, _stack);
    }

    public Argument getArgument() {
        return ArgumentListCall.toStr(intParentRetriever.getParent());
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
