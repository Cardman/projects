package code.formathtml.render;

import code.util.Ints;
import code.util.StringList;

public final class MetaComboList extends MetaInput {
    private final int visible;
    private final StringList choicesValues;
    private final StringList choicesStrings;
    private final Ints selected;

    public MetaComboList(MetaContainer _parent, int _group, StringList _choicesStrings, StringList _choicesValues) {
        this(_parent, _group, _choicesStrings, _choicesValues, new Ints(), 1);
    }

    public MetaComboList(MetaContainer _parent, int _group, StringList _choicesStrings, StringList _choicesValues, Ints _selected, int _visible) {
        super(_parent, _group);
        choicesValues = _choicesValues;
        choicesStrings = _choicesStrings;
        selected = _selected;
        visible = _visible;
    }

    public int getVisible() {
        return visible;
    }

    public StringList getChoicesStrings() {
        return choicesStrings;
    }

    public StringList getChoicesValues() {
        return choicesValues;
    }

    public Ints getSelected() {
        return selected;
    }
}
