package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.core.*;

public final class GeneComponentModelPlace implements GeneComponentModel<Place>, ChangeableFormType {
    private final AbstractProgramInfos compoFactory;
    private final FacadeGame facade;
    private final SubscribedTranslationList translationList;
    private final AbsCommonFrame frame;
    private GeneComponentModelElt<String> placeKind;
    private Place edited;
    private GeneComponentModelText name;
    private final ContentComponentModelRoad road = new ContentComponentModelRoad();
    private short nbPlace = -1;
    private final CrudGeneFormEntPlace crud;
    public GeneComponentModelPlace(CrudGeneFormEntPlace _c, AbstractProgramInfos _core, FacadeGame _facadeGame, SubscribedTranslationList _subscription, AbsCommonFrame _fr) {
        crud = _c;
        compoFactory = _core;
        facade = _facadeGame;
        translationList = _subscription;
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
        form_.add(road.form(compoFactory,facade,translationList,frame));
        if (!GeneComponentModelEltStrCom.disable(_select, 0)) {
            placeKind.getSelect().addListener(new ChangingTypeEvent(this));
            ConverterCommonMapUtil.trigger(placeKind,MessagesEditorSelect.PLACE_CITY);
        }
        return form_;
    }

    @Override
    public void applyChange() {
        nbPlace = -1;
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
        road();
        road.display(eff_);
        placeKind.getSelect().repaint();
        frame.pack();
    }

    @Override
    public Place value() {
        edited.setName(name.valueString());
        if (edited instanceof Road) {
            translationList.setFormLevelGridUniq(null);
            ((Road)edited).getLevelRoad().setBlocks(road.getLevelWithWild().getLevel().getEdited());
            ((Road)edited).getLevelRoad().setWildPokemonAreas(road.getLevelWithWild().getAreas().getList());
        }
        return edited;
    }

    @Override
    public void value(Place _v) {
        nbPlace = (short) crud.getSelectedIndex();
        edited = _v;
        name.valueString(edited.getName());
        road();
    }

    private void road() {
        if (edited instanceof Road) {
            road.getLevelWithWild().getAreas().setupValues(ConverterCommonMapUtil.copyListArea(((Road)edited).getLevelRoad().getWildPokemonAreas()));
            Points<Block> blocks_ = ConverterCommonMapUtil.copyPointsBlock(((Road) edited).getLevelRoad().getBlocks());
            road.getLevelWithWild().setupGridDims(blocks_,nbPlace,(byte) 0,edited,((Road) edited).getLevelRoad());
            road.getLevelWithWild().getAreas().setBlocks(blocks_);
            displayRepaint(MessagesEditorSelect.PLACE_ROAD);
        }
    }

    private void displayRepaint(String _eff) {
        road.display(_eff);
        placeKind.setupValue(_eff);
        placeKind.getSelect().repaint();
    }
    public GeneComponentModelElt<String> getPlaceKind() {
        return placeKind;
    }

    public GeneComponentModelText getName() {
        return name;
    }

    public ContentComponentModelRoad getRoad() {
        return road;
    }
}
