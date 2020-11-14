package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.blocks.*;
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
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class Coverage {
    private CustList<FileBlock> files = new CustList<FileBlock>();
    private final CustList<RootBlock> refFoundTypes = new CustList<RootBlock>();
    private final CustList<OperatorBlock> refOperators = new CustList<OperatorBlock>();
    private final StringList toStringOwners = new StringList();
    private final IdMap<Block,TypeCoverageResult> types = new IdMap<Block, TypeCoverageResult>();
    private final IdMap<Block,FunctionCoverageResult> lambdas = new IdMap<Block,FunctionCoverageResult>();
    private final IdMap<Block,FunctionCoverageResult> operators = new IdMap<Block,FunctionCoverageResult>();
    private final IdMap<ExecBlock,Block> mappingOperators = new IdMap<ExecBlock,Block>();
    private final IdMap<ExecBlock,Block> mappingLambdas = new IdMap<ExecBlock,Block>();
    private final IdMap<ExecBlock,Block> mappingTypes = new IdMap<ExecBlock,Block>();
    private KeyWords keyWords;
    private final boolean covering;

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
        types.addEntry(_type,new TypeCoverageResult());
    }

    public void putOperator(OperatorBlock _type) {
        if (!isCovering()) {
            return;
        }
        refOperators.add(_type);
        operators.addEntry(_type,new FunctionCoverageResult());
    }
    public void putCalls(RootBlock _type, MemberCallingsBlock _block) {
        if (!isCovering()) {
            return;
        }
        types.getValue(_type.getNumberAll()).getFunctions().addEntry(_block, new FunctionCoverageResult());
    }
    public void putCallsAnon(MemberCallingsBlock _block) {
        if (!isCovering()) {
            return;
        }
        lambdas.addEntry(_block, new FunctionCoverageResult());
    }
    public void putBlockOperationsLoops(MemberCallingsBlock _mem,Block _block) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        fctRes_.getCoverLoops().addEntry(_block,new BooleanCoverageResult());
    }

    public void putBlockOperationsConditions(MemberCallingsBlock _mem,Block _block) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        fctRes_.getCoversConditions().addEntry(_block,new BooleanCoverageResult());
    }

    public void putBlockOperationsSwitchs(MemberCallingsBlock _mem,Block _block) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        fctRes_.getCoverSwitchs().addEntry(_block,new SwitchCoverageResult());
    }

    public void putBlockOperationsSwitchs(MemberCallingsBlock _mem,Block _block, Block _child) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        SwitchCoverageResult swRes_ = fctRes_.getCoverSwitchs().getVal(_block);
        swRes_.getChildren().addEntry(_child, new StandardCoverageResult());
    }
    public void putCatches(MemberCallingsBlock _mem,Block _block) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        fctRes_.getCatches().addEntry(_block,BoolVal.FALSE);
    }
    public void putBlockOperations(MemberCallingsBlock _mem,Block _block) {
        if (!isCovering()) {
            return;
        }
        _block.setOuterFct(_mem);
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        fctRes_.getBlocks().addEntry(_block, new BlockCoverageResult());
    }

    public void putBlockOperations(MemberCallingsBlock _mem,ExecBlock _exec, Block _block) {
        if (!isCovering()) {
            return;
        }
        if (_block instanceof OperatorBlock) {
            mappingOperators.addEntry(_exec,_block);
            return;
        }
        if (_block instanceof AnonymousFunctionBlock) {
            mappingLambdas.addEntry(_exec,_block);
            return;
        }
        if (_block instanceof MemberCallingsBlock) {
            types.getValue(((RootBlock)_mem.getParent()).getNumberAll()).getMappingBlocks().addEntry(_exec, _block);
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_mem);
        fctRes_.getMappingBlocks().addEntry(_exec, _block);
    }

    public void putBlockOperationsType(ExecBlock _exec, Block _block) {
        if (!isCovering()) {
            return;
        }
        mappingTypes.addEntry(_exec,_block);
    }
    public void putBlockOperationsField(Block _block) {
        if (!isCovering()) {
            return;
        }
        IdMap<Block, BlockCoverageResult> fctRes_ = getTypeRes(_block);
        fctRes_.addEntry(_block, new BlockCoverageResult());
    }

    public void putBlockOperationsField(ExecBlock _exec, Block _block) {
        if (!isCovering()) {
            return;
        }
        types.getValue(((RootBlock)_block.getParent()).getNumberAll()).getMappingFields().addEntry(_exec, _block);
    }
    public void putBlockOperationsAnnotMethodField(Block _block) {
        if (!isCovering()) {
            return;
        }
        types.getValue(((RootBlock)_block.getParent()).getNumberAll()).getAnnotationsFields().addEntry(_block, new BlockCoverageResult());
    }
    public void putBlockOperationsAnnotField(InfoBlock _block) {
        if (!isCovering()) {
            return;
        }
        IdMap<Block, BlockCoverageResult> fctRes_ = getTypeRes((Block)_block);
        fctRes_.getValue(_block.getFieldNumber()).getAnnotations().add(new BlockCoverageResult());
    }
    public void putBlockOperationsAnnotType(RootBlock _block) {
        if (!isCovering()) {
            return;
        }
        TypeCoverageResult fctRes_ = types.getValue(_block.getNumberAll());
        fctRes_.getAnnotations().add(new BlockCoverageResult());
    }
    public void putBlockOperationsAnnotMethod(MemberCallingsBlock _block) {
        if (!isCovering()) {
            return;
        }
        if (_block instanceof AnnotationMethodBlock) {
            AnnotationMethodBlock mem_ = (AnnotationMethodBlock) _block;
            BlockCoverageResult fctRes_ = types.getValue(((RootBlock)mem_.getParent()).getNumberAll()).getAnnotationsFields().getValue(mem_.getNameNumber());
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
            fctRes_ = operators.getValue(((OperatorBlock) _mem).getNameNumber());
        } else if (_mem instanceof AnonymousFunctionBlock){
            fctRes_ = lambdas.getValue(((AnonymousFunctionBlock)_mem).getNumberLambda());
        } else {
            fctRes_ = types.getValue(((RootBlock)_mem.getParent()).getNumberAll()).getFunctions().getValue(_mem.getNumberBodyFct());
        }
        return fctRes_;
    }

    private IdMap<Block, BlockCoverageResult> getTypeRes(Block _mem) {
        return types.getValue(((RootBlock)_mem.getParent()).getNumberAll()).getFields();
    }

    private BlockCoverageResult getFieldRes(Block _mem) {
        return types.getValue(((RootBlock)_mem.getParent()).getNumberAll()).getFields().getValue(((InfoBlock)_mem).getFieldNumber());
    }

    public IdMap<Block, FunctionCoverageResult> getOperators() {
        return operators;
    }

    public IdMap<Block, TypeCoverageResult> getTypes() {
        return types;
    }

    public void putBlockOperation(int _indexAnnotGroup, int _indexAnnot, Forwards _fwd, Block _block, OperationNode _op, ExecOperationNode _exec) {
        if (!isCovering()) {
            return;
        }
        IdMap<OperationNode,AbstractCoverageResult> instr_;
        IdMap<ExecOperationNode, OperationNode> mapping_;
        BlockCoverageResult blr_ =  getResultBlock(_block,_fwd.isAnnotAnalysis(),_indexAnnotGroup,_indexAnnot);
        mapping_ = blr_.getMapping();
        instr_ = blr_.getCovers();
        mapping_.addEntry(_exec, _op);
        if (_op.getParent() instanceof SafeDotOperation) {
            if (_op.getParent().getFirstChild() == _op) {
                instr_.addEntry(_op, new NullCoverageResult());
                return;
            }
        }
        String prim_ = _fwd.getAliasPrimBoolean();
        String boolType_ = _fwd.getAliasBoolean();
        if (_op.getParent() instanceof NullSafeOperation) {
            if (_op.getArgument() == null) {
                if (_op.getResultClass().isBoolType(boolType_,prim_)) {
                    instr_.addEntry(_op, new NullBooleanCoverageResult());
                } else {
                    instr_.addEntry(_op, new NullCoverageResult());
                }
            } else {
                instr_.addEntry(_op,new StandardCoverageResult());
            }
            return;
        }
        if (_op.getParent() instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation c_ = (CompoundAffectationOperation) _op.getParent();
            if (StringUtil.quickEq(c_.getOper(),Block.NULL_EQ)) {
                if (_op.getResultClass().isBoolType(boolType_,prim_)) {
                    instr_.addEntry(_op, new NullBooleanCoverageResult());
                } else {
                    instr_.addEntry(_op, new NullCoverageResult());
                }
                return;
            }
        }
        if ((_op.getResultClass().matchClass(prim_) || !_op.getResultClass().getImplicitsTest().isEmpty())&& _op.getArgument() == null) {
            instr_.addEntry(_op,new BooleanCoverageResult());
        } else {
            instr_.addEntry(_op,new StandardCoverageResult());
        }
    }

    public void putToStringOwner(String _owner) {
        if (!isCovering()) {
            return;
        }
        toStringOwners.add(_owner);
    }

    public void passLoop(ContextEl _context, ExecBlock _loop, Argument _value) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _context.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
        BooleanCoverageResult covTwo_ = fctRes_.getCoverLoops().getVal(fctRes_.getMappingBlocks().getVal(_loop));
        covTwo_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }
    public void passConditions(ContextEl _context, ExecBlock _condition, Argument _value, ExecOperationNode _exec) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _context.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
        BooleanCoverageResult covTwo_ = fctRes_.getCoversConditions().getVal(fctRes_.getMappingBlocks().getVal(_condition));
        covTwo_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
        if (_exec.getArgument() != null) {
            covTwo_.fullCover();
        } else {
            covTwo_.cover(_value);
        }
    }
    public void passSwitch(ContextEl _context, ExecBlock _parent, ExecBlock _child, Argument _value) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _context.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
        StandardCoverageResult covTwo_ = fctRes_.getCoverSwitchs().getVal(fctRes_.getMappingBlocks().getVal(_parent)).getChildren().getVal(fctRes_.getMappingBlocks().getVal(_child));
        covTwo_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }
    public void passSwitch(ContextEl _context, ExecBlock _parent, Argument _value) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _context.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
        StandardCoverageResult covTwo_ = fctRes_.getCoverSwitchs().getVal(fctRes_.getMappingBlocks().getVal(_parent)).getResultNoDef();
        covTwo_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }

    public void passCatches(AbstractPageEl _page,ExecBlock _block) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_page);
        fctRes_.getCatches().set(fctRes_.getMappingBlocks().getVal(_block),BoolVal.TRUE);
    }

    public void passBlockOperation(ContextEl _context, ExecOperationNode _exec, boolean _full, ArgumentsPair _pair) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _context.getLastPage();
        ExecBlock en_ = lastPage_.getBlock();
        IdMap<OperationNode, AbstractCoverageResult> instr_;
        Block matchBl_;
        OperationNode ana_;
        int indexAnnotGroup_ = -1;
        int indexAnnot_ = -1;
        if (lastPage_ instanceof ReflectAnnotationPageEl) {
            ReflectAnnotationPageEl annotRet_ = (ReflectAnnotationPageEl)lastPage_;
            if (annotRet_.isOnParameters()) {
                indexAnnotGroup_ = annotRet_.getIndexAnnotationParam();
                indexAnnot_ = annotRet_.getAnnotationsParamsIndexes().get(indexAnnotGroup_).get(annotRet_.getIndexAnnotation());
            } else {
                indexAnnot_ = annotRet_.getAnnotationsIndexes().get(annotRet_.getIndexAnnotation());
            }
            AnnotatedStruct annotated_ = annotRet_.getAnnotated();
            ExecAnnotableBlock annotableBlock_ = annotated_.getAnnotableBlock();
            if (annotated_ instanceof FieldMetaInfo) {
                ExecRootBlock type_ = ((FieldMetaInfo)annotated_).getDeclaring();
                TypeCoverageResult val_ = types.getVal(mappingTypes.getVal(type_));
                matchBl_ = val_.getMappingFields().getVal((ExecBlock) annotableBlock_);
            } else if (annotated_ instanceof AnnotatedParamStruct){
                ExecRootBlock type_ = ((AnnotatedParamStruct) annotated_).getPair().getType();
                if (annotableBlock_ instanceof ExecAnnotationMethodBlock){
                    matchBl_ = types.getVal(mappingTypes.getVal(type_)).getMappingFields().getVal((ExecBlock) annotableBlock_);
                } else {
                    matchBl_ = getFctBlock(type_, ((AnnotatedParamStruct) annotated_).getAnnotableBlockParam());
                }
            } else {
                ExecRootBlock type_ = ((ClassMetaInfo) annotated_).getRootBlock();
                matchBl_ = mappingTypes.getVal(type_);
            }
        } else if (lastPage_ instanceof ReflectGetDefaultValuePageEl) {
            ReflectGetDefaultValuePageEl annotRet_ = (ReflectGetDefaultValuePageEl)lastPage_;
            ExecTypeFunction pair_ = annotRet_.getMetaInfo().getPair();
            ExecRootBlock type_ = pair_.getType();
            ExecAnnotationMethodBlock annotMeth_ = (ExecAnnotationMethodBlock) pair_.getFct();
            matchBl_ = types.getVal(mappingTypes.getVal(type_)).getMappingFields().getVal(annotMeth_);
        } else if (en_ instanceof ExecInfoBlock) {
            TypeCoverageResult val_ = types.getVal(mappingTypes.getVal(lastPage_.getBlockRootType()));
            matchBl_ = val_.getMappingFields().getVal(en_);
        } else if (en_ instanceof ExecAnnotationMethodBlock) {
            matchBl_ = types.getVal(mappingTypes.getVal(lastPage_.getBlockRootType())).getMappingFields().getVal(en_);
        } else {
            FunctionCoverageResult fctRes_ = getFctRes(lastPage_);
            matchBl_ = fctRes_.getMappingBlocks().getVal(en_);
        }
        BlockCoverageResult blRes_ = getResultBlock(matchBl_, lastPage_ instanceof ReflectAnnotationPageEl, indexAnnotGroup_, indexAnnot_);
        ana_ = blRes_.getMapping().getVal(_exec);
        instr_ = blRes_.getCovers();
        AbstractCoverageResult result_ = instr_.getVal(ana_);
        if (result_ == null) {
            return;
        }
        result_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
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
                if (StringUtil.quickEq(p_.getOper(),"||=")) {
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
        return getFctRes((MemberCallingsBlock) getFctBlock(_type, _block));
    }
    private Block getFctBlock(ExecRootBlock _type, ExecMemberCallingsBlock _block) {
        Block valLambda_ = mappingLambdas.getVal(_block);
        if (valLambda_ instanceof AnonymousFunctionBlock) {
            return valLambda_;
        }
        Block t_ = mappingTypes.getVal(_type);
        if (!(t_ instanceof RootBlock)) {
            return mappingOperators.getVal(_block);
        }
        TypeCoverageResult value_ = types.getValue(((RootBlock) t_).getNumberAll());
        return value_.getMappingBlocks().getVal(_block);
    }
    public CustList<FileBlock> getFiles() {
        return files;
    }

    public AbstractCoverageResult getCovers(Block _block, OperationNode _oper, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        return getResultBlock(_block, _annot, _indexAnnotGroup, _indexAnnot).getCovers().getVal(_oper);
    }
    private BlockCoverageResult getResultBlock(Block _block, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        if (_annot) {
            if (_block instanceof InfoBlock) {
                BlockCoverageResult fieldRes_ = getFieldRes(_block);
                return fieldRes_.getAnnotations().get(_indexAnnot);
            }
            if (_block instanceof AnnotationMethodBlock) {
                AnnotationMethodBlock mem_ = (AnnotationMethodBlock) _block;
                BlockCoverageResult fctRes_ = types.getValue(((RootBlock)mem_.getParent()).getNumberAll()).getAnnotationsFields().getValue(mem_.getNameNumber());
                return fctRes_.getAnnotations().get(_indexAnnot);
            }
            if (_block instanceof NamedFunctionBlock) {
                NamedFunctionBlock mem_ = (NamedFunctionBlock) _block;
                FunctionCoverageResult fctRes_ = getFctRes(mem_);
                if (_indexAnnotGroup < 0) {
                    return fctRes_.getAnnotations().get(_indexAnnot);
                }
                return fctRes_.getAnnotationsParams().get(_indexAnnotGroup).get(_indexAnnot);
            }
            TypeCoverageResult fctRes_ = types.getValue(((RootBlock)_block).getNumberAll());
            return fctRes_.getAnnotations().get(_indexAnnot);
        }
        if (_block instanceof AnnotationMethodBlock) {
            return types.getValue(((RootBlock)_block.getParent()).getNumberAll()).getAnnotationsFields().getValue(((AnnotationMethodBlock)_block).getNameNumber());
        }
        if (_block instanceof InfoBlock) {
            return getFieldRes(_block);
        }
        MemberCallingsBlock outerFuntion_ = _block.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getBlocks().getVal(_block);
    }

    public AbstractCoverageResult getCoversConditions(Block _exec) {
        MemberCallingsBlock outerFuntion_ = _exec.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoversConditions().getVal(_exec);
    }

    public StandardCoverageResult getCoverSwitchs(Block _sw, Block _child) {
        MemberCallingsBlock outerFuntion_ = _sw.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoverSwitchs().getVal(_sw).getChildren().getVal(_child);
    }
    public  IdMap<Block, StandardCoverageResult> getCoverSwitchs(Block _sw) {
        MemberCallingsBlock outerFuntion_ = _sw.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoverSwitchs().getVal(_sw).getChildren();
    }

    public StandardCoverageResult getCoverNoDefSwitchs(Block _sw) {
        MemberCallingsBlock outerFuntion_ = _sw.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoverSwitchs().getVal(_sw).noDefault();
    }

    public boolean getCatches(Block _catch) {
        MemberCallingsBlock outerFuntion_ = _catch.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCatches().getVal(_catch) == BoolVal.TRUE;
    }

    public AbstractCoverageResult getCoverLoops(Block _bl) {
        MemberCallingsBlock outerFuntion_ = _bl.getOuterFct();
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoverLoops().getVal(_bl);
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
}
