package code.expressionlanguage.adv;

import code.gui.AbsTextArea;
import code.gui.AbsTxtComponent;
import code.gui.events.AfterValidateText;
import code.gui.events.DefValidateText;
import code.util.CustList;
import code.util.StringList;
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
    @Override
    public StringList filter(String _look, CustList<String> _dict) {
        StringList other_ = DefValidateText.filt(_look, _dict);
        String tr_ = _look.trim();
        if (StringUtil.contains(_dict, tr_)) {
            value.setText(StringUtil.nullToEmpty(messages.getVal(tr_)));
            other_.add(0, tr_);
        }
        return other_;
    }
}
