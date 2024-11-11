package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.evolution.*;
import aiki.instances.*;
import aiki.map.pokemon.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelEvolution implements GeneComponentModel<EditedCrudPair<String, Evolution>> {
    private final AbstractProgramInfos programInfos;
    private GeneComponentModelEltStrSub geneComponentModelSelectKey;
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
    private final AbsCommonFrame frame;
    private AbsSpinner compoLevel;
    private AbsCustComponent compoItem;
    private AbsPanel compoGender;
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
    @Override
    public AbsCustComponent gene(int _select) {
        geneComponentModelSelectKey = ConverterCommonMapUtil.buildPkFull(programInfos, fac, subscribedTranslationList);
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectEvoTr(MessagesPkEditor.getAppliTr(programInfos.currentLg())).getMapping();
        evolutionKind = new GeneComponentModelEltEnum<String>(programInfos, messages_);
        item = ConverterCommonMapUtil.buildItFull(programInfos, fac,subscribedTranslationList);
        evoTeamPokemon = ConverterCommonMapUtil.buildPkFull(programInfos,fac,subscribedTranslationList);
        evoMove = ConverterCommonMapUtil.buildMvFull(programInfos,fac,subscribedTranslationList);
        evoMoveType = ConverterCommonMapUtil.buildTypeElt(programInfos,fac,subscribedTranslationList);
        evoGender = ConverterCommonMapUtil.buildGender(programInfos,fac);
        AbsCompoFactory compoFactory_ = programInfos.getCompoFactory();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(geneComponentModelSelectKey.geneEnum());
        AbsPanel evoForm_ = compoFactory_.newLineBox();
        AbsPanel selected_ = compoFactory_.newLineBox();
        evoForm_.add(evolutionKind.geneEnum(""));
        evoForm_.add(selected_);
        compoLevel = level.geneInt();
        compoLevel.setVisible(false);
        selected_.add(compoLevel);
        compoItem = item.geneEnum();
        compoItem.setVisible(false);
        selected_.add(compoItem);
        compoGender = evoGender.geneEnum(Gender.NO_GENDER);
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
        evolutionKind.getSelect().addListener(new ChangingEvolutionEvent(this));
        evolutionKind.getSelect().select(0);
        evolutionKind.getSelect().events(null);
        page_.add(evoForm_);
        return page_;
    }

    public void applyChange() {
        String evo_ = evolutionKind.tryRet("");
        compoLevel.setVisible(StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_LEVEL_SIMPLE) || StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_LEVEL_GENDER));
        compoItem.setVisible(StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_STONE_SIMPLE) || StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_STONE_GENDER) || StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_ITEM));
        compoGender.setVisible(StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_LEVEL_GENDER) || StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_STONE_GENDER));
        compoMove.setVisible(StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_MOVE));
        compoMoveType.setVisible(StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_MOVE_TYPE));
        compoTeamPokemon.setVisible(StringUtil.quickEq(evo_,MessagesEditorSelect.EVO_TEAM));
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
    @Override
    public EditedCrudPair<String, Evolution> value() {
        if (edited instanceof EvolutionLevelSimple) {
            ((EvolutionLevelSimple)edited).setLevel((short) level.valueInt());
        }
        if (edited instanceof EvolutionLevelGender) {
            ((EvolutionLevelGender)edited).setLevel((short) level.valueInt());
            ((EvolutionLevelGender)edited).setGender(evoGender.tryRet(Gender.NO_GENDER));
        }
        if (edited instanceof EvolutionStoneSimple) {
            ((EvolutionStoneSimple)edited).setStone(item.tryRet());
        }
        if (edited instanceof EvolutionStoneGender) {
            ((EvolutionStoneGender)edited).setStone(item.tryRet());
            ((EvolutionStoneGender)edited).setGender(evoGender.tryRet(Gender.NO_GENDER));
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
        return new EditedCrudPair<String, Evolution>(geneComponentModelSelectKey.tryRet(),edited);
    }

    @Override
    public void value(EditedCrudPair<String, Evolution> _v) {
        Evolution evo_ = _v.getValue();
        if (evo_ instanceof EvolutionLevelSimple) {
            evolutionKind.getSelect().select(0);
            evolutionKind.getSelect().events(null);
            level.valueInt(((EvolutionLevelSimple)evo_).getLevel());
        }
        if (evo_ instanceof EvolutionLevelGender) {
            evolutionKind.getSelect().select(1);
            evolutionKind.getSelect().events(null);
            level.valueInt(((EvolutionLevelGender)evo_).getLevel());
            evoGender.setupValue(((EvolutionLevelGender)evo_).getGender());
        }
        if (evo_ instanceof EvolutionStoneSimple) {
            evolutionKind.getSelect().select(2);
            evolutionKind.getSelect().events(null);
            item.setupValue(((EvolutionStoneSimple)evo_).getStone());
        }
        if (evo_ instanceof EvolutionStoneGender) {
            evolutionKind.getSelect().select(3);
            evolutionKind.getSelect().events(null);
            item.setupValue(((EvolutionStoneGender)evo_).getStone());
            evoGender.setupValue(((EvolutionStoneGender)evo_).getGender());
        }
        if (evo_ instanceof EvolutionHappiness) {
            evolutionKind.getSelect().select(4);
            evolutionKind.getSelect().events(null);
        }
        if (evo_ instanceof EvolutionItem) {
            evolutionKind.getSelect().select(5);
            evolutionKind.getSelect().events(null);
            item.setupValue(((EvolutionItem)evo_).getItem());
        }
        if (evo_ instanceof EvolutionMove) {
            evolutionKind.getSelect().select(6);
            evolutionKind.getSelect().events(null);
            evoMove.setupValue(((EvolutionMove)evo_).getMove());
        }
        if (evo_ instanceof EvolutionMoveType) {
            evolutionKind.getSelect().select(7);
            evolutionKind.getSelect().events(null);
            evoMoveType.setupValue(((EvolutionMoveType)evo_).getType());
        }
        if (evo_ instanceof EvolutionTeam) {
            evolutionKind.getSelect().select(8);
            evolutionKind.getSelect().events(null);
            evoTeamPokemon.setupValue(((EvolutionTeam)evo_).getPokemon());
        }
        edited = evo_;
        geneComponentModelSelectKey.setupValue(_v.getKey());
        geneComponentModelSelectKey.getSelectUniq().getSelect().setEnabled(false);
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(geneComponentModelSelectKey.getSubs());
        ids_.addAllElts(evoTeamPokemon.getSubs());
        ids_.addAllElts(evoMove.getSubs());
        ids_.addAllElts(evoMoveType.getSubs());
        ids_.addAllElts(item.getSubs());
        return ids_;
    }

    public GeneComponentModelEltStrSub getGeneComponentModelSelectKey() {
        return geneComponentModelSelectKey;
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
