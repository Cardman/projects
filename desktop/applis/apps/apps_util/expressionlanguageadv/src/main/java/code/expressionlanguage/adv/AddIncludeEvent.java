package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class AddIncludeEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final GuiStackForm form;
    private final ResultContext currentResult;

    public AddIncludeEvent(GuiStackForm _f, AbsDebuggerGui _w, ResultContext _res) {
        this.form = _f;
        this.window = _w;
        currentResult = _res;
    }

    @Override
    public void action() {
        ReadOnlyFormTabEditor e_ = form.getReadOnlyFormTabEditor();
        form.add(currentResult,form.getMustBe(), e_);
        form.actualiseLists(window.getCommonFrame());
    }
}
