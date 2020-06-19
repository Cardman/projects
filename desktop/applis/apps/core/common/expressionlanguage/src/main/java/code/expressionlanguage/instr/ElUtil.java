package code.expressionlanguage.instr;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.coverage.*;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.*;
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
        _conf.getAnalyzing().setAccessStaticContext(hiddenVarTypes_);
        Delimiters d_ = ElResolver.checkSyntaxQuick(_el, _conf);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _el, _conf, d_);
        CustList<PartOffsetAffect> names_ = new CustList<PartOffsetAffect>();
        if (opTwo_.getOperators().isEmpty()) {
            for (EntryCust<Integer,String> e: opTwo_.getValues().entryList()) {
                String var_ = e.getValue();
                String trimmed_ = var_.trim();
                String name_ = getFieldName(trimmed_);
                int offset_ = _valueOffset + e.getKey();
                if (StringList.isDollarWord(trimmed_)) {
                    addFieldName(names_, var_, offset_, false, name_);
                }
            }
            return names_;
        }
        if (opTwo_.getPriority() == ElResolver.DECL_PRIO) {
            for (EntryCust<Integer,String> e: opTwo_.getValues().entryList()) {
                String var_ = e.getValue();
                String trimmed_ = var_.trim();
                String name_ = getFieldName(trimmed_);
                int offset_ = _valueOffset + e.getKey();
                if (StringList.isDollarWord(trimmed_)) {
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
                && StringList.quickEq(opTwo_.getOperators().firstValue(),"=")) {
            String var_ = opTwo_.getValues().firstValue();
            int off_ = opTwo_.getValues().firstKey();
            String trimmed_ = var_.trim();
            String name_ = getFieldName(trimmed_);
            if (StringList.isDollarWord(trimmed_)) {
                addFieldName(names_,var_,_valueOffset+off_,true,name_);
            }
        }
        return names_;
    }

    private static void addFieldName(CustList<PartOffsetAffect> _list, String _name, int _offset, boolean _aff, String name_) {
        int delta_ = StringList.getFirstPrintableCharIndex(_name);
        _list.add(new PartOffsetAffect(new PartOffset(name_,delta_+_offset),_aff));
    }

    private static String getFieldName(String _v) {
        int k_ = 0;
        int lenField_ = _v.length();
        StringBuilder fieldName_ = new StringBuilder();
        while (k_ < lenField_) {
            char fieldChar_ = _v.charAt(k_);
            if (!StringList.isDollarWordChar(fieldChar_)) {
                break;
            }
            fieldName_.append(fieldChar_);
            k_++;
        }
        return fieldName_.toString();
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
        _conf.getAnalyzing().setAccessStaticContext(access_);
    }

    public static CustList<ExecOperationNode> getAnalyzedOperationsReadOnly(String _el, ContextEl _conf, Calculation _calcul) {
        MethodAccessKind hiddenVarTypes_ = _calcul.getStaticBlock();
        _conf.getAnalyzing().setAccessStaticContext(hiddenVarTypes_);
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, CustList.FIRST_INDEX);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            badEl_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
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


    public static CustList<OperationNode> getAnalyzedOperationsQucikly(String _el, ContextEl _conf, Calculation _calcul) {
        MethodAccessKind hiddenVarTypes_ = _calcul.getStaticBlock();
        _conf.getAnalyzing().setAccessStaticContext(hiddenVarTypes_);
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, CustList.FIRST_INDEX);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            badEl_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
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
            return new CustList<OperationNode>(e_);
        }
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _el, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _conf);
        String fieldName_ = _calcul.getFieldName();
        return getSortedDescNodesReadOnly(op_, _conf,fieldName_);
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

    private static void tryCalculateNode(ContextEl _context, OperationNode _current) {
        if (_current instanceof ReductibleOperable) {
            ((ReductibleOperable) _current).tryCalculateNode(_context);
        }
    }

    private static void processDot(ContextEl _context, OperationNode _next, OperationNode _current, MethodOperation _par) {
        if (_par instanceof AbstractDotOperation) {
            if (!(_next instanceof PossibleIntermediateDotted)) {
                return;
            }
            if (_current instanceof StaticCallAccessOperation){
                PossibleIntermediateDotted possible_ = (PossibleIntermediateDotted) _next;
                possible_.setIntermediateDotted();
                possible_.setPreviousArgument(Argument.createVoid());
                MethodAccessKind access_ = MethodAccessKind.STATIC_CALL;
                if (!(_next instanceof LambdaOperation) && ((StaticCallAccessOperation)_current).isImplicit() && _context.getAnalyzing().getStaticContext() == MethodAccessKind.STATIC) {
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
            _context.getAnalyzing().setOkNumOp(true);
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
                _context.getAnalyzing().setOkNumOp(true);
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

    public static boolean isDeclaringField(OperationNode _var, ContextEl _an) {
        Block bl_ = _an.getAnalyzing().getCurrentBlock();
        if (!(bl_ instanceof FieldBlock)) {
            return false;
        }
        if (!(_var instanceof StandardFieldOperation)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }

    public static boolean isDeclaringLoopVariable(MutableLoopVariableOperation _var, ContextEl _an) {
        if (!isDeclaringLoopVariable(_an)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }
    public static boolean isDeclaringLoopVariable(MethodOperation _par, ContextEl _an) {
        if (!isDeclaringLoopVariable(_an)) {
            return false;
        }
        return isDeclaringVariable(_par);
    }
    static boolean isDeclaringLoopVariable(ContextEl _an) {
        if (!_an.getAnalyzing().isMerged()) {
            return false;
        }
        if (!_an.getAnalyzing().getLoopDeclaring().hasLoopDeclarator()) {
            return false;
        }
        return _an.getAnalyzing().getForLoopPartState() == ForLoopPart.INIT;
    }
    public static boolean isDeclaringVariable(VariableOperation _var, ContextEl _an) {
        if (!isDeclaringVariable(_an)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }
    public static boolean isDeclaringVariable(MethodOperation _par, ContextEl _an) {
        if (!isDeclaringVariable(_an)) {
            return false;
        }
        return isDeclaringVariable(_par);
    }
    static boolean isDeclaringVariable(ContextEl _an) {
        if (!_an.getAnalyzing().isMerged()) {
            return false;
        }
        return _an.getAnalyzing().getLocalDeclaring().hasDeclarator();
    }
    public static boolean isDeclaringVariable(OperationNode _var) {
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

    public static boolean checkFinalFieldReadOnly(ContextEl _conf, SettableAbstractFieldOperation _cst, StringMap<Boolean> _ass) {
        boolean fromCurClass_ = _cst.isFromCurrentClassReadOnly(_conf);
        ClassField cl_ = _cst.getFieldIdReadOnly();
        FieldInfo meta_ = _conf.getFieldInfo(cl_);
        if (meta_ == null) {
            return false;
        }
        String fieldName_ = cl_.getFieldName();
        return meta_.isFinalField() && checkFinalReadOnly(_conf, _cst, _ass, fromCurClass_, cl_, fieldName_);
    }
    private static boolean checkFinalReadOnly(ContextEl _conf, SettableAbstractFieldOperation _cst, StringMap<Boolean> _ass, boolean _fromCurClass, ClassField _cl, String _fieldName) {
        boolean checkFinal_;
        if (_conf.isAssignedFields()) {
            checkFinal_ = true;
        } else if (_conf.isAssignedStaticFields()) {
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

    private static void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExpressionLanguage _el, ContextEl _context, int _offset) {
        AbstractPageEl pageEl_ = _context.getLastPage();
        pageEl_.setTranslatedOffset(_offset);
        int fr_ = _el.getIndex();
        int len_ = _nodes.size();
        while (fr_ < len_) {
            ExecOperationNode o = _nodes.getKey(fr_);
            ArgumentsPair pair_ = _nodes.getValue(fr_);
            if (!(o instanceof AtomicExecCalculableOperation)) {
                Argument a_ = Argument.getNullableValue(o.getArgument());
                if (!pair_.getImplicits().isEmpty()) {
                    o.setSimpleArgument(a_,_context,_nodes);
                }
                if (_context.callsOrException()) {
                    processCalling(_el, _context, pageEl_, o);
                    return;
                }
                _context.getCoverage().passBlockOperation(_context,o,a_,true);
                fr_++;
                continue;
            }
            if (pair_.getArgument() != null) {
                if (!pair_.getImplicits().isEmpty()) {
                    o.setSimpleArgument(pair_.getArgument(),_context,_nodes);
                }
                if (_context.callsOrException()) {
                    processCalling(_el, _context, pageEl_, o);
                    return;
                }
                _context.getCoverage().passBlockOperation(_context,o,pair_.getArgument(),true);
                fr_++;
                continue;
            }
            AtomicExecCalculableOperation a_ = (AtomicExecCalculableOperation)o;
            a_.calculate(_nodes, _context);
            if (_context.callsOrException()) {
                processCalling(_el, _context, pageEl_, o);
                return;
            }
            Argument res_ = pair_.getArgument();
            Struct st_ = res_.getStruct();
            fr_ = ExecOperationNode.getNextIndex(o, st_);
        }
        pageEl_.setTranslatedOffset(0);
    }

    private static void processCalling(ExpressionLanguage _el, ContextEl _context, AbstractPageEl _pageEl, ExecOperationNode _o) {
        _el.setCurrentOper(_o);
        if (!_context.calls()) {
            _pageEl.setTranslatedOffset(0);
        }
    }
    public static void tryCalculate(FieldBlock _field, CustList<OperationNode> _ops, ContextEl _context, String _fieldName) {
        CustList<OperationNode> nodes_ = _ops;
        OperationNode root_ = nodes_.last();
        CustList<OperationNode> sub_;
        if (!(root_ instanceof DeclaringOperation)) {
            sub_ = nodes_;
        } else {
            MethodOperation m_ = (MethodOperation)root_;
            int index_ = StringList.indexOf(_field.getFieldName(),_fieldName);
            CustList<OperationNode> ch_ = m_.getChildrenNodes();
            OperationNode rootLoc_ = ch_.get(index_);
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
            OperationNode curr_ = sub_.get(ind_);
            Argument a_ = curr_.getArgument();
            if (a_ != null) {
                ind_ = ExecOperationNode.getNextIndex(curr_, a_.getStruct());
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
    private static CustList<ExecOperationNode> getExecutableNodes(ContextEl _an,CustList<OperationNode> _list) {
        Block bl_ = _an.getAnalyzing().getCurrentBlock();
        CustList<ExecOperationNode> out_ = new CustList<ExecOperationNode>();
        OperationNode root_ = _list.last();
        OperationNode current_ = root_;
        ExecOperationNode exp_ = ExecOperationNode.createExecOperationNode(current_);
        _an.getCoverage().setCurrentRoot(root_);
        _an.getCoverage().putBlockOperation(_an,bl_,root_,current_,exp_);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (exp_ instanceof ExecMethodOperation && op_ != null) {
                ExecOperationNode loc_ = ExecOperationNode.createExecOperationNode(op_);
                _an.getCoverage().putBlockOperation(_an,bl_,root_,op_,loc_);
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
                    ExecOperationNode loc_ = ExecOperationNode.createExecOperationNode(op_);
                    _an.getCoverage().putBlockOperation(_an,bl_,root_,op_,loc_);
                    ExecMethodOperation par_ = exp_.getParent();
                    par_.appendChild(loc_);
                    if (op_.getParent() instanceof AbstractDotOperation && loc_ instanceof ExecPossibleIntermediateDotted) {
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
