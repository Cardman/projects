package code.expressionlanguage.adv;

import code.gui.AbsAttrSet;
import code.gui.AbsTextPane;

public final class SetCharacterAttributes implements Runnable {
    private final AbsTextPane tabEditor;
    private final int begin;
    private final int length;
    private final AbsAttrSet attrs;

    public SetCharacterAttributes(AbsTextPane _t,int _b, int _l, AbsAttrSet _a) {
        this.tabEditor = _t;
        begin = _b;
        length = _l;
        attrs = _a;
    }

    @Override
    public void run() {
        tabEditor.setCharacterAttributes(begin,length,attrs,false);
    }
}
