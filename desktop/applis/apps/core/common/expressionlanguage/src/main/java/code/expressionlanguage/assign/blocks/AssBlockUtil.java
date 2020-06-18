package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.BlocksFlags;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.methods.*;
import code.util.IdMap;

public final class AssBlockUtil {
    private AssBlockUtil() {
    }

    public static AssMemberCallingsBlock buildFctInstructions(MemberCallingsBlock _mem, ExecMemberCallingsBlock _execMem, ContextEl _cont, AssBlock _prev, AssignedVariablesBlock _a) {
        _mem.buildFctInstructionsReadOnly(_cont,_execMem);
        AnalyzingEl a_ = _cont.getAnalyzing().getAnalysisAss();
        AssMemberCallingsBlock meth_ = AssBlockUtil.getExecutableNodes(a_.getCanCompleteNormally(), a_.getCanCompleteNormallyGroup(), _mem);
        meth_.buildFctInstructions(_cont,_prev,_a);
        return meth_;
    }
    public static AssMemberCallingsBlock getExecutableNodes(BlocksFlags _normal, BlocksFlags _group, MemberCallingsBlock _list) {
        AssMemberCallingsBlock m_ = getAssMemberCalling(_normal, _group, _list);
        if (_list.getFirstChild() == null) {
            return m_;
        }
        Block c_ = _list;
        AssBlock ac_ = m_;
        while (c_ != null) {
            Block f_ = c_.getFirstChild();
            if (ac_ instanceof AssBracedBlock&&f_ != null) {
                AssBlock af_ = createAssBlock(_normal, _group, f_);
                ((AssBracedBlock)ac_).appendChild(af_);
                ac_ = af_;
                c_ = f_;
                continue;
            }
            while (true) {
                Block n_ = c_.getNextSibling();
                if (n_ != null) {
                    AssBlock af_ = createAssBlock(_normal, _group, n_);
                    AssBracedBlock par_ = ac_.getParent();
                    par_.appendChild(af_);
                    ac_ = af_;
                    c_ = n_;
                    break;
                }
                BracedBlock p_ = c_.getParent();
                if (p_ == _list) {
                    c_ = null;
                    break;
                }
                c_ = p_;
                ac_ = ac_.getParent();
            }
        }
        return m_;
    }
    public static AssBlock createAssBlock(BlocksFlags _normal, BlocksFlags _group, Block _anaNode) {
        if (_anaNode instanceof ForMutableIterativeLoop) {
            return new AssForMutableIterativeLoop(get(_normal,_anaNode), get(_group,_anaNode), (ForMutableIterativeLoop) _anaNode);
        }
        if (_anaNode instanceof BreakBlock) {
            return new AssBreakBlock(get(_normal,_anaNode), get(_group,_anaNode), (BreakBlock) _anaNode);
        }
        if (_anaNode instanceof ContinueBlock) {
            return new AssContinueBlock(get(_normal,_anaNode), get(_group,_anaNode), (ContinueBlock) _anaNode);
        }
        if (_anaNode instanceof IfCondition) {
            return new AssIfCondition(get(_normal,_anaNode), get(_group,_anaNode), (IfCondition) _anaNode);
        }
        if (_anaNode instanceof ElseIfCondition) {
            return new AssElseIfCondition(get(_normal,_anaNode), get(_group,_anaNode), (ElseIfCondition) _anaNode);
        }
        if (_anaNode instanceof ElseCondition) {
            return new AssElseCondition(get(_normal,_anaNode), get(_group,_anaNode), (ElseCondition) _anaNode);
        }
        if (_anaNode instanceof TryEval) {
            return new AssTryEval(get(_normal,_anaNode), get(_group,_anaNode), (TryEval) _anaNode);
        }
        if (_anaNode instanceof AbstractCatchEval) {
            return new AssCatchEval(get(_normal,_anaNode), get(_group,_anaNode), (AbstractCatchEval) _anaNode);
        }
        if (_anaNode instanceof FinallyEval) {
            return new AssFinallyEval(get(_normal,_anaNode), get(_group,_anaNode), (FinallyEval) _anaNode);
        }
        if (_anaNode instanceof SwitchPartBlock) {
            return new AssSwitchPartBlock(get(_normal,_anaNode), get(_group,_anaNode), _anaNode instanceof DefaultCondition);
        }
        if (_anaNode instanceof SwitchBlock) {
            return new AssSwitchBlock(get(_normal,_anaNode), get(_group,_anaNode), (SwitchBlock) _anaNode);
        }
        if (_anaNode instanceof ReturnMethod) {
            return new AssReturnMethod(get(_normal,_anaNode), get(_group,_anaNode), (ReturnMethod) _anaNode);
        }
        if (_anaNode instanceof Throwing) {
            return new AssThrowing(get(_normal,_anaNode), get(_group,_anaNode), (Throwing) _anaNode);
        }
        if (_anaNode instanceof DeclareVariable) {
            return new AssDeclareVariable(get(_normal,_anaNode), get(_group,_anaNode),(DeclareVariable)_anaNode);
        }
        if (_anaNode instanceof Line) {
            return new AssLine(get(_normal,_anaNode), get(_group,_anaNode),(Line)_anaNode);
        }
        if (_anaNode instanceof EmptyInstruction) {
            return new AssEmptyInstruction(get(_normal,_anaNode), get(_group,_anaNode));
        }
        if (_anaNode instanceof DoBlock) {
            return new AssDoBlock(get(_normal,_anaNode), get(_group,_anaNode),(DoBlock)_anaNode);
        }
        if (_anaNode instanceof DoWhileCondition) {
            return new AssDoWhileCondition(get(_normal,_anaNode), get(_group,_anaNode),(DoWhileCondition)_anaNode);
        }
        if (_anaNode instanceof WhileCondition) {
            return new AssWhileCondition(get(_normal,_anaNode), get(_group,_anaNode),(WhileCondition)_anaNode);
        }
        if (_anaNode instanceof ForEachLoop) {
            return new AssForEach(get(_normal,_anaNode), get(_group,_anaNode),(ForEachLoop)_anaNode);
        }
        if (_anaNode instanceof ForEachTable) {
            return new AssForEach(get(_normal,_anaNode), get(_group,_anaNode),(ForEachTable)_anaNode);
        }
        if (_anaNode instanceof ForIterativeLoop) {
            return new AssForIterativeLoop(get(_normal,_anaNode), get(_group,_anaNode),(ForIterativeLoop)_anaNode);
        }
        if (_anaNode instanceof RootBlock) {
            return new AssRootBlock(get(_normal,_anaNode), get(_group,_anaNode));
        }
        return new AssUnclassedBracedBlock(get(_normal,_anaNode), get(_group,_anaNode));
    }

    private static AssMemberCallingsBlock getAssMemberCalling(BlocksFlags _normal, BlocksFlags _group, Block _anaNode) {
        if (_anaNode instanceof ConstructorBlock) {
            return new AssConstructorBlock(get(_normal,_anaNode),get(_group,_anaNode));
        }
        if (_anaNode instanceof InitBlock) {
            return new AssInit(get(_normal,_anaNode),get(_group,_anaNode));
        }
        return new AssStdMethodBlock(get(_normal,_anaNode), get(_group,_anaNode));
    }
    private static boolean get(BlocksFlags _normal, Block _anaNode) {
        return _normal.getVal(_anaNode);
    }
}
