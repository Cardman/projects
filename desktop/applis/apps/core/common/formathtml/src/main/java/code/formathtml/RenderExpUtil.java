package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.instr.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;

import code.expressionlanguage.exec.opers.ReductibleOperable;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.*;
import code.formathtml.util.AnalyzingDoc;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

public final class RenderExpUtil {
    private RenderExpUtil() {
    }

    public static CustList<RendDynOperationNode> getAnalyzedOperations(String _el, Configuration _conf, int _glInd, int _minIndex, AnalyzingDoc _anaDoc) {
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(_el, _conf.getContext(), _minIndex);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_anaDoc.getFileName());
            badEl_.setIndexFile(_glInd+badOffset_);
            badEl_.buildError(_conf.getContext().getAnalyzing().getAnalysisMessages().getBadExpression(),
                    " ",
                    Integer.toString(badOffset_),
                    _el);
            Configuration.addError(badEl_, _anaDoc, _conf.getContext().getAnalyzing());
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(d_);
            ErrorPartOperation e_ = new ErrorPartOperation(0, 0, null, tmpOp_);
            String argClName_ = _conf.getStandards().getAliasObject();
            e_.setResultClass(new ClassArgumentMatching(argClName_));
            e_.setOrder(0);
            int end_ = d_.getIndexEnd();
            _anaDoc.setNextIndex(end_+2);
            return new CustList<RendDynOperationNode>((RendDynOperationNode)RendDynOperationNode.createExecOperationNode(e_,_conf.getContext()));
        }
        int beg_ = d_.getIndexBegin();
        int end_ = d_.getIndexEnd();
        _anaDoc.setNextIndex(end_+2);
        String el_ = _el.substring(beg_,end_+1);
        OperationsSequence opTwo_ = getOperationsSequence(_minIndex, el_, _conf, d_, _anaDoc);
        OperationNode op_ = createOperationNode(_minIndex, CustList.FIRST_INDEX, null, opTwo_, _conf, _anaDoc);
        CustList<OperationNode> all_ = getSortedDescNodes(op_, _conf, _anaDoc);
        return getExecutableNodes(all_,_conf.getContext());
    }

    public static CustList<RendDynOperationNode> getAnalyzedOperations(String _el, int _index, Configuration _conf, AnalyzingDoc _anaDoc) {
        return getAnalyzedOperations(_el,1,_index,_conf, _anaDoc);
    }
    public static CustList<RendDynOperationNode> getAnalyzedOperations(String _el, int _gl, int _index, Configuration _conf, AnalyzingDoc _anaDoc) {
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf.getContext(), _index);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_anaDoc.getFileName());
            badEl_.setIndexFile(_gl+badOffset_);
            badEl_.buildError(_conf.getContext().getAnalyzing().getAnalysisMessages().getBadExpression(),
                    " ",
                    Integer.toString(badOffset_),
                    _el);
            Configuration.addError(badEl_, _anaDoc, _conf.getContext().getAnalyzing());
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(d_);
            ErrorPartOperation e_ = new ErrorPartOperation(0, 0, null, tmpOp_);
            String argClName_ = _conf.getStandards().getAliasObject();
            e_.setResultClass(new ClassArgumentMatching(argClName_));
            e_.setOrder(0);
            return new CustList<RendDynOperationNode>((RendDynOperationNode)RendDynOperationNode.createExecOperationNode(e_,_conf.getContext()));
        }
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = getOperationsSequence(_index, el_, _conf, d_, _anaDoc);
        OperationNode op_ = createOperationNode(_index, CustList.FIRST_INDEX, null, opTwo_, _conf, _anaDoc);
        CustList<OperationNode> all_ = getSortedDescNodes(op_, _conf, _anaDoc);
        return getExecutableNodes(all_,_conf.getContext());
    }

    public static CustList<RendDynOperationNode> getExecutableNodes(CustList<OperationNode> _list, ContextEl _cont) {
        CustList<RendDynOperationNode> out_ = new CustList<RendDynOperationNode>();
        OperationNode root_ = _list.last();
        OperationNode current_ = root_;
        RendDynOperationNode exp_ = RendDynOperationNode.createExecOperationNode(current_,_cont);
        setImplicits(exp_,_cont);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (exp_ instanceof RendMethodOperation&&op_ != null) {
                RendDynOperationNode loc_ = RendDynOperationNode.createExecOperationNode(op_,_cont);
                setImplicits(loc_,_cont);
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
                    RendDynOperationNode loc_ = RendDynOperationNode.createExecOperationNode(op_,_cont);
                    setImplicits(loc_,_cont);
                    RendMethodOperation par_ = exp_.getParent();
                    par_.appendChild(loc_);
                    if (op_.getParent() instanceof AbstractDotOperation && loc_ instanceof RendPossibleIntermediateDotted) {
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
    public static CustList<OperationNode> getSortedDescNodes(OperationNode _root, Configuration _context, AnalyzingDoc _analyzingDoc) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode c_ = _root;
        while (c_ != null) {
            if (c_ instanceof PreAnalyzableOperation) {
                ((PreAnalyzableOperation) c_).preAnalyze(_context.getContext());
            }
            c_ = getAnalyzedNext(c_, _root, list_, _context, _analyzingDoc);
        }
        return list_;
    }

    private static OperationNode getAnalyzedNext(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, Configuration _context, AnalyzingDoc _anaDoc) {
        
        OperationNode next_ = createFirstChild(_current, _context, 0, _anaDoc);
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            return next_;
        }
        OperationNode current_ = _current;
        while (true) {
            _context.getAnalyzing().setOkNumOp(true);
            processAnalyze(_context, current_, _anaDoc);
            if (current_ instanceof ReductibleOperable) {
                ((ReductibleOperable)current_).tryCalculateNode(_context.getContext());
            }
            current_.setOrder(_sortedNodes.size());
            _sortedNodes.add(current_);
            if (current_ instanceof StaticInitOperation) {
                next_ = createFirstChild(current_.getParent(), _context, 1, _anaDoc);
            } else {
                next_ = createNextSibling(current_, _context, _anaDoc);
            }
            MethodOperation par_ = current_.getParent();
            if (next_ != null) {
                if (par_ instanceof AbstractDotOperation) {
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
                _context.getAnalyzing().setOkNumOp(true);
                processAnalyze(_context, par_, _anaDoc);
                ClassArgumentMatching cl_ = par_.getResultClass();
                if (AnaTypeUtil.isPrimitive(cl_, _context.getAnalyzing())) {
                    cl_.setUnwrapObject(cl_);
                }
                par_.tryCalculateNode(_context.getContext());
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

    private static void processAnalyze(Configuration _context, OperationNode _current, AnalyzingDoc _anaDoc) {
        if (_current instanceof InterfaceFctConstructor) {
            _current.analyze(_context.getContext());
            return;
        }
        if (!(_current instanceof AbstractInvokingConstructor)) {
            if (_current instanceof ThisOperation) {
                if (((ThisOperation)_current).isIntermediateDottedOperation()) {
                    FoundErrorInterpret badNb_ = new FoundErrorInterpret();
                    badNb_.setFileName(_anaDoc.getFileName());
                    badNb_.setIndexFile(Configuration.getCurrentLocationIndex(_context.getContext().getAnalyzing(), _anaDoc));
                    badNb_.buildError(_context.getRendAnalysisMessages().getUnexpectedExp());
                    Configuration.addError(badNb_, _anaDoc, _context.getContext().getAnalyzing());
                    return;
                }
            }
            _current.analyze(_context.getContext());
            if (_current instanceof AffectationOperation) {
                OperationNode settable_ = ((AffectationOperation) _current).getSettableOp();
                if (settable_ instanceof SettableAbstractFieldOperation) {
                    SettableAbstractFieldOperation field_ = (SettableAbstractFieldOperation) settable_;
                    FieldInfo info_ = field_.getFieldMetaInfo();
                    if (info_ != null) {
                        if (info_.isFinalField()) {
                            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
                            badNb_.setFileName(_anaDoc.getFileName());
                            badNb_.setIndexFile(Configuration.getCurrentLocationIndex(_context.getContext().getAnalyzing(), _anaDoc));
                            StringBuilder id_ = new StringBuilder();
                            id_.append(info_.getClassField().getClassName());
                            id_.append(";");
                            id_.append(info_.getClassField().getFieldName());
                            badNb_.buildError(_context.getContext().getAnalyzing().getAnalysisMessages().getFinalField(),
                                    id_.toString());
                            Configuration.addError(badNb_, _anaDoc, _context.getContext().getAnalyzing());
                        }
                    }
                }
            }
        } else {
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFileName(_anaDoc.getFileName());
            badNb_.setIndexFile(Configuration.getCurrentLocationIndex(_context.getContext().getAnalyzing(), _anaDoc));
            badNb_.buildError(_context.getRendAnalysisMessages().getUnexpectedExp());
            Configuration.addError(badNb_, _anaDoc, _context.getContext().getAnalyzing());
        }
    }

    private static OperationNode createFirstChild(OperationNode _block, Configuration _context, int _index, AnalyzingDoc _anaDoc) {
        if (!(_block instanceof MethodOperation)) {
            return null;
        }
        MethodOperation block_ = (MethodOperation) _block;
        if (block_.getChildren().isEmpty()) {
            if (isInitializeStaticClassFirst(_index, block_)) {
                Delimiters d_ = block_.getOperations().getDelimiter();
                OperationsSequence opSeq_ = new OperationsSequence();
                opSeq_.setFctName(block_.getOperations().getFctName());
                opSeq_.setDelimiter(d_);
                return new StaticInitOperation(block_.getIndexInEl(), CustList.FIRST_INDEX, block_, opSeq_);
            }
            return null;
        }
        String value_ = block_.getChildren().getValue(0);
        Delimiters d_ = block_.getOperations().getDelimiter();
        int curKey_ = block_.getChildren().getKey(0);
        int offset_ = block_.getIndexInEl()+curKey_;
        if (isInitializeStaticClassFirst(_index, block_)) {
            OperationsSequence opSeq_ = new OperationsSequence();
            opSeq_.setFctName(block_.getOperations().getFctName());
            opSeq_.setDelimiter(d_);
            return new StaticInitOperation(block_.getIndexInEl(), CustList.FIRST_INDEX, block_, opSeq_);
        }
        OperationsSequence r_ = getOperationsSequence(offset_, value_, _context, d_, _anaDoc);
        return createOperationNode(offset_, _index, block_, r_, _context, _anaDoc);
    }

    private static boolean isInitializeStaticClassFirst(int _index, MethodOperation block_) {
        return block_ instanceof StandardInstancingOperation
                && _index == CustList.FIRST_INDEX
                && ((StandardInstancingOperation) block_).isNewBefore();
    }

    private static OperationNode createNextSibling(OperationNode _block, Configuration _context, AnalyzingDoc _anaDoc) {
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
        OperationsSequence r_ = getOperationsSequence(offset_, value_, _context, d_, _anaDoc);
        return createOperationNode(offset_, _block.getIndexChild() + 1, p_, r_, _context, _anaDoc);
    }
    static OperationsSequence getOperationsSequence(int _offset, String _string,
                                                    Configuration _conf, Delimiters _d, AnalyzingDoc _anaDoc) {
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
            if (_anaDoc.isInternGlobal()) {
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
        return ElResolver.getOperationsSequence(_offset, _string, _conf.getContext(), _d);
    }
    static OperationNode createOperationNode(int _index,
                                             int _indexChild, MethodOperation _m, OperationsSequence _op, Configuration _an, AnalyzingDoc _analyzingDoc) {
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        if (_op.getOperators().isEmpty()) {
            String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
            String str_ = originalStr_.trim();
            if (_analyzingDoc.isInternGlobal() && StringList.quickEq(str_, keyWordIntern_)) {
                return new InternGlobalOperation(_index, _indexChild, _m, _op, _analyzingDoc);
            }
        }
        return OperationNode.createOperationNode(_index, _indexChild, _m, _op, _an.getContext());
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
        return Argument.getNullableValue(arguments_.lastValue().getArgument());
    }

    public static IdMap<RendDynOperationNode,ArgumentsPair> getAllArgs(CustList<RendDynOperationNode> _nodes, Configuration _context) {
        IdMap<RendDynOperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<RendDynOperationNode,ArgumentsPair>();
        for (RendDynOperationNode o: _nodes) {
            ArgumentsPair a_ = new ArgumentsPair();
            a_.setArgument(o.getArgument());
            a_.setImplicits(o.getImplicits());
            a_.setImplicitsTest(o.getImplicitsTest());
            arguments_.addEntry(o, a_);
        }
        int fr_ = 0;
        int len_ = _nodes.size();
        while (fr_ < len_) {
            RendDynOperationNode o = arguments_.getKey(fr_);
            ArgumentsPair pair_ = arguments_.getValue(fr_);
            if (!(o instanceof RendCalculableOperation)) {
                Argument a_ = Argument.getNullableValue(o.getArgument());
                o.setSimpleArgument(a_,_context,arguments_);
                Struct st_ = a_.getStruct();
                fr_ = RendDynOperationNode.getNextIndex(o, st_);
                continue;
            }
            if (pair_.getArgument() != null) {
                o.setSimpleArgument(pair_.getArgument(),_context,arguments_);
                Argument res_ = Argument.getNullableValue(pair_.getArgument());
                Struct st_ = res_.getStruct();
                fr_ = RendDynOperationNode.getNextIndex(o, st_);
                continue;
            }
            RendCalculableOperation a_ = (RendCalculableOperation)o;
            a_.calculate(arguments_, _context);
            if (_context.getContext().hasException()) {
                return arguments_;
            }
            Argument res_ = Argument.getNullableValue(pair_.getArgument());
            Struct st_ = res_.getStruct();
            if (o.getNextSibling() != null&&pair_.isArgumentTest()){
                RendMethodOperation par_ = o.getParent();
                if (par_ instanceof RendAndOperation){
                    st_ = BooleanStruct.of(false);
                }
                if (par_ instanceof RendOrOperation){
                    st_ = BooleanStruct.of(true);
                }
                if (par_ instanceof RendCompoundAffectationOperation){
                    RendCompoundAffectationOperation p_ = (RendCompoundAffectationOperation) par_;
                    if (StringList.quickEq(p_.getOper(),"&&=")) {
                        st_ = BooleanStruct.of(false);
                    }
                    if (StringList.quickEq(p_.getOper(),"||=")) {
                        st_ = BooleanStruct.of(true);
                    }
                }
            }
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

    public static void setImplicits(RendDynOperationNode _ex, ContextEl _context){
        ClassArgumentMatching resultClass_ = _ex.getResultClass();
        ImplicitMethods implicits_ = _ex.getImplicits();
        ImplicitMethods implicitsTest_ = _ex.getImplicitsTest();
        ElUtil.setImplicits(_context,resultClass_,implicits_,implicitsTest_);
    }
}
