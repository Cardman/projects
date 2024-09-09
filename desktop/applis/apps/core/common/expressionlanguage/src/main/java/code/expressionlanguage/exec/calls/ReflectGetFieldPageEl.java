package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.IntParentRetriever;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ReflectGetFieldPageEl extends AbstractLambdaVariable {

    private final ReflectFieldContent content;

    public ReflectGetFieldPageEl(IntParentRetriever _i, int _anc, FieldMetaInfo _metaInfo, boolean _lambda) {
        this(new ReflectFieldContent(_i, _anc, _metaInfo),_lambda);
        setGlobalArgumentStruct(_metaInfo);
    }

    public ReflectGetFieldPageEl(ReflectFieldContent _cont, boolean _lambda) {
        super(_lambda, _cont);
        content = _cont;
    }
    @Override
    Struct calculate(ContextEl _context, StackCall _stack) {
        String baseClass_ = content.getMetaInfo().getFormatted().getFormatted();
        baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
        LgNames stds_ = _context.getStandards();
        if (stds_.getStandards().contains(baseClass_)) {
            String name_ = content.getMetaInfo().getName();
            ClassField id_ = new ClassField(baseClass_, name_);
            return stds_.getSimpleResult(id_);
        }
        Struct arg_ = ExecFieldTemplates.getField(content.getMetaInfo(), content.getIntParentRetriever().getParent(), _context, _stack);
        if (_context.callsOrException(_stack)) {
            return NullStruct.NULL_VALUE;
        }
        return arg_;
    }

    public Struct getArgument() {
        return content.getIntParentRetriever().getParent();
    }

    public ReflectFieldContent getContent() {
        return content;
    }
}
