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
import code.expressionlanguage.fwd.PutCoveragePhase;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
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
    public void putCalls(RootBlock _type, MemberCallingsBlock _call) {
        if (!isCovering()) {
            return;
        }
        if (_call instanceof InstanceBlock) {
            types.get(_type.getNumberAll()).getFunctionsInst().add(new FunctionCoverageResult());
            return;
        }
        if (_call instanceof StaticBlock) {
            types.get(_type.getNumberAll()).getFunctionsStat().add(new FunctionCoverageResult());
            return;
        }
        if (_call instanceof ConstructorBlock) {
            types.get(_type.getNumberAll()).getFunctionsCtor().add(new FunctionCoverageResult());
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
    public void putBlockOperationsLoops(MemberCallingsBlock _mem, AbstractForLoop _block, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        _block.setConditionNb(fctRes_.getCoverLoops().size());
        fctRes_.getCoverLoops().addEntry(_exec,new BooleanCoverageResult());
    }

    public void putBlockOperationsConditions(MemberCallingsBlock _mem, ConditionBlock _block, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        _block.setConditionNb(fctRes_.getCoversConditions().size());
        fctRes_.getCoversConditions().addEntry(_exec,new BooleanCoverageResult());
    }

    public void putBlockOperationsConditionsForMutable(MemberCallingsBlock _mem, ForMutableIterativeLoop _block, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        _block.setConditionNb(fctRes_.getCoversConditionsForMutable().size());
        fctRes_.getCoversConditionsForMutable().addEntry(_exec,new BooleanCoverageResult());
    }

    public void putBlockOperationsSwitchs(MemberCallingsBlock _mem, SwitchBlock _block, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        _block.setConditionNb(fctRes_.getCoverSwitchs().size());
        fctRes_.getCoverSwitchs().addEntry(_exec,new SwitchCoverageResult());
    }

    public void putBlockOperationsSwitchsPart(MemberCallingsBlock _mem, SwitchBlock _block, SwitchPartBlock _child, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        SwitchCoverageResult swRes_ = fctRes_.getCoverSwitchs().getValue(_block.getConditionNb());
        _child.setConditionNb(swRes_.getChildren().size());
        if (_child instanceof DefaultCondition) {
            swRes_.setDefCase(true);
        }
        swRes_.getChildren().addEntry(_exec, new StandardCoverageResult());
    }

    public void putBlockOperationsSwitchsMethodPart(MemberCallingsBlock _mem, SwitchPartBlock _child, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        SwitchCoverageResult swRes_ = fctRes_.getCoverSwitchsMethod();
        _child.setConditionNb(swRes_.getChildren().size());
        if (_child instanceof DefaultCondition) {
            swRes_.setDefCase(true);
        }
        swRes_.getChildren().addEntry(_exec, new StandardCoverageResult());
    }
    public void putCatches(MemberCallingsBlock _mem, AbstractCatchEval _block, ExecBlock _exec) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        _block.setConditionNb(fctRes_.getCatches().size());
        fctRes_.getCatches().addEntry(_exec,BoolVal.FALSE);
    }
    public void putBlockOperations(MemberCallingsBlock _mem, AbsBk _block) {
        if (!isCovering()) {
            return;
        }
        _block.setOuterFct(_mem);
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        _block.setBlockNb(fctRes_.getBlocks().size());
        fctRes_.getBlocks().add(new BlockCoverageResult());
    }

    public void putBlockOperations(MemberCallingsBlock _mem,ExecBlock _exec, AbsBk _block) {
        if (!isCovering()) {
            return;
        }
        if (_block instanceof OperatorBlock) {
            mappingOperators.addEntry(_exec,(MemberCallingsBlock)_block);
            return;
        }
        if (AbsBk.isAnonBlock(_block)) {
            mappingLambdas.addEntry(_exec,(MemberCallingsBlock)_block);
            return;
        }
        if (_block instanceof SwitchMethodBlock) {
            mappingSwitchMethods.addEntry(_exec,(MemberCallingsBlock)_block);
            return;
        }
        if (_block instanceof MemberCallingsBlock) {
            types.get(((RootBlock)_mem.getParent()).getNumberAll()).getMappingBlocks().addEntry(_exec, (MemberCallingsBlock)_block);
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        fctRes_.getMappingBlocks().addEntry(_exec, _block);
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

    public void putBlockOperation(int _indexAnnotGroup, int _indexAnnot, PutCoveragePhase _phase, Forwards _fwd, AbsBk _block, OperationNode _op, ExecOperationNode _exec) {
        if (!isCovering()) {
            return;
        }
        BlockCoverageResult blr_ =  getResultBlock(_block,_phase == PutCoveragePhase.ANNOTATION,_indexAnnotGroup,_indexAnnot);
        putBlockOperation(_fwd, blr_, _op, _exec);
    }

    private static void putBlockOperation(Forwards _fwd, BlockCoverageResult _blr, OperationNode _op, ExecOperationNode _exec) {
        IdMap<ExecOperationNode, OperationNode> mapping_ = _blr.getMapping();
        CustList<AbstractCoverageResult> instr_ = _blr.getCovers();
        _op.setIndexInExp(instr_.size());
        mapping_.addEntry(_exec, _op);
        if (_op.getParent() instanceof SafeDotOperation) {
            if (_op.getParent().getFirstChild() == _op) {
                instr_.add(new NullCoverageResult());
                return;
            }
        }
        String prim_ = _fwd.getAliasPrimBoolean();
        String boolType_ = _fwd.getAliasBoolean();
        if (_op.getParent() instanceof NullSafeOperation) {
            if (_op.getArgument() == null) {
                if (_op.getResultClass().isBoolType(boolType_,prim_)) {
                    instr_.add(new NullBooleanCoverageResult());
                } else {
                    instr_.add(new NullCoverageResult());
                }
            } else {
                instr_.add(new StandardCoverageResult());
            }
            return;
        }
        if (_op.getParent() instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation c_ = (CompoundAffectationOperation) _op.getParent();
            if (StringUtil.quickEq(c_.getOper(), AbsBk.NULL_EQ) || StringUtil.quickEq(c_.getOper(), AbsBk.NULL_EQ_SHORT)) {
                if (_op.getResultClass().isBoolType(boolType_,prim_)) {
                    instr_.add(new NullBooleanCoverageResult());
                } else {
                    instr_.add(new NullCoverageResult());
                }
                return;
            }
        }
        if ((_op.getResultClass().matchClass(prim_) || !_op.getResultClass().getImplicitsTest().isEmpty())&& _op.getArgument() == null) {
            instr_.add(new BooleanCoverageResult());
        } else {
            instr_.add(new StandardCoverageResult());
        }
    }

    public void putToStringOwner(String _owner) {
        if (!isCovering()) {
            return;
        }
        toStringOwners.add(_owner);
    }

    public void passLoop(ExecBlock _loop, Argument _value, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _stackCall.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
        BooleanCoverageResult covTwo_ = fctRes_.getCoverLoops().getVal(_loop);
        covTwo_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }
    public void passConditions(ExecBlock _condition, Argument _value, ExecOperationNode _exec, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _stackCall.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
        BooleanCoverageResult covTwo_ = fctRes_.getCoversConditions().getVal(_condition);
        covTwo_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        if (_exec.getArgument() != null) {
            covTwo_.fullCover();
        } else {
            covTwo_.cover(_value);
        }
    }
    public void passConditionsForMutable(ExecBlock _condition, Argument _value, ExecOperationNode _exec, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _stackCall.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
        BooleanCoverageResult covTwo_ = fctRes_.getCoversConditionsForMutable().getVal(_condition);
        covTwo_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        if (_exec.getArgument() != null) {
            covTwo_.fullCover();
        } else {
            covTwo_.cover(_value);
        }
    }
    public void passSwitch(ExecBlock _parent, ExecBlock _child, Argument _value, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _stackCall.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
        StandardCoverageResult covTwo_ = fctRes_.getCoverSwitchs().getVal(_parent).getChildren().getVal(_child);
        covTwo_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }
    public void passSwitch(ExecBlock _parent, Argument _value, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _stackCall.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
        StandardCoverageResult covTwo_ = fctRes_.getCoverSwitchs().getVal(_parent).getResultNoDef();
        covTwo_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }
    public void passSwitchMethod(ExecBlock _child, Argument _value, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _stackCall.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
        StandardCoverageResult covTwo_ = fctRes_.getCoverSwitchsMethod().getChildren().getVal(_child);
        covTwo_.setInit(_stackCall.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }

    public void passCatches(AbstractPageEl _page,ExecBlock _block) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_page);
        fctRes_.getCatches().set(_block,BoolVal.TRUE);
    }

    public void passBlockOperation(ExecOperationNode _exec, boolean _full, ArgumentsPair _pair, StackCall _stackCall) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _stackCall.getLastPage();
        ExecBlock en_ = lastPage_.getBlock();
        int indexAnnotGroup_ = -1;
        int indexAnnot_ = -1;
        ExecRootBlock type_;
        if (lastPage_ instanceof ReflectAnnotationPageEl) {
            ReflectAnnotationPageEl annotRet_ = (ReflectAnnotationPageEl)lastPage_;
            int indexAnnotation_ = annotRet_.getIndexAnnotation();
            if (annotRet_.isOnParameters()) {
                indexAnnotGroup_ = annotRet_.getIndexAnnotationParam();
                indexAnnot_ = annotRet_.getAnnotationsParamsIndexes().get(indexAnnotGroup_).get(indexAnnotation_);
            } else {
                indexAnnot_ = annotRet_.getAnnotationsIndexes().get(indexAnnotation_);
            }
            AnnotatedStruct annotated_ = annotRet_.getAnnotated();
            if (annotated_ instanceof FieldMetaInfo) {
                type_ = ((FieldMetaInfo)annotated_).getDeclaring();
            } else if (annotated_ instanceof AnnotatedParamStruct){
                type_ = ((AnnotatedParamStruct) annotated_).getPair().getType();
            } else {
                type_ = ((ClassMetaInfo) annotated_).getRootBlock();
            }
        } else if (lastPage_ instanceof ReflectGetDefaultValuePageEl) {
            ReflectGetDefaultValuePageEl annotRet_ = (ReflectGetDefaultValuePageEl)lastPage_;
            ExecTypeFunction pair_ = annotRet_.getMetaInfo().getPair();
            type_ = pair_.getType();
        } else {
            type_ = lastPage_.getBlockRootType();
        }
        RootBlock typeAna_ = mappingTypes.getVal(type_);
        AbsBk matchBl_;
        if (lastPage_ instanceof ReflectAnnotationPageEl) {
            ReflectAnnotationPageEl annotRet_ = (ReflectAnnotationPageEl)lastPage_;
            AnnotatedStruct annotated_ = annotRet_.getAnnotated();
            ExecAnnotableBlock annotableBlock_ = annotated_.getAnnotableBlock();
            if (annotated_ instanceof FieldMetaInfo) {
                TypeCoverageResult val_ = types.get(typeAna_.getNumberAll());
                matchBl_ = val_.getMappingFields().getVal((ExecBlock) annotableBlock_);
            } else if (annotated_ instanceof AnnotatedParamStruct){
                if (annotableBlock_ instanceof ExecAnnotationMethodBlock){
                    matchBl_ = types.get(typeAna_.getNumberAll()).getMappingFields().getVal((ExecBlock) annotableBlock_);
                } else {
                    matchBl_ = getFctBlock((ExecMemberCallingsBlock) ((AnnotatedParamStruct) annotated_).getAnnotableBlockParam(), typeAna_);
                }
            } else {
                matchBl_ = typeAna_;
            }
        } else if (lastPage_ instanceof ReflectGetDefaultValuePageEl) {
            ReflectGetDefaultValuePageEl annotRet_ = (ReflectGetDefaultValuePageEl)lastPage_;
            ExecTypeFunction pair_ = annotRet_.getMetaInfo().getPair();
            ExecAnnotationMethodBlock annotMeth_ = (ExecAnnotationMethodBlock) pair_.getFct();
            matchBl_ = types.get(typeAna_.getNumberAll()).getMappingFields().getVal(annotMeth_);
        } else {
            if (en_ instanceof ExecInfoBlock || en_ instanceof ExecAnnotationMethodBlock) {
                matchBl_ = types.get(typeAna_.getNumberAll()).getMappingFields().getVal(en_);
            } else {
                FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
                matchBl_ = fctRes_.getMappingBlocks().getVal(en_);
            }
        }
        BlockCoverageResult blRes_ = getResultBlock(matchBl_, lastPage_ instanceof ReflectAnnotationPageEl, indexAnnotGroup_, indexAnnot_);
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

    private static Struct getValueStruct(ExecOperationNode _oper, OperationNode _ana, ArgumentsPair _v) {
        Argument res_ = Argument.getNullableValue(_v.getArgument());
        Struct v_ = res_.getStruct();
        if (_oper.getNextSibling() != null&&!_ana.getResultClass().getImplicitsTest().isEmpty()){
            ExecMethodOperation par_ = _oper.getParent();
            if (par_ instanceof ExecAndOperation){
                v_ = BooleanStruct.of(!_v.isArgumentTest());
            }
            if (par_ instanceof ExecOrOperation){
                v_ = BooleanStruct.of(_v.isArgumentTest());
            }
            if (par_ instanceof ExecCompoundAffectationOperation){
                ExecCompoundAffectationOperation p_ = (ExecCompoundAffectationOperation) par_;
                if (StringUtil.quickEq(p_.getOper(),"&&=")) {
                    v_ = BooleanStruct.of(!_v.isArgumentTest());
                }
                if (StringUtil.quickEq(p_.getOper(),"&&&=")) {
                    v_ = BooleanStruct.of(!_v.isArgumentTest());
                }
                if (StringUtil.quickEq(p_.getOper(),"||=")) {
                    v_ = BooleanStruct.of(_v.isArgumentTest());
                }
                if (StringUtil.quickEq(p_.getOper(),"|||=")) {
                    v_ = BooleanStruct.of(_v.isArgumentTest());
                }
            }
        }
        return v_;
    }

    public void passCalls(AbstractPageEl _page) {
        if (!isCovering()) {
            return;
        }
        getFctRes(_page).setCalled(true);
    }
    private FunctionCoverageResult getFctRes(AbstractPageEl _page) {
        return getFctRes(_page.getBlockRootType(), (ExecMemberCallingsBlock) _page.getBlockRoot());
    }
    private FunctionCoverageResult getFctRes(ExecRootBlock _type, ExecMemberCallingsBlock _block) {
        return getFctRes(getFctBlock(_block, mappingTypes.getVal(_type)));
    }
    private MemberCallingsBlock getFctBlock(ExecMemberCallingsBlock _block, RootBlock _type) {
        MemberCallingsBlock valLambda_ = mappingLambdas.getVal(_block);
        if (AbsBk.isAnonBlock(valLambda_)) {
            return valLambda_;
        }
        MemberCallingsBlock valSwitchMethod_ = mappingSwitchMethods.getVal(_block);
        if (valSwitchMethod_ instanceof SwitchMethodBlock) {
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

    public AbstractCoverageResult getCovers(AbsBk _block, OperationNode _oper, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        return getResultBlock(_block, _annot, _indexAnnotGroup, _indexAnnot).getCovers().get(_oper.getIndexInExp());
    }
    private BlockCoverageResult getResultBlock(AbsBk _block, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        if (_annot) {
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
        MemberCallingsBlock outerFuntion_ = _block.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getBlocks().get(_block.getBlockNb());
    }

    public AbstractCoverageResult getCoversConditions(ConditionBlock _exec) {
        MemberCallingsBlock outerFuntion_ = _exec.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoversConditions().getValue(_exec.getConditionNb());
    }

    public AbstractCoverageResult getCoversConditionsForMutable(ForMutableIterativeLoop _exec) {
        MemberCallingsBlock outerFuntion_ = _exec.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoversConditionsForMutable().getValue(_exec.getConditionNb());
    }
    public StandardCoverageResult getCoverSwitchs(SwitchBlock _sw, SwitchPartBlock _child) {
        MemberCallingsBlock outerFuntion_ = _sw.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoverSwitchs().getValue(_sw.getConditionNb()).getChildren().getValue(_child.getConditionNb());
    }
    public  IdMap<ExecBlock, StandardCoverageResult> getCoverSwitchs(SwitchBlock _sw) {
        MemberCallingsBlock outerFuntion_ = _sw.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoverSwitchs().getValue(_sw.getConditionNb()).getChildren();
    }
    public StandardCoverageResult getCoverSwitchsMethod(MemberCallingsBlock _sw, SwitchPartBlock _child) {
        FunctionCoverageResult fctRes_ = getFctRes(_sw);
        return fctRes_.getCoverSwitchsMethod().getChildren().getValue(_child.getConditionNb());
    }
    public  IdMap<ExecBlock, StandardCoverageResult> getCoverSwitchsMethod(MemberCallingsBlock _sw) {
        FunctionCoverageResult fctRes_ = getFctRes(_sw);
        return fctRes_.getCoverSwitchsMethod().getChildren();
    }

    public StandardCoverageResult getCoverNoDefSwitchs(SwitchBlock _sw) {
        MemberCallingsBlock outerFuntion_ = _sw.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoverSwitchs().getValue(_sw.getConditionNb()).noDefault();
    }

    public boolean getCatches(AbstractCatchEval _catch) {
        MemberCallingsBlock outerFuntion_ = _catch.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCatches().getValue(_catch.getConditionNb()) == BoolVal.TRUE;
    }

    public AbstractCoverageResult getCoverLoops(AbstractForLoop _bl) {
        MemberCallingsBlock outerFuntion_ = _bl.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
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
