package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.analyze.opers.CompoundAffectationOperation;
import code.expressionlanguage.analyze.opers.NullSafeOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.SafeDotOperation;
import code.expressionlanguage.exec.calls.ReflectAnnotationPageEl;
import code.expressionlanguage.exec.calls.ReflectGetDefaultValuePageEl;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class Coverage {
    private final CustList<FileBlock> files = new CustList<FileBlock>();
    private final CustList<RootBlock> refFoundTypes = new CustList<RootBlock>();
    private final CustList<OperatorBlock> refOperators = new CustList<OperatorBlock>();
    private final StringList toStringOwners = new StringList();
    private final StringList randCodeOwners = new StringList();
    private final CustList<TypeCoverageResult> types = new CustList<TypeCoverageResult>();
    private final CustList<FunctionCoverageResult> lambdas = new CustList<FunctionCoverageResult>();
    private final CustList<FunctionCoverageResult> switchMethods = new CustList<FunctionCoverageResult>();
    private final CustList<FunctionCoverageResult> operators = new CustList<FunctionCoverageResult>();
    private final IdMap<ExecBlock,MemberCallingsBlock> mappingOperators = new IdMap<ExecBlock,MemberCallingsBlock>();
    private final IdMap<ExecBlock,MemberCallingsBlock> mappingLambdas = new IdMap<ExecBlock,MemberCallingsBlock>();
    private final IdMap<ExecBlock,MemberCallingsBlock> mappingSwitchMethods = new IdMap<ExecBlock,MemberCallingsBlock>();
    private final IdMap<ExecBlock,RootBlock> mappingTypes = new IdMap<ExecBlock,RootBlock>();
    private KeyWords keyWords;
    private final boolean covering;
    private boolean implicit;
    private boolean displayEncode;

    public Coverage(boolean _covering) {
        this.covering = _covering;
    }

    public void putFile(FileBlock _file) {
        if (!isCovering()) {
            return;
        }
        files.add(_file);
    }

    public void putType(RootBlock _type) {
        if (!isCovering()) {
            return;
        }
        refFoundTypes.add(_type);
        types.add(new TypeCoverageResult());
    }

    public void putOperator(OperatorBlock _type) {
        if (!isCovering()) {
            return;
        }
        refOperators.add(_type);
        operators.add(new FunctionCoverageResult());
    }
    public void putCallsInstanceBlock(RootBlock _type) {
        if (!isCovering()) {
            return;
        }
        types.get(_type.getNumberAll()).getFunctionsInst().add(new FunctionCoverageResult());
    }
    public void putCallsStaticBlock(RootBlock _type) {
        if (!isCovering()) {
            return;
        }
        types.get(_type.getNumberAll()).getFunctionsStat().add(new FunctionCoverageResult());
    }
    public void putCallsConstructorBlock(RootBlock _type) {
        if (!isCovering()) {
            return;
        }
        types.get(_type.getNumberAll()).getFunctionsCtor().add(new FunctionCoverageResult());
    }
    public void putCallsNamedCalledFunctionBlock(RootBlock _type) {
        if (!isCovering()) {
            return;
        }
        types.get(_type.getNumberAll()).getFunctions().add(new FunctionCoverageResult());
    }
    public void putCallsAnon() {
        if (!isCovering()) {
            return;
        }
        lambdas.add(new FunctionCoverageResult());
    }
    public void putCallsSwitchMethod() {
        if (!isCovering()) {
            return;
        }
        switchMethods.add(new FunctionCoverageResult());
    }
    public void putBlockOperationsLoops(AbstractForLoop _block, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctResBl(_block);
        _block.setConditionNb(fctRes_.getCoverLoops().size());
        fctRes_.getCoverLoops().addEntry(_exec,new BooleanCoverageResult());
    }

    public void putBlockOperationsConditions(AbsBk _block, WithConditionPart _bk, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctResBl(_block);
        _bk.setConditionNb(fctRes_.getCoversConditions().size());
        fctRes_.getCoversConditions().addEntry(_exec,new BooleanCoverageResult());
    }

    public void putBlockOperationsSwitchs(SwitchBlock _block, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctResBl(_block);
        _block.setConditionNb(fctRes_.getCoverSwitchs().size());
        fctRes_.getCoverSwitchs().addEntry(_exec,new SwitchCoverageResult());
    }

    public void putBlockOperationsSwitchsPart(SwitchBlock _block, SwitchPartBlock _child, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctResBl(_block);
        SwitchCoverageResult swRes_ = fctRes_.getCoverSwitchs().getValue(_block.getConditionNb());
        _child.setConditionNb(swRes_.getChildren().size());
        feed(_child, _exec, swRes_);
    }

    public void putBlockOperationsSwitchsMethodPart(SwitchPartBlock _child, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctResBl(_child);
        SwitchCoverageResult swRes_ = fctRes_.getCoverSwitchsMethod();
        _child.setConditionNb(swRes_.getChildren().size());
        feed(_child, _exec, swRes_);
    }

    private static void feed(SwitchPartBlock _child, ExecBlock _exec, SwitchCoverageResult _swRes) {
        if (_child instanceof DefaultCondition) {
            _swRes.setDefCase(true);
            _swRes.getChildren().addEntry(_exec, new CustList<AbstractCoverageResult>(new StandardCoverageResult()));
        }
        if (_child instanceof CaseCondition) {
            int count_ = Math.max(1,((CaseCondition) _child).getStdValues().size()+ ((CaseCondition) _child).getEnumValues().size());
            CustList<AbstractCoverageResult> list_ = new CustList<AbstractCoverageResult>();
            for (int i = 0; i < count_; i++) {
                list_.add(new StandardCoverageResult());
            }
            _swRes.getChildren().addEntry(_exec, list_);
        }
    }

    public void putCatches(AbstractCatchEval _block, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctResBl(_block);
        _block.setConditionNb(fctRes_.getCatches().size());
        fctRes_.getCatches().addEntry(_exec,BoolVal.FALSE);
    }
    public void putBlockOperationsPre(MemberCallingsBlock _mem, AbsBk _block) {
        if (!isCovering()) {
            return;
        }
        _block.setOuterFct(_mem);
        FunctionCoverageResult fctRes_ = getFctResBl(_block);
        _block.setBlockNb(fctRes_.getBlocks().size());
        fctRes_.getBlocks().add(new BlockCoverageResult());
    }

    public void putBlockOperations(ExecBlock _exec, AbsBk _block) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctResBl(_block);
        fctRes_.getMappingBlocks().addEntry(_exec, _block);
    }

    public void putBlockOperationsCaller(ExecBlock _exec, MemberCallingsBlock _block) {
        if (!isCovering()) {
            return;
        }
        if (_block instanceof OperatorBlock) {
            mappingOperators.addEntry(_exec,_block);
            return;
        }
        if (AbsBk.isAnonBlock(_block)) {
            mappingLambdas.addEntry(_exec,_block);
            return;
        }
        if (_block instanceof SwitchMethodBlock) {
            mappingSwitchMethods.addEntry(_exec,_block);
            return;
        }
        types.get(((RootBlock)_block.getParent()).getNumberAll()).getMappingBlocks().addEntry(_exec, _block);
    }
    public void putBlockOperationsType(ExecBlock _exec, RootBlock _block) {
        if (!isCovering()) {
            return;
        }
        mappingTypes.addEntry(_exec,_block);
    }
    public void putBlockOperationsField(AbsBk _block) {
        if (!isCovering()) {
            return;
        }
        CustList<BlockCoverageResult> fctRes_ = getTypeRes(_block);
        fctRes_.add(new BlockCoverageResult());
    }

    public void putBlockOperationsField(ExecBlock _exec, AbsBk _block) {
        if (!isCovering()) {
            return;
        }
        types.get(((RootBlock)_block.getParent()).getNumberAll()).getMappingFields().addEntry(_exec, _block);
    }
    public void putBlockOperationsAnnotMethodField(AbsBk _block) {
        if (!isCovering()) {
            return;
        }
        types.get(((RootBlock)_block.getParent()).getNumberAll()).getAnnotationsFields().add(new BlockCoverageResult());
    }
    public void putBlockOperationsAnnotField(InfoBlock _block) {
        if (!isCovering()) {
            return;
        }
        CustList<BlockCoverageResult> fctRes_ = getTypeRes((AbsBk)_block);
        fctRes_.get(_block.getFieldNumber()).getAnnotations().add(new BlockCoverageResult());
    }
    public void putBlockOperationsAnnotType(RootBlock _block) {
        if (!isCovering()) {
            return;
        }
        TypeCoverageResult fctRes_ = types.get(_block.getNumberAll());
        fctRes_.getAnnotations().add(new BlockCoverageResult());
    }
    public void putBlockOperationsAnnotMethod(MemberCallingsBlock _block) {
        if (!isCovering()) {
            return;
        }
        if (AbsBk.isAnnotBlock(_block)) {
            NamedCalledFunctionBlock mem_ = (NamedCalledFunctionBlock) _block;
            BlockCoverageResult fctRes_ = types.get(((RootBlock)mem_.getParent()).getNumberAll()).getAnnotationsFields().get(mem_.getNameNumber());
            fctRes_.getAnnotations().add(new BlockCoverageResult());
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_block);
        fctRes_.getAnnotations().add(new BlockCoverageResult());
    }
    public void putBlockOperationsAnnotMethodParam(MemberCallingsBlock _block) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_block);
        fctRes_.getAnnotationsParams().add(new CustList<BlockCoverageResult>());
    }
    public void putBlockOperationsAnnotMethod(MemberCallingsBlock _block, int _indexGroup) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_block);
        fctRes_.getAnnotationsParams().get(_indexGroup).add(new BlockCoverageResult());
    }
    public FunctionCoverageResult getFctResBl(AbsBk _mem) {
        return getFctRes(_mem.getOuterFct());
    }
    public FunctionCoverageResult getFctRes(MemberCallingsBlock _mem) {
        FunctionCoverageResult fctRes_;
        if (_mem instanceof OperatorBlock) {
            fctRes_ = operators.get(((OperatorBlock) _mem).getOperatorNumber());
        } else if (AbsBk.isAnonBlock(_mem)){
            fctRes_ = lambdas.get(((NamedCalledFunctionBlock)_mem).getNumberLambda());
        } else if (_mem instanceof SwitchMethodBlock){
            fctRes_ = switchMethods.get(((SwitchMethodBlock)_mem).getConditionNb());
        } else {
            TypeCoverageResult type_ = types.get(((RootBlock) _mem.getParent()).getNumberAll());
            if (_mem instanceof InstanceBlock) {
                fctRes_ = type_.getFunctionsInst().get(((InstanceBlock)_mem).getInstanceNb());
            } else if (_mem instanceof StaticBlock) {
                fctRes_ = type_.getFunctionsStat().get(((StaticBlock)_mem).getStaticNb());
            } else if (_mem instanceof ConstructorBlock) {
                fctRes_ = type_.getFunctionsCtor().get(((ConstructorBlock)_mem).getCtorNumber());
            } else {
                fctRes_ = type_.getFunctions().get(((NamedCalledFunctionBlock)_mem).getNameOverrideNumber());
            }
        }
        return fctRes_;
    }

    private CustList<BlockCoverageResult> getTypeRes(AbsBk _mem) {
        return types.get(((RootBlock)_mem.getParent()).getNumberAll()).getFields();
    }

    private BlockCoverageResult getFieldRes(AbsBk _mem) {
        return types.get(((RootBlock)_mem.getParent()).getNumberAll()).getFields().get(((InfoBlock)_mem).getFieldNumber());
    }

    public CustList<FunctionCoverageResult> getOperators() {
        return operators;
    }

    public CustList<TypeCoverageResult> getTypes() {
        return types;
    }

    public void putBlockOperation(int _indexAnnotGroup, int _indexAnnot, Forwards _fwd, AbsBk _block, OperationNode _op, ExecOperationNode _exec) {
        if (!isCovering()) {
            return;
        }
        BlockCoverageResult blr_ =  getResultBlock(_block, _indexAnnotGroup,_indexAnnot);
        putBlockOperation(_fwd, blr_, _op, _exec);
    }

    private static void putBlockOperation(Forwards _fwd, BlockCoverageResult _blr, OperationNode _op, ExecOperationNode _exec) {
        IdMap<ExecOperationNode, OperationNode> mapping_ = _blr.getMapping();
        CustList<AbstractCoverageResult> instr_ = _blr.getCovers();
        _op.setIndexInExp(instr_.size());
        mapping_.addEntry(_exec, _op);
        if (isFirstSafeDot(_op)) {
            instr_.add(new NullCoverageResult());
            return;
        }
        if (_op.getParent() instanceof NullSafeOperation) {
            nullSafe(_fwd,_op, instr_);
            return;
        }
        if (_op.getParent() instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation c_ = (CompoundAffectationOperation) _op.getParent();
            if (isLogicEq(c_.getOper(), AbsBk.NULL_EQ, AbsBk.NULL_EQ_SHORT)) {
                nullSafeCore(_fwd,_op, instr_);
                return;
            }
        }
        standardCoverage(_fwd,_op, instr_);
    }

    private static void nullSafe(Forwards _fwd, OperationNode _op, CustList<AbstractCoverageResult> _instr) {
        if (_op.getArgument() == null) {
            nullSafeCore(_fwd,_op, _instr);
        } else {
            _instr.add(new StandardCoverageResult());
        }
    }

    private static void nullSafeCore(Forwards _fwd, OperationNode _op, CustList<AbstractCoverageResult> _instr) {
        String prim_ = _fwd.getAliasPrimBoolean();
        String boolType_ = _fwd.getAliasBoolean();
        if (_op.getResultClass().isBoolType(boolType_, prim_)) {
            _instr.add(new NullBooleanCoverageResult());
        } else {
            _instr.add(new NullCoverageResult());
        }
    }

    private static boolean isFirstSafeDot(OperationNode _op) {
        return _op.getParent() instanceof SafeDotOperation && _op.getParent().getFirstChild() == _op;
    }

    private static void standardCoverage(Forwards _fwd, OperationNode _op, CustList<AbstractCoverageResult> _instr) {
        String prim_ = _fwd.getAliasPrimBoolean();
        if ((_op.getResultClass().matchClass(prim_) || !_op.getResultClass().getImplicitsTest().isEmpty())&& _op.getArgument() == null) {
            _instr.add(new BooleanCoverageResult());
        } else {
            _instr.add(new StandardCoverageResult());
        }
    }

    public void putToStringOwner(String _owner) {
        if (!isCovering()) {
            return;
        }
        toStringOwners.add(_owner);
    }

    public void putRandCodeOwner(String _owner) {
        if (!isCovering()) {
            return;
        }
        randCodeOwners.add(_owner);
    }

    public void passLoop(ExecBlock _loop, Argument _value, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_stackCall);
        AbstractCoverageResult covTwo_ = fctRes_.getCoverLoops().getVal(_loop);
        covTwo_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }
    public void passConditions(ExecBlock _condition, Argument _value, ExecOperationNode _exec, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_stackCall);
        AbstractCoverageResult covTwo_ = fctRes_.getCoversConditions().getVal(_condition);
        covCond(_value, _exec, _stackCall, covTwo_);
    }

    private static void covCond(Argument _value, ExecOperationNode _exec, StackCall _stackCall, AbstractCoverageResult _result) {
        _result.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        if (_exec.getArgument() != null) {
            _result.fullCover();
        } else {
            _result.cover(_value);
        }
    }

    public void passSwitch(ExecBlock _parent, ExecResultCase _child, Argument _value, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_stackCall);
        SwitchCoverageResult sw_ = fctRes_.getCoverSwitchs().getVal(_parent);
        procCase(_child, _value, _stackCall, sw_);
    }

    public void passSwitchMethod(ExecResultCase _child, Argument _value, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_stackCall);
        SwitchCoverageResult sw_ = fctRes_.getCoverSwitchsMethod();
        procCase(_child,_value,_stackCall,sw_);
    }

    private static void procCase(ExecResultCase _child, Argument _value, StackCall _stackCall, SwitchCoverageResult _sw) {
        AbstractCoverageResult covTwo_ = result(_child, _sw);
        covTwo_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }

    private static AbstractCoverageResult result(ExecResultCase _child, SwitchCoverageResult _sw) {
        if (_child == null) {
            return _sw.getResultNoDef();
        }
        return _sw.getChildren().getVal(_child.getBlock()).get(_child.getIndex());
    }

    public void passCatches(ExecBlock _block,StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_stackCall);
        fctRes_.getCatches().set(_block,BoolVal.TRUE);
    }

    public void passBlockOperation(ExecOperationNode _exec, boolean _full, ArgumentsPair _pair, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _stackCall.getLastPage();
        int indexAnnotGroup_ = -1;
        int indexAnnot_ = -1;
        if (lastPage_ instanceof ReflectAnnotationPageEl) {
            ReflectAnnotationPageEl annotRet_ = (ReflectAnnotationPageEl)lastPage_;
            int indexAnnotation_ = annotRet_.getIndexAnnotation();
            if (annotRet_.isOnParameters()) {
                indexAnnotGroup_ = annotRet_.getIndexAnnotationParam();
                indexAnnot_ = annotRet_.getAnnotationsParamsIndexes().get(indexAnnotGroup_).get(indexAnnotation_);
            } else {
                indexAnnot_ = annotRet_.getAnnotationsIndexes().get(indexAnnotation_);
            }
        }
        AbsBk matchBl_ = matchBl(lastPage_);
        BlockCoverageResult blRes_ = getResultBlock(matchBl_, indexAnnotGroup_, indexAnnot_);
        OperationNode ana_ = blRes_.getMapping().getVal(_exec);
        CustList<AbstractCoverageResult> instr_ = blRes_.getCovers();
        if (ana_ == null) {
            return;
        }
        AbstractCoverageResult result_ = instr_.get(ana_.getIndexInExp());
        result_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        if (_full) {
            result_.fullCover();
        } else {
            Struct valueStruct_ = getValueStruct(_exec,ana_, _pair);
            result_.cover(new Argument(valueStruct_));
        }
    }

    private AbsBk matchBl(AbstractPageEl _lastPage) {
        ExecRootBlock type_ = matchType(_lastPage);
        RootBlock typeAna_ = mappingTypes.getVal(type_);
        return matchBl(typeAna_, _lastPage);
    }

    private static ExecRootBlock matchType(AbstractPageEl _lastPage) {
        ExecRootBlock type_;
        if (_lastPage instanceof ReflectAnnotationPageEl) {
            ReflectAnnotationPageEl annotRet_ = (ReflectAnnotationPageEl) _lastPage;
            AnnotatedStruct annotated_ = annotRet_.getAnnotated();
            type_ = annotated_.getFormatted().getRootBlock();
        } else if (_lastPage instanceof ReflectGetDefaultValuePageEl) {
            ReflectGetDefaultValuePageEl annotRet_ = (ReflectGetDefaultValuePageEl) _lastPage;
            type_ = annotRet_.getMetaInfo().getPairType();
        } else {
            type_ = _lastPage.getBlockRootType();
        }
        return type_;
    }

    private AbsBk matchBl(RootBlock _rootBlock, AbstractPageEl _lastPage) {
        AbsBk matchBl_;
        if (_lastPage instanceof ReflectAnnotationPageEl) {
            ReflectAnnotationPageEl annotRet_ = (ReflectAnnotationPageEl)_lastPage;
            AnnotatedStruct annotated_ = annotRet_.getAnnotated();
            if (annotated_ instanceof FieldMetaInfo) {
                ExecInfoBlock annotableBlock_ = ((FieldMetaInfo)annotated_).getAnnotableBlock();
                TypeCoverageResult val_ = types.get(_rootBlock.getNumberAll());
                matchBl_ = val_.getMappingFields().getVal((ExecBlock) annotableBlock_);
            } else if (annotated_ instanceof AnnotatedParamStruct){
                ExecMemberCallingsBlock annotableBlock_ = ((AnnotatedParamStruct)annotated_).getCallee();
                if (annotableBlock_ instanceof ExecAnnotationMethodBlock){
                    matchBl_ = types.get(_rootBlock.getNumberAll()).getMappingFields().getVal(annotableBlock_);
                } else {
                    matchBl_ = getFctBlock(annotableBlock_, _rootBlock);
                }
            } else {
                matchBl_ = _rootBlock;
            }
        } else if (_lastPage instanceof ReflectGetDefaultValuePageEl) {
            ReflectGetDefaultValuePageEl annotRet_ = (ReflectGetDefaultValuePageEl)_lastPage;
            ExecAnnotationMethodBlock annotMeth_ = annotRet_.getAnnotMethod();
            matchBl_ = types.get(_rootBlock.getNumberAll()).getMappingFields().getVal(annotMeth_);
        } else {
            ExecBlock en_ = _lastPage.getCoveredBlock();
            if (en_ instanceof ExecInfoBlock || en_ instanceof ExecAnnotationMethodBlock) {
                matchBl_ = types.get(_rootBlock.getNumberAll()).getMappingFields().getVal(en_);
            } else {
                FunctionCoverageResult fctRes_ = getFctRes(_lastPage);
                matchBl_ = fctRes_.getMappingBlocks().getVal(en_);
            }
        }
        return matchBl_;
    }
    private static Struct getValueStruct(ExecOperationNode _oper, OperationNode _ana, ArgumentsPair _v) {
        Argument res_ = Argument.getNullableValue(_v.getArgument());
        Struct v_ = res_.getStruct();
        if (_oper.getNextSibling() == null || _ana.getResultClass().getImplicitsTest().isEmpty()) {
            return v_;
        }
        ExecMethodOperation par_ = _oper.getParent();
        if (par_ instanceof ExecQuickOperation){
            return BooleanStruct.of(((ExecQuickOperation)par_).match(BooleanStruct.of(_v.isArgumentTest())));
        }
        if (par_ instanceof ExecCompoundAffectationOperation){
            ExecCompoundAffectationOperation p_ = (ExecCompoundAffectationOperation) par_;
            return compound(v_,p_,_v);
        }
        return v_;
    }

    private static Struct compound(Struct _before,ExecCompoundAffectationOperation _par, ArgumentsPair _v) {
        Struct v_ = _before;
        if (isLogicEq(_par.getOper(), AbsBk.AND_LOG_EQ, AbsBk.AND_LOG_EQ_SHORT)) {
            v_ = BooleanStruct.of(!_v.isArgumentTest());
        }
        if (isLogicEq(_par.getOper(), AbsBk.OR_LOG_EQ, AbsBk.OR_LOG_EQ_SHORT)) {
            v_ = BooleanStruct.of(_v.isArgumentTest());
        }
        return v_;
    }
    private static boolean isLogicEq(String _oper, String _opEq, String _opShEq) {
        return StringUtil.quickEq(_oper, _opEq) || StringUtil.quickEq(_oper, _opShEq);
    }

    public void passCalls(AbstractPageEl _page) {
        if (!isCovering()) {
            return;
        }
        getFctRes(_page).setCalled(true);
    }
    private FunctionCoverageResult getFctRes(StackCall _stackCall) {
        return getFctRes(_stackCall.getLastPage());
    }
    private FunctionCoverageResult getFctRes(AbstractPageEl _page) {
        return getFctRes(getFctBlock((ExecMemberCallingsBlock) _page.getBlockRoot(), mappingTypes.getVal(_page.getBlockRootType())));
    }
    private MemberCallingsBlock getFctBlock(ExecMemberCallingsBlock _block, RootBlock _type) {
        MemberCallingsBlock valLambda_ = mappingLambdas.getVal(_block);
        if (valLambda_ != null) {
            return valLambda_;
        }
        MemberCallingsBlock valSwitchMethod_ = mappingSwitchMethods.getVal(_block);
        if (valSwitchMethod_ != null) {
            return valSwitchMethod_;
        }
        if (_type == null) {
            return mappingOperators.getVal(_block);
        }
        TypeCoverageResult value_ = types.get(_type.getNumberAll());
        return value_.getMappingBlocks().getVal(_block);
    }
    public CustList<FileBlock> getFiles() {
        return files;
    }

    public AbstractCoverageResult getCovers(AbsBk _block, OperationNode _oper, int _indexAnnotGroup, int _indexAnnot) {
        return getResultBlock(_block, _indexAnnotGroup, _indexAnnot).getCovers().get(_oper.getIndexInExp());
    }
    private BlockCoverageResult getResultBlock(AbsBk _block, int _indexAnnotGroup, int _indexAnnot) {
        if (_indexAnnot >= 0) {
            if (_block instanceof InfoBlock) {
                BlockCoverageResult fieldRes_ = getFieldRes(_block);
                return fieldRes_.getAnnotations().get(_indexAnnot);
            }
            if (AbsBk.isAnnotBlock(_block)) {
                NamedCalledFunctionBlock mem_ = (NamedCalledFunctionBlock) _block;
                BlockCoverageResult fctRes_ = types.get(((RootBlock)mem_.getParent()).getNumberAll()).getAnnotationsFields().get(mem_.getNameNumber());
                return fctRes_.getAnnotations().get(_indexAnnot);
            }
            if (_block instanceof MemberCallingsBlock) {
                MemberCallingsBlock mem_ = (MemberCallingsBlock) _block;
                FunctionCoverageResult fctRes_ = getFctRes(mem_);
                if (_indexAnnotGroup < 0) {
                    return fctRes_.getAnnotations().get(_indexAnnot);
                }
                return fctRes_.getAnnotationsParams().get(_indexAnnotGroup).get(_indexAnnot);
            }
            TypeCoverageResult fctRes_ = types.get(((RootBlock)_block).getNumberAll());
            return fctRes_.getAnnotations().get(_indexAnnot);
        }
        if (AbsBk.isAnnotBlock(_block)) {
            return types.get(((RootBlock)_block.getParent()).getNumberAll()).getAnnotationsFields().get(((NamedCalledFunctionBlock)_block).getNameNumber());
        }
        if (_block instanceof InfoBlock) {
            return getFieldRes(_block);
        }
        FunctionCoverageResult fctRes_ = getFctResBl(_block);
        return fctRes_.getBlocks().get(_block.getBlockNb());
    }

    public AbstractCoverageResult getCoversConditions(AbsBk _bk ,WithConditionPart _exec) {
        FunctionCoverageResult fctRes_ = getFctResBl(_bk);
        return fctRes_.getCoversConditions().getValue(_exec.getConditionNb());
    }

    public CustList<AbstractCoverageResult> getCoverSwitchs(SwitchPartBlock _child) {
        SwitchBlock switchParent_ = _child.getSwitchParent();
        SwitchCoverageResult sw_;
        if (switchParent_ != null) {
            sw_ = coverSwitchs(switchParent_);
        } else {
            sw_ = coverSwitchsMethod(_child.getSwitchMethod());
        }
        return sw_.getChildren().getValue(_child.getConditionNb());
    }

    public SwitchCoverageResult coverSwitchsMethod(MemberCallingsBlock _sw) {
        FunctionCoverageResult fctRes_ = getFctResBl(_sw);
        return fctRes_.getCoverSwitchsMethod();
    }

    public SwitchCoverageResult coverSwitchs(SwitchBlock _sw) {
        FunctionCoverageResult fctRes_ = getFctResBl(_sw);
        return fctRes_.getCoverSwitchs().getValue(_sw.getConditionNb());
    }

    public boolean getCatches(AbstractCatchEval _catch) {
        FunctionCoverageResult fctRes_ = getFctResBl(_catch);
        return fctRes_.getCatches().getValue(_catch.getConditionNb()) == BoolVal.TRUE;
    }

    public AbstractCoverageResult getCoverLoops(AbstractForLoop _bl) {
        FunctionCoverageResult fctRes_ = getFctResBl(_bl);
        return fctRes_.getCoverLoops().getValue(_bl.getConditionNb());
    }

    public CustList<RootBlock> getRefFoundTypes() {
        return refFoundTypes;
    }

    public CustList<OperatorBlock> getRefOperators() {
        return refOperators;
    }

    public StringList getToStringOwners() {
        return toStringOwners;
    }

    public StringList getRandCodeOwners() {
        return randCodeOwners;
    }

    public KeyWords getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(KeyWords _keyWords) {
        if (!isCovering()) {
            return;
        }
        this.keyWords = _keyWords;
    }

    public boolean isCovering() {
        return covering;
    }

    public boolean isImplicit() {
        return implicit;
    }

    public void setImplicit(boolean _implicit) {
        this.implicit = _implicit;
    }

    public boolean isDisplayEncode() {
        return displayEncode;
    }

    public void setDisplayEncode(boolean _displayEncode) {
        this.displayEncode = _displayEncode;
    }
}
