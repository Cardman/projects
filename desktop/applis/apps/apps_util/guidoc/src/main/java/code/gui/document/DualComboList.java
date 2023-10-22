package code.gui.document;

import code.formathtml.render.IntComboList;
import code.formathtml.render.MetaComboList;
import code.gui.AbsCustComponent;
import code.gui.GuiBaseUtil;
import code.gui.ScrollCustomGraphicList;
import code.gui.SelectableIndexes;
import code.util.Ints;
import code.util.StringList;

public final class DualComboList extends DualInput implements IntComboList {

    private final StringList choicesValues;

    public DualComboList(DualContainer _container, MetaComboList _component,
                         RenderedPage _page) {
        super(_container, _component, combo(_page,_component), _page);
        choicesValues = _component.getChoicesValues();
        updateGraphics(getSelect().getGlobal(),_component);
    }

    private static ScrollCustomGraphicList<String> combo(RenderedPage _page, MetaComboList _component) {
        return GuiBaseUtil.standard(_page.getGene().getCompoFactory(), _page.getGene().getImageFactory(), false, _component.getChoicesStrings(), _component.getSelected(), _component.getVisible());
    }
    @Override
    public AbsCustComponent getGraphic() {
        return getSelect().getGlobal();
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

    public void setSelectedIndexes(Ints _l) {
        ((SelectableIndexes)getSelect()).select(_l);
    }
}
