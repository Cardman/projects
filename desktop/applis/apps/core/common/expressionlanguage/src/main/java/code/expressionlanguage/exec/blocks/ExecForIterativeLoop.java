package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public abstract class ExecForIterativeLoop extends ExecBracedBlock implements WithEl {

    private final String label;

    private final String importedClassName;

    private final String importedClassIndexName;

    private final String variableName;

    private final ExecOperationNodeListOff init;
    private final ExecOperationNodeListOff exp;
    private final ExecOperationNodeListOff step;

    protected ExecForIterativeLoop(String _label, String _importedClassName, String _importedClassIndexName, String _variableName,
                                ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, ExecOperationNodeListOff _step) {
        this.label = _label;
        this.importedClassName = _importedClassName;
        this.importedClassIndexName = _importedClassIndexName;
        this.variableName = _variableName;
        init = _init;
        exp = _exp;
        step = _step;
    }

    public static void processLastElementLoop(ContextEl _conf, LoopBlockStack _l, StackCall _stack, ExecForIterativeLoop _loop) {
        if (_l.getContent().hasNextIter()) {
            _loop.incrementLoop(_conf, _l, _stack);
            _conf.getCoverage().passLoop(_loop, new Argument(BooleanStruct.of(true)), _stack);
            return;
        }
        _l.getContent().setFinished(true);
        _conf.getCoverage().passLoop(_loop, new Argument(BooleanStruct.of(false)), _stack);

    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processIterative(_cont, _stack, label, init, exp, step, this);
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
        _ip.removeRefVar(var_);
    }

    private void incrementLoop(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        _l.getContent().incr();
        String var_ = getVariableName();
        Argument struct_ = ExecTemplates.getWrapValue(_conf,var_, -1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall);
        long o_ = NumParsers.convertToNumber(struct_.getStruct()).longStruct()+_l.getContent().getStep();
        Struct element_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _conf.getStandards().getPrimTypes()), new LongStruct(o_));
        ExecTemplates.setWrapValue(_conf,var_, new Argument(element_),-1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall);
        ExecTemplates.incrIndexLoop(_conf,var_, -1, _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getVars(), _stackCall);
    }

    public String getVariableName() {
        return variableName;
    }

}
