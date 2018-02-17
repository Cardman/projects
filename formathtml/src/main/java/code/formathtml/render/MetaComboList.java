package code.formathtml.render;

import code.util.Numbers;
import code.util.ints.SortableMap;

public final class MetaComboList extends MetaInput {
    private final int visible;
    private final SortableMap<String,String> choices;
    private final Numbers<Integer> selected;

    public MetaComboList(MetaContainer _parent, int _group, SortableMap<String,String> _choices) {
        this(_parent, _group, _choices, new Numbers<Integer>(), 1);
    }

    public MetaComboList(MetaContainer _parent, int _group, SortableMap<String,String> _choices, Numbers<Integer> _selected, int _visible) {
        super(_parent, _group);
        choices = _choices;
        selected = _selected;
        visible = _visible;
    }

    public int getVisible() {
        return visible;
    }

    public SortableMap<String, String> getChoices() {
        return choices;
    }

    public Numbers<Integer> getSelected() {
        return selected;
    }
}
