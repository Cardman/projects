package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.AbsLineDeclarator;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class AffectationOperation extends MethodOperation {

    private OperationNode settableOp;

    private boolean synthetic;

    private final int opOffset;

    public AffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        opOffset = _op.getOperators().firstKey();
    }

    public boolean isSynthetic() {
        return synthetic;
    }

    public void setSynthetic(boolean _synthetic) {
        this.synthetic = _synthetic;
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        StrTypes ops_ = getOperators();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode right_ = chidren_.last();
        SettableElResult elt_ = tryGetSettable(this);
        AnaClassArgumentMatching clMatchLeftPoss_ = leftType(_page, elt_);
        if (!isLeftValue(elt_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
            //oper
            un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                    "=");
            _page.getLocalizer().addError(un_);
            getPartOffsetsChildren().add(new InfoErrorDto(un_,_page,1));
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
            clMatchLeftPoss_ = revolveVariable(_page, clMatchLeftPoss_, decl_, inf_);
            if (t_ == ConstType.REF_LOC_VAR) {
                checkRefVar(_page, clMatchLeftPoss_);
                return;
            }
        }
        if (elt_ instanceof SettableAbstractFieldOperation) {
            settableOp = (SettableAbstractFieldOperation)elt_;
        }
        if (elt_ instanceof SettableFieldOperation) {
            SettableFieldOperation cst_ = (SettableFieldOperation)elt_;
            StringMap<BoolVal> fieldsAfterLast_ = _page.getDeclaredAssignments();
            if (ElUtil.checkFinalFieldReadOnly(cst_, fieldsAfterLast_, _page)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                //field name len
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                _page.getLocalizer().addError(un_);
                getPartOffsetsChildren().add(new InfoErrorDto(un_,_page,1));
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
            cast_.setIndexFile(_page);
            //oper
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            getPartOffsetsChildren().add(new InfoErrorDto(cast_,_page,1));
            return;
        }
        CompoundAffectationOperation.convertRight(_page,1,clMatchLeft_,this);
        if (AnaTypeUtil.isPrimitive(clMatchLeft_, _page)) {
            right_.getResultClass().setUnwrapObject(clMatchLeft_, _page.getPrimitiveTypes());
        }
    }

    private AnaClassArgumentMatching leftType(AnalyzedPageEl _page, SettableElResult _elt) {
        AnaClassArgumentMatching clMatchLeftPoss_;
        if (_elt != null) {
            setResultClass(AnaClassArgumentMatching.copy(_elt.getResultClass(), _page.getPrimitiveTypes()));
            _elt.setVariable(true);
            clMatchLeftPoss_ = _elt.getResultClass();
        } else {
            clMatchLeftPoss_ = new AnaClassArgumentMatching("");
        }
        return clMatchLeftPoss_;
    }

    private void checkRefVar(AnalyzedPageEl _page, AnaClassArgumentMatching _clMatchLeftPoss) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        if (!_clMatchLeftPoss.matchClass(clMatchRight_)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page);
            //oper
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(_clMatchLeftPoss.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            getPartOffsetsChildren().add(new InfoErrorDto(cast_, _page,1));
        }
    }

    private AnaClassArgumentMatching revolveVariable(AnalyzedPageEl _page, AnaClassArgumentMatching _clMatchLeftPoss, AbsLineDeclarator _decl, String _inf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching clMatchLeftPoss_ = _clMatchLeftPoss;
        if (StringUtil.contains(_page.getVariablesNamesToInfer(), _inf)) {
            AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
            String type_ = clMatchRight_.getSingleNameOrEmpty();
            if (!type_.isEmpty()) {
                AnaClassArgumentMatching n_ = new AnaClassArgumentMatching(type_, _page.getPrimitiveTypes());
                AnaLocalVariable lv_ = _page.getInfosVars().getVal(_inf);
                lv_.setClassName(type_);
                _page.getVariablesNamesToInfer().removeString(_inf);
                _decl.setImportedClassName(type_);
                _page.setCurrentVarSetting(type_);
                settableOp.setResultClass(n_);
                clMatchLeftPoss_ = n_;
                setResultClass(AnaClassArgumentMatching.copy(n_, _page.getPrimitiveTypes()));
            }
        }
        return clMatchLeftPoss_;
    }

    public static String processInfer(String _import, AnalyzedPageEl _page) {
        StringList vars_ = _page.getVariablesNames();
        if (StringUtil.quickEq(_import, _page.getKeyWords().getKeyWordVar())) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
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

}