package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.AnonymousResult;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.KeyWords;
import code.maths.litteral.StrTypes;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class RenderAnalysis {
    private RenderAnalysis() {
    }

    public static OperationNode getRootAnalyzedOperationsDel(String _el, int _minIndex, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setCurrentAnonymousResults(new CustList<AnonymousResult>());
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(_el, _minIndex, _page);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_anaDoc.getFileName());
            badEl_.setIndexFile(badOffset_);
            badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                    " ",
                    Long.toString(badOffset_),
                    _el);
            AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(d_);
            ErrorPartOperation e_ = new ErrorPartOperation(0, 0, null, tmpOp_);
            String argClName_ = _page.getAliasObject();
            e_.setResultClass(new AnaClassArgumentMatching(argClName_));
            e_.setOrder(0);
            _anaDoc.setNextIndex(_el.length());
            return e_;
        }
        int beg_ = d_.getIndexBegin();
        int end_ = d_.getIndexEnd();
        _anaDoc.setNextIndex(end_+2);
        String el_ = _el.substring(beg_,end_+1);
        OperationsSequence opTwo_ = getOperationsSequence(_minIndex, el_, d_, _anaDoc, _page);
        OperationNode op_ = createOperationNode(_minIndex, IndexConstants.FIRST_INDEX, null, opTwo_, _anaDoc, _page);
        getSortedDescNodes(op_, _anaDoc, _page);
        return op_;
    }

    public static OperationNode getRootAnalyzedOperations(String _el, int _index, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setCurrentAnonymousResults(new CustList<AnonymousResult>());
        Delimiters d_ = ElResolver.checkSyntax(_el, _index, _page);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_anaDoc.getFileName());
            badEl_.setIndexFile(badOffset_);
            badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                    " ",
                    Long.toString(badOffset_),
                    _el);
            AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(d_);
            ErrorPartOperation e_ = new ErrorPartOperation(0, 0, null, tmpOp_);
            String argClName_ = _page.getAliasObject();
            e_.setResultClass(new AnaClassArgumentMatching(argClName_));
            e_.setOrder(0);
            return e_;
        }
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = getOperationsSequence(_index, el_, d_, _anaDoc, _page);
        OperationNode op_ = createOperationNode(_index, IndexConstants.FIRST_INDEX, null, opTwo_, _anaDoc, _page);
        getSortedDescNodes(op_, _anaDoc, _page);
        return op_;
    }

    public static CustList<OperationNode> getSortedDescNodes(OperationNode _root, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode c_ = _root;
        while (c_ != null) {
            preAnalyze(_page, c_);
            c_ = getAnalyzedNext(c_, _root, list_, _analyzingDoc, _page);
        }
        return _analyzingDoc.getReducingOperations().reduced(list_,_page);
    }

    private static void preAnalyze(AnalyzedPageEl _page, OperationNode _c) {
        if (_c instanceof PreAnalyzableOperation) {
            ((PreAnalyzableOperation) _c).preAnalyze(_page);
        }
    }

    private static OperationNode getAnalyzedNext(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        OperationNode next_ = createFirstChild(_current, _anaDoc, _page);
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            return next_;
        }
        OperationNode current_ = _current;
        while (true) {
            _page.setOkNumOp(true);
            processAnalyze(current_, _anaDoc, _page);
            current_.setOrder(_sortedNodes.size());
            _sortedNodes.add(current_);
            next_ = createNextSibling(current_, _anaDoc, _page);
            MethodOperation par_ = current_.getParent();
            if (next_ != null) {
                if (par_ instanceof AbstractDotOperation) {
                    if (next_ instanceof PossibleIntermediateDotted) {
                        PossibleIntermediateDotted possible_ = (PossibleIntermediateDotted) next_;
                        ElUtil.check(current_, possible_, _page);
                        MethodAccessKind static_ = MethodId.getKind(current_ instanceof StaticAccessOperation);
                        possible_.setIntermediateDotted();
                        possible_.setPreviousResultClass(current_.getResultClass(), static_);
                    }
                }
                par_.appendChild(next_);
                return next_;
            }
            if (par_ == _root) {
                _page.setOkNumOp(true);
                processAnalyze(par_, _anaDoc, _page);
                AnaClassArgumentMatching cl_ = par_.getResultClass();
                if (AnaTypeUtil.isPrimitive(cl_, _page)) {
                    cl_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
                }
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

    private static void processAnalyze(OperationNode _current, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (_current instanceof InterfaceFctConstructor) {
            ElUtil.analyzeInfer(_current, _page);
            return;
        }
        if (!(_current instanceof AbstractInvokingConstructor)) {
            if (_current instanceof ThisOperation) {
                if (((ThisOperation)_current).isIntermediateDottedOperation()) {
                    FoundErrorInterpret badNb_ = new FoundErrorInterpret();
                    badNb_.setFileName(_anaDoc.getFileName());
                    badNb_.setIndexFile(AnalyzingDoc.getCurrentLocationIndex(_page, _anaDoc));
                    badNb_.buildError(_anaDoc.getRendAnalysisMessages().getUnexpectedExp());
                    AnalyzingDoc.addError(badNb_, _anaDoc, _page);
                    return;
                }
            }
            ElUtil.analyzeInfer(_current, _page);
            if (_current instanceof AffectationOperation) {
                OperationNode settable_ = ((AffectationOperation) _current).getSettableOp();
                if (settable_ instanceof SettableAbstractFieldOperation) {
                    SettableAbstractFieldOperation field_ = (SettableAbstractFieldOperation) settable_;
                    if (field_.getSettableFieldContent().isFinalField()) {
                        FoundErrorInterpret badNb_ = new FoundErrorInterpret();
                        badNb_.setFileName(_anaDoc.getFileName());
                        badNb_.setIndexFile(AnalyzingDoc.getCurrentLocationIndex(_page, _anaDoc));
                        StringBuilder id_ = new StringBuilder();
                        id_.append(field_.getSettableFieldContent().getClassField().getClassName());
                        id_.append(";");
                        id_.append(field_.getSettableFieldContent().getClassField().getFieldName());
                        badNb_.buildError(_page.getAnalysisMessages().getFinalField(),
                                id_.toString());
                        AnalyzingDoc.addError(badNb_, _anaDoc, _page);
                    }
                }
            }
        } else {
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFileName(_anaDoc.getFileName());
            badNb_.setIndexFile(AnalyzingDoc.getCurrentLocationIndex(_page, _anaDoc));
            badNb_.buildError(_anaDoc.getRendAnalysisMessages().getUnexpectedExp());
            AnalyzingDoc.addError(badNb_, _anaDoc, _page);
        }
    }

    private static OperationNode createFirstChild(OperationNode _block, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
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
        OperationsSequence r_ = getOperationsSequence(offset_, value_, d_, _anaDoc, _page);
        return createOperationNode(offset_, 0, block_, r_, _anaDoc, _page);
    }

    private static OperationNode createNextSibling(OperationNode _block, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        MethodOperation p_ = _block.getParent();
        if (p_ == null) {
            return null;
        }
        StrTypes children_ = p_.getChildren();
        int del_ = _block.getIndexChild() + 1;
        if (del_ >= children_.size()) {
            return null;
        }
        String value_ = children_.getValue(del_);
        Delimiters d_ = _block.getOperations().getDelimiter();
        int curKey_ = children_.getKey(del_);
        int offset_ = p_.getIndexInEl()+curKey_;
        OperationsSequence r_ = getOperationsSequence(offset_, value_, d_, _anaDoc, _page);
        return createOperationNode(offset_, _block.getIndexChild() + 1, p_, r_, _anaDoc, _page);
    }

    public static OperationsSequence getOperationsSequence(int _offset, String _string,
                                                           Delimiters _d, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        int len_ = _string.length();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < len_) {
            if (!StringUtil.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        if (i_ < len_) {
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordIntern_ = keyWords_.getKeyWordIntern();
            int lastPrintChar_ = len_ - 1;
            while (StringUtil.isWhitespace(_string.charAt(lastPrintChar_))) {
                lastPrintChar_--;
            }
            len_ = lastPrintChar_+1;
            String sub_ = _string.substring(i_, len_);
            if (_anaDoc.isInternGlobal()) {
                if (StringUtil.quickEq(sub_, keyWordIntern_)) {
                    OperationsSequence op_ = new OperationsSequence();
                    op_.setConstType(ConstType.WORD);
                    op_.setOperators(new StrTypes());
                    op_.setValue(_string, i_);
                    op_.setDelimiter(_d);
                    return op_;
                }
            }
        }
        return ElResolver.getOperationsSequence(_offset, _string, _d, _page);
    }

    public static OperationNode createOperationNode(int _index,
                                                    int _indexChild, MethodOperation _m, OperationsSequence _op, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        if (_op.getOperators().isEmpty()) {
            String originalStr_ = _op.getValues().getValue(IndexConstants.FIRST_INDEX);
            String str_ = originalStr_.trim();
            if (_analyzingDoc.isInternGlobal() && StringUtil.quickEq(str_, keyWordIntern_)) {
                return new InternGlobalOperation(_index, _indexChild, _m, _op, _analyzingDoc);
            }
        }
        return OperationNode.createOperationNode(_index, _indexChild, _m, _op, _page);
    }
}
