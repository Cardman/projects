package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.CastOperation;
import code.expressionlanguage.opers.ExplicitOperation;
import code.expressionlanguage.opers.LambdaOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
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
    public void quickCalculate(Analyzable _conf) {
        CastOperation.tryGetArg(this, _conf, className);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        Argument objArg_ = _arguments.first();
        String paramName_ = _conf.getOperationPageEl().formatVarType(className, _conf);
        wrapFct(paramName_,_arguments,_conf);
        Templates.checkObject(paramName_, objArg_, _conf);
        return objArg_;
    }
    public static void wrapFct(String _className,CustList<Argument> _arguments, ExecutableCode _conf) {
        Argument objArg_ = _arguments.first();
        if (ExplicitOperation.customCast(_className)) {
            String argCl_ = objArg_.getObjectClassName(_conf.getContextEl());
            String idArg_ = Templates.getIdFromAllTypes(argCl_);
            if (StringList.quickEq(idArg_, _conf.getStandards().getAliasFct())) {
                String id_ = Templates.getIdFromAllTypes(_className);
                GeneType r_ = _conf.getClassBody(id_);
                if (r_ instanceof InterfaceBlock && r_.isStaticType()) {
                    int instEltCount_ = 0;
                    StringList superType_ = new StringList(id_);
                    superType_.addAllElts(r_.getAllSuperTypes());
                    for (String i: superType_) {
                        for (Block b: Classes.getDirectChildren(_conf.getClasses().getClassBody(i))) {
                            if ((b instanceof FieldBlock)) {
                                if (((FieldBlock)b).isStaticField()) {
                                    continue;
                                }
                                instEltCount_++;
                            }
                            if (b instanceof InstanceBlock) {
                                instEltCount_++;
                            }
                            if (b instanceof ConstructorBlock) {
                                instEltCount_++;
                            }
                        }
                    }
                    CustList<ClassMethodId> functional_ = ((RootBlock) r_).getFunctional();
                    if (instEltCount_ == 0 && functional_.size() == 1) {
                        ClassMethodId clRealId_ = functional_.first();
                        MethodId realId_ = clRealId_.getConstraints();
                        String geneStr_ = r_.getGenericString();
                        MethodId idMeth_ = realId_.quickFormat(geneStr_, _conf);
                        String gene_ = clRealId_.getClassName();
                        String geneFor_ = Templates.quickFormat(_className,gene_,_conf);
                        String ret_ = Classes.getMethodBodiesById(_conf.getContextEl(),gene_, realId_).first().getImportedReturnType();
                        ret_ = Templates.quickFormat(geneStr_,ret_,_conf);
                        ClassMethodIdReturn parmMe_ = new ClassMethodIdReturn(true);
                        parmMe_.setId(new ClassMethodId(clRealId_.getClassName(),idMeth_));
                        parmMe_.setRealId(realId_);
                        parmMe_.setReturnType(ret_);
                        parmMe_.setRealClass(gene_);
                        String fctParam_ = LambdaOperation.formatReturn(EMPTY_STRING,_conf,false, parmMe_, false);
                        fctParam_ = Templates.quickFormat(geneFor_,fctParam_,_conf);
                        if (Templates.isCorrectExecute(argCl_,fctParam_,_conf)) {
                            AbstractFunctionalInstance struct_ = _conf.getStandards().newFunctionalInstance(_className,_conf.getContextEl());
                            struct_.setFunctional(objArg_.getStruct());
                            objArg_.setStruct(struct_);
                        }
                    }
                }
            }
        }
    }
}
