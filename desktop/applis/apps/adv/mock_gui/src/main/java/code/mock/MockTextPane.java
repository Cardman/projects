package code.mock;

import code.gui.AbsAttrSet;
import code.gui.AbsTextPane;
import code.gui.events.AbsEnabledAction;
import code.gui.images.MetaFont;
import code.util.CustList;
import code.util.StringMap;

public final class MockTextPane extends MockTxtComponent implements AbsTextPane {
    private final StringMap<AbsEnabledAction> actions = new StringMap<AbsEnabledAction>();
    private final StringMap<CustList<AbsAttrSet>> attrSets = new StringMap<CustList<AbsAttrSet>>();


    @Override
    public void registerKeyboardAction(AbsEnabledAction _action, int _a, int _b) {
        actions.put(_a+","+_b,_action);
    }

    @Override
    public StringMap<AbsEnabledAction> getActionsMap() {
        return actions;
    }

    @Override
    public void setFontSize(int _size) {
        MetaFont m_ = getMetaFont();
        setFont(new MetaFont(m_.getFontFamily(),m_.getFont(),_size));
    }

    @Override
    public void clearCharacterAttributes(int _begin, int _length) {
        if (_length <= 0) {
            return;
        }
        int until_ = _begin + _length;
        for (int i = _begin; i < until_; i++) {
            String k_ = Long.toString(i);
            if (attrSets.contains(k_)) {
                attrSets.getVal(k_).clear();
            } else {
                attrSets.addEntry(k_,new CustList<AbsAttrSet>());
            }
        }
    }

    @Override
    public void setCharacterAttributes(int _begin, int _length, AbsAttrSet _attrs, boolean _replace) {
        if (_length <= 0) {
            return;
        }
        int until_ = _begin + _length;
        for (int i = _begin; i < until_; i++) {
            String k_ = Long.toString(i);
            if (attrSets.contains(k_)) {
                if (_replace) {
                    attrSets.getVal(k_).clear();
                }
                attrSets.getVal(k_).add(_attrs);
            } else {
                CustList<AbsAttrSet> elts_ = new CustList<AbsAttrSet>();
                elts_.add(_attrs);
                attrSets.addEntry(k_,elts_);
            }
        }
    }

    public StringMap<CustList<AbsAttrSet>> getAttrSets() {
        return attrSets;
    }
}
