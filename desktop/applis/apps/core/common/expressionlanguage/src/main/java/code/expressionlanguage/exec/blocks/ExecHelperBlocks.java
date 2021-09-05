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
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
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

    public static AbstractStask hasBlockBreak(AbstractPageEl _ip, String _label) {
        AbstractStask bl_ = _ip.tryGetLastStack();
        if (bl_ == null) {
            _ip.setNullReadWrite();
            return null;
        }
        if (_label.isEmpty()) {
            if (bl_ instanceof LoopBlockStack) {
                ExecBlock forLoopLoc_ = bl_.getCurrentVisitedBlock();
                _ip.setBlock(forLoopLoc_);
                ((LoopBlockStack)bl_).getContent().setFinished(true);
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
                ExecBlock forLoopLoc_ = bl_.getCurrentVisitedBlock();
                _ip.setBlock(forLoopLoc_);
                ((LoopBlockStack)bl_).getContent().setFinished(true);
            }
            if (bl_ instanceof IfBlockStack) {
                ExecBlock forLoopLoc_ = ((IfBlockStack)bl_).getLastBlock();
                _ip.setBlock(forLoopLoc_);
            }
            if (bl_ instanceof TryBlockStack) {
                ExecBlock forLoopLoc_ = ((TryBlockStack)bl_).getLastBlock();
                _ip.setBlock(forLoopLoc_);
            }
            if (bl_ instanceof SwitchBlockStack) {
                ExecBlock forLoopLoc_ = ((SwitchBlockStack)bl_).getBlock();
                _ip.setBlock(forLoopLoc_);
            }
            return null;
        }
        return bl_;
    }

    public static AbstractStask hasBlockContinue(ContextEl _conf, AbstractPageEl _ip, String _label, StackCall _stackCall) {
        AbstractStask bl_ = _ip.tryGetLastStack();
        if (bl_ == null) {
            _ip.setNullReadWrite();
            return null;
        }
        if (bl_ instanceof LoopBlockStack) {
            ExecBracedBlock br_ = bl_.getCurrentVisitedBlock();
            if (_label.isEmpty()||StringUtil.quickEq(_label, bl_.getLabel())){
                LoopBlockStack lSt_;
                lSt_ = (LoopBlockStack) bl_;
                removeLocalVarsLoop(_ip,br_);
                _ip.setBlock(br_);
                processLastElementLoop(_conf,_stackCall,br_,lSt_);
                return null;
            }
        }
        return bl_;
    }

    public static void setVisited(AbstractPageEl _ip, ExecBracedBlock _block) {
        AbstractStask abstractStask_ = _ip.tryGetLastStack();
        if (abstractStask_ == null) {
            _ip.setNullReadWrite();
            return;
        }
        abstractStask_.setCurrentVisitedBlock(_block);
    }

    public static void processFinally(ContextEl _cont, ExecBracedBlock _block, StackCall _stackCall) {
        processEnt(_cont, _block, _stackCall);
//        AbstractPageEl ip_ = _stackCall.getLastPage();
//        TryBlockStack ts_ = ip_.getLastTry();
//        if (ts_ == null) {
//            ip_.setNullReadWrite();
//            return;
//        }
//        ts_.setCurrentVisitedBlock(_block);
//        if (ts_.isEntered()) {
//            processBlockAndRemove(_cont, _block, _stackCall);
//            return;
//        }
//        ts_.setEntered(true);
//        ip_.setBlock(_block.getFirstChild());
    }

    public static void processEnt(ContextEl _cont, ExecBracedBlock _block, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        AbstractStask ts_ = ip_.tryGetLastStack();
        if (!(ts_ instanceof EnteredStack)) {
            ip_.setNullReadWrite();
            return;
        }
        ts_.setCurrentVisitedBlock(_block);
        if (((EnteredStack)ts_).isEntered()) {
            processBlockAndRemove(_cont, _block, _stackCall);
            return;
        }
        ((EnteredStack)ts_).setEntered(true);
        ip_.setBlock(_block.getFirstChild());
    }
    public static void processIf(ContextEl _cont, String _label, ExecCondition _cond, StackCall _stackCall, ExecOperationNodeListOff _condition) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (ip_.matchStatement(_cond)) {
            processBlockAndRemove(_cont, _cond, _stackCall);
            return;
        }
        ConditionReturn assert_ = evaluateConditionBas(_cont, _stackCall, _cond, _condition);
        if (assert_ == ConditionReturn.CALL_EX) {
            return;
        }
        IfBlockStack if_ = new IfBlockStack();
        if_.setExecLastBlock(_cond);
        if_.setLabel(_label);
        ExecBlock n_ = _cond.getNextSibling();
        while (isNextIfParts(n_)) {
            if_.setExecLastBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        if_.setExecBlock(_cond);
        if_.setCurrentVisitedBlock(_cond);
        ip_.addBlock(if_);
        if (assert_ == ConditionReturn.YES) {
            if_.setEntered(true);
            ip_.setBlock(_cond.getFirstChild());
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
        if (!(if_ instanceof EnteredStack)) {
            ip_.setNullReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!((EnteredStack)if_).isEntered()) {
            ConditionReturn assert_ = evaluateConditionBas(_cont, _stackCall, _cond, _condition);
            if (assert_ == ConditionReturn.CALL_EX) {
                return;
            }
            if (assert_ == ConditionReturn.YES) {
                ((EnteredStack)if_).setEntered(true);
                ip_.setBlock(_cond.getFirstChild());
                return;
            }
        }
        if (isNextIfParts(_cond.getNextSibling())) {
            ip_.setBlock(_cond.getNextSibling());
            return;
        }
        processBlockAndRemove(_cont, _cond, _stackCall);
    }

    public static void processElse(ContextEl _cont, ExecBracedBlock _cond, StackCall _stackCall) {
        processEnt(_cont, _cond, _stackCall);
//        AbstractPageEl ip_ = _stackCall.getLastPage();
//        IfBlockStack if_ = ip_.getLastIf();
//        if (if_ == null) {
//            ip_.setNullReadWrite();
//            return;
//        }
//        if_.setCurrentVisitedBlock(_cond);
//        if (if_.isEntered()) {
//            processBlockAndRemove(_cont, _cond, _stackCall);
//            return;
//        }
//        if_.setEntered(true);
//        ip_.setBlock(_cond.getFirstChild());
    }

    public static void processDoWhile(ContextEl _cont, ExecCondition _cond, StackCall _stackCall, ExecOperationNodeListOff _condition) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        AbstractStask l_ = ip_.tryGetLastStack();
        if (!(l_ instanceof LoopBlockStack)) {
            ip_.setNullReadWrite();
            return;
        }
        ((LoopBlockStack) l_).getContent().setEvaluatingKeepLoop(true);
        ConditionReturn keep_ = evaluateConditionBas(_cont, _stackCall, _cond, _condition);
        afterConditLoop(keep_, ((LoopBlockStack) l_).getContent());
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        ip_.setBlock(_cond.getPreviousSibling());
    }

    public static void processBlockAndRemove(ContextEl _context, ExecBlock _bl, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (ip_.noBlock()) {
            ip_.setNullReadWrite();
            return;
        }
        ip_.removeLastBlock();
        processBlock(_context, _stackCall, _bl);
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
        afterCond(_cont, _stack, _label, _execLoop, res_);
    }

    private static void afterCond(ContextEl _cont, StackCall _stack, String _label, ExecBracedBlock _execLoop, ConditionReturn _res) {
        if (_res == ConditionReturn.CALL_EX) {
            return;
        }
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(_label);
        l_.setCurrentVisitedBlock(_execLoop);
        boolean finished_ = _res == ConditionReturn.NO;
        l_.getContent().setFinished(finished_);
        ip_.addBlock(l_);
        if (finished_) {
            processBlockAndRemove(_cont, _execLoop, _stack);
            return;
        }
        ip_.setBlock(_execLoop.getFirstChild());
    }

    public static void processLastElementLoopWhile(ContextEl _conf, LoopBlockStack _l, StackCall _stack, ExecWhileCondition _loop, ExecOperationNodeListOff _condition) {
        _l.getContent().setEvaluatingKeepLoop(true);
        ConditionReturn keep_ = evaluateConditionBas(_conf, _stack, _loop, _condition);
        afterConditLoop(keep_, _l.getContent());
    }

    public static void afterConditLoop(ConditionReturn _keep, LoopBlockStackContent _content) {
        if (_keep == ConditionReturn.CALL_EX) {
            return;
        }
        if (_keep == ConditionReturn.NO) {
            _content.setFinished(true);
        }
        _content.setEvaluatingKeepLoop(false);
    }

    public static void processLastElementLoopDo(StackCall _stack, ExecDoBlock _loop) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.setBlock(_loop.getNextSibling());
    }

    public static void processDo(ContextEl _cont, StackCall _stack, String _label, ExecDoBlock _loop) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(_loop);
        if (c_ != null) {
            processVisitedLoop(c_, _loop, _loop.getNextSibling(), _cont, _stack, ip_);
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(_label);
        l_.setCurrentVisitedBlock(_loop);
        ip_.addBlock(l_);
        ip_.setBlock(_loop.getFirstChild());
    }

    public static void processTry(StackCall _stack, String _label, ExecTryEval _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ExecBlock n_ = _block.getNextSibling();
        TryBlockStack tryStack_ = new TryBlockStack();
        tryStack_.setLabel(_label);
        while (isNextTryParts(n_)) {
            tryStack_.setExecLastBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        tryStack_.setCurrentVisitedBlock(_block);
        ip_.addBlock(tryStack_);
        ip_.setBlock(_block.getFirstChild());
    }

    public static void processCatch(ContextEl _cont, StackCall _stack, ExecAbstractCatchEval _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        if (isNextTryParts(_block.getNextSibling())) {
            ip_.setBlock(_block.getNextSibling());
        } else {
            processBlockAndRemove(_cont, _block, _stack);
        }
    }

    private static boolean isNextTryParts(ExecBlock _n) {
        return _n instanceof ExecAbstractCatchEval || _n instanceof ExecFinallyEval;
    }

    public static void processLastElementLoopMutable(ContextEl _conf, LoopBlockStack _l, StackCall _stack, ExecOperationNodeListOff _step, StringList _variableNames, ExecOperationNodeListOff _exp, ExecForMutableIterativeLoop _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        _l.getContent().setEvaluatingKeepLoop(true);
        int index_ = 0;
        ip_.globalOffset(_step.getOffset());
        if (!_step.getList().isEmpty()) {
            tryToCalculate(_conf,IndexConstants.FIRST_INDEX,_stack,_step.getList(),0, _block);
            if (_conf.callsOrException(_stack)) {
                return;
            }
            index_++;
        }
        if (ip_.sizeEl() <= index_) {
            for (String v : _variableNames) {
                ExecTemplates.incrIndexLoop(_conf,v, -1, ip_.getCache(), ip_.getVars(), _stack);
            }
        }
        ConditionReturn keep_ = evaluateCondition(_conf, index_, _stack, _exp, _block);
        afterConditLoop(keep_, _l.getContent());
    }

    public static void processMutableFor(ContextEl _cont, StackCall _stack, ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, String _label, StringList _variableNames, ExecForMutableIterativeLoop _bl) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(_bl);
        if (c_ != null) {
            processVisitedLoop(c_, _bl, _bl, _cont, _stack, ip_);
            return;
        }
        ip_.globalOffset(_init.getOffset());
        int index_ = 0;
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
        if (!_init.getList().isEmpty()) {
            tryToCalculate(_cont,IndexConstants.FIRST_INDEX,_stack,_init.getList(),0, _bl);
            index_++;
        }
        ConditionReturn res_ = evaluateCondition(_cont, index_, _stack, _exp, _bl);
        afterCond(_cont, _stack, _label, _bl, res_);
    }

    private static ConditionReturn evaluateCondition(ContextEl _context, int _index, StackCall _stackCall, ExecOperationNodeListOff _list, ExecForMutableIterativeLoop _block) {
        if (_context.callsOrException(_stackCall)) {
            return ConditionReturn.CALL_EX;
        }
        AbstractPageEl last_ = _stackCall.getLastPage();
        if (_list.getList().isEmpty()) {
            last_.clearCurrentEls();
            return ConditionReturn.YES;
        }
        last_.globalOffset(_list.getOffset());
        Argument arg_ = tryToCalculate(_context, _index, _stackCall, _list.getList(), 0, _block);
        if (_context.callsOrException(_stackCall)) {
            return ConditionReturn.CALL_EX;
        }
        last_.clearCurrentEls();
        _context.getCoverage().passConditionsForMutable(_block, arg_, _list.getList().last(), _stackCall);
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    public static Argument tryToCalculate(ContextEl _context, int _index, StackCall _stackCall, CustList<ExecOperationNode> _list, int _offset, ExecBlock _coveredBlock) {
        ArgumentsPair argumentsPair_ = tryToCalculatePair(_context, _index, _stackCall, _list, _offset, _coveredBlock);
        return ExpressionLanguage.getNullable(argumentsPair_);
    }

    public static ArgumentsPair tryToCalculatePair(ContextEl _context, int _index, StackCall _stackCall, CustList<ExecOperationNode> _list, int _offset, ExecBlock _coveredBlock) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        ExpressionLanguage exp_ = ip_.getCurrentEl(_index, _list, _coveredBlock);
        return ExpressionLanguage.tryToCalculatePair(_context, exp_, _offset, _stackCall);
    }

    public static ConditionReturn evaluateConditionBas(ContextEl _context, StackCall _stackCall, ExecCondition _execCondition, ExecOperationNodeListOff _condition) {
        AbstractPageEl last_ = _stackCall.getLastPage();
        last_.globalOffset(_condition.getOffset());
        Argument arg_ = tryToCalculate(_context, IndexConstants.FIRST_INDEX, _stackCall, _condition.getList(), 0, _execCondition);
        if (_context.callsOrException(_stackCall)) {
            return ConditionReturn.CALL_EX;
        }
        last_.clearCurrentEls();
        _context.getCoverage().passConditions(_execCondition, arg_, _condition.getList().last(), _stackCall);
        if (BooleanStruct.isTrue(NumParsers.convertToBoolean(arg_.getStruct()))) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    public static void processForEach(ContextEl _cont, StackCall _stack, String _label, ExecOperationNodeListOff _expression, ExecAbstractForEachLoop _bl) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(_bl);
        if (c_ != null) {
            processVisitedLoop(c_, _bl, _bl, _cont, _stack, ip_);
            return;
        }
        Struct its_ = processLoopForEach(_cont, _stack, _expression, _bl);
        if (_cont.callsOrException(_stack)) {
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
        varsLoop_.put(_bl.getVariableName(), lv_);
        _bl.checkIfNext(_cont, l_, _stack);
    }

    private static Struct processLoopForEach(ContextEl _conf, StackCall _stackCall, ExecOperationNodeListOff _expression, ExecBlock _coveredBlock) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        ip_.globalOffset(_expression.getOffset());
        Argument arg_ = tryToCalculate(_conf, IndexConstants.FIRST_INDEX, _stackCall, _expression.getList(), 0, _coveredBlock);
        if (_conf.callsOrException(_stackCall)) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();

    }

    public static ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall, String _locName, CustList<ExecOperationNode> _el, ExecBlock _coveredBlock) {
        _stackCall.getLastPage().putInternVars(_locName, _l.getContent().getStructIterator(), _conf);
        Argument arg_ = tryToCalculate(_conf, IndexConstants.FIRST_INDEX, _stackCall, _el, 0, _coveredBlock);
        if (_conf.callsOrException(_stackCall)) {
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
        Argument arg_ = tryToCalculate(_cont, IndexConstants.SECOND_INDEX, _stack, _cont.getClasses().getExpsIteratorCust(), 0, _block);
        if (_cont.callsOrException(_stack)) {
            return null;
        }
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Struct iterStr_ = arg_.getStruct();
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(_label);
        l_.getContent().setIndex(-1);
        l_.setCurrentVisitedBlock(_block);
        l_.getContent().setStructIterator(iterStr_);
        l_.getContent().setMaxIteration(length_);
        l_.getContent().setContainer(_its);
        return l_;
    }

    public static Argument retrieveValueIt(ContextEl _conf, LoopBlockStack _l, StackCall _stack, ExecBlock _coveredBlock) {
        String locName_ = _conf.getClasses().getNextVarCust();
        AbstractPageEl abs_ = _stack.getLastPage();
        abs_.putInternVars(locName_, _l.getContent().getStructIterator(), _conf);
        return tryToCalculate(_conf,IndexConstants.SECOND_INDEX,_stack, _conf.getClasses().getExpsNextCust(),0, _coveredBlock);
    }

    public static void processLastElementLoopTable(ContextEl _conf, LoopBlockStack _l, StackCall _stack, String _variableNameFirst, String _variableNameSecond, ExecForEachTable _block) {
        _l.getContent().setEvaluatingKeepLoop(true);
        ConditionReturn has_ = iteratorHasNext(_conf, _l, _stack, _block);
        if (has_ == ConditionReturn.CALL_EX) {
            return;
        }
        boolean hasNext_ = has_ == ConditionReturn.YES;

        if (hasNext_) {
            _conf.getCoverage().passLoop(_block, new Argument(BooleanStruct.of(true)), _stack);
            incrementLoop(_conf, _l, _stack, _variableNameFirst, _variableNameSecond, _block);
        } else {
            _conf.getCoverage().passLoop(_block, new Argument(BooleanStruct.of(false)), _stack);
            _stack.getLastPage().clearCurrentEls();
            _l.getContent().setFinished(true);
            _l.getContent().setEvaluatingKeepLoop(false);
        }
    }

    public static void processTable(ContextEl _cont, StackCall _stack, String _label, String _variableNameFirst, String _variableNameSecond, ExecOperationNodeListOff _expression, ExecForEachTable _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(_block);
        if (c_ != null) {
            processVisitedLoop(c_, _block, _block, _cont, _stack, ip_);
            return;
        }
        Struct its_ = processLoop(_expression,_cont, _stack,_block);
        if (_cont.callsOrException(_stack)) {
            return;
        }
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Classes cls_ = _cont.getClasses();
        String locName_ = cls_.getIteratorTableVarCust();
        _stack.getLastPage().putInternVars(locName_, its_, _cont);
        Argument arg_ = tryToCalculate(_cont, IndexConstants.SECOND_INDEX, _stack, _cont.getClasses().getExpsIteratorTableCust(), 0, _block);
        if (_cont.callsOrException(_stack)) {
            return;
        }
        Struct iterStr_ = arg_.getStruct();
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(_label);
        l_.getContent().setIndex(-1);
        l_.getContent().setFinished(false);
        l_.setCurrentVisitedBlock(_block);
        l_.getContent().setStructIterator(iterStr_);
        l_.getContent().setMaxIteration(length_);
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.getContent().setEvaluatingKeepLoop(true);
        l_.getContent().setContainer(its_);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String className_;
        className_ = _stack.formatVarType(_block.getImportedClassNameFirst());
        Struct defFirst_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setIndexClassName(_block.getImportedClassIndexName());
        varsLoop_.put(_variableNameFirst, lv_);
        ip_.putValueVar(_variableNameFirst, LocalVariable.newLocalVariable(defFirst_,className_));
        className_ = _stack.formatVarType(_block.getImportedClassNameSecond());
        Struct defSecond_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setIndexClassName(_block.getImportedClassIndexName());
        varsLoop_.put(_variableNameSecond, lv_);
        ip_.putValueVar(_variableNameSecond, LocalVariable.newLocalVariable(defSecond_,className_));
        iteratorHasNext(_cont, l_, _stack, _block);
    }

    private static Struct processLoop(ExecOperationNodeListOff _expression, ContextEl _conf, StackCall _stackCall, ExecBlock _coveredBlock) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        ip_.globalOffset(_expression.getOffset());
        Argument arg_ = tryToCalculate(_conf, IndexConstants.FIRST_INDEX, _stackCall, _expression.getList(), 0, _coveredBlock);
        if (_conf.callsOrException(_stackCall)) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_== NullStruct.NULL_VALUE) {
            String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
        }
        return ito_;

    }
    private static void incrementLoop(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall, String _variableNameFirst, String _variableNameSecond, ExecForEachTable _block) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        Classes cls_ = _conf.getClasses();
        AbstractPageEl call_ = _stackCall.getLastPage();
        if (call_.sizeEl() < 2) {
            String locName_ = cls_.getNextPairVarCust();
            _stackCall.getLastPage().putInternVars(locName_, _l.getContent().getStructIterator(), _conf);
        }
        tryToCalculate(_conf,IndexConstants.SECOND_INDEX, _stackCall, _conf.getClasses().getExpsNextPairCust(),0, _block);
        if (_conf.callsOrException(_stackCall)) {
            return;
        }
        if (call_.sizeEl() < 3) {
            String locName_ = cls_.getFirstVarCust();
            Struct value_ = call_.getValue(1).getStruct();
            _stackCall.getLastPage().putInternVars(locName_, value_, _conf);
        }
        Argument arg_ = tryToCalculate(_conf, 2, _stackCall, _conf.getClasses().getExpsFirstCust(), 0, _block);
        if (_conf.callsOrException(_stackCall)) {
            return;
        }
        if (call_.sizeEl() < 4) {
            ExecTemplates.setWrapValue(_conf, _variableNameFirst, arg_,-1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall);
            ExecTemplates.incrIndexLoop(_conf, _variableNameFirst, -1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getVars(), _stackCall);
            if (_conf.callsOrException(_stackCall)) {
                return;
            }
            String locName_ = cls_.getSecondVarCust();
            Struct value_ = call_.getValue(1).getStruct();
            _stackCall.getLastPage().putInternVars(locName_, value_, _conf);
        }
        arg_ = tryToCalculate(_conf,3,_stackCall,_conf.getClasses().getExpsSecondCust(),0, _block);
        if (_conf.callsOrException(_stackCall)) {
            return;
        }
        ExecTemplates.setWrapValue(_conf, _variableNameSecond, arg_,-1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall);
        ExecTemplates.incrIndexLoop(_conf, _variableNameSecond, -1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getVars(), _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return;
        }
        call_.clearCurrentEls();
        call_.setBlock(_block.getFirstChild());
        _l.getContent().setEvaluatingKeepLoop(false);
    }

    private static ConditionReturn iteratorHasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall, ExecBlock _coveredBlock) {
        String locName_ = _conf.getClasses().getHasNextPairVarCust();
        return hasNext(_conf, _l, _stackCall, locName_, _conf.getClasses().getExpsHasNextPairCust(), _coveredBlock);
    }

    public static void processIterative(ContextEl _cont, StackCall _stack, String _label, ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, ExecOperationNodeListOff _step, ExecForIterativeLoop _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(_block);
        if (c_ != null) {
            processVisitedLoop(c_, _block, _block, _cont, _stack, ip_);
            return;
        }
        LoopBlockStack l_ = processLoop(_cont, _stack, _label, _init, _exp, _step, _block);
        if (l_ == null) {
            return;
        }
        _cont.getCoverage().passLoop(_block, new Argument(BooleanStruct.of(!l_.getContent().isFinished())), _stack);
        visitOrFinish(l_,_block,_cont,_stack,ip_);
    }

    private static LoopBlockStack processLoop(ContextEl _conf, StackCall _stackCall, String _label, ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, ExecOperationNodeListOff _step, ExecForIterativeLoop _block) {
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
        AbstractPageEl ip_ = _stackCall.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = _block.getVariableName();

        ip_.globalOffset(_init.getOffset());
        Argument argFrom_ = tryToCalculate(_conf, IndexConstants.FIRST_INDEX, _stackCall, _init.getList(), 0, _block);
        if (_conf.callsOrException(_stackCall)) {
            return null;
        }
        if (argFrom_.isNull()) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
            return null;
        }
        ip_.globalOffset(_exp.getOffset());
        Argument argTo_ = tryToCalculate(_conf, IndexConstants.SECOND_INDEX, _stackCall, _exp.getList(), 0, _block);
        if (_conf.callsOrException(_stackCall)) {
            return null;
        }
        if (argTo_.isNull()) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
            return null;
        }
        ip_.globalOffset(_step.getOffset());
        Argument argStep_ = tryToCalculate(_conf, IndexConstants.SECOND_INDEX + 1, _stackCall, _step.getList(), 0, _block);
        if (_conf.callsOrException(_stackCall)) {
            return null;
        }
        if (argStep_.isNull()) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
            return null;
        }
        ip_.clearCurrentEls();
        long fromValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argFrom_.getStruct())).longStruct();
        long toValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argTo_.getStruct())).longStruct();
        long stepValue_ = stepValue(argStep_, fromValue_, toValue_);
        boolean isEq_ = _block instanceof ExecForIterativeLoopEq;
        boolean finished_ = stepValue_ == 0 || fromValue_ == toValue_ && !isEq_;
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(_label);
        l_.getContent().setFinished(finished_);
        l_.setCurrentVisitedBlock(_block);
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

    public static long stepValue(Argument _argStep, long _fromValue, long _toValue) {
        long stepValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(_argStep.getStruct())).longStruct();
        if (stepValue_ > 0) {
            if (_fromValue > _toValue) {
                stepValue_ = -stepValue_;
            }
        } else if (stepValue_ < 0 && _fromValue < _toValue) {
            stepValue_ = -stepValue_;
        }
        return stepValue_;
    }

    public static void processSwitch(ContextEl _cont, StackCall _stack, String _label, ExecOperationNodeListOff _value, ExecAbstractSwitchBlock _bl) {
        AbstractPageEl ip_ = _stack.getLastPage();
        if (ip_.matchStatement(_bl)) {
            processBlockAndRemove(_cont, _bl, _stack);
            return;
        }
        ip_.globalOffset(_value.getOffset());
        Argument arg_ = tryToCalculate(_cont, IndexConstants.FIRST_INDEX, _stack, _value.getList(), 0, _bl);
        if (_cont.callsOrException(_stack)) {
            return;
        }
        SwitchBlockStack if_ = new SwitchBlockStack();
        if_.setLabel(_label);
        _bl.processCase(_cont,if_,arg_, _stack);
    }

    private static void processVisitedLoop(LoopBlockStack _l, ExecBracedBlock _bl, ExecBlock _next, ContextEl _context, StackCall _stackCall, AbstractPageEl _current) {
        if (_l.getContent().isEvaluatingKeepLoop()) {
            processLastElementLoop(_context,_stackCall,_bl,_l);
            return;
        }
        visitOrFinish(_l, _next, _context, _stackCall, _current);
    }

    private static void visitOrFinish(LoopBlockStack _l, ExecBlock _next, ContextEl _context, StackCall _stackCall, AbstractPageEl _current) {
        if (_l.getContent().isFinished()) {
            processBlockAndRemove(_context, _next, _stackCall);
            return;
        }
        _current.setBlock(_l.getCurrentVisitedBlock().getFirstChild());
    }

    public static void processUnclassed(ContextEl _cont, StackCall _stack, ExecUnclassedBracedBlock _bl) {
        AbstractPageEl ip_ = _stack.getLastPage();
        if (ip_.matchStatement(_bl)) {
            processBlockAndRemove(_cont, _bl, _stack);
            return;
        }
        IfBlockStack if_ = new IfBlockStack();
        if_.setLabel("");
        if_.setExecLastBlock(_bl);
        if_.setExecBlock(_bl);
        if_.setCurrentVisitedBlock(_bl);
        ip_.addBlock(if_);
        if_.setEntered(true);
        ip_.setBlock(_bl.getFirstChild());
    }

    public static void processEmpty(ContextEl _cont, StackCall _stack, ExecEmptyInstruction _bl) {
        processBlock(_cont, _stack, _bl);
    }

    public static void processLine(ContextEl _cont, StackCall _stack, ExecOperationNodeListOff _exp, ExecLine _block) {
        AbstractPageEl ip_ = _stack.getLastPage();

        ip_.globalOffset(_exp.getOffset());
        tryToCalculate(_cont,IndexConstants.FIRST_INDEX,_stack,_exp.getList(),0, _block);
        if (_cont.callsOrException(_stack)) {
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
        processBlock(_cont, _stack, _block);
    }

    public static void processDeclare(ContextEl _cont, StackCall _stack, String _importedClassName, ExecDeclareVariable _block) {
        AbstractPageEl ip_ = _stack.getLastPage();
        String formatted_ = _stack.formatVarType(_importedClassName);
        Struct struct_ = ExecClassArgumentMatching.defaultValue(formatted_, _cont);
        for (String v: _block.getVariableNames()) {
            LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,formatted_);
            ip_.putValueVar(v, lv_);
        }
        processBlock(_cont, _stack, _block);
    }

    public static void processMemberBlock(AbstractInitPageEl _lastPage) {
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
        _lastPage.setNullReadWrite();
    }

    private static void processBlock(ContextEl _conf, StackCall _stackCall, ExecBlock _execBlock) {
        ExecBlock n_ = _execBlock.getNextSibling();
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (n_ != null) {
            ip_.setBlock(n_);
            return;
        }
        ExecBracedBlock par_ = _execBlock.getParent();
        AbstractStask lastStack_ = ip_.tryGetLastStack();
        if (lastStack_ != null) {
            ip_.setBlock(par_);
            if (lastStack_ instanceof LoopBlockStack) {
                removeLocalVarsLoop(ip_, par_);
                processLastElementLoop(_conf, _stackCall, par_, (LoopBlockStack) lastStack_);
            }
            if (lastStack_ instanceof IfBlockStack) {
                nextIfStack(ip_, par_, (IfBlockStack) lastStack_);
            }
            if (lastStack_ instanceof TryBlockStack) {
                nextTryStack(_conf, _stackCall, ip_, par_, (TryBlockStack) lastStack_);
            }
            if (lastStack_ instanceof SwitchBlockStack) {
                nextSwitchBlock(ip_, par_, (SwitchBlockStack) lastStack_);
            }
            return;
        }
        ip_.setNullReadWrite();
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
        if (_lastStack.getExecLastVisitedBlock() == _par) {
            _ip.setBlock(_lastStack.getBlock());
        } else {
            _ip.setBlock(_par.getNextSibling());
        }
    }

    private static void nextTryStack(ContextEl _conf, StackCall _stackCall, AbstractPageEl _ip, ExecBracedBlock _par, TryBlockStack _lastStack) {
        if (_par instanceof ExecTryEval) {
            _par.removeAllVars(_ip);
            _ip.setBlock(_par.getNextSibling());
        }
        if (_par instanceof ExecAbstractCatchEval) {
            _par.removeAllVars(_ip);
        }
        if (_par instanceof ExecFinallyEval) {
            _par.removeAllVars(_ip);
            AbruptCallingFinally call_ = _lastStack.getCalling();
            if (call_ != null) {
                MethodCallingFinally callingFinally_ = call_.getCallingFinally();
                if (callingFinally_ != null) {
                    callingFinally_.removeBlockFinally(_conf, _stackCall);
                } else {
                    Struct exception_ = _lastStack.getException();
                    _stackCall.setCallingState(new CustomFoundExc(exception_));
                    LocalThrowing.removeBlockFinally(_conf, exception_, _stackCall);
                }
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

    private static void processLastElementLoop(ContextEl _conf, StackCall _stackCall, ExecBracedBlock _par, LoopBlockStack _lastStack) {
        if (_par instanceof ExecDoBlock) {
            processLastElementLoopDo(_stackCall, (ExecDoBlock) _par);
        }
        if (_par instanceof ExecAbstractForEachLoop) {
            ExecAbstractForEachLoop.processLastElementLoop(_conf, _lastStack, _stackCall, ((ExecAbstractForEachLoop) _par));
        }
        if (_par instanceof ExecForIterativeLoop) {
            ExecForIterativeLoop.processLastElementLoop(_conf, _lastStack, _stackCall, ((ExecForIterativeLoop) _par));
        }
        if (_par instanceof ExecForMutableIterativeLoop) {
            processLastElementLoopMutable(_conf, _lastStack, _stackCall, ((ExecForMutableIterativeLoop) _par).getStep(), ((ExecForMutableIterativeLoop) _par).getVariableNames(), ((ExecForMutableIterativeLoop) _par).getExp(), ((ExecForMutableIterativeLoop) _par));
        }
        if (_par instanceof ExecForEachTable) {
            processLastElementLoopTable(_conf, _lastStack, _stackCall, ((ExecForEachTable) _par).getVariableNameFirst(), ((ExecForEachTable) _par).getVariableNameSecond(), ((ExecForEachTable) _par));
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
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(_label);
        l_.getContent().setIndex(-1);
        l_.getContent().setFinished(finished_);
        l_.setCurrentVisitedBlock(_execLoop);
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

    public static boolean setRemovedCallingFinallyToProcessLoop(AbstractPageEl _ip,AbstractStask _vars, MethodCallingFinally _call, Struct _ex) {
        return _vars == null || setRemovedCallingFinallyToProcess(_ip, _vars, _call, _ex);
    }
    public static boolean setRemovedCallingFinallyToProcess(AbstractPageEl _ip,AbstractStask _vars, MethodCallingFinally _call, Struct _ex) {
        if (!(_vars instanceof TryBlockStack)) {
            _ip.removeLastBlock();
            return false;
        }
        TryBlockStack try_ = (TryBlockStack) _vars;
        if (try_.getCurrentVisitedBlock() instanceof ExecFinallyEval) {
            _ip.removeLastBlock();
            return false;
        }
        ExecBracedBlock br_ = try_.getLastBlock();
        if (br_ instanceof ExecFinallyEval) {
            _ip.setBlock(br_);
            try_.setException(_ex);
            try_.setCalling(new AbruptCallingFinally(_call));
            return true;
        }
        _ip.removeLastBlock();
        return false;
    }
}
