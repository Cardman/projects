package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;

import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.util.*;

public final class AffectationOperation extends MethodOperation {

    private OperationNode settableOp;

    private boolean synthetic;

    private int opOffset;
    private int foundOffset;

    public AffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    public void setSynthetic(boolean synthetic) {
        this.synthetic = synthetic;
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
        opOffset = getOperations().getOperators().firstKey();
    }

    @Override
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode root_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        SettableElResult elt_ = tryGetSettable(this);
        boolean ok_ = elt_ != null;
        LgNames stds_ = _conf.getStandards();
        if (!ok_) {
            setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //oper
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedAffect(),
                    "=");
            _conf.getAnalyzing().getLocalizer().addError(un_);
            IntTreeMap< String> ops_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _conf);
            int opLocat_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",opLocat_));
            err_.add(new PartOffset("</a>",opLocat_+1));
            getPartOffsetsChildren().add(err_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (elt_ instanceof VariableOperation) {
            VariableOperation v_ = (VariableOperation)elt_;
            settableOp = v_;
            String inf_ = v_.getVariableName();
            if (ElUtil.isDeclaringVariable(v_, _conf) && StringList.contains(_conf.getAnalyzing().getVariablesNamesToInfer(), inf_)) {
                ClassArgumentMatching clMatchRight_ = right_.getResultClass();
                String type_ = clMatchRight_.getSingleNameOrEmpty();
                if (!type_.isEmpty()) {
                    ClassArgumentMatching n_ = new ClassArgumentMatching(type_);
                    AnaLocalVariable lv_ = _conf.getAnalyzing().getInfosVars().getVal(inf_);
                    lv_.setClassName(type_);
                    _conf.getAnalyzing().getVariablesNamesToInfer().removeString(inf_);
                    _conf.getAnalyzing().getLocalDeclaring().setupDeclaratorClass(type_);
                    _conf.getAnalyzing().setCurrentVarSetting(type_);
                    v_.setResultClass(n_);
                }
            }
        }
        if (elt_ instanceof MutableLoopVariableOperation) {
            MutableLoopVariableOperation v_ = (MutableLoopVariableOperation)elt_;
            settableOp = v_;
            String inf_ = v_.getVariableName();
            if (ElUtil.isDeclaringLoopVariable(v_, _conf) && StringList.contains(_conf.getAnalyzing().getVariablesNamesToInfer(), inf_)) {
                ClassArgumentMatching clMatchRight_ = right_.getResultClass();
                String type_ = clMatchRight_.getSingleNameOrEmpty();
                if (!type_.isEmpty()) {
                    ClassArgumentMatching n_ = new ClassArgumentMatching(type_);
                    AnaLocalVariable lv_ = _conf.getAnalyzing().getInfosVars().getVal(inf_);
                    lv_.setClassName(type_);
                    _conf.getAnalyzing().getVariablesNamesToInfer().removeString(inf_);
                    _conf.getAnalyzing().getLoopDeclaring().setupLoopDeclaratorClass(type_);
                    _conf.getAnalyzing().setCurrentVarSetting(type_);
                    v_.setResultClass(n_);
                }
            }
        }
        if (elt_ instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)elt_;
            settableOp = cst_;
            StringMap<Boolean> fieldsAfterLast_ = _conf.getAnalyzing().getDeclaredAssignments();
            if (!synthetic&&ElUtil.checkFinalFieldReadOnly(_conf, cst_, fieldsAfterLast_)) {
                setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _conf);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //field name len
                un_.buildError(_conf.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                _conf.getAnalyzing().getLocalizer().addError(un_);
                IntTreeMap< String> ops_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _conf);
                int opLocat_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+1));
                getPartOffsetsChildren().add(err_);
                setResultClass(ClassArgumentMatching.copy(elt_.getResultClass()));
                elt_.setVariable(true);
                return;
            }
        }

        setResultClass(ClassArgumentMatching.copy(elt_.getResultClass()));
        elt_.setVariable(true);
        ClassArgumentMatching clMatchRight_ = right_.getResultClass();
        ClassArgumentMatching clMatchLeft_ = elt_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);

        if (clMatchRight_.isVariable()) {
            if (!clMatchLeft_.isPrimitive(_conf)) {
                return;
            }
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //oper
            cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(clMatchRight_.getNames(),"&"),
                    StringList.join(clMatchLeft_.getNames(),"&"));
            _conf.getAnalyzing().getLocalizer().addError(cast_);
            IntTreeMap< String> ops_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _conf);
            int opLocat_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
            err_.add(new PartOffset("</a>",opLocat_+1));
            getPartOffsetsChildren().add(err_);
            return;
        }
        StringMap<StringList> vars_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(clMatchRight_);
        mapping_.setParam(clMatchLeft_);
        if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
            ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_conf, clMatchLeft_.getSingleNameOrEmpty(), clMatchRight_);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                clMatchRight_.getImplicits().add(cl_);
                clMatchRight_.setRootNumber(res_.getRootNumber());
                clMatchRight_.setMemberNumber(res_.getMemberNumber());
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //oper
                cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(clMatchRight_.getNames(),"&"),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                _conf.getAnalyzing().getLocalizer().addError(cast_);
                IntTreeMap< String> ops_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _conf);
                int opLocat_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+1));
                getPartOffsetsChildren().add(err_);
            }
        }
        setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);
        IntTreeMap< String> ops_ = getOperations().getOperators();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _conf);
        foundOffset = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
        if (PrimitiveTypeUtil.isPrimitive(clMatchLeft_, _conf)) {
            right_.getResultClass().setUnwrapObject(clMatchLeft_);
            right_.cancelArgument();
        }
    }

    public static String processInfer(ContextEl _cont, String _import) {
        StringList vars_ = _cont.getAnalyzing().getVariablesNames();
        if (StringList.quickEq(_import,_cont.getKeyWords().getKeyWordVar())) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_cont.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_cont.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //'var' len
            un_.buildError(_cont.getAnalysisMessages().getUnassignedInferingType(),
                    _import,
                    StringList.join(vars_,"&"));
            _cont.getAnalyzing().getLocalizer().addError(un_);
            return un_.getBuiltError();
        }
        for (String v: _cont.getAnalyzing().getVariablesNamesToInfer()) {
            AnaLocalVariable lv_ = _cont.getAnalyzing().getInfosVars().getVal(v);
            lv_.setClassName(_import);
        }
        return "";
    }

    public static String processInferLoop(ContextEl _cont, String _import) {
        StringList vars_ = _cont.getAnalyzing().getVariablesNames();
        if (StringList.quickEq(_import,_cont.getKeyWords().getKeyWordVar())) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_cont.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_cont.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //'var' len
            un_.buildError(_cont.getAnalysisMessages().getUnassignedInferingType(),
                    _import,
                    StringList.join(vars_,"&"));
            _cont.getAnalyzing().getLocalizer().addError(un_);
            return un_.getBuiltError();
        }
        for (String v: _cont.getAnalyzing().getVariablesNamesToInfer()) {
            AnaLocalVariable lv_ = _cont.getAnalyzing().getInfosVars().getVal(v);
            lv_.setClassName(_import);
        }
        return "";
    }
    static SettableElResult tryGetSettable(MethodOperation _operation) {
        OperationNode root_ = getFirstToBeAnalyzed(_operation);
        SettableElResult elt_;
        if (!(root_ instanceof AbstractDotOperation)) {
            elt_ = castTo(root_);
        } else {
            OperationNode beforeLast_ = ((MethodOperation)root_).getChildrenNodes().last();
            elt_ = castTo(beforeLast_);
        }
        return elt_;
    }
    public static OperationNode getFirstToBeAnalyzed(MethodOperation _operation) {
        OperationNode root_ = _operation.getFirstChild();
        while (root_ instanceof IdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }
    private static SettableElResult castTo(OperationNode _op) {
        if (_op instanceof SettableElResult) {
            return (SettableElResult) _op;
        }
        return null;
    }
    @Override
    public void quickCalculate(ContextEl _conf) {
        setArg(_conf,this, getSettableOp());
    }

    public static void setArg(ContextEl _conf, MethodOperation _current, OperationNode _settable) {
        if (!ElUtil.isDeclaringField(_settable, _conf)) {
            return;
        }
        StandardFieldOperation fieldRef_ = (StandardFieldOperation) _settable;
        OperationNode lastChild_ = _current.getChildrenNodes().get(1);
        Argument value_ = lastChild_.getArgument();
        ClassField id_ = fieldRef_.getFieldIdReadOnly();
        Struct str_ = value_.getStruct();
        _conf.getClasses().initializeStaticField(id_, str_);
        _current.setSimpleArgument(value_);
    }

    public int getOpOffset() {
        return opOffset;
    }

    public OperationNode getSettableOp() {
        return settableOp;
    }

    public int getFoundOffset() {
        return foundOffset;
    }
}