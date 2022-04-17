package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.maths.litteralcom.StrTypes;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ElUtil {

    private ElUtil() {
    }

    public static CustList<PartOffsetAffect> getFieldNames(ResultExpression _res,int _valueOffset, String _el, Calculation _calcul, AnalyzedPageEl _page) {
        MethodAccessKind hiddenVarTypes_ = _calcul.getStaticBlock();
        _page.setAccessStaticContext(hiddenVarTypes_);
        _page.setCurrentAnonymousResults(_res.getAnonymousResults());
        _page.setCurrentParts(_res.getParts());
        Delimiters d_ = ElResolver.checkSyntaxQuick(_el, _page);
        CustList<PartOffsetAffect> names_ = new CustList<PartOffsetAffect>();
        if (d_.getBadOffset() >= 0) {
            return names_;
        }
        for (VariableInfo v: d_.getVariables()) {
            if (v.getDeclaringField() != null) {
                names_.add(new PartOffsetAffect(new FieldPartOffset(v.getName(),v.getFirstChar()+_valueOffset),v.isAffect()));
            }
        }
        return names_;
    }

    private static void setupStaticContext(MethodAccessKind _hiddenVarTypes, OperationNode _op, AnalyzedPageEl _page) {
        MethodAccessKind ctorAcc_;
        if (_op instanceof AbstractInvokingConstructor) {
            ctorAcc_ = MethodAccessKind.STATIC_CALL;
        } else {
            ctorAcc_ = MethodAccessKind.INSTANCE;
        }
        MethodAccessKind access_ = MethodId.getKind(_hiddenVarTypes,ctorAcc_);
        _page.setAccessStaticContext(access_);
    }

    public static OperationNode getRootAnalyzedOperationsReadOnly(ResultExpression _res, String _el, Calculation _calcul, AnalyzedPageEl _page) {
        MethodAccessKind hiddenVarTypes_ = _calcul.getStaticBlock();
        _page.setAccessStaticContext(hiddenVarTypes_);
        _page.setCurrentEmptyPartErr("");
        _page.setCurrentAnonymousResults(_res.getAnonymousResults());
        _page.setCurrentParts(_res.getParts());
        Delimiters d_ = ElResolver.checkSyntax(_el, IndexConstants.FIRST_INDEX, _page);
        int badOffset_ = d_.getBadOffset();
        if (_el.trim().isEmpty()) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(_page);
            //badOffset char
            badEl_.buildError(_page.getAnalysisMessages().getEmptyPart());
            _page.addLocError(badEl_);
            _page.setCurrentEmptyPartErr(badEl_.getBuiltError());
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(d_);
            ErrorPartOperation e_ = new ErrorPartOperation(0, 0, null, tmpOp_);
            String argClName_ = _page.getAliasObject();
            e_.setResultClass(new AnaClassArgumentMatching(argClName_));
            e_.setOrder(0);
            _res.setRoot(e_);
            return e_;
        }
        OperationNode op_;
        if (badOffset_ >= 0) {
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(d_);
            op_ = new ErrorPartOperation(0, 0, null, tmpOp_);
        } else {
            OperationsSequence opTwo_ = ElResolver.getOperationsSequence(IndexConstants.FIRST_INDEX, _el, d_, _page, null);
            op_ = OperationNode.createPossDeclOperationNode(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, opTwo_, _page);
        }
        setupStaticContext(hiddenVarTypes_, op_, _page);
        setSyntheticRoot(op_, _calcul);
        getSortedDescNodesReadOnly(op_, _calcul, _page);
        _res.setRoot(op_);
        return op_;
    }


    public static CustList<OperationNode> getAnalyzedOperationsQucikly(ResultExpression _res,String _el, Calculation _calcul, AnalyzedPageEl _page) {
        MethodAccessKind hiddenVarTypes_ = _calcul.getStaticBlock();
        _page.setAccessStaticContext(hiddenVarTypes_);
        _page.setCurrentAnonymousResults(_res.getAnonymousResults());
        _page.setCurrentParts(_res.getParts());
        _page.setCurrentParts(_page.getCurrentParts());
        Delimiters d_ = ElResolver.checkSyntax(_el, IndexConstants.FIRST_INDEX, _page);
        int badOffset_ = d_.getBadOffset();
        if (_el.trim().isEmpty()) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(_page);
            //badOffset char
            badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                    " ",
                    Long.toString(badOffset_),
                    _el);
            _page.addLocError(badEl_);
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(d_);
            ErrorPartOperation e_ = new ErrorPartOperation(0, 0, null, tmpOp_);
            String argClName_ = _page.getAliasObject();
            e_.setResultClass(new AnaClassArgumentMatching(argClName_));
            e_.setOrder(0);
            return new CustList<OperationNode>(e_);
        }
        OperationNode op_;
        if (badOffset_ >= 0) {
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(d_);
            op_ = new ErrorPartOperation(0, 0, null, tmpOp_);
        } else {
            OperationsSequence opTwo_ = ElResolver.getOperationsSequence(IndexConstants.FIRST_INDEX, _el, d_, _page, null);
            op_ = OperationNode.createPossDeclOperationNode(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, opTwo_, _page);
        }
        return getSortedDescNodesReadOnly(op_, _calcul, _page);
    }

    private static void setSyntheticRoot(OperationNode _op, Calculation _calc) {
        if (_op instanceof AffectationOperation && _calc.hasFieldName()) {
            ((AffectationOperation) _op).setSynthetic(true);
        }
    }


    private static CustList<OperationNode> getSortedDescNodesReadOnly(OperationNode _root, Calculation _calc, AnalyzedPageEl _page) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode c_ = _root;
        while (c_ != null) {
            preAnalyze(c_, _page);
            c_ = getAnalyzedNextReadOnly(c_, _root, list_,_calc, _page);
        }
        return list_;
    }

    private static void preAnalyze(OperationNode _c, AnalyzedPageEl _page) {
        if (_c instanceof PreAnalyzableOperation) {
            ((PreAnalyzableOperation) _c).preAnalyze(_page);
        }
    }

    private static void processDot(OperationNode _next, OperationNode _current, MethodOperation _par, AnalyzedPageEl _page) {
        if (_par instanceof AbstractDotOperation) {
            if (!(_next instanceof PossibleIntermediateDotted)) {
                return;
            }
            PossibleIntermediateDotted possible_ = (PossibleIntermediateDotted) _next;
            check(_current, possible_, _page);
            if (_current instanceof StaticCallAccessOperation){
                possible_.setIntermediateDotted();
                MethodAccessKind access_ = MethodAccessKind.STATIC_CALL;
                if (!(_next instanceof LambdaOperation) && ((StaticCallAccessOperation)_current).isImplicit() && _page.getStaticContext() == MethodAccessKind.STATIC) {
                    access_ = MethodAccessKind.STATIC;
                }
                possible_.setPreviousResultClass(_current.getResultClass(), access_);

            } else {
                MethodAccessKind static_ = MethodId.getKind(_current instanceof StaticAccessOperation);
                possible_.setIntermediateDotted();
                possible_.setPreviousResultClass(_current.getResultClass(), static_);

            }
        }
    }

    public static void check(OperationNode _current, PossibleIntermediateDotted _next, AnalyzedPageEl _page) {
        if (_current instanceof StaticCallAccessOperation&&!(_next instanceof LambdaOperation)) {
            ((StaticCallAccessOperation)_current).check(_page);
        }
    }

    public static ClassField tryGetAccess(OperationNode _op) {
        OperationNode op_ = _op;
        if (op_ instanceof DotOperation) {
            op_ = ((DotOperation)op_).getChildrenNodes().last();
        }
        if (op_ instanceof SettableFieldOperation) {
            SettableFieldOperation set_ = (SettableFieldOperation) op_;
            RootBlock fieldType_ = set_.getFieldType();
            if (fieldType_ instanceof EnumBlock) {
                for (InnerTypeOrElement f:((EnumBlock)fieldType_).getEnumBlocks()) {
                    if (StringUtil.contains(f.getFieldName(),set_.getFieldName())) {
                        return new ClassField(set_.getResultClass().getSingleNameOrEmpty(), set_.getFieldName());
                    }
                }
            }
        }
        return null;
    }
    private static OperationNode getAnalyzedNextReadOnly(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, Calculation _calc, AnalyzedPageEl _page) {
        OperationNode next_ = create(_calc, _page, _current, 0);
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            return next_;
        }
        OperationNode current_ = _current;
        while (true) {
            _page.setOkNumOp(true);
            retrieveErrorsAnalyze(current_, _page);
            current_.setOrder(_sortedNodes.size());
            _sortedNodes.add(current_);
            next_ = create(_calc, _page, current_.getParent(), current_.getIndexChild() + 1);
            MethodOperation par_ = current_.getParent();
            if (next_ != null) {
                processDot(next_, current_, par_, _page);
                par_.appendChild(next_);
                return next_;
            }
            if (par_ == _root) {
                _page.setOkNumOp(true);
                retrieveErrorsAnalyze(par_, _page);
                unwrapPrimitive(par_, _page);
                par_.setOrder(_sortedNodes.size());
                _sortedNodes.add(par_);
                return null;
            }
            if (par_ == null) {
                return null;
            }
            current_ = par_;
        }
    }

    private static void unwrapPrimitive(MethodOperation _par, AnalyzedPageEl _page) {
        AnaClassArgumentMatching cl_ = _par.getResultClass();
        if (AnaTypeUtil.isPrimitive(cl_, _page)) {
            cl_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
        }
    }

    public static void retrieveErrorsAnalyze(OperationNode _current, AnalyzedPageEl _page) {
        analyzeInfer(_current, _page);
        AbsBk currentBlock_ = _page.getCurrentBlock();
        if (currentBlock_ instanceof FieldBlock) {
            MethodOperation parent_ = _current.getParent();
            if (parent_ instanceof DeclaringOperation) {
                if (!(_current instanceof DeclaredFieldOperation)
                        &&!(_current instanceof AffectationOperation)) {
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFile(_page.getCurrentFile());
                    b_.setIndexFile(_page);
                    b_.buildError(_page.getAnalysisMessages().getNotRetrievedFields());
                    _page.addLocError(b_);
                    _current.addErr(b_.getBuiltError());
                }
            } else {
                if (parent_ instanceof AffectationOperation && parent_.getFirstChild() == _current && (parent_.getParent() == null || parent_.getParent() instanceof DeclaringOperation) && !(_current instanceof DeclaredFieldOperation)) {
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFile(_page.getCurrentFile());
                    b_.setIndexFile(_page);
                    b_.buildError(_page.getAnalysisMessages().getNotRetrievedFields());
                    _page.addLocError(b_);
                    _current.addErr(b_.getBuiltError());
                }
            }
        }
        mergeDotted(_current);
    }

    private static void mergeDotted(OperationNode _current) {
        if (_current instanceof AbstractDotOperation) {
            OperationNode last_ = ((AbstractDotOperation) _current).getChildrenNodes().last();
            if (last_ instanceof ArrOperation && _current.getOperations().getOperators().firstValue().isEmpty()) {
                last_.mergeErrs(_current);
            }
        }
    }

    public static void analyzeInfer(OperationNode _current, AnalyzedPageEl _page) {
        _current.analyze(_page);
        InvokingOperation.tryInfer(_current, _page);
    }

    private static OperationNode create(Calculation _calc, AnalyzedPageEl _page, OperationNode _pa, int _del) {
        if (!(_pa instanceof MethodOperation)) {
            return null;
        }
        MethodOperation p_ = (MethodOperation) _pa;
        StrTypes children_ = p_.getChildren();
        if (_del >= children_.size()) {
            return null;
        }
        String value_ = children_.getValue(_del);
        Delimiters d_ = p_.getOperations().getDelimiter();
        int curKey_ = children_.getKey(_del);
        int offset_ = p_.getIndexInEl()+curKey_;
        OperationsSequence r_ = ElResolver.getOperationsSequence(offset_, value_, d_, _page, p_);
        OperationNode op_ = OperationNode.createOperationNode(offset_, _del, p_, r_, _page);
        setFieldName(_calc, p_, op_);
        return op_;
    }

    private static void setFieldName(Calculation _calc, MethodOperation _p, OperationNode _c) {
        if (_c instanceof StandardInstancingOperation && _p instanceof AffectationOperation && ((AffectationOperation)_p).isSynthetic()) {
            ((StandardInstancingOperation) _c).fieldName(_calc);
        }
    }

    public static boolean isDeclaringVariable(MethodOperation _par) {
        if (_par == null) {
            return true;
        }
        if (_par instanceof DeclaringOperation) {
            return true;
        }
        if (_par instanceof AffectationOperation) {
            if (_par.getParent() == null) {
                return null == _par.getFirstChild();
            }
            if (_par.getParent() instanceof DeclaringOperation) {
                return null == _par.getFirstChild();
            }
        }
        return false;
    }

    public static boolean checkFinalFieldReadOnly(SettableFieldOperation _cst, StringMap<BoolVal> _ass, AnalyzedPageEl _page) {
        boolean fromCurClass_ = _cst.isFromCurrentClassReadOnly(_page);
        ClassField cl_ = _cst.getFieldIdReadOnly();
        String fieldName_ = cl_.getFieldName();
        return _cst.getSettableFieldContent().isFinalField() && checkFinalReadOnly(_cst.getSettableFieldContent().isStaticField(), _ass, fromCurClass_, fieldName_, _page);
    }
    private static boolean checkFinalReadOnly(boolean _staticField, StringMap<BoolVal> _ass, boolean _fromCurClass, String _fieldName, AnalyzedPageEl _page) {
        if (_page.isAssignedFields()) {
            return true;
        }
        if (_page.isAssignedStaticFields()) {
            if (_staticField || !_fromCurClass) {
                return true;
            }
            return chFinal(_ass, _fieldName);
        }
        if (!_fromCurClass) {
            return true;
        }
        return chFinal(_ass, _fieldName);
    }

    private static boolean chFinal(StringMap<BoolVal> _ass, String _fieldName) {
        boolean checkFinal_ = false;
        for (EntryCust<String, BoolVal> e: _ass.entryList()) {
            if (!StringUtil.quickEq(e.getKey(), _fieldName) || e.getValue() == BoolVal.TRUE) {
                continue;
            }
            checkFinal_ = true;
        }
        return checkFinal_;
    }

}
