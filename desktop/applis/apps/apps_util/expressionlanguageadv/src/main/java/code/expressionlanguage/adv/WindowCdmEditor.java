package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.events.QuittingEvent;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.util.IdList;
import code.util.StringMap;

public final class WindowCdmEditor implements AbsGroupFrame {
    private final AbsTextPane center;
    private StringMap<String> messages;
    private final AbsCommonFrame commonFrame;
    private final AbsSpinner spinner;
    private final AbsTextField finder;
    private final AbsPlainButton closeFinder;
    private final AbsPanel panel;
    private final AbsPanel finderPanel;
    private final IdList<WindowCdmEditor> ides;
    public WindowCdmEditor(String _lg, AbstractProgramInfos _list, IdList<WindowCdmEditor> _opened) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        GuiBaseUtil.choose(_lg, _list, this, MessGuiGr.ms());
        spinner = _list.getCompoFactory().newSpinner(4,1,64,1);
        center = _list.getCompoFactory().newTextPane();
        center.setFont(new MetaFont(GuiConstants.MONOSPACED,GuiConstants.fontStyle(false,false),12));
        center.setBackground(GuiConstants.BLACK);
        center.setForeground(GuiConstants.WHITE);
        center.setCaretColor(GuiConstants.WHITE);
        AbsScrollPane sc_ = _list.getCompoFactory().newAbsScrollPane(center);
        sc_.setPreferredSize(new MetaDimension(512,512));
        finder = _list.getCompoFactory().newTextField();
        closeFinder = _list.getCompoFactory().newPlainButton("x");
        finderPanel = _list.getCompoFactory().newLineBox();
        finderPanel.setVisible(false);
        finderPanel.add(finder);
        closeFinder.addActionListener(new ClosePanelAction(finderPanel,center));
        finderPanel.add(closeFinder);
        center.registerKeyboardAction(_list.getCompoFactory().wrap(new FindAction(this)),GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK);
        panel = _list.getCompoFactory().newPageBox();
        panel.add(spinner);
        panel.add(sc_);
        panel.add(finderPanel);
        commonFrame.setContentPane(panel);
        commonFrame.pack();
        commonFrame.setVisible(true);
        commonFrame.addWindowListener(new QuittingEvent(this));
        ides = _opened;
        _opened.add(this);
    }

    @Override
    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public AbsSpinner getSpinner() {
        return spinner;
    }

    public AbsTextPane getCenter() {
        return center;
    }

    public AbsPanel getFinderPanel() {
        return finderPanel;
    }

    public AbsTextField getFinder() {
        return finder;
    }

    public AbsPlainButton getCloseFinder() {
        return closeFinder;
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
