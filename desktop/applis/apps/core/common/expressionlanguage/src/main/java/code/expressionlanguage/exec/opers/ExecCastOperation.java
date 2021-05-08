package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecFunctionalInfo;
import code.expressionlanguage.functionid.IdentifiableUtil;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

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
                String id_ = StringExpUtil.getIdFromAllTypes(_className);
                ExecRootBlock r_ = _conf.getClasses().getClassBody(id_);
                if (r_ instanceof ExecInterfaceBlock && r_.withoutInstance()) {
                    CustList<ExecFunctionalInfo> functional_ = r_.getFunctionalBodies();
                    if ((!r_.isWithInstanceElements() || _full)&& functional_.size() == 1) {
                        ExecFunctionalInfo clRealId_ = functional_.first();
                        ExecOverridableBlock overridableBlock_ = clRealId_.getOverridableBlock();
                        ExecRootBlock overridableBlockParent_ = clRealId_.getOverridableBlockParent();
                        MethodId realId_ = overridableBlock_.getId();
                        String gene_ = clRealId_.getClassName();
                        MethodId idMeth_ = realId_.quickFormat(overridableBlockParent_,gene_);
                        String geneFor_ = ExecInherits.quickFormat(r_,_className,gene_);
                        String ret_ = overridableBlock_.getImportedReturnType();
                        ret_ = ExecInherits.quickFormat(overridableBlockParent_,gene_,ret_);
                        String fctParam_ = formatReturn(_conf, ret_, idMeth_);
                        fctParam_ = ExecInherits.quickFormat(overridableBlockParent_,geneFor_,fctParam_);
                        String argCl_ = str_.getClassName(_conf);
                        if (ExecInherits.isCorrectExecute(argCl_,fctParam_,_conf)) {
                            AbstractFunctionalInstance struct_;
                            if (_full) {
                                struct_ = _conf.getStandards().newFullFunctionalInstance(_className, r_, (LambdaStruct) str_, overridableBlock_, _conf);
                            } else {
                                struct_ = _conf.getStandards().newFunctionalInstance(_className, r_, (LambdaStruct) str_, overridableBlock_, _conf);
                            }
                            _objArg.setStruct(struct_);
                        }
                    }
                }
            }
        }
    }
    private static String formatReturn(ContextEl _an, String _returnType, MethodId _shortId) {
        LgNames stds_ = _an.getStandards();
        String fctBase_ = stds_.getContent().getReflect().getAliasFct();
        StringList paramsReturn_ = new StringList();
        IdentifiableUtil.appendLeftPart(paramsReturn_, _shortId);
        if (_shortId.isRetRef()) {
            paramsReturn_.add("~"+_returnType);
        } else {
            paramsReturn_.add(_returnType);
        }
        return StringUtil.concat(fctBase_, StringExpUtil.TEMPLATE_BEGIN, StringUtil.join(paramsReturn_, StringExpUtil.TEMPLATE_SEP), StringExpUtil.TEMPLATE_END);
    }
}
