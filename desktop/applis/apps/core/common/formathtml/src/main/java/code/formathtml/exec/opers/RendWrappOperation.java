package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.formathtml.Configuration;
import code.formathtml.SimplePageEl;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendWrappOperation extends RendAbstractUnaryOperation {
    public RendWrappOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        if (getFirstChild() instanceof RendStdRefVariableOperation) {
            RendStdRefVariableOperation ch_ = (RendStdRefVariableOperation) getFirstChild();
            String variableName_ = ch_.getVariableContent().getVariableName();
            AbstractWrapper val_ = _conf.getLastPage().getRefParams().getVal(variableName_);
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            pair_.setWrapper(val_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_nodes,_context);
            return;
        }
        if (getFirstChild() instanceof RendStdVariableOperation) {
            RendStdVariableOperation ch_ = (RendStdVariableOperation) getFirstChild();
            String variableName_ = ch_.getVariableContent().getVariableName();
            SimplePageEl ip_ = _conf.getPageEl();
            LocalVariable val_ = ip_.getValueVars().getVal(variableName_);
            VariableWrapper v_ = new VariableWrapper();
            val_ = ExecTemplates.local(val_);
            v_.setLocal(val_);
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            pair_.setWrapper(v_);
            setQuickNoConvertSimpleArgument(Argument.createVoid(),_nodes,_context);
            return;
        }
        RendDynOperationNode chFirst_ = getFirstChild();
        if (chFirst_ instanceof RendDotOperation) {
            chFirst_ = getLastNode((RendMethodOperation) chFirst_);
        }
        if (chFirst_ instanceof RendSettableFieldOperation) {
            RendSettableFieldOperation ch_ = (RendSettableFieldOperation)chFirst_;
            ExecSettableOperationContent settableFieldContent_ = ch_.getSettableFieldContent();
            FieldWrapper f_ = new FieldWrapper();
            f_.setFieldType(settableFieldContent_.getRealType());
            f_.setFinalField(settableFieldContent_.isFinalField());
            f_.setStaticField(settableFieldContent_.isStaticField());
            f_.setId(settableFieldContent_.getClassField());
            f_.setRootBlock(ch_.getRootBlock());
            Argument previous_;
            if (!settableFieldContent_.isStaticField()) {
                Argument previousArgument_ = getPreviousArg(ch_,_nodes, _conf);
                String className_ = settableFieldContent_.getClassField().getClassName();
                previous_ = new Argument(ExecTemplates.getParent(settableFieldContent_.getAnc(), className_, previousArgument_.getStruct(), _context));
            } else {
                previous_ = new Argument();
            }
            f_.setContainer(previous_.getStruct());
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            pair_.setWrapper(f_);
        }
        if (chFirst_ instanceof RendArrOperation) {
            RendArrOperation ch_ = (RendArrOperation)chFirst_;
            ArrayWrapper a_ = new ArrayWrapper();
            Argument previousArgument_ = getPreviousArg(ch_,_nodes, _conf);
            a_.setContainer(previousArgument_.getStruct());
            ArgumentsPair pairIndex_ = getArgumentPair(_nodes, ch_.getFirstChild());
            a_.setIndex(pairIndex_.getArgument().getStruct());
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            pair_.setWrapper(a_);
        }
        setQuickNoConvertSimpleArgument(Argument.createVoid(),_nodes,_context);
    }
}
