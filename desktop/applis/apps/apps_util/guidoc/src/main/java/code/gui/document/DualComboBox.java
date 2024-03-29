package code.gui.document;

import code.formathtml.render.IntComboBox;
import code.formathtml.render.MetaComboBox;
import code.gui.AbsCustComponent;
import code.gui.GuiBaseUtil;
import code.gui.SelectableIndexes;
import code.util.Ints;
import code.util.StringList;

public final class DualComboBox extends DualInput implements IntComboBox {

    private final StringList choicesValues;

    public DualComboBox(DualContainer _container, MetaComboBox _component,
                        RenderedPage _page) {
        super(_container, _component, GuiBaseUtil.combo(_page.getGene().getImageFactory(),new StringList(_component.getChoicesStrings()), _component.getSelected(), _page.getCompoFactory()), _page);
        choicesValues = _component.getChoicesValues();
        updateGraphics(getSelect().getGlobal(),_component);
    }

    @Override
    public AbsCustComponent getGraphic() {
        return getSelect().getGlobal();
    }

    @Override
    public Ints getSelectedIndexes() {
        return getSelect().getSelectedIndexes();
    }

    @Override
    public String getValue(int _index) {
        return choicesValues.get(_index);
    }

    public void setSelectedIndexes(Ints _l) {
        ((SelectableIndexes)getSelect()).select(_l);
    }
}
