package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.events.AbsChangeListener;
import code.gui.events.AbsKeyListenerReleased;
import code.gui.events.AbsMouseListenerIntRel;
import code.gui.initialize.AbsCompoFactory;

public final class TabValueChanged implements AbsChangeListener, AbsMouseListenerIntRel, AbsKeyListenerReleased {
    public static final int TABS = 1024;
    private final WindowWithTreeImpl window;

    public TabValueChanged(WindowWithTreeImpl _w) {
        this.window = _w;
    }

    @Override
    public void stateChanged() {
        int inde_ = window.getEditors().getSelectedIndex();
        if (!window.getTabs().isValidIndex(inde_)){
            return;
        }
        TabEditor tab_ = window.getTabs().get(inde_);
        AbsTextPane c_ = tab_.getCenter();
        AbsCompoFactory compo_ = tab_.getFactories().getCompoFactory();
        int chWi_ = compo_.stringWidth(c_.getMetaFont(),"#");
        int tabWidth_ = chWi_ * window.getTabWidth();
        AbsTabStops tabs_ = compo_.newAbsTabStops(TABS);
        for (int j = 0; j < tabs_.getLength(); j++) {
            tabs_.setTab(j, compo_.newAbsTabStop((j + 1) * tabWidth_));
        }
        AbsAttrSet as_ = compo_.newAttrSet();
        as_.addTabs(tabs_);
        c_.setParagraphAttributes(as_);
        tab_.getPreview().setParagraphAttributes(as_);
        DocumentTextChange.updateEditorText(tab_);
        FindExpressionTask.updatedSegColorsBase(tab_);
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        window.getEditors().events();
    }

    @Override
    public void keyReleased(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        window.getEditors().events();
    }
}
