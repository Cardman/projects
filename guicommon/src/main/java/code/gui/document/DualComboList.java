package code.gui.document;

import code.formathtml.render.MetaComboList;
import code.gui.GraphicStringList;
import code.util.Numbers;
import code.util.StringList;

public final class DualComboList extends DualInput {

    public DualComboList(DualContainer _container, MetaComboList _component,
             RenderedPage _page) {
        super(_container, _component, new GraphicStringList(false, false, new StringList(_component.getChoicesStrings())), _page);
    }

    @Override
    public MetaComboList getComponent() {
        return (MetaComboList) super.getComponent();
    }

    public Numbers<Integer> getSelectedIndexes() {
        return getSelect().getSelectedIndexes();
    }

    @Override
    public StringList getValue() {
        Numbers<Integer> indexes_ = getSelect().getSelectedIndexes();
        StringList values_ = new StringList();
        MetaComboList combo_ = getComponent();
        for (int i: indexes_) {
            values_.add(combo_.getChoicesValues().get(i));
        }
        return values_;
    }

}
