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
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
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
        fctRes_.getCatches().addEntry(_block,false);
    }
    public void putBlockOperations(MemberCallingsBlock _mem,Block _block) {
        if (!isCovering()) {
            return;
        }
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

    public FunctionCoverageResult getFctRes(MemberCallingsBlock _mem) {
        FunctionCoverageResult fctRes_;
        if (_mem instanceof OperatorBlock) {
            fctRes_ = operators.getValue(((OperatorBlock) _mem).getNameNumber());
        } else if (_mem instanceof AnonymousFunctionBlock){
            fctRes_ = lambdas.getValue(((AnonymousFunctionBlock)_mem).getNumberLambda());
        } else {
            fctRes_ = types.getValue(((RootBlock)_mem.getParent()).getNumberAll()).getFunctions().getValue(_mem.getNumberFct());
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

    public void putBlockOperation(Forwards _fwd, Block _block, OperationNode _op, ExecOperationNode _exec) {
        if (!isCovering()) {
            return;
        }
        if (_fwd.isAnnotAnalysis()) {
            return;
        }
        IdMap<OperationNode,AbstractCoverageResult> instr_;
        IdMap<ExecOperationNode, OperationNode> mapping_;
        if (_block instanceof InfoBlock) {
            BlockCoverageResult fieldRes_ = getFieldRes(_block);
            mapping_ = fieldRes_.getMapping();
            instr_ = fieldRes_.getCovers();
        } else {
            FunctionCoverageResult fctRes_ = getFctRes(Block.getOuterFuntion(_block));
            mapping_ = fctRes_.getBlocks().getVal(_block).getMapping();
            instr_ = fctRes_.getBlocks().getVal(_block).getCovers();
        }
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
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_.getBlockRootType(), (ExecMemberCallingsBlock) lastPage_.getBlockRoot());
        BooleanCoverageResult covTwo_ = fctRes_.getCoverLoops().getVal(fctRes_.getMappingBlocks().getVal(_loop));
        covTwo_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }
    public void passConditions(ContextEl _context, ExecBlock _condition, Argument _value, ExecOperationNode _exec) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _context.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_.getBlockRootType(), (ExecMemberCallingsBlock) lastPage_.getBlockRoot());
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
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_.getBlockRootType(), (ExecMemberCallingsBlock) lastPage_.getBlockRoot());
        StandardCoverageResult covTwo_ = fctRes_.getCoverSwitchs().getVal(fctRes_.getMappingBlocks().getVal(_parent)).getChildren().getVal(fctRes_.getMappingBlocks().getVal(_child));
        covTwo_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }
    public void passSwitch(ContextEl _context, ExecBlock _parent, Argument _value) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _context.getLastPage();
        FunctionCoverageResult fctRes_ = getFctRes(lastPage_.getBlockRootType(), (ExecMemberCallingsBlock) lastPage_.getBlockRoot());
        StandardCoverageResult covTwo_ = fctRes_.getCoverSwitchs().getVal(fctRes_.getMappingBlocks().getVal(_parent)).getResultNoDef();
        covTwo_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
        covTwo_.cover(_value);
    }

    public void passCatches(AbstractPageEl _page,ExecBlock _block) {
        if (!isCovering()) {
            return;
        }
        FunctionCoverageResult fctRes_ = getFctRes(_page.getBlockRootType(), (ExecMemberCallingsBlock) _page.getBlockRoot());
        fctRes_.getCatches().set(fctRes_.getMappingBlocks().getVal(_block),true);
    }

    public void passBlockOperation(ContextEl _context, ExecOperationNode _exec, boolean _full, ArgumentsPair _pair) {
        if (!isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _context.getLastPage();
        ExecBlock en_ = lastPage_.getBlock();
        IdMap<OperationNode, AbstractCoverageResult> instr_ = null;
        OperationNode ana_ = null;
        if (en_ instanceof ExecInfoBlock) {
            TypeCoverageResult val_ = types.getVal(mappingTypes.getVal(lastPage_.getBlockRootType()));
            Block bl_ = val_.getMappingFields().getVal(en_);
            BlockCoverageResult fieldRes_ = getFieldRes(bl_);
            ana_ = fieldRes_.getMapping().getVal(_exec);
            instr_ = fieldRes_.getCovers();
        } else if (lastPage_.getBlockRoot() instanceof ExecMemberCallingsBlock) {
            FunctionCoverageResult fctRes_ = getFctRes(lastPage_.getBlockRootType(), (ExecMemberCallingsBlock) lastPage_.getBlockRoot());
            Block bl_ = fctRes_.getMappingBlocks().getVal(en_);
            BlockCoverageResult blVal_ = fctRes_.getBlocks().getVal(bl_);
            ana_ = blVal_.getMapping().getVal(_exec);
            instr_ = blVal_.getCovers();
        }
        if (instr_ == null) {
            return;
        }
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

    public void passCalls(ExecRootBlock _type, ExecMemberCallingsBlock _block) {
        if (!isCovering()) {
            return;
        }
        getFctRes(_type, _block).setCalled(true);
    }
    private FunctionCoverageResult getFctRes(ExecRootBlock _type, ExecMemberCallingsBlock _block) {
        Block valLambda_ = mappingLambdas.getVal(_block);
        if (valLambda_ instanceof AnonymousFunctionBlock) {
            return getFctRes((AnonymousFunctionBlock)valLambda_);
        }
        Block t_ = mappingTypes.getVal(_type);
        if (!(t_ instanceof RootBlock)) {
            return getFctRes((MemberCallingsBlock) mappingOperators.getVal(_block));
        }
        TypeCoverageResult value_ = types.getValue(((RootBlock) t_).getNumberAll());
        return getFctRes((MemberCallingsBlock) value_.getMappingBlocks().getVal(_block));
    }
    public CustList<FileBlock> getFiles() {
        return files;
    }

    public AbstractCoverageResult getCovers(Block _block, OperationNode _oper) {
        if (_block instanceof InfoBlock) {
            BlockCoverageResult fieldRes_ = getFieldRes(_block);
            return fieldRes_.getCovers().getVal(_oper);
        }
        if (!(_block instanceof BuildableElMethod)) {
            StandardCoverageResult res_ = new StandardCoverageResult();
            res_.fullCover();
            return res_;
        }
        MemberCallingsBlock outerFuntion_ = Block.getOuterFuntion(_block);
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getBlocks().getVal(_block).getCovers().getVal(_oper);
    }

    public AbstractCoverageResult getCoversConditions(Block _exec) {
        MemberCallingsBlock outerFuntion_ = Block.getOuterFuntion(_exec);
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoversConditions().getVal(_exec);
    }

    public StandardCoverageResult getCoverSwitchs(Block _sw, Block _child) {
        MemberCallingsBlock outerFuntion_ = Block.getOuterFuntion(_sw);
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoverSwitchs().getVal(_sw).getChildren().getVal(_child);
    }
    public  IdMap<Block, StandardCoverageResult> getCoverSwitchs(Block _sw) {
        MemberCallingsBlock outerFuntion_ = Block.getOuterFuntion(_sw);
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoverSwitchs().getVal(_sw).getChildren();
    }

    public StandardCoverageResult getCoverNoDefSwitchs(Block _sw) {
        MemberCallingsBlock outerFuntion_ = Block.getOuterFuntion(_sw);
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCoverSwitchs().getVal(_sw).noDefault();
    }

    public boolean getCatches(Block _catch) {
        MemberCallingsBlock outerFuntion_ = Block.getOuterFuntion(_catch);
        FunctionCoverageResult fctRes_ = getFctRes(outerFuntion_);
        return fctRes_.getCatches().getVal(_catch);
    }

    public AbstractCoverageResult getCoverLoops(Block _bl) {
        MemberCallingsBlock outerFuntion_ = Block.getOuterFuntion(_bl);
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
