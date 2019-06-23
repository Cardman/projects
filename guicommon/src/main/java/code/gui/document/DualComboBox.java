package code.gui.document;

import code.formathtml.render.MetaComboBox;
import code.gui.GraphicCombo;
import code.util.Ints;
import code.util.StringList;

public final class DualComboBox extends DualInput {

    private final StringList choicesValues;

    public DualComboBox(DualContainer _container, MetaComboBox _component,
                        RenderedPage _page) {
        super(_container, _component, new GraphicCombo(new StringList(_component.getChoicesStrings()), _component.getSelected()), _page);
        choicesValues = _component.getChoicesValues();
        updateGraphics(getSelect().getGlobal(),_component);
    }

    public Ints getSelectedIndexes() {
        return getSelect().getSelectedIndexes();
    }

    public String getValue() {
        Ints values_ = getSelect().getSelectedIndexes();
        if (values_.isEmpty()) {
            return "";
        }
        return choicesValues.get(values_.first());
    }

}
