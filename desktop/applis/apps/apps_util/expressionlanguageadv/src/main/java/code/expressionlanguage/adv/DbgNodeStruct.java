package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsButton;
import code.gui.AbsTextArea;
import code.gui.initialize.AbsCompoFactory;

public interface DbgNodeStruct {
    Struct value();
    String str();
    ContextEl getResult();
    ContextEl getOriginal();
    AbsTextArea logs();
    AbsButton stopButton();
    AbsCustComponent panel();
    void logs(AbsTextArea _a);
    void stopButton(AbsButton _b);
    void panel(AbsCustComponent _c);
    String repr();
    boolean feedChildren(AbsCompoFactory _compo);
    void repr(String _r);
    void repr(Struct _r);
    void append(AbsCompoFactory _compo, String _prefix, Struct _str);
}
