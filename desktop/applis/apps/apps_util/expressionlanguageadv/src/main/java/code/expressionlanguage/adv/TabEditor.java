package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;

public final class TabEditor {
    private final WindowCdmEditor windowEditor;
    private final AbstractProgramInfos factories;
    private final AbsTextPane center;
    private final AbsTextField finder;
    private final AbsPlainButton closeFinder;
    private final AbsPanel finderPanel;
    private final AbsPanel panel;
    private final CustList<SegmentFindPart> parts = new CustList<SegmentFindPart>();
    private final AbsCommonFrame commonFrame;
    private final AbsPlainLabel label;
    private int currentPart = -1;

    public TabEditor(WindowCdmEditor _editor) {
        windowEditor = _editor;
        commonFrame = _editor.getCommonFrame();
        AbstractProgramInfos frames_ = commonFrame.getFrames();
        factories = frames_;
        label = frames_.getCompoFactory().newPlainLabel(":");
        center = frames_.getCompoFactory().newTextPane();
        center.setFont(new MetaFont(GuiConstants.MONOSPACED,GuiConstants.fontStyle(false,false),12));
        center.setBackground(GuiConstants.BLACK);
        center.setForeground(GuiConstants.WHITE);
        center.setCaretColor(GuiConstants.WHITE);
        center.addCaretListener(new EditorCaretListener(this));
        AbsScrollPane sc_ = frames_.getCompoFactory().newAbsScrollPane(center);
        sc_.setPreferredSize(new MetaDimension(512,512));
        finder = frames_.getCompoFactory().newTextField();
        closeFinder = frames_.getCompoFactory().newPlainButton("x");
        finderPanel = frames_.getCompoFactory().newLineBox();
        finderPanel.setVisible(false);
        finder.addAutoComplete(new FinderTextChange(this));
        finderPanel.add(finder);
        closeFinder.addActionListener(new ClosePanelAction(finderPanel,center));
        finderPanel.add(closeFinder);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new FindAction(this)),GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK);
        panel = frames_.getCompoFactory().newPageBox();
        panel.add(sc_);
        panel.add(label);
        panel.add(finderPanel);
    }

    public WindowCdmEditor getWindowEditor() {
        return windowEditor;
    }

    public AbsPlainLabel getLabel() {
        return label;
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public AbstractProgramInfos getFactories() {
        return factories;
    }

    public AbsPlainButton getCloseFinder() {
        return closeFinder;
    }

    public AbsPanel getFinderPanel() {
        return finderPanel;
    }

    public AbsTextField getFinder() {
        return finder;
    }

    public AbsTextPane getCenter() {
        return center;
    }

    public AbsPanel getPanel() {
        return panel;
    }

    public CustList<SegmentFindPart> getParts() {
        return parts;
    }

    public int getCurrentPart() {
        return currentPart;
    }

    public void setCurrentPart(int _c) {
        this.currentPart = _c;
    }
}
