package code.gui;

import code.gui.events.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.Ints;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class AutoCompleteDocument implements AbsAutoCompleteListener {

    private final AbsEnabledAction upAction;
    private final AbsEnabledAction downAction;
    private final AbsEnabledAction enterAction;
    private final AbsEnabledAction escapeAction;
    private boolean wholeString = true;

    private final StringList dictionary = new StringList();

    private final AbsPopupMenu popup;

    private final AbsGraphicList<String> list;

    private final AbsTxtComponent textField;
    private final AbstractProgramInfos abs;
    private final AfterValidateText afterValidateText;

    public AutoCompleteDocument(AbsTxtComponent _field, StringList _aDictionary, AbstractProgramInfos _abs) {
        this(_field,_aDictionary,_abs,new DefValidateText());
    }
    public AutoCompleteDocument(AbsTxtComponent _field, StringList _aDictionary, AbstractProgramInfos _abs, AfterValidateText _after) {
        afterValidateText = _after;
        textField = _field;
        dictionary.addAllElts(_aDictionary);
        popup = _abs.getCompoFactory().newAbsPopupMenu();
        popup.setFocusable(false);
        popup.setVisible(false);
        abs = _abs;
        AbsGraphicList<String> comp_ = _abs.getGeneGraphicList().createStrList(_abs.getImageFactory(),new StringList(), _abs.getCompoFactory());
        list = comp_;
        popup.add(_abs.getCompoFactory().newAbsScrollPane(comp_.self()));
        textField.addAutoComplete(this);
        upAction = _abs.getCompoFactory().wrap(new AutoCompleteUpEvent(this));
        textField.registerKeyboardAction(upAction,GuiConstants.VK_UP,0);
        downAction = _abs.getCompoFactory().wrap(new AutoCompleteDownEvent(this));
        textField.registerKeyboardAction(downAction,GuiConstants.VK_DOWN,0);
        enterAction = _abs.getCompoFactory().wrap(new AutoCompleteEnterEvent(this));
        textField.registerKeyboardAction(enterAction,GuiConstants.VK_ENTER,0);
        escapeAction = _abs.getCompoFactory().wrap(new AutoCompleteEscapeEvent(this));
        textField.registerKeyboardAction(escapeAction,GuiConstants.VK_ESCAPE,0);
    }

    /**
     * Displays autocomplete popup at the correct location.
     */
    void showAutocompletePopup(){
        int height_ = textField.getHeight();
        popup.show(textField,0, height_);
    }

    /**
     * Closes autocomplete popup.
     */
    public void hideAutocompletePopup(){
        popup.setVisible(false);
    }

    public void enterEvent() {
        Ints ind_ = list.getSelectedIndexes();
        if (ind_.isEmpty()) {
            afterValidateText.act(textField,textField.getText().trim());
            return;
        }
        String text_ = list.get(ind_.first()).trim();
        list.clear();
        list.setSelectedIndice(-1);
        hideAutocompletePopup();
        afterValidateText.act(textField,text_);
    }

    public void downEvent() {
        int index_ = list.getSelectedIndex();
        if (index_ != -1 && list.getList().size() > index_ + 1) {
            list.clearAllRange();
            list.setSelectedIndice(index_ + 1);
        }
    }

    public void upEvent() {
        int index_ = list.getSelectedIndex();
        if (index_ > 0) {
            list.clearAllRange();
            list.setSelectedIndice(index_ - 1);
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

    private void documentChangedEvent() {
        if (!wholeString) {
            return;
        }
        abs.getCompoFactory().invokeNow(new DocumentChanged(this));
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
        list.setVisibleRowCount(NumberUtil.min(r_.size(),10));

        // Selecting first result
        list.setSelectedIndice(0);

        showAutocompletePopup();
    }

    public void setDictionary(StringList _dictionary) {
        dictionary.clear();
        dictionary.addAllElts(_dictionary);
    }

    public AbsGraphicList<String> getList() {
        return list;
    }

    public AbsTxtComponent getTextField() {
        return textField;
    }

    public AbsPopupMenu getPopup() {
        return popup;
    }

    public void setMode(boolean _wholeString) {
        wholeString = _wholeString;
        upAction.setEnabled(_wholeString);
        downAction.setEnabled(_wholeString);
        enterAction.setEnabled(_wholeString);
        escapeAction.setEnabled(_wholeString);
    }
}
