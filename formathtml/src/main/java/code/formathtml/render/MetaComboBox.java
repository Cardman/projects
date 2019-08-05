package code.formathtml.render;

import code.util.Ints;
import code.util.StringList;

public final class MetaComboBox extends MetaInput implements IntComboBox {

    private final long formNb;
    private final StringList choicesValues;
    private final StringList choicesStrings;
    private int selected;
    private Ints selectedIndexes;

    public MetaComboBox(MetaContainer _parent, int _group,
                        StringList _choicesStrings, StringList _choicesValues,
                        int _selected, Ints _selectedIndexes, long _formNb) {
        super(_parent, _group);
        choicesValues = _choicesValues;
        choicesStrings = _choicesStrings;
        selected = _selected;
        selectedIndexes = _selectedIndexes;
        formNb = _formNb;
    }

    public StringList getChoicesStrings() {
        return choicesStrings;
    }

    public StringList getChoicesValues() {
        return choicesValues;
    }

    public int getSelected() {
        return selected;
    }

    @Override
    public long getFormNb() {
        return formNb;
    }

    @Override
    public Ints getSelectedIndexes() {
        return selectedIndexes;
    }

    @Override
    public String getValue(int _index) {
        return choicesValues.get(_index);
    }
}
