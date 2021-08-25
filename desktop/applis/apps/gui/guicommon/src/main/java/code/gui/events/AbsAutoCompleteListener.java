package code.gui.events;

public interface AbsAutoCompleteListener {
    void init();

    void hideAutocompletePopup();
    void focusGained();

    void focusLost();

    void insertUpdate();

    void removeUpdate();

    void changedUpdate();
}
