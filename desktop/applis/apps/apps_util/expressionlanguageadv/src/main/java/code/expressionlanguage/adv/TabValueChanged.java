package code.expressionlanguage.adv;

import code.gui.AbsAttrSet;
import code.gui.AbsSpinner;
import code.gui.AbsTabStops;
import code.gui.AbsTextPane;
import code.gui.events.AbsChangeListener;
import code.gui.initialize.AbsCompoFactory;

public final class TabValueChanged implements AbsChangeListener {
    public static final int TABS = 1024;
    private final WindowCdmEditor window;

    public TabValueChanged(WindowCdmEditor _w) {
        this.window = _w;
    }

    @Override
    public void stateChanged() {
        TabEditor tab_ = window.getTabEditor();
        AbsTextPane c_ = tab_.getCenter();
        AbsSpinner cen_ = window.getSpinner();
        int chWi_ = c_.stringWidth(c_.getMetaFont(),"#");
        int tabWidth_ = chWi_ * cen_.getValue();
        AbsCompoFactory compo_ = tab_.getCommonFrame().getFrames().getCompoFactory();
        AbsTabStops tabs_ = compo_.newAbsTabStops(TABS);
        for (int j = 0; j < tabs_.getLength(); j++) {
            tabs_.setTab(j, compo_.newAbsTabStop((j + 1) * tabWidth_));
        }
        AbsAttrSet as_ = compo_.newAttrSet();
        as_.addTabs(tabs_);
        c_.setParagraphAttributes(as_);
    }
}
