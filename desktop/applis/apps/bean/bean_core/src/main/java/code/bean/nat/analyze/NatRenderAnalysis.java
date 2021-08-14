package code.bean.nat.analyze;

import code.bean.nat.analyze.opers.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.instr.NatElResolver;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.KeyWords;
import code.formathtml.analyze.*;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class NatRenderAnalysis {
    private NatRenderAnalysis() {
    }

    public static NatOperationNode getRootAnalyzedOperationsDel(String _el, int _minIndex, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        Delimiters d_ = NatElResolver.checkSyntaxDelimiters(_el, _minIndex,_anaDoc, _page);
        int end_ = d_.getIndexEnd();
        _anaDoc.setNextIndex(end_+2);
        String el_ = _el.substring(_minIndex,end_+1);
        NatOperationsSequence opTwo_ = getOperationsSequence(_minIndex, el_, d_, _anaDoc, _page);
        NatOperationNode op_ = createOperationNode(_minIndex, IndexConstants.FIRST_INDEX, null, opTwo_, _anaDoc, _page);
        getSortedDescNodes(op_, _anaDoc, _page);
        return op_;
    }

    public static NatOperationNode getRootAnalyzedOperations(String _el, int _index, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        Delimiters d_ = NatElResolver.checkSyntax(_el, _index,_anaDoc, _page);
        String el_ = _el.substring(_index);
        NatOperationsSequence opTwo_ = getOperationsSequence(_index, el_, d_, _anaDoc, _page);
        NatOperationNode op_ = createOperationNode(_index, IndexConstants.FIRST_INDEX, null, opTwo_, _anaDoc, _page);
        getSortedDescNodes(op_, _anaDoc, _page);
        return op_;
    }

    public static CustList<NatOperationNode> getSortedDescNodes(NatOperationNode _root, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        CustList<NatOperationNode> list_ = new CustList<NatOperationNode>();
        NatOperationNode c_ = _root;
        while (c_ != null) {

            c_ = getAnalyzedNext(c_, _root, list_, _analyzingDoc, _page);
        }
        return list_;
    }

    private static NatOperationNode getAnalyzedNext(NatOperationNode _current, NatOperationNode _root, CustList<NatOperationNode> _sortedNodes, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        NatOperationNode next_ = createFirstChild(_current, _anaDoc, _page);
        if (next_ != null) {
            ((MethodNatOperation) _current).appendChild(next_);
            return next_;
        }
        NatOperationNode current_ = _current;
        while (true) {
            _page.setOkNumOp(true);
            processAnalyze(current_, _page);
            current_.setOrder(_sortedNodes.size());
            _sortedNodes.add(current_);
            next_ = createNextSibling(current_, _anaDoc, _page);
            MethodNatOperation par_ = current_.getParent();
            if (next_ != null) {
                if (par_ instanceof AbstractDotNatOperation) {
                    NatPossibleIntermediateDotted possible_ = (NatPossibleIntermediateDotted) next_;
                    MethodAccessKind static_ = MethodId.getKind(false);
                    possible_.setIntermediateDotted();
                    possible_.setPreviousResultClass(current_.getNames(), static_);
                }
                par_.appendChild(next_);
                return next_;
            }
            if (par_ == _root) {
                _page.setOkNumOp(true);
                processAnalyze(par_, _page);
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

    private static void processAnalyze(NatOperationNode _current, AnalyzedPageEl _page) {
        _current.analyze(_page);

    }

    private static NatOperationNode createFirstChild(NatOperationNode _block, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (!(_block instanceof MethodNatOperation)) {
            return null;
        }
        MethodNatOperation block_ = (MethodNatOperation) _block;
        if (block_.getChildren().isEmpty()) {
            return null;
        }
        String value_ = block_.getChildren().getValue(0);
        Delimiters d_ = block_.getOperations().getDelimiter();
        int curKey_ = block_.getChildren().getKey(0);
        int offset_ = block_.getIndexInEl()+curKey_;
        NatOperationsSequence r_ = getOperationsSequence(offset_, value_, d_, _anaDoc, _page);
        return createOperationNode(offset_, 0, block_, r_, _anaDoc, _page);
    }

    private static NatOperationNode createNextSibling(NatOperationNode _block, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        MethodNatOperation p_ = _block.getParent();
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
        NatOperationsSequence r_ = getOperationsSequence(offset_, value_, d_, _anaDoc, _page);
        return createOperationNode(offset_, _block.getIndexChild() + 1, p_, r_, _anaDoc, _page);
    }

    public static NatOperationsSequence getOperationsSequence(int _offset, String _string,
                                                              Delimiters _d, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        int len_ = _string.length();
        int i_ = IndexConstants.FIRST_INDEX;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        int lastPrintChar_ = len_ - 1;
        len_ = lastPrintChar_+1;
        String sub_ = _string.substring(i_, len_);
        if (_anaDoc.isInternGlobal() && StringUtil.quickEq(sub_, keyWordIntern_)) {
            NatOperationsSequence op_ = new NatOperationsSequence();
            op_.setConstType(ConstType.WORD);
            op_.setOperators(new StrTypes());
            op_.setValue(_string, i_);
            op_.setDelimiter(_d);
            return op_;
        }
        return NatElResolver.getOperationsSequence(_offset, _string, _d);
    }

    public static NatOperationNode createOperationNode(int _index,
                                                       int _indexChild, MethodNatOperation _m, NatOperationsSequence _op, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        if (_op.getOperators().isEmpty()) {
            String originalStr_ = _op.getValues().getValue(IndexConstants.FIRST_INDEX);
            String str_ = originalStr_.trim();
            if (_analyzingDoc.isInternGlobal() && StringUtil.quickEq(str_, keyWordIntern_)) {
                return new InternGlobalNatOperation(_index, _indexChild, _m, _op, _analyzingDoc);
            }
        }
        return NatOperationNode.createOperationNode(_index, _indexChild, _m, _op, _page);
    }

    public static void setupInts(AnalyzedPageEl _page, AnalyzingDoc _analyzingDoc) {
        _page.setCurrentAnaBlockForEachLoop(null);
        _page.setCurrentAnaBlockForEachTable(null);
        _page.setHiddenTypes(new AdvancedHiddenTypes(_page));
        _page.setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(_page, _analyzingDoc));
        _page.setCurrentConstraints(new AdvancedCurrentConstraints());
        _page.setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        _page.setLoopDeclaring(new AdvancedLoopDeclaring(_analyzingDoc));
        _page.setLocalDeclaring(new AdvancedLocalDeclaring(_analyzingDoc));
        _page.setBuildingConstraints(new AdvancedBuildingConstraints(_page));
        _page.setLocalizer(new AdvancedLocalizer(_page, _analyzingDoc));
        _page.setTokenValidation(new AdvancedTokenValidation(_page));
    }
}
