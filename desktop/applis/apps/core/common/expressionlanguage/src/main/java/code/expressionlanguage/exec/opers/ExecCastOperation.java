package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecFunctionalInfo;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCastOperation extends ExecAbstractUnaryOperation {

    private final ExecTypeCheckContent typeCheckContent;
    public ExecCastOperation(ExecOperationContent _opCont, ExecTypeCheckContent _typeCheckContent) {
        super(_opCont);
        typeCheckContent = _typeCheckContent;
    }


    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        setRelOffsetPossibleLastPage(typeCheckContent.getOffset(), _stack);
        Argument objArg_ = new Argument(ExecHelper.getFirstArgument(arguments_).getStruct());
        String paramName_ = _stack.formatVarType(typeCheckContent.getClassName());
        wrapFct(paramName_,false, _conf, objArg_);
        ExecTemplates.checkObject(paramName_, objArg_, _conf, _stack);
        setSimpleArgument(objArg_, _conf, _nodes, _stack);
    }

    public static void wrapFct(String _className, boolean _full, ContextEl _conf, Argument _objArg) {
        if (StringExpUtil.customCast(_className)) {
            Struct str_ = _objArg.getStruct();
            if (str_ instanceof LambdaStruct) {
                ExecFormattedRootBlock className_ = ExecFormattedRootBlock.build(_className, _conf.getClasses());
                ExecRootBlock r_ = className_.getRootBlock();
                if (r_ instanceof ExecInterfaceBlock && r_.withoutInstance()) {
                    CustList<ExecFunctionalInfo> functional_ = r_.getFunctionalBodies();
                    if ((!r_.isWithInstanceElements() || _full)&& functional_.size() == 1) {
                        ExecFunctionalInfo clRealId_ = functional_.first();
                        String fctParam_ = ExecInherits.quickFormat(className_, clRealId_.getFctParam());
                        String argCl_ = str_.getClassName(_conf);
                        if (ExecInherits.isCorrectExecute(argCl_,fctParam_,_conf)) {
                            ExecOverridableBlock overridableBlock_ = clRealId_.getOverridableBlock();
                            AbstractFunctionalInstance struct_;
                            if (_full) {
                                struct_ = _conf.getStandards().newFullFunctionalInstance(className_, r_, (LambdaStruct) str_, overridableBlock_, _conf);
                            } else {
                                struct_ = _conf.getStandards().newFunctionalInstance(className_, r_, (LambdaStruct) str_, overridableBlock_, _conf);
                            }
                            _objArg.setStruct(struct_);
                        }
                    }
                }
            }
        }
    }
}
