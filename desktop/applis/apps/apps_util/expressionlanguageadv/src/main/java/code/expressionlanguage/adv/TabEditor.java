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
    private final AbsPlainButton prevOcc;
    private final AbsPlainButton nextOcc;
    private final AbsPlainButton closeFinder;
    private final AbsPanel finderPanel;
    private final AbsPanel panel;
    private final CustList<SegmentFindPart> parts = new CustList<SegmentFindPart>();
    private final AbsCommonFrame commonFrame;
    private final AbsPlainLabel label;
    private final AbsPlainLabel labelOcc;
    private int currentPart = -1;

    public TabEditor(WindowCdmEditor _editor) {
        windowEditor = _editor;
        commonFrame = _editor.getCommonFrame();
        AbstractProgramInfos frames_ = commonFrame.getFrames();
        factories = frames_;
        label = frames_.getCompoFactory().newPlainLabel(":");
        labelOcc = frames_.getCompoFactory().newPlainLabel("/");
        center = frames_.getCompoFactory().newTextPane();
        center.setFont(new MetaFont(GuiConstants.MONOSPACED,GuiConstants.fontStyle(false,false),12));
        center.setBackground(GuiConstants.BLACK);
        center.setForeground(GuiConstants.WHITE);
        center.setCaretColor(GuiConstants.WHITE);
        center.addCaretListener(new EditorCaretListener(this));
        center.addAutoComplete(new DocumentTextChange(this));
        AbsScrollPane sc_ = frames_.getCompoFactory().newAbsScrollPane(center);
        sc_.setPreferredSize(new MetaDimension(512,512));
        finder = frames_.getCompoFactory().newTextField();
        prevOcc = frames_.getCompoFactory().newPlainButton("<-");
        nextOcc = frames_.getCompoFactory().newPlainButton("->");
        closeFinder = frames_.getCompoFactory().newPlainButton("x");
        finderPanel = frames_.getCompoFactory().newLineBox();
        finderPanel.setVisible(false);
        finder.addAutoComplete(new FinderTextChange(this));
        finderPanel.add(finder);
        closeFinder.addActionListener(new ClosePanelAction(this));
        finderPanel.add(labelOcc);
        prevOcc.addActionListener(new ChgSegmentPartEvent(this,-1));
        finderPanel.add(prevOcc);
        nextOcc.addActionListener(new ChgSegmentPartEvent(this,1));
        finderPanel.add(nextOcc);
        finderPanel.add(closeFinder);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new FindAction(this)),GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK);
        panel = frames_.getCompoFactory().newPageBox();
        panel.add(sc_);
        panel.add(label);
        panel.add(finderPanel);
    }
    public void updateNavSelect() {
        if (getParts().isValidIndex(getCurrentPart())) {
            SegmentFindPart s_ = getParts().get(getCurrentPart());
            getCenter().select(s_.getBegin(),s_.getEnd());
        }
        updateNav();
    }
    public void updateNav() {
        int n_ = getCurrentPart();
        getLabelOcc().setText((n_+1)+"/"+getParts().size());
        prevOcc.setEnabled(!getParts().isEmpty());
        nextOcc.setEnabled(!getParts().isEmpty());
    }

    public AbsPlainLabel getLabelOcc() {
        return labelOcc;
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

    public AbsPlainButton getPrevOcc() {
        return prevOcc;
    }

    public AbsPlainButton getNextOcc() {
        return nextOcc;
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
