package code.expressionlanguage.adv;

import code.gui.AbsAttrSet;
import code.gui.AbsTabStops;
import code.gui.AbsTextPane;
import code.gui.events.AbsChangeListener;
import code.gui.initialize.AbsCompoFactory;

public final class TabValueChanged implements AbsChangeListener {
    public static final int TABS = 1024;
    private final WindowCdmEditor window;
    private final boolean fromSpinner;

    public TabValueChanged(WindowCdmEditor _w, boolean _f) {
        this.window = _w;
        fromSpinner = _f;
    }

    @Override
    public void stateChanged() {
        int inde_ = window.getEditors().getSelectedIndex();
        if (!window.getTabs().isValidIndex(inde_)){
            return;
        }
        TabEditor tab_ = window.getTabs().get(inde_);
        AbsTextPane c_ = tab_.getCenter();
        int chWi_ = c_.stringWidth(c_.getMetaFont(),"#");
        int tabWidth_ = chWi_ * window.getTabWidth();
        AbsCompoFactory compo_ = tab_.getCommonFrame().getFrames().getCompoFactory();
        AbsTabStops tabs_ = compo_.newAbsTabStops(TABS);
        for (int j = 0; j < tabs_.getLength(); j++) {
            tabs_.setTab(j, compo_.newAbsTabStop((j + 1) * tabWidth_));
        }
        AbsAttrSet as_ = compo_.newAttrSet();
        as_.addTabs(tabs_);
        c_.setParagraphAttributes(as_);
        if (!fromSpinner) {
            DocumentTextChange.updateEditorText(tab_);
        }
    }
}
