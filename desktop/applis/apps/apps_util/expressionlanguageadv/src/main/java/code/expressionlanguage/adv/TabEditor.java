package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.events.AbsEnabledAction;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractBaseExecutorService;
import code.util.CustList;
import code.util.StringList;

public final class TabEditor {
    private final WindowCdmEditor windowEditor;
    private final AbstractProgramInfos factories;
    private final AbsTextPane center;
    private final AbsTextField finder;
    private final AbsCustCheckBox caseSens;
    private final AbsCustCheckBox wholeWord;
    private final AbsTextField replacer;
    private final AbsPlainButton prevOcc;
    private final AbsPlainButton nextOcc;
    private final AbsPlainButton closeFinder;
    private final AbsPlainButton replaceOne;
    private final AbsPlainButton replaceAll;
    private final AbsPlainButton replacePrevious;
    private final AbsPlainButton replaceNext;
    private final AbsPanel navModifPanel;
    private final AbsPanel finderPanel;
    private final AbsPanel replacerPanel;
    private final AbsPanel panel;
    private final CustList<SegmentFindPart> parts = new CustList<SegmentFindPart>();
    private final AbsCommonFrame commonFrame;
    private final AbsPlainLabel label;
    private final AbsPlainLabel labelOcc;
    private final StringList texts = new StringList();
    private final AbsEnabledAction undo;
    private final AbsEnabledAction redo;
    private final AbstractBaseExecutorService taskManager;
    private boolean enabledSyntax = true;
    private int currentPart = -1;
    private int currentText = -1;
    private String fullPath;
    private final String useFeed;
    private int index=-1;
    private int dest;
    private final AbsSpinner row;
    private final AbsSpinner col;
    private final AbsPlainButton val;

    public TabEditor(WindowCdmEditor _editor, String _fullPath, String _lr) {
        useFeed = _lr;
        fullPath = _fullPath;
        windowEditor = _editor;
        commonFrame = _editor.getCommonFrame();
        AbstractProgramInfos frames_ = commonFrame.getFrames();
        taskManager = frames_.getThreadFactory().newExecutorService();
        factories = frames_;
        label = frames_.getCompoFactory().newPlainLabel(":");
        labelOcc = frames_.getCompoFactory().newPlainLabel("/");
        center = frames_.getCompoFactory().newTextPane();
        center.setFocusable(true);
        center.addMouseListener(new ClickTextPane(center));
        center.setFont(new MetaFont(GuiConstants.MONOSPACED,GuiConstants.fontStyle(false,false),12));
        center.setBackground(GuiConstants.BLACK);
        center.setForeground(GuiConstants.WHITE);
        center.setCaretColor(GuiConstants.WHITE);
        center.addCaretListener(new EditorCaretListener(this));
        center.addAutoComplete(new DocumentTextChange(this));
        AbsScrollPane sc_ = frames_.getCompoFactory().newAbsScrollPane(center);
        sc_.setPreferredSize(new MetaDimension(512,512));
        finder = frames_.getCompoFactory().newTextField();
        replacer = frames_.getCompoFactory().newTextField();
        prevOcc = frames_.getCompoFactory().newPlainButton("<-");
        nextOcc = frames_.getCompoFactory().newPlainButton("->");
        closeFinder = frames_.getCompoFactory().newPlainButton("x");
        replaceOne = frames_.getCompoFactory().newPlainButton("1");
        replaceOne.addActionListener(new ReplaceAction(this,false,false));
        replaceAll = frames_.getCompoFactory().newPlainButton("*");
        replaceAll.addActionListener(new ReplaceAction(this,true,true));
        replacePrevious = frames_.getCompoFactory().newPlainButton("<-");
        replacePrevious.addActionListener(new ReplaceAction(this,true,false));
        replaceNext = frames_.getCompoFactory().newPlainButton("->");
        replaceNext.addActionListener(new ReplaceAction(this,false,true));
        navModifPanel = frames_.getCompoFactory().newPageBox();
        navModifPanel.setVisible(false);
        finderPanel = frames_.getCompoFactory().newLineBox();
        AbsPanel colRowPanel_ = frames_.getCompoFactory().newLineBox();
        finder.addAutoComplete(new FinderTextChange(this));
        finderPanel.add(finder);
        caseSens = frames_.getCompoFactory().newCustCheckBox("Aa");
        caseSens.addActionListener(new ToggleFindOptionEvent(this));
        caseSens.setSelected(true);
        finderPanel.add(caseSens);
        wholeWord = frames_.getCompoFactory().newCustCheckBox("_");
        wholeWord.addActionListener(new ToggleFindOptionEvent(this));
        wholeWord.setSelected(true);
        finderPanel.add(wholeWord);
        row = frames_.getCompoFactory().newSpinner(1, 1, Integer.MAX_VALUE, 1);
        col = frames_.getCompoFactory().newSpinner(1, 1, Integer.MAX_VALUE, 1);
        row.addChangeListener(new RowColStateChangedEvent(this,row,col));
        colRowPanel_.add(row);
        col.addChangeListener(new RowColStateChangedEvent(this,row,col));
        colRowPanel_.add(col);
        val = frames_.getCompoFactory().newPlainButton("GO");
        val.addActionListener(new ValidateNavLine(this));
        colRowPanel_.add(val);
        closeFinder.addActionListener(new ClosePanelAction(this));
        finderPanel.add(labelOcc);
        prevOcc.addActionListener(new ChgSegmentPartEvent(this,-1));
        finderPanel.add(prevOcc);
        nextOcc.addActionListener(new ChgSegmentPartEvent(this,1));
        finderPanel.add(nextOcc);
        finderPanel.add(closeFinder);
        navModifPanel.add(finderPanel);
        navModifPanel.add(colRowPanel_);
        replacerPanel = frames_.getCompoFactory().newLineBox();
        replacerPanel.add(replacer);
        replacerPanel.add(replaceOne);
        replacerPanel.add(replaceAll);
        replacerPanel.add(replacePrevious);
        replacerPanel.add(replaceNext);
        navModifPanel.add(replacerPanel);
        undo = frames_.getCompoFactory().wrap(new UndoRedoAction(this, -1));
        redo = frames_.getCompoFactory().wrap(new UndoRedoAction(this, 1));
        undo.setEnabled(false);
        redo.setEnabled(false);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new FindAction(this, true)),GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new FindAction(this, false)),GuiConstants.VK_R,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new StoreUndoRedoAction(this)),GuiConstants.VK_Y,GuiConstants.CTRL_DOWN_MASK+GuiConstants.SHIFT_DOWN_MASK);
        center.registerKeyboardAction(undo,GuiConstants.VK_Z,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(redo,GuiConstants.VK_Y,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new ClearUndoRedoAction(this)),GuiConstants.VK_Z,GuiConstants.CTRL_DOWN_MASK+GuiConstants.SHIFT_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new SaveTextFileNode(this)),GuiConstants.VK_S,GuiConstants.CTRL_DOWN_MASK);
        center.registerKeyboardAction(frames_.getCompoFactory().wrap(new CloseTabEditorEvent(this)),GuiConstants.VK_K,GuiConstants.CTRL_DOWN_MASK);
        panel = frames_.getCompoFactory().newPageBox();
        panel.add(sc_);
        panel.add(label);
        panel.add(navModifPanel);
//        caseSens.setFocusable(false);
//        wholeWord.setFocusable(false);
//        finder.setFocusable(false);
//        prevOcc.setFocusable(false);
//        nextOcc.setFocusable(false);
//        closeFinder.setFocusable(false);
//        row.setFocusable(false);
//        col.setFocusable(false);
//        val.setFocusable(false);
    }
    public void afterValidate(int _dest) {
        index = _dest;
//        center.setEditable(true);
        center.requestFocus();
        center.select(dest, dest);
        center.visibleCaret();
//        center.setEditable(false);
        dest = 0;
    }
    public AbsSpinner getRow() {
        return row;
    }

    public AbsSpinner getCol() {
        return col;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _i) {
        this.index = _i;
    }

    public AbsPlainButton getVal() {
        return val;
    }

    public boolean enabled(int _row, int _col) {
        return index(_row, _col) > -1;
    }
    public int index(int _row, int _col) {
        int adjRow_ = _row - 1;
        int adjCol_ = _col - 1;
        int tw_ = windowEditor.getTabWidth();
        String txt_ = getCenter().getText();
        int index_ = 0;
        int row_ = 0;
        while (index_ >= 0) {
            int next_ = txt_.indexOf('\n',index_);
            if (row_ == adjRow_) {
                int j_ = tab(tw_, txt_, index_, adjCol_);
                if (j_ > -1) {
                    dest = index_ + j_;
                    return dest;
                }
                dest(next_,txt_);
                return -1;
            }
            if (next_ < 0) {
                index_=-1;
            } else {
                index_ = next_ + 1;
                row_++;
            }
        }
        dest(-1,txt_);
        return -1;
    }

    private void dest(int _index,String _txt) {
        if (_index > -1) {
            dest = _index;
            return;
        }
        dest = _txt.length();
    }

    private int tab(int _tw, String _txt, int _index, int _delta) {
        int j_ = 0;
        int d_ = 0;
        while (d_ < _delta) {
            int curIndex_ = j_ + _index;
            if (curIndex_ >= _txt.length()) {
                return -1;
            }
            char ch_ = _txt.charAt(curIndex_);
            if (ch_ == '\n') {
                return -1;
            }
            if (ch_ == '\t') {
                d_ += _tw;
                d_ -= d_ % _tw;
            } else {
                d_++;
            }
            j_++;
        }
        return j_;
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

    public String getUseFeed() {
        return useFeed;
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

    public AbsPanel getNavModifPanel() {
        return navModifPanel;
    }

    public AbsPanel getReplacerPanel() {
        return replacerPanel;
    }

    public AbsTextField getFinder() {
        return finder;
    }

    public AbsCustCheckBox getCaseSens() {
        return caseSens;
    }

    public AbsCustCheckBox getWholeWord() {
        return wholeWord;
    }

    public AbsPlainButton getReplaceOne() {
        return replaceOne;
    }

    public AbsPlainButton getReplaceAll() {
        return replaceAll;
    }

    public AbsPlainButton getReplacePrevious() {
        return replacePrevious;
    }

    public AbsPlainButton getReplaceNext() {
        return replaceNext;
    }

    public AbsTextField getReplacer() {
        return replacer;
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

    public AbstractBaseExecutorService getTaskManager() {
        return taskManager;
    }

    public CustList<SegmentFindPart> getParts() {
        return parts;
    }

    public StringList getTexts() {
        return texts;
    }

    public int getCurrentPart() {
        return currentPart;
    }

    public void setCurrentPart(int _c) {
        this.currentPart = _c;
    }

    public int getCurrentText() {
        return currentText;
    }

    public void setCurrentText(int _c) {
        this.currentText = _c;
    }

    public boolean isEnabledSyntax() {
        return enabledSyntax;
    }

    public void setEnabledSyntax(boolean _e) {
        this.enabledSyntax = _e;
    }

    public AbsEnabledAction getRedo() {
        return redo;
    }

    public AbsEnabledAction getUndo() {
        return undo;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String _f) {
        this.fullPath = _f;
    }
}
