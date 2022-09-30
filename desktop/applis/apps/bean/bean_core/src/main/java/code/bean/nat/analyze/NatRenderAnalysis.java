package code.bean.nat.analyze;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatDelimiters;
import code.bean.nat.analyze.instr.NatElResolver;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.bean.nat.analyze.opers.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class NatRenderAnalysis {

    public static final String INTERN = "$intern";

    private NatRenderAnalysis() {
    }

    public static NatOperationNode getRootAnalyzedOperationsDel(String _el, int _minIndex, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatDelimiters d_ = NatElResolver.checkSyntax(_el, _minIndex,_anaDoc);
        int end_ = d_.getIndexEnd();
        _anaDoc.setNextIndex(end_+2);
        String el_ = _el.substring(_minIndex,end_+1);
        NatOperationsSequence opTwo_ = getOperationsSequence(_minIndex, el_, d_, _anaDoc);
        NatOperationNode op_ = createOperationNode(_minIndex, IndexConstants.FIRST_INDEX, null, opTwo_, _anaDoc, _page);
        getSortedDescNodes(op_, _anaDoc, _page);
        return op_;
    }

    public static NatOperationNode getRootAnalyzedOperations(String _el, int _index, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatDelimiters d_ = NatElResolver.checkSyntax(_el, _index,_anaDoc);
        String el_ = _el.substring(_index);
        NatOperationsSequence opTwo_ = getOperationsSequence(_index, el_, d_, _anaDoc);
        NatOperationNode op_ = createOperationNode(_index, IndexConstants.FIRST_INDEX, null, opTwo_, _anaDoc, _page);
        getSortedDescNodes(op_, _anaDoc, _page);
        return op_;
    }

    public static void getSortedDescNodes(NatOperationNode _root, NatAnalyzingDoc _analyzingDoc, NatAnalyzedCode _page) {
        CustList<NatOperationNode> list_ = new CustList<NatOperationNode>();
        NatOperationNode c_ = _root;
        while (c_ != null) {

            c_ = getAnalyzedNext(c_, _root, list_, _analyzingDoc, _page);
        }
    }

    private static NatOperationNode getAnalyzedNext(NatOperationNode _current, NatOperationNode _root, CustList<NatOperationNode> _sortedNodes, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatOperationNode next_ = create(_anaDoc, _page, _current, 0);
        if (next_ != null) {
            ((MethodNatOperation) _current).appendChild(next_);
            return next_;
        }
        NatOperationNode current_ = _current;
        while (true) {
            processAnalyze(current_, _page);
            current_.setOrder(_sortedNodes.size());
            _sortedNodes.add(current_);
            next_ = create(_anaDoc, _page, current_.getParent(), current_.getIndexChild() + 1);
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

    private static void processAnalyze(NatOperationNode _current, NatAnalyzedCode _page) {
        _current.analyze(_page);

    }

    private static NatOperationNode create(NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page, NatOperationNode _pa, int _nex) {
        if (!(_pa instanceof MethodNatOperation)) {
            return null;
        }
        MethodNatOperation p_ = (MethodNatOperation) _pa;
        StrTypes children_ = p_.getChildren();
        if (_nex >= children_.size()) {
            return null;
        }
        return build(_anaDoc, _page, p_, _nex);
    }

    private static NatOperationNode build(NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page, MethodNatOperation _p, int _del) {
        StrTypes children_ = _p.getChildren();
        String value_ = children_.getValue(_del);
        NatDelimiters d_ = _p.getOperations().getDelimiterNat();
        int curKey_ = children_.getKey(_del);
        int offset_ = _p.getIndexInEl() + curKey_;
        NatOperationsSequence r_ = getOperationsSequence(offset_, value_, d_, _anaDoc);
        return createOperationNode(offset_, _del, _p, r_, _anaDoc, _page);
    }

    public static NatOperationsSequence getOperationsSequence(int _offset, String _string,
                                                              NatDelimiters _d, NatAnalyzingDoc _anaDoc) {
        int len_ = _string.length();
        int i_ = IndexConstants.FIRST_INDEX;
        int lastPrintChar_ = len_ - 1;
        len_ = lastPrintChar_+1;
        String sub_ = _string.substring(i_, len_);
        if (_anaDoc.isInternGlobal() && StringUtil.quickEq(sub_, INTERN)) {
            NatOperationsSequence op_ = new NatOperationsSequence();
            op_.setOpersNat(new StrTypes());
            op_.setValue(_string);
            op_.setDelimiterNat(_d);
            return op_;
        }
        return NatElResolver.getOperationsSequence(_offset, _string, _d);
    }

    public static NatOperationNode createOperationNode(int _index,
                                                       int _indexChild, MethodNatOperation _m, NatOperationsSequence _op, NatAnalyzingDoc _analyzingDoc, NatAnalyzedCode _page) {
        if (_op.getOpersNat().isEmpty()) {
            String originalStr_ = _op.getValNat().getValue(IndexConstants.FIRST_INDEX);
            String str_ = originalStr_.trim();
            if (_analyzingDoc.isInternGlobal() && StringUtil.quickEq(str_, INTERN)) {
                return new InternGlobalNatOperation(_index, _indexChild, _m, _op, _analyzingDoc);
            }
        }
        return NatOperationNode.createOperationNode(_index, _indexChild, _m, _op, _page);
    }

}
