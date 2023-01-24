package code.gui.document;

import code.formathtml.render.IntCheckBox;
import code.formathtml.render.MetaCheckedBox;
import code.gui.AbsCustCheckBox;
import code.gui.AbsCustComponent;
import code.sml.SetupableAnalyzingDoc;

public final class DualCheckedBox extends DualInput implements IntCheckBox {

    private final AbsCustCheckBox check;

    public DualCheckedBox(DualContainer _container, MetaCheckedBox _component,
                          RenderedPage _page) {
        super(_container, _component, _page);
        check = _page.getCompoFactory().newCustCheckBox("", _component.isChecked());
        updateGraphics(check,_component);
    }

    @Override
    public AbsCustComponent getGraphic() {
        return check;
    }

    @Override
    public String getValue() {
        if (check.isSelected()) {
            return SetupableAnalyzingDoc.ON;
        }
        return SetupableAnalyzingDoc.OFF;
    }

}
