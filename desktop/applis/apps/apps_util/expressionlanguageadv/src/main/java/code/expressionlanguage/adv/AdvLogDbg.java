package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.gui.AbsTextArea;

public final class AdvLogDbg implements AbsLogDbg {
    private final AbsTextArea area;

    public AdvLogDbg(AbsTextArea _a) {
        this.area = _a;
        area.setText("");
    }

    @Override
    public void log(String _ev) {
        area.append(_ev+"\n");
    }
}
