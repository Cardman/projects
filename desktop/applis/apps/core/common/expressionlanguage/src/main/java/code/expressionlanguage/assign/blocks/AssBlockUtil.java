package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.BlocksFlags;
import code.expressionlanguage.analyze.BlocksLabels;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.methods.*;

public final class AssBlockUtil {
    private AssBlockUtil() {
    }

    public static AssMemberCallingsBlock buildFctInstructions(MemberCallingsBlock _mem, ExecMemberCallingsBlock _execMem, ContextEl _cont, AssBlock _prev, AssignedVariablesBlock _a) {
        _mem.buildFctInstructionsReadOnly(_cont,_execMem);
        AnalyzingEl a_ = _cont.getAnalyzing().getAnalysisAss();
        AssMemberCallingsBlock meth_ = AssBlockUtil.getExecutableNodes(a_.getCanCompleteNormally(), a_.getCanCompleteNormallyGroup(),a_.getFinals(),a_.getLabelsMapping(), _execMem);
        meth_.buildFctInstructions(_cont,_prev,_a);
        return meth_;
    }
    public static AssMemberCallingsBlock getExecutableNodes(BlocksFlags _normal, BlocksFlags _group, BlocksFlags _finals, BlocksLabels _lab, ExecMemberCallingsBlock _list) {
        AssMemberCallingsBlock m_ = getAssMemberCalling(_normal, _group, _list);
        if (_list.getFirstChild() == null) {
            return m_;
        }
        ExecBlock c_ = _list;
        AssBlock ac_ = m_;
        while (c_ != null) {
            ExecBlock f_ = c_.getFirstChild();
            if (ac_ instanceof AssBracedBlock&&f_ != null) {
                AssBlock af_ = createAssBlock(_normal, _group,_finals,_lab, f_);
                ((AssBracedBlock)ac_).appendChild(af_);
                ac_ = af_;
                c_ = f_;
                continue;
            }
            while (true) {
                ExecBlock n_ = c_.getNextSibling();
                if (n_ != null) {
                    AssBlock af_ = createAssBlock(_normal, _group,_finals,_lab, n_);
                    AssBracedBlock par_ = ac_.getParent();
                    par_.appendChild(af_);
                    ac_ = af_;
                    c_ = n_;
                    break;
                }
                ExecBracedBlock p_ = c_.getParent();
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
    public static AssBlock createAssBlock(BlocksFlags _normal, BlocksFlags _group, BlocksFlags _finals, BlocksLabels _lab, ExecBlock _anaNode) {
        if (_anaNode instanceof ExecForMutableIterativeLoop) {
            return new AssForMutableIterativeLoop(get(_normal,_anaNode), get(_group,_anaNode), get(_finals,_anaNode),get(_lab,_anaNode),(ExecForMutableIterativeLoop) _anaNode);
        }
        if (_anaNode instanceof ExecBreakBlock) {
            return new AssBreakBlock(get(_normal,_anaNode), get(_group,_anaNode), (ExecBreakBlock) _anaNode);
        }
        if (_anaNode instanceof ExecContinueBlock) {
            return new AssContinueBlock(get(_normal,_anaNode), get(_group,_anaNode), (ExecContinueBlock) _anaNode);
        }
        if (_anaNode instanceof ExecIfCondition) {
            return new AssIfCondition(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode), (ExecIfCondition) _anaNode);
        }
        if (_anaNode instanceof ExecElseIfCondition) {
            return new AssElseIfCondition(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode), (ExecElseIfCondition) _anaNode);
        }
        if (_anaNode instanceof ExecElseCondition) {
            return new AssElseCondition(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode));
        }
        if (_anaNode instanceof ExecTryEval) {
            return new AssTryEval(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode));
        }
        if (_anaNode instanceof ExecAbstractCatchEval) {
            return new AssCatchEval(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode));
        }
        if (_anaNode instanceof ExecFinallyEval) {
            return new AssFinallyEval(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode));
        }
        if (_anaNode instanceof ExecSwitchPartBlock) {
            return new AssSwitchPartBlock(get(_normal,_anaNode), get(_group,_anaNode), _anaNode instanceof ExecDefaultCondition);
        }
        if (_anaNode instanceof ExecSwitchBlock) {
            return new AssSwitchBlock(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode), (ExecSwitchBlock) _anaNode);
        }
        if (_anaNode instanceof ExecReturnMethod) {
            return new AssReturnMethod(get(_normal,_anaNode), get(_group,_anaNode), (ExecReturnMethod) _anaNode);
        }
        if (_anaNode instanceof ExecThrowing) {
            return new AssThrowing(get(_normal,_anaNode), get(_group,_anaNode), (ExecThrowing) _anaNode);
        }
        if (_anaNode instanceof ExecDeclareVariable) {
            return new AssDeclareVariable(get(_normal,_anaNode), get(_group,_anaNode), get(_finals,_anaNode));
        }
        if (_anaNode instanceof ExecLine) {
            return new AssLine(get(_normal,_anaNode), get(_group,_anaNode),(ExecLine)_anaNode);
        }
        if (_anaNode instanceof ExecEmptyInstruction) {
            return new AssEmptyInstruction(get(_normal,_anaNode), get(_group,_anaNode));
        }
        if (_anaNode instanceof ExecDoBlock) {
            return new AssDoBlock(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode));
        }
        if (_anaNode instanceof ExecDoWhileCondition) {
            return new AssDoWhileCondition(get(_normal,_anaNode), get(_group,_anaNode),(ExecDoWhileCondition)_anaNode);
        }
        if (_anaNode instanceof ExecWhileCondition) {
            return new AssWhileCondition(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode),(ExecWhileCondition)_anaNode);
        }
        if (_anaNode instanceof ExecForEachLoop) {
            return new AssForEach(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode),(ExecForEachLoop)_anaNode);
        }
        if (_anaNode instanceof ExecForEachTable) {
            return new AssForEach(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode),(ExecForEachTable)_anaNode);
        }
        if (_anaNode instanceof ExecForIterativeLoop) {
            return new AssForIterativeLoop(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode),(ExecForIterativeLoop)_anaNode);
        }
        return new AssUnclassedBracedBlock(get(_normal,_anaNode), get(_group,_anaNode));
    }

    private static AssMemberCallingsBlock getAssMemberCalling(BlocksFlags _normal, BlocksFlags _group, ExecBlock _anaNode) {
        if (_anaNode instanceof ExecConstructorBlock) {
            return new AssConstructorBlock(get(_normal,_anaNode),get(_group,_anaNode));
        }
        if (_anaNode instanceof ExecInitBlock) {
            return new AssInit(get(_normal,_anaNode),get(_group,_anaNode));
        }
        return new AssStdMethodBlock(get(_normal,_anaNode), get(_group,_anaNode));
    }
    private static boolean get(BlocksFlags _normal, ExecBlock _anaNode) {
        return _normal.getVal(_anaNode);
    }

    private static String get(BlocksLabels _normal, ExecBlock _anaNode) {
        return _normal.getVal(_anaNode);
    }
}
