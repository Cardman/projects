package code.gui;

import code.gui.events.AfterValidateText;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class GeneComponentModelEntryStringInteger implements GeneComponentModel<EditedCrudPair<String,Integer>> {
    private final AbstractProgramInfos factory;
    private final GeneComponentModelString key;
    private final GeneComponentModelInt value;
    public GeneComponentModelEntryStringInteger(AbstractProgramInfos _api,StringList _aDictionary, AfterValidateText _after) {
        factory = _api;
        key = new GeneComponentModelString(_api, _aDictionary, _after);
        value = new GeneComponentModelInt(_api);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        AbsPanel form_ = factory.getCompoFactory().newPageBox();
        form_.add(getKey().geneString());
        form_.add(getValue().geneInt());
        return form_;
    }

    @Override
    public EditedCrudPair<String, Integer> value() {
        return new EditedCrudPair<String, Integer>(key.valueString(), value.valueInt());
    }

    @Override
    public void value(EditedCrudPair<String, Integer> _v) {
        key.valueString(_v.getKey());
        key.getTextField().setEditable(false);
        value.valueInt(_v.getValue());
    }

    public GeneComponentModelString getKey() {
        return key;
    }

    public GeneComponentModelInt getValue() {
        return value;
    }
}
