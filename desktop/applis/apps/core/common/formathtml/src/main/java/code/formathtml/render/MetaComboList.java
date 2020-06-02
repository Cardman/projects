package code.formathtml.render;

import code.util.Ints;
import code.util.StringList;

public final class MetaComboList extends MetaInput implements IntComboList {
    private final long formNb;
    private final int visible;
    private final StringList choicesValues;
    private final StringList choicesStrings;
    private final Ints selected;

    public MetaComboList(MetaContainer _parent, int _group, StringList _choicesStrings, StringList _choicesValues, Ints _selected, int _visible, long _formNb) {
        super(_parent, _group);
        choicesValues = _choicesValues;
        choicesStrings = _choicesStrings;
        selected = _selected;
        visible = _visible;
        formNb = _formNb;
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

    @Override
    public long getFormNb() {
        return formNb;
    }

    @Override
    public StringList getValue() {
        StringList values_ = new StringList();
        for (int i: selected) {
            values_.add(choicesValues.get(i));
        }
        return values_;
    }
}
