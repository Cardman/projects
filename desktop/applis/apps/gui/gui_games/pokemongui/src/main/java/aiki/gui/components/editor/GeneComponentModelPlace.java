package aiki.gui.components.editor;

import aiki.instances.*;
import aiki.map.places.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.core.*;

public final class GeneComponentModelPlace implements GeneComponentModel<Place>, ChangeableFormType {
    private final AbstractProgramInfos compoFactory;
    private final AbsCommonFrame frame;
    private GeneComponentModelElt<String> placeKind;
    private Place edited;
    private GeneComponentModelText name;
    public GeneComponentModelPlace(AbstractProgramInfos _core, AbsCommonFrame _fr) {
        compoFactory = _core;
        frame = _fr;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        placeKind = new GeneComponentModelElt<String>(compoFactory, MessagesPkEditor.getMessagesEditorSelectPlaceTr(MessagesPkEditor.getAppliTr(compoFactory.currentLg())).getMapping(), new EmptyDefValue());
        AbsCompoFactory compoFactory_ = compoFactory.getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(placeKind.geneEnum());
        name = new GeneComponentModelText(compoFactory);
        form_.add(name.geneString());
        if (!GeneComponentModelEltStrCom.disable(_select, 0)) {
            placeKind.getSelect().addListener(new ChangingTypeEvent(this));
            ConverterCommonMapUtil.trigger(placeKind,MessagesEditorSelect.PLACE_CITY);
        }
        return form_;
    }

    @Override
    public void applyChange() {
        String eff_ = placeKind.tryRet();
        if (StringUtil.quickEq(eff_, MessagesEditorSelect.PLACE_CITY)) {
            edited = Instances.newCity();
        }
        if (StringUtil.quickEq(eff_, MessagesEditorSelect.PLACE_ROAD)) {
            edited = Instances.newRoad();
        }
        if (StringUtil.quickEq(eff_, MessagesEditorSelect.PLACE_CAVE)) {
            edited = Instances.newCave();
        }
        if (StringUtil.quickEq(eff_, MessagesEditorSelect.PLACE_LEAGUE)) {
            edited = Instances.newLeague();
        }
        placeKind.getSelect().repaint();
        frame.pack();
    }

    @Override
    public Place value() {
        edited.setName(name.valueString());
        return edited;
    }

    @Override
    public void value(Place _v) {
        edited = _v;
        name.valueString(edited.getName());
    }

    public GeneComponentModelElt<String> getPlaceKind() {
        return placeKind;
    }

    public GeneComponentModelText getName() {
        return name;
    }
}
