package code.expressionlanguage.adv;

import code.gui.AbsAttrSet;
import code.gui.AbsSpinner;
import code.gui.AbsTabStops;
import code.gui.events.AbsChangeListener;
import code.gui.initialize.AbsCompoFactory;

public final class TabValueChanged implements AbsChangeListener {
    private final WindowCdmEditor window;

    public TabValueChanged(WindowCdmEditor _w) {
        this.window = _w;
    }

    @Override
    public void stateChanged() {
        AbsSpinner cen_ = window.getSpinner();
        int chWi_ = cen_.stringWidth(cen_.getMetaFont(),"#");
        int tabWidth_ = chWi_ * cen_.getValue();
        AbsCompoFactory compo_ = window.getCommonFrame().getFrames().getCompoFactory();
        AbsTabStops tabs_ = compo_.newAbsTabStops(1024);
        for (int j = 0; j < tabs_.getLength(); j++) {
            tabs_.setTab(j, compo_.newAbsTabStop((j + 1) * tabWidth_));
        }
        AbsAttrSet as_ = compo_.newAttrSet();
        as_.addTabs(tabs_);
        window.getTabEditor().getCenter().setParagraphAttributes(as_);
    }
}
