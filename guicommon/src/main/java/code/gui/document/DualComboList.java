package code.gui.document;

import code.formathtml.render.IntComboList;
import code.formathtml.render.MetaComboList;
import code.gui.GraphicStringList;
import code.util.Ints;
import code.util.StringList;

public final class DualComboList extends DualInput implements IntComboList {

    private final StringList choicesValues;

    public DualComboList(DualContainer _container, MetaComboList _component,
                         RenderedPage _page) {
        super(_container, _component, new GraphicStringList(false, false, new StringList(_component.getChoicesStrings()), _component.getSelected(),_component.getVisible()), _page);
        choicesValues = _component.getChoicesValues();
        updateGraphics(getSelect().getGlobal(),_component);
    }

    @Override
    public StringList getValue() {
        Ints indexes_ = getSelect().getSelectedIndexes();
        StringList values_ = new StringList();
        for (int i: indexes_) {
            values_.add(choicesValues.get(i));
        }
        return values_;
    }

}
