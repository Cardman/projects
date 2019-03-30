package code.expressionlanguage.methods.util;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.ReflectAnnotationPageEl;
import code.expressionlanguage.calls.ReflectGetDefaultValuePageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.util.IdMap;
import code.util.StringMap;

public final class Coverage {
    private IdMap<Block,IdMap<ExecOperationNode,AbstractCoverageResult>> covers = new IdMap<Block,IdMap<ExecOperationNode,AbstractCoverageResult>>();
    private IdMap<Block,BooleanCoverageResult> coverLoops = new IdMap<Block,BooleanCoverageResult>();
    private StringMap<IdMap<NamedFunctionBlock,Boolean>> calls = new StringMap<IdMap<NamedFunctionBlock,Boolean>>();
    private IdMap<Block,IdMap<Block,StandardCoverageResult>> coverSwitchs = new IdMap<Block,IdMap<Block,StandardCoverageResult>>();
    private IdMap<Block,StandardCoverageResult> coverNoDefSwitchs = new IdMap<Block,StandardCoverageResult>();
    private IdMap<Block,IdMap<ExecOperationNode,OperationNode>> mapping = new IdMap<Block,IdMap<ExecOperationNode,OperationNode>>();
    public void putBlockOperationsLoops(Analyzable _context, Block _block) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        coverLoops.put(_block,new BooleanCoverageResult());
    }
    public void putBlockOperationsSwitchs(Analyzable _context, Block _block, boolean _def) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        coverSwitchs.put(_block, new IdMap<Block, StandardCoverageResult>());
        if (!_def) {
            coverNoDefSwitchs.put(_block,new StandardCoverageResult());
        }
    }
    public void putBlockOperationsSwitchs(Analyzable _context, Block _block, Block _child) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        coverSwitchs.getVal(_block).put(_child, new StandardCoverageResult());
    }
    public void putBlockOperations(Analyzable _context, Block _block) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        covers.put(_block, new IdMap<ExecOperationNode, AbstractCoverageResult>());
        getMapping().put(_block,new IdMap<ExecOperationNode, OperationNode>());
    }
    public void putBlockOperation(Analyzable _context, Block _block, OperationNode _op, ExecOperationNode _exec) {
        if (!_context.getContextEl().isCovering() || _context.getContextEl().isEnabledInternVars()) {
            return;
        }
        if (_context.getContextEl().isAnnotAnalysis()) {
            return;
        }
        IdMap<ExecOperationNode,AbstractCoverageResult> instr_;
        instr_ = covers.getVal(_block);
        IdMap<ExecOperationNode, OperationNode> mapping_ = getMapping().getVal(_block);
        mapping_.put(_exec,_op);
        String b_ = _context.getStandards().getAliasPrimBoolean();
        if (_op.getResultClass().matchClass(b_) && _exec.getArgument() == null) {
            instr_.put(_exec,new BooleanCoverageResult());
        } else {
            instr_.put(_exec,new StandardCoverageResult());
        }
    }
    public void putCalls(Analyzable _context, String _type) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        calls.put(_type,new IdMap<NamedFunctionBlock,Boolean>());
    }
    public void putCalls(Analyzable _context, String _type,NamedFunctionBlock _block) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        calls.getVal(_type).put(_block,false);
    }
    public void passLoop(Analyzable _context, Argument _value) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        ReadWrite rw_ = _context.getContextEl().getLastPage().getReadWrite();
        Block en_ = rw_.getBlock();
        coverLoops.getVal(en_).cover(_value);
    }
    public void passSwitch(Analyzable _context, Block _child,Argument _value) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        ReadWrite rw_ = _context.getContextEl().getLastPage().getReadWrite();
        Block en_ = rw_.getBlock();
        coverSwitchs.getVal(en_).getVal(_child).cover(_value);
    }
    public void passSwitch(Analyzable _context, Argument _value) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        ReadWrite rw_ = _context.getContextEl().getLastPage().getReadWrite();
        Block en_ = rw_.getBlock();
        StandardCoverageResult cov_ = coverNoDefSwitchs.getVal(en_);
        cov_.cover(_value);
    }
    public void passBlockOperation(Analyzable _context, ExecOperationNode _exec, Argument _value) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        AbstractPageEl lastPage_ = _context.getContextEl().getLastPage();
        if (lastPage_ instanceof ReflectAnnotationPageEl || lastPage_ instanceof ReflectGetDefaultValuePageEl) {
            return;
        }
        ReadWrite rw_ = lastPage_.getReadWrite();
        Block en_ = rw_.getBlock();
        IdMap<ExecOperationNode,AbstractCoverageResult> instr_;
        instr_ = covers.getVal(en_);
        instr_.getVal(_exec).cover(_value);
    }
    public void passCalls(Analyzable _context, String _type,NamedFunctionBlock _block) {
        if (!_context.getContextEl().isCovering()) {
            return;
        }
        calls.getVal(_type).set(_block,true);
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

    public IdMap<Block, IdMap<Block, StandardCoverageResult>> getCoverSwitchs() {
        return coverSwitchs;
    }

    public IdMap<Block, StandardCoverageResult> getCoverNoDefSwitchs() {
        return coverNoDefSwitchs;
    }

    public StringMap<IdMap<NamedFunctionBlock, Boolean>> getCalls() {
        return calls;
    }
}
