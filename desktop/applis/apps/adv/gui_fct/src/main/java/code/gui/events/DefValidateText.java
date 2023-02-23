package code.gui.events;

import code.gui.AbsTxtComponent;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class DefValidateText implements AfterValidateText {
    @Override
    public void act(AbsTxtComponent _compo, String _typed) {
        _compo.setText(_typed);
        _compo.setCaretPosition(_typed.length());
    }
    @Override
    public StringList filter(String _look, CustList<String> _dict) {
        return filt(_look, _dict);
    }

    public static StringList filt(String _look, CustList<String> _dict) {
        StringList r_ = new StringList();
        String tr_ = _look.trim();
        if (!tr_.isEmpty()) {
            for (String s : _dict) {
                if (StringUtil.quickEq(s, tr_)) {
                    continue;
                }
                if (s.startsWith(tr_)) {
                    r_.add(s);
                }
            }
        }
        return r_;
    }
}
