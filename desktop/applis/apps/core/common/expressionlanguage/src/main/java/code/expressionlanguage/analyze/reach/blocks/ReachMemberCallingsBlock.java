package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class ReachMemberCallingsBlock extends ReachBracedBlock implements FunctionBlock,ReturnableWithSignature {
    protected ReachMemberCallingsBlock(Block _info) {
        super(_info);
    }

    public static ReachMemberCallingsBlock newReachBlocks(MemberCallingsBlock _list) {
        ReachMemberCallingsBlock m_ = ReachBlock.newReachBlock(_list);
        if (_list.getFirstChild() == null) {
            return m_;
        }
        Block c_ = _list;
        ReachBlock ac_ = m_;
        while (c_ != null) {
            Block f_ = c_.getFirstChild();
            if (!(c_ instanceof RootBlock)&&ac_ instanceof ReachBracedBlock&&f_ != null) {
                ReachBlock af_ = ReachBlock.newReachBlock(f_);
                ((ReachBracedBlock)ac_).appendChild(af_);
                ac_ = af_;
                c_ = f_;
                continue;
            }
            while (true) {
                Block n_ = c_.getNextSibling();
                if (n_ != null) {
                    ReachBlock af_ = ReachBlock.newReachBlock(n_);
                    ReachBracedBlock par_ = ac_.getParent();
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

    public final void buildFctInstructionsReadOnly(AnalyzedPageEl _page, AnalyzingEl _anEl) {
        _page.setGlobalOffset(getOffset().getOffsetTrim());
        _page.setOffset(0);
        ReachBlock firstChild_ = getFirstChild();
        _page.setExecDeclareVariable(null);
        _page.setMerged(false);
        _page.setCurrentBlock(getInfo());
        _page.setAnalysisAss(_anEl);
        ReachBlock en_ = this;
        if (firstChild_ == null) {
            _anEl.reach(this);
            abrupt(_anEl);
            setAssignmentAfterCallReadOnly(_anEl, _page);
            _page.getInfosVars().clear();
            return;
        }
        while (true) {
            _page.setCurrentBlock(en_.getInfo());
            _page.setCurrentAnaBlock(en_.getInfo());
            _anEl.putLabel(this);
            if (en_ == this) {
                _anEl.reach(this);
            } else {
                en_.reach(_anEl, _page);
            }
            processUnreachable(_anEl, en_, _page);
            ReachBlock n_ = en_.getFirstChild();
            addParent(_anEl, en_, n_);
            boolean visit_ = true;
            if (en_ != this) {
                visit_ = tryBuildExpressionLanguageReadOnly(en_, _page);
            }
            if (visit_ && n_ != null) {
                en_ = n_;
                continue;
            }
            if (visit_) {
                en_.abrupt(_anEl);
                abrupGroup(_anEl, en_);
            }
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                ReachBracedBlock par_;
                par_ = en_.getParent();
                _page.setCurrentBlock(par_.getInfo());
                _page.setCurrentAnaBlock(par_.getInfo());
                par_.abrupt(_anEl);
                par_.abruptGroup(_anEl);
                if (par_ == this) {
                    setAssignmentAfterCallReadOnly(_anEl, _page);
                    _page.getInfosVars().clear();
                    return;
                }
                en_ = par_;
            }
        }
    }
    public static void buildExec(AnalyzedPageEl _page,MemberCallingsBlock _from,ExecMemberCallingsBlock _dest) {
        ExecBracedBlock blockToWrite_ = _dest;
        ExecFileBlock fileDest_ = _dest.getFile();
        Block firstChild_ = _from.getFirstChild();
        _page.setExecDeclareVariable(null);
        _page.setMerged(false);
        _page.setCurrentBlock(_from);
        IdMap<BracedBlock,ExecBracedBlock> mappingBracedMembers_ = new IdMap<BracedBlock,ExecBracedBlock>();
        mappingBracedMembers_.put(_from,_dest);
        _page.getCoverage().putBlockOperations(_dest,_from);
        Block en_ = _from;
        if (firstChild_ == null) {
            return;
        }
        while (true) {
            _page.setCurrentBlock(en_);
            _page.setCurrentAnaBlock(en_);
            _page.getCoverage().putBlockOperations(en_);
            Block n_ = en_.getFirstChild();
            boolean visit_ = true;
            if (en_ != _from) {
                if (en_ instanceof BreakBlock) {
                    ExecBreakBlock exec_ = new ExecBreakBlock(en_.getOffset(), ((BreakBlock) en_).getLabel());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof CaseCondition) {
                    BracedBlock par_ = en_.getParent();
                    if (!(par_ instanceof SwitchBlock)) {
                        ExecNullCaseCondition exec_ = new ExecNullCaseCondition(en_.getOffset(), ((CaseCondition)en_).getValueOffset());
                        exec_.setFile(fileDest_);
                        blockToWrite_.appendChild(exec_);
                        mappingBracedMembers_.put((BracedBlock) en_,exec_);
                        _page.getCoverage().putBlockOperations(exec_,en_);
                    } else {
                        _page.getCoverage().putBlockOperationsSwitchs(par_,en_);
                        SwitchBlock sw_ = (SwitchBlock) par_;
                        if (!sw_.getInstanceTest().isEmpty()) {
                            if (((CaseCondition) en_).getImportedType().isEmpty()) {
                                ExecNullInstanceCaseCondition exec_ = new ExecNullInstanceCaseCondition(en_.getOffset(), ((CaseCondition) en_).getValueOffset());
                                exec_.setFile(fileDest_);
                                blockToWrite_.appendChild(exec_);
                                mappingBracedMembers_.put((BracedBlock) en_,exec_);
                                _page.getCoverage().putBlockOperations(exec_,en_);
                            } else {
                                ExecInstanceCaseCondition exec_ = new ExecInstanceCaseCondition(en_.getOffset(), ((CaseCondition)en_).getVariableName(), ((CaseCondition) en_).getImportedType(), ((CaseCondition) en_).getValueOffset());
                                exec_.setFile(fileDest_);
                                blockToWrite_.appendChild(exec_);
                                mappingBracedMembers_.put((BracedBlock) en_,exec_);
                                _page.getCoverage().putBlockOperations(exec_,en_);
                            }
                        } else {
                            ElUtil.getExecutableNodes(_page, ((CaseCondition)en_).getRoot());
                            if (((CaseCondition) en_).isBuiltEnum()) {
                                if (((CaseCondition) en_).isNullCaseEnum()) {
                                    ExecNullCaseCondition exec_ = new ExecNullCaseCondition(en_.getOffset(), ((CaseCondition) en_).getValueOffset());
                                    exec_.setFile(fileDest_);
                                    blockToWrite_.appendChild(exec_);
                                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                                    _page.getCoverage().putBlockOperations(exec_,en_);
                                } else {
                                    ExecEnumCaseCondition exec_ = new ExecEnumCaseCondition(en_.getOffset(), ((CaseCondition) en_).getValue(), ((CaseCondition) en_).getValueOffset());
                                    exec_.setFile(fileDest_);
                                    blockToWrite_.appendChild(exec_);
                                    mappingBracedMembers_.put((BracedBlock) en_, exec_);
                                    _page.getCoverage().putBlockOperations(exec_, en_);
                                }
                            } else if (((CaseCondition) en_).getArgument() == null) {
                                ExecBracedBlock exec_ = new ExecUnclassedBracedBlock(en_.getOffset());
                                exec_.setFile(fileDest_);
                                blockToWrite_.appendChild(exec_);
                                mappingBracedMembers_.put((BracedBlock) en_,exec_);
                                _page.getCoverage().putBlockOperations(exec_,en_);
                            } else {
                                ExecBracedBlock exec_;
                                if (!((CaseCondition) en_).getArgument().isNull()) {
                                    exec_ = new ExecStdCaseCondition(en_.getOffset(), ((CaseCondition) en_).getValueOffset(), ((CaseCondition) en_).getArgument());
                                } else {
                                    exec_ = new ExecNullCaseCondition(en_.getOffset(), ((CaseCondition) en_).getValueOffset());
                                }
                                exec_.setFile(fileDest_);
                                blockToWrite_.appendChild(exec_);
                                mappingBracedMembers_.put((BracedBlock) en_,exec_);
                                _page.getCoverage().putBlockOperations(exec_,en_);
                            }
                        }
                    }
                } else if (en_ instanceof CatchEval) {
                    _page.getCoverage().putCatches(en_);
                    ExecCatchEval exec_ = new ExecCatchEval(en_.getOffset(),((CatchEval)en_).getVariableName(), ((CatchEval)en_).getImportedClassName());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof IfCondition) {
                    CustList<ExecOperationNode> opCondition_ = ElUtil.getExecutableNodes(_page, ((Condition)en_).getRoot());
                    ExecCondition exec_ = new ExecIfCondition(en_.getOffset(), ((Condition) en_).getConditionOffset(), ((IfCondition) en_).getLabel(), opCondition_);
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperationsConditions(en_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof ElseIfCondition) {
                    CustList<ExecOperationNode> opCondition_ = ElUtil.getExecutableNodes(_page, ((Condition)en_).getRoot());
                    ExecCondition exec_ = new ExecElseIfCondition(en_.getOffset(), ((Condition) en_).getConditionOffset(), opCondition_);
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperationsConditions(en_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof WhileCondition) {
                    CustList<ExecOperationNode> opCondition_ = ElUtil.getExecutableNodes(_page, ((Condition)en_).getRoot());
                    ExecCondition exec_ = new ExecWhileCondition(en_.getOffset(), ((Condition) en_).getConditionOffset(), ((WhileCondition) en_).getLabel(), opCondition_);
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperationsConditions(en_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof DoWhileCondition) {
                    CustList<ExecOperationNode> opCondition_ = ElUtil.getExecutableNodes(_page, ((Condition)en_).getRoot());
                    ExecCondition exec_ = new ExecDoWhileCondition(en_.getOffset(), ((Condition) en_).getConditionOffset(), opCondition_);
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperationsConditions(en_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof DoBlock) {
                    ExecDoBlock exec_ = new ExecDoBlock(en_.getOffset(),((DoBlock)en_).getLabel());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof ContinueBlock) {
                    ExecContinueBlock exec_ = new ExecContinueBlock(en_.getOffset(), ((ContinueBlock) en_).getLabel());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof DeclareVariable) {
                    ExecDeclareVariable exec_ = new ExecDeclareVariable(en_.getOffset(), ((DeclareVariable) en_).getImportedClassName(),((DeclareVariable)en_).getVariableNames());
                    _page.setExecDeclareVariable(exec_);
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof DefaultCondition) {
                    BracedBlock b_ = en_.getParent();
                    if (!(b_ instanceof SwitchBlock)) {
                        ExecDefaultCondition exec_ = new ExecDefaultCondition(en_.getOffset());
                        exec_.setFile(fileDest_);
                        blockToWrite_.appendChild(exec_);
                        mappingBracedMembers_.put((BracedBlock) en_,exec_);
                        _page.getCoverage().putBlockOperations(exec_,en_);
                    } else {
                        _page.getCoverage().putBlockOperationsSwitchs(b_,en_);
                        SwitchBlock s_ = (SwitchBlock) b_;
                        String instanceTest_ = s_.getInstanceTest();
                        if (instanceTest_.isEmpty()) {
                            ExecDefaultCondition exec_ = new ExecDefaultCondition(en_.getOffset());
                            exec_.setFile(fileDest_);
                            blockToWrite_.appendChild(exec_);
                            mappingBracedMembers_.put((BracedBlock) en_,exec_);
                            _page.getCoverage().putBlockOperations(exec_,en_);
                        } else {
                            ExecInstanceDefaultCondition exec_ = new ExecInstanceDefaultCondition(en_.getOffset(), ((DefaultCondition)en_).getVariableName(), instanceTest_, ((DefaultCondition)en_).getVariableOffset());
                            exec_.setFile(fileDest_);
                            blockToWrite_.appendChild(exec_);
                            mappingBracedMembers_.put((BracedBlock) en_, exec_);
                            _page.getCoverage().putBlockOperations(exec_, en_);
                        }
                    }
                } else if (en_ instanceof ElseCondition) {
                    ExecElseCondition exec_ = new ExecElseCondition(en_.getOffset());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_, exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof Line) {
                    CustList<ExecOperationNode> op_ = ElUtil.getExecutableNodes(_page, ((Line)en_).getRoot());
                    ExecDeclareVariable ex_ = _page.getExecDeclareVariable();
                    if (ex_ != null) {
                        ex_.setImportedClassName(((Line) en_).getImportedClass());
                    }
                    _page.setExecDeclareVariable(null);
                    ExecLine exec_ = new ExecLine(en_.getOffset(), ((Line) en_).getExpressionOffset(),op_);
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof EmptyInstruction) {
                    ExecEmptyInstruction exec_ = new ExecEmptyInstruction(en_.getOffset());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof FinallyEval) {
                    ExecFinallyEval exec_ = new ExecFinallyEval(en_.getOffset());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_, exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof ForEachLoop) {
                    _page.getCoverage().putBlockOperationsLoops(en_);
                    CustList<ExecOperationNode> op_ = ElUtil.getExecutableNodes(_page, ((ForEachLoop)en_).getRoot());
                    ExecForEachLoop exec_ = new ExecForEachLoop(en_.getOffset(), ((ForEachLoop) en_).getLabel(), ((ForEachLoop)en_).getImportedClassName(),
                            ((ForEachLoop)en_).getImportedClassIndexName(), ((ForEachLoop)en_).getVariableName(), ((ForEachLoop)en_).getVariableNameOffset(), ((ForEachLoop)en_).getExpressionOffset(),op_);
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_, exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof ForEachTable) {
                    _page.getCoverage().putBlockOperationsLoops(en_);
                    CustList<ExecOperationNode> op_ = ElUtil.getExecutableNodes(_page, ((ForEachTable)en_).getRoot());
                    ExecForEachTable exec_ = new ExecForEachTable(en_.getOffset(), ((ForEachTable) en_).getLabel(), ((ForEachTable)en_).getImportedClassNameFirst(),
                            ((ForEachTable)en_).getImportedClassNameSecond(),
                            ((ForEachTable)en_).getImportedClassIndexName(), ((ForEachTable)en_).getVariableNameFirst(),
                            ((ForEachTable)en_).getVariableNameSecond(), ((ForEachTable)en_).getExpressionOffset(),op_);
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof ForIterativeLoop) {
                    _page.getCoverage().putBlockOperationsLoops(en_);
                    CustList<ExecOperationNode> init_ = ElUtil.getExecutableNodes(_page, ((ForIterativeLoop)en_).getRootInit());
                    CustList<ExecOperationNode> exp_ = ElUtil.getExecutableNodes(_page, ((ForIterativeLoop)en_).getRootExp());
                    CustList<ExecOperationNode> step_ = ElUtil.getExecutableNodes(_page, ((ForIterativeLoop)en_).getRootStep());
                    ExecForIterativeLoop exec_ = new ExecForIterativeLoop(en_.getOffset(), ((ForIterativeLoop) en_).getLabel(), ((ForIterativeLoop)en_).getImportedClassName(),
                            ((ForIterativeLoop)en_).getImportedClassIndexName(), ((ForIterativeLoop)en_).getVariableName(), ((ForIterativeLoop)en_).getVariableNameOffset(), ((ForIterativeLoop)en_).getInitOffset(),
                            ((ForIterativeLoop)en_).getExpressionOffset(), ((ForIterativeLoop)en_).getStepOffset(), ((ForIterativeLoop)en_).isEq(),init_,exp_,step_);
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof ForMutableIterativeLoop) {
                    CustList<ExecOperationNode> init_;
                    OperationNode rInit_ = ((ForMutableIterativeLoop) en_).getRootInit();
                    if (rInit_ == null) {
                        init_ = new CustList<ExecOperationNode>();
                    } else {
                        init_ = ElUtil.getExecutableNodes(_page, rInit_);
                    }
                    CustList<ExecOperationNode> exp_;
                    OperationNode rExp_ = ((ForMutableIterativeLoop) en_).getRootExp();
                    if (rExp_ == null) {
                        exp_ = new CustList<ExecOperationNode>();
                    } else {
                        exp_ = ElUtil.getExecutableNodes(_page, rExp_);
                    }
                    _page.getCoverage().putBlockOperationsConditions(en_);
                    OperationNode rStep_ = ((ForMutableIterativeLoop) en_).getRootStep();
                    CustList<ExecOperationNode> step_;
                    if (rStep_ == null) {
                        step_ = new CustList<ExecOperationNode>();
                    } else {
                        step_ = ElUtil.getExecutableNodes(_page, rStep_);
                    }
                    ExecForMutableIterativeLoop exec_ = new ExecForMutableIterativeLoop(en_.getOffset(), ((ForMutableIterativeLoop) en_).getLabel(), ((ForMutableIterativeLoop) en_).getImportedClassName(), ((ForMutableIterativeLoop) en_).getImportedClassIndexName(),
                            ((ForMutableIterativeLoop) en_).getVariableNames(), ((ForMutableIterativeLoop) en_).getInitOffset(), ((ForMutableIterativeLoop) en_).getExpressionOffset(), ((ForMutableIterativeLoop) en_).getStepOffset(),
                            init_,exp_,step_);
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof NullCatchEval) {
                    _page.getCoverage().putCatches(en_);
                    ExecNullCatchEval exec_ = new ExecNullCatchEval(en_.getOffset());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof ReturnMethod) {
                    OperationNode r_ = ((ReturnMethod) en_).getRoot();
                    if (r_ == null) {
                        ExecReturnMethod exec_ = new ExecReturnMethod(en_.getOffset(), true, ((ReturnMethod) en_).getExpressionOffset(),null, ((ReturnMethod) en_).getReturnType());
                        exec_.setFile(fileDest_);
                        blockToWrite_.appendChild(exec_);
                        _page.getCoverage().putBlockOperations(exec_,en_);
                    } else {
                        CustList<ExecOperationNode> op_ = ElUtil.getExecutableNodes(_page, r_);
                        ExecReturnMethod exec_ = new ExecReturnMethod(en_.getOffset(), false, ((ReturnMethod) en_).getExpressionOffset(),op_, ((ReturnMethod) en_).getReturnType());
                        exec_.setFile(fileDest_);
                        blockToWrite_.appendChild(exec_);
                        _page.getCoverage().putBlockOperations(exec_,en_);
                    }
                } else if (en_ instanceof SwitchBlock) {
                    Block first_ = en_.getFirstChild();
                    boolean def_ = false;
                    while (first_ != null) {
                        Block elt_ = first_;
                        if (elt_ instanceof DefaultCondition) {
                            def_ = true;
                            first_ = first_.getNextSibling();
                            continue;
                        }
                        first_ = first_.getNextSibling();
                    }
                    _page.getCoverage().putBlockOperationsSwitchs(en_,def_);
                    CustList<ExecOperationNode> op_ = ElUtil.getExecutableNodes(_page, ((SwitchBlock)en_).getRoot());
                    ExecBracedBlock exec_;
                    if (!((SwitchBlock) en_).getInstanceTest().isEmpty()) {
                        exec_ = new ExecInstanceSwitchBlock(en_.getOffset(), ((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_);
                    } else if (((SwitchBlock) en_).isEnumTest()) {
                        exec_ = new ExecEnumSwitchBlock(en_.getOffset(), ((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_);
                    } else {
                        exec_ = new ExecStdSwitchBlock(en_.getOffset(), ((SwitchBlock) en_).getLabel(), ((SwitchBlock) en_).getValueOffset(), op_);
                    }
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof TryEval) {
                    ExecTryEval exec_ = new ExecTryEval(en_.getOffset(), ((TryEval) en_).getLabel());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof Throwing) {
                    CustList<ExecOperationNode> op_ = ElUtil.getExecutableNodes(_page, ((Throwing) en_).getRoot());
                    ExecThrowing exec_ = new ExecThrowing(en_.getOffset(), ((Throwing)en_).getExpressionOffset(),op_);
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                } else if (en_ instanceof UnclassedBracedBlock) {
                    ExecUnclassedBracedBlock exec_ = new ExecUnclassedBracedBlock(en_.getOffset());
                    exec_.setFile(fileDest_);
                    blockToWrite_.appendChild(exec_);
                    mappingBracedMembers_.put((BracedBlock) en_,exec_);
                    _page.getCoverage().putBlockOperations(exec_,en_);
                    visit_= true;
                } else {
                    visit_ = false;
                }
            }
            if (visit_ && en_ instanceof BracedBlock&& n_ != null) {
                blockToWrite_ = mappingBracedMembers_.getVal((BracedBlock) en_);
            }
            if (visit_ && n_ != null) {
                en_ = n_;
                continue;
            }
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                BracedBlock par_;
                par_ = en_.getParent();
                _page.setCurrentBlock(par_);
                _page.setCurrentAnaBlock(par_);
                if (par_ == _from) {
                    return;
                }
                blockToWrite_ = blockToWrite_.getParent();
                en_ = par_;
            }
        }
    }

    public abstract void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page);

    protected static boolean tryBuildExpressionLanguageReadOnly(ReachBlock _block, AnalyzedPageEl _page) {
        if (_block instanceof ReachBuildableElMethod) {
            ((ReachBuildableElMethod)_block).buildExpressionLanguageReadOnly(_page);
            return true;
        }
        return processOther(_block, _page);
    }

    private static boolean processOther(ReachBlock _block, AnalyzedPageEl _page) {
        return _block instanceof ReachUnclassedBracedBlock;
    }

    private static void abrupGroup(AnalyzingEl _anEl, ReachBlock _en) {
        if (_en instanceof ReachBracedBlock) {
            ((ReachBracedBlock) _en).abruptGroup(_anEl);
        }
    }

    private static void addParent(AnalyzingEl _anEl, ReachBlock _en,
                                  ReachBlock _n) {
        if (_en instanceof ReachBracedBlock && _n != null) {
            if (_en instanceof ReachBreakableBlock) {
                _anEl.putLabel(_en,((ReachBreakableBlock)_en).getRealLabel());
            }
        }
    }

    private void processUnreachable(AnalyzingEl _anEl, ReachBlock _en, AnalyzedPageEl _page) {
        if (!(_en instanceof ReachRootBlock)&&!_anEl.isReachable(_en)) {
            //error
            FoundErrorInterpret deadCode_ = new FoundErrorInterpret();
            deadCode_.setFileName(getFile().getFileName());
            deadCode_.setIndexFile(_en.getOffset().getOffsetTrim());
            //all header expression
            deadCode_.buildError(_page.getAnalysisMessages().getDeadCode(),
                    getPseudoSignature(_page));
            _page.addLocError(deadCode_);
            _en.getErrorsBlock().add(deadCode_.getBuiltError());
            _en.setReachableError(true);
        }
    }

    public String getPseudoSignature(AnalyzedPageEl _page) {
        return getSignature(_page);
    }

}
