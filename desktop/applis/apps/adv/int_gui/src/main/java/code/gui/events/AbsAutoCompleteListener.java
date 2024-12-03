package code.gui.events;

public interface AbsAutoCompleteListener {

    void insertUpdate(int _off, int _len);

    void removeUpdate(int _off, int _len);

    void changedUpdate();
}
