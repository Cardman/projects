package code.expressionlanguage.instr;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.BadElError;
import code.expressionlanguage.errors.custom.BadOperandsNumber;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.AbstractCoverageResult;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BooleanCoverageResult;
import code.expressionlanguage.methods.util.StandardCoverageResult;
import code.expressionlanguage.opers.*;
import code.expressionlanguage.opers.exec.*;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.structs.Struct;
import code.util.*;

public final class ElUtil {

    private ElUtil() {
    }

    public static Argument tryToCalculate(ContextEl _conf, ExpressionLanguage _right, int _offset) {
        if (_right.isFinished()) {
            return _right.getArgument();
        }
        IdMap<ExecOperationNode, ArgumentsPair> allRight_ = _right.getArguments();
        calculate(allRight_, _right, _conf, _offset);
        if (_conf.callsOrException()) {
            return _right.getArgument();
        }
        _right.finish();
        return _right.getArgument();
    }

    public static CustList<PartOffsetAffect> getFieldNames(int _valueOffset, String _el, ContextEl _conf, Calculation _calcul) {
        boolean hiddenVarTypes_ = _calcul.isStaticBlock();
        _conf.setStaticContext(hiddenVarTypes_);
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, CustList.FIRST_INDEX);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _el, _conf, d_);
        CustList<PartOffsetAffect> names_ = new CustList<PartOffsetAffect>();
        if (opTwo_.getOperators().isEmpty()) {
            for (EntryCust<Integer,String> e: opTwo_.getValues().entryList()) {
                addIfNotEmpty(names_,e.getValue(),_valueOffset+e.getKey(),false);
            }
            return names_;
        }
        if (opTwo_.getPriority() == ElResolver.DECL_PRIO) {
            for (EntryCust<Integer,String> e: opTwo_.getValues().entryList()) {
                addIfNotEmpty(names_,e.getValue(),_valueOffset+e.getKey(),false);
            }
            return names_;
        }
        if (opTwo_.getPriority() == ElResolver.AFF_PRIO) {
            String var_ = opTwo_.getValues().firstValue();
            int off_ = opTwo_.getValues().firstKey();
            addIfNotEmpty(names_,var_,_valueOffset+off_,true);
        }
        return names_;
    }

    private static void addIfNotEmpty(CustList<PartOffsetAffect> _list, String _name, int _offset, boolean _aff) {
        String name_ = getFieldName(_name);
        if (name_.isEmpty()) {
            return;
        }
        int delta_ = StringList.getFirstPrintableCharIndex(_name);
        _list.add(new PartOffsetAffect(new PartOffset(name_,delta_+_offset),_aff || _name.indexOf('=') > -1));
    }
    private static String getFieldName(String _v) {
        String v_ = _v.trim();
        int k_ = 0;
        int lenField_ = v_.length();
        StringBuilder fieldName_ = new StringBuilder();
        while (k_ < lenField_) {
            char fieldChar_ = v_.charAt(k_);
            if (!StringList.isDollarWordChar(fieldChar_)) {
                break;
            }
            fieldName_.append(fieldChar_);
            k_++;
        }
        return fieldName_.toString();
    }
    public static CustList<ExecOperationNode> getAnalyzedOperations(String _el, ContextEl _conf, Calculation _calcul) {
        boolean hiddenVarTypes_ = _calcul.isStaticBlock();
        _conf.setStaticContext(hiddenVarTypes_);
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, CustList.FIRST_INDEX);
        if (d_.getBadOffset() >= 0) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(d_.getBadOffset());
            badEl_.setEl(_el);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(badEl_);
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(new Delimiters());
            ErrorPartOperation e_ = new ErrorPartOperation(0, 0, null, tmpOp_);
            String argClName_ = _conf.getStandards().getAliasObject();
            e_.setResultClass(new ClassArgumentMatching(argClName_));    
            Block currentBlock_ = _conf.getCurrentBlock();
            currentBlock_.defaultAssignmentBefore(_conf, e_);
            e_.tryAnalyzeAssignmentAfter(_conf);
            currentBlock_.defaultAssignmentAfter(_conf, e_);
            e_.setOrder(0);
            return new CustList<ExecOperationNode>((ExecOperationNode)ExecOperationNode.createExecOperationNode(e_));
        }
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _el, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _conf);
        String fieldName_ = _calcul.getFieldName();
        setupStaticContext(_conf, hiddenVarTypes_, op_);
        setFieldName(op_, fieldName_);
        CustList<OperationNode> all_ = getSortedDescNodes(op_, hiddenVarTypes_, _conf);
        return getExecutableNodes(_conf,all_);
    }

    private static void setupStaticContext(ContextEl _conf, boolean _hiddenVarTypes, OperationNode _op) {
        _conf.setStaticContext(_hiddenVarTypes || _op instanceof AbstractInvokingConstructor);
    }

    public static CustList<ExecOperationNode> getAnalyzedOperationsReadOnly(String _el, ContextEl _conf, Calculation _calcul) {
        boolean hiddenVarTypes_ = _calcul.isStaticBlock();
        _conf.setStaticContext(hiddenVarTypes_);
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, CustList.FIRST_INDEX);
        if (d_.getBadOffset() >= 0) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(d_.getBadOffset());
            badEl_.setEl(_el);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(badEl_);
            OperationsSequence tmpOp_ = new OperationsSequence();
            tmpOp_.setDelimiter(new Delimiters());
            ErrorPartOperation e_ = new ErrorPartOperation(0, 0, null, tmpOp_);
            String argClName_ = _conf.getStandards().getAliasObject();
            e_.setResultClass(new ClassArgumentMatching(argClName_));
            e_.setOrder(0);
            return new CustList<ExecOperationNode>((ExecOperationNode)ExecOperationNode.createExecOperationNode(e_));
        }
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _el, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _conf);
        String fieldName_ = _calcul.getFieldName();
        setupStaticContext(_conf, hiddenVarTypes_, op_);
        setFieldName(op_, fieldName_);
        CustList<OperationNode> all_ = getSortedDescNodesReadOnly(op_, hiddenVarTypes_, _conf);
        return getExecutableNodes(_conf,all_);
    }

    private static void setFieldName(OperationNode _op, String _fieldName) {
        if (_op instanceof StandardInstancingOperation) {
            ((StandardInstancingOperation) _op).setFieldName(_fieldName);
        }
    }


    private static CustList<OperationNode> getSortedDescNodes(OperationNode _root, boolean _staticBlock,ContextEl _context) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        Block currentBlock_ = _context.getCurrentBlock();
        if (currentBlock_ != null) {
            currentBlock_.defaultAssignmentBefore(_context, _root);
        }
        OperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                if (currentBlock_ != null) {
                    currentBlock_.defaultAssignmentAfter(_context, _root);
                }
                break;
            }
            preAnalyze(_context, c_);
            c_ = getAnalyzedNext(c_, _root, list_, _staticBlock, _context);
        }
        return list_;
    }


    private static CustList<OperationNode> getSortedDescNodesReadOnly(OperationNode _root, boolean _staticBlock,ContextEl _context) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                break;
            }
            preAnalyze(_context, c_);
            c_ = getAnalyzedNextReadOnly(c_, _root, list_, _staticBlock, _context);
        }
        return list_;
    }

    private static void preAnalyze(ContextEl _context, OperationNode _c) {
        if (_c instanceof PreAnalyzableOperation) {
            ((PreAnalyzableOperation) _c).preAnalyze(_context);
        }
    }

    private static OperationNode getAnalyzedNext(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, boolean _staticBlock,ContextEl _context) {
        
        OperationNode next_ = createFirstChild(_current, _context, 0);
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            ((MethodOperation) _current).tryAnalyzeAssignmentBefore(_context, next_);
            return next_;
        }
        OperationNode current_ = _current;
        while (true) {
            _context.setOkNumOp(true);
            current_.analyze(_context);
            current_.setOrder(_sortedNodes.size());
            tryCalculateNode(_context, current_);
            current_.tryAnalyzeAssignmentAfter(_context);
            _sortedNodes.add(current_);
            next_ = processNext(_context, current_);
            MethodOperation par_ = current_.getParent();
            if (next_ != null) {
                processDot(_context, next_, current_, par_);
                par_.appendChild(next_);
                par_.tryAnalyzeAssignmentBeforeNextSibling(_context, next_, current_);
                return next_;
            }
            if (par_ == _root) {
                _context.setOkNumOp(true);
                par_.analyze(_context);
                ClassArgumentMatching cl_ = par_.getResultClass();
                unwrapPrimitive(_context, par_, cl_);
                par_.tryCalculateNode(_context);
                par_.tryAnalyzeAssignmentAfter(_context);
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

    private static void tryCalculateNode(ContextEl _context, OperationNode _current) {
        if (_current instanceof ReductibleOperable) {
            ((ReductibleOperable) _current).tryCalculateNode(_context);
        }
    }

    private static void processDot(ContextEl _context, OperationNode _next, OperationNode _current, MethodOperation _par) {
        if (_par instanceof DotOperation) {
            if (!(_next instanceof PossibleIntermediateDotted)) {
                _next.setRelativeOffsetPossibleAnalyzable(_next.getIndexInEl(), _context);
                BadOperandsNumber badNb_ = new BadOperandsNumber();
                badNb_.setFileName(_context.getCurrentFileName());
                badNb_.setOperandsNumber(0);
                badNb_.setIndexFile(_context.getCurrentLocationIndex());
                _context.getClasses().addError(badNb_);
            } else {
                PossibleIntermediateDotted possible_ = (PossibleIntermediateDotted) _next;
                boolean static_ = _current instanceof StaticAccessOperation;
                possible_.setIntermediateDotted();
                possible_.setPreviousArgument(_current.getArgument());
                possible_.setPreviousResultClass(_current.getResultClass(), static_);
                _current.setSiblingSet(possible_);
            }
        }
    }

    private static OperationNode getAnalyzedNextReadOnly(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, boolean _staticBlock,ContextEl _context) {

        OperationNode next_ = createFirstChild(_current, _context, 0);
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            return next_;
        }
        OperationNode current_ = _current;
        while (true) {
            _context.setOkNumOp(true);
            current_.analyze(_context);
            current_.setOrder(_sortedNodes.size());
            tryCalculateNode(_context, current_);
            _sortedNodes.add(current_);
            next_ = processNext(_context, current_);
            MethodOperation par_ = current_.getParent();
            if (next_ != null) {
                processDot(_context, next_, current_, par_);
                par_.appendChild(next_);
                return next_;
            }
            if (par_ == _root) {
                _context.setOkNumOp(true);
                par_.analyze(_context);
                ClassArgumentMatching cl_ = par_.getResultClass();
                unwrapPrimitive(_context, par_, cl_);
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

    private static void unwrapPrimitive(ContextEl _context, MethodOperation _par, ClassArgumentMatching _cl) {
        if (PrimitiveTypeUtil.isPrimitive(_cl, _context)) {
            _cl.setUnwrapObject(_cl);
            _par.cancelArgument();
        }
    }

    private static OperationNode processNext(ContextEl _context, OperationNode _current) {
        OperationNode next_;
        if (_current instanceof StaticInitOperation) {
            next_ = createFirstChild(_current.getParent(), _context, 1);
        } else {
            next_ = createNextSibling(_current, _context);
        }
        return next_;
    }

    private static OperationNode createFirstChild(OperationNode _block, ContextEl _context, int _index) {
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
        OperationsSequence r_ = ElResolver.getOperationsSequence(offset_, value_, _context, d_);
        OperationNode op_ = OperationNode.createOperationNode(offset_, _index, block_, r_, _context);
        return op_;
    }

    private static OperationNode createNextSibling(OperationNode _block, ContextEl _context) {
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
        OperationsSequence r_ = ElResolver.getOperationsSequence(offset_, value_, _context, d_);
        OperationNode op_ = OperationNode.createOperationNode(offset_, _block.getIndexChild() + 1, p_, r_, _context);
        return op_;
    }
    public static CustList<OperationNode> filterInvoking(CustList<OperationNode> _list) {
        CustList<OperationNode> out_ = new CustList<OperationNode>();
        for (OperationNode o: _list) {
            if (o instanceof StaticInitOperation) {
                continue;
            }
            out_.add(o);
        }
        return out_;
    }
    public static boolean isInlineDeclaringField(Operable _var, Analyzable _an) {
        if (!_an.isGearConst()) {
            return false;
        }
        return isDeclaringField(_var,_an);
    }
    public static boolean isDeclaringField(Operable _var, Analyzable _an) {
        Block bl_ = _an.getCurrentBlock();
        if (!(bl_ instanceof FieldBlock)) {
            return false;
        }
        if (!(_var instanceof StandardFieldOperable)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }

    public static boolean isDeclaringLoopVariable(MutableLoopVariableOperation _var, Analyzable _an) {
        if (!isDeclaringLoopVariable(_an)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }
    public static boolean isDeclaringLoopVariable(MethodOperation _par, Analyzable _an) {
        if (!isDeclaringLoopVariable(_an)) {
            return false;
        }
        return isDeclaringVariable(_par);
    }
    public static boolean isDeclaringLoopVariable(Analyzable _an) {
        if (!_an.isMerged()) {
            return false;
        }
        if (!_an.hasLoopDeclarator()) {
            return false;
        }
        if (_an.getForLoopPartState() != ForLoopPart.INIT) {
            return false;
        }
        return true;
    }
    public static boolean isDeclaringVariable(VariableOperation _var, Analyzable _an) {
        if (!isDeclaringVariable(_an)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }
    public static boolean isDeclaringVariable(MethodOperation _par, Analyzable _an) {
        if (!isDeclaringVariable(_an)) {
            return false;
        }
        return isDeclaringVariable(_par);
    }
    public static boolean isDeclaringVariable(Analyzable _an) {
        if (!_an.isMerged()) {
            return false;
        }
        if (!_an.hasDeclarator()) {
            return false;
        }
        return true;
    }
    public static boolean isDeclaringVariable(Operable _var) {
        ParentOperable par_ = _var.getParentOperable();
        if (par_ == null) {
            return true;
        }
        if (par_ instanceof DeclaringOperable) {
            return true;
        }
        if (par_ instanceof AffectationOperable) {
            if (par_.getParentOperable() == null) {
                return _var == par_.getFirstChildOperable();
            }
            if (par_.getParentOperable() instanceof DeclaringOperable) {
                return _var == par_.getFirstChildOperable();
            }
        }
        return false;
    }
    public static boolean isDeclaringVariable(MethodOperation _par) {
        if (_par == null) {
            return true;
        }
        if (_par instanceof DeclaringOperable) {
            return true;
        }
        if (_par instanceof AffectationOperable) {
            if (_par.getParent() == null) {
                return null == _par.getFirstChild();
            }
            if (_par.getParent() instanceof DeclaringOperable) {
                return null == _par.getFirstChild();
            }
        }
        return false;
    }
    public static boolean checkFinalVar(Analyzable _conf, Assignment _ass) {
        if (!_ass.isUnassignedAfter()) {
            return true;
        }
        return stepForLoop(_conf);
    }
    public static boolean checkFinalField(Analyzable _conf, SettableAbstractFieldOperation _cst, StringMap<Assignment> _ass) {
        boolean fromCurClass_ = _cst.isFromCurrentClass(_conf);
        ClassField cl_ = _cst.getFieldId();
        if (cl_ == null) {
            return false;
        }
        String fieldName_ = cl_.getFieldName();
        if (stepForLoop(_conf)) {
            return true;
        }
        return checkFinal(_conf, _cst, _ass, fromCurClass_, cl_, fieldName_);
    }

    public static boolean checkFinalFieldReadOnly(Analyzable _conf, SettableAbstractFieldOperation _cst, StringMap<Assignment> _ass) {
        boolean fromCurClass_ = _cst.isFromCurrentClass(_conf);
        ClassField cl_ = _cst.getFieldIdReadOnly();
        FieldInfo meta_ = _conf.getFieldInfo(cl_);
        if (meta_ == null) {
            return false;
        }
        String fieldName_ = cl_.getFieldName();
        return meta_.isFinalField() && checkFinal(_conf, _cst, _ass, fromCurClass_, cl_, fieldName_);
    }
    private static boolean checkFinal(Analyzable _conf, SettableAbstractFieldOperation _cst, StringMap<Assignment> _ass, boolean _fromCurClass, ClassField _cl, String _fieldName) {
        boolean checkFinal_;
        if (_conf.getContextEl().isAssignedFields()) {
            checkFinal_ = true;
        } else if (_conf.getContextEl().isAssignedStaticFields()) {
            FieldInfo meta_ = _conf.getFieldInfo(_cl);
            if (meta_.isStaticField()) {
                checkFinal_ = true;
            } else if (!_fromCurClass) {
                checkFinal_ = true;
            } else {
                if (isDeclaringField(_cst, _conf)) {
                    checkFinal_ = false;
                } else {
                    checkFinal_ = false;
                    for (EntryCust<String, Assignment> e: _ass.entryList()) {
                        if (!StringList.quickEq(e.getKey(), _fieldName)) {
                            continue;
                        }
                        if (e.getValue().isUnassignedAfter()) {
                            continue;
                        }
                        checkFinal_ = true;
                    }
                }
            }
        } else if (!_fromCurClass) {
            checkFinal_ = true;
        } else {
            if (isDeclaringField(_cst, _conf)) {
                checkFinal_ = false;
            } else {
                checkFinal_ = false;
                for (EntryCust<String, Assignment> e: _ass.entryList()) {
                    if (!StringList.quickEq(e.getKey(), _fieldName)) {
                        continue;
                    }
                    if (e.getValue().isUnassignedAfter()) {
                        continue;
                    }
                    checkFinal_ = true;
                }
            }
        }
        return checkFinal_;
    }

    private static boolean stepForLoop(Analyzable _conf) {
        if (_conf.getCurrentBlock() instanceof ForMutableIterativeLoop) {
            return _conf.getForLoopPartState() == ForLoopPart.STEP;
        }
        return false;
    }

    private static void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExpressionLanguage _el, ContextEl _context, int _offset) {
        AbstractPageEl pageEl_ = _context.getLastPage();
        pageEl_.setTranslatedOffset(_offset);
        int fr_ = _el.getIndex();
        int len_ = _nodes.size();
        while (fr_ < len_) {
            ExecOperationNode o = _nodes.getKey(fr_);
            ArgumentsPair pair_ = _nodes.getValue(fr_);
            if (!(o instanceof AtomicExecCalculableOperation)) {
                _context.getCoverage().passBlockOperation(_context,o,Argument.createVoid(),true);
                fr_++;
                continue;
            }
            if (pair_.getArgument() != null) {
                _context.getCoverage().passBlockOperation(_context,o,pair_.getArgument(),true);
                fr_++;
                continue;
            }
            AtomicExecCalculableOperation a_ = (AtomicExecCalculableOperation)o;
            a_.calculate(_nodes, _context);
            if (_context.hasExceptionOrFailInit()) {
                pageEl_.setTranslatedOffset(0);
                pageEl_.clearCurrentEls();
                return;
            }
            if (_context.calls()) {
                _el.setCurrentOper(o);
                return;
            }
            Argument res_ = pair_.getArgument();
            Struct st_ = res_.getStruct();
            fr_ = ExecOperationNode.getNextIndex(o, st_);
        }
        _context.getLastPage().setTranslatedOffset(0);
    }
    public static void buildCoverageReport(ContextEl _cont,int _offsetBlock,
                                           Block _block,
                                           CustList<ExecOperationNode> _nodes,
                                           int _endBlock,
                                           CustList<PartOffset> _parts) {
        buildCoverageReport(_cont,_offsetBlock,_block,_nodes,_endBlock,_parts,0,"",false);
    }
    public static void buildCoverageReport(ContextEl _cont,int _offsetBlock,
                                           Block _block,
                                           CustList<ExecOperationNode> _nodes,
                                           int _endBlock,
                                           CustList<PartOffset> _parts, int _tr, String _fieldName, boolean _annotation) {
        ExecOperationNode root_ = _nodes.last();
        ExecOperationNode curOp_ = root_;
        int sum_ = _tr + _offsetBlock - _fieldName.length();
        String currentFileName_ = _cont.getCoverage().getCurrentFileName();
        boolean addCover_ = !(_block instanceof CaseCondition) && !(_block instanceof AnnotationMethodBlock) && !_annotation;
        while (true) {
            OperationNode val_ = getMapping(_cont, _block, curOp_, _annotation);
            AbstractCoverageResult result_ = getCovers(_cont, _block, curOp_, _annotation);
            String tag_;
            if (!addCover_ ||val_ instanceof StaticInitOperation) {
                tag_ = "";
            } else if (val_.getArgument() != null && val_.getParent() != null && val_.getParent().getArgument() != null) {
                ExecMethodOperation parent_ = curOp_.getParent();
                AbstractCoverageResult resultPar_ = getCovers(_cont, _block, parent_, _annotation);
                if (resultPar_.isPartialCovered()) {
                    tag_ = "<span class=\"f\">";
                } else {
                    tag_ = "<span class=\"n\">";
                }
            } else if (result_.isFullCovered()) {
                tag_ = "<span class=\"f\">";
            } else if (result_.isPartialCovered()) {
                if (val_ instanceof AffectationOperation && val_.getFirstChild().getNextSibling().getArgument() != null) {
                    tag_ = "<span class=\"f\">";
                } else {
                    tag_ = "<span class=\"p\">";
                }
            } else {
                tag_ = "<span class=\"n\">";
            }
            if (curOp_ != root_ || _fieldName.isEmpty()) {
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()));
            }
            if (curOp_ instanceof NamedCalledOperation) {
                if (val_ instanceof FctOperation) {
                    _parts.addAllElts(((FctOperation)val_).getPartOffsets());
                }
                if (val_ instanceof ChoiceFctOperation) {
                    _parts.addAllElts(((ChoiceFctOperation)val_).getPartOffsets());
                }
                if (val_ instanceof SuperFctOperation) {
                    _parts.addAllElts(((SuperFctOperation)val_).getPartOffsets());
                }
                int delta_ = ((NamedCalledOperation) curOp_).getDelta();
                ClassMethodId classMethodId_ = ((NamedCalledOperation) curOp_).getClassMethodId();
                String className_ = classMethodId_.getClassName();
                className_ = Templates.getIdFromAllTypes(className_);
                MethodId id_ = classMethodId_.getConstraints();
                GeneType type_ = _cont.getClassBody(className_);
                if (isFromCustFile(type_)) {
                    String file_ = ((RootBlock) type_).getFile().getRenderFileName();
                    String rel_ = getRelativize(_cont, currentFileName_, className_, id_, type_, file_);
                    tag_ = "<a title=\""+transform(className_ +"."+ id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                    _parts.add(new PartOffset(tag_,sum_ +delta_+ val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,sum_ +delta_+ val_.getIndexInEl()+id_.getName().length()));
                }
            }
            if (val_ instanceof AnnotationInstanceOperation) {
                _parts.addAllElts(((AnnotationInstanceOperation)val_).getPartOffsets());
            }
            if (curOp_ instanceof ExecVariableOperation) {
                String varName_ = ((ExecVariableOperation) curOp_).getVariableName();
                int delta_ = ((ExecVariableOperation) curOp_).getOff();
                if (_block instanceof Line && _block.getPreviousSibling() instanceof DeclareVariable && isDeclaringVariable(curOp_)) {
                    MethodOperation parAn_ = val_.getParent();
                    ExecMethodOperation par_ = curOp_.getParent();
                    int index_ = curOp_.getIndexChild();
                    if (par_ instanceof ExecAffectationOperation) {
                        index_ = par_.getIndexChild();
                        parAn_ = parAn_.getParent();
                    }
                    if (parAn_ == null) {
                        tag_ = "<a name=\"m"+ _offsetBlock +"\">";
                        _cont.getCoverage().getLocalVars().put(varName_, _offsetBlock);
                        _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                        tag_ = "</a>";
                        _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                    } else {
                        int id_ = parAn_.getChildren().getKey(index_);
                        id_ += StringList.getFirstPrintableCharIndex(parAn_.getChildren().getValue(index_));
                        id_ += _offsetBlock;
                        tag_ = "<a name=\"m"+id_+"\">";
                        _cont.getCoverage().getLocalVars().put(varName_,id_);
                        _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                        tag_ = "</a>";
                        _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                    }

                } else {
                    Integer id_ = _cont.getCoverage().getLocalVars().getVal(varName_);
                    tag_ = "<a href=\"#m"+id_+"\">";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                }
            }
            if (curOp_ instanceof ExecMutableLoopVariableOperation) {
                String varName_ = ((ExecMutableLoopVariableOperation) curOp_).getVariableName();
                int delta_ = ((ExecMutableLoopVariableOperation) curOp_).getOff();
                if (_block instanceof ForMutableIterativeLoop && _cont.getCoverage().isPossibleDeclareLoopVars() && isDeclaringVariable(curOp_)) {
                    MethodOperation parAn_ = val_.getParent();
                    ExecMethodOperation par_ = curOp_.getParent();
                    int index_ = curOp_.getIndexChild();
                    if (par_ instanceof ExecAffectationOperation) {
                        index_ = par_.getIndexChild();
                        parAn_ = parAn_.getParent();
                    }
                    if (parAn_ == null) {
                        tag_ = "<a name=\"m"+ _offsetBlock +"\">";
                        _cont.getCoverage().getMutableVars().put(varName_, _offsetBlock);
                        _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                        tag_ = "</a>";
                        _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                    } else {
                        int id_ = parAn_.getChildren().getKey(index_);
                        id_ += StringList.getFirstPrintableCharIndex(parAn_.getChildren().getValue(index_));
                        id_ += _offsetBlock;
                        tag_ = "<a name=\"m"+id_+"\">";
                        _cont.getCoverage().getMutableVars().put(varName_,id_);
                        _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                        tag_ = "</a>";
                        _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                    }

                } else {
                    Integer id_ = _cont.getCoverage().getMutableVars().getVal(varName_);
                    tag_ = "<a href=\"#m"+id_+"\">";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                }
            }
            if (curOp_ instanceof ExecFinalVariableOperation) {
                String varName_ = ((ExecFinalVariableOperation) curOp_).getVariableName();
                int delta_ = ((ExecFinalVariableOperation) curOp_).getOff();
                ConstType type_ = ((ExecFinalVariableOperation) curOp_).getType();
                if (type_ == ConstType.LOOP_VAR) {
                    Integer id_ = _cont.getCoverage().getLoopVars().getVal(varName_);
                    tag_ = "<a href=\"#m"+id_+"\">";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                } else if (type_ == ConstType.LOOP_INDEX) {
                    int deltaLoc_ = ((FinalVariableOperation)val_).getDelta();
                    Integer id_ = _cont.getCoverage().getLoopVars().getVal(varName_);
                    tag_ = "<a href=\"#m"+id_+"\">";
                    _parts.add(new PartOffset(tag_,deltaLoc_+delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,deltaLoc_+delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                } else if (type_ == ConstType.CATCH_VAR) {
                    Integer id_ = _cont.getCoverage().getCatchVars().getVal(varName_);
                    tag_ = "<a href=\"#m"+id_+"\">";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                } else {
                    Integer id_ = _cont.getCoverage().getParamVars().getVal(varName_);
                    tag_ = "<a href=\"#m"+id_+"\">";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                }
            }
            if (curOp_ instanceof ExecValueOperation) {
                int delta_ = ((ValueOperation) val_).getOff();
                tag_ = "<b>";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                tag_ = "</b>";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+_cont.getKeyWords().getKeyWordValue().length()));
            }
            if (curOp_ instanceof ExecAssocationOperation) {
                AssocationOperation a_ = (AssocationOperation) val_;
                String fieldName_ = a_.getFieldName();
                String annotation_ = a_.getAnnotation();
                ClassField c_ = new ClassField(annotation_,fieldName_);
                int delta_ = a_.getSum();
                updateFieldAnchor(_cont,_parts,c_,sum_ +delta_+ val_.getIndexInEl(),fieldName_.length(),true);
            }
            if (curOp_ instanceof ExecSettableFieldOperation) {
                if (_block instanceof FieldBlock && isDeclaringVariable(curOp_)) {
                    ClassField c_ = ((ExecSettableFieldOperation)curOp_).getFieldId();
                    int id_;
                    if (curOp_.getParent() instanceof ExecAffectationOperation) {
                        id_ = ((FieldBlock) _block).getValuesOffset().get(curOp_.getParent().getIndexChild());
                    } else {
                        id_ = ((FieldBlock) _block).getValuesOffset().get(curOp_.getIndexChild());
                    }
                    tag_ = "<a name=\"m"+id_+"\">";
                    int d_ = ((ExecSettableFieldOperation)curOp_).getDelta();
                    _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+d_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+d_+c_.getFieldName().length()));
                } else {
                    _parts.addAllElts(((SettableAbstractFieldOperation) val_).getPartOffsets());
                    ClassField c_ = ((ExecSettableFieldOperation)curOp_).getFieldId();
                    int delta_ = ((SettableAbstractFieldOperation) val_).getOff();
                    updateFieldAnchor(_cont,_parts,c_,sum_ +delta_+ val_.getIndexInEl() + ((ExecSettableFieldOperation)curOp_).getDelta(),c_.getFieldName().length(),false);
                }
            }
            if (curOp_ instanceof ExecStandardInstancingOperation) {
                String cl_ = ((ExecStandardInstancingOperation)curOp_).getClassName();
                cl_ = Templates.getIdFromAllTypes(cl_);
                ConstructorId c_ = ((ExecStandardInstancingOperation)curOp_).getConstId();
                GeneType type_ = _cont.getClassBody(cl_);
                if (isFromCustFile(type_)) {
                    String file_ = ((RootBlock) type_).getFile().getRenderFileName();
                    CustList<GeneConstructor> ctors_ = Classes.getConstructorBodiesById(_cont, cl_, c_);
                    StandardInstancingOperation inst_ = (StandardInstancingOperation) val_;
                    if (!ctors_.isEmpty() && inst_.getFieldName().isEmpty()) {
                        ConstructorBlock ctor_ = (ConstructorBlock) ctors_.first();
                        String rel_ = relativize(currentFileName_, file_ + "#m" + ctor_.getNameOffset());
                        tag_ = "<a title=\""+ transform(cl_ +"."+ c_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                        int offsetNew_ =StringList.getFirstPrintableCharIndex(inst_.getMethodName());
                        _parts.add(new PartOffset(tag_,offsetNew_+sum_ + val_.getIndexInEl()));
                        tag_ = "</a>";
                        _parts.add(new PartOffset(tag_,offsetNew_+sum_ + val_.getIndexInEl()+_cont.getKeyWords().getKeyWordNew().length()));
                    }
                }
                _parts.addAllElts(((StandardInstancingOperation)val_).getPartOffsets());
            }
            if (curOp_ instanceof ExecDimensionArrayInstancing) {
                _parts.addAllElts(((DimensionArrayInstancing)val_).getPartOffsets());
            }
            if (val_ instanceof ElementArrayInstancing) {
                _parts.addAllElts(((ElementArrayInstancing)val_).getPartOffsets());
            }
            if (curOp_ instanceof ExecLambdaOperation) {
                ClassMethodId classMethodId_ = ((ExecLambdaOperation) curOp_).getMethod();
                ConstructorId realId_ = ((ExecLambdaOperation) curOp_).getRealId();
                ClassField fieldId_ = ((ExecLambdaOperation) curOp_).getFieldId();
                if (classMethodId_ != null) {
                    String className_ = classMethodId_.getClassName();
                    className_ = Templates.getIdFromAllTypes(className_);
                    MethodId id_ = classMethodId_.getConstraints();
                    if (!StringList.isDollarWord(id_.getName()) && !id_.getName().startsWith("[]")) {
                        OperatorBlock operator_ = Classes.getOperatorsBodiesById(_cont, id_).first();
                        String file_ = operator_.getFile().getRenderFileName();
                        String rel_ = relativize(currentFileName_, file_ + "#m" + operator_.getNameOffset());
                        tag_ = "<a title=\""+ transform(id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                        _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()));
                        tag_ = "</a>";
                        _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+_cont.getKeyWords().getKeyWordLambda().length()));
                    } else {
                        GeneType type_ = _cont.getClassBody(className_);
                        if (isFromCustFile(type_)) {
                            String file_ = ((RootBlock) type_).getFile().getRenderFileName();
                            String rel_ = getRelativize(_cont, currentFileName_, className_, id_, type_, file_);
                            tag_ = "<a title=\""+transform(className_ +"."+ id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                            _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()));
                            tag_ = "</a>";
                            _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+_cont.getKeyWords().getKeyWordLambda().length()));
                        }
                    }
                } else if (realId_ != null) {
                    String cl_ = ((ExecLambdaOperation) curOp_).getFoundClass();
                    cl_ = Templates.getIdFromAllTypes(cl_);
                    GeneType type_ = _cont.getClassBody(cl_);
                    if (isFromCustFile(type_)) {
                        String file_ = ((RootBlock) type_).getFile().getRenderFileName();
                        CustList<GeneConstructor> ctors_ = Classes.getConstructorBodiesById(_cont, cl_, realId_);
                        if (!ctors_.isEmpty()) {
                            ConstructorBlock ctor_ = (ConstructorBlock) ctors_.first();
                            String rel_ = relativize(currentFileName_, file_ + "#m" + ctor_.getNameOffset());
                            tag_ = "<a title=\""+ transform(cl_ +"."+ realId_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                            _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()));
                            tag_ = "</a>";
                            _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+_cont.getKeyWords().getKeyWordLambda().length()));
                        }
                    }
                } else {
                    updateFieldAnchor(_cont,_parts,fieldId_,sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordLambda().length(),false);
                }
                _parts.addAllElts(((LambdaOperation)val_).getPartOffsets());
            }
            if (curOp_ instanceof ExecIdFctOperation) {
                _parts.addAllElts(((IdFctOperation)val_).getPartOffsets());
            }
            if (curOp_ instanceof ExecVarargOperation) {
                _parts.addAllElts(((VarargOperation)val_).getPartOffsets());
            }
            if (curOp_ instanceof ExecForwardOperation) {
                _parts.addAllElts(((ForwardOperation)val_).getPartOffsets());
            }
            if (curOp_ instanceof ExecDefaultValueOperation) {
                _parts.addAllElts(((DefaultValueOperation)val_).getPartOffsets());
            }
            if (curOp_ instanceof ExecStaticInfoOperation) {
                _parts.addAllElts(((StaticInfoOperation)val_).getPartOffsets());
            }
            if (curOp_ instanceof ExecStaticAccessOperation) {
                _parts.addAllElts(((StaticAccessOperation)val_).getPartOffsets());
            }
            if (curOp_ instanceof ExecCallDynMethodOperation) {
                tag_ = "<b>";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()));
                tag_ = "</b>";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+_cont.getStandards().getAliasCall().length()));
            }
            if (curOp_ instanceof ExecAbstractInvokingConstructor) {
                ConstructorId c_ = ((ExecAbstractInvokingConstructor)curOp_).getConstId();
                String cl_ = c_.getName();
                cl_ = Templates.getIdFromAllTypes(cl_);
                RootBlock type_ = (RootBlock) _cont.getClassBody(cl_);
                String file_ = type_.getFile().getRenderFileName();
                ConstructorBlock ctor_ = (ConstructorBlock) Classes.getConstructorBodiesById(_cont, cl_, c_).first();
                String rel_ = relativize(currentFileName_, file_ + "#m" + ctor_.getNameOffset());
                tag_ = "<a title=\""+ transform(cl_ +"."+ c_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+((AbstractInvokingConstructor)val_).getOffsetOper()));
            }
            if (curOp_ instanceof ExecCustNumericOperation && curOp_.getFirstChild().getNextSibling() == null) {
                ExecCustNumericOperation par_ = (ExecCustNumericOperation) curOp_;
                ClassMethodId classMethodId_ = par_.getClassMethodId();
                MethodId id_ = classMethodId_.getConstraints();
                OperatorBlock operator_ = Classes.getOperatorsBodiesById(_cont, id_).first();
                String file_ = operator_.getFile().getRenderFileName();
                String rel_ = relativize(currentFileName_, file_ + "#m" + operator_.getNameOffset());
                tag_ = "<a title=\""+ transform(id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+par_.getOpOffset()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+par_.getOpOffset()+id_.getName().length()));
            }
            if (curOp_ instanceof ExecUnaryBooleanOperation) {
                if (!result_.isFullCovered() && result_.isPartialCovered()) {
                    int offsetOp_ = val_.getOperations().getOperators().firstKey();
                    if(((BooleanCoverageResult)result_).isCoverTrue()){
                        tag_ = "<a title=\""+ transform(_cont.getStandards().getTrueString())+"\">";
                        _parts.add(new PartOffset(tag_, sum_ + val_.getIndexInEl()+offsetOp_));
                        tag_ = "</a>";
                        _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+offsetOp_+1));
                    } else {
                        tag_ = "<a title=\""+ transform(_cont.getStandards().getFalseString())+"\">";
                        _parts.add(new PartOffset(tag_, sum_ + val_.getIndexInEl()+offsetOp_));
                        tag_ = "</a>";
                        _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+offsetOp_+1));
                    }
                }
            }
            if (curOp_ instanceof ExecCastOperation) {
                _parts.addAllElts(((CastOperation)val_).getPartOffsets());
            }
            if (curOp_ instanceof ExecSemiAffectationOperation) {
                ExecSemiAffectationOperation par_ = (ExecSemiAffectationOperation) curOp_;
                int offsetOp_ = val_.getOperations().getOperators().firstKey();
                if(!par_.isPost()) {
                    ClassMethodId classMethodId_ = par_.getClassMethodId();
                    if (classMethodId_ != null) {
                        MethodId id_ = classMethodId_.getConstraints();
                        OperatorBlock operator_ = Classes.getOperatorsBodiesById(_cont, id_).first();
                        String file_ = operator_.getFile().getRenderFileName();
                        String rel_ = relativize(currentFileName_, file_ + "#m" + operator_.getNameOffset());
                        tag_ = "<a title=\""+ transform(id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                        _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+offsetOp_));
                        tag_ = "</a>";
                        _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+offsetOp_+1));
                    }
                    if (par_.getSettable() instanceof ExecCustArrOperation) {
                        ExecCustArrOperation parArr_ = (ExecCustArrOperation) par_.getSettable();
                        ClassMethodId classMethodIdArr_ = parArr_.getClassMethodId();
                        String className_ = classMethodIdArr_.getClassName();
                        className_ = Templates.getIdFromAllTypes(className_);
                        MethodId methodId_ = classMethodIdArr_.getConstraints();
                        MethodId id_ = new MethodId(false,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
                        RootBlock type_ = (RootBlock) _cont.getClassBody(className_);
                        OverridableBlock method_ = Classes.getMethodBodiesById(_cont, className_, id_).first();
                        String file_ = type_.getFile().getRenderFileName();
                        String rel_ = relativize(currentFileName_, file_ + "#m" + method_.getNameOffset());
                        tag_ = "<a title=\""+ transform(className_ +"."+ id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                        _parts.add(new PartOffset(tag_, sum_ + val_.getIndexInEl()+offsetOp_+1));
                        tag_ = "</a>";
                        _parts.add(new PartOffset(tag_, sum_ + val_.getIndexInEl()+offsetOp_+2));
                    }
                }
            }
            if (curOp_ instanceof ExecCustArrOperation) {
                ExecCustArrOperation par_ = (ExecCustArrOperation) curOp_;
                ClassMethodId classMethodId_ = par_.getClassMethodId();
                String className_ = classMethodId_.getClassName();
                className_ = Templates.getIdFromAllTypes(className_);
                MethodId methodId_ = classMethodId_.getConstraints();
                MethodId id_;
                if (par_.resultCanBeSet()) {
                    id_ = new MethodId(false,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
                } else {
                    id_ = new MethodId(false,"[]",methodId_.getParametersTypes(),methodId_.isVararg());
                }
                RootBlock type_ = (RootBlock) _cont.getClassBody(className_);
                OverridableBlock method_ = Classes.getMethodBodiesById(_cont, className_, id_).first();
                String file_ = type_.getFile().getRenderFileName();
                int offsetEnd_ = sum_ + val_.getIndexInEl();
                String rel_ = relativize(currentFileName_, file_ + "#m" + method_.getNameOffset());
                tag_ = "<a title=\""+ transform(className_ +"."+ id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                _parts.add(new PartOffset(tag_, offsetEnd_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, offsetEnd_+1));
            }
            ExecOperationNode firstChildOp_ = curOp_.getFirstChild();
            if (firstChildOp_ != null) {
                curOp_ = firstChildOp_;
                continue;
            }
            boolean stopOp_ = false;
            while (true) {
                MethodOperation parent_ = val_.getParent();
                int offsetEnd_ = 0;
                if (parent_ != null) {
                    int indexChild_ = val_.getIndexChild();
                    if (parent_ instanceof StandardInstancingOperation && parent_.getFirstChild() instanceof StaticInitOperation) {
                        indexChild_--;
                    }
                    IntTreeMap<String> children_ = parent_.getChildren();
                    if (indexChild_ > -1) {
                        offsetEnd_ = sum_ + val_.getIndexInEl() + children_.getValue(indexChild_).length();
                    } else {
                        offsetEnd_ = sum_ + val_.getIndexInEl();
                    }
                }
                if (!addCover_ || val_ instanceof StaticInitOperation) {
                    tag_ = "";
                } else {
                    tag_ = "</span>";
                }
                ExecOperationNode nextSiblingOp_ = curOp_.getNextSibling();
                ExecMethodOperation parentOp_ = curOp_.getParent();
                if (nextSiblingOp_ != null) {
                    _parts.add(new PartOffset(tag_,offsetEnd_));
                    if (parentOp_ instanceof ExecCatOperation) {
                        if (canCallToString(curOp_.getResultClass(),_cont) || canCallToString(nextSiblingOp_.getResultClass(),_cont)) {
                            tag_ = "<i>";
                            _parts.add(new PartOffset(tag_, offsetEnd_));
                            tag_ = "</i>";
                            _parts.add(new PartOffset(tag_,offsetEnd_+1));
                        }
                    }
                    if (parentOp_ instanceof ExecCustNumericOperation) {
                        ExecCustNumericOperation par_ = (ExecCustNumericOperation) parentOp_;
                        ClassMethodId classMethodId_ = par_.getClassMethodId();
                        MethodId id_ = classMethodId_.getConstraints();
                        OperatorBlock operator_ = Classes.getOperatorsBodiesById(_cont, id_).first();
                        String file_ = operator_.getFile().getRenderFileName();
                        String rel_ = relativize(currentFileName_, file_ + "#m" + operator_.getNameOffset());
                        tag_ = "<a title=\""+ transform(id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                        _parts.add(new PartOffset(tag_, offsetEnd_));
                        tag_ = "</a>";
                        _parts.add(new PartOffset(tag_,offsetEnd_+id_.getName().length()));
                    }
                    if (parentOp_ instanceof ExecCompoundAffectationOperation) {
                        ExecCompoundAffectationOperation par_ = (ExecCompoundAffectationOperation) parentOp_;
                        ClassMethodId classMethodId_ = par_.getClassMethodId();
                        int opDelta_ = par_.getOper().length() - 1;
                        if (classMethodId_ != null) {
                            MethodId id_ = classMethodId_.getConstraints();
                            OperatorBlock operator_ = Classes.getOperatorsBodiesById(_cont, id_).first();
                            String file_ = operator_.getFile().getRenderFileName();
                            String rel_ = relativize(currentFileName_, file_ + "#m" + operator_.getNameOffset());
                            tag_ = "<a title=\""+ transform(id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                            _parts.add(new PartOffset(tag_, offsetEnd_));
                            tag_ = "</a>";
                            _parts.add(new PartOffset(tag_,offsetEnd_+opDelta_));
                        } else if (nextSiblingOp_.getResultClass().isConvertToString() && canCallToString(nextSiblingOp_.getResultClass(),_cont)){
                            tag_ = "<i>";
                            _parts.add(new PartOffset(tag_, offsetEnd_));
                            tag_ = "</i>";
                            _parts.add(new PartOffset(tag_,offsetEnd_+opDelta_));
                        } else {
                            String b_ = _cont.getStandards().getAliasPrimBoolean();
                            if (nextSiblingOp_.getResultClass().matchClass(b_)) {
                                AbstractCoverageResult resultLast_ = getCovers(_cont, _block, nextSiblingOp_, _annotation);
                                boolean partial_ = false;
                                if (!resultLast_.isFullCovered() && resultLast_.isPartialCovered()) {
                                    partial_ = true;
                                }
                                if (partial_) {
                                    if(((BooleanCoverageResult)resultLast_).isCoverTrue()){
                                        tag_ = "<a title=\""+ transform(_cont.getStandards().getTrueString())+"\">";
                                        _parts.add(new PartOffset(tag_, offsetEnd_));
                                        tag_ = "</a>";
                                        _parts.add(new PartOffset(tag_,offsetEnd_+opDelta_));
                                    } else {
                                        tag_ = "<a title=\""+ transform(_cont.getStandards().getFalseString())+"\">";
                                        _parts.add(new PartOffset(tag_, offsetEnd_));
                                        tag_ = "</a>";
                                        _parts.add(new PartOffset(tag_,offsetEnd_+opDelta_));
                                    }
                                }
                            }
                        }
                        if (par_.getSettable() instanceof ExecCustArrOperation) {
                            ExecCustArrOperation parArr_ = (ExecCustArrOperation) par_.getSettable();
                            ClassMethodId classMethodIdArr_ = parArr_.getClassMethodId();
                            String className_ = classMethodIdArr_.getClassName();
                            className_ = Templates.getIdFromAllTypes(className_);
                            MethodId methodId_ = classMethodIdArr_.getConstraints();
                            MethodId id_ = new MethodId(false,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
                            RootBlock type_ = (RootBlock) _cont.getClassBody(className_);
                            OverridableBlock method_ = Classes.getMethodBodiesById(_cont, className_, id_).first();
                            String file_ = type_.getFile().getRenderFileName();
                            String rel_ = relativize(currentFileName_, file_ + "#m" + method_.getNameOffset());
                            tag_ = "<a title=\""+ transform(className_ +"."+ id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                            _parts.add(new PartOffset(tag_, opDelta_+offsetEnd_));
                            tag_ = "</a>";
                            _parts.add(new PartOffset(tag_, opDelta_+offsetEnd_+1));
                        }
                    }

                    AbstractCoverageResult resultLoc_ = getCovers(_cont, _block, parentOp_, _annotation);
                    if (parentOp_ instanceof ExecEqOperation || parentOp_ instanceof ExecNbCmpOperation || parentOp_ instanceof ExecStrCmpOperation) {
                        int length_ = ((MiddleSymbolOperation) parent_).getOp().length();
                        if (!resultLoc_.isFullCovered() && resultLoc_.isPartialCovered()) {
                            if(((BooleanCoverageResult)resultLoc_).isCoverTrue()){
                                tag_ = "<a title=\""+ transform(_cont.getStandards().getTrueString())+"\">";
                                _parts.add(new PartOffset(tag_, offsetEnd_));
                                tag_ = "</a>";
                                _parts.add(new PartOffset(tag_,offsetEnd_+length_));
                            } else {
                                tag_ = "<a title=\""+ transform(_cont.getStandards().getFalseString())+"\">";
                                _parts.add(new PartOffset(tag_, offsetEnd_));
                                tag_ = "</a>";
                                _parts.add(new PartOffset(tag_,offsetEnd_+length_));
                            }
                        }
                    }
                    if (parentOp_ instanceof ExecQuickOperation) {
                        AbstractCoverageResult resultFirst_ = getCovers(_cont, _block, curOp_, _annotation);
                        AbstractCoverageResult resultLast_ = getCovers(_cont, _block, nextSiblingOp_, _annotation);
                        boolean partial_ = false;
                        if (!resultFirst_.isFullCovered() && resultFirst_.isPartialCovered()) {
                            partial_ = true;
                        }
                        if (!resultLast_.isFullCovered() && resultLast_.isPartialCovered()) {
                            partial_ = true;
                        }
                        if (partial_) {
                            if(((BooleanCoverageResult)resultFirst_).isCoverTrue()){
                                tag_ = "<a title=\""+ transform(_cont.getStandards().getTrueString())+"\">";
                                _parts.add(new PartOffset(tag_, offsetEnd_));
                                tag_ = "</a>";
                                _parts.add(new PartOffset(tag_,offsetEnd_+1));
                            } else {
                                tag_ = "<a title=\""+ transform(_cont.getStandards().getFalseString())+"\">";
                                _parts.add(new PartOffset(tag_, offsetEnd_));
                                tag_ = "</a>";
                                _parts.add(new PartOffset(tag_,offsetEnd_+1));
                            }
                            if(((BooleanCoverageResult)resultLast_).isCoverTrue()){
                                tag_ = "<a title=\""+ transform(_cont.getStandards().getTrueString())+"\">";
                                _parts.add(new PartOffset(tag_, offsetEnd_+1));
                                tag_ = "</a>";
                                _parts.add(new PartOffset(tag_,offsetEnd_+2));
                            } else if(((BooleanCoverageResult)resultLast_).isCoverFalse()){
                                tag_ = "<a title=\""+ transform(_cont.getStandards().getFalseString())+"\">";
                                _parts.add(new PartOffset(tag_, offsetEnd_+1));
                                tag_ = "</a>";
                                _parts.add(new PartOffset(tag_,offsetEnd_+2));
                            }
                        }
                    }
                    curOp_=nextSiblingOp_;
                    break;
                }
                if (parentOp_ == null) {
                    stopOp_ = true;
                    break;
                }
                _parts.add(new PartOffset(tag_,offsetEnd_));
                if (parentOp_ instanceof ExecCustArrOperation) {
                    ExecCustArrOperation par_ = (ExecCustArrOperation) parentOp_;
                    ClassMethodId classMethodId_ = par_.getClassMethodId();
                    String className_ = classMethodId_.getClassName();
                    className_ = Templates.getIdFromAllTypes(className_);
                    MethodId methodId_ = classMethodId_.getConstraints();
                    MethodId id_;
                    if (par_.resultCanBeSet()) {
                        id_ = new MethodId(false,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
                    } else {
                        id_ = new MethodId(false,"[]",methodId_.getParametersTypes(),methodId_.isVararg());
                    }
                    RootBlock type_ = (RootBlock) _cont.getClassBody(className_);
                    OverridableBlock method_ = Classes.getMethodBodiesById(_cont, className_, id_).first();
                    String file_ = type_.getFile().getRenderFileName();
                    String rel_ = relativize(currentFileName_, file_ + "#m" + method_.getNameOffset());
                    tag_ = "<a title=\""+ transform(className_ +"."+ id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                    _parts.add(new PartOffset(tag_, offsetEnd_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, offsetEnd_+1));
                }
                if (parentOp_ instanceof ExecInstanceOfOperation) {
                    _parts.addAllElts(((InstanceOfOperation)parent_).getPartOffsets());
                }
                if (parentOp_ instanceof ExecSemiAffectationOperation) {
                    ExecSemiAffectationOperation par_ = (ExecSemiAffectationOperation) parentOp_;
                    if(par_.isPost()) {
                        ClassMethodId classMethodId_ = par_.getClassMethodId();
                        if (classMethodId_ != null) {
                            MethodId id_ = classMethodId_.getConstraints();
                            OperatorBlock operator_ = Classes.getOperatorsBodiesById(_cont, id_).first();
                            String file_ = operator_.getFile().getRenderFileName();
                            String rel_ = relativize(currentFileName_, file_ + "#m" + operator_.getNameOffset());
                            tag_ = "<a title=\""+ transform(id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                            _parts.add(new PartOffset(tag_,offsetEnd_));
                            tag_ = "</a>";
                            _parts.add(new PartOffset(tag_,offsetEnd_+1));
                        }
                        if (par_.getSettable() instanceof ExecCustArrOperation) {
                            ExecCustArrOperation parArr_ = (ExecCustArrOperation) par_.getSettable();
                            ClassMethodId classMethodIdArr_ = parArr_.getClassMethodId();
                            String className_ = classMethodIdArr_.getClassName();
                            className_ = Templates.getIdFromAllTypes(className_);
                            MethodId methodId_ = classMethodIdArr_.getConstraints();
                            MethodId id_ = new MethodId(false,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
                            RootBlock type_ = (RootBlock) _cont.getClassBody(className_);
                            OverridableBlock method_ = Classes.getMethodBodiesById(_cont, className_, id_).first();
                            String file_ = type_.getFile().getRenderFileName();
                            String rel_ = relativize(currentFileName_, file_ + "#m" + method_.getNameOffset());
                            tag_ = "<a title=\""+ transform(className_ +"."+ id_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                            _parts.add(new PartOffset(tag_, offsetEnd_+1));
                            tag_ = "</a>";
                            _parts.add(new PartOffset(tag_, offsetEnd_+2));
                        }
                    }
                }
                if (parentOp_ == root_) {
                    stopOp_ = true;
                    break;
                }
                curOp_ = parentOp_;
                val_ = getMapping(_cont, _block, curOp_, _annotation);
            }
            if (stopOp_) {
                tag_ = "</span>";
                if (addCover_ && _fieldName.isEmpty()) {
                    _parts.add(new PartOffset(tag_,_endBlock+_tr));
                }
                break;
            }
        }
    }

    private static String getRelativize(ContextEl _cont, String _currentFileName, String _className, MethodId _id, GeneType _type, String _file) {
        String rel_;
        if (_type instanceof AnnotationBlock) {
            CustList<AnnotationMethodBlock> list_ = Classes.getMethodAnnotationBodiesById((RootBlock) _type, _id.getName());
            rel_ = relativize(_currentFileName, _file + "#m" + list_.first().getNameOffset());
        } else {
            OverridableBlock method_;
            method_ = Classes.getMethodBodiesById(_cont, _className, _id).first();
            rel_ = relativize(_currentFileName, _file + "#m" + method_.getNameOffset());
        }
        return rel_;
    }

    private static AbstractCoverageResult getCovers(ContextEl _cont, Block _block, ExecOperationNode _oper, boolean _annotation) {
        if (_block instanceof AnnotationMethodBlock || _annotation) {
            StandardCoverageResult res_ = new StandardCoverageResult();
            res_.fullCover();
            return res_;
        }
        return _cont.getCoverage().getCovers().getVal(_block).getVal(_oper);
    }

    private static OperationNode getMapping(ContextEl _cont, Block _block, ExecOperationNode _curOp, boolean _annotation) {
        if (_annotation) {
            return _cont.getCoverage().getMappingAnnotMembers().getVal(_block).getVal(_curOp);
        }
        if (_block instanceof AnnotationMethodBlock) {
            return _cont.getCoverage().getMappingAnnot().getVal(_block).getVal(_curOp);
        }
        return _cont.getCoverage().getMapping().getVal(_block).getVal(_curOp);
    }

    private static void updateFieldAnchor(ContextEl _cont,CustList<PartOffset> _parts,ClassField _id, int _begin, int _length, boolean _annotation) {
        String className_ = _id.getClassName();
        String currentFileName_ = _cont.getCoverage().getCurrentFileName();
        className_ = Templates.getIdFromAllTypes(className_);
        GeneType type_ = _cont.getClassBody(className_);
        if (isFromCustFile(type_)) {
            if (_annotation) {
                for (AnnotationMethodBlock m: Classes.getMethodAnnotationBodiesById((Block) type_,_id.getFieldName())) {
                    String file_ = ((RootBlock) type_).getFile().getRenderFileName();
                    String rel_ = relativize(currentFileName_,file_+"#m"+m.getNameOffset());
                    String tag_ = "<a title=\""+transform(className_ +"."+ _id.getFieldName())+"\" href=\""+rel_+"\">";
                    _parts.add(new PartOffset(tag_,_begin));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,_begin+_length));
                }
            } else {
                int delta_ = -1;
                for (Block b: Classes.getDirectChildren((Block) type_)) {
                    if (!(b instanceof FieldBlock)) {
                        continue;
                    }
                    FieldBlock f_ = (FieldBlock) b;
                    int i_ = 0;
                    int index_ = -1;
                    for (String n: f_.getFieldName()) {
                        if (StringList.quickEq(n, _id.getFieldName())) {
                            index_ = i_;
                            break;
                        }
                        i_++;
                    }
                    if (index_ > -1) {
                        delta_ = f_.getValuesOffset().get(index_);
                    }
                }
                if (delta_ > -1) {
                    String file_ = ((RootBlock) type_).getFile().getRenderFileName();
                    String rel_ = relativize(currentFileName_,file_+"#m"+delta_);
                    String tag_ = "<a title=\""+transform(className_ +"."+ _id.getFieldName())+"\" href=\""+rel_+"\">";
                    _parts.add(new PartOffset(tag_,_begin));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,_begin+_length));
                } else {
                    for (Block b: Classes.getDirectChildren((Block) type_)) {
                        if (!(b instanceof InnerTypeOrElement)) {
                            continue;
                        }
                        InnerTypeOrElement f_ = (InnerTypeOrElement)b;
                        if (!StringList.quickEq(f_.getUniqueFieldName(),_id.getFieldName())) {
                            continue;
                        }
                        delta_ = f_.getFieldNameOffset();
                    }
                    String file_ = ((RootBlock) type_).getFile().getRenderFileName();
                    String rel_ = relativize(currentFileName_,file_+"#m"+delta_);
                    String tag_ = "<a title=\""+transform(className_ +"."+ _id.getFieldName())+"\" href=\""+rel_+"\">";
                    _parts.add(new PartOffset(tag_,_begin));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,_begin+_length));
                }
            }
        }
    }
    public static String relativize(String _currentFile,String _refFile) {
        int diffFirst_ = -1;
        int countCommon_ = 0;
        boolean finished_ = true;
        int len_ = Math.min(_currentFile.length(),_refFile.length());
        for (int i =0; i < len_; i++) {
            char curChar_ = _currentFile.charAt(i);
            char relChar_ = _refFile.charAt(i);
            if (curChar_ != relChar_) {
                finished_ = false;
                break;
            }
            if(curChar_ == '/') {
                diffFirst_ = i;
                countCommon_++;
            }
        }
        if (finished_) {
            return _refFile.substring(len_);
        }
        String relFile_ = _refFile.substring(diffFirst_ + 1);
        if (_currentFile.indexOf('/',diffFirst_+1) < 0) {
            return relFile_;
        }
        StringBuilder b_ = new StringBuilder();
        int count_ = StringList.indexesOfChar(_currentFile,'/').size() - countCommon_;
        for (int i = 0; i < count_; i++) {
            b_.append("../");
        }
        return b_.append(relFile_).toString();
    }
    public static String transform(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            if (c == '<') {
                str_.append("&lt;");
            } else if (c == '>') {
                str_.append("&gt;");
            } else if (c == '&') {
                str_.append("&amp;");
            } else if (c == '\"') {
                str_.append("&quot;");
            } else {
                str_.append(c);
            }
        }
        return str_.toString();
    }
    private static boolean canCallToString(ClassArgumentMatching _type, ContextEl _cont) {
        if (_type.matchClass(_cont.getStandards().getAliasObject())) {
            return true;
        }
        if (_type.isArray()) {
            return false;
        }
        for (String s: _type.getNames()) {
            String id_ = Templates.getIdFromAllTypes(s);
            GeneType cl_ = _cont.getClassBody(id_);
            if (!(cl_ instanceof RootBlock)) {
                continue;
            }
            if (cl_ instanceof AnnotationBlock) {
                continue;
            }
            if (OperationNode.tryGetDeclaredToString(_cont,s).isFoundMethod()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFromCustFile(GeneType _g) {
        if (!(_g instanceof RootBlock)) {
            return false;
        }
        return !((RootBlock)_g).getFile().isPredefined();
    }
    public static void tryCalculate(FieldBlock _field, ContextEl _context, String _fieldName) {
        CustList<ExecOperationNode> nodes_ = _field.getOpValue();
        ExecOperationNode root_ = nodes_.last();
        CustList<ExecOperationNode> sub_;
        if (!(root_ instanceof ExecDeclaringOperation)) {
            sub_ = nodes_;
        } else {
            ExecMethodOperation m_ = (ExecMethodOperation)root_;
            int index_ = StringList.indexOf(_field.getFieldName(),_fieldName);
            CustList<ExecOperationNode> ch_ = m_.getChildrenNodes();
            ExecOperationNode rootLoc_ = ch_.get(index_);
            int from_;
            int to_ = rootLoc_.getOrder() + 1;
            if (index_ == 0) {
                from_ = 0;
            } else {
                from_ = ch_.get(index_-1).getOrder() + 1;
            }
            sub_ = nodes_.sub(from_, to_);
        }
        int ind_ = 0;
        int len_ = sub_.size();
        while (ind_ < len_) {
            ExecOperationNode curr_ = sub_.get(ind_);
            Argument a_ = curr_.getArgument();
            if (a_ != null) {
                ind_++;
                continue;
            }
            if (curr_ instanceof ReductibleOperable) {
                ((ReductibleOperable)curr_).tryCalculateNode(_context);
            }
            a_ = curr_.getArgument();
            if (a_ == null) {
                return;
            }
            ind_ = ExecOperationNode.getNextIndex(curr_, a_.getStruct());
        }
    }
    private static CustList<ExecOperationNode> getExecutableNodes(Analyzable _an,CustList<OperationNode> _list) {
        Block bl_ = _an.getCurrentBlock();
        CustList<ExecOperationNode> out_ = new CustList<ExecOperationNode>();
        OperationNode root_ = _list.last();
        OperationNode current_ = root_;
        ExecOperationNode exp_ = (ExecOperationNode) ExecOperationNode.createExecOperationNode(current_);
        _an.getContextEl().getCoverage().putBlockOperation(_an,bl_,current_,exp_);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (op_ != null) {
                ExecOperationNode loc_ = (ExecOperationNode) ExecOperationNode.createExecOperationNode(op_);
                _an.getContextEl().getCoverage().putBlockOperation(_an,bl_,op_,loc_);
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
                    ExecOperationNode loc_ = (ExecOperationNode) ExecOperationNode.createExecOperationNode(op_);
                    _an.getContextEl().getCoverage().putBlockOperation(_an,bl_,op_,loc_);
                    ExecMethodOperation par_ = exp_.getParent();
                    par_.appendChild(loc_);
                    if (op_.getParent() instanceof DotOperation && loc_ instanceof ExecPossibleIntermediateDotted) {
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
    public static CustList<ExecOperationNode> getReducedNodes(ExecOperationNode _root) {
        CustList<ExecOperationNode> out_ = new CustList<ExecOperationNode>();
        ExecOperationNode current_ = _root;
        while (current_ != null) {
            ExecOperationNode op_ = current_.getFirstChild();
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
