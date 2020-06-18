package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.ReflectAnnotationPageEl;
import code.expressionlanguage.exec.calls.ReflectGetDefaultValuePageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.opers.CompoundAffectationOperation;
import code.expressionlanguage.opers.NullSafeOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SafeDotOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class Coverage {
    private IdMap<Block,IdMap<ExecOperationNode,AbstractCoverageResult>> covers = new IdMap<Block,IdMap<ExecOperationNode,AbstractCoverageResult>>();
    private IdMap<Block,BooleanCoverageResult> coverLoops = new IdMap<Block,BooleanCoverageResult>();
    private StringMap<IdMap<NamedFunctionBlock,Boolean>> calls = new StringMap<IdMap<NamedFunctionBlock,Boolean>>();
    private IdMap<Block,IdMap<Block,StandardCoverageResult>> coverSwitchs = new IdMap<Block,IdMap<Block,StandardCoverageResult>>();
    private IdMap<Block,StandardCoverageResult> coverNoDefSwitchs = new IdMap<Block,StandardCoverageResult>();
    private IdMap<Block,Boolean> catches = new IdMap<Block,Boolean>();
    private IdMap<ExecBlock,Block> mappingBlocks = new IdMap<ExecBlock,Block>();
    private IdMap<Block,IdMap<ExecOperationNode,OperationNode>> mapping = new IdMap<Block,IdMap<ExecOperationNode,OperationNode>>();
    private IdMap<Block,IdMap<ExecOperationNode,OperationNode>> mappingAnnot = new IdMap<Block,IdMap<ExecOperationNode,OperationNode>>();
    private IdMap<Block,IdMap<ExecOperationNode,OperationNode>> mappingAnnotMembers = new IdMap<Block,IdMap<ExecOperationNode,OperationNode>>();
    private StringMap<Integer> localVars = new StringMap<Integer>();
    private StringMap<Integer> mutableVars = new StringMap<Integer>();
    private StringMap<Integer> loopVars = new StringMap<Integer>();
    private StringMap<Integer> catchVars = new StringMap<Integer>();
    private StringMap<Integer> paramVars = new StringMap<Integer>();
    private String currentFileName = "";
    private boolean possibleDeclareLoopVars;
    private CustList<PartOffset> currentParts = new CustList<PartOffset>();
    public void putBlockOperationsLoops(ContextEl _context, Block _block) {
        if (!_context.isCovering()) {
            return;
        }
        coverLoops.put(_block,new BooleanCoverageResult());
    }
    public void putBlockOperationsSwitchs(ContextEl _context, Block _block, boolean _def) {
        if (!_context.isCovering()) {
            return;
        }
        coverSwitchs.put(_block, new IdMap<Block, StandardCoverageResult>());
        if (!_def) {
            coverNoDefSwitchs.put(_block,new StandardCoverageResult());
        }
    }
    public void putBlockOperationsSwitchs(ContextEl _context, Block _block, Block _child) {
        if (!_context.isCovering()) {
            return;
        }
        coverSwitchs.getVal(_block).put(_child, new StandardCoverageResult());
    }
    public void putBlockOperations(ContextEl _context, Block _block) {
        if (!_context.isCovering()) {
            return;
        }
        covers.put(_block, new IdMap<ExecOperationNode, AbstractCoverageResult>());
        getMapping().put(_block,new IdMap<ExecOperationNode, OperationNode>());
    }
    public void putBlockOperations(ContextEl _context, ExecBlock _exec,Block _block) {
        if (!_context.isCovering()) {
            return;
        }
        getMappingBlocks().put(_exec,_block);
    }
    public void putBlockOperationsField(ContextEl _context, Block _block) {
        if (!_context.isCovering()) {
            return;
        }
        if (_context.isAnnotAnalysisField()) {
            mappingAnnot.put(_block,new IdMap<ExecOperationNode, OperationNode>());
            return;
        }
        mappingAnnotMembers.put(_block,new IdMap<ExecOperationNode, OperationNode>());
    }
    public void putBlockOperation(ContextEl _context, Block _block, OperationNode _op, ExecOperationNode _exec) {
        if (!_context.isCovering()) {
            return;
        }
        if (_context.isAnnotAnalysis()) {
            if (_context.isAnnotAnalysisField()) {
                IdMap<ExecOperationNode, OperationNode> mapping_ = mappingAnnot.getVal(_block);
                mapping_.put(_exec,_op);
                return;
            }
            IdMap<ExecOperationNode, OperationNode> mapping_ = mappingAnnotMembers.getVal(_block);
            mapping_.put(_exec,_op);
            return;
        }
        IdMap<ExecOperationNode,AbstractCoverageResult> instr_;
        instr_ = covers.getVal(_block);
        IdMap<ExecOperationNode, OperationNode> mapping_ = getMapping().getVal(_block);
        mapping_.put(_exec,_op);
        if (_op.getParent() instanceof SafeDotOperation) {
            if (_op.getParent().getFirstChild() == _op) {
                instr_.put(_exec, new NullCoverageResult());
                return;
            }
        }
        if (_op.getParent() instanceof NullSafeOperation) {
            if (_exec.getArgument() == null) {
                if (_op.getResultClass().isBoolType(_context)) {
                    instr_.put(_exec, new NullBooleanCoverageResult());
                } else {
                    instr_.put(_exec, new NullCoverageResult());
                }
            } else {
                instr_.put(_exec,new StandardCoverageResult());
            }
            return;
        }
        if (_op.getParent() instanceof CompoundAffectationOperation) {
            CompoundAffectationOperation c_ = (CompoundAffectationOperation) _op.getParent();
            if (StringList.quickEq(c_.getOper(),Block.NULL_EQ)) {
                if (_op.getResultClass().isBoolType(_context)) {
                    instr_.put(_exec, new NullBooleanCoverageResult());
                } else {
                    instr_.put(_exec, new NullCoverageResult());
                }
                return;
            }
        }
        String b_ = _context.getStandards().getAliasPrimBoolean();
        if (_op.getResultClass().matchClass(b_) && _exec.getArgument() == null) {
            instr_.put(_exec,new BooleanCoverageResult());
        } else {
            instr_.put(_exec,new StandardCoverageResult());
        }
    }
    public void putCalls(ContextEl _context, String _type) {
        if (!_context.isCovering()) {
            return;
        }
        calls.put(_type,new IdMap<NamedFunctionBlock,Boolean>());
    }
    public void putCalls(ContextEl _context, String _type,NamedFunctionBlock _block) {
        if (!_context.isCovering()) {
            return;
        }
        calls.getVal(_type).put(_block,false);
    }
    public void putCatches(ContextEl _context, Block _block) {
        if (!_context.isCovering()) {
            return;
        }
        catches.put(_block,false);
    }
    public void passExecLoop(ContextEl _context, Argument _value) {
        if (!_context.isCovering()) {
            return;
        }
        ReadWrite rw_ = _context.getLastPage().getReadWrite();
        ExecBlock en_ = rw_.getBlock();
        BooleanCoverageResult cov_ = coverLoops.getVal(mappingBlocks.getVal(en_));
        cov_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
        cov_.cover(_value);
    }
    public void passSwitch(ContextEl _context, ExecBlock _child,Argument _value) {
        if (!_context.isCovering()) {
            return;
        }
        ReadWrite rw_ = _context.getLastPage().getReadWrite();
        ExecBlock en_ = rw_.getBlock();
        StandardCoverageResult cov_ = coverSwitchs.getVal(mappingBlocks.getVal(en_)).getVal(mappingBlocks.getVal(_child));
        cov_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
        cov_.cover(_value);
    }
    public void passExecSwitch(ContextEl _context, Argument _value) {
        if (!_context.isCovering()) {
            return;
        }
        ReadWrite rw_ = _context.getLastPage().getReadWrite();
        ExecBlock en_ = rw_.getBlock();
        StandardCoverageResult cov_ = coverNoDefSwitchs.getVal(mappingBlocks.getVal(en_));
        cov_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
        cov_.cover(_value);
    }
    public void passBlockOperation(ContextEl _context, ExecOperationNode _exec, Argument _value, boolean _full) {
        if (!_context.isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _context.getLastPage();
        if (lastPage_ instanceof ReflectAnnotationPageEl || lastPage_ instanceof ReflectGetDefaultValuePageEl) {
            return;
        }
        ReadWrite rw_ = lastPage_.getReadWrite();
        ExecBlock en_ = rw_.getBlock();
        IdMap<ExecOperationNode,AbstractCoverageResult> instr_;
        instr_ = covers.getVal(mappingBlocks.getVal(en_));
        if (instr_ == null) {
            return;
        }
        AbstractCoverageResult result_ = instr_.getVal(_exec);
        if (result_ == null) {
            return;
        }
        result_.setInit(_context.getInitializingTypeInfos().isWideInitEnums());
        if (_full) {
            result_.fullCover();
        } else {
            result_.cover(_value);
        }
    }
    public void passCalls(ContextEl _context, String _type,ExecNamedFunctionBlock _block) {
        if (!_context.isCovering()) {
            return;
        }
        calls.getVal(_type).set((NamedFunctionBlock)mappingBlocks.getVal(_block),true);
    }
    public void passCatches(ContextEl _context, ExecBlock _block) {
        if (!_context.isCovering()) {
            return;
        }
        catches.set(mappingBlocks.getVal(_block),true);
    }
    public IdMap<ExecOperationNode, AbstractCoverageResult> getCovers(ExecBlock _exec) {
        return covers.getVal(mappingBlocks.getVal(_exec));
    }
    public IdMap<Block, IdMap<ExecOperationNode, AbstractCoverageResult>> getCovers() {
        return covers;
    }

    public IdMap<Block, BooleanCoverageResult> getCoverLoops() {
        return coverLoops;
    }

    public IdMap<Block, IdMap<ExecOperationNode, OperationNode>> getMapping() {
        return mapping;
    }

    public IdMap<ExecBlock, Block> getMappingBlocks() {
        return mappingBlocks;
    }

    public IdMap<Block, IdMap<ExecOperationNode, OperationNode>> getMappingAnnot() {
        return mappingAnnot;
    }

    public IdMap<Block, IdMap<ExecOperationNode, OperationNode>> getMappingAnnotMembers() {
        return mappingAnnotMembers;
    }

    public StandardCoverageResult getCoverSwitchs(ExecBlock _sw, ExecBlock _child) {
        return coverSwitchs.getVal(mappingBlocks.getVal(_sw)).getVal(mappingBlocks.getVal(_child));
    }

    public  IdMap<Block, StandardCoverageResult> getCoverSwitchs(ExecBlock _sw) {
        return coverSwitchs.getVal(mappingBlocks.getVal(_sw));
    }

    public IdMap<Block, IdMap<Block, StandardCoverageResult>> getCoverSwitchs() {
        return coverSwitchs;
    }

    public StandardCoverageResult getCoverNoDefSwitchs(ExecBlock _sw) {
        return coverNoDefSwitchs.getVal(mappingBlocks.getVal(_sw));
    }

    public IdMap<Block, StandardCoverageResult> getCoverNoDefSwitchs() {
        return coverNoDefSwitchs;
    }

    public StringMap<IdMap<NamedFunctionBlock, Boolean>> getCalls() {
        return calls;
    }

    public IdMap<Block, Boolean> getCatches() {
        return catches;
    }

    public StringMap<Integer> getLocalVars() {
        return localVars;
    }

    public StringMap<Integer> getMutableVars() {
        return mutableVars;
    }

    public StringMap<Integer> getLoopVars() {
        return loopVars;
    }

    public StringMap<Integer> getCatchVars() {
        return catchVars;
    }

    public StringMap<Integer> getParamVars() {
        return paramVars;
    }

    public boolean isPossibleDeclareLoopVars() {
        return possibleDeclareLoopVars;
    }

    public void setPossibleDeclareLoopVars(boolean _possibleDeclareLoopVars) {
        possibleDeclareLoopVars = _possibleDeclareLoopVars;
    }

    public String getCurrentFileName() {
        return currentFileName;
    }

    public void setCurrentFileName(String _currentFileName) {
        currentFileName = _currentFileName;
    }

    public CustList<PartOffset> getCurrentParts() {
        return currentParts;
    }

    public boolean getCatches(ExecBlock _catch) {
        return catches.getVal(mappingBlocks.getVal(_catch));
    }

    public AbstractCoverageResult getCoverLoops(ExecBlock _bl) {
        return coverLoops.getVal(mappingBlocks.getVal(_bl));
    }
}
