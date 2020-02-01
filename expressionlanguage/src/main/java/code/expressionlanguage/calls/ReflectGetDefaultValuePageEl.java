package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AnnotationBlock;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class ReflectGetDefaultValuePageEl extends AbstractReflectPageEl {

    private boolean init;
    private CustList<ExecOperationNode> ops;

    @Override
    public boolean checkCondition(ContextEl _context) {
        MethodMetaInfo instance_ = (MethodMetaInfo) getGlobalArgument().getStruct();
        String cl_ = instance_.getFormClassName();
        String id_ = Templates.getIdFromAllTypes(cl_);
        GeneType type_ = _context.getClassBody(id_);
        if (!(type_ instanceof AnnotationBlock)) {
            setReturnedArgument(new Argument());
            return true;
        }
        if (!init) {
            String name_ = instance_.getName();
            AnnotationBlock ann_ = (AnnotationBlock) type_;
            for (AnnotationMethodBlock m: ContextEl.getAnnotationMethods(ann_)) {
                if (!StringList.quickEq(m.getName(), name_)) {
                    continue;
                }
                ops = m.getOpValue();
                if (ops.isEmpty()) {
                    String clMethod_ = m.getImportedReturnType();
                    Struct value_ = PrimitiveTypeUtil.defaultValue(clMethod_, _context);
                    Argument out_ = new Argument();
                    out_.setStruct(value_);
                    setReturnedArgument(out_);
                    return true;
                }
            }
            init = true;
        }
        ExpressionLanguage el_ = getCurrentEl(0,ops);
        Argument ret_ = el_.calculateMember(_context);
        if (_context.callsOrException()) {
            return false;
        }
        clearCurrentEls();
        setReturnedArgument(ret_);
        return true;
    }

    @Override
    public boolean receive(Argument _argument, ContextEl _context) {
        return basicReceive(_argument,_context);
    }

}
