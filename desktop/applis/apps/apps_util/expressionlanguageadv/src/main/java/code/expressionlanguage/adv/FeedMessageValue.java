package code.expressionlanguage.adv;

import code.gui.AbsTextArea;
import code.gui.AbsTxtComponent;
import code.gui.events.AfterValidateText;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FeedMessageValue implements AfterValidateText {
    private final AbsTextArea value;
    private final StringMap<String> messages;

    public FeedMessageValue(AbsTextArea _v, StringMap<String> _m) {
        this.value = _v;
        this.messages = _m;
    }

    @Override
    public void act(AbsTxtComponent _compo, String _typed) {
        _compo.setText(_typed);
        _compo.setCaretPosition(_typed.length());
        value.setText(StringUtil.nullToEmpty(messages.getVal(_typed)));
    }
}
