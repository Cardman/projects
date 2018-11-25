package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.AnnotationBlock;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class ReflectGetDefaultValuePageEl extends AbstractReflectPageEl {

    private boolean init;
    private CustList<OperationNode> ops;

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
            for (GeneMethod m: Classes.getMethodBlocks(ann_)) {
                if (!(m instanceof AnnotationMethodBlock)) {
                    continue;
                }
                AnnotationMethodBlock a_ = (AnnotationMethodBlock) m;
                if (!StringList.quickEq(a_.getName(), name_)) {
                    continue;
                }
                ops = a_.getOpValue();
                if (ops.isEmpty()) {
                    String clMethod_ = a_.getImportedReturnType();
                    Struct value_ = PrimitiveTypeUtil.defaultValue(clMethod_, _context);
                    Argument out_ = new Argument();
                    out_.setStruct(value_);
                    setReturnedArgument(out_);
                    return true;
                }
            }
            init = true;
        }
        ExpressionLanguage el_;
        if (!isEmptyEl()) {
            el_ = getLastEl();
        } else {
            el_ = new ExpressionLanguage(ops);
            addCurrentEl(el_);
        }
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
        getLastEl().setArgument(_argument, _context);
        if (_context.isFailInit()) {
            return false;
        }
        return _context.processException();
    }

}
