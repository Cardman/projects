package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.util.IdList;
import code.util.StringMap;

public final class WindowCdmEditor implements AbsGroupFrame {
    private final TabEditor tabEditor;
    private StringMap<String> messages;
    private final AbsCommonFrame commonFrame;
    private final AbsSpinner spinner;
    private final AbsPanel panel;
    private final IdList<WindowCdmEditor> ides;
    public WindowCdmEditor(String _lg, AbstractProgramInfos _list, IdList<WindowCdmEditor> _opened) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        GuiBaseUtil.choose(_lg, _list, this, MessGuiGr.ms());
        tabEditor = new TabEditor(this);
        spinner = _list.getCompoFactory().newSpinner(4,1,64,1);
        TabValueChanged l_ = new TabValueChanged(this);
        spinner.addChangeListener(l_);
        l_.stateChanged();
        panel = _list.getCompoFactory().newPageBox();
        panel.add(spinner);
        panel.add(tabEditor.getPanel());
        commonFrame.setContentPane(panel);
        commonFrame.pack();
        commonFrame.setVisible(true);
        commonFrame.addWindowListener(new QuittingEvent(this));
        ides = _opened;
        _opened.add(this);
    }

    public TabEditor getTabEditor() {
        return tabEditor;
    }

    @Override
    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public AbsSpinner getSpinner() {
        return spinner;
    }

    public AbsPanel getPanel() {
        return panel;
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    public StringMap<String> getMessages() {
        return messages;
    }

    public void setMessages(StringMap<String> _messages) {
        this.messages = _messages;
    }
    @Override
    public void changeLanguage(String _language) {
        setMessages(getMessages());
    }

    @Override
    public void dispatchExit() {
        commonFrame.dispatchExit();
    }

    @Override
    public void quit() {
        commonFrame.setVisible(false);
        ides.removeObj(this);
        GuiBaseUtil.tryExit(commonFrame);
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }

    public IdList<WindowCdmEditor> getIdes() {
        return ides;
    }
}
