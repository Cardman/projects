package code.gui.events;

public interface AbsAutoCompleteListener {

    void focusGained();

    void focusLost();

    void insertUpdate();

    void removeUpdate();

    void changedUpdate();
}
