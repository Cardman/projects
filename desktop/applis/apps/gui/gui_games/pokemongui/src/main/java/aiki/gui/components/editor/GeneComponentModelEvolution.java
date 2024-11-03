package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.evolution.*;
import aiki.instances.*;
import aiki.map.pokemon.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelEvolution implements GeneComponentModel<Evolution> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame fac;
    private GeneComponentModelEltEnum<String> evolutionKind;
    private final GeneComponentModelInt level;
    private GeneComponentModelEltStrSub item;
    private GeneComponentModelEltStrSub evoTeamPokemon;
    private GeneComponentModelEltStrSub evoMove;
    private GeneComponentModelEltStrSub evoMoveType;
    private GeneComponentModelEltEnum<Gender> evoGender;
    private final SubscribedTranslationList subscribedTranslationList;
    private Evolution edited;
    private AbsPanel selected;
    private final AbsCommonFrame frame;

    public GeneComponentModelEvolution(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _subscription) {
        frame = _f;
        programInfos = _core;
        fac = _facade;
        level = new GeneComponentModelInt(_core);
        subscribedTranslationList = _subscription;
    }
    @Override
    public AbsCustComponent gene() {
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectEvoTr(MessagesPkEditor.getAppliTr(programInfos.currentLg())).getMapping();
        evolutionKind = new GeneComponentModelEltEnum<String>(programInfos, messages_);
        AbsCompoFactory compoFactory_ = programInfos.getCompoFactory();
        AbsPanel evoForm_ = compoFactory_.newLineBox();
        selected = compoFactory_.newLineBox();
        evoForm_.add(evolutionKind.geneEnum(""));
        evoForm_.add(selected);
        evolutionKind.getSelect().addListener(new ChangingEvolutionEvent(this));
        evolutionKind.getSelect().select(0);
        evolutionKind.getSelect().events(null);
        return evoForm_;
    }

    public void applyChange() {
        selected.removeAll();
        String evo_ = evolutionKind.tryRet("");
        item = ConverterCommonMapUtil.buildItFull(programInfos, fac,subscribedTranslationList);
        evoTeamPokemon = ConverterCommonMapUtil.buildPkFull(programInfos,fac,subscribedTranslationList);
        evoMove = ConverterCommonMapUtil.buildMvFull(programInfos,fac,subscribedTranslationList);
        evoMoveType = ConverterCommonMapUtil.buildTypeElt(programInfos,fac,subscribedTranslationList);
        evoGender = ConverterCommonMapUtil.buildGender(programInfos,fac);
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_LEVEL_SIMPLE)) {
            edited = Instances.newEvolutionLevelSimple();
            selected.add(level.geneInt());
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_LEVEL_GENDER)) {
            edited = Instances.newEvolutionLevelGender();
            selected.add(level.geneInt());
            selected.add(evoGender.geneEnum(Gender.NO_GENDER));
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_STONE_SIMPLE)) {
            edited = Instances.newEvolutionStoneSimple();
            selected.add(item.geneEnum());
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_STONE_GENDER)) {
            edited = Instances.newEvolutionStoneGender();
            selected.add(item.geneEnum());
            selected.add(evoGender.geneEnum(Gender.NO_GENDER));
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_HAPPINESS)) {
            edited = Instances.newEvolutionHappiness();
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_ITEM)) {
            edited = Instances.newEvolutionItem();
            selected.add(item.geneEnum());
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_MOVE)) {
            edited = Instances.newEvolutionMove();
            selected.add(evoMove.geneEnum());
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_MOVE_TYPE)) {
            edited = Instances.newEvolutionMoveType();
            selected.add(evoMoveType.geneEnum());
        }
        if (StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_TEAM)) {
            edited = Instances.newEvolutionTeam();
            selected.add(evoTeamPokemon.geneEnum());
        }
        evolutionKind.getSelect().repaint();
        frame.pack();
    }
    @Override
    public Evolution value() {
        if (edited instanceof EvolutionLevelSimple) {
            ((EvolutionLevelSimple)edited).setLevel((short) level.valueInt());
        }
        if (edited instanceof EvolutionLevelGender) {
            ((EvolutionLevelGender)edited).setLevel((short) level.valueInt());
            ((EvolutionLevelGender)edited).setGender(evoGender.tryRet(Gender.NO_GENDER));
        }
        if (edited instanceof EvolutionStoneSimple) {
            ((EvolutionStoneSimple)edited).setStone(item.tryRet(""));
        }
        if (edited instanceof EvolutionStoneGender) {
            ((EvolutionStoneGender)edited).setStone(item.tryRet(""));
            ((EvolutionStoneGender)edited).setGender(evoGender.tryRet(Gender.NO_GENDER));
        }
        if (edited instanceof EvolutionItem) {
            ((EvolutionItem)edited).setItem(item.tryRet(""));
        }
        if (edited instanceof EvolutionMove) {
            ((EvolutionMove)edited).setMove(evoMove.tryRet(""));
        }
        if (edited instanceof EvolutionMoveType) {
            ((EvolutionMoveType)edited).setType(evoMoveType.tryRet(""));
        }
        if (edited instanceof EvolutionTeam) {
            ((EvolutionTeam)edited).setPokemon(evoTeamPokemon.tryRet(""));
        }
        return edited;
    }

    @Override
    public Evolution value(Evolution _v) {
        if (_v instanceof EvolutionLevelSimple) {
            evolutionKind.getSelect().select(0);
            evolutionKind.getSelect().events(null);
            level.valueInt(((EvolutionLevelSimple)_v).getLevel());
        }
        if (_v instanceof EvolutionLevelGender) {
            evolutionKind.getSelect().select(1);
            evolutionKind.getSelect().events(null);
            level.valueInt(((EvolutionLevelGender)_v).getLevel());
            evoGender.setupValue(((EvolutionLevelGender)_v).getGender());
        }
        if (_v instanceof EvolutionStoneSimple) {
            evolutionKind.getSelect().select(2);
            evolutionKind.getSelect().events(null);
            item.setupValue(((EvolutionStoneSimple)_v).getStone());
        }
        if (_v instanceof EvolutionStoneGender) {
            evolutionKind.getSelect().select(3);
            evolutionKind.getSelect().events(null);
            item.setupValue(((EvolutionStoneGender)_v).getStone());
            evoGender.setupValue(((EvolutionStoneGender)_v).getGender());
        }
        if (_v instanceof EvolutionHappiness) {
            evolutionKind.getSelect().select(4);
            evolutionKind.getSelect().events(null);
        }
        if (_v instanceof EvolutionItem) {
            evolutionKind.getSelect().select(5);
            evolutionKind.getSelect().events(null);
            item.setupValue(((EvolutionItem)_v).getItem());
        }
        if (_v instanceof EvolutionMove) {
            evolutionKind.getSelect().select(6);
            evolutionKind.getSelect().events(null);
            evoMove.setupValue(((EvolutionMove)_v).getMove());
        }
        if (_v instanceof EvolutionMoveType) {
            evolutionKind.getSelect().select(7);
            evolutionKind.getSelect().events(null);
            evoMoveType.setupValue(((EvolutionMoveType)_v).getType());
        }
        if (_v instanceof EvolutionTeam) {
            evolutionKind.getSelect().select(8);
            evolutionKind.getSelect().events(null);
            evoTeamPokemon.setupValue(((EvolutionTeam)_v).getPokemon());
        }
        edited = _v;
        return null;
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(evoTeamPokemon.getSubs());
        ids_.addAllElts(evoMove.getSubs());
        ids_.addAllElts(evoMoveType.getSubs());
        ids_.addAllElts(item.getSubs());
        return ids_;
    }

    public GeneComponentModelEltEnum<Gender> getEvoGender() {
        return evoGender;
    }

    public GeneComponentModelEltEnum<String> getEvolutionKind() {
        return evolutionKind;
    }

    public GeneComponentModelInt getLevel() {
        return level;
    }

    public GeneComponentModelEltStrSub getItem() {
        return item;
    }

    public GeneComponentModelEltStrSub getEvoMove() {
        return evoMove;
    }

    public GeneComponentModelEltStrSub getEvoMoveType() {
        return evoMoveType;
    }

    public GeneComponentModelEltStrSub getEvoTeamPokemon() {
        return evoTeamPokemon;
    }
}
