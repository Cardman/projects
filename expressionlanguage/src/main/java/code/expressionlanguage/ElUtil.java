package code.expressionlanguage;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.DeclareVariable;
import code.expressionlanguage.methods.FieldBlock;
import code.expressionlanguage.methods.ForLoopPart;
import code.expressionlanguage.methods.ForMutableIterativeLoop;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadElError;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.ExpLanguages;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.AbstractInvokingConstructor;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.DeclaringOperation;
import code.expressionlanguage.opers.DotOperation;
import code.expressionlanguage.opers.EmptyPartOperation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.InstanceOperation;
import code.expressionlanguage.opers.LeafOperation;
import code.expressionlanguage.opers.MethodOperation;
import code.expressionlanguage.opers.MutableLoopVariableOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.PossibleIntermediateDotted;
import code.expressionlanguage.opers.PreAnalyzableOperation;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.SettableElResult;
import code.expressionlanguage.opers.StaticAccessOperation;
import code.expressionlanguage.opers.StaticInitOperation;
import code.expressionlanguage.opers.VariableOperation;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class ElUtil {

    private ElUtil() {
    }

    public static ExpLanguages getAnalyzedAffectation(int _attrOp, int _attrLeft, int _attrRight,
            String _left, String _right, String _oper, ContextEl _conf, boolean _staticContext, boolean _hiddenVarTypes) {
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        page_.setOffset(0);
        page_.setGlobalOffset(_attrLeft);
        Delimiters dLeft_ = ElResolver.checkSyntax(_left, _conf, CustList.FIRST_INDEX);
        if (dLeft_.getBadOffset() >= 0) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(dLeft_.getBadOffset());
            badEl_.setEl(_left);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badEl_);
            return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
        }
        _conf.setAnalyzingRoot(false);
        OperationsSequence opTwoLeft_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _left, _conf, dLeft_);
        OperationNode opLeft_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwoLeft_, _conf);
        if (opTwoLeft_.isError()) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(dLeft_.getBadOffset());
            badEl_.setEl(_left);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badEl_);
            return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
        }
        CustList<OperationNode> allLeft_ = getSortedDescNodes(opLeft_, _hiddenVarTypes, _conf);
        page_.setOffset(0);
        page_.setGlobalOffset(_attrRight);
        Delimiters dRight_ = ElResolver.checkSyntax(_right, _conf, CustList.FIRST_INDEX);
        if (dRight_.getBadOffset() >= 0) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(dRight_.getBadOffset());
            badEl_.setEl(_right);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badEl_);
            return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
        }
        OperationsSequence opTwoRight_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _right, _conf, dRight_);
        OperationNode opRight_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwoRight_, _conf);
        if (opTwoRight_.isError()) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(dRight_.getBadOffset());
            badEl_.setEl(_right);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badEl_);
            return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
        }
        CustList<OperationNode> allRight_ = getSortedDescNodes(opRight_, _hiddenVarTypes, _conf);
        page_.setOffset(0);
        page_.setGlobalOffset(_attrLeft);
        page_.setOffset(0);
        page_.setGlobalOffset(_attrRight);
        page_.setOffset(0);
        page_.setGlobalOffset(_attrLeft);
        ClassArgumentMatching clMatchRight_ = opRight_.getResultClass();
        SettableElResult r_ = ExpressionLanguage.getSettable(allLeft_);
        if (r_ == null){
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(dRight_.getBadOffset());
            badEl_.setEl(_right);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badEl_);
            return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
        }
        ClassArgumentMatching clMatchLeft_ = r_.getResultClass();
        page_.setOffset(0);
        if (_attrOp >= 0) {
            page_.setGlobalOffset(_attrOp);
        }
        LgNames stds_ = _conf.getStandards();
        if (_oper.length() == 2) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(clMatchRight_.getName());
            mapping_.setParam(clMatchLeft_.getName());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setRc(_conf.getCurrentLocation());
            if (StringList.quickEq(_oper, Block.EQ_PLUS) || StringList.quickEq(_oper, Block.PLUS_EQ)) {
                if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
                    if (!clMatchLeft_.matchClass(_conf.getStandards().getAliasString())) {
                        _conf.getClasses().addError(cast_);
                        return new ExpLanguages(allLeft_, allRight_);
                    }
                } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_, _conf)) {
                    _conf.getClasses().addError(cast_);
                    return new ExpLanguages(allLeft_, allRight_);
                }
            } else if (StringList.quickEq(_oper, Block.AND_EQ) || StringList.quickEq(_oper, Block.OR_EQ)) {
                if (!StringList.quickEq(clMatchLeft_.getName(), stds_.getAliasBoolean())) {
                    if (!StringList.quickEq(clMatchLeft_.getName(), stds_.getAliasPrimBoolean())) {
                        _conf.getClasses().addError(cast_);
                        return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
                    }
                }
                if (!StringList.quickEq(clMatchRight_.getName(), stds_.getAliasBoolean())) {
                    if (!StringList.quickEq(clMatchRight_.getName(), stds_.getAliasPrimBoolean())) {
                        _conf.getClasses().addError(cast_);
                        return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
                    }
                }
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
                _conf.getClasses().addError(cast_);
                return new ExpLanguages(allLeft_, allRight_);
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_, _conf)) {
                _conf.getClasses().addError(cast_);
                return new ExpLanguages(allLeft_, allRight_);
            }
            r_.getResultClass().setUnwrapObject(clMatchLeft_.getName());
            opRight_.getResultClass().setUnwrapObject(clMatchLeft_.getName());
        } else {
            if (clMatchRight_.isVariable()) {
                if (!clMatchLeft_.isPrimitive(_conf)) {
                    return new ExpLanguages(allLeft_, allRight_);
                }
                Mapping mapping_ = new Mapping();
                mapping_.setArg(clMatchRight_.getName());
                mapping_.setParam(clMatchLeft_.getName());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(cast_);
                return new ExpLanguages(allLeft_, allRight_);
            }
            StringMap<StringList> vars_ = new StringMap<StringList>();
            boolean buildMap_ = true;
            if (_staticContext) {
                buildMap_ = false;
            } else if (page_.getGlobalClass() == null) {
                buildMap_ = false;
            }
            if (buildMap_) {
                for (TypeVar t: Templates.getConstraints(page_.getGlobalClass(), _conf)) {
                    vars_.put(t.getName(), t.getConstraints());
                }
            }
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(vars_);
            mapping_.setArg(clMatchRight_.getName());
            mapping_.setParam(clMatchLeft_.getName());
            if (!Templates.isCorrect(mapping_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(cast_);
            }
            if (PrimitiveTypeUtil.isPrimitive(clMatchLeft_.getName(), _conf)) {
                opRight_.getResultClass().setUnwrapObject(clMatchLeft_.getName());
            }
        }
        return new ExpLanguages(allLeft_, allRight_);
    }
    public static Argument tryToCalculate(ContextEl _conf, ExpressionLanguage _right, int _offset) {
        if (_right.isFinished()) {
            return _right.getArgument();
        }
        IdMap<OperationNode, ArgumentsPair> allRight_ = _right.getArguments();
        calculate(allRight_, _right, _conf, _offset);
        if (_conf.callsOrException()) {
            return _right.getArgument();
        }
        _right.finish();
        Argument arg_ = _right.getArgument();
        return arg_;
    }
    public static void tryToCalculateLeftAffect(ExpressionLanguage _left, ContextEl _conf, String _op) {
        if (_left.isFinished()) {
            return;
        }
        IdMap<OperationNode, ArgumentsPair> allLeft_ = _left.getArguments();
        calculate(allLeft_, _left, _conf, 0);
        if (_conf.callsOrException()) {
            return;
        }
        _left.finish();
    }
    public static void tryToCalculateRightAffect(ExpressionLanguage _right, ContextEl _conf, String _op) {
        if (_right.isFinished()) {
            return;
        }
        IdMap<OperationNode, ArgumentsPair> allRight_ = _right.getArguments();
        calculate(allRight_, _right, _conf, 0);
        if (_conf.callsOrException()) {
            return;
        }
        _right.finish();
        _conf.getLastPage().setRightArgument(_right.getArgument());
    }
    public static void tryToCalculateAllAffect(ExpressionLanguage _left, ContextEl _conf, String _op) {
        IdMap<OperationNode, ArgumentsPair> allLeft_ = _left.getArguments();
        SettableElResult settable_ = _left.getSettable();
        OperationNode op_ = (OperationNode) settable_;
        ArgumentsPair a_ = allLeft_.getVal(op_);
        a_.setArgument(Argument.createVoid());
    }


    public static CustList<OperationNode> getAnalyzedOperations(String _el, ContextEl _conf, Calculation _calcul) {
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, CustList.FIRST_INDEX);
        if (d_.getBadOffset() >= 0) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(d_.getBadOffset());
            badEl_.setEl(_el);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badEl_);
            EmptyPartOperation e_ = new EmptyPartOperation(0, 0, null, null);
            String argClName_ = _conf.getStandards().getAliasObject();
            e_.setResultClass(new ClassArgumentMatching(argClName_));    
            Block currentBlock_ = _conf.getCurrentBlock();
            if (currentBlock_ != null) {
                currentBlock_.defaultAssignmentBefore(_conf, e_);
                e_.tryAnalyzeAssignmentAfter(_conf);
                currentBlock_.defaultAssignmentAfter(_conf, e_);
            }
            return new CustList<OperationNode>(e_);
        }
        _conf.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _el, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_, _conf);
        if (opTwo_.isError()) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(d_.getBadOffset());
            badEl_.setEl(_el);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badEl_);
        }
        _conf.setAnalyzingRoot(false);
        String fieldName_ = _calcul.getFieldName();
        boolean hiddenVarTypes_ = _calcul.isStaticBlock();
        _conf.setStaticContext(hiddenVarTypes_ || op_ instanceof AbstractInvokingConstructor);
        if (op_ instanceof InstanceOperation) {
            ((InstanceOperation)op_).setFieldName(fieldName_);
        }
        CustList<OperationNode> all_ = getSortedDescNodes(op_, hiddenVarTypes_, _conf);
        return all_;
    }


    public static CustList<OperationNode> getSortedDescNodes(OperationNode _root, boolean _staticBlock,Analyzable _context) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        _context.getTextualSortedOperations().clear();
        Block currentBlock_ = _context.getCurrentBlock();
        if (currentBlock_ != null && !_context.isAnnotAnalysis()) {
            currentBlock_.defaultAssignmentBefore(_context, _root);
        }
        OperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                if (currentBlock_ != null && !_context.isAnnotAnalysis()) {
                    currentBlock_.defaultAssignmentAfter(_context, _root);
                }
                break;
            }
            if (c_ instanceof PreAnalyzableOperation) {
                ((PreAnalyzableOperation)c_).preAnalyze(_context);
            }
            c_ = getAnalyzedNext(c_, _root, list_, _staticBlock, _context);
        }
        return list_;
    }

    private static OperationNode getAnalyzedNext(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, boolean _staticBlock,Analyzable _context) {
        _context.getTextualSortedOperations().add(_current);
        
        OperationNode next_ = createFirstChild(_current, _context, 0);
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            if (!_context.isAnnotAnalysis()) {
                ((MethodOperation) _current).tryAnalyzeAssignmentBefore(_context, next_);
            }
            return next_;
        }
        OperationNode current_ = _current;
        while (true) {
            current_.setStaticBlock(_staticBlock);
            current_.analyze(_context);
            current_.tryCalculateNode(_context);
            if (!_context.isAnnotAnalysis()) {
                current_.tryAnalyzeAssignmentAfter(_context);
            }
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
                        badNb_.setRc(_context.getCurrentLocation());
                        _context.getClasses().addError(badNb_);
                    } else {
                        PossibleIntermediateDotted possible_ = (PossibleIntermediateDotted) next_;
                        boolean static_ = current_ instanceof StaticAccessOperation;
                        possible_.setIntermediateDotted();
                        possible_.setPreviousArgument(current_.getArgument());
                        possible_.setPreviousResultClass(current_.getResultClass(), static_);
                        current_.setSiblingSet(possible_);
                    }
                }
                par_.appendChild(next_);
                if (!_context.isAnnotAnalysis()) {
                    par_.tryAnalyzeAssignmentBeforeNextSibling(_context, next_, current_);
                }
                return next_;
            }
            if (par_ == _root) {
                par_.setStaticBlock(_staticBlock);
                par_.analyze(_context);
                ClassArgumentMatching cl_ = par_.getResultClass();
                if (PrimitiveTypeUtil.isPrimitive(cl_, _context)) {
                    cl_.setUnwrapObject(cl_);
                }
                par_.tryCalculateNode(_context);
                if (!_context.isAnnotAnalysis()) {
                    par_.tryAnalyzeAssignmentAfter(_context);
                }
                _sortedNodes.add(par_);
                return null;
            }
            if (par_ == null) {
                return null;
            }
            current_ = par_;
        }
    }
    private static OperationNode createFirstChild(OperationNode _block, Analyzable _context, int _index) {
        if (!(_block instanceof MethodOperation)) {
            return null;
        }
        MethodOperation block_ = (MethodOperation) _block;
        if (block_.getChildren() == null || block_.getChildren().isEmpty()) {
            if (_context.getOptions().isInitializeStaticClassFirst() && _block instanceof InstanceOperation) {
                if (((InstanceOperation)_block).initStaticClass() && _index == CustList.FIRST_INDEX) {
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
        d_.setChildOffest(curKey_);
        int offset_ = block_.getIndexInEl()+curKey_;
        if (_context.getOptions().isInitializeStaticClassFirst() && _block instanceof InstanceOperation) {
            if (((InstanceOperation)_block).initStaticClass() && _index == CustList.FIRST_INDEX) {
                OperationsSequence opSeq_ = new OperationsSequence();
                opSeq_.setFctName(block_.getOperations().getFctName());
                opSeq_.setDelimiter(new Delimiters());
                opSeq_.getDelimiter().setIndexBegin(d_.getIndexBegin());
                return new StaticInitOperation(block_.getIndexInEl(), CustList.FIRST_INDEX, block_, opSeq_);
            }
        }
        _context.setAnalyzingRoot(block_ instanceof DeclaringOperation);
        OperationsSequence r_ = ElResolver.getOperationsSequence(offset_, value_, _context, d_);
        OperationNode op_ = OperationNode.createOperationNode(offset_, _index, block_, r_, _context);
        if (r_.isError()) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(offset_);
            badEl_.setEl(value_);
            badEl_.setFileName(_context.getCurrentFileName());
            badEl_.setRc(_context.getCurrentLocation());
            _context.getClasses().addError(badEl_);
        }
        return op_;
    }

    private static OperationNode createNextSibling(OperationNode _block, Analyzable _context) {
        MethodOperation p_ = _block.getParent();
        if (p_ == null) {
            return null;
        }
        NatTreeMap<Integer,String> children_ = p_.getChildren();
        int delta_ = 1;
        if (p_ instanceof InstanceOperation) {
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
        d_.setChildOffest(curKey_);
        int offset_ = p_.getIndexInEl()+curKey_;
        _context.setAnalyzingRoot(p_ instanceof DeclaringOperation);
        OperationsSequence r_ = ElResolver.getOperationsSequence(offset_, value_, _context, d_);
        OperationNode op_ = OperationNode.createOperationNode(offset_, _block.getIndexChild() + 1, p_, r_, _context);
        if (r_.isError()) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(offset_);
            badEl_.setEl(value_);
            badEl_.setFileName(_context.getCurrentFileName());
            badEl_.setRc(_context.getCurrentLocation());
            _context.getClasses().addError(badEl_);
        }
        return op_;
    }
    public static boolean isDeclaringLoopVariable(MutableLoopVariableOperation _var, Analyzable _an) {
        Block bl_ = _an.getCurrentBlock();
        if (!_an.isMerged()) {
            return false;
        }
        if (!(bl_ instanceof ForMutableIterativeLoop)) {
            return false;
        }
        if (_an.getForLoopPartState() != ForLoopPart.INIT) {
            return false;
        }
        return isDeclaringVariable(_var);
    }
    public static boolean isDeclaringLoopVariable(MethodOperation _par, Analyzable _an) {
        Block bl_ = _an.getCurrentBlock();
        if (!_an.isMerged()) {
            return false;
        }
        if (!(bl_ instanceof ForMutableIterativeLoop)) {
            return false;
        }
        if (_an.getForLoopPartState() != ForLoopPart.INIT) {
            return false;
        }
        return isDeclaringVariable(_par);
    }
    public static boolean isDeclaringVariable(VariableOperation _var, Analyzable _an) {
        Block bl_ = _an.getCurrentBlock();
        if (!_an.isMerged()) {
            return false;
        }
        if (bl_ == null) {
            return false;
        }
        if (!(bl_.getPreviousSibling() instanceof DeclareVariable)) {
            return false;
        }
        return isDeclaringVariable(_var);
    }
    public static boolean isDeclaringVariable(MethodOperation _par, Analyzable _an) {
        Block bl_ = _an.getCurrentBlock();
        if (!_an.isMerged()) {
            return false;
        }
        if (bl_ == null) {
            return false;
        }
        if (!(bl_.getPreviousSibling() instanceof DeclareVariable)) {
            return false;
        }
        return isDeclaringVariable(_par);
    }
    private static boolean isDeclaringVariable(LeafOperation _var) {
        MethodOperation par_ = _var.getParent();
        if (par_ == null) {
            return true;
        }
        if (par_ instanceof DeclaringOperation) {
            return true;
        }
        if (par_ instanceof AffectationOperation) {
            if (par_.getParent() == null) {
                if (_var == par_.getFirstChild()) {
                    return true;
                }
            }
            if (par_.getParent() instanceof DeclaringOperation) {
                if (_var == par_.getFirstChild()) {
                    return true;
                }
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
                if (null == _par.getFirstChild()) {
                    return true;
                }
            }
            if (_par.getParent() instanceof DeclaringOperation) {
                if (null == _par.getFirstChild()) {
                    return true;
                }
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
        if (stepForLoop(_conf)) {
            return true;
        }
        boolean checkFinal_ = false;
        if (_conf.isAssignedFields()) {
            checkFinal_ = true;
        } else if (_conf.isAssignedStaticFields()) {
            FieldInfo meta_ = _conf.getFieldInfo(cl_);
            if (meta_.isStaticField()) {
                checkFinal_ = true;
            } else if (!fromCurClass_) {
                checkFinal_ = true;
            } else {
                if (!_ass.getVal(cl_.getFieldName()).isUnassignedAfter()) {
                    checkFinal_ = true;
                }
            }
        } else if (!fromCurClass_) {
            checkFinal_ = true;
        } else {
            if (!_ass.getVal(cl_.getFieldName()).isUnassignedAfter()) {
                checkFinal_ = true;
            }
        }
        return checkFinal_;
    }
    public static boolean stepForLoop(Analyzable _conf) {
        if (_conf.getCurrentBlock() instanceof ForMutableIterativeLoop) {
            if (_conf.getForLoopPartState() == ForLoopPart.STEP) {
                return true;
            }
        }
        return false;
    }
    public static CustList<OperationNode> getDirectChildren(OperationNode _element) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode firstChild_ = _element.getFirstChild();
        OperationNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    static void calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ExpressionLanguage _el, ContextEl _context, int _offset) {
        AbstractPageEl pageEl_ = _context.getLastPage();
        pageEl_.setTranslatedOffset(_offset);
        for (EntryCust<OperationNode,ArgumentsPair> e: _nodes.entryList()) {
            OperationNode o = e.getKey();
            if (!o.isCalculated(_nodes)) {
                ArgumentsPair a_ = e.getValue();
                Argument arg_ = o.calculate(_nodes, _context);
                if (_context.getException() != null) {
                    pageEl_.setTranslatedOffset(0);
                    pageEl_.clearCurrentEls();
                    return;
                }
                if (_context.calls()) {
                    _el.setCurrentOper(o);
                    return;
                }
                a_.setArgument(arg_);
            }
        }
        _context.getLastPage().setTranslatedOffset(0);
    }
    public static void tryCalculate(FieldBlock _field, ContextEl _context, EqList<SortedClassField> _list) {
        RootBlock r_ = (RootBlock) _field.getParent();
        String fieldName_ = _field.getFieldName();
        ClassField key_ = new ClassField(r_.getFullName(), fieldName_);
        for (SortedClassField f: _list) {
            if (f.getClassField().eq(key_)) {
                tryCalculate(_field.getOpValue(), _context, 0, _list, f);
                if (f.isOk()) {
                    _context.getClasses().initializeStaticField(key_, f.getStruct());
                }
                break;
            }
        }
    }
    static void tryCalculate(CustList<OperationNode> _nodes, ContextEl _context, int _offset, EqList<SortedClassField> _list, SortedClassField _current) {
        AnalyzedPageEl pageEl_ = _context.getAnalyzing();
        pageEl_.setTranslatedOffset(_offset);
        for (OperationNode o: _nodes) {
            if (!o.isCalculated()) {
                o.tryCalculateNode(_context, _list, _current);
            }
        }
        Argument arg_ = _nodes.last().getArgument();
        if (arg_ == null) {
            _current.setOk(false);
            pageEl_.setTranslatedOffset(0);
            return;
        }
        ClassField key_ = _current.getClassField();
        FieldInfo fm_ = _context.getFieldInfo(key_);
        Struct str_ = arg_.getStruct();
        str_ = PrimitiveTypeUtil.convertObject(new ClassArgumentMatching(fm_.getType()), str_, _context);
        _current.setStruct(str_);
        pageEl_.setTranslatedOffset(0);
    }
}
