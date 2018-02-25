package code.formathtml.render;

import code.util.Numbers;
import code.util.StringList;

public final class MetaComboList extends MetaInput {
    private final int visible;
    private final StringList choicesValues;
    private final StringList choicesStrings;
    private final Numbers<Integer> selected;

    public MetaComboList(MetaContainer _parent, String _name, int _group, StringList _choicesStrings, StringList _choicesValues) {
        this(_parent, _name, _group, _choicesStrings, _choicesValues, new Numbers<Integer>(), 1);
    }

    public MetaComboList(MetaContainer _parent, String _name, int _group, StringList _choicesStrings, StringList _choicesValues, Numbers<Integer> _selected, int _visible) {
        super(_parent, _group, _name);
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

    public Numbers<Integer> getSelected() {
        return selected;
    }
}
