package code.gui;

import code.gui.events.AbsKeyListener;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;
import code.util.core.StringUtil;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

public final class AutoCompleteDocument implements FocusListener, DocumentListener, AbsKeyListener {

    private boolean wholeString = true;

    private final StringList dictionary = new StringList();

    private final PopupMenu popup;

    private final AbsGraphicList<String> list;

    private final TextField textField;
    private final ChangeableTitle changeableTitle;

    public AutoCompleteDocument(TextField _field, StringList _aDictionary, ChangeableTitle _changeableTitle, AbstractProgramInfos _abs) {
        textField = _field;
        changeableTitle = _changeableTitle;
        dictionary.addAllElts(_aDictionary);
        popup = new PopupMenu();
        AbsGraphicList<String> comp_ = _abs.getGeneGraphicList().createStrList(_abs.getImageFactory(),new StringList());
        list = comp_;
        popup.add(new ScrollPane(comp_.self()));
        init();
    }

    private void init() {
        textField.addFocusListener(this);
        textField.getDocument().addDocumentListener(this);
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
        CustComponent par_ = textField;
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
    public void focusGained(FocusEvent _e) {
        if (skip()) {
            return;
        }
        CustComponent.invokeLater(new FocusGained(this));
    }

    /**
     * Closes autocomplete popup.
     */
    void hideAutocompletePopup(){
        popup.setVisible(false);
    }

    @Override
    public void focusLost(FocusEvent _e) {
        if (skip()) {
            return;
        }
        CustComponent.invokeLater(new FocusLost(this));
    }

    @Override
    public void keyPressed(AbsCtrlKeyState _e, char _keyChar, int _keyCode) {
        if (skip()) {
            return;
        }
        if (_keyCode == KeyEvent.VK_UP) {
            int index_ = list.getSelectedIndex();
            if (index_ > 0) {
                list.clearAllRange();
                list.setSelectedIndice(index_ - 1);
            }
        } else if (_keyCode == KeyEvent.VK_DOWN) {
            int index_ = list.getSelectedIndex();
            if (index_ != -1 && list.getList().size() > index_ + 1) {
                list.clearAllRange();
                list.setSelectedIndice(index_ + 1);
            }
        } else if (_keyCode == KeyEvent.VK_ENTER) {
            String text_ = list.getSelectedValue();
            textField.setText(text_);
            textField.setCaretPosition(text_.length());
        } else {
            if (_keyCode == KeyEvent.VK_ESCAPE) {
                hideAutocompletePopup();
            }
        }
    }
    @Override
    public void insertUpdate(DocumentEvent _e){
        documentChangedEvent();
    }

    @Override
    public void removeUpdate(DocumentEvent _e){
        documentChangedEvent();
    }

    @Override
    public void changedUpdate(DocumentEvent _e) {
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
        CustComponent.invokeLater(new DocumentChanged(this));
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
