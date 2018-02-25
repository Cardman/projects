package code.formathtml.render;

import code.util.StringList;

public final class MetaComboBox extends MetaInput {

    private final int selected;
    private final StringList choicesValues;
    private final StringList choicesStrings;

    public MetaComboBox(MetaContainer _parent, String _name, int _group, StringList _choicesStrings, StringList _choicesValues) {
        this(_parent, _name, _group, _choicesStrings, _choicesValues, 0);
    }

    public MetaComboBox(MetaContainer _parent, String _name, int _group, StringList _choicesStrings, StringList _choicesValues, int _selected) {
        super(_parent, _group, _name);
        choicesValues = _choicesValues;
        choicesStrings = _choicesStrings;
        selected = _selected;
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
}
