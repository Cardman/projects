package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsPlainButton;
import code.gui.AbsTextArea;

public interface DbgNodeStruct {
    Struct value();
    String str();
    ContextEl getResult();
    ContextEl getOriginal();
    AbsTextArea logs();
    AbsPlainButton stopButton();
    AbsCustComponent panel();
    void logs(AbsTextArea _a);
    void stopButton(AbsPlainButton _b);
    void panel(AbsCustComponent _c);
    String repr();
    void repr(String _r);
}
