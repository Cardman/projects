package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.*;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.*;
import code.expressionlanguage.opers.exec.ReductibleOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendCalculableOperation;
import code.formathtml.exec.RendAffectationOperation;
import code.formathtml.exec.RendCompoundAffectationOperation;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.exec.RendMethodOperation;
import code.formathtml.exec.RendPossibleIntermediateDotted;
import code.formathtml.exec.RendSemiAffectationOperation;
import code.formathtml.exec.InternGlobalOperation;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

public final class RenderExpUtil {
    private RenderExpUtil() {
    }

    public static CustList<RendDynOperationNode> getAnalyzedOperations(String _el, Configuration _conf, int _glInd,int _minIndex, char _begin, char _end) {
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(_el, _conf, _minIndex, _begin, _end);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_glInd+badOffset_);
            badEl_.buildError(_conf.getContext().getAnalysisMessages().getBadExpression(),
                    ElUtil.possibleChar(badOffset_,_el),
                    Integer.toString(badOffset_),
                    _el);
            _conf.addError(badEl_);
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(new Delimiters());
            ErrorPartOperation e_ = new ErrorPartOperation(0, 0, null, tmpOp_);
            String argClName_ = _conf.getStandards().getAliasObject();
            e_.setResultClass(new ClassArgumentMatching(argClName_));
            e_.setOrder(0);
            int end_ = d_.getIndexEnd();
            _conf.setNextIndex(end_+2);
            return new CustList<RendDynOperationNode>((RendDynOperationNode)RendDynOperationNode.createExecOperationNode(e_));
        }
        int beg_ = d_.getIndexBegin();
        int end_ = d_.getIndexEnd();
        int cap_ = end_+1 - beg_;
        StringBuilder str_ = new StringBuilder(cap_);
        for (int i = beg_; i <= end_; i++) {
            if (d_.getEscapings().contains(i)) {
                str_.append(' ');
            } else {
                str_.append(_el.charAt(i));
            }
        }
        _conf.setNextIndex(end_+2);
        String el_ = str_.toString();
        OperationsSequence opTwo_ = getOperationsSequence(_minIndex, el_, _conf, d_);
        OperationNode op_ = createOperationNode(_minIndex, CustList.FIRST_INDEX, null, opTwo_, _conf);
        CustList<OperationNode> all_ = getSortedDescNodes(op_, _conf);
        return getExecutableNodes(all_);
    }

    public static CustList<RendDynOperationNode> getAnalyzedOperations(String _el, int _index, Configuration _conf) {
        return getAnalyzedOperations(_el,1,_index,_conf);
    }
    public static CustList<RendDynOperationNode> getAnalyzedOperations(String _el, int _gl,int _index, Configuration _conf) {
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, _index);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_gl+badOffset_);
            badEl_.buildError(_conf.getContext().getAnalysisMessages().getBadExpression(),
                    ElUtil.possibleChar(badOffset_,_el),
                    Integer.toString(badOffset_),
                    _el);
            _conf.addError(badEl_);
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(new Delimiters());
            ErrorPartOperation e_ = new ErrorPartOperation(0, 0, null, tmpOp_);
            String argClName_ = _conf.getStandards().getAliasObject();
            e_.setResultClass(new ClassArgumentMatching(argClName_));
            e_.setOrder(0);
            return new CustList<RendDynOperationNode>((RendDynOperationNode)RendDynOperationNode.createExecOperationNode(e_));
        }
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = getOperationsSequence(_index, el_, _conf, d_);
        OperationNode op_ = createOperationNode(_index, CustList.FIRST_INDEX, null, opTwo_, _conf);
        CustList<OperationNode> all_ = getSortedDescNodes(op_, _conf);
        return getExecutableNodes(all_);
    }

    public static Argument processEl(String _el, int _index, Configuration _conf) {
        ContextEl context_ = _conf.getContext();
        CustList<RendDynOperationNode> out_ = getAnalyzed(_el,_index,_conf);
        if (!_conf.isEmptyErrors()) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            badEl_.setLocationFile(_conf.getLocationFile(badEl_.getFileName(),badEl_.getIndexFile()));
            badEl_.buildError(_conf.getContext().getAnalysisMessages().getBadExpression(),
                    ElUtil.possibleChar(_index,_el),
                    Integer.toString(_index),
                    _el);
            _conf.setException(new ErrorStruct(_conf, badEl_.display(), _conf.getStandards().getAliasIllegalArg()));
            context_.setNullAnalyzing();
            return Argument.createVoid();
        }
        context_.setNullAnalyzing();
        out_ = getReducedNodes(out_.last());
        return calculateReuse(out_, _conf);
    }
    public static Argument processQuickEl(String _el, int _index, Configuration _conf) {
        CustList<RendDynOperationNode> out_ = getAnalyzed(_el,_index,_conf);
        ContextEl context_ = _conf.getContext();
        context_.setNullAnalyzing();
        out_ = getReducedNodes(out_.last());
        return calculateReuse(out_, _conf);
    }
    private static CustList<RendDynOperationNode> getAnalyzed(String _el, int _index, Configuration _conf) {
        _conf.setupAnalyzing();
        Argument argGl_ = _conf.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_.isNull();
        _conf.setAccessStaticContext(MethodId.getKind(static_));
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, _index);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            _conf.getLastPage().setOffset(badOffset_);
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            badEl_.buildError(_conf.getContext().getAnalysisMessages().getBadExpression(),
                    ElUtil.possibleChar(badOffset_,_el),
                    Integer.toString(badOffset_),
                    _el);
            _conf.addError(badEl_);
            _conf.setException(new ErrorStruct(_conf, badEl_.display(), _conf.getStandards().getAliasIllegalArg()));
            return new CustList<RendDynOperationNode>();
        }
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = getOperationsSequence(_index, el_, _conf, d_);
        OperationNode op_ = createOperationNode(_index, CustList.FIRST_INDEX, null, opTwo_, _conf);
        CustList<OperationNode> all_ = getSortedDescNodes(op_, _conf);
        return getExecutableNodes(all_);
    }
    public static CustList<RendDynOperationNode> getExecutableNodes(CustList<OperationNode> _list) {
        CustList<RendDynOperationNode> out_ = new CustList<RendDynOperationNode>();
        OperationNode root_ = _list.last();
        OperationNode current_ = root_;
        RendDynOperationNode exp_ = RendDynOperationNode.createExecOperationNode(current_);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (exp_ instanceof RendMethodOperation&&op_ != null) {
                RendDynOperationNode loc_ = RendDynOperationNode.createExecOperationNode(op_);
                ((RendMethodOperation) exp_).appendChild(loc_);
                exp_ = loc_;
                current_ = op_;
                continue;
            }
            while (true) {
                if (exp_ instanceof RendAffectationOperation) {
                    ((RendAffectationOperation) exp_).setup();
                }
                if (exp_ instanceof RendSemiAffectationOperation) {
                    ((RendSemiAffectationOperation) exp_).setup();
                }
                if (exp_ instanceof RendCompoundAffectationOperation) {
                    ((RendCompoundAffectationOperation) exp_).setup();
                }
                out_.add(exp_);
                op_ = current_.getNextSibling();
                if (op_ != null) {
                    RendDynOperationNode loc_ = RendDynOperationNode.createExecOperationNode(op_);
                    RendMethodOperation par_ = exp_.getParent();
                    par_.appendChild(loc_);
                    if (op_.getParent() instanceof DotOperation && loc_ instanceof RendPossibleIntermediateDotted) {
                        exp_.setSiblingSet((RendPossibleIntermediateDotted) loc_);
                    }
                    exp_ = loc_;
                    current_ = op_;
                    break;
                }
                op_ = current_.getParent();
                if (op_ == null) {
                    current_ = null;
                    break;
                }
                RendMethodOperation par_ = exp_.getParent();
                if (op_ == root_) {
                    if (par_ instanceof RendAffectationOperation) {
                        ((RendAffectationOperation) par_).setup();
                    }
                    if (par_ instanceof RendSemiAffectationOperation) {
                        ((RendSemiAffectationOperation) par_).setup();
                    }
                    if (par_ instanceof RendCompoundAffectationOperation) {
                        ((RendCompoundAffectationOperation) par_).setup();
                    }
                    out_.add(par_);
                    current_ = null;
                    break;
                }
                current_ = op_;
                exp_ = par_;
            }
        }
        return out_;
    }
    public static CustList<OperationNode> getSortedDescNodes(OperationNode _root, Configuration _context) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode c_ = _root;
        while (c_ != null) {
            if (c_ instanceof PreAnalyzableOperation) {
                ((PreAnalyzableOperation) c_).preAnalyze(_context);
            }
            c_ = getAnalyzedNext(c_, _root, list_, _context);
        }
        return list_;
    }

    private static OperationNode getAnalyzedNext(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, Configuration _context) {
        
        OperationNode next_ = createFirstChild(_current, _context, 0);
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            return next_;
        }
        OperationNode current_ = _current;
        while (true) {
            _context.setOkNumOp(true);
            processAnalyze(_context, current_);
            if (current_ instanceof ReductibleOperable) {
                ((ReductibleOperable)current_).tryCalculateNode(_context);
            }
            current_.setOrder(_sortedNodes.size());
            _sortedNodes.add(current_);
            if (current_ instanceof StaticInitOperation) {
                next_ = createFirstChild(current_.getParent(), _context, 1);
            } else {
                next_ = createNextSibling(current_, _context);
            }
            MethodOperation par_ = current_.getParent();
            if (next_ != null) {
                if (par_ instanceof DotOperation) {
                    if (next_ instanceof PossibleIntermediateDotted) {
                        PossibleIntermediateDotted possible_ = (PossibleIntermediateDotted) next_;
                        MethodAccessKind static_ = MethodId.getKind(current_ instanceof StaticAccessOperation);
                        possible_.setIntermediateDotted();
                        possible_.setPreviousArgument(current_.getArgument());
                        possible_.setPreviousResultClass(current_.getResultClass(), static_);
                    }
                }
                par_.appendChild(next_);
                return next_;
            }
            if (par_ == _root) {
                _context.setOkNumOp(true);
                processAnalyze(_context, par_);
                ClassArgumentMatching cl_ = par_.getResultClass();
                if (PrimitiveTypeUtil.isPrimitive(cl_, _context)) {
                    cl_.setUnwrapObject(cl_);
                }
                par_.tryCalculateNode(_context);
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

    private static void processAnalyze(Configuration _context, OperationNode _current) {
        if (!(_current instanceof AbstractInvokingConstructor)) {
            if (_current instanceof ThisOperation) {
                if (((ThisOperation)_current).isIntermediateDottedOperation()) {
                    FoundErrorInterpret badNb_ = new FoundErrorInterpret();
                    badNb_.setFileName(_context.getCurrentFileName());
                    badNb_.setIndexFile(_context.getCurrentLocationIndex());
                    badNb_.buildError(_context.getRendAnalysisMessages().getUnexpectedExp());
                    _context.addError(badNb_);
                    return;
                }
            }
            _current.analyze(_context);
            if (_current instanceof AffectationOperation) {
                SettableElResult settable_ = ((AffectationOperation) _current).getSettable();
                if (settable_ instanceof SettableAbstractFieldOperation) {
                    SettableAbstractFieldOperation field_ = (SettableAbstractFieldOperation) settable_;
                    FieldInfo info_ = field_.getFieldMetaInfo();
                    if (info_ != null) {
                        if (info_.isFinalField()) {
                            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
                            badNb_.setFileName(_context.getCurrentFileName());
                            badNb_.setIndexFile(_context.getCurrentLocationIndex());
                            StringBuilder id_ = new StringBuilder();
                            id_.append(info_.getClassField().getClassName());
                            id_.append(";");
                            id_.append(info_.getClassField().getFieldName());
                            badNb_.buildError(_context.getContext().getAnalysisMessages().getFinalField(),
                                    id_.toString());
                            _context.addError(badNb_);
                        }
                    }
                }
            }
        } else {
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFileName(_context.getCurrentFileName());
            badNb_.setIndexFile(_context.getCurrentLocationIndex());
            badNb_.buildError(_context.getRendAnalysisMessages().getUnexpectedExp());
            _context.addError(badNb_);
        }
    }

    private static OperationNode createFirstChild(OperationNode _block, Configuration _context, int _index) {
        if (!(_block instanceof MethodOperation)) {
            return null;
        }
        MethodOperation block_ = (MethodOperation) _block;
        if (block_.getChildren().isEmpty()) {
            if (_context.getOptions().isInitializeStaticClassFirst() && block_ instanceof StandardInstancingOperation) {
                if (_index == CustList.FIRST_INDEX) {
                    Delimiters d_ = block_.getOperations().getDelimiter();
                    OperationsSequence opSeq_ = new OperationsSequence();
                    opSeq_.setFctName(block_.getOperations().getFctName());
                    opSeq_.setDelimiter(new Delimiters());
                    opSeq_.getDelimiter().setIndexBegin(d_.getIndexBegin());
                    return new StaticInitOperation(block_.getIndexInEl(), CustList.FIRST_INDEX, block_, opSeq_);
                }
            }
            return null;
        }
        String value_ = block_.getChildren().getValue(0);
        Delimiters d_ = block_.getOperations().getDelimiter();
        int curKey_ = block_.getChildren().getKey(0);
        int offset_ = block_.getIndexInEl()+curKey_;
        if (_context.getOptions().isInitializeStaticClassFirst() && block_ instanceof StandardInstancingOperation) {
            if (_index == CustList.FIRST_INDEX) {
                OperationsSequence opSeq_ = new OperationsSequence();
                opSeq_.setFctName(block_.getOperations().getFctName());
                opSeq_.setDelimiter(new Delimiters());
                opSeq_.getDelimiter().setIndexBegin(d_.getIndexBegin());
                return new StaticInitOperation(block_.getIndexInEl(), CustList.FIRST_INDEX, block_, opSeq_);
            }
        }
        OperationsSequence r_ = getOperationsSequence(offset_, value_, _context, d_);
        return createOperationNode(offset_, _index, block_, r_, _context);
    }

    private static OperationNode createNextSibling(OperationNode _block, Configuration _context) {
        MethodOperation p_ = _block.getParent();
        if (p_ == null) {
            return null;
        }
        IntTreeMap<String> children_ = p_.getChildren();
        int delta_ = 1;
        if (p_ instanceof StandardInstancingOperation) {
            if (p_.getFirstChild() instanceof StaticInitOperation) {
                delta_ = 0;
            }
        }
        if (_block.getIndexChild() + delta_ >= children_.size()) {
            return null;
        }
        String value_ = children_.getValue(_block.getIndexChild() + delta_);
        Delimiters d_ = _block.getOperations().getDelimiter();
        int curKey_ = children_.getKey(_block.getIndexChild() + delta_);
        int offset_ = p_.getIndexInEl()+curKey_;
        OperationsSequence r_ = getOperationsSequence(offset_, value_, _context, d_);
        return createOperationNode(offset_, _block.getIndexChild() + 1, p_, r_, _context);
    }
    static OperationsSequence getOperationsSequence(int _offset, String _string,
                                                    Configuration _conf, Delimiters _d) {
        int len_ = _string.length();
        int i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            if (!Character.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        if (i_ < len_) {
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordIntern_ = keyWords_.getKeyWordIntern();
            int lastPrintChar_ = len_ - 1;
            while (Character.isWhitespace(_string.charAt(lastPrintChar_))) {
                lastPrintChar_--;
            }
            len_ = lastPrintChar_+1;
            String sub_ = _string.substring(i_, len_);
            if (_conf.isInternGlobal()) {
                if (StringList.quickEq(sub_, keyWordIntern_)) {
                    OperationsSequence op_ = new OperationsSequence();
                    op_.setConstType(ConstType.WORD);
                    op_.setOperators(new IntTreeMap< String>());
                    op_.setValue(_string, i_);
                    op_.setDelimiter(_d);
                    return op_;
                }
            }
        }
        return ElResolver.getOperationsSequence(_offset, _string, _conf, _d);
    }
    static OperationNode createOperationNode(int _index,
                                             int _indexChild, MethodOperation _m, OperationsSequence _op, Configuration _an) {
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        if (_op.getOperators().isEmpty()) {
            String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
            String str_ = originalStr_.trim();
            if (_an.isInternGlobal() && StringList.quickEq(str_, keyWordIntern_)) {
                return new InternGlobalOperation(_index, _indexChild, _m, _op);
            }
        }
        return OperationNode.createOperationNode(_index, _indexChild, _m, _op, _an);
    }

    public static Argument calculateReuse(CustList<RendDynOperationNode> _nodes, Configuration _context, Argument _arg) {
        Argument globalArgument_ = _context.getLastPage().getGlobalArgument();
        _context.getLastPage().setGlobalArgumentStruct(_arg.getStruct(),_context);
        Argument argument_ = calculateReuse(_nodes, _context);
        _context.getLastPage().setGlobalArgumentStruct(globalArgument_.getStruct(),_context);
        return argument_;
    }
    public static Argument calculateReuse(CustList<RendDynOperationNode> _nodes, Configuration _context) {
        IdMap<RendDynOperationNode,ArgumentsPair> arguments_;
        arguments_ = getAllArgs(_nodes,_context);
        return arguments_.lastValue().getArgument();
    }

    public static IdMap<RendDynOperationNode,ArgumentsPair> getAllArgs(CustList<RendDynOperationNode> _nodes, Configuration _context) {
        IdMap<RendDynOperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<RendDynOperationNode,ArgumentsPair>();
        for (RendDynOperationNode o: _nodes) {
            ArgumentsPair a_ = new ArgumentsPair();
            a_.setArgument(o.getArgument());
            if (o instanceof RendPossibleIntermediateDotted) {
                a_.setPreviousArgument(((RendPossibleIntermediateDotted)o).getPreviousArgument());
            }
            arguments_.addEntry(o, a_);
        }
        int fr_ = 0;
        int len_ = _nodes.size();
        while (fr_ < len_) {
            RendDynOperationNode o = arguments_.getKey(fr_);
            ArgumentsPair pair_ = arguments_.getValue(fr_);
            if (!(o instanceof RendCalculableOperation)) {
                fr_++;
                continue;
            }
            if (pair_.getArgument() != null) {
                fr_++;
                continue;
            }
            RendCalculableOperation a_ = (RendCalculableOperation)o;
            a_.calculate(arguments_, _context);
            if (_context.getContext().hasException()) {
                return arguments_;
            }
            Argument res_ = pair_.getArgument();
            Struct st_ = res_.getStruct();
            fr_ = RendDynOperationNode.getNextIndex(o, st_);
        }
        return arguments_;
    }
    public static CustList<RendDynOperationNode> getReducedNodes(RendDynOperationNode _root) {
        CustList<RendDynOperationNode> out_ = new CustList<RendDynOperationNode>();
        RendDynOperationNode current_ = _root;
        while (current_ != null) {
            RendDynOperationNode op_ = current_.getFirstChild();
            if (op_ != null) {
                if (current_.getArgument() == null) {
                    current_ = op_;
                    continue;
                }
            }
            while (true) {
                current_.setOrder(out_.size());
                out_.add(current_);
                op_ = current_.getNextSibling();
                if (op_ != null) {
                    current_ = op_;
                    break;
                }
                op_ = current_.getParent();
                if (op_ == _root) {
                    op_.setOrder(out_.size());
                    out_.add(op_);
                    current_ = null;
                    break;
                }
                if (op_ == null) {
                    current_ = null;
                    break;
                }
                current_ = op_;
            }
        }
        return out_;
    }
}
