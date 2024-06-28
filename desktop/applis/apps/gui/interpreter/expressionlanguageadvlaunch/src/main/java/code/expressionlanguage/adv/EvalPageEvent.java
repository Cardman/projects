package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public class EvalPageEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ResultContext resultContext;
    public EvalPageEvent(AbsDebuggerGui _win, ResultContext _res) {
        window = _win;
        resultContext = _res;
    }
    @Override
    public void action() {
        window.dynamicAnalyzeSelectedPage(resultContext);
    }
}
