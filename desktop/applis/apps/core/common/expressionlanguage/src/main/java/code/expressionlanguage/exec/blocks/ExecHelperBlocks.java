package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractInitPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ExecHelperBlocks {
    private ExecHelperBlocks() {
    }

    public static AbstractStask hasBlockBreak(StackCall _stackCall, AbstractPageEl _ip, String _label) {
        AbstractStask bl_ = _ip.tryGetLastStack();
        if (bl_ == null) {
            _stackCall.nullReadWrite();
            return null;
        }
        if (_label.isEmpty()) {
            if (bl_ instanceof LoopBlockStack) {
                ExecBlock forLoopLoc_ = ((LoopBlockStack)bl_).getExecBlock();
                _ip.setBlock(forLoopLoc_);
                finishLoop(((LoopBlockStack)bl_).getContent());
                return null;
            }
            if (bl_ instanceof SwitchBlockStack) {
                ExecBlock forLoopLoc_ = ((SwitchBlockStack)bl_).getBlock();
                _ip.setBlock(forLoopLoc_);
                return null;
            }
            return bl_;
        }
        if (StringUtil.quickEq(_label, bl_.getLabel())){
            if (bl_ instanceof LoopBlockStack) {
                ExecBlock forLoopLoc_ = ((LoopBlockStack)bl_).getExecBlock();
                _ip.setBlock(forLoopLoc_);
                finishLoop(((LoopBlockStack)bl_).getContent());
            }
            if (bl_ instanceof IfBlockStack) {
                ExecBlock forLoopLoc_ = ((IfBlockStack)bl_).getLastBlock();
                _ip.setBlock(forLoopLoc_);
            }
            if (bl_ instanceof TryBlockStack) {
                _ip.setBlock(((TryBlockStack)bl_).getBlock());
            }
            if (bl_ instanceof SwitchBlockStack) {
                ExecBlock forLoopLoc_ = ((SwitchBlockStack)bl_).getBlock();
                ((SwitchBlockStack) bl_).enter();
                _ip.setBlock(forLoopLoc_);
            }
            return null;
        }
        return bl_;
    }

    public static AbstractStask hasBlockContinue(StackCall _stackCall, AbstractPageEl _ip, String _label) {
        AbstractStask bl_ = _ip.tryGetLastStack();
        if (bl_ == null) {
            _stackCall.nullReadWrite();
            return null;
        }
        if (bl_ instanceof LoopBlockStack) {
            ExecBracedBlock br_ = ((LoopBlockStack)bl_).getExecBlock();
            if (_label.isEmpty()||StringUtil.quickEq(_label, bl_.getLabel())){
                _ip.setBlock(br_);
                removeLocalVarsLoop(_ip, br_);
                ((LoopBlockStack) bl_).getContent().setEvaluatingKeepLoop(true);
                return null;
            }
        }
        return bl_;
    }

    public static void setVisitedDefault(ContextEl _cont, StackCall _stack, ExecDefaultCondition _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        AbstractStask abstractStask_ = ip_.tryGetLastStack();
        if (!(abstractStask_ instanceof SwitchBlockStack)) {
            _stack.nullReadWrite();
            return;
        }
        if (((SwitchBlockStack) abstractStask_).isEntered()) {
            ip_.setBlock(_block.getFirstChild());
            ((SwitchBlockStack) abstractStask_).setCurrentVisitedBlock(_block);
            return;
        }
        ip_.globalOffset(_block.getOff());
        if (checkBp(_stack,ip_,_block)) {
            return;
        }
        ExecBlock ch_ = _block.getFirstChild();
        String type_ = _stack.formatVarType(((SwitchBlockStack) abstractStask_).getInstanceTest());
        String var_ = _block.getVariableName();
        if (!var_.isEmpty()) {
            ip_.putValueVar(var_, LocalVariable.newLocalVariable(((SwitchBlockStack) abstractStask_).getValue(), type_));
        }
        entered(_cont, _stack, _block, (SwitchBlockStack) abstractStask_, new ExecResultCase(ConditionReturn.YES, _block,0), ch_);
    }
    public static void setVisitedCase(ContextEl _cont, StackCall _stack, ExecBracedBlock _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        AbstractStask abstractStask_ = ip_.tryGetLastStack();
        if (!(abstractStask_ instanceof SwitchBlockStack)) {
            _stack.nullReadWrite();
            return;
        }
        if (((SwitchBlockStack) abstractStask_).isEntered()) {
            ip_.setBlock(_block.getFirstChild());
            ((SwitchBlockStack) abstractStask_).setCurrentVisitedBlock(_block);
            return;
        }
        ExecResultCase res_ = procTypeVar(_cont, _stack, _block, (ExecAbstractCaseCondition) _block, ((SwitchBlockStack) abstractStask_).getValue(), false);
        if (res_.getCondition() == ConditionReturn.CALL_EX){
            return;
        }
        if (res_.getCondition() == ConditionReturn.YES){
            entered(_cont, _stack, _block, (SwitchBlockStack) abstractStask_, res_, _block.getFirstChild());
            return;
        }
        ExecBlock after_ = ((SwitchBlockStack) abstractStask_).next(_block);
        if (after_ == null) {
            ExecListLastBkSw infos_ = ((SwitchBlockStack) abstractStask_).getInfos();
            ExecBracedBlock de_ = infos_.getFirstDef();
            if (de_ != null) {
                ip_.setBlock(de_);
                return;
            }
            coverSw(_cont, _stack, (SwitchBlockStack) abstractStask_, null);
            ip_.setBlock(((SwitchBlockStack) abstractStask_).getBlock());
            return;
        }
        ip_.setBlock(after_);
    }

    private static void entered(ContextEl _cont, StackCall _stack, ExecBracedBlock _block, SwitchBlockStack _abs, ExecResultCase _res, ExecBlock _c) {
        _abs.enter();
        AbstractPageEl ip_ = _stack.getLastPage();
        coverSw(_cont, _stack, _abs, _res);
        ip_.setBlock(_c);
        _abs.setCurrentVisitedBlock(_block);
    }

    private static void coverSw(ContextEl _cont, StackCall _stack, SwitchBlockStack _abs, ExecResultCase _res) {
        if (_abs.getBlock() instanceof ExecAbstractSwitchMethod) {
            _cont.getCoverage().passSwitchMethod(_res, new Argument(_abs.getValue()), _stack);
        }
        if (_abs.getBlock() instanceof ExecAbstractSwitchBlock) {
            _cont.getCoverage().passSwitch(_abs.getBlock(), _res, new Argument(_abs.getValue()), _stack);
        }
    }

    public static void processEnt(ExecBracedBlock _block, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        AbstractStask ts_ = ip_.tryGetLastStack();
        if (!(ts_ instanceof EnteredStack)) {
            _stackCall.nullReadWrite();
            return;
        }
        ((EnteredStack)ts_).setCurrentVisitedBlock(_block);
        if (((EnteredStack)ts_).isEntered()) {
            processBlockAndRemove(_block, _stackCall);
            return;
        }
        if (checkBp(_stackCall,ip_,_block)) {
            return;
        }
        enterIfBlock(_block, ip_, (EnteredStack)ts_);
    }

    private static void enterIfBlock(ExecBracedBlock _block, AbstractPageEl _ip, EnteredStack _e) {
        _ip.setBlock(_block.getFirstChild());
        _e.setEntered(true);
    }

    public static void processIf(ContextEl _cont, String _label, ExecCondition _cond, StackCall _stackCall, ExecOperationNodeListOff _condition) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (ip_.matchStatement(_cond)) {
            processBlockAndRemove(_cond, _stackCall);
            return;
        }
        ConditionReturn assert_ = evaluateConditionBas(_cont, _stackCall, _cond, _condition);
        if (assert_ == ConditionReturn.CALL_EX) {
            return;
        }
        ExecBracedBlock lastBlock_ = _cond;
        ExecBlock n_ = _cond.getNextSibling();
        while (isNextIfParts(n_)) {
            lastBlock_ = (ExecBracedBlock) n_;
            n_ = n_.getNextSibling();
        }
        IfBlockStack if_ = new IfBlockStack(_cond,lastBlock_);
        if_.setLabel(_label);
        if_.setCurrentVisitedBlock(_cond);
        ip_.addBlock(if_);
        if (assert_ == ConditionReturn.YES) {
            enterIfBlock(_cond, ip_, if_);
        } else {
            ExecBlock next_ = _cond.getNextSibling();
            if (isNextIfParts(next_)) {
                ip_.setBlock(next_);
            }
        }
    }
    public static void processElseIf(ContextEl _cont, ExecCondition _cond, StackCall _stackCall, ExecOperationNodeListOff _condition) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        AbstractStask if_ = ip_.tryGetLastStack();
        if (!(if_ instanceof IfBlockStack)) {
            _stackCall.nullReadWrite();
            return;
        }
        ((EnteredStack)if_).setCurrentVisitedBlock(_cond);
        if (!((EnteredStack)if_).isEntered()) {
            ConditionReturn assert_ = evaluateConditionBas(_cont, _stackCall, _cond, _condition);
            if (assert_ == ConditionReturn.CALL_EX) {
                return;
            }
            if (assert_ == ConditionReturn.YES) {
                enterIfBlock(_cond, ip_, (EnteredStack)if_);
                return;
            }
        }
        ExecBlock next_ = _cond.getNextSibling();
        if (isNextIfParts(next_)) {
            ip_.setBlock(next_);
            return;
        }
        processBlockAndRemove(_cond, _stackCall);
    }

    public static void processElse(ExecBracedBlock _cond, StackCall _stackCall) {
        processEnt(_cond, _stackCall);
    }

    public static void processDoWhile(ContextEl _cont, ExecCondition _cond, StackCall _stackCall, ExecOperationNodeListOff _condition) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        AbstractStask l_ = ip_.tryGetLastStack();
        if (!(l_ instanceof LoopBlockStack)) {
            _stackCall.nullReadWrite();
            return;
        }
        ((LoopBlockStack) l_).getContent().setEvaluatingKeepLoop(true);
        ConditionReturn keep_ = evaluateConditionBas(_cont, _stackCall, _cond, _condition);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            finishLoop(((LoopBlockStack) l_).getContent());
        } else {
            ((LoopBlockStack) l_).getContent().setEvaluatingKeepLoop(false);
        }
        ip_.setBlock(_cond.getPreviousSibling());
    }

    public static void processBlockAndRemove(ExecBlock _bl, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (ip_.noBlock()) {
            _stackCall.nullReadWrite();
            return;
        }
        ip_.removeLastBlock();
        processBlock(_stackCall, _bl);
    }

    public static boolean isNextIfParts(ExecBlock _n) {
        return _n instanceof ExecElseIfCondition || _n instanceof ExecElseCondition;
    }

    public static void processWhile(ContextEl _cont, StackCall _stack, String _label, ExecWhileCondition _execLoop, ExecOperationNodeListOff _condition) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(_execLoop);
        if (c_ != null) {
            processVisitedLoop(c_, _execLoop, _execLoop, _cont, _stack, ip_);
            return;
        }
        ConditionReturn res_ = evaluateConditionBas(_cont, _stack, _execLoop, _condition);
        afterCond(_stack, _label, _execLoop, res_);
    }

    private static void afterCond(StackCall _stack, String _label, ExecBracedBlock _execLoop, ConditionReturn _res) {
        if (_res == ConditionReturn.CALL_EX) {
            return;
        }
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack l_ = new LoopBlockStack(_execLoop);
        l_.setLabel(_label);
        boolean finished_ = _res == ConditionReturn.NO;
        l_.getContent().setFinished(finished_);
        ip_.addBlock(l_);
        visitOrFinish(l_,_execLoop, _stack, ip_);
    }

    public static void processLastElementLoopWhile(ContextEl _conf, LoopBlockStack _l, StackCall _stack, ExecWhileCondition _loop, ExecOperationNodeListOff _condition) {
        _l.getContent().setEvaluatingKeepLoop(true);
        ConditionReturn keep_ = evaluateConditionBas(_conf, _stack, _loop, _condition);
        afterConditLoop(keep_, _l.getContent(),_stack,_loop);
    }

    private static void afterConditLoop(ConditionReturn _keep, LoopBlockStackContent _content, StackCall _stack, ExecBracedBlock _braced) {
        if (_keep == ConditionReturn.CALL_EX) {
            return;
        }
        if (_keep == ConditionReturn.NO) {
            finishLoop(_content);
        } else {
            goToFirstBlock(_content,_braced, _stack.getLastPage());
        }
    }

    private static void processLastElementLoopDo(StackCall _stack, ExecDoBlock _loop) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.setBlock(_loop.getNextSibling());
    }

    public static void processDo(ContextEl _cont, StackCall _stack, String _label, ExecDoBlock _loop) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(_loop);
        if (c_ != null) {
            if (!c_.getContent().isEvaluatingKeepLoop() && !c_.getContent().isFinished() && checkBp(_stack,ip_, _loop)) {
                return;
            }
            processVisitedLoop(c_, _loop, _loop.getNextSibling(), _cont, _stack, ip_);
            return;
        }
        if (checkBp(_stack,ip_, _loop)) {
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack(_loop);
        l_.setLabel(_label);
        ip_.addBlock(l_);
        goToFirstBlock(l_.getContent(), _loop,ip_);
    }

    public static void processTry(StackCall _stack, String _label, ExecTryEval _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        AbstractStask i_ = ip_.tryGetLastStack();
        if (i_ instanceof TryBlockStack && ((TryBlockStack)i_).getBlock() == _block) {
            ExecBracedBlock l_ = ((TryBlockStack) i_).getLastBlock();
            if (l_ instanceof ExecElseCondition) {
                ip_.setBlock(l_);
                return;
            }
            processBlockAndRemove(l_, _stack);
            return;
        }
        ip_.globalOffset(_block.getOff());
        if (checkBp(_stack,ip_, _block)) {
            return;
        }
        ExecBlock n_ = _block.getNextSibling();
        ExecBracedBlock last_ = null;
        while (isNextTryParts(n_)) {
            last_ = (ExecBracedBlock) n_;
            n_ = n_.getNextSibling();
        }
        TryBlockStack tryStack_ = new TryBlockStack(last_,_block);
        tryStack_.setLabel(_label);
        tryStack_.setCurrentVisitedBlock(_block);
        ip_.addBlock(tryStack_);
        ip_.setBlock(_block.getFirstChild());
    }

    public static void processCatch(ContextEl _cont, StackCall _stackCall, ExecAbstractCatchEval _cond) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        AbstractStask if_ = ip_.tryGetLastStack();
        if (!(if_ instanceof TryBlockStack)) {
            _stackCall.nullReadWrite();
            return;
        }
        ((TryBlockStack)if_).setCurrentVisitedBlock(_cond);
        if (((TryBlockStack) if_).isEnteredCatch()) {
            ExecBlock next_ = _cond.getNextSibling();
            if (isNextTryParts(next_)) {
                ip_.setBlock(next_);
                return;
            }
            processBlockAndRemove(_cond, _stackCall);
            return;
        }
        Struct exc_ = Argument.getNull(((TryBlockStack) if_).getException());
        ExecResultCase assert_ = procTypeVar(_cont, _stackCall, _cond, _cond, exc_, _cond.isCatchAll());
        if (assert_.getCondition() == ConditionReturn.CALL_EX) {
            return;
        }
        if (assert_.getCondition() == ConditionReturn.YES) {
            ((TryBlockStack) if_).setCalling(null);
            enterGuardCatchBlock(_cond, ip_, (TryBlockStack) if_);
            _cont.getCoverage().passCatches(exc_,assert_, _stackCall);
            return;
        }
        removeVar(_cond.getContent(), _stackCall);
        ExecBlock next_ = _cond.getNextSibling();
        if (isNextTryParts(next_)) {
            ip_.setBlock(next_);
            return;
        }
        _stackCall.setCallingState(new CustomFoundExc(exc_));
    }

    private static void removeVar(ExecFilterContent _cond, StackCall _stackCall) {
        String v_ = _cond.getVariableName();
        if (!v_.isEmpty()) {
            _stackCall.getLastPage().removeRefVar(v_);
        }
    }

    private static void enterGuardCatchBlock(ExecBracedBlock _block, AbstractPageEl _ip, TryBlockStack _ts) {
        _ts.setEnteredCatch(true);
        _ip.setBlock(_block.getFirstChild());
    }

    private static boolean isNextTryParts(ExecBlock _n) {
        return _n instanceof ExecAbstractCatchEval || _n instanceof ExecElseCondition;
    }

    public static void processSwitch(ContextEl _cont, StackCall _stack, String _label, ExecOperationNodeListOff _value, ExecAbstractSwitchBlock _bl) {
        AbstractPageEl ip_ = _stack.getLastPage();
        if (ip_.matchStatement(_bl)) {
            processBlockAndRemove(_bl, _stack);
            return;
        }
        ip_.globalOffset(_value.getOffset());
        Argument arg_ = tryToCalculate(_cont, IndexConstants.FIRST_INDEX, _stack, _value.getList(), 0, _bl,_value.getEnd());
        if (_stack.stopAt(_cont)) {
            return;
        }
        ip_.clearCurrentEls();
        _bl.processCase(_label,arg_, _stack);
    }

    public static void processLastElementLoopMutable(ContextEl _conf, LoopBlockStack _l, StackCall _stack, ExecOperationNodeListOff _step, StringList _variableNames, ExecOperationNodeListOff _exp, ExecForMutableIterativeLoop _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        _l.getContent().setEvaluatingKeepLoop(true);
        ip_.globalOffset(_step.getOffset());
        tryToCalculate(_conf,IndexConstants.FIRST_INDEX,_stack,_step.getList(),0, _block,_step.getEnd());
        if (_stack.stopAt(_conf)) {
            return;
        }
        if (ip_.sizeEl() <= IndexConstants.SECOND_INDEX) {
            for (String v : _variableNames) {
                ExecVariableTemplates.incrIndexLoop(_conf,v, -1, ip_.getCache(), ip_.getVars(), _stack);
            }
        }
        ConditionReturn keep_ = evaluateCondition(_conf, _stack, _exp, _block);
        afterConditLoop(keep_, _l.getContent(),_stack,_block);
    }

    public static void processMutableFor(ContextEl _cont, StackCall _stack, ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, String _label, StringList _variableNames, ExecForMutableIterativeLoop _bl) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(_bl);
        if (c_ != null) {
            processVisitedLoop(c_, _bl, _bl, _cont, _stack, ip_);
            return;
        }
        ip_.globalOffset(_init.getOffset());
        if (ip_.isEmptyEl()) {
            String formatted_ = _stack.formatVarType(_bl.getImportedClassName());
            Struct struct_ = ExecClassArgumentMatching.defaultValue(formatted_, _cont);
            for (String v: _variableNames) {
                LoopVariable lv_ = new LoopVariable();
                lv_.setIndexClassName(_bl.getImportedClassIndexName());
                ip_.getVars().put(v, lv_);
                ip_.putValueVar(v, LocalVariable.newLocalVariable(struct_,formatted_));
            }
        }
        tryToCalculate(_cont,IndexConstants.FIRST_INDEX,_stack,_init.getList(),0, _bl, _init.getEnd());
        if (_stack.stopAt(_cont)){
            return;
        }
        ConditionReturn res_ = evaluateCondition(_cont, _stack, _exp, _bl);
        afterCond(_stack, _label, _bl, res_);
    }

    private static ConditionReturn evaluateCondition(ContextEl _context, StackCall _stackCall, ExecOperationNodeListOff _list, ExecForMutableIterativeLoop _block) {
        return evaluateConditionBas(_context, IndexConstants.SECOND_INDEX,_stackCall,_block,_list);
    }

    public static Argument tryToCalculate(ContextEl _context, int _index, StackCall _stackCall, CustList<ExecOperationNode> _list, int _offset, ExecBlock _coveredBlock, int _end) {
        ArgumentsPair argumentsPair_ = tryToCalculatePair(_context, _index, _stackCall, _list, _offset, _coveredBlock, _end);
        return ExpressionLanguage.getNullable(argumentsPair_);
    }

    public static ArgumentsPair tryToCalculatePair(ContextEl _context, int _index, StackCall _stackCall, CustList<ExecOperationNode> _list, int _offset, ExecBlock _coveredBlock, int _end) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        ExpressionLanguageBp o_ = ip_.eltBp(_stackCall,_index,_list,_coveredBlock);
        ExpressionLanguage exp_ = o_.getExpressionLanguage();
        if (o_.getDiff() == 1){
            return null;
        }
        ArgumentsPair ag_ = ExpressionLanguage.tryToCalculatePair(_context, exp_, _offset, _stackCall);
        if (_stackCall.stopAt(_context)) {
            return null;
        }
        if (ip_.sizeEl() == _index + 1&&_stackCall.getStopper().isStopAtExcMethod()) {
            if (_end < 0) {
                return ag_;
            }
            int g_ = ip_.getGlobalOffset();
            int of_ = ip_.getOffset();
            ip_.globalOffset(_end);
            if (_stackCall.getStopper().isStopAtRef(_context,_stackCall)) {
                ip_.setBkup(g_);
                ip_.setBkupOff(of_);
                return null;
            }
            ip_.setGlobalOffset(ip_.getBkup());
            ip_.setOffset(ip_.getBkupOff());
        }
        return ag_;
    }

    private static ConditionReturn evaluateConditionBas(ContextEl _context, StackCall _stackCall, ExecBlock _execCondition, ExecOperationNodeListOff _condition) {
        return evaluateConditionBas(_context, IndexConstants.FIRST_INDEX, _stackCall, _execCondition, _condition);
    }
    private static ConditionReturn evaluateConditionBas(ContextEl _context, int _index, StackCall _stackCall, ExecBlock _execCondition, ExecOperationNodeListOff _condition) {
        AbstractPageEl last_ = _stackCall.getLastPage();
        last_.globalOffset(_condition.getOffset());
        Argument arg_ = tryToCalculate(_context, _index, _stackCall, _condition.getList(), 0, _execCondition,_condition.getEnd());
        if (_stackCall.stopAt(_context)) {
            return ConditionReturn.CALL_EX;
        }
        last_.clearCurrentEls();
        if (!_condition.getList().isEmpty()) {
            _context.getCoverage().passConditions(_execCondition, arg_, _condition.getList().last(), _stackCall);
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    private static ExecResultCase procTypeVar(ContextEl _cont, StackCall _stack, ExecBracedBlock _br, ExecWithFilterContent _in, Struct _arg, boolean _all) {
        AbstractPageEl lastPage_ = _stack.getLastPage();
        lastPage_.globalOffset(_in.getContent().getOffset());
        if (lastPage_.eltBp(_stack,0,new CustList<ExecOperationNode>(),_br).getDiff() == 1) {
            return new ExecResultCase(ConditionReturn.CALL_EX, _br, -1);
        }
        int index_ = first(1, _cont, _stack, _in.getContent(), _arg, _all);
        if (index_ < 0) {
            lastPage_.clearCurrentEls();
            return new ExecResultCase(ConditionReturn.NO, _br, index_);
        }
        ExecOperationNodeListOff exp_ = _in.getExp();
        CustList<ExecOperationNode> list_ = exp_.getList();
        int offset_ = exp_.getOffset();
        lastPage_.globalOffset(offset_);
        int e_;
        if (list_.isEmpty()) {
            e_ = -1;
        } else {
            e_ = exp_.getEnd();
        }
        Argument visit_ = ExecHelperBlocks.tryToCalculate(_cont, 1, _stack, list_, 0, _br,e_);
        if (_stack.stopAt(_cont)) {
            String v_ = _in.getContent().getVariableName();
            if (!v_.isEmpty() && !_stack.calls()) {
                _stack.getLastPage().removeRefVar(v_);
            }
            return new ExecResultCase(ConditionReturn.CALL_EX, _br, index_);
        }
        _stack.getLastPage().clearCurrentEls();
        if (BooleanStruct.isFalse(visit_.getStruct())) {
            String v_ = _in.getContent().getVariableName();
            if (!v_.isEmpty()) {
                _stack.getLastPage().removeRefVar(v_);
            }
            return new ExecResultCase(ConditionReturn.NO, _br, index_);
        }
        return new ExecResultCase(ConditionReturn.YES, _br, index_);
    }

    public static int first(int _limit,ContextEl _cont, AbstractStackCall _stack, ExecFilterContent _in, Struct _arg, boolean _all) {
        if (_all) {
            if (_stack.sizeElLast() == _limit) {
                putVar(_stack, _in, _cont.getStandards().getCoreNames().getAliasObject(), _arg);
            }
            return 0;
        }
        int m_ = _in.getList().match(_arg, _cont);
        if (m_ > -1) {
            return m_;
        }
        String cl_ = _in.getImportedClassName();
        if (!cl_.isEmpty()) {
            if (_arg == NullStruct.NULL_VALUE) {
                return -1;
            }
            if (_stack.sizeElLast() == _limit) {
                String name_ = _stack.formatVarType(cl_);
                if (ExecInherits.safeObject(name_, _arg.getClassName(_cont), _cont) != ErrorType.NOTHING) {
                    return -1;
                }
                putVar(_stack, _in, name_, _arg);
            }
            return 0;
        }
        return -1;
    }

    public static boolean firstMatch(ContextEl _cont, AbstractStackCall _stack, ExecFilterContent _in, Struct _arg, boolean _all) {
        if (_all) {
            return true;
        }
        int m_ = _in.getList().match(_arg, _cont);
        if (m_ > -1) {
            return true;
        }
        String cl_ = _in.getImportedClassName();
        if (!cl_.isEmpty()) {
            if (_arg == NullStruct.NULL_VALUE) {
                return false;
            }
            String name_ = _stack.formatVarType(cl_);
            return ExecInherits.safeObject(name_, _arg.getClassName(_cont), _cont) == ErrorType.NOTHING;
        }
        return false;
    }
    private static void putVar(AbstractStackCall _stack, ExecFilterContent _in, String _type, Struct _arg) {
        String varName_ = _in.getVariableName();
        if (!varName_.isEmpty()) {
            _stack.putVar(varName_, LocalVariable.newLocalVariable(_arg, _type));
        }
    }

    public static void processForEach(ContextEl _cont, StackCall _stack, String _label, ExecOperationNodeListOff _expression, ExecAbstractForEachLoop _bl) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(_bl);
        if (c_ != null) {
            processVisitedLoop(c_, _bl, _bl, _cont, _stack, ip_);
            return;
        }
        Struct its_ = processLoopForEach(_cont, _stack, _expression, _bl);
        if (_stack.stopAt(_cont)) {
            return;
        }
        LoopBlockStack l_ = _bl.newLoopBlockStack(_cont, _label,its_, _stack);
        if (l_ == null) {
            return;
        }
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.getContent().setEvaluatingKeepLoop(true);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setIndexClassName(_bl.getImportedClassIndexName());
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(_bl.getVariable().getName(), lv_);
        _bl.checkIfNext(_cont, l_, _stack);
    }

    private static Struct processLoopForEach(ContextEl _conf, StackCall _stackCall, ExecOperationNodeListOff _expression, ExecBlock _coveredBlock) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        ip_.globalOffset(_expression.getOffset());
        Argument arg_ = tryToCalculate(_conf, IndexConstants.FIRST_INDEX, _stackCall, _expression.getList(), 0, _coveredBlock, _expression.getEnd());
        if (_stackCall.stopAt(_conf)) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();

    }

    private static void processLastElementLoopLoop(ContextEl _conf, LoopBlockStack _l, StackCall _stack, ExecAbstractForEachLoop _loop) {
        _l.getContent().setEvaluatingKeepLoop(true);
        ConditionReturn hasNext_ = _loop.hasNext(_conf,_l, _stack);
        incrOrFinishLoop(_loop,_conf, hasNext_,_l, _stack);
    }

    static void incrOrFinishLoop(ExecAbstractForEachLoop _loop,ContextEl _cont, ConditionReturn _hasNext, LoopBlockStack _l, StackCall _stackCall) {
        if (_hasNext == ConditionReturn.CALL_EX) {
            return;
        }
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (_hasNext == ConditionReturn.NO) {
            ip_.clearCurrentEls();
            _cont.getCoverage().passLoop(_loop, new Argument(BooleanStruct.of(false)), _stackCall);
            finishLoop(_l.getContent());
            return;
        }
        _cont.getCoverage().passLoop(_loop, new Argument(BooleanStruct.of(true)), _stackCall);
        incrementLoopLoop(_loop,_cont, _l, _stackCall);
    }

    private static void finishLoop(LoopBlockStackContent _content) {
        _content.setEvaluatingKeepLoop(false);
        _content.setFinished(true);
    }

    static void incrementLoopLoop(ExecAbstractForEachLoop _bk, ContextEl _conf, LoopBlockStack _l, StackCall _stackCall) {
        AbstractPageEl abs_ = _stackCall.getLastPage();

        abs_.globalOffset(_bk.getVariable().getOffset());
        Argument arg_ = _bk.retrieveValue(_conf,_l, _stackCall);
        if (_stackCall.stopAt(_conf)) {
            return;
        }
        abs_.clearCurrentEls();
        if (!(_bk instanceof ExecForEachRefArray)) {
            String className_ = _stackCall.formatVarType(_bk.getImportedClassName());
            _stackCall.getLastPage().getRefParams().set(_bk.getVariable().getName(),new VariableWrapper(LocalVariable.newLocalVariable(arg_.getStruct(),className_)));
        }
        ExecVariableTemplates.setWrapValue(_conf, _bk.getVariable().getName(), arg_,-1, abs_.getCache(), abs_.getRefParams(), _stackCall);
        ExecVariableTemplates.incrIndexLoop(_conf, _bk.getVariable().getName(), -1, abs_.getCache(), abs_.getVars(), _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return;
        }
        goToFirstBlock(_l.getContent(), _bk, abs_);
    }

    public static ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall, String _locName, CustList<ExecOperationNode> _el, ExecBlock _coveredBlock) {
        _stackCall.getLastPage().putInternVars(_locName, _l.getContent().getStructIterator(), _conf);
        Argument arg_ = tryToCalculate(_conf, IndexConstants.FIRST_INDEX, _stackCall, _el, 0, _coveredBlock,-1);
        if (_stackCall.stopAt(_conf)) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    public static LoopBlockStack newLoopBlockStackIt(ContextEl _cont, String _label, Struct _its, StackCall _stack, ExecForEachIterable _block) {
        if (_its == NullStruct.NULL_VALUE) {
            String npe_ = _cont.getStandards().getContent().getCoreNames().getAliasNullPe();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, npe_, _stack)));
            return null;
        }
        String locName_ = _cont.getClasses().getIteratorVarCust();
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.putInternVars(locName_, _its, _cont);
        Argument arg_ = tryToCalculate(_cont, IndexConstants.SECOND_INDEX, _stack, _cont.getClasses().getExpsIteratorCust(), 0, _block,-1);
        if (_stack.stopAt(_cont)) {
            return null;
        }
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Struct iterStr_ = arg_.getStruct();
        LoopBlockStack l_ = new LoopBlockStack(_block);
        l_.setLabel(_label);
        l_.getContent().setIndex(-1);
        l_.getContent().setStructIterator(iterStr_);
        l_.getContent().setMaxIteration(length_);
        l_.getContent().setContainer(_its);
        return l_;
    }

    public static Argument retrieveValueIt(ContextEl _conf, LoopBlockStack _l, StackCall _stack, ExecAbstractForEachLoop _coveredBlock) {
        String locName_ = _conf.getClasses().getNextVarCust();
        AbstractPageEl abs_ = _stack.getLastPage();
        abs_.putInternVars(locName_, _l.getContent().getStructIterator(), _conf);
        return tryToCalculate(_conf,IndexConstants.SECOND_INDEX,_stack, _conf.getClasses().getExpsNextCust(),0, _coveredBlock,-1);
    }

    private static void processLastElementLoopTable(ContextEl _conf, LoopBlockStack _l, StackCall _stack, ExecForEachTable _block) {
        _l.getContent().setEvaluatingKeepLoop(true);
        ConditionReturn has_ = iteratorHasNextTable(_conf, _l, _stack, _block);
        if (has_ == ConditionReturn.CALL_EX) {
            return;
        }
        boolean hasNext_ = has_ == ConditionReturn.YES;

        if (hasNext_) {
            _conf.getCoverage().passLoop(_block, new Argument(BooleanStruct.of(true)), _stack);
            incrementLoopTable(_conf, _l, _stack, _block);
        } else {
            _conf.getCoverage().passLoop(_block, new Argument(BooleanStruct.of(false)), _stack);
            _stack.getLastPage().clearCurrentEls();
            finishLoop(_l.getContent());
        }
    }

    public static void processTable(ContextEl _cont, StackCall _stack, String _label, ExecVariableName _variableNameFirst, ExecVariableName _variableNameSecond, ExecOperationNodeListOff _expression, ExecForEachTable _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(_block);
        if (c_ != null) {
            processVisitedLoop(c_, _block, _block, _cont, _stack, ip_);
            return;
        }
        Struct its_ = processLoop(_expression,_cont, _stack,_block);
        if (_stack.stopAt(_cont)) {
            return;
        }
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Classes cls_ = _cont.getClasses();
        String locName_ = cls_.getIteratorTableVarCust();
        _stack.getLastPage().putInternVars(locName_, its_, _cont);
        Argument arg_ = tryToCalculate(_cont, IndexConstants.SECOND_INDEX, _stack, _cont.getClasses().getExpsIteratorTableCust(), 0, _block,-1);
        if (_stack.stopAt(_cont)) {
            return;
        }
        Struct iterStr_ = arg_.getStruct();
        LoopBlockStack l_ = new LoopBlockStack(_block);
        l_.setLabel(_label);
        l_.getContent().setIndex(-1);
        l_.getContent().setFinished(false);
        l_.getContent().setStructIterator(iterStr_);
        l_.getContent().setMaxIteration(length_);
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.getContent().setEvaluatingKeepLoop(true);
        l_.getContent().setContainer(its_);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String className_;
        className_ = _stack.formatVarType(_variableNameFirst.getType());
        Struct defFirst_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setIndexClassName(_block.getImportedClassIndexName());
        varsLoop_.put(_variableNameFirst.getName(), lv_);
        ip_.putValueVar(_variableNameFirst.getName(), LocalVariable.newLocalVariable(defFirst_,className_));
        className_ = _stack.formatVarType(_variableNameSecond.getType());
        Struct defSecond_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setIndexClassName(_variableNameSecond.getType());
        varsLoop_.put(_variableNameSecond.getName(), lv_);
        ip_.putValueVar(_variableNameSecond.getName(), LocalVariable.newLocalVariable(defSecond_,className_));
        iteratorHasNextTable(_cont, l_, _stack, _block);
    }

    private static Struct processLoop(ExecOperationNodeListOff _expression, ContextEl _conf, StackCall _stackCall, ExecBlock _coveredBlock) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        ip_.globalOffset(_expression.getOffset());
        Argument arg_ = tryToCalculate(_conf, IndexConstants.FIRST_INDEX, _stackCall, _expression.getList(), 0, _coveredBlock,_expression.getEnd());
        if (_stackCall.stopAt(_conf)) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_== NullStruct.NULL_VALUE) {
            String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
        }
        return ito_;

    }
    private static void incrementLoopTable(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall, ExecForEachTable _block) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        Classes cls_ = _conf.getClasses();
        AbstractPageEl call_ = _stackCall.getLastPage();
        if (call_.sizeEl() < 2) {
            String locName_ = cls_.getNextPairVarCust();
            _stackCall.getLastPage().putInternVars(locName_, _l.getContent().getStructIterator(), _conf);
            _stackCall.getLastPage().globalOffset(_block.getOffsets().getSeparatorNext());
        }
        tryToCalculate(_conf,IndexConstants.SECOND_INDEX, _stackCall, _conf.getClasses().getExpsNextPairCust(),0, _block,-1);
        if (_stackCall.stopAt(_conf)) {
            return;
        }
        if (call_.sizeEl() < 3) {
            String locName_ = cls_.getFirstVarCust();
            Struct value_ = call_.getValue(1).getStruct();
            _stackCall.getLastPage().putInternVars(locName_, value_, _conf);
            _stackCall.getLastPage().globalOffset(_block.getVariableFirst().getOffset());
        }
        Argument arg_ = tryToCalculate(_conf, 2, _stackCall, _conf.getClasses().getExpsFirstCust(), 0, _block,-1);
        if (_stackCall.stopAt(_conf)) {
            return;
        }
        if (call_.sizeEl() < 4) {
            String className_ = _stackCall.formatVarType(_block.getVariableFirst().getType());
            _stackCall.getLastPage().getRefParams().set(_block.getVariableFirst().getName(),new VariableWrapper(LocalVariable.newLocalVariable(arg_.getStruct(),className_)));
            ExecVariableTemplates.setWrapValue(_conf, _block.getVariableFirst().getName(), arg_,-1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall);
            ExecVariableTemplates.incrIndexLoop(_conf, _block.getVariableFirst().getName(), -1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getVars(), _stackCall);
            if (_conf.callsOrException(_stackCall)) {
                return;
            }
            String locName_ = cls_.getSecondVarCust();
            Struct value_ = call_.getValue(1).getStruct();
            _stackCall.getLastPage().putInternVars(locName_, value_, _conf);
            _stackCall.getLastPage().globalOffset(_block.getVariableSecond().getOffset());
        }
        arg_ = tryToCalculate(_conf,3,_stackCall,_conf.getClasses().getExpsSecondCust(),0, _block,-1);
        if (_stackCall.stopAt(_conf)) {
            return;
        }
        String className_ = _stackCall.formatVarType(_block.getVariableSecond().getType());
        _stackCall.getLastPage().getRefParams().set(_block.getVariableSecond().getName(),new VariableWrapper(LocalVariable.newLocalVariable(arg_.getStruct(),className_)));
        ExecVariableTemplates.setWrapValue(_conf, _block.getVariableSecond().getName(), arg_,-1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall);
        ExecVariableTemplates.incrIndexLoop(_conf, _block.getVariableSecond().getName(), -1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getVars(), _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return;
        }
        call_.clearCurrentEls();
        goToFirstBlock(_l.getContent(), _block, call_);
    }

    private static void goToFirstBlock(LoopBlockStackContent _l, ExecBracedBlock _block, AbstractPageEl _call) {
        _call.setBlock(_block.getFirstChild());
        _l.setEvaluatingKeepLoop(false);
    }

    private static ConditionReturn iteratorHasNextTable(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall, ExecForEachTable _coveredBlock) {
        String locName_ = _conf.getClasses().getHasNextPairVarCust();
        _stackCall.getLastPage().globalOffset(_coveredBlock.getOffsets().getSeparator());
        return hasNext(_conf, _l, _stackCall, locName_, _conf.getClasses().getExpsHasNextPairCust(), _coveredBlock);
    }

    public static void processIterative(ContextEl _cont, StackCall _stack, String _label, ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, ExecOperationNodeListOff _step, ExecForIterativeLoop _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(_block);
        if (c_ != null) {
            processVisitedLoop(c_, _block, _block, _cont, _stack, ip_);
            return;
        }
        LoopBlockStack l_ = processLoopIter(_cont, _stack, _label, _init, _exp, _step, _block);
        if (l_ == null) {
            return;
        }
        _cont.getCoverage().passLoop(_block, new Argument(BooleanStruct.of(!l_.getContent().isFinished())), _stack);
        visitOrFinish(l_,_block, _stack,ip_);
    }

    private static LoopBlockStack processLoopIter(ContextEl _conf, StackCall _stackCall, String _label, ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, ExecOperationNodeListOff _step, ExecForIterativeLoop _block) {
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
        AbstractPageEl ip_ = _stackCall.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = _block.getVariableName();

        ip_.globalOffset(_init.getOffset());
        Argument argFrom_ = tryToCalculate(_conf, IndexConstants.FIRST_INDEX, _stackCall, _init.getList(), 0, _block,_init.getEnd());
        if (_stackCall.stopAt(_conf)) {
            return null;
        }
        if (argFrom_.isNull()) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
            return null;
        }
        ip_.globalOffset(_exp.getOffset());
        Argument argTo_ = tryToCalculate(_conf, IndexConstants.SECOND_INDEX, _stackCall, _exp.getList(), 0, _block,_exp.getEnd());
        if (_stackCall.stopAt(_conf)) {
            return null;
        }
        if (argTo_.isNull()) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
            return null;
        }
        ip_.globalOffset(_step.getOffset());
        Argument argStep_ = tryToCalculate(_conf, IndexConstants.SECOND_INDEX + 1, _stackCall, _step.getList(), 0, _block,_step.getEnd());
        if (_stackCall.stopAt(_conf)) {
            return null;
        }
        if (argStep_.isNull()) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
            return null;
        }
        ip_.globalOffset(_block.getVariable().getOffset());
        if (checkBp(_stackCall,IndexConstants.SECOND_INDEX + 2,ip_,_block)) {
            return null;
        }
        long fromValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argFrom_.getStruct())).longStruct();
        long toValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argTo_.getStruct())).longStruct();
        long stepValue_ = stepValue(argStep_.getStruct(), fromValue_, toValue_);
        boolean isEq_ = _block instanceof ExecForIterativeLoopEq;
        boolean finished_ = stepValue_ == 0 || fromValue_ == toValue_ && !isEq_;
        LoopBlockStack l_ = new LoopBlockStack(_block);
        l_.setLabel(_label);
        l_.getContent().setFinished(finished_);
        l_.getContent().setEq(isEq_);
        l_.getContent().setCurrentValue(fromValue_);
        l_.getContent().setAchieveValue(toValue_);
        l_.getContent().setStep(stepValue_);
        ip_.addBlock(l_);
        if (finished_) {
            return l_;
        }
        Struct struct_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(_block.getImportedClassName(), _conf.getStandards().getPrimTypes()), new LongStruct(fromValue_));
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndexClassName(_block.getImportedClassIndexName());
        varsLoop_.put(var_, lv_);
        ip_.putValueVar(var_, LocalVariable.newLocalVariable(struct_, _block.getImportedClassName()));
        return l_;
    }

    public static long stepValue(Struct _argStep, long _fromValue, long _toValue) {
        long stepValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(_argStep)).longStruct();
        if (stepValue_ > 0) {
            if (_fromValue > _toValue) {
                stepValue_ = -stepValue_;
            }
        } else if (stepValue_ < 0 && _fromValue < _toValue) {
            stepValue_ = -stepValue_;
        }
        return stepValue_;
    }

    private static void processLastElementLoopIter(ContextEl _conf, LoopBlockStack _l, StackCall _stack, ExecForIterativeLoop _loop) {
        if (_l.getContent().hasNextIter()) {
            incrementLoopIter(_conf, _l, _stack, _loop);
            _conf.getCoverage().passLoop(_loop, new Argument(BooleanStruct.of(true)), _stack);
            return;
        }
        finishLoop(_l.getContent());
        _conf.getCoverage().passLoop(_loop, new Argument(BooleanStruct.of(false)), _stack);

    }

    private static void incrementLoopIter(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall, ExecForIterativeLoop _loop) {
        _stackCall.getLastPage().globalOffset(_loop.getVariable().getOffset());
        if (checkBp(_stackCall,_stackCall.getLastPage(), _loop)) {
            return;
        }
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        _l.getContent().incr();
        String variableName_ = _loop.getVariableName();
        Argument struct_ = ExecVariableTemplates.getWrapValue(_conf, variableName_, -1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall);
        long o_ = NumParsers.convertToNumber(struct_.getStruct()).longStruct()+_l.getContent().getStep();
        Struct element_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(_loop.getImportedClassName(), _conf.getStandards().getPrimTypes()), new LongStruct(o_));
        String className_ = _loop.getImportedClassName();
        _stackCall.getLastPage().getRefParams().set(variableName_,new VariableWrapper(LocalVariable.newLocalVariable(element_,className_)));
        ExecVariableTemplates.setWrapValue(_conf, variableName_, new Argument(element_),-1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall);
        ExecVariableTemplates.incrIndexLoop(_conf, variableName_, -1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getVars(), _stackCall);
        goToFirstBlock(_l.getContent(),_loop,_stackCall.getLastPage());
    }

    private static void processVisitedLoop(LoopBlockStack _l, ExecBracedBlock _bl, ExecBlock _next, ContextEl _context, StackCall _stackCall, AbstractPageEl _current) {
        if (_l.getContent().isEvaluatingKeepLoop()) {
            processLastElementLoop(_context,_stackCall,_bl,_l);
            return;
        }
        visitOrFinish(_l, _next, _stackCall, _current);
    }

    private static void visitOrFinish(LoopBlockStack _l, ExecBlock _next, StackCall _stackCall, AbstractPageEl _current) {
        if (_l.getContent().isFinished()) {
            processBlockAndRemove(_next, _stackCall);
            return;
        }
        goToFirstBlock(_l.getContent(),_l.getExecBlock(),_current);
    }

    public static void processUnclassed(StackCall _stack, ExecUnclassedBracedBlock _bl) {
        AbstractPageEl ip_ = _stack.getLastPage();
        if (ip_.matchStatement(_bl)) {
            processBlockAndRemove(_bl, _stack);
            return;
        }
        if (checkBp(_stack,ip_,_bl)) {
            return;
        }
        IfBlockStack if_ = new IfBlockStack(_bl,_bl);
        if_.setLabel("");
        if_.setCurrentVisitedBlock(_bl);
        ip_.addBlock(if_);
        enterIfBlock(_bl,ip_,if_);
    }

    public static boolean checkBp(StackCall _stack,AbstractPageEl _ip, ExecBlock _bl) {
        return checkBp(_stack,0, _ip, _bl);
    }
    public static boolean checkBp(StackCall _stack, int _index,AbstractPageEl _ip, ExecBlock _bl) {
        ExpressionLanguageBp el_ = _ip.eltBp(_stack,_index,new CustList<ExecOperationNode>(), _bl);
        if (el_.getDiff() != 0) {
            return true;
        }
        _ip.clearCurrentEls();
        return false;
    }

    public static void processEmpty(StackCall _stack, ExecEmptyInstruction _bl) {
        processBlock(_stack, _bl);
    }

    public static void processLine(ContextEl _cont, StackCall _stack, ExecOperationNodeListOff _exp, ExecLine _block) {
        AbstractPageEl ip_ = _stack.getLastPage();

        ip_.globalOffset(_exp.getOffset());
        tryToCalculate(_cont,IndexConstants.FIRST_INDEX,_stack,_exp.getList(),0, _block,_exp.getEnd());
        if (_stack.stopAt(_cont)) {
            return;
        }
        if (ip_ instanceof AbstractCallingInstancingPageEl &&(_block.isCallSuper() || _block.isCallInts())) {
            AbstractCallingInstancingPageEl inst_ = (AbstractCallingInstancingPageEl)ip_;

            ExecBlock bl_ = _block.getNextSibling();
            if (inst_.hasToInitFields(bl_, _stack)) {
                return;
            }
//            boolean initFields_ = AbstractCallingInstancingPageEl.initFields(bl_);
//            //initialize fields if there is no interface constructors to call
//            if (!inst_.isFirstField() && initFields_) {
//                inst_.setFirstField(true);
//                _stack.setCallingState(new NotInitializedFields(inst_));
//                return;
//            }
//            //fields of the current class are initialized if there is no other interface constructors to call
        }
        ip_.clearCurrentEls();
        processBlock(_stack, _block);
    }

    public static void processDeclare(ContextEl _cont, StackCall _stack, String _importedClassName, ExecDeclareVariable _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        String formatted_ = _stack.formatVarType(_importedClassName);
        Struct struct_ = ExecClassArgumentMatching.defaultValue(formatted_, _cont);
        for (String v: _block.getVariableNames()) {
            LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,formatted_);
            ip_.putValueVar(v, lv_);
        }
        processBlock(_stack, _block);
    }

    public static void processMemberBlock(StackCall _stack,AbstractInitPageEl _lastPage) {
        int cur_ = _lastPage.getMember();
        int next_ = cur_ + 1;
        CustList<ExecBlock> visited_ = _lastPage.getVisited();
        if (visited_.isValidIndex(next_)) {
            _lastPage.setMember(next_);
            _lastPage.setBlock(visited_.get(next_));
            return;
        }
//        ExecBlock n_ = getNextSibling();
//        if (n_ != null) {
//            _lastPage.setBlock(n_);
//            return;
//        }
        _stack.nullReadWrite();
    }

    private static void processBlock(StackCall _stackCall, ExecBlock _execBlock) {
        ExecBlock n_ = _execBlock.getNextSibling();
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (n_ != null) {
            ip_.setBlock(n_);
            return;
        }
        ExecBracedBlock par_ = _execBlock.getParent();
        AbstractStask lastStack_ = ip_.tryGetLastStack();
        if (lastStack_ != null) {
            if (lastStack_ instanceof LoopBlockStack) {
                ip_.setBlock(par_);
                removeLocalVarsLoop(ip_, par_);
                ((LoopBlockStack) lastStack_).getContent().setEvaluatingKeepLoop(true);
            }
            if (lastStack_ instanceof IfBlockStack) {
                nextIfStack(ip_, par_, (IfBlockStack) lastStack_);
            }
            if (lastStack_ instanceof TryBlockStack) {
                nextTryStack(_stackCall, ip_, par_, (TryBlockStack) lastStack_);
            }
            if (lastStack_ instanceof SwitchBlockStack) {
                nextSwitchBlock(ip_, par_, (SwitchBlockStack) lastStack_);
            }
            return;
        }
        _stackCall.nullReadWrite();
    }

    private static void nextSwitchBlock(AbstractPageEl _ip, ExecBracedBlock _par, SwitchBlockStack _lastStack) {
        if (_par instanceof ExecDefaultCondition) {
            _par.removeAllVars(_ip);
            redirect(_ip, _par, _lastStack);
        }
        if (_par instanceof ExecAbstractCaseCondition) {
            _par.removeAllVars(_ip);
            redirect(_ip, _par, _lastStack);
        }
    }

    private static void redirect(AbstractPageEl _ip, ExecBracedBlock _par, SwitchBlockStack _lastStack) {
        ExecBlock n_ = _par.getNextSibling();
        if (_lastStack.isAtMostOne() || n_ == null) {
            _ip.setBlock(_lastStack.getBlock());
        } else {
            _ip.setBlock(n_);
        }
    }

    private static void nextTryStack(StackCall _stackCall, AbstractPageEl _ip, ExecBracedBlock _par, TryBlockStack _lastStack) {
        if (_par instanceof ExecTryEval) {
            _par.removeAllVars(_ip);
            _ip.setBlock(_par);
//            _ip.setBlock(_par.getNextSibling());
        }
        if (_par instanceof ExecAbstractCatchEval) {
            _par.removeAllVars(_ip);
            _ip.setBlock(_par);
        }
        if (_par instanceof ExecElseCondition) {
            _par.removeAllVars(_ip);
            AbruptCallingFinally call_ = _lastStack.getCalling();
            if (call_ != null) {
                ExecBlock callingFinally_ = call_.getCallingFinally();
                if (callingFinally_ instanceof ExecAbstractExpressionReturnMethod) {
                    ((ExecAbstractExpressionReturnMethod)callingFinally_).tryReturn(_stackCall,_ip);
                } else if (callingFinally_ != null){
                    _ip.setBlock(callingFinally_);
                } else {
                    _stackCall.setCallingState(new CustomFoundExc(Argument.getNull(_lastStack.getException())));
                }
            } else {
                _ip.setBlock(_par);
            }
        }
    }

    private static void nextIfStack(AbstractPageEl _ip, ExecBracedBlock _par, IfBlockStack _lastStack) {
        if (_par instanceof ExecIfCondition) {
            _par.removeAllVars(_ip);
//            if (isNextIfParts(_par.getNextSibling())) {
//                _ip.setBlock(_par.getNextSibling());
//                _ip.setLastIf(_lastStack);
//            }
        }
        if (_par instanceof ExecElseIfCondition) {
            _par.removeAllVars(_ip);
//            if (isNextIfParts(_par.getNextSibling())) {
//                _ip.setBlock(_par.getNextSibling());
//                _ip.setLastIf(_lastStack);
//            }
        }
        if (_par instanceof ExecElseCondition) {
            _par.removeAllVars(_ip);
        }
        if (_par instanceof ExecUnclassedBracedBlock) {
            _par.removeAllVars(_ip);
        }
        _ip.setBlock(_lastStack.getLastBlock());
    }

    private static void removeLocalVarsLoop(AbstractPageEl _ip, ExecBracedBlock _par) {
        if (_par instanceof ExecDoBlock) {
            _par.removeLocalVars(_ip);
        }
        if (_par instanceof ExecAbstractForEachLoop) {
            _par.removeLocalVars(_ip);
        }
        if (_par instanceof ExecForIterativeLoop) {
            _par.removeLocalVars(_ip);
        }
        if (_par instanceof ExecForMutableIterativeLoop) {
            _par.removeLocalVars(_ip);
        }
        if (_par instanceof ExecForEachTable) {
            _par.removeLocalVars(_ip);
        }
        if (_par instanceof ExecWhileCondition) {
            _par.removeLocalVars(_ip);
        }
    }

    private static void processLastElementLoop(ContextEl _conf, StackCall _stackCall, ExecBlock _par, LoopBlockStack _lastStack) {
        if (_par instanceof ExecDoBlock) {
            processLastElementLoopDo(_stackCall, (ExecDoBlock) _par);
        }
        if (_par instanceof ExecAbstractForEachLoop) {
            processLastElementLoopLoop(_conf, _lastStack, _stackCall, ((ExecAbstractForEachLoop) _par));
        }
        if (_par instanceof ExecForIterativeLoop) {
            processLastElementLoopIter(_conf, _lastStack, _stackCall, ((ExecForIterativeLoop) _par));
        }
        if (_par instanceof ExecForMutableIterativeLoop) {
            processLastElementLoopMutable(_conf, _lastStack, _stackCall, ((ExecForMutableIterativeLoop) _par).getStep(), ((ExecForMutableIterativeLoop) _par).getVariableNames(), ((ExecForMutableIterativeLoop) _par).getExp(), ((ExecForMutableIterativeLoop) _par));
        }
        if (_par instanceof ExecForEachTable) {
            processLastElementLoopTable(_conf, _lastStack, _stackCall, ((ExecForEachTable) _par));
        }
        if (_par instanceof ExecWhileCondition) {
            processLastElementLoopWhile(_conf, _lastStack, _stackCall, (ExecWhileCondition) _par, ((ExecWhileCondition) _par).getCondition());
        }
    }

    public static LoopBlockStack blockStackForArray(ContextEl _cont, String _label, Struct _its, StackCall _stack, ExecAbstractForEachLoop _execLoop) {
        boolean finished_ = false;
        int length_ = getLength(_its, _cont, _stack);
        if (length_ == IndexConstants.SIZE_EMPTY) {
            finished_ = true;
        }
        if (_cont.callsOrException(_stack)) {
            return null;
        }
        LoopBlockStack l_ = new LoopBlockStack(_execLoop);
        l_.setLabel(_label);
        l_.getContent().setIndex(-1);
        l_.getContent().setFinished(finished_);
        l_.getContent().setMaxIteration(length_);
        l_.getContent().setContainer(_its);
        return l_;
    }

    public static int getLength(Struct _str, ContextEl _cont, StackCall _stackCall) {
        if (_str instanceof ArrayStruct) {
            return ((ArrayStruct)_str).getLength();
        }
        String npe_ = _cont.getStandards().getContent().getCoreNames().getAliasNullPe();
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, npe_, _stackCall)));
        return -1;
    }

    public static ConditionReturn hasNext(LoopBlockStack _l) {
        return hasNext(_l.getContent());
    }

    public static ConditionReturn hasNext(LoopBlockStackContent _l) {
        if (_l.hasNext()) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    public static boolean setRemovedCallingFinallyToProcessLoop(AbstractPageEl _ip,AbstractStask _vars, ExecBlock _call, Struct _ex) {
        return _vars == null || setRemovedCallingFinallyToProcess(_ip, _vars, _call, _ex);
    }
    public static boolean setRemovedCallingFinallyToProcess(AbstractPageEl _ip,AbstractStask _vars, ExecBlock _call, Struct _ex) {
        if (!(_vars instanceof TryBlockStack)) {
            _ip.removeLastBlock();
            return false;
        }
        TryBlockStack try_ = (TryBlockStack) _vars;
        if (try_.getCurrentVisitedBlock() instanceof ExecElseCondition) {
            _ip.removeLastBlock();
            return false;
        }
        ExecBracedBlock br_ = try_.getLastBlock();
        if (br_ instanceof ExecElseCondition) {
            _ip.setBlock(br_);
            try_.setException(_ex);
            try_.setCalling(new AbruptCallingFinally(_call));
            return true;
        }
        _ip.removeLastBlock();
        return false;
    }
}
