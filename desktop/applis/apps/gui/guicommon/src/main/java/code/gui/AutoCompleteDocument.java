package code.gui;

import code.gui.events.AbsAutoCompleteListener;
import code.gui.events.AbsKeyListener;
import code.gui.initialize.AbstractProgramInfos;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AutoCompleteDocument implements AbsAutoCompleteListener, AbsKeyListener {

    private boolean wholeString = true;

    private final StringList dictionary = new StringList();

    private final AbsPopupMenu popup;

    private final AbsGraphicList<String> list;

    private final AbsTextField textField;
    private final ChangeableTitle changeableTitle;
    private final AbstractProgramInfos abs;

    public AutoCompleteDocument(AbsTextField _field, StringList _aDictionary, ChangeableTitle _changeableTitle, AbstractProgramInfos _abs) {
        textField = _field;
        changeableTitle = _changeableTitle;
        dictionary.addAllElts(_aDictionary);
        popup = _abs.getCompoFactory().newAbsPopupMenu();
        abs = _abs;
        AbsGraphicList<String> comp_ = _abs.getGeneGraphicList().createStrList(_abs.getImageFactory(),new StringList(), _abs.getCompoFactory());
        list = comp_;
        popup.add(_abs.getCompoFactory().newAbsScrollPane(comp_.self()));
        init();
    }

    private void init() {
        textField.addAutoComplete(this);
        textField.addKeyListener(this);
    }

    /**
     * Displays autocomplete popup at the correct location.
     */
    void showAutocompletePopup(){
        if (skip()) {
            return;
        }
        if (list.getList().isEmpty()) {
            return;
        }
        int height_ = textField.getHeight();
        AbsCustComponent par_ = textField;
        int x_ = changeableTitle.getLocationOnScreen().x;
        int y_ = changeableTitle.getLocationOnScreen().y+30;
        while (par_ != null) {
            x_ += par_.getXcoords();
            y_ += par_.getYcoords();
            par_ = par_.getParent();
        }
        popup.show(x_, y_ + height_);
    }

    @Override
    public void focusGained() {
        if (skip()) {
            return;
        }
        FrameUtil.invokeLater(new FocusGained(this), abs);
    }

    /**
     * Closes autocomplete popup.
     */
    void hideAutocompletePopup(){
        popup.setVisible(false);
    }

    @Override
    public void focusLost() {
        if (skip()) {
            return;
        }
        FrameUtil.invokeLater(new FocusLost(this), abs);
    }

    @Override
    public void keyPressed(AbsCtrlKeyState _e, char _keyChar, int _keyCode) {
        if (skip()) {
            return;
        }
        if (_keyCode == GuiConstants.VK_UP) {
            int index_ = list.getSelectedIndex();
            if (index_ > 0) {
                list.clearAllRange();
                list.setSelectedIndice(index_ - 1);
            }
        } else if (_keyCode == GuiConstants.VK_DOWN) {
            int index_ = list.getSelectedIndex();
            if (index_ != -1 && list.getList().size() > index_ + 1) {
                list.clearAllRange();
                list.setSelectedIndice(index_ + 1);
            }
        } else if (_keyCode == GuiConstants.VK_ENTER) {
            Ints ind_ = list.getSelectedIndexes();
            if (ind_.isEmpty()) {
                return;
            }
            String text_ = list.get(ind_.first());
            textField.setText(text_);
            textField.setCaretPosition(text_.length());
        } else {
            if (_keyCode == GuiConstants.VK_ESCAPE) {
                hideAutocompletePopup();
            }
        }
    }
    @Override
    public void insertUpdate(){
        documentChangedEvent();
    }

    @Override
    public void removeUpdate(){
        documentChangedEvent();
    }

    @Override
    public void changedUpdate() {
        documentChangedEvent();
    }

    @Override
    public void keyTyped(AbsCtrlKeyState _keyState, char _keyChar) {
        // Do nothing
    }

    @Override
    public void keyReleased(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        // Do nothing
    }
    private void documentChangedEvent() {
        if (skip()) {
            return;
        }
        FrameUtil.invokeLater(new DocumentChanged(this), abs);
    }
    void documentChanged() {
        // Updating results list
        StringList r_ = new StringList();
        String text_ = textField.getText();
        String tr_ = text_.trim();
        if (!tr_.isEmpty()) {
            for (String s : dictionary) {
                if (StringUtil.quickEq(s, tr_)) {
                    continue;
                }
                if (s.startsWith(tr_)) {
                    r_.add(s);
                }
            }
        }

        // Updating list view
        list.clear();
        for (String s: r_){
            list.add(s);
        }
        if (list.getList().isEmpty()) {
            hideAutocompletePopup();
            return;
        }
        list.setVisibleRowCount(Math.min(r_.size(),10));

        // Selecting first result
        list.setSelectedIndice(0);

        showAutocompletePopup();
    }

    public void setDictionary(StringList _dictionary) {
        dictionary.clear();
        dictionary.addAllElts(_dictionary);
    }

    private boolean skip() {
        return !wholeString;
    }

    public void setMode(boolean _wholeString) {
        wholeString = _wholeString;
    }
}
