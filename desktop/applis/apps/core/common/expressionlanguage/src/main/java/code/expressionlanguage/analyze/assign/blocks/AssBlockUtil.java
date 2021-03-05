package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.BlocksFlags;
import code.expressionlanguage.analyze.BlocksLabels;
import code.expressionlanguage.analyze.blocks.*;


public final class AssBlockUtil {
    private AssBlockUtil() {
    }

    public static AssMemberCallingsBlock getExecutableNodes(BlocksFlags _normal, BlocksFlags _group, BlocksLabels _lab, MemberCallingsBlock _list) {
        AssMemberCallingsBlock m_ = getAssMemberCalling(_normal, _group, _list);
        if (_list.getFirstChild() == null) {
            return m_;
        }
        AbsBk c_ = _list;
        AssBlock ac_ = m_;
        while (c_ != null) {
            AbsBk f_ = c_.getFirstChild();
            if (!(c_ instanceof RootBlock)&&ac_ instanceof AssBracedBlock&&f_ != null) {
                AssBlock af_ = createAssBlock(_normal, _group, _lab, f_);
                ((AssBracedBlock)ac_).appendChild(af_);
                ac_ = af_;
                c_ = f_;
                continue;
            }
            while (true) {
                AbsBk n_ = c_.getNextSibling();
                if (n_ != null) {
                    AssBlock af_ = createAssBlock(_normal, _group, _lab, n_);
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

    public static AssSimStdMethodBlock getSimExecutableNodes(BlocksFlags _normal, BlocksFlags _group, MemberCallingsBlock _list) {
        AssSimStdMethodBlock m_ = getAssSimMemberCalling(_normal, _group, _list);
        if (_list.getFirstChild() == null) {
            return m_;
        }
        AbsBk c_ = _list;
        AssBlock ac_ = m_;
        while (c_ != null) {
            AbsBk f_ = c_.getFirstChild();
            if (!(c_ instanceof RootBlock)&&ac_ instanceof AssBracedBlock&&f_ != null) {
                AssBlock af_ = createAssSimBlock(_normal, _group, f_);
                ((AssBracedBlock)ac_).appendChild(af_);
                ac_ = af_;
                c_ = f_;
                continue;
            }
            while (true) {
                AbsBk n_ = c_.getNextSibling();
                if (n_ != null) {
                    AssBlock af_ = createAssSimBlock(_normal, _group, n_);
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
    public static AssBlock createAssBlock(BlocksFlags _normal, BlocksFlags _group, BlocksLabels _lab, AbsBk _anaNode) {
        if (_anaNode instanceof ForMutableIterativeLoop) {
            return new AssForMutableIterativeLoop(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode),(ForMutableIterativeLoop) _anaNode);
        }
        if (_anaNode instanceof BreakBlock) {
            return new AssBreakBlock(get(_normal,_anaNode), get(_group,_anaNode), (BreakBlock) _anaNode);
        }
        if (_anaNode instanceof ContinueBlock) {
            return new AssContinueBlock(get(_normal,_anaNode), get(_group,_anaNode), (ContinueBlock) _anaNode);
        }
        if (_anaNode instanceof IfCondition) {
            return new AssIfCondition(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode), (IfCondition) _anaNode);
        }
        if (_anaNode instanceof ElseIfCondition) {
            return new AssElseIfCondition(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode), (ElseIfCondition) _anaNode);
        }
        if (_anaNode instanceof ElseCondition) {
            return new AssElseCondition(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode));
        }
        if (_anaNode instanceof TryEval) {
            return new AssTryEval(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode));
        }
        if (_anaNode instanceof AbstractCatchEval) {
            return new AssCatchEval(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode));
        }
        if (_anaNode instanceof FinallyEval) {
            return new AssFinallyEval(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode));
        }
        if (_anaNode instanceof DefaultCondition) {
            return new AssSwitchPartBlock(get(_normal,_anaNode), get(_group,_anaNode), true);
        }
        if (_anaNode instanceof CaseCondition) {
            return new AssSwitchPartBlock(get(_normal,_anaNode), get(_group,_anaNode), false);
        }
        if (_anaNode instanceof SwitchBlock) {
            return new AssSwitchBlock(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode), (SwitchBlock) _anaNode);
        }
        if (_anaNode instanceof ReturnMethod) {
            return new AssReturnMethod(get(_normal,_anaNode), get(_group,_anaNode), (ReturnMethod) _anaNode);
        }
        if (_anaNode instanceof Throwing) {
            return new AssThrowing(get(_normal,_anaNode), get(_group,_anaNode), (Throwing) _anaNode);
        }
        if (_anaNode instanceof DeclareVariable) {
            return new AssDeclareVariable(get(_normal,_anaNode), get(_group,_anaNode));
        }
        if (_anaNode instanceof Line) {
            return new AssLine(get(_normal,_anaNode), get(_group,_anaNode),(Line)_anaNode);
        }
        if (_anaNode instanceof EmptyInstruction) {
            return new AssEmptyInstruction(get(_normal,_anaNode), get(_group,_anaNode));
        }
        if (_anaNode instanceof DoBlock) {
            return new AssDoBlock(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode));
        }
        if (_anaNode instanceof DoWhileCondition) {
            return new AssDoWhileCondition(get(_normal,_anaNode), get(_group,_anaNode),(DoWhileCondition)_anaNode);
        }
        if (_anaNode instanceof WhileCondition) {
            return new AssWhileCondition(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode),(WhileCondition)_anaNode);
        }
        if (_anaNode instanceof ForEachLoop) {
            return new AssForEach(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode),(ForEachLoop)_anaNode);
        }
        if (_anaNode instanceof ForEachTable) {
            return new AssForEach(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode),(ForEachTable)_anaNode);
        }
        if (_anaNode instanceof ForIterativeLoop) {
            return new AssForIterativeLoop(get(_normal,_anaNode), get(_group,_anaNode),get(_lab,_anaNode),(ForIterativeLoop)_anaNode);
        }
        return new AssUnclassedBracedBlock(get(_normal,_anaNode), get(_group,_anaNode));
    }
    public static AssBlock createAssSimBlock(BlocksFlags _normal, BlocksFlags _group, AbsBk _anaNode) {
        if (_anaNode instanceof ForMutableIterativeLoop) {
            return new AssSimForMutableIterativeLoop(get(_normal,_anaNode), get(_group,_anaNode),(ForMutableIterativeLoop) _anaNode);
        }
        if (_anaNode instanceof DeclareVariable) {
            return new AssSimDeclareVariable(get(_normal,_anaNode), get(_group,_anaNode));
        }
        if (_anaNode instanceof Line) {
            return new AssSimLine(get(_normal,_anaNode), get(_group,_anaNode),(Line)_anaNode);
        }
        if (_anaNode instanceof ForIterativeLoop) {
            return new AssSimForIterativeLoop(get(_normal,_anaNode), get(_group,_anaNode),(ForIterativeLoop)_anaNode);
        }
        if (_anaNode instanceof ConditionBlock) {
            return new AssSimBracedBlock(get(_normal,_anaNode), get(_group,_anaNode), ((ConditionBlock) _anaNode).getRoot());
        }
        if (_anaNode instanceof SwitchBlock) {
            return new AssSimBracedBlock(get(_normal,_anaNode), get(_group,_anaNode), ((SwitchBlock) _anaNode).getRoot());
        }
        if (_anaNode instanceof ReturnMethod) {
            return new AssSimBracedBlock(get(_normal,_anaNode), get(_group,_anaNode), ((ReturnMethod) _anaNode).getRoot());
        }
        if (_anaNode instanceof Throwing) {
            return new AssSimBracedBlock(get(_normal,_anaNode), get(_group,_anaNode), ((Throwing) _anaNode).getRoot());
        }
        if (_anaNode instanceof ForEachLoop) {
            return new AssSimBracedBlock(get(_normal,_anaNode), get(_group,_anaNode),((ForEachLoop)_anaNode).getRoot());
        }
        if (_anaNode instanceof ForEachTable) {
            return new AssSimBracedBlock(get(_normal,_anaNode), get(_group,_anaNode),((ForEachTable)_anaNode).getRoot());
        }
        return new AssSimBracedBlock(get(_normal,_anaNode), get(_group,_anaNode),null);
    }

    private static AssMemberCallingsBlock getAssMemberCalling(BlocksFlags _normal, BlocksFlags _group, AbsBk _anaNode) {
        if (_anaNode instanceof ConstructorBlock) {
            return new AssConstructorBlock(get(_normal,_anaNode),get(_group,_anaNode));
        }
        if (_anaNode instanceof InitBlock) {
            return new AssInit(get(_normal,_anaNode),get(_group,_anaNode));
        }
        return new AssStdMethodBlock(get(_normal,_anaNode), get(_group,_anaNode));
    }
    private static AssSimStdMethodBlock getAssSimMemberCalling(BlocksFlags _normal, BlocksFlags _group, AbsBk _anaNode) {
        return new AssSimStdMethodBlock(get(_normal,_anaNode), get(_group,_anaNode));
    }
    private static boolean get(BlocksFlags _normal, AbsBk _anaNode) {
        return _normal.getVal(_anaNode);
    }

    private static String get(BlocksLabels _normal, AbsBk _anaNode) {
        return _normal.getVal(_anaNode);
    }
}
