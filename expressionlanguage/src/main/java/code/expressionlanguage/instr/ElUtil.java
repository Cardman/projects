package code.expressionlanguage.instr;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.common.FunctionIdUtil;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.*;
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
        MethodAccessKind hiddenVarTypes_ = _calcul.getStaticBlock();
        _conf.setAccessStaticContext(hiddenVarTypes_);
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
        MethodAccessKind hiddenVarTypes_ = _calcul.getStaticBlock();
        _conf.setAccessStaticContext(hiddenVarTypes_);
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, CustList.FIRST_INDEX);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            //badOffset char
            badEl_.buildError(_conf.getAnalysisMessages().getBadExpression(),
                    possibleChar(badOffset_,_el),
                    Integer.toString(badOffset_),
                    _el);
            _conf.addError(badEl_);
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
        setSyntheticRoot(op_, fieldName_);
        CustList<OperationNode> all_ = getSortedDescNodes(op_, _conf,fieldName_);
        return getExecutableNodes(_conf,all_);
    }

    public static String possibleChar(int _index, String _str) {
        if (_index >= _str.length()) {
            return " ";
        }
        return Character.toString(_str.charAt(_index));
    }
    private static void setupStaticContext(ContextEl _conf, MethodAccessKind _hiddenVarTypes, OperationNode _op) {
        MethodAccessKind ctorAcc_;
        if (_op instanceof AbstractInvokingConstructor) {
            ctorAcc_ = MethodAccessKind.STATIC_CALL;
        } else {
            ctorAcc_ = MethodAccessKind.INSTANCE;
        }
        MethodAccessKind access_ = MethodId.getKind(_hiddenVarTypes,ctorAcc_);
        _conf.setAccessStaticContext(access_);
    }

    public static CustList<ExecOperationNode> getAnalyzedOperationsReadOnly(String _el, ContextEl _conf, Calculation _calcul) {
        MethodAccessKind hiddenVarTypes_ = _calcul.getStaticBlock();
        _conf.setAccessStaticContext(hiddenVarTypes_);
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, CustList.FIRST_INDEX);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            //badOffset char
            badEl_.buildError(_conf.getAnalysisMessages().getBadExpression(),
                    possibleChar(badOffset_,_el),
                    Integer.toString(badOffset_),
                    _el);
            _conf.addError(badEl_);
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
        setSyntheticRoot(op_, fieldName_);
        CustList<OperationNode> all_ = getSortedDescNodesReadOnly(op_, _conf,fieldName_);
        return getExecutableNodes(_conf,all_);
    }

    private static void setSyntheticRoot(OperationNode _op, String _fieldName) {
        if (_op instanceof AffectationOperation && !_fieldName.isEmpty()) {
            ((AffectationOperation) _op).setSynthetic(true);
        }
    }

    private static void setFieldName(OperationNode _op, String _fieldName) {
        if (_op instanceof StandardInstancingOperation) {
            ((StandardInstancingOperation) _op).setFieldName(_fieldName);
        }
    }


    private static CustList<OperationNode> getSortedDescNodes(OperationNode _root, ContextEl _context, String _fieldName) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        Block currentBlock_ = _context.getCurrentBlock();
        currentBlock_.defaultAssignmentBefore(_context, _root);
        OperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                currentBlock_.defaultAssignmentAfter(_context, _root);
                break;
            }
            preAnalyze(_context, c_);
            c_ = getAnalyzedNext(c_, _root, list_, _context,_fieldName);
        }
        return list_;
    }


    private static CustList<OperationNode> getSortedDescNodesReadOnly(OperationNode _root, ContextEl _context, String _fieldName) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode c_ = _root;
        while (c_ != null) {
            preAnalyze(_context, c_);
            c_ = getAnalyzedNextReadOnly(c_, _root, list_, _context, _fieldName);
        }
        return list_;
    }

    private static void preAnalyze(ContextEl _context, OperationNode _c) {
        if (_c instanceof PreAnalyzableOperation) {
            ((PreAnalyzableOperation) _c).preAnalyze(_context);
        }
    }

    private static OperationNode getAnalyzedNext(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, ContextEl _context, String _fieldName) {
        
        OperationNode next_ = createFirstChild(_current, _context, 0,_fieldName);
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
            next_ = processNext(_context, current_,_fieldName);
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
                tryCalculateNode(_context,par_);
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
                return;
            }
            if (_current instanceof StaticCallAccessOperation){
                PossibleIntermediateDotted possible_ = (PossibleIntermediateDotted) _next;
                possible_.setIntermediateDotted();
                possible_.setPreviousArgument(Argument.createVoid());
                MethodAccessKind access_ = MethodAccessKind.STATIC_CALL;
                if (!(_next instanceof LambdaOperation) && ((StaticCallAccessOperation)_current).isImplicit() && _context.getStaticContext() == MethodAccessKind.STATIC) {
                    access_ = MethodAccessKind.STATIC;
                }
                possible_.setPreviousResultClass(_current.getResultClass(), access_);
                _current.setSiblingSet(possible_);
            } else {
                PossibleIntermediateDotted possible_ = (PossibleIntermediateDotted) _next;
                MethodAccessKind static_ = MethodId.getKind(_current instanceof StaticAccessOperation);
                possible_.setIntermediateDotted();
                possible_.setPreviousArgument(_current.getArgument());
                possible_.setPreviousResultClass(_current.getResultClass(), static_);
                _current.setSiblingSet(possible_);
            }
        }
    }

    private static OperationNode getAnalyzedNextReadOnly(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, ContextEl _context, String _fieldName) {

        OperationNode next_ = createFirstChild(_current, _context, 0,_fieldName);
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
            next_ = processNext(_context, current_,_fieldName);
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
                tryCalculateNode(_context,par_);
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

    private static OperationNode processNext(ContextEl _context, OperationNode _current, String _fieldName) {
        OperationNode next_;
        if (_current instanceof StaticInitOperation) {
            next_ = createFirstChild(_current.getParent(), _context, 1,_fieldName);
        } else {
            next_ = createNextSibling(_current, _context,_fieldName);
        }
        return next_;
    }

    private static OperationNode createFirstChild(OperationNode _block, ContextEl _context, int _index, String _fieldName) {
        if (!(_block instanceof MethodOperation)) {
            return null;
        }
        MethodOperation block_ = (MethodOperation) _block;
        if (block_.getChildren().isEmpty()) {
            if (isInitializeStaticClassFirst(_index, block_)) {
                Delimiters d_ = block_.getOperations().getDelimiter();
                OperationsSequence opSeq_ = new OperationsSequence();
                opSeq_.setFctName(block_.getOperations().getFctName());
                opSeq_.setDelimiter(new Delimiters());
                opSeq_.getDelimiter().setIndexBegin(d_.getIndexBegin());
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
            opSeq_.setDelimiter(new Delimiters());
            opSeq_.getDelimiter().setIndexBegin(d_.getIndexBegin());
            return new StaticInitOperation(block_.getIndexInEl(), CustList.FIRST_INDEX, block_, opSeq_);
        }
        OperationsSequence r_ = ElResolver.getOperationsSequence(offset_, value_, _context, d_);
        OperationNode op_ = OperationNode.createOperationNode(offset_, _index, block_, r_, _context);
        setFieldName(_fieldName, block_, op_);
        return op_;
    }

    private static boolean isInitializeStaticClassFirst(int _index, MethodOperation block_) {
        return block_ instanceof StandardInstancingOperation
                && _index == CustList.FIRST_INDEX
                && ((StandardInstancingOperation) block_).isNewBefore();
    }

    private static OperationNode createNextSibling(OperationNode _block, ContextEl _context, String _fieldName) {
        MethodOperation p_ = _block.getParent();
        if (p_ == null) {
            return null;
        }
        IntTreeMap<String> children_ = p_.getChildren();
        int delta_ = 1;
        if (p_ instanceof StandardInstancingOperation && p_.getFirstChild() instanceof StaticInitOperation) {
            delta_ = 0;
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
        setFieldName(_fieldName, p_, op_);
        return op_;
    }

    private static void setFieldName(String _fieldName, MethodOperation _p, OperationNode _c) {
        if (_p instanceof AffectationOperation && _p.getParent() == null) {
            setFieldName(_c,_fieldName);
        }
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
    static boolean isDeclaringLoopVariable(Analyzable _an) {
        if (!_an.isMerged()) {
            return false;
        }
        if (!_an.hasLoopDeclarator()) {
            return false;
        }
        return _an.getForLoopPartState() == ForLoopPart.INIT;
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
    static boolean isDeclaringVariable(Analyzable _an) {
        if (!_an.isMerged()) {
            return false;
        }
        return _an.hasDeclarator();
    }
    private static boolean isDeclaringVariable(Operable _var) {
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
    private static boolean isDeclaringVariable(MethodOperation _par) {
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
        StringMap<Boolean> ass_ = new StringMap<Boolean>();
        for (EntryCust<String,Assignment> e: _ass.entryList()) {
            ass_.addEntry(e.getKey(),e.getValue().isUnassignedAfter());
        }
        return checkFinalReadOnly(_conf, _cst, ass_, fromCurClass_, cl_, fieldName_);
    }

    public static boolean checkFinalFieldReadOnly(Analyzable _conf, SettableAbstractFieldOperation _cst, StringMap<Boolean> _ass) {
        boolean fromCurClass_ = _cst.isFromCurrentClassReadOnly(_conf);
        ClassField cl_ = _cst.getFieldIdReadOnly();
        FieldInfo meta_ = _conf.getFieldInfo(cl_);
        if (meta_ == null) {
            return false;
        }
        String fieldName_ = cl_.getFieldName();
        return meta_.isFinalField() && checkFinalReadOnly(_conf, _cst, _ass, fromCurClass_, cl_, fieldName_);
    }
    private static boolean checkFinalReadOnly(Analyzable _conf, SettableAbstractFieldOperation _cst, StringMap<Boolean> _ass, boolean _fromCurClass, ClassField _cl, String _fieldName) {
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
                    for (EntryCust<String, Boolean> e: _ass.entryList()) {
                        if (!StringList.quickEq(e.getKey(), _fieldName)) {
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
            if (isDeclaringField(_cst, _conf)) {
                checkFinal_ = false;
            } else {
                checkFinal_ = false;
                for (EntryCust<String, Boolean> e: _ass.entryList()) {
                    if (!StringList.quickEq(e.getKey(), _fieldName)) {
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
            if (_context.callsOrException()) {
                _el.setCurrentOper(o);
                if (!_context.calls()) {
                    pageEl_.setTranslatedOffset(0);
                }
                return;
            }
            Argument res_ = pair_.getArgument();
            Struct st_ = res_.getStruct();
            fr_ = ExecOperationNode.getNextIndex(o, st_);
        }
        pageEl_.setTranslatedOffset(0);
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
        if (!_fieldName.isEmpty()) {
            root_ = root_.getFirstChild().getNextSibling();
        }
        ExecOperationNode curOp_ = root_;
        int sum_ = _tr + _offsetBlock - _fieldName.length();
        String currentFileName_ = _cont.getCoverage().getCurrentFileName();
        boolean addCover_ = !(_block instanceof CaseCondition) && !(_block instanceof AnnotationMethodBlock) && !_annotation;
        IdMap<ExecOperationNode, OperationNode> mapping_ = getMapping(_cont, _block, _annotation);
        while (true) {
            OperationNode val_ = mapping_.getVal(curOp_);
            AbstractCoverageResult result_ = getCovers(_cont, _block, curOp_);
            getBeginOp(_cont, _block, _parts, _fieldName, root_, curOp_, sum_, addCover_, val_, result_);
            left(_cont,currentFileName_,_offsetBlock,_block,sum_,val_,curOp_,result_,_parts);
            ExecOperationNode firstChildOp_ = curOp_.getFirstChild();
            if (firstChildOp_ != null) {
                curOp_ = firstChildOp_;
                continue;
            }
            boolean stopOp_ = false;
            while (true) {
                MethodOperation parent_ = val_.getParent();
                int offsetEnd_ = getOffsetEnd(sum_, val_, parent_);
                String tag_ = getEndTag(addCover_, val_);
                ExecOperationNode nextSiblingOp_ = curOp_.getNextSibling();
                ExecMethodOperation parentOp_ = curOp_.getParent();
                _parts.add(new PartOffset(tag_,offsetEnd_));
                if (nextSiblingOp_ != null) {
                    middle(_cont,currentFileName_,_block, offsetEnd_,curOp_,nextSiblingOp_,
                            parentOp_,parent_,_parts);
                    curOp_=nextSiblingOp_;
                    break;
                }
                if (parentOp_ == null) {
                    getEnd(_endBlock, _parts, _tr, _fieldName, addCover_);
                    stopOp_ = true;
                    break;
                }
                right(_cont,currentFileName_,offsetEnd_,parentOp_,parent_,_parts);
                if (parentOp_ == root_) {
                    getEnd(_endBlock, _parts, _tr, _fieldName, addCover_);
                    stopOp_ = true;
                    break;
                }
                curOp_ = parentOp_;
                val_ = mapping_.getVal(curOp_);
            }
            if (stopOp_) {
                break;
            }
        }
    }

    private static void getBeginOp(ContextEl _cont, Block _block, CustList<PartOffset> _parts, String _fieldName, ExecOperationNode root_, ExecOperationNode curOp_, int sum_, boolean addCover_, OperationNode val_, AbstractCoverageResult result_) {
        if (curOp_ != root_ || _fieldName.isEmpty()) {
            String tag_ = getBegin(_cont, _block, root_, curOp_, addCover_, val_, result_);
            _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()));
        }
    }

    private static void getEnd(int _endBlock, CustList<PartOffset> _parts, int _tr, String _fieldName, boolean addCover_) {
        String tag_ = "</span>";
        if (addCover_ && _fieldName.isEmpty()) {
            _parts.add(new PartOffset(tag_,_endBlock+_tr));
        }
    }

    private static String getEndTag(boolean addCover_, OperationNode val_) {
        String tag_;
        if (!addCover_ || val_ instanceof StaticInitOperation || val_.getParent() == null) {
            tag_ = "";
        } else {
            tag_ = "</span>";
        }
        return tag_;
    }

    private static String getBegin(ContextEl _cont, Block _block, ExecOperationNode root_, ExecOperationNode curOp_, boolean addCover_, OperationNode val_, AbstractCoverageResult result_) {
        String tag_;
        if (!addCover_ ||val_ instanceof StaticInitOperation) {
            tag_ = "";
            return tag_;
        }
        if (val_.getArgument() != null) {
            ExecOperationNode par_ = curOp_;
            while (par_ != root_) {
                if (par_.getArgument() == null) {
                    break;
                }
                par_ = par_.getParent();
            }
            AbstractCoverageResult resultPar_ = getCovers(_cont, _block, par_);
            if (resultPar_.isPartialCovered()) {
                tag_ = getFullInit(resultPar_);
                return tag_;
            }
            tag_ = "<span class=\"n\">";
            return tag_;
        }
        if (result_.isFullCovered()) {
            tag_ = getFullInit(result_);
            return tag_;
        }
        if (result_.isPartialCovered()) {
            return getPartialInit(val_, result_);
        }
        tag_ = "<span class=\"n\">";
        return tag_;
    }

    private static String getPartialInit(OperationNode val_, AbstractCoverageResult result_) {
        String tag_;
        if (val_ instanceof AffectationOperation && val_.getFirstChild().getNextSibling().getArgument() != null) {
            tag_ = getFullInit(result_);
            return tag_;
        }
        if (result_.isInit()) {
            tag_ = "<span class=\"q\">";
            return tag_;
        }
        tag_ = "<span class=\"p\">";
        return tag_;
    }

    private static void left(ContextEl _cont,
                             String currentFileName_,
                             int _offsetBlock,
                             Block _block,
                             int sum_,
                             OperationNode val_,
                             ExecOperationNode curOp_,
                             AbstractCoverageResult result_,
                             CustList<PartOffset> _parts) {
        processNamedFct(_cont, currentFileName_, sum_, val_, curOp_, _parts);
        processVariables(_cont, _offsetBlock, _block, sum_, val_, curOp_, _parts);
        processConstants(sum_, val_, _parts);
        processIndexerValue(_cont, sum_, val_, curOp_, _parts);
        processAssociation(_cont, sum_, val_, curOp_, _parts);
        processFields(_cont, _block, sum_, val_, curOp_, _parts);
        processInstances(_cont, currentFileName_, sum_, val_, curOp_, _parts);
        processLamba(_cont, currentFileName_, sum_, val_, curOp_, _parts);
        processLeafType(val_, curOp_, _parts);
        processDynamicCall(_cont, sum_, val_, curOp_, _parts);
        processRichHeader(_cont, currentFileName_, sum_, val_, curOp_, _parts);
        processUnaryLeftOperationsCovers(_cont, sum_, val_, curOp_, result_, _parts);
        processUnaryLeftOperationsLinks(_cont, currentFileName_, sum_, val_, curOp_, _parts);
        processLeftIndexer(_cont, currentFileName_, sum_, val_, curOp_, _parts);
    }

    private static void processConstants(int sum_, OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof ConstantOperation) {
            if (val_.getOperations().getConstType() == ConstType.STRING) {
                int off_ = val_.getOperations().getOffset();
                String tag_ = "<span class=\"s\">";
                int begin_ = sum_ + off_ + val_.getIndexInEl();
                _parts.add(new PartOffset(tag_, begin_));
                tag_ = "</span>";
                _parts.add(new PartOffset(tag_,begin_+ ((ConstantOperation)val_).getLength()));
            }
            if (val_.getOperations().getConstType() == ConstType.CHARACTER) {
                int off_ = val_.getOperations().getOffset();
                String tag_ = "<span class=\"s\">";
                int begin_ = sum_ + off_ + val_.getIndexInEl();
                _parts.add(new PartOffset(tag_,begin_));
                tag_ = "</span>";
                _parts.add(new PartOffset(tag_,begin_+ ((ConstantOperation)val_).getLength()));
            }
        }
    }

    private static void processDynamicCall(ContextEl _cont, int sum_, OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
        if (curOp_ instanceof ExecCallDynMethodOperation) {
            String tag_ = "<b>";
            _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+_cont.getStandards().getAliasCall().length()));
        }
    }

    private static void processLeftIndexer(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
        if (curOp_ instanceof ExecCustArrOperation) {
            ExecCustArrOperation par_ = (ExecCustArrOperation) curOp_;
            ClassMethodId classMethodId_ = par_.getClassMethodId();
            String className_ = classMethodId_.getClassName();
            className_ = Templates.getIdFromAllTypes(className_);
            MethodId methodId_ = classMethodId_.getConstraints();
            MethodId id_;
            if (par_.resultCanBeSet()) {
                id_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
            } else {
                id_ = new MethodId(MethodAccessKind.INSTANCE,"[]",methodId_.getParametersTypes(),methodId_.isVararg());
            }
            int offsetEnd_ = sum_ + val_.getIndexInEl();
            addParts(_cont,currentFileName_,className_,id_,offsetEnd_,1,_parts);
        }
    }

    private static void processAssociation(ContextEl _cont, int sum_, OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
        if (curOp_ instanceof ExecAssocationOperation) {
            AssocationOperation a_ = (AssocationOperation) val_;
            String fieldName_ = a_.getFieldName();
            String annotation_ = a_.getAnnotation();
            ClassField c_ = new ClassField(annotation_,fieldName_);
            int delta_ = a_.getSum();
            String className_ = c_.getClassName();
            String currentFileName_ = _cont.getCoverage().getCurrentFileName();
            className_ = Templates.getIdFromAllTypes(className_);
            addParts(_cont,currentFileName_,className_,
                    new MethodId(MethodAccessKind.INSTANCE,c_.getFieldName(),new StringList()),
                    sum_ +delta_+ val_.getIndexInEl(),fieldName_.length(),_parts
            );
        }
    }

    private static void processIndexerValue(ContextEl _cont, int sum_, OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
        if (curOp_ instanceof ExecValueOperation) {
            int delta_ = ((ValueOperation) val_).getOff();
            String tag_ = "<b>";
            _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+_cont.getKeyWords().getKeyWordValue().length()));
        }
    }

    private static void processNamedFct(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
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
            addParts(_cont,currentFileName_,className_,id_,
                    sum_ +delta_+ val_.getIndexInEl(),id_.getName().length(),
                    _parts);
        }
        if (val_ instanceof AnnotationInstanceOperation) {
            _parts.addAllElts(((AnnotationInstanceOperation)val_).getPartOffsets());
        }
    }

    private static void processFields(ContextEl _cont, Block _block, int sum_, OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
        if (curOp_ instanceof ExecSettableFieldOperation) {
            if (_block instanceof FieldBlock && isDeclaringVariable(curOp_)) {
                ClassField c_ = ((ExecSettableFieldOperation)curOp_).getFieldId();
                int id_;
                if (curOp_.getParent() instanceof ExecAffectationOperation) {
                    id_ = ((FieldBlock) _block).getValuesOffset().get(curOp_.getParent().getIndexChild());
                } else {
                    id_ = ((FieldBlock) _block).getValuesOffset().get(curOp_.getIndexChild());
                }
                String tag_ = "<a name=\"m"+id_+"\">";
                int d_ = ((ExecSettableFieldOperation)curOp_).getDelta();
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+d_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+d_+c_.getFieldName().length()));
            } else {
                _parts.addAllElts(((SettableAbstractFieldOperation) val_).getPartOffsets());
                ClassField c_ = ((ExecSettableFieldOperation)curOp_).getFieldId();
                int delta_ = ((SettableAbstractFieldOperation) val_).getOff();
                updateFieldAnchor(_cont,_parts,c_,sum_ +delta_+ val_.getIndexInEl() + ((ExecSettableFieldOperation)curOp_).getDelta(),c_.getFieldName().length());
            }
        }
    }

    private static void processVariables(ContextEl _cont, int _offsetBlock, Block _block, int sum_, OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
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
                    String tag_ = "<a name=\"m"+ _offsetBlock +"\">";
                    _cont.getCoverage().getLocalVars().put(varName_, _offsetBlock);
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                } else {
                    int id_ = parAn_.getChildren().getKey(index_);
                    id_ += StringList.getFirstPrintableCharIndex(parAn_.getChildren().getValue(index_));
                    id_ += _offsetBlock;
                    String tag_ = "<a name=\"m"+id_+"\">";
                    _cont.getCoverage().getLocalVars().put(varName_,id_);
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                }

            } else {
                Integer id_ = _cont.getCoverage().getLocalVars().getVal(varName_);
                String tag_ = "<a href=\"#m"+id_+"\">";
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
                    String tag_ = "<a name=\"m"+ _offsetBlock +"\">";
                    _cont.getCoverage().getMutableVars().put(varName_, _offsetBlock);
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                } else {
                    int id_ = parAn_.getChildren().getKey(index_);
                    id_ += StringList.getFirstPrintableCharIndex(parAn_.getChildren().getValue(index_));
                    id_ += _offsetBlock;
                    String tag_ = "<a name=\"m"+id_+"\">";
                    _cont.getCoverage().getMutableVars().put(varName_,id_);
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                }

            } else {
                Integer id_ = _cont.getCoverage().getMutableVars().getVal(varName_);
                String tag_ = "<a href=\"#m"+id_+"\">";
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
                String tag_ = "<a href=\"#m"+id_+"\">";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
            } else if (type_ == ConstType.LOOP_INDEX) {
                int deltaLoc_ = ((FinalVariableOperation)val_).getDelta();
                Integer id_ = _cont.getCoverage().getLoopVars().getVal(varName_);
                String tag_ = "<a href=\"#m"+id_+"\">";
                _parts.add(new PartOffset(tag_,deltaLoc_+delta_+sum_ + val_.getIndexInEl()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,deltaLoc_+delta_+sum_ + val_.getIndexInEl()+varName_.length()));
            } else if (type_ == ConstType.CATCH_VAR) {
                Integer id_ = _cont.getCoverage().getCatchVars().getVal(varName_);
                String tag_ = "<a href=\"#m"+id_+"\">";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
            } else {
                Integer id_ = _cont.getCoverage().getParamVars().getVal(varName_);
                String tag_ = "<a href=\"#m"+id_+"\">";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
            }
        }
    }

    private static void processInstances(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
        if (curOp_ instanceof ExecStandardInstancingOperation) {
            String cl_ = ((ExecStandardInstancingOperation)curOp_).getClassName();
            cl_ = Templates.getIdFromAllTypes(cl_);
            ConstructorId c_ = ((ExecStandardInstancingOperation)curOp_).getConstId();
            StandardInstancingOperation inst_ = (StandardInstancingOperation) val_;
            if (inst_.getFieldName().isEmpty()) {
                int offsetNew_ =StringList.getFirstPrintableCharIndex(inst_.getMethodName());
                addParts(_cont,currentFileName_,cl_,c_,
                        offsetNew_+sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordNew().length(),
                        _parts);
            }
            _parts.addAllElts(inst_.getPartOffsets());
        }
        if (curOp_ instanceof ExecDimensionArrayInstancing) {
            _parts.addAllElts(((DimensionArrayInstancing)val_).getPartOffsets());
        }
        if (val_ instanceof ElementArrayInstancing) {
            _parts.addAllElts(((ElementArrayInstancing)val_).getPartOffsets());
        }
    }

    private static void processLeafType(OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
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
        if (val_ instanceof StaticAccessOperation) {
            _parts.addAllElts(((StaticAccessOperation)val_).getPartOffsets());
        }
        if (val_ instanceof StaticCallAccessOperation) {
            _parts.addAllElts(((StaticCallAccessOperation)val_).getPartOffsets());
        }
    }

    private static void processLamba(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
        if (!(curOp_ instanceof ExecLambdaOperation)) {
            return;
        }
        ClassMethodId classMethodId_ = ((ExecLambdaOperation) curOp_).getMethod();
        ConstructorId realId_ = ((ExecLambdaOperation) curOp_).getRealId();
        ClassField fieldId_ = ((ExecLambdaOperation) curOp_).getFieldId();
        int off_ = ((LambdaOperation)val_).getClassNameOffset();
        if (classMethodId_ != null) {
            String className_ = classMethodId_.getClassName();
            className_ = Templates.getIdFromAllTypes(className_);
            MethodId id_ = classMethodId_.getConstraints();
            addParts(_cont,currentFileName_,className_,id_,
                    off_+sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordLambda().length(),
            _parts);
        } else if (realId_ != null) {
            String cl_ = ((ExecLambdaOperation) curOp_).getFoundClass();
            cl_ = Templates.getIdFromAllTypes(cl_);
            addParts(_cont,currentFileName_,cl_,realId_,
                    off_+sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordLambda().length(),
                    _parts);
        } else {
            updateFieldAnchor(_cont,_parts,fieldId_,off_+sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordLambda().length());
        }
        _parts.addAllElts(((LambdaOperation)val_).getPartOffsets());
    }

    private static void processRichHeader(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
        if (curOp_ instanceof ExecAbstractInvokingConstructor) {
            ConstructorId c_ = ((ExecAbstractInvokingConstructor)curOp_).getConstId();
            String cl_ = c_.getName();
            cl_ = Templates.getIdFromAllTypes(cl_);
            addParts(_cont,currentFileName_,cl_,c_,
                    sum_ + val_.getIndexInEl(),((AbstractInvokingConstructor)val_).getOffsetOper(),
                    _parts);
        }
        if (curOp_ instanceof ExecExplicitOperatorOperation) {
            ExecExplicitOperatorOperation par_ = (ExecExplicitOperatorOperation) curOp_;
            ClassMethodId classMethodId_ = par_.getClassMethodId();
            MethodId id_ = classMethodId_.getConstraints();
            addParts(_cont,currentFileName_,"",id_,
                    sum_ + val_.getIndexInEl(),((ExplicitOperatorOperation)val_).getOffsetOper(),
                    _parts);
        }
    }

    private static void processUnaryLeftOperationsCovers(ContextEl _cont, int sum_, OperationNode val_, ExecOperationNode curOp_, AbstractCoverageResult result_, CustList<PartOffset> _parts) {
        if (curOp_ instanceof ExecUnaryBooleanOperation && result_.isStrictPartialCovered()) {
            int offsetOp_ = val_.getOperations().getOperators().firstKey();
            safe(result_,_cont,sum_ + val_.getIndexInEl() + offsetOp_,_parts,1);
        }
    }

    private static void processUnaryLeftOperationsLinks(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, ExecOperationNode curOp_, CustList<PartOffset> _parts) {
        if (curOp_ instanceof ExecCustNumericOperation && curOp_.getFirstChild().getNextSibling() == null) {
            ExecCustNumericOperation par_ = (ExecCustNumericOperation) curOp_;
            ClassMethodId classMethodId_ = par_.getClassMethodId();
            MethodId id_ = classMethodId_.getConstraints();
            addParts(_cont,currentFileName_,"",id_,
                    sum_ + val_.getIndexInEl()+par_.getOpOffset(),id_.getName().length(),
                    _parts);
        }
        if (curOp_ instanceof ExecCastOperation) {
            _parts.addAllElts(((CastOperation)val_).getPartOffsets());
        }
        if (curOp_ instanceof ExecExplicitOperation) {
            String className_ = ((ExplicitOperation) val_).getClassName();
            int offsetOp_ = val_.getOperations().getOperators().firstKey();
            MethodId castId_ = ((ExplicitOperation) val_).getCastOpId();
            addParts(_cont,currentFileName_,Templates.getIdFromAllTypes(className_),castId_,
                    offsetOp_+sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordExplicit().length(),
                    _parts);
            _parts.addAllElts(((ExplicitOperation)val_).getPartOffsets());
        }
        if (curOp_ instanceof ExecSemiAffectationOperation) {
            ExecSemiAffectationOperation par_ = (ExecSemiAffectationOperation) curOp_;
            int offsetOp_ = val_.getOperations().getOperators().firstKey();
            if(!par_.isPost()) {
                ClassMethodId classMethodId_ = par_.getClassMethodId();
                if (classMethodId_ != null) {
                    MethodId id_ = classMethodId_.getConstraints();
                    addParts(_cont,currentFileName_,"",id_,
                            sum_ + val_.getIndexInEl()+offsetOp_,1,
                            _parts);
                }
                if (par_.getSettable() instanceof ExecCustArrOperation) {
                    ExecCustArrOperation parArr_ = (ExecCustArrOperation) par_.getSettable();
                    ClassMethodId classMethodIdArr_ = parArr_.getClassMethodId();
                    String className_ = classMethodIdArr_.getClassName();
                    className_ = Templates.getIdFromAllTypes(className_);
                    MethodId methodId_ = classMethodIdArr_.getConstraints();
                    MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
                    addParts(_cont,currentFileName_,className_,id_,
                            sum_ + val_.getIndexInEl()+offsetOp_+1,1,
                            _parts);
                }
            }
        }
    }

    private static int getOffsetEnd(int sum_, OperationNode val_, MethodOperation parent_) {
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
        return offsetEnd_;
    }

    private static void middle(ContextEl _cont,
                               String currentFileName_,
                               Block _block,
                               int offsetEnd_,
                               ExecOperationNode curOp_,
                               ExecOperationNode nextSiblingOp_,
                               ExecMethodOperation parentOp_,
                               MethodOperation parent_,
                               CustList<PartOffset> _parts) {
        processCat(_cont, offsetEnd_, curOp_, nextSiblingOp_, parentOp_, _parts);
        processCustomOperator(_cont, currentFileName_, offsetEnd_, parentOp_, _parts);
        processCompoundAffLeftOp(_cont, currentFileName_, offsetEnd_, nextSiblingOp_, parentOp_, _parts);
        processCompoundAffCover(_cont, _block, offsetEnd_, nextSiblingOp_, parentOp_, _parts);
        processCompoundAffRightOp(_cont, currentFileName_, offsetEnd_, parentOp_, _parts);

        processCompare(_cont, _block, offsetEnd_, parentOp_, parent_, _parts);
        processNullSafe(_cont, _block, offsetEnd_, curOp_, nextSiblingOp_, parentOp_, _parts);
        processLogicAndOrOperation(_cont, _block, offsetEnd_, curOp_, nextSiblingOp_, parentOp_, _parts);
    }

    private static void processCustomOperator(ContextEl _cont, String currentFileName_, int offsetEnd_, ExecMethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (parentOp_ instanceof ExecCustNumericOperation) {
            ExecCustNumericOperation par_ = (ExecCustNumericOperation) parentOp_;
            ClassMethodId classMethodId_ = par_.getClassMethodId();
            MethodId id_ = classMethodId_.getConstraints();
            addParts(_cont,currentFileName_,"",id_,offsetEnd_,id_.getName().length(),_parts);
        }
    }

    private static void processCat(ContextEl _cont, int offsetEnd_, ExecOperationNode curOp_, ExecOperationNode nextSiblingOp_, ExecMethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (parentOp_ instanceof ExecCatOperation && canCallToString(_cont, curOp_, nextSiblingOp_)) {
            String tag_ = "<i>";
            _parts.add(new PartOffset(tag_, offsetEnd_));
            tag_ = "</i>";
            _parts.add(new PartOffset(tag_, offsetEnd_ + 1));
        }
    }

    private static boolean canCallToString(ContextEl _cont, ExecOperationNode curOp_, ExecOperationNode nextSiblingOp_) {
        return canCallToString(curOp_.getResultClass(),_cont) || canCallToString(nextSiblingOp_.getResultClass(),_cont);
    }

    private static void processNullSafe(ContextEl _cont, Block _block, int offsetEnd_, ExecOperationNode curOp_, ExecOperationNode nextSiblingOp_, ExecMethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (parentOp_ instanceof ExecNullSafeOperation) {
            AbstractCoverageResult resultFirst_ = getCovers(_cont, _block, curOp_);
            AbstractCoverageResult resultLast_ = getCovers(_cont, _block, nextSiblingOp_);
            safe(resultFirst_,_cont,offsetEnd_,_parts, 1);
            safe(resultLast_,_cont,offsetEnd_+1,_parts, 1);
        }
    }

    private static void processCompare(ContextEl _cont, Block _block, int offsetEnd_, ExecMethodOperation parentOp_, MethodOperation parent_, CustList<PartOffset> _parts) {
        if (parentOp_ instanceof ExecEqOperation || parentOp_ instanceof ExecNbCmpOperation || parentOp_ instanceof ExecStrCmpOperation) {
            int length_ = ((MiddleSymbolOperation)parent_) .getOp().length();
            AbstractCoverageResult resultLoc_ = getCovers(_cont, _block, parentOp_);
            safe(resultLoc_,_cont,offsetEnd_,_parts,length_);
        }
    }

    private static void processCompoundAffLeftOp(ContextEl _cont, String currentFileName_, int offsetEnd_, ExecOperationNode nextSiblingOp_, ExecMethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (!(parentOp_ instanceof ExecCompoundAffectationOperation)) {
            return;
        }
        ExecCompoundAffectationOperation par_ = (ExecCompoundAffectationOperation) parentOp_;
        ClassMethodId classMethodId_ = par_.getClassMethodId();
        int opDelta_ = par_.getOper().length() - 1;
        if (classMethodId_ != null) {
            MethodId id_ = classMethodId_.getConstraints();
            addParts(_cont,currentFileName_,"",id_,offsetEnd_,opDelta_,_parts);
        } else if (nextSiblingOp_.getResultClass().isConvertToString() && canCallToString(nextSiblingOp_.getResultClass(),_cont)){
            String tag_ = "<i>";
            _parts.add(new PartOffset(tag_, offsetEnd_));
            tag_ = "</i>";
            _parts.add(new PartOffset(tag_,offsetEnd_+opDelta_));
        }
    }
    private static void processCompoundAffCover(ContextEl _cont, Block _block, int offsetEnd_, ExecOperationNode nextSiblingOp_, ExecMethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (!(parentOp_ instanceof ExecCompoundAffectationOperation)) {
            return;
        }
        ExecCompoundAffectationOperation par_ = (ExecCompoundAffectationOperation) parentOp_;
        ClassMethodId classMethodId_ = par_.getClassMethodId();
        if (classMethodId_ != null) {
            return;
        }
        if (nextSiblingOp_.getResultClass().isConvertToString() && canCallToString(nextSiblingOp_.getResultClass(),_cont)){
            return;
        }
        int opDelta_ = par_.getOper().length() - 1;
        if (StringList.quickEq(par_.getOper(),"??=")){
            AbstractCoverageResult resultLast_ = getCovers(_cont, _block, nextSiblingOp_);
            safe(resultLast_,_cont,offsetEnd_,_parts, opDelta_);
        } else {
            String b_ = _cont.getStandards().getAliasPrimBoolean();
            if (nextSiblingOp_.getResultClass().matchClass(b_)) {
                AbstractCoverageResult resultLast_ = getCovers(_cont, _block, nextSiblingOp_);
                safe(resultLast_,_cont,offsetEnd_,_parts,opDelta_);
            }
        }
    }
    private static void processCompoundAffRightOp(ContextEl _cont, String currentFileName_, int offsetEnd_, ExecMethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (!(parentOp_ instanceof ExecCompoundAffectationOperation)) {
            return;
        }
        ExecCompoundAffectationOperation par_ = (ExecCompoundAffectationOperation) parentOp_;
        int opDelta_ = par_.getOper().length() - 1;
        if (par_.getSettable() instanceof ExecCustArrOperation) {
            ExecCustArrOperation parArr_ = (ExecCustArrOperation) par_.getSettable();
            ClassMethodId classMethodIdArr_ = parArr_.getClassMethodId();
            String className_ = classMethodIdArr_.getClassName();
            className_ = Templates.getIdFromAllTypes(className_);
            MethodId methodId_ = classMethodIdArr_.getConstraints();
            MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
            addParts(_cont,currentFileName_,className_,id_,opDelta_+offsetEnd_,1,_parts);
        }
    }
    private static void processLogicAndOrOperation(ContextEl _cont, Block _block, int offsetEnd_, ExecOperationNode curOp_, ExecOperationNode nextSiblingOp_, ExecMethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (!(parentOp_ instanceof ExecQuickOperation)) {
            return;
        }
        AbstractCoverageResult resultFirst_ = getCovers(_cont, _block, curOp_);
        AbstractCoverageResult resultLast_ = getCovers(_cont, _block, nextSiblingOp_);
        safe(resultFirst_,_cont,offsetEnd_,_parts,1);
        safe(resultLast_,_cont,offsetEnd_+1,_parts,1);
    }

    private static void right(ContextEl _cont,
                              String currentFileName_,
                              int offsetEnd_,
                              ExecMethodOperation parentOp_,
                              MethodOperation parent_,
                              CustList<PartOffset> _parts) {
        processRightIndexer(_cont, currentFileName_, offsetEnd_, parentOp_, _parts);
        processUnaryRightOperations(_cont, currentFileName_, offsetEnd_, parentOp_, parent_, _parts);
    }

    private static void processRightIndexer(ContextEl _cont, String currentFileName_, int offsetEnd_, ExecMethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (parentOp_ instanceof ExecCustArrOperation) {
            ExecCustArrOperation par_ = (ExecCustArrOperation) parentOp_;
            ClassMethodId classMethodId_ = par_.getClassMethodId();
            String className_ = classMethodId_.getClassName();
            className_ = Templates.getIdFromAllTypes(className_);
            MethodId methodId_ = classMethodId_.getConstraints();
            MethodId id_;
            if (par_.resultCanBeSet()) {
                id_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
            } else {
                id_ = new MethodId(MethodAccessKind.INSTANCE,"[]",methodId_.getParametersTypes(),methodId_.isVararg());
            }
            addParts(_cont,currentFileName_,className_,id_,offsetEnd_,1,_parts);
        }
    }

    private static void processUnaryRightOperations(ContextEl _cont, String currentFileName_, int offsetEnd_, ExecMethodOperation parentOp_, MethodOperation parent_, CustList<PartOffset> _parts) {
        if (parentOp_ instanceof ExecInstanceOfOperation) {
            _parts.addAllElts(((InstanceOfOperation)parent_).getPartOffsets());
        }
        if (parentOp_ instanceof ExecSemiAffectationOperation) {
            ExecSemiAffectationOperation par_ = (ExecSemiAffectationOperation) parentOp_;
            if(par_.isPost()) {
                ClassMethodId classMethodId_ = par_.getClassMethodId();
                if (classMethodId_ != null) {
                    MethodId id_ = classMethodId_.getConstraints();
                    addParts(_cont,currentFileName_,"",id_,offsetEnd_,1,_parts);
                }
                if (par_.getSettable() instanceof ExecCustArrOperation) {
                    ExecCustArrOperation parArr_ = (ExecCustArrOperation) par_.getSettable();
                    ClassMethodId classMethodIdArr_ = parArr_.getClassMethodId();
                    String className_ = classMethodIdArr_.getClassName();
                    className_ = Templates.getIdFromAllTypes(className_);
                    MethodId methodId_ = classMethodIdArr_.getConstraints();
                    MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
                    addParts(_cont,currentFileName_,className_,id_,offsetEnd_+1,1,_parts);
                }
            }
        }
    }

    private static void safe(AbstractCoverageResult _res, ContextEl _cont, int offsetEnd_, CustList<PartOffset> _parts, int _delta) {
        if (!_res.isStrictPartialCovered()) {
            return;
        }
        if (_res instanceof BooleanCoverageResult) {
            logicalAndOr((BooleanCoverageResult) _res,_cont,offsetEnd_,_parts,_delta);
        }
        if (_res instanceof NullCoverageResult) {
            nullSafe((NullCoverageResult)_res,_cont,offsetEnd_,_parts, _delta);
        }
        if (_res instanceof NullBooleanCoverageResult){
            nullBooleanSafe((NullBooleanCoverageResult)_res,_cont,offsetEnd_,_parts, _delta);
        }
    }

    private static void logicalAndOr(BooleanCoverageResult _res, ContextEl _cont,
                                     int offsetEnd_, CustList<PartOffset> _parts, int _delta) {
        StringList founds_ = new StringList();
        if (_res.isCoverTrue()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getTrueString());
        }
        if (_res.isCoverFalse()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getFalseString());
        }
        String tag_ = "<a title=\""+ transform(StringList.join(founds_, ","))+"\">";
        _parts.add(new PartOffset(tag_, offsetEnd_));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,offsetEnd_+_delta));
    }

    private static void nullBooleanSafe(NullBooleanCoverageResult _res, ContextEl _cont, int offsetEnd_, CustList<PartOffset> _parts, int _delta) {
        StringList founds_ = new StringList();
        if (_res.isCoverNull()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getNullCoverString());
        }
        if (_res.isCoverTrue()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getTrueString());
        }
        if (_res.isCoverFalse()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getFalseString());
        }
        String tag_ = "<a title=\"" + transform(StringList.join(founds_, ",")) + "\">";
        _parts.add(new PartOffset(tag_, offsetEnd_));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, offsetEnd_ + _delta));
    }
    private static void nullSafe(NullCoverageResult _res, ContextEl _cont, int offsetEnd_, CustList<PartOffset> _parts, int _delta) {
        StringList founds_ = new StringList();
        if (_res.isCoverNull()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getNullCoverString());
        }
        if (_res.isCoverNotNull()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getNotNullCoverString());
        }
        String tag_ = "<a title=\""+ transform(StringList.join(founds_,","))+"\">";
        _parts.add(new PartOffset(tag_, offsetEnd_));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,offsetEnd_+ _delta));
    }
    private static String getFullInit(AbstractCoverageResult _resultPar) {
        String tag_;
        if (_resultPar.isInit()) {
            tag_ = "<span class=\"g\">";
        } else {
            tag_ = "<span class=\"f\">";
        }
        return tag_;
    }
    private static void addParts(ContextEl _cont, String _currentFileName, String _className,
                                 Identifiable _id, int _begin, int _length,
                                 CustList<PartOffset> _parts) {
        if (_id == null) {
            return;
        }
        String cl_ = Templates.getIdFromAllTypes(_className);
        String rel_ = getRelativize(_cont, _currentFileName, _className, _id);
        if (rel_.isEmpty()) {
            return;
        }
        String tag_;
        if (_id instanceof MethodId) {
            if (!StringList.isDollarWord(_id.getName()) && !_id.getName().startsWith("[]")) {
                tag_ = "<a title=\""+ transform(_id.getSignature(_cont))+"\" href=\""+rel_+"\">";
            } else {
                tag_ = "<a title=\""+ transform(cl_ +"."+ _id.getSignature(_cont))+"\" href=\""+rel_+"\">";
            }
        } else {
            tag_ = "<a title=\""+ transform(cl_ +"."+ _id.getSignature(_cont))+"\" href=\""+rel_+"\">";
        }
        _parts.add(new PartOffset(tag_,_begin));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,_begin+_length));
    }
    private static String getRelativize(ContextEl _cont, String _currentFileName, String _className, Identifiable _id) {
        String rel_;
        if (_id instanceof MethodId && FunctionIdUtil.isOperatorName(_id)) {
            NamedFunctionBlock operator_ = Classes.getOperatorsBodiesById(_cont, (MethodId) _id).first();
            String file_ = operator_.getFile().getRenderFileName();
            rel_ = relativize(_currentFileName, file_ + "#m" + operator_.getNameOffset());
            return rel_;
        }
        String cl_ = Templates.getIdFromAllTypes(_className);
        GeneType type_ = _cont.getClassBody(cl_);
        if (!isFromCustFile(type_)) {
            return "";
        }
        if (_id instanceof MethodId){
            NamedFunctionBlock method_;
            CustList<NamedFunctionBlock> methods_ = Classes.getMethodBodiesById(_cont, _className, (MethodId) _id);
            if (methods_.isEmpty()) {
                return "";
            }
            method_ = methods_.first();
            rel_ = relativize(_currentFileName, method_.getFile().getRenderFileName() + "#m" + method_.getNameOffset());
        } else {
            CustList<ConstructorBlock> ctors_ = Classes.getConstructorBodiesById(_cont, _className, (ConstructorId) _id);
            if (ctors_.isEmpty()) {
                return "";
            }
            ConstructorBlock ctor_ = ctors_.first();
            rel_ = relativize(_currentFileName, ctor_.getFile().getRenderFileName() + "#m" + ctor_.getNameOffset());
        }
        return rel_;
    }

    private static AbstractCoverageResult getCovers(ContextEl _cont, Block _block, ExecOperationNode _oper) {
        IdMap<ExecOperationNode, AbstractCoverageResult> map_ = _cont.getCoverage().getCovers().getVal(_block);
        if (map_ == null) {
            map_ = new IdMap<ExecOperationNode, AbstractCoverageResult>();
        }
        AbstractCoverageResult res_ = map_.getVal(_oper);
        if (res_ == null) {
            res_ = new StandardCoverageResult();
            res_.fullCover();
        }
        return res_;
    }

    private static IdMap<ExecOperationNode, OperationNode> getMapping(ContextEl _cont, Block _block, boolean _annotation) {
        if (_annotation) {
            return _cont.getCoverage().getMappingAnnotMembers().getVal(_block);
        }
        if (_block instanceof AnnotationMethodBlock) {
            return _cont.getCoverage().getMappingAnnot().getVal(_block);
        }
        return _cont.getCoverage().getMapping().getVal(_block);
    }
    private static void updateFieldAnchor(ContextEl _cont, CustList<PartOffset> _parts, ClassField _id, int _begin, int _length) {
        String className_ = _id.getClassName();
        String currentFileName_ = _cont.getCoverage().getCurrentFileName();
        className_ = Templates.getIdFromAllTypes(className_);
        GeneType type_ = _cont.getClassBody(className_);
        if (!isFromCustFile(type_)) {
            return;
        }
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
            if (exp_ instanceof ExecMethodOperation && op_ != null) {
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
            if (op_ != null && current_.getArgument() == null) {
                current_ = op_;
                continue;
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
