package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;

import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.LgNames;
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
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode root_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        SettableElResult elt_ = tryGetSettable(this);
        boolean ok_ = elt_ != null;
        LgNames stds_ = _page.getStandards();
        if (!ok_) {
            setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //oper
            un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                    "=");
            _page.getLocalizer().addError(un_);
            IntTreeMap< String> ops_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
            int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",opLocat_));
            err_.add(new PartOffset("</a>",opLocat_+1));
            getPartOffsetsChildren().add(err_);
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (elt_ instanceof VariableOperation) {
            VariableOperation v_ = (VariableOperation)elt_;
            settableOp = v_;
            String inf_ = v_.getVariableName();
            if (ElUtil.isDeclaringVariable(v_, _page) && StringList.contains(_page.getVariablesNamesToInfer(), inf_)) {
                AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
                String type_ = clMatchRight_.getSingleNameOrEmpty();
                if (!type_.isEmpty()) {
                    AnaClassArgumentMatching n_ = new AnaClassArgumentMatching(type_, _page.getStandards());
                    AnaLocalVariable lv_ = _page.getInfosVars().getVal(inf_);
                    lv_.setClassName(type_);
                    _page.getVariablesNamesToInfer().removeString(inf_);
                    _page.getLocalDeclaring().setupDeclaratorClass(type_);
                    _page.setCurrentVarSetting(type_);
                    v_.setResultClass(n_);
                }
            }
        }
        if (elt_ instanceof MutableLoopVariableOperation) {
            MutableLoopVariableOperation v_ = (MutableLoopVariableOperation)elt_;
            settableOp = v_;
            String inf_ = v_.getVariableName();
            if (ElUtil.isDeclaringLoopVariable(v_, _page) && StringList.contains(_page.getVariablesNamesToInfer(), inf_)) {
                AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
                String type_ = clMatchRight_.getSingleNameOrEmpty();
                if (!type_.isEmpty()) {
                    AnaClassArgumentMatching n_ = new AnaClassArgumentMatching(type_, _page.getStandards());
                    AnaLocalVariable lv_ = _page.getInfosVars().getVal(inf_);
                    lv_.setClassName(type_);
                    _page.getVariablesNamesToInfer().removeString(inf_);
                    _page.getLoopDeclaring().setupLoopDeclaratorClass(type_);
                    _page.setCurrentVarSetting(type_);
                    v_.setResultClass(n_);
                }
            }
        }
        if (elt_ instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)elt_;
            settableOp = cst_;
            StringMap<Boolean> fieldsAfterLast_ = _page.getDeclaredAssignments();
            if (!synthetic&&ElUtil.checkFinalFieldReadOnly(cst_, fieldsAfterLast_, _page)) {
                setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //field name len
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                _page.getLocalizer().addError(un_);
                IntTreeMap< String> ops_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
                int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+1));
                getPartOffsetsChildren().add(err_);
                setResultClass(AnaClassArgumentMatching.copy(elt_.getResultClass(), _page.getStandards()));
                elt_.setVariable(true);
                return;
            }
        }

        setResultClass(AnaClassArgumentMatching.copy(elt_.getResultClass(), _page.getStandards()));
        elt_.setVariable(true);
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        AnaClassArgumentMatching clMatchLeft_ = elt_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _page);

        if (clMatchRight_.isVariable()) {
            if (!clMatchLeft_.isPrimitive(_page)) {
                return;
            }
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_page.getLocalizer().getCurrentFileName());
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //oper
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(clMatchRight_.getNames(),"&"),
                    StringList.join(clMatchLeft_.getNames(),"&"));
            _page.getLocalizer().addError(cast_);
            IntTreeMap< String> ops_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
            int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
            err_.add(new PartOffset("</a>",opLocat_+1));
            getPartOffsetsChildren().add(err_);
            return;
        }
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(clMatchRight_);
        mapping_.setParam(clMatchLeft_);
        if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
            ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(clMatchLeft_.getSingleNameOrEmpty(), clMatchRight_, _page);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                clMatchRight_.getImplicits().add(cl_);
                clMatchRight_.setRootNumber(res_.getRootNumber());
                clMatchRight_.setMemberNumber(res_.getMemberNumber());
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(clMatchRight_.getNames(),"&"),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                _page.getLocalizer().addError(cast_);
                IntTreeMap< String> ops_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
                int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+1));
                getPartOffsetsChildren().add(err_);
            }
        }
        setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _page);
        IntTreeMap< String> ops_ = getOperations().getOperators();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        foundOffset = _page.getLocalizer().getCurrentLocationIndex();
        if (AnaTypeUtil.isPrimitive(clMatchLeft_, _page)) {
            right_.getResultClass().setUnwrapObject(clMatchLeft_, _page.getStandards());
        }
    }

    public static String processInfer(String _import, AnalyzedPageEl _page) {
        return processInferLoop(_import, _page);
    }

    public static String processInferLoop(String _import, AnalyzedPageEl _page) {
        StringList vars_ = _page.getVariablesNames();
        if (StringList.quickEq(_import, _page.getKeyWords().getKeyWordVar())) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //'var' len
            un_.buildError(_page.getAnalysisMessages().getUnassignedInferingType(),
                    _import,
                    StringList.join(vars_,"&"));
            _page.getLocalizer().addError(un_);
            return un_.getBuiltError();
        }
        for (String v: _page.getVariablesNamesToInfer()) {
            AnaLocalVariable lv_ = _page.getInfosVars().getVal(v);
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