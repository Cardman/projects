package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecFunctionalInfo;
import code.expressionlanguage.functionid.IdentifiableUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.CastOperation;
import code.expressionlanguage.analyze.opers.ExplicitOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecCastOperation extends ExecAbstractUnaryOperation {

    private String className;
    private int offset;
    public ExecCastOperation(CastOperation _c) {
        super(_c);
        className = _c.getClassName();
        offset = _c.getOffset();
    }


    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        Argument objArg_ = new Argument();
        objArg_.setStruct(_arguments.first().getStruct());
        String paramName_ = _conf.getLastPage().formatVarType(className, _conf);
        wrapFct(paramName_,false, _conf, objArg_);
        ExecTemplates.checkObject(paramName_, objArg_, _conf);
        return objArg_;
    }
    public static void wrapFct(String _className, boolean _full, ContextEl _conf, Argument _objArg) {
        if (ExplicitOperation.customCast(_className)) {
            Struct str_ = _objArg.getStruct();
            if (str_ instanceof LambdaStruct) {
                String id_ = StringExpUtil.getIdFromAllTypes(_className);
                ExecRootBlock r_ = _conf.getClasses().getClassBody(id_);
                if (r_ instanceof ExecInterfaceBlock && r_.isStaticType()) {
                    int instEltCount_ = 0;
                    StringList superType_ = new StringList(id_);
                    superType_.addAllElts(r_.getAllSuperTypes());
                    for (String i: superType_) {
                        for (ExecBlock b: ExecBlock.getDirectChildren(_conf.getClasses().getClassBody(i))) {
                            if ((b instanceof ExecFieldBlock)) {
                                if (((ExecFieldBlock)b).isStaticField()) {
                                    continue;
                                }
                                instEltCount_++;
                            }
                            if (b instanceof ExecInstanceBlock) {
                                instEltCount_++;
                            }
                            if (b instanceof ExecConstructorBlock) {
                                instEltCount_++;
                            }
                        }
                    }
                    CustList<ExecFunctionalInfo> functional_ = r_.getFunctionalBodies();
                    if ((instEltCount_ == 0 || _full)&& functional_.size() == 1) {
                        ExecFunctionalInfo clRealId_ = functional_.first();
                        MethodId realId_ = clRealId_.getOverridableBlock().getId();
                        String geneStr_ = r_.getGenericString();
                        MethodId idMeth_ = realId_.quickFormat(r_,geneStr_);
                        String gene_ = clRealId_.getClassName();
                        String geneFor_ = ExecTemplates.quickFormat(r_,_className,gene_);
                        String ret_ = clRealId_.getOverridableBlock().getImportedReturnType();
                        ret_ = ExecTemplates.quickFormat(r_,geneStr_,ret_);
                        ClassMethodIdReturn parmMe_ = new ClassMethodIdReturn(true);
                        parmMe_.setId(new ClassMethodId(clRealId_.getClassName(),idMeth_));
                        parmMe_.setRealId(realId_);
                        parmMe_.setReturnType(ret_);
                        parmMe_.setRealClass(gene_);
                        String fctParam_ = formatReturn(EMPTY_STRING,_conf, parmMe_, false);
                        fctParam_ = ExecTemplates.quickFormat(r_,geneFor_,fctParam_);
                        String argCl_ = _objArg.getObjectClassName(_conf);
                        if (ExecTemplates.isCorrectExecute(argCl_,fctParam_,_conf)) {
                            if (_full) {
                                AbstractFunctionalInstance struct_ = _conf.getStandards().newFullFunctionalInstance(_className,r_,(LambdaStruct) str_,_conf);
                                _objArg.setStruct(struct_);
                            } else {
                                AbstractFunctionalInstance struct_ = _conf.getStandards().newFunctionalInstance(_className,r_,(LambdaStruct) str_,_conf);
                                _objArg.setStruct(struct_);
                            }
                        }
                    }
                }
            }
        }
    }
    public static String formatReturn(String _foundClass, ContextEl _an, ClassMethodIdReturn _id, boolean _demand) {
        LgNames stds_ = _an.getStandards();
        String fctBase_ = stds_.getAliasFct();
        String returnType_ = _id.getReturnType();
        StringList paramsReturn_ = new StringList();
        MethodId id_ = _id.getId().getConstraints();
        IdentifiableUtil.appendLeftPart(_foundClass, _demand, paramsReturn_, id_, _id.getRealId().isStaticMethod());
        paramsReturn_.add(returnType_);
        return StringList.concat(fctBase_, Templates.TEMPLATE_BEGIN, StringList.join(paramsReturn_, Templates.TEMPLATE_SEP), Templates.TEMPLATE_END);
    }
}
