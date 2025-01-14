package code.gui;

import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.Ints;
import code.util.StringList;
import code.util.core.NumberUtil;

public final class AutoCompleteDocument implements AbsAutoCompleteListener {

    private final AbsEnabledAction upAction;
    private final AbsEnabledAction downAction;
    private final AbsEnabledAction enterAction;
    private final AbsEnabledAction escapeAction;
    private boolean wholeString = true;

    private final StringList dictionary = new StringList();

    private final AbsPopupMenu popup;

    private final ScrollCustomGraphicList<String> list;

    private final AbsTxtComponent textField;
    private final AfterValidateText afterValidateText;
    private boolean applying;

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
        list = GuiBaseUtil.standard(_abs.getCompoFactory(), _abs.getImageFactory(), true);
        popup.add(list.getScrollPane());
        textField.addAutoComplete(this);
        upAction = _abs.getCompoFactory().wrap(new AutoCompleteUpEvent(this));
        textField.registerKeyboardAction(upAction,GuiConstants.VK_UP,0);
        downAction = _abs.getCompoFactory().wrap(new AutoCompleteDownEvent(this));
        textField.registerKeyboardAction(downAction,GuiConstants.VK_DOWN,0);
        enterAction = _abs.getCompoFactory().wrap(new AutoCompleteEnterEvent(this,_after));
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
            return;
        }
        String text_ = list.get(ind_.first()).trim();
        list.clear();
        list.select(-1);
        list.revalidate();
        list.repaint();
        hideAutocompletePopup();
        applying = true;
        afterValidateText.act(textField,text_);
        applying = false;
    }

    public void downEvent() {
        int index_ = list.getSelectedIndex();
        if (index_ != -1 && list.getList().size() > index_ + 1) {
            list.select(index_ + 1);
            list.revalidate();
            list.repaint();
        }
    }

    public void upEvent() {
        int index_ = list.getSelectedIndex();
        if (index_ > 0) {
            list.select(index_ - 1);
            list.revalidate();
            list.repaint();
        }
    }

    @Override
    public void insertUpdate(int _off, int _len){
        documentChangedEvent();
    }

    @Override
    public void removeUpdate(int _off, int _len){
        documentChangedEvent();
    }

    @Override
    public void changedUpdate(int _off, int _len) {
        documentChangedEvent();
    }

    private void documentChangedEvent() {
        if (!wholeString) {
            return;
        }
        documentChanged();
    }
    void documentChanged() {
        if (applying) {
            return;
        }
        // Updating results list
        String text_ = textField.getText();
        StringList r_ = afterValidateText.filter(text_,dictionary);

        // Updating list view
        list.clear();
        for (String s: r_){
            list.add(s);
        }
        if (list.getList().isEmpty()) {
            list.repaint();
            list.revalidate();
            hideAutocompletePopup();
            return;
        }
        list.getScrollPane().setPreferredSize(new MetaDimension(list.getScrollPane().getPreferredSizeValue().getWidth(),32*NumberUtil.min(r_.size(),10)));

        // Selecting first result
        list.select(0);
        list.revalidate();
        list.repaint();

        showAutocompletePopup();
    }

    public void setDictionary(StringList _dictionary) {
        dictionary.clear();
        dictionary.addAllElts(_dictionary);
    }

    public ScrollCustomGraphicList<String> getList() {
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
