package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.variables.LoopVariable;
import code.util.StringMap;

public interface ForLoop extends Loop {

    String getVariableName();
    
    String getClassName();

    String getClassIndexName();

    void incrementLoop(ContextEl _conf, LoopBlockStack _l, StringMap<LoopVariable> _vars);
}
