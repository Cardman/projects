package code.gui.document;

import code.formathtml.render.MetaComboBox;
import code.gui.GraphicCombo;
import code.util.Numbers;
import code.util.StringList;

public final class DualComboBox extends DualInput {

    public DualComboBox(DualContainer _container, MetaComboBox _component,
             RenderedPage _page) {
        super(_container, _component, new GraphicCombo(new StringList(_component.getChoicesStrings()), _component.getSelected()), _page);
    }

    @Override
    public MetaComboBox getComponent() {
        return (MetaComboBox) super.getComponent();
    }

    public Numbers<Integer> getSelectedIndexes() {
        return getSelect().getSelectedIndexes();
    }

    @Override
    public String getValue() {
        Numbers<Integer> values_ = getSelect().getSelectedIndexes();
        if (values_.isEmpty()) {
            return "";
        }
        return getComponent().getChoicesValues().get(values_.first());
    }

}
