package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.AbsLineDeclarator;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AffectationOperation extends MethodOperation {

    private OperationNode settableOp;

    private boolean synthetic;

    private int opOffset;
    private int foundOffset;

    public AffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    public boolean isSynthetic() {
        return synthetic;
    }

    public void setSynthetic(boolean _synthetic) {
        this.synthetic = _synthetic;
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
        opOffset = getOperations().getOperators().firstKey();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        StrTypes ops_ = getOperations().getOperators();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode right_ = chidren_.last();
        SettableElResult elt_ = tryGetSettable(this);
        AnaClassArgumentMatching clMatchLeftPoss_;
        if (elt_ != null) {
            setResultClass(AnaClassArgumentMatching.copy(elt_.getResultClass(), _page.getPrimitiveTypes()));
            elt_.setVariable(true);
            clMatchLeftPoss_ = elt_.getResultClass();
        } else {
            clMatchLeftPoss_ = new AnaClassArgumentMatching("");
        }
        if (!isLeftValue(elt_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //oper
            un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                    "=");
            _page.getLocalizer().addError(un_);
            int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
            getPartOffsetsChildren().add(new InfoErrorDto(un_.getBuiltError(),opLocat_,1));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        AbsLineDeclarator decl_ = null;
        String inf_ = "";
        ConstType t_ = ConstType.NOTHING;
        if (elt_ instanceof VariableOperation) {
            settableOp = (VariableOperation)elt_;
            VariableOperation v_ = (VariableOperation)elt_;
            t_ = v_.getType();
            inf_ = v_.getVariableName();
            decl_ = v_.getLineDeclarator();
        }
        if (decl_ != null) {
            if (StringUtil.contains(_page.getVariablesNamesToInfer(), inf_)) {
                AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
                String type_ = clMatchRight_.getSingleNameOrEmpty();
                if (!type_.isEmpty()) {
                    AnaClassArgumentMatching n_ = new AnaClassArgumentMatching(type_, _page.getPrimitiveTypes());
                    AnaLocalVariable lv_ = _page.getInfosVars().getVal(inf_);
                    lv_.setClassName(type_);
                    _page.getVariablesNamesToInfer().removeString(inf_);
                    decl_.setImportedClassName(type_);
                    _page.setCurrentVarSetting(type_);
                    settableOp.setResultClass(n_);
                    clMatchLeftPoss_ = n_;
                    setResultClass(AnaClassArgumentMatching.copy(n_, _page.getPrimitiveTypes()));
                }
            }
            if (t_ == ConstType.REF_LOC_VAR) {
                AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
                if (!clMatchLeftPoss_.matchClass(clMatchRight_)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //oper
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                            StringUtil.join(clMatchLeftPoss_.getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(cast_);
                    int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
                    getPartOffsetsChildren().add(new InfoErrorDto(cast_.getBuiltError(),opLocat_,1));
                } else {
                    foundOffset = _page.getLocalizer().getCurrentLocationIndex();
                }
                return;
            }
        }
        if (elt_ instanceof SettableAbstractFieldOperation) {
            settableOp = (SettableAbstractFieldOperation)elt_;
        }
        if (elt_ instanceof SettableFieldOperation) {
            SettableFieldOperation cst_ = (SettableFieldOperation)elt_;
            StringMap<Boolean> fieldsAfterLast_ = _page.getDeclaredAssignments();
            if (ElUtil.checkFinalFieldReadOnly(cst_, fieldsAfterLast_, _page)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //field name len
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                _page.getLocalizer().addError(un_);
                int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
                getPartOffsetsChildren().add(new InfoErrorDto(un_.getBuiltError(),opLocat_,1));
                return;
            }
        }


        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        AnaClassArgumentMatching clMatchLeft_ = clMatchLeftPoss_;

        if (clMatchRight_.isVariable()) {
            if (!clMatchLeft_.isPrimitive(_page)) {
                return;
            }
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //oper
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
            getPartOffsetsChildren().add(new InfoErrorDto(cast_.getBuiltError(),opLocat_,1));
            return;
        }
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(clMatchRight_);
        mapping_.setParam(clMatchLeft_);
        if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
            ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(clMatchLeft_.getSingleNameOrEmpty(), clMatchRight_, _page);
            if (res_ != null) {
                clMatchRight_.implicitInfos(res_);
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
                getPartOffsetsChildren().add(new InfoErrorDto(cast_.getBuiltError(),opLocat_,1));
            }
        }
        foundOffset = _page.getLocalizer().getCurrentLocationIndex();
        if (AnaTypeUtil.isPrimitive(clMatchLeft_, _page)) {
            right_.getResultClass().setUnwrapObject(clMatchLeft_, _page.getPrimitiveTypes());
        }
    }

    public static String processInfer(String _import, AnalyzedPageEl _page) {
        StringList vars_ = _page.getVariablesNames();
        if (StringUtil.quickEq(_import, _page.getKeyWords().getKeyWordVar())) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //'var' len
            un_.buildError(_page.getAnalysisMessages().getUnassignedInferingType(),
                    _import,
                    StringUtil.join(vars_,ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(un_);
            return un_.getBuiltError();
        }
        for (String v: _page.getVariablesNamesToInfer()) {
            AnaLocalVariable lv_ = _page.getInfosVars().getVal(v);
            lv_.setClassName(_import);
        }
        return "";
    }
    public static SettableElResult tryGetSettable(MethodOperation _operation) {
        OperationNode root_ = getFirstToBeAnalyzed(_operation);
        return castDottedTo(root_);
    }
    public static SettableElResult tryGetCastSettable(MethodOperation _operation) {
        OperationNode root_ = getFirstCastToBeAnalyzed(_operation);
        return castDottedTo(root_);
    }
    public static SettableElResult tryGetSettableArg(OperationNode _operation) {
        OperationNode root_ = tryGetCastSettableArg(_operation);
        root_ = castId(root_);
        return castDottedTo(root_);
    }

    private static OperationNode tryGetCastSettableArg(OperationNode _operation) {
        OperationNode root_ = _operation;
        if (root_ instanceof NamedArgumentOperation) {
            root_ = root_.getFirstChild();
        }
        root_ = deepId(root_);
        return root_;
    }

    public static SettableElResult castDottedTo(OperationNode _root) {
        SettableElResult elt_;
        if (!(_root instanceof AbstractDotOperation)) {
            elt_ = castTo(_root);
        } else {
            OperationNode beforeLast_ = ((MethodOperation)_root).getChildrenNodes().last();
            elt_ = castTo(beforeLast_);
        }
        return elt_;
    }

    private static OperationNode getFirstCastToBeAnalyzed(MethodOperation _operation) {
        OperationNode root_ = getFirstToBeAnalyzed(_operation);
        return castId(root_);
    }

    private static OperationNode castId(OperationNode _root) {
        OperationNode root_ = _root;
        if (root_ instanceof CastOperation) {
            root_ = root_.getFirstChild();
        }
        return deepId(root_);
    }

    public static OperationNode getFirstToBeAnalyzed(MethodOperation _operation) {
        OperationNode root_ = _operation.getFirstChild();
        return deepId(root_);
    }
    private static OperationNode deepId(OperationNode _root) {
        OperationNode root_ = _root;
        while (stdId(root_)) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }

    private static boolean stdId(OperationNode _root) {
        return _root instanceof IdOperation&&((IdOperation) _root).isStandard();
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