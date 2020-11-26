package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.FieldBlock;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ElUtil {

    private ElUtil() {
    }

    public static CustList<PartOffsetAffect> getFieldNames(int _valueOffset, String _el, Calculation _calcul, AnalyzedPageEl _page) {
        MethodAccessKind hiddenVarTypes_ = _calcul.getStaticBlock();
        _page.setAccessStaticContext(hiddenVarTypes_);
        Delimiters d_ = ElResolver.checkSyntaxQuick(_el, _page);
        CustList<PartOffsetAffect> names_ = new CustList<PartOffsetAffect>();
        if (d_.getBadOffset() >= 0) {
            return names_;
        }
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(IndexConstants.FIRST_INDEX, _el, d_, _page);
        if (opTwo_.getOperators().isEmpty()) {
            for (EntryCust<Integer,String> e: opTwo_.getValues().entryList()) {
                String var_ = e.getValue();
                String trimmed_ = var_.trim();
                String name_ = getFieldName(trimmed_);
                int offset_ = _valueOffset + e.getKey();
                addPart(names_, var_, trimmed_, name_, offset_, false);
            }
            return names_;
        }
        if (opTwo_.getPriority() == ElResolver.DECL_PRIO) {
            for (EntryCust<Integer,String> e: opTwo_.getValues().entryList()) {
                String var_ = e.getValue();
                String trimmed_ = var_.trim();
                String name_ = getFieldName(trimmed_);
                int offset_ = _valueOffset + e.getKey();
                if (StringExpUtil.isTypeLeafPart(trimmed_)) {
                    addFieldName(names_, var_, offset_, false, name_);
                    continue;
                }
                String afterName_ = trimmed_.substring(name_.length()).trim();
                if (ElResolver.isPureAffectation(afterName_,afterName_.length())) {
                    addFieldName(names_, var_, offset_, true, name_);
                }
            }
            return names_;
        }
        if (opTwo_.getPriority() == ElResolver.AFF_PRIO
                && StringUtil.quickEq(opTwo_.getOperators().firstValue(),"=")) {
            String var_ = opTwo_.getValues().firstValue();
            int off_ = opTwo_.getValues().firstKey();
            String trimmed_ = var_.trim();
            String name_ = getFieldName(trimmed_);
            addPart(names_, var_, trimmed_, name_, _valueOffset + off_, true);
        }
        return names_;
    }

    private static void addPart(CustList<PartOffsetAffect> _names, String _var, String _trimmed, String _name, int _i, boolean _b) {
        if (StringExpUtil.isTypeLeafPart(_trimmed)) {
            addFieldName(_names, _var, _i, _b, _name);
        }
    }

    private static void addFieldName(CustList<PartOffsetAffect> _list, String _name, int _offset, boolean _aff, String _op) {
        int delta_ = StringUtil.getFirstPrintableCharIndex(_name);
        _list.add(new PartOffsetAffect(new PartOffset(_op,delta_+_offset),_aff, new StringList()));
    }

    private static String getFieldName(String _v) {
        int k_ = 0;
        int lenField_ = _v.length();
        StringBuilder fieldName_ = new StringBuilder();
        while (k_ < lenField_) {
            char fieldChar_ = _v.charAt(k_);
            if (!StringExpUtil.isTypeLeafChar(fieldChar_)) {
                break;
            }
            fieldName_.append(fieldChar_);
            k_++;
        }
        return fieldName_.toString();
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

    public static OperationNode getRootAnalyzedOperationsReadOnly(String _el, Calculation _calcul, AnalyzedPageEl _page) {
        MethodAccessKind hiddenVarTypes_ = _calcul.getStaticBlock();
        _page.setAccessStaticContext(hiddenVarTypes_);
        _page.setCurrentEmptyPartErr("");
        Delimiters d_ = ElResolver.checkSyntax(_el, IndexConstants.FIRST_INDEX, _page);
        int badOffset_ = d_.getBadOffset();
        if (_el.trim().isEmpty()) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_page.getLocalizer().getCurrentFileName());
            badEl_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
            return e_;
        }
        OperationNode op_;
        if (badOffset_ >= 0) {
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(d_);
            op_ = new ErrorPartOperation(0, 0, null, tmpOp_);
        } else {
            OperationsSequence opTwo_ = ElResolver.getOperationsSequence(IndexConstants.FIRST_INDEX, _el, d_, _page);
            op_ = OperationNode.createOperationNode(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, null, opTwo_, _page);
        }
        String fieldName_ = _calcul.getFieldName();
        boolean hasFieldName_ = _calcul.isHasFieldName();
        setupStaticContext(hiddenVarTypes_, op_, _page);
        setSyntheticRoot(op_, hasFieldName_);
        getSortedDescNodesReadOnly(op_, fieldName_,hasFieldName_, _page);
        return op_;
    }


    public static CustList<OperationNode> getAnalyzedOperationsQucikly(String _el, Calculation _calcul, AnalyzedPageEl _page) {
        MethodAccessKind hiddenVarTypes_ = _calcul.getStaticBlock();
        _page.setAccessStaticContext(hiddenVarTypes_);
        Delimiters d_ = ElResolver.checkSyntax(_el, IndexConstants.FIRST_INDEX, _page);
        _page.getAnonymous().removeLast();
        _page.getAnonymousLambda().removeLast();
        int badOffset_ = d_.getBadOffset();
        if (_el.trim().isEmpty()) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_page.getLocalizer().getCurrentFileName());
            badEl_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
            OperationsSequence opTwo_ = ElResolver.getOperationsSequence(IndexConstants.FIRST_INDEX, _el, d_, _page);
            op_ = OperationNode.createOperationNode(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, null, opTwo_, _page);
        }
        String fieldName_ = _calcul.getFieldName();
        return getSortedDescNodesReadOnly(op_, fieldName_,false, _page);
    }

    private static void setSyntheticRoot(OperationNode _op, boolean _hasFieldName) {
        if (_op instanceof AffectationOperation && _hasFieldName) {
            ((AffectationOperation) _op).setSynthetic(true);
        }
    }

    private static void setFieldName(OperationNode _op, String _fieldName, boolean _hasFieldName) {
        if (_op instanceof StandardInstancingOperation) {
            ((StandardInstancingOperation) _op).setFieldName(_fieldName);
            ((StandardInstancingOperation) _op).setHasFieldName(_hasFieldName);
        }
    }


    private static CustList<OperationNode> getSortedDescNodesReadOnly(OperationNode _root, String _fieldName, boolean _hasFieldName, AnalyzedPageEl _page) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode c_ = _root;
        while (c_ != null) {
            preAnalyze(c_, _page);
            c_ = getAnalyzedNextReadOnly(c_, _root, list_, _fieldName,_hasFieldName, _page);
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
            if (_current instanceof StaticCallAccessOperation){
                PossibleIntermediateDotted possible_ = (PossibleIntermediateDotted) _next;
                possible_.setIntermediateDotted();
                MethodAccessKind access_ = MethodAccessKind.STATIC_CALL;
                if (!(_next instanceof LambdaOperation) && ((StaticCallAccessOperation)_current).isImplicit() && _page.getStaticContext() == MethodAccessKind.STATIC) {
                    access_ = MethodAccessKind.STATIC;
                }
                possible_.setPreviousResultClass(_current.getResultClass(), access_);

            } else {
                PossibleIntermediateDotted possible_ = (PossibleIntermediateDotted) _next;
                MethodAccessKind static_ = MethodId.getKind(_current instanceof StaticAccessOperation);
                possible_.setIntermediateDotted();
                possible_.setPreviousResultClass(_current.getResultClass(), static_);

            }
        }
    }

    private static OperationNode getAnalyzedNextReadOnly(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, String _fieldName, boolean _hasFieldName, AnalyzedPageEl _page) {
        OperationNode next_ = createFirstChild(_current, _fieldName,_hasFieldName, _page);
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
            next_ = processNext(current_,_fieldName,_hasFieldName, _page);
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
        _current.analyze(_page);
        Block currentBlock_ = _page.getCurrentBlock();
        if (currentBlock_ instanceof FieldBlock) {
            MethodOperation parent_ = _current.getParent();
            if (parent_ instanceof DeclaringOperation) {
                if (!(_current instanceof StandardFieldOperation)
                        &&!(_current instanceof AffectationOperation)) {
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(_page.getCurrentBlock().getFile().getFileName());
                    b_.setIndexFile(_page.getTraceIndex());
                    b_.buildError(_page.getAnalysisMessages().getNotRetrievedFields());
                    _page.addLocError(b_);
                    _current.addErr(b_.getBuiltError());
                }
            } else {
                if (parent_ instanceof AffectationOperation && parent_.getFirstChild() == _current && (parent_.getParent() == null ||parent_.getParent() instanceof DeclaringOperation)) {
                    if (!(_current instanceof StandardFieldOperation)) {
                        FoundErrorInterpret b_;
                        b_ = new FoundErrorInterpret();
                        b_.setFileName(_page.getCurrentBlock().getFile().getFileName());
                        b_.setIndexFile(_page.getTraceIndex());
                        b_.buildError(_page.getAnalysisMessages().getNotRetrievedFields());
                        _page.addLocError(b_);
                        _current.addErr(b_.getBuiltError());
                    }
                }
            }
        }
        if (_current instanceof AbstractDotOperation) {
            OperationNode last_ = ((AbstractDotOperation) _current).getChildrenNodes().last();
            if (last_ instanceof ArrOperation) {
                if (_current.getOperations().getOperators().firstValue().isEmpty()) {
                    last_.mergeErrs(_current);
                }
            }
        }
    }

    private static OperationNode processNext(OperationNode _current, String _fieldName, boolean _hasFieldName, AnalyzedPageEl _page) {
        return createNextSibling(_current, _fieldName,_hasFieldName, _page);
    }

    private static OperationNode createFirstChild(OperationNode _block, String _fieldName, boolean _hasFieldName, AnalyzedPageEl _page) {
        if (!(_block instanceof MethodOperation)) {
            return null;
        }
        MethodOperation block_ = (MethodOperation) _block;
        if (block_.getChildren().isEmpty()) {
            return null;
        }
        String value_ = block_.getChildren().getValue(0);
        Delimiters d_ = block_.getOperations().getDelimiter();
        int curKey_ = block_.getChildren().getKey(0);
        int offset_ = block_.getIndexInEl()+curKey_;
        OperationsSequence r_ = ElResolver.getOperationsSequence(offset_, value_, d_, _page);
        OperationNode op_ = OperationNode.createOperationNode(offset_, 0, block_, r_, _page);
        setFieldName(_fieldName, block_, op_,_hasFieldName);
        return op_;
    }

    private static OperationNode createNextSibling(OperationNode _block, String _fieldName, boolean _hasFieldName, AnalyzedPageEl _page) {
        MethodOperation p_ = _block.getParent();
        if (p_ == null) {
            return null;
        }
        IntTreeMap<String> children_ = p_.getChildren();
        int del_ = _block.getIndexChild() + 1;
        if (del_ >= children_.size()) {
            return null;
        }
        String value_ = children_.getValue(del_);
        Delimiters d_ = _block.getOperations().getDelimiter();
        int curKey_ = children_.getKey(del_);
        int offset_ = p_.getIndexInEl()+curKey_;
        OperationsSequence r_ = ElResolver.getOperationsSequence(offset_, value_, d_, _page);
        OperationNode op_ = OperationNode.createOperationNode(offset_, _block.getIndexChild() + 1, p_, r_, _page);
        setFieldName(_fieldName, p_, op_,_hasFieldName);
        return op_;
    }

    private static void setFieldName(String _fieldName, MethodOperation _p, OperationNode _c, boolean _hasFieldName) {
        if (_p instanceof AffectationOperation && _p.getParent() == null) {
            setFieldName(_c,_fieldName,_hasFieldName);
        }
    }

    public static CustList<OperationNode> filterInvoking(CustList<OperationNode> _list) {
        return _list;
    }

    public static boolean isDeclaringField(OperationNode _var, AnalyzedPageEl _page) {
        Block bl_ = _page.getCurrentBlock();
        if (!(bl_ instanceof FieldBlock)) {
            return false;
        }
        return isDeclaringField(_var);
    }

    public static boolean isDeclaringField(OperationNode _var) {
        if (!(_var instanceof StandardFieldOperation)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }

    public static boolean isDeclaringLoopVariable(MutableLoopVariableOperation _var, AnalyzedPageEl _page) {
        if (!isDeclaringLoopVariable(_page)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }
    public static boolean isDeclaringLoopVariable(MethodOperation _par, AnalyzedPageEl _page) {
        if (!isDeclaringLoopVariable(_page)) {
            return false;
        }
        return isDeclaringVariable(_par);
    }
    private static boolean isDeclaringLoopVariable(AnalyzedPageEl _page) {
        if (!_page.isMerged()) {
            return false;
        }
        if (!_page.getLoopDeclaring().hasLoopDeclarator()) {
            return false;
        }
        return _page.getForLoopPartState() == ForLoopPart.INIT;
    }
    public static boolean isDeclaringVariable(VariableOperation _var, AnalyzedPageEl _page) {
        if (!isDeclaringVariable(_page)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }
    public static boolean isDeclaringVariable(MethodOperation _par, AnalyzedPageEl _page) {
        if (!isDeclaringVariable(_page)) {
            return false;
        }
        return isDeclaringVariable(_par);
    }
    private static boolean isDeclaringVariable(AnalyzedPageEl _page) {
        if (!_page.isMerged()) {
            return false;
        }
        return _page.getLocalDeclaring().hasDeclarator();
    }
    private static boolean isDeclaringVariable(OperationNode _var) {
        MethodOperation par_ = _var.getParent();
        if (par_ == null) {
            return true;
        }
        if (par_ instanceof DeclaringOperation) {
            return true;
        }
        if (par_ instanceof AffectationOperation) {
            if (par_.getParent() == null) {
                return _var == par_.getFirstChild();
            }
            if (par_.getParent() instanceof DeclaringOperation) {
                return _var == par_.getFirstChild();
            }
        }
        return false;
    }
    private static boolean isDeclaringVariable(MethodOperation _par) {
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

    public static boolean checkFinalFieldReadOnly(SettableAbstractFieldOperation _cst, StringMap<Boolean> _ass, AnalyzedPageEl _page) {
        boolean fromCurClass_ = _cst.isFromCurrentClassReadOnly(_page);
        ClassField cl_ = _cst.getFieldIdReadOnly();
        String fieldName_ = cl_.getFieldName();
        FieldInfo meta_ = _page.getFieldFilter().getFieldInfo(_cst.getFieldType(), fieldName_);
        if (meta_ == null) {
            return false;
        }
        return meta_.isFinalField() && checkFinalReadOnly(_cst, _ass, fromCurClass_, fieldName_, meta_, _page);
    }
    private static boolean checkFinalReadOnly(SettableAbstractFieldOperation _cst, StringMap<Boolean> _ass, boolean _fromCurClass, String _fieldName, FieldInfo _meta, AnalyzedPageEl _page) {
        boolean checkFinal_;
        if (_page.isAssignedFields()) {
            checkFinal_ = true;
        } else if (_page.isAssignedStaticFields()) {
            if (_meta.isStaticField()) {
                checkFinal_ = true;
            } else if (!_fromCurClass) {
                checkFinal_ = true;
            } else {
                if (isDeclaringField(_cst, _page)) {
                    checkFinal_ = false;
                } else {
                    checkFinal_ = false;
                    for (EntryCust<String, Boolean> e: _ass.entryList()) {
                        if (!StringUtil.quickEq(e.getKey(), _fieldName)) {
                            continue;
                        }
                        if (e.getValue()) {
                            continue;
                        }
                        checkFinal_ = true;
                    }
                }
            }
        } else if (!_fromCurClass) {
            checkFinal_ = true;
        } else {
            if (isDeclaringField(_cst, _page)) {
                checkFinal_ = false;
            } else {
                checkFinal_ = false;
                for (EntryCust<String, Boolean> e: _ass.entryList()) {
                    if (!StringUtil.quickEq(e.getKey(), _fieldName)) {
                        continue;
                    }
                    if (e.getValue()) {
                        continue;
                    }
                    checkFinal_ = true;
                }
            }
        }
        return checkFinal_;
    }

}
