package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.evolution.*;
import aiki.instances.*;
import aiki.map.pokemon.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelEvolution implements ChangeableFormType{
    private final AbstractProgramInfos programInfos;
    private final FacadeGame fac;
    private GeneComponentModelElt<String> evolutionKind;
    private final GeneComponentModelInt level;
    private GeneComponentModelEltEnumSub<String> item;
    private GeneComponentModelEltEnumSub<String> evoTeamPokemon;
    private GeneComponentModelEltEnumSub<String> evoMove;
    private GeneComponentModelEltEnumSub<String> evoMoveType;
    private GeneComponentModelEltEnumSub<Gender> evoGender;
    private final SubscribedTranslationList subscribedTranslationList;
    private Evolution edited;
    private final AbsCommonFrame frame;
    private AbsSpinner compoLevel;
    private AbsCustComponent compoItem;
    private AbsCustComponent compoGender;
    private AbsCustComponent compoMove;
    private AbsCustComponent compoMoveType;
    private AbsCustComponent compoTeamPokemon;

    public GeneComponentModelEvolution(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _subscription) {
        frame = _f;
        programInfos = _core;
        fac = _facade;
        level = new GeneComponentModelInt(_core);
        subscribedTranslationList = _subscription;
    }

    public AbsPanel geneEvo() {
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectEvoTr(MessagesPkEditor.getAppliTr(programInfos.currentLg())).getMapping();
        evolutionKind = new GeneComponentModelElt<String>(programInfos, messages_,new EmptyDefValue());
        item = ConverterCommonMapUtil.buildItFull(programInfos, fac,subscribedTranslationList);
        evoTeamPokemon = ConverterCommonMapUtil.buildPkFull(programInfos,fac,subscribedTranslationList);
        evoMove = ConverterCommonMapUtil.buildMvFull(programInfos,fac,subscribedTranslationList);
        evoMoveType = ConverterCommonMapUtil.buildTypeElt(programInfos,fac,subscribedTranslationList);
        evoGender = ConverterCommonMapUtil.buildGender(programInfos,fac,subscribedTranslationList);
        AbsCompoFactory compoFactory_ = programInfos.getCompoFactory();
        AbsPanel evoForm_ = compoFactory_.newLineBox();
        AbsPanel selected_ = compoFactory_.newLineBox();
        evoForm_.add(evolutionKind.geneEnum());
        evoForm_.add(selected_);
        compoLevel = level.geneInt();
        compoLevel.setVisible(false);
        selected_.add(compoLevel);
        compoItem = item.geneEnum();
        compoItem.setVisible(false);
        selected_.add(compoItem);
        compoGender = evoGender.geneEnum();
        compoGender.setVisible(false);
        selected_.add(compoGender);
        compoMove = evoMove.geneEnum();
        compoMove.setVisible(false);
        selected_.add(compoMove);
        compoMoveType = evoMoveType.geneEnum();
        compoMoveType.setVisible(false);
        selected_.add(compoMoveType);
        compoTeamPokemon = evoTeamPokemon.geneEnum();
        compoTeamPokemon.setVisible(false);
        selected_.add(compoTeamPokemon);
        evolutionKind.getSelect().addListener(new ChangingTypeEvent(this));
        ConverterCommonMapUtil.trigger(evolutionKind,MessagesEditorSelect.EVO_LEVEL_SIMPLE);
        return evoForm_;
    }

    @Override
    public void applyChange() {
        String evo_ = evolutionKind.tryRet();
        display(evo_);
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_LEVEL_SIMPLE)) {
            edited = Instances.newEvolutionLevelSimple();
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_LEVEL_GENDER)) {
            edited = Instances.newEvolutionLevelGender();
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_STONE_SIMPLE)) {
            edited = Instances.newEvolutionStoneSimple();
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_STONE_GENDER)) {
            edited = Instances.newEvolutionStoneGender();
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_HAPPINESS)) {
            edited = Instances.newEvolutionHappiness();
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_ITEM)) {
            edited = Instances.newEvolutionItem();
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_MOVE)) {
            edited = Instances.newEvolutionMove();
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_MOVE_TYPE)) {
            edited = Instances.newEvolutionMoveType();
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_TEAM)) {
            edited = Instances.newEvolutionTeam();
        }
        evolutionKind.getSelect().repaint();
        frame.pack();
    }

    public Evolution valueEvo() {
        if (edited instanceof EvolutionLevelSimple) {
            ((EvolutionLevelSimple)edited).setLevel((short) level.valueInt());
        }
        if (edited instanceof EvolutionLevelGender) {
            ((EvolutionLevelGender)edited).setLevel((short) level.valueInt());
            ((EvolutionLevelGender)edited).setGender(evoGender.tryRet());
        }
        if (edited instanceof EvolutionStoneSimple) {
            ((EvolutionStoneSimple)edited).setStone(item.tryRet());
        }
        if (edited instanceof EvolutionStoneGender) {
            ((EvolutionStoneGender)edited).setStone(item.tryRet());
            ((EvolutionStoneGender)edited).setGender(evoGender.tryRet());
        }
        if (edited instanceof EvolutionItem) {
            ((EvolutionItem)edited).setItem(item.tryRet());
        }
        if (edited instanceof EvolutionMove) {
            ((EvolutionMove)edited).setMove(evoMove.tryRet());
        }
        if (edited instanceof EvolutionMoveType) {
            ((EvolutionMoveType)edited).setType(evoMoveType.tryRet());
        }
        if (edited instanceof EvolutionTeam) {
            ((EvolutionTeam)edited).setPokemon(evoTeamPokemon.tryRet());
        }
        return edited;
    }

    public void valueEvo(Evolution _evo) {
        if (_evo instanceof EvolutionLevelSimple) {
            level.valueInt(((EvolutionLevelSimple) _evo).getLevel());
            displayRepaint(MessagesEditorSelect.EVO_LEVEL_SIMPLE);
        }
        if (_evo instanceof EvolutionLevelGender) {
            level.valueInt(((EvolutionLevelGender) _evo).getLevel());
            evoGender.setupValue(((EvolutionLevelGender) _evo).getGender());
            displayRepaint(MessagesEditorSelect.EVO_LEVEL_GENDER);
        }
        if (_evo instanceof EvolutionStoneSimple) {
            item.setupValue(((EvolutionStoneSimple) _evo).getStone());
            displayRepaint(MessagesEditorSelect.EVO_STONE_SIMPLE);
        }
        if (_evo instanceof EvolutionStoneGender) {
            item.setupValue(((EvolutionStoneGender) _evo).getStone());
            evoGender.setupValue(((EvolutionStoneGender) _evo).getGender());
            displayRepaint(MessagesEditorSelect.EVO_STONE_GENDER);
        }
        if (_evo instanceof EvolutionHappiness) {
            displayRepaint(MessagesEditorSelect.EVO_HAPPINESS);
        }
        if (_evo instanceof EvolutionItem) {
            item.setupValue(((EvolutionItem) _evo).getItem());
            displayRepaint(MessagesEditorSelect.EVO_ITEM);
        }
        if (_evo instanceof EvolutionMove) {
            evoMove.setupValue(((EvolutionMove) _evo).getMove());
            displayRepaint(MessagesEditorSelect.EVO_MOVE);
        }
        if (_evo instanceof EvolutionMoveType) {
            evoMoveType.setupValue(((EvolutionMoveType) _evo).getType());
            displayRepaint(MessagesEditorSelect.EVO_MOVE_TYPE);
        }
        if (_evo instanceof EvolutionTeam) {
            evoTeamPokemon.setupValue(((EvolutionTeam) _evo).getPokemon());
            displayRepaint(MessagesEditorSelect.EVO_TEAM);
        }
        edited = _evo;
    }

    private void displayRepaint(String _eff) {
        display(_eff);
        getEvolutionKind().setupValue(_eff);
        getEvolutionKind().getSelect().repaint();
    }
    private void display(String _evo) {
        compoLevel.setVisible(StringUtil.quickEq(_evo,MessagesEditorSelect.EVO_LEVEL_SIMPLE) || StringUtil.quickEq(_evo,MessagesEditorSelect.EVO_LEVEL_GENDER));
        compoItem.setVisible(StringUtil.quickEq(_evo,MessagesEditorSelect.EVO_STONE_SIMPLE) || StringUtil.quickEq(_evo,MessagesEditorSelect.EVO_STONE_GENDER) || StringUtil.quickEq(_evo,MessagesEditorSelect.EVO_ITEM));
        compoGender.setVisible(StringUtil.quickEq(_evo,MessagesEditorSelect.EVO_LEVEL_GENDER) || StringUtil.quickEq(_evo,MessagesEditorSelect.EVO_STONE_GENDER));
        compoMove.setVisible(StringUtil.quickEq(_evo,MessagesEditorSelect.EVO_MOVE));
        compoMoveType.setVisible(StringUtil.quickEq(_evo,MessagesEditorSelect.EVO_MOVE_TYPE));
        compoTeamPokemon.setVisible(StringUtil.quickEq(_evo,MessagesEditorSelect.EVO_TEAM));
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(evoTeamPokemon.getSubs());
        ids_.addAllElts(evoMove.getSubs());
        ids_.addAllElts(evoMoveType.getSubs());
        ids_.addAllElts(item.getSubs());
        ids_.addAllElts(evoGender.getSubs());
        return ids_;
    }

    public GeneComponentModelEltEnumSub<Gender> getEvoGender() {
        return evoGender;
    }

    public GeneComponentModelElt<String> getEvolutionKind() {
        return evolutionKind;
    }

    public GeneComponentModelInt getLevel() {
        return level;
    }

    public GeneComponentModelEltEnumSub<String> getItem() {
        return item;
    }

    public GeneComponentModelEltEnumSub<String> getEvoMove() {
        return evoMove;
    }

    public GeneComponentModelEltEnumSub<String> getEvoMoveType() {
        return evoMoveType;
    }

    public GeneComponentModelEltEnumSub<String> getEvoTeamPokemon() {
        return evoTeamPokemon;
    }
}
