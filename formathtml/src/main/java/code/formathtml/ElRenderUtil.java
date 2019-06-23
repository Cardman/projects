package code.formathtml;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.BadElError;
import code.expressionlanguage.errors.custom.BadOperandsNumber;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ConstType;
import code.expressionlanguage.instr.Delimiters;
import code.expressionlanguage.instr.ElResolver;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.DotOperation;
import code.expressionlanguage.opers.MethodOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.PossibleIntermediateDotted;
import code.expressionlanguage.opers.StandardInstancingOperation;
import code.expressionlanguage.opers.StaticAccessOperation;
import code.expressionlanguage.opers.StaticInitOperation;
import code.expressionlanguage.opers.exec.ReductibleOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.ErrorStruct;
import code.formathtml.exec.DirectExecCalculableOperation;
import code.formathtml.exec.ExecAffectationOperation;
import code.formathtml.exec.ExecCompoundAffectationOperation;
import code.formathtml.exec.ExecDynOperationNode;
import code.formathtml.exec.ExecMethodOperation;
import code.formathtml.exec.ExecPossibleIntermediateDotted;
import code.formathtml.exec.ExecSemiAffectationOperation;
import code.formathtml.exec.InternGlobalOperation;
import code.formathtml.util.BadElRender;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

public final class ElRenderUtil {
    private ElRenderUtil() {
    }
    public static Argument processEl(String _el, Configuration _conf, int _minIndex, char _begin, char _end) {
        ContextEl context_ = _conf.getContext();
        _conf.setupAnalyzing();
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(_el, _conf, _minIndex, _begin, _end);
        if (d_.getBadOffset() >= 0) {
            _conf.setOffset(d_.getBadOffset());
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getAdvStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
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
        Argument argGl_ = _conf.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        _conf.setStaticContext(static_);
        CustList<OperationNode> all_ = getSortedDescNodes(op_, static_, _conf);
        if (!_conf.getClasses().isEmptyErrors()) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getAdvStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        context_.setAnalyzing(null);
        CustList<ExecDynOperationNode> out_ = getExecutableNodes(all_, _conf);
        calculate(out_, _conf);
        Argument arg_ = out_.last().getArgument();
        return arg_;
    }

    public static Argument processEl(String _el, int _index, Configuration _conf) {
        ContextEl context_ = _conf.getContext();
        _conf.setupAnalyzing();
        Argument argGl_ = _conf.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        _conf.setStaticContext(static_);
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, _index);
        if (d_.getBadOffset() >= 0) {
            _conf.getLastPage().setOffset(d_.getBadOffset());
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getAdvStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = getOperationsSequence(_index, el_, _conf, d_);
        OperationNode op_ = createOperationNode(_index, CustList.FIRST_INDEX, null, opTwo_, _conf);
        CustList<OperationNode> all_ = getSortedDescNodes(op_, static_, _conf);
        if (!_conf.getClasses().isEmptyErrors()) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getAdvStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        context_.setAnalyzing(null);
        CustList<ExecDynOperationNode> out_ = getExecutableNodes(all_, _conf);
        calculate(out_, _conf);
        Argument arg_  = out_.last().getArgument();
        return arg_;
    }
    public static CustList<ExecDynOperationNode> getExecutableNodes(CustList<OperationNode> _list, Analyzable _an) {
        CustList<ExecDynOperationNode> out_ = new CustList<ExecDynOperationNode>();
        OperationNode root_ = _list.last();
        OperationNode current_ = root_;
        ExecDynOperationNode exp_ = (ExecDynOperationNode) ExecDynOperationNode.createExecOperationNode(current_, _an);
        while (true) {
            if (current_ == null) {
                break;
            }
            OperationNode op_ = current_.getFirstChild();
            if (op_ != null) {
                ExecDynOperationNode loc_ = (ExecDynOperationNode) ExecDynOperationNode.createExecOperationNode(op_, _an);
                if (!(exp_ instanceof ExecMethodOperation)) {
                    return out_;
                }
                ((ExecMethodOperation)exp_).appendChild(loc_);
                exp_ = loc_;
                current_ = op_;
                continue;
            }
            while (true) {
                if (exp_ instanceof ExecAffectationOperation) {
                    ((ExecAffectationOperation)exp_).setup();
                }
                if (exp_ instanceof ExecSemiAffectationOperation) {
                    ((ExecSemiAffectationOperation)exp_).setup();
                }
                if (exp_ instanceof ExecCompoundAffectationOperation) {
                    ((ExecCompoundAffectationOperation)exp_).setup();
                }
                out_.add(exp_);
                op_ = current_.getNextSibling();
                if (op_ != null) {
                    ExecDynOperationNode loc_ = (ExecDynOperationNode) ExecDynOperationNode.createExecOperationNode(op_, _an);
                    ExecMethodOperation par_ = exp_.getParent();
                    if (par_ == null) {
                        return out_;
                    }
                    par_.appendChild(loc_);
                    if (op_.getParent() instanceof DotOperation) {
                        exp_.setSiblingSet((ExecPossibleIntermediateDotted) loc_);
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
                ExecMethodOperation par_ = exp_.getParent();
                if (op_ == root_) {
                    if (par_ instanceof ExecAffectationOperation) {
                        ((ExecAffectationOperation)par_).setup();
                    }
                    if (par_ instanceof ExecSemiAffectationOperation) {
                        ((ExecSemiAffectationOperation)par_).setup();
                    }
                    if (par_ instanceof ExecCompoundAffectationOperation) {
                        ((ExecCompoundAffectationOperation)par_).setup();
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
    public static CustList<OperationNode> getSortedDescNodes(OperationNode _root, boolean _staticBlock,Configuration _context) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                break;
            }
            c_ = getAnalyzedNext(c_, _root, list_, _staticBlock, _context);
        }
        return list_;
    }

    private static OperationNode getAnalyzedNext(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, boolean _staticBlock,Configuration _context) {
        
        OperationNode next_ = createFirstChild(_current, _context, 0);
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            return next_;
        }
        OperationNode current_ = _current;
        while (true) {
            _context.setOkNumOp(true);
            current_.analyze(_context);
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
                    if (!(next_ instanceof PossibleIntermediateDotted)) {
                        next_.setRelativeOffsetPossibleAnalyzable(next_.getIndexInEl(), _context);
                        BadOperandsNumber badNb_ = new BadOperandsNumber();
                        badNb_.setFileName(_context.getCurrentFileName());
                        badNb_.setOperandsNumber(0);
                        badNb_.setIndexFile(_context.getCurrentLocationIndex());
                        _context.getClasses().addError(badNb_);
                    } else {
                        PossibleIntermediateDotted possible_ = (PossibleIntermediateDotted) next_;
                        boolean static_ = current_ instanceof StaticAccessOperation;
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
                par_.analyze(_context);
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
    private static OperationNode createFirstChild(OperationNode _block, Configuration _context, int _index) {
        if (!(_block instanceof MethodOperation)) {
            return null;
        }
        MethodOperation block_ = (MethodOperation) _block;
        if (block_.getChildren() == null || block_.getChildren().isEmpty()) {
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
        OperationNode op_ = createOperationNode(offset_, _index, block_, r_, _context);
        return op_;
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
        OperationNode op_ = createOperationNode(offset_, _block.getIndexChild() + 1, p_, r_, _context);
        return op_;
    }
    private static OperationsSequence getOperationsSequence(int _offset, String _string,
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
            int firstPrintChar_ = i_;
            int lastPrintChar_ = len_ - 1;
            while (lastPrintChar_ >= 0) {
                if (!Character.isWhitespace(_string.charAt(lastPrintChar_))) {
                    break;
                }
                lastPrintChar_--;
            }
            len_ = lastPrintChar_+1;
            String sub_ = _string.substring(firstPrintChar_, len_);
            if (_conf.isInternGlobal()) {
                if (StringList.quickEq(sub_, keyWordIntern_)) {
                    OperationsSequence op_ = new OperationsSequence();
                    op_.setConstType(ConstType.WORD);
                    op_.setOperators(new IntTreeMap< String>());
                    op_.setValue(_string, firstPrintChar_);
                    op_.setDelimiter(_d);
                    return op_;
                }
            }
        }
        return ElResolver.getOperationsSequence(_offset, _string, _conf, _d);
    }
    private static OperationNode createOperationNode(int _index,
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
    static void calculate(CustList<ExecDynOperationNode> _nodes, Configuration _context) {     
        int ind_ = 0;
        int len_ = _nodes.size();
        while (ind_ < len_) {
            ExecDynOperationNode curr_ = _nodes.get(ind_);
            if (!(curr_ instanceof DirectExecCalculableOperation)) {
                ind_++;
                continue;
            }
            Argument a_ = curr_.getArgument();
            if (a_ != null) {
                ind_++;
                continue;
            }
            DirectExecCalculableOperation dir_ = (DirectExecCalculableOperation) curr_;
            dir_.calculate(_context);
            a_ = curr_.getArgument();
            if (_context.getException() != null) {
                return;
            }
            ind_ = ExecDynOperationNode.getNextIndex(curr_, a_.getStruct());
        }
        
    }

}
