package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.events.AbsActionListener;
import code.util.StringMap;

public abstract class AbsAnalyzingDebugEvent implements AbsActionListener {
    private final AbsDebuggerGui gui;

    protected AbsAnalyzingDebugEvent(AbsDebuggerGui _g) {
        this.gui = _g;
    }

    @Override
    public void action() {
        gui.getManageGui().submit(new BuildGuiTask(act(),base(),gui,manageOpt(),src()));
    }

    protected abstract AbsOpenFrameInteract act();

    protected abstract ResultContext base();

    protected abstract ManageOptions manageOpt();

    protected abstract StringMap<String> src();
}
