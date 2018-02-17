package code.formathtml.render;

import code.util.ints.SortableMap;

public final class MetaComboBox extends MetaInput {

    private final SortableMap<String,String> choices;
    private final int selected;

    public MetaComboBox(MetaContainer _parent, int _group, SortableMap<String,String> _choices) {
        this(_parent, _group, _choices, 0);
    }

    public MetaComboBox(MetaContainer _parent, int _group, SortableMap<String,String> _choices, int _selected) {
        super(_parent, _group);
        choices = _choices;
        selected = _selected;
    }

    public SortableMap<String, String> getChoices() {
        return choices;
    }

    public int getSelected() {
        return selected;
    }
}
