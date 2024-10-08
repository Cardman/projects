package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.CompoundAffectationOperation;
import code.expressionlanguage.analyze.opers.NullSafeOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.SafeDotOperation;
import code.expressionlanguage.common.OptionsReport;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.ReflectAnnotationPageEl;
import code.expressionlanguage.exec.calls.ReflectGetDefaultValuePageEl;
import code.expressionlanguage.exec.opers.CompoundedOperator;
import code.expressionlanguage.exec.opers.ExecMethodOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.NumberUtil;
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
    private final OptionsReport optionsReport = new OptionsReport();

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
        getOperators().add(new FunctionCoverageResult());
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
            int count_ = NumberUtil.max(1,((CaseCondition) _child).getFilterContent().getStdValues().size()+ ((CaseCondition) _child).getFilterContent().getEnumValues().size());
            CustList<AbstractCoverageResult> list_ = new CustList<AbstractCoverageResult>();
            for (int i = 0; i < count_; i++) {
                list_.add(new StandardCoverageResult());
            }
            _swRes.getChildren().addEntry(_exec, list_);
        }
    }

    public void putCatches(CatchEval _block, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        int count_ = NumberUtil.max(1,_block.getFilterContent().getStdValues().size()+ _block.getFilterContent().getEnumValues().size());
        CustList<AbstractCoverageResult> list_ = new CustList<AbstractCoverageResult>();
        for (int i = 0; i < count_; i++) {
            list_.add(new StandardCoverageResult());
        }
        FunctionCoverageResult fctRes_ = getFctResBl(_block);
        _block.setConditionNb(fctRes_.getCatches().size());
        fctRes_.getCatches().addEntry(_exec,list_);
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
        fctRes_.get(_block.getElements().getFieldNumber()).getAnnotations().add(new BlockCoverageResult());
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
    public void putBlockOperationsAnnotMethodSupp(MemberCallingsBlock _block) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_block);
        fctRes_.getAnnotationsSupp().add(new BlockCoverageResult());
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
        return types.get(((RootBlock)_mem.getParent()).getNumberAll()).getFields().get(((InfoBlock)_mem).getElements().getFieldNumber());
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
            if (c_.isNullSafe()) {
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
        ExecTypeFunction pair_ = FetchMemberUtil.fetchOvTypeFunction(_op.getResultClass().getMemberId(), _fwd);
        if ((_op.getResultClass().matchClass(prim_) || !_op.getResultClass().getImplicitsTest().isEmpty() || pair_ != null && StringUtil.quickEq(pair_.getFct().getImportedReturnType(),prim_))&& _op.getArgument() == null) {
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

    public void passLoop(ExecBlock _loop, Struct _value, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_stackCall);
        AbstractCoverageResult covTwo_ = fctRes_.getCoverLoops().getVal(_loop);
        covTwo_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }
    public void passConditions(ExecBlock _condition, Struct _value, ExecOperationNode _exec, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_stackCall);
        AbstractCoverageResult covTwo_ = fctRes_.getCoversConditions().getVal(_condition);
        covCond(_value, _exec, _stackCall, covTwo_);
    }

    private static void covCond(Struct _value, ExecOperationNode _exec, StackCall _stackCall, AbstractCoverageResult _result) {
        _result.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        if (_exec.getArgument() != null) {
            _result.fullCover();
        } else {
            _result.cover(_value);
        }
    }

    public void passSwitch(ExecBlock _parent, ExecResultCase _child, Struct _value, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_stackCall);
        SwitchCoverageResult sw_ = fctRes_.getCoverSwitchs().getVal(_parent);
        procCase(_child, _value, _stackCall, sw_);
    }

    public void passSwitchMethod(ExecResultCase _child, Struct _value, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_stackCall);
        SwitchCoverageResult sw_ = fctRes_.getCoverSwitchsMethod();
        procCase(_child,_value,_stackCall,sw_);
    }

    private static void procCase(ExecResultCase _child, Struct _value, StackCall _stackCall, SwitchCoverageResult _sw) {
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

    public void passCatches(Struct _ex, ExecResultCase _child, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_stackCall);
        AbstractCoverageResult cov_ = fctRes_.getCatches().getVal(_child.getBlock()).get(_child.getIndex());
        cov_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        cov_.cover(_ex);
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
            if (annotRet_.isOnParameters() == ReflectingType.ANNOTATION_PARAM) {
                indexAnnotGroup_ = annotRet_.getIndexAnnotationParam();
                indexAnnot_ = annotRet_.getAnnotationsParamsIndexes().get(indexAnnotGroup_).get(indexAnnotation_);
            } else if (annotRet_.isOnParameters() == ReflectingType.ANNOT_SUPP) {
                indexAnnotGroup_ = annotRet_.getIndexAnnotationParam();
                indexAnnot_ = annotRet_.getAnnotationsIndexes().get(indexAnnotation_);
            } else {
                indexAnnot_ = annotRet_.getAnnotationsIndexes().get(indexAnnotation_);
            }
        }
        passBlockOperationOp(_exec, _full, _pair, _stackCall, lastPage_, indexAnnot_, indexAnnotGroup_);
    }

    private void passBlockOperationOp(ExecOperationNode _exec, boolean _full, ArgumentsPair _pair, StackCall _stackCall, AbstractPageEl _lastPage, int _indexAnnot, int _indexAnnotGroup) {
        AbsBk matchBl_ = matchBl(_lastPage);
        BlockCoverageResult blRes_ = getResultBlock(matchBl_, _indexAnnotGroup, _indexAnnot);
        passBlockOperationOp(_exec, _full, _pair, _stackCall, blRes_);
    }

    private void passBlockOperationOp(ExecOperationNode _exec, boolean _full, ArgumentsPair _pair, StackCall _stackCall, BlockCoverageResult _blRes) {
        OperationNode ana_ = _blRes.getMapping().getVal(_exec);
        CustList<AbstractCoverageResult> instr_ = _blRes.getCovers();
        if (ana_ == null) {
            return;
        }
        AbstractCoverageResult result_ = instr_.get(ana_.getIndexInExp());
        result_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        if (_full) {
            result_.fullCover();
        } else {
            Struct valueStruct_ = getValueStruct(_exec,ana_, _pair);
            if (result_ instanceof BooleanCoverageResult && !(valueStruct_ instanceof BooleanStruct)) {
                result_.cover(ArgumentListCall.getNull(_pair.getArgumentBeforeImpl()));
            } else {
                result_.cover(valueStruct_);
            }
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
        if (_lastPage instanceof ReflectAnnotationPageEl) {
            ReflectAnnotationPageEl annotRet_ = (ReflectAnnotationPageEl)_lastPage;
            AnnotatedStruct annotated_ = annotRet_.getAnnotated();
            if (annotated_ instanceof FieldMetaInfo) {
                ExecInfoBlock annotableBlock_ = ((FieldMetaInfo)annotated_).getAnnotableBlock();
                TypeCoverageResult val_ = types.get(_rootBlock.getNumberAll());
                return val_.getMappingFields().getVal((ExecBlock) annotableBlock_);
            }
            if (annotated_ instanceof AnnotatedParamStruct){
                ExecMemberCallingsBlock annotableBlock_ = ((AnnotatedParamStruct)annotated_).getCallee();
                if (annotableBlock_ instanceof ExecAnnotationMethodBlock){
                    return types.get(_rootBlock.getNumberAll()).getMappingFields().getVal(annotableBlock_);
                }
                return getFctBlock(annotableBlock_, _rootBlock);
            }
            return _rootBlock;
        }
        if (_lastPage instanceof ReflectGetDefaultValuePageEl) {
            ReflectGetDefaultValuePageEl annotRet_ = (ReflectGetDefaultValuePageEl)_lastPage;
            ExecAnnotationMethodBlock annotMeth_ = annotRet_.getAnnotMethod();
            return types.get(_rootBlock.getNumberAll()).getMappingFields().getVal(annotMeth_);
        }
        ExecBlock en_ = _lastPage.getCoveredBlock();
        if (en_ instanceof ExecInfoBlock || en_ instanceof ExecAnnotationMethodBlock) {
            return types.get(_rootBlock.getNumberAll()).getMappingFields().getVal(en_);
        }
        FunctionCoverageResult fctRes_ = getFctRes(_lastPage);
        return fctRes_.getMappingBlocks().getVal(en_);
    }
    private static Struct getValueStruct(ExecOperationNode _oper, OperationNode _ana, ArgumentsPair _v) {
        Struct o_ = ArgumentListCall.getNull(_v.getArgument());
        ExecMethodOperation par_ = _oper.getParent();
        if (par_ instanceof CompoundedOperator){
            CompoundedOperator p_ = (CompoundedOperator) par_;
            if (ExecOperationNode.andEq(p_)) {
                return value(_oper, _ana, o_, !_v.isArgumentTest());
            }
            if (ExecOperationNode.orEq(p_)) {
                return value(_oper, _ana, o_, _v.isArgumentTest());
            }
        }
        return o_;
    }

    private static Struct value(ExecOperationNode _oper, OperationNode _ana, Struct _original, boolean _v) {
        if (noTest(_oper, _ana)) {
            return _original;
        }
        return BooleanStruct.of(_v);
    }

    private static boolean noTest(ExecOperationNode _oper, OperationNode _ana) {
        return !ExpressionLanguage.isAncSettable(_oper) || _ana.getResultClass().getImplicitsTest().isEmpty();
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
                if (_indexAnnotGroup >= fctRes_.getAnnotationsParams().size()) {
                    return fctRes_.getAnnotationsSupp().get(_indexAnnot);
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

    public CustList<AbstractCoverageResult> getCatches(AbstractCatchEval _catch) {
        FunctionCoverageResult fctRes_ = getFctResBl(_catch);
        return fctRes_.getCatches().getValue(_catch.getConditionNb());
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

    public OptionsReport getOptionsReport() {
        return optionsReport;
    }
}
