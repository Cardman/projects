package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.comparators.*;
import aiki.beans.fight.*;
import aiki.beans.game.*;
import aiki.comparators.*;
import aiki.db.*;
import aiki.fight.enums.*;
import aiki.game.*;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.game.fight.util.*;
import aiki.map.pokemon.enums.*;
import aiki.util.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public final class SimulationFighterForm extends SimulationCommonForm {
    private final SimulationBeanUpdateEntryValues<String, Long> status;
    private final SimulationBeanUpdateEntryValues<String, Long> nbUsesMoves;
    private final SimulationBeanAbstractAction coreAction;
    private final SimulationBeanUpdateEntryValues<String, Rate> damageRateInflictedByType;
    private final SimulationBeanUpdateEntryValues<String, Rate> damageRateSufferedByType;
    private final SimulationBeanUpdateEntryValues<String, Rate> damageSufferedCateg;
    private final SimulationBeanUpdateEntryValues<String, Rate> damageSufferedCategRound;
    private final SimulationBeanUpdateEntryValues<String, BoolVal> enabledMovesForAlly;
    private final StringMap<SimulationBeanUpdateEntryValues<String,UsesOfMove>> moves = new StringMap<SimulationBeanUpdateEntryValues<String, UsesOfMove>>();
    private final SimulationBeanUpdateEntryValues<MoveTeamPosition, Long> statusRelat;
    private final SimulationBeanUpdateEntryValues<MoveTeamPosition, BoolVal> incrUserAccuracy;
    private final SimulationBeanUpdateEntryValues<MoveTeamPosition, AffectedMove> trackingMoves;
    private final SimulationBeanUpdateEntryValues<MoveTeamPosition, ActivityOfMove> trappingMoves;
    private final SimulationBeanUpdateEntryValues<MoveTeamPosition, CustList<String>> privateMoves;
    private final SimulationBeanUpdateEntryValues<String, CopiedMove> copiedMoves;
    private final SimulationBeanUpdateEntryValues<String, ActivityOfMove> enabledMoves;
    private final SimulationBeanUpdateEntryValues<String, ActivityOfMove> enabledMovesProt;
    private final SimulationBeanUpdateEntryValues<String, ActivityOfMove> enabledMovesUnprot;
    private final SimulationBeanUpdateEntryValues<String, ActivityOfMove> enabledMovesEndRound;
    private final SimulationBeanUpdateEntryValues<String, ActivityOfMove> enabledMovesConstChoices;
    private final SimulationBeanUpdateEntryValues<String, ActivityOfMove> enabledChangingTypesMoves;
    private final SimulationBeanUpdateEntryValues<String, ActivityOfMove> enabledCounteringMoves;
    private SimulationBeanUpdateEntryValues<String, MovesAbilities> movesAbilitiesEvos;
    private SimulationBeanUpdateEntryValues<Statistic, Long> ev;
    private SimulationBeanUpdateEntryValues<Statistic, Long> iv;
    private SimulationBeanUpdateEntryValues<Statistic, Long> statisBoost;
    private SimulationBeanUpdateEntryValues<Statistic, Rate> statisBase;


    public SimulationFighterForm(SimulationBean _b, Fighter _f, int _max) {
        super(_b);
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_TITLE_GEN));
        coreAction = new SimulationBeanAbstractAction(_f, new IntBeanChgFighter(one(_f, getBean().getBuilder().getGenInput()), two(_f, getBean().getBuilder().getGenInput()), three(_f, getBean().getBuilder().getGenInput()), four(_f, getBean().getBuilder().getGenInput()), five(_f, getBean().getBuilder().getGenInput()), six(_f, getBean().getBuilder().getGenInput(), _max)));
        getBean().feedParents();
        status = movesFighter(dict(_f), _f.getStatus(), MessagesDataSimulation.M_P_86_STATUS, MessagesDataSimulation.M_P_86_STATUS_KEY, MessagesDataSimulation.M_P_86_STATUS_VALUE);
        nbUsesMoves = movesFighter(nbUses(_f), _f.getNbUsesMoves(), MessagesDataSimulation.M_P_86_NB_USES, MessagesDataSimulation.M_P_86_NB_USES_KEY, MessagesDataSimulation.M_P_86_NB_USES_VALUE);
        damageRateInflictedByType = typesFighter(typesFighter(_f.getDamageRateInflictedByType()), _f.getDamageRateInflictedByType(), MessagesDataSimulation.M_P_86_DAMAGE_POWER_TYPES_INF, MessagesDataSimulation.M_P_86_DAMAGE_POWER_TYPES_KEY, MessagesDataSimulation.M_P_86_DAMAGE_POWER_TYPES_VALUE_ONE);
        damageRateSufferedByType = typesFighter(typesFighter(_f.getDamageRateSufferedByType()), _f.getDamageRateSufferedByType(), MessagesDataSimulation.M_P_86_DAMAGE_POWER_TYPES_SUF, MessagesDataSimulation.M_P_86_DAMAGE_POWER_TYPES_KEY, MessagesDataSimulation.M_P_86_DAMAGE_POWER_TYPES_VALUE_TWO);
        damageSufferedCateg = typesFighter(catFighter(_f.getDamageSufferedCateg()), _f.getDamageSufferedCateg(), MessagesDataSimulation.M_P_86_SUFFERING_DAMAGE, MessagesDataSimulation.M_P_86_SUFFERING_DAMAGE_KEY, MessagesDataSimulation.M_P_86_SUFFERING_DAMAGE_VALUE_ROUND);
        damageSufferedCategRound = typesFighter(catFighter(_f.getDamageSufferedCategRound()), _f.getDamageSufferedCategRound(), MessagesDataSimulation.M_P_86_SUFFERING_DAMAGE, MessagesDataSimulation.M_P_86_SUFFERING_DAMAGE_KEY, MessagesDataSimulation.M_P_86_SUFFERING_DAMAGE_VALUE_USING);
        enabledMovesForAlly = accFighter(_f);
        useFighter(_f.getMoves(),MessagesDataSimulation.M_P_86_MOVES);
        useFighter(_f.getCurrentMoves(),MessagesDataSimulation.M_P_86_CURRENT_MOVES);
        statusRelat = statusRel(_f);
        incrUserAccuracy = incrAcc(_f);
        trackingMoves = affected(_f);
        trappingMoves = trap(_f);
        privateMoves = privateMoves(_f);
        copiedMoves = cpMoves(_f);
        evos(_f);
        enabledMoves = enFighter(_f.getEnabledMoves(), getBean().getDataBase().getMovesEffectIndivIncr());
        enabledMovesProt = enFighter(_f.getEnabledMovesProt(), getBean().getDataBase().getMovesEffectIndivIncr());
        enabledMovesUnprot = enFighter(_f.getEnabledMovesUnprot(), getBean().getDataBase().getMovesEffectIndivIncr());
        enabledMovesEndRound = enFighter(_f.getEnabledMovesEndRound(), getBean().getDataBase().getMovesEffEndRoundIndivIncr());
        enabledMovesConstChoices = enFighter(_f.getEnabledMovesConstChoices(), getBean().getDataBase().getMovesConstChoices());
        enabledChangingTypesMoves = enFighter(_f.getEnabledChangingTypesMoves(), getBean().getDataBase().getMovesChangingTypes());
        enabledCounteringMoves = enFighter(_f.getEnabledCounteringMoves(), getBean().getDataBase().getMovesCountering());
        stat(_f);
    }

    private DictionaryComparator<TranslatedKey, Long> dict(Fighter _f) {
        DictionaryComparator<TranslatedKey,Long> st_ = new DictionaryComparator<TranslatedKey, Long>(new ComparingTranslatedKey());
        for (EntryCust<String,Long> e: _f.getStatus().entryList()) {
            st_.put(CommonBean.buildSt(getBean().getFacade(),e.getKey()),e.getValue());
        }
        return st_;
    }

    private DictionaryComparator<TranslatedKey, Long> nbUses(Fighter _f) {
        DictionaryComparator<TranslatedKey,Long> st_ = new DictionaryComparator<TranslatedKey, Long>(new ComparingTranslatedKey());
        for (EntryCust<String,Long> e: _f.getNbUsesMoves().entryList()) {
            st_.put(CommonBean.buildMv(getBean().getFacade(),e.getKey()),e.getValue());
        }
        return st_;
    }

    private DictionaryComparator<TranslatedKey, Rate> typesFighter(StringMap<Rate> _info) {
        DictionaryComparator<TranslatedKey,Rate> st_ = new DictionaryComparator<TranslatedKey, Rate>(new ComparingTranslatedKey());
        for (EntryCust<String,Rate> e: _info.entryList()) {
            st_.put(CommonBean.buildTy(getBean().getFacade(),e.getKey()),e.getValue());
        }
        return st_;
    }

    private DictionaryComparator<TranslatedKey, Rate> catFighter(StringMap<Rate> _info) {
        DictionaryComparator<TranslatedKey,Rate> st_ = new DictionaryComparator<TranslatedKey, Rate>(new ComparingTranslatedKey());
        for (EntryCust<String,Rate> e: _info.entryList()) {
            st_.put(CommonBean.buildCa(getBean().getFacade(),e.getKey()),e.getValue());
        }
        return st_;
    }
    private SimulationBeanUpdateEntryValues<String,Long> movesFighter(AbsMap<TranslatedKey, Long> _d, AbsMap<String, Long> _i, String _key, String _h1, String _h2) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,_key));
        getBean().initGrid();
        getBean().getBuilder().colCount(2);
        getBean().headerCol(MessagesPkBean.SIMU,_h1);
        getBean().headerCol(MessagesPkBean.SIMU,_h2);
        StringMap<IntBeanChgValue<Long>> o_ = new StringMap<IntBeanChgValue<Long>>();
        for (EntryCust<TranslatedKey,Long> e:_d.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getTranslation());
            IntBeanChgLong chgPl_ = DifficultyBeanForm.iv(getBean().getBuilder().getGenInput(), getBean(), e.getValue());
            o_.addEntry(e.getKey().getKey(),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<String, Long>(_i,o_);
    }

    private SimulationBeanUpdateEntryValues<String,Rate> typesFighter(AbsMap<TranslatedKey, Rate> _d, AbsMap<String, Rate> _i, String _key, String _h1, String _h2) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,_key));
        getBean().initGrid();
        getBean().getBuilder().colCount(2);
        getBean().headerCol(MessagesPkBean.SIMU, _h1);
        getBean().headerCol(MessagesPkBean.SIMU, _h2);
        StringMap<IntBeanChgValue<Rate>> o_ = new StringMap<IntBeanChgValue<Rate>>();
        for (EntryCust<TranslatedKey,Rate> e:_d.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getTranslation());
            IntBeanChgRate chgPl_ = getBean().getBuilder().getGenInput().newRate();
            chgPl_.valueRate(e.getValue());
            o_.addEntry(e.getKey().getKey(),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<String, Rate>(_i,o_);
    }

    private SimulationBeanUpdateEntryValues<String,BoolVal> accFighter(Fighter _f) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_ENBALED_MOVES_ALLY));
        getBean().initGrid();
        getBean().getBuilder().colCount(2);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_ALLY_KEY);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_ALLY_ENABLED);
        DictionaryComparator<TranslatedKey, BoolVal> st_ = new DictionaryComparator<TranslatedKey, BoolVal>(new ComparingTranslatedKey());
        for (EntryCust<String,BoolVal> e: _f.getEnabledMovesForAlly().entryList()) {
            st_.put(CommonBean.buildMv(getBean().getFacade(),e.getKey()),e.getValue());
        }
        StringMap<IntBeanChgValue<BoolVal>> o_ = new StringMap<IntBeanChgValue<BoolVal>>();
        for (EntryCust<TranslatedKey,BoolVal> e:st_.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getTranslation());
            IntBeanChgBool chgPl_ = getBean().getBuilder().getGenInput().newBool();
            SimulationBean.selectCheck(chgPl_, e.getValue());
            o_.addEntry(e.getKey().getKey(),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<String, BoolVal>(_f.getEnabledMovesForAlly(),o_);
    }
    private void useFighter(StringMap<UsesOfMove> _us, String _key) {
        PageFormSimu formLocal_ = getBean().getBuilder().initPageForm(getBean());
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,_key));
        useFighterContent(_us, formLocal_,_key);
        getBean().getBuilder().feedParentForm();
    }

    public void useFighterContent(StringMap<UsesOfMove> _us, PageFormSimu _form, String _key) {
        getBean().initGrid();
        getBean().getBuilder().colCount(4);
        DictionaryComparator<TranslatedKey,UsesOfMove> st_ = new DictionaryComparator<TranslatedKey, UsesOfMove>(new ComparingTranslatedKey());
        for (EntryCust<String,UsesOfMove> e: _us.entryList()) {
            st_.put(CommonBean.buildMv(getBean().getFacade(),e.getKey()),e.getValue());
        }
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVES_KEY);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVES_VALUE_ONE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVES_VALUE_TWO);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ACTION);
        int len_ = _us.size();
        DictionaryComparator<String, String> map_ = DictionaryComparatorUtil.buildMvStr(getBean().getDataBase(), getBean().getLanguage());
        StringMap<String> fill_ = new StringMap<String>();
        fill_.putAllMap(getBean().getDataBase().getTranslatedMoves().getVal(getBean().getLanguage()));
        for (int i = 0; i < len_; i++) {
            fill_.removeKey(_us.getKey(i));
        }
        map_.putAllMap(fill_);
        if (!map_.isEmpty()) {
            IntBeanChgString key_ = getBean().getBuilder().getGenInput().newString(map_);
            IntBeanChgUsesOfMove value_ = getBean().getBuilder().getGenInput().newUse();
            getBean().getBuilder().button("+").addEvt(new SimulationBeanAddEntry<String,UsesOfMove>(_us,key_,value_, new UpdateFormUsesOfMove(getBean(),this,_us,_key), _form));
            getBean().getBuilder().nextPart();
        }
        StringMap<IntBeanChgValue<UsesOfMove>> o_ = new StringMap<IntBeanChgValue<UsesOfMove>>();
        for (EntryCust<TranslatedKey,UsesOfMove> e:st_.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getTranslation());
            IntBeanChgUsesOfMove chgPl_ = getBean().getBuilder().getGenInput().newUse();
            chgPl_.valueUse(e.getValue());
            getBean().initLine();
            getBean().getBuilder().button("-").addEvt(new SimulationBeanRemoveEntry<String,UsesOfMove>(_us,e.getKey().getKey(), new UpdateFormUsesOfMove(getBean(),this,_us,_key), _form));
            getBean().feedParentsCts();
            o_.addEntry(e.getKey().getKey(),chgPl_);
        }
        getBean().feedParents();
        moves.put(_key,new SimulationBeanUpdateEntryValues<String, UsesOfMove>(_us,o_));
    }

    private SimulationBeanUpdateEntryValues<MoveTeamPosition,Long> statusRel(Fighter _f) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_STATUS_REL));
        getBean().initGrid();
        getBean().getBuilder().colCount(3);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_STATUS_KEY);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TARGET);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_STATUS_REL_ENABLED);
        DictionaryComparator<MoveTeamPositionFighterName, Long> map_ = DictionaryComparatorUtil.buildMoveTeamPositionShort();
        for (EntryCust<MoveTeamPosition, Long> e: _f.getStatusRelat().entryList()) {
            String move_ = getBean().getDataBase().getTranslatedStatus().getVal(getBean().getLanguage()).getVal(e.getKey().getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, e.getKey().getTeamPosition());
            map_.put(new MoveTeamPositionFighterName(m_,e.getKey().getMove()),e.getValue());
        }
        MoveTeamPositions<IntBeanChgValue<Long>> o_ = new MoveTeamPositions<IntBeanChgValue<Long>>();
        for (EntryCust<MoveTeamPositionFighterName, Long> e:map_.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getMoveTeamPosition().getMove());
            getBean().formatMessageDirCts(e.getKey().getMoveTeamPosition().getTeamPosition().display());
            IntBeanChgLong chgPl_ = getBean().getBuilder().getGenInput().newLong();
            chgPl_.valueLong(e.getValue());
            o_.addEntry(new MoveTeamPosition(e.getKey().getName(),e.getKey().getMoveTeamPosition().getTeamPosition()),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<MoveTeamPosition,Long>(_f.getStatusRelat(),o_);
    }
    private SimulationBeanUpdateEntryValues<MoveTeamPosition,BoolVal> incrAcc(Fighter _f) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_INC_ACCURACY));
        getBean().initGrid();
        getBean().getBuilder().colCount(3);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TARGET);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_INC_ACCURACY_ENABLED);
        DictionaryComparator<MoveTeamPositionFighterName, Integer> map_ = DictionaryComparatorUtil.buildMoveTeamPositionBoolVal();
        for (EntryCust<MoveTeamPosition, BoolVal> e: _f.getIncrUserAccuracy().entryList()) {
            String move_ = getBean().getDataBase().getTranslatedMoves().getVal(getBean().getLanguage()).getVal(e.getKey().getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, e.getKey().getTeamPosition());
            map_.put(new MoveTeamPositionFighterName(m_,e.getKey().getMove()),CommonBean.toInt(e.getValue()));
        }
        MoveTeamPositions<IntBeanChgValue<BoolVal>> o_ = new MoveTeamPositions<IntBeanChgValue<BoolVal>>();
        for (EntryCust<MoveTeamPositionFighterName, Integer> e:map_.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getMoveTeamPosition().getMove());
            getBean().formatMessageDirCts(e.getKey().getMoveTeamPosition().getTeamPosition().display());
            IntBeanChgBool chgPl_ = getBean().getBuilder().getGenInput().newBool();
            chgPl_.setSelected(CommonBean.toBool(e.getValue()));
            o_.addEntry(new MoveTeamPosition(e.getKey().getName(),e.getKey().getMoveTeamPosition().getTeamPosition()),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<MoveTeamPosition,BoolVal>(_f.getIncrUserAccuracy(),o_);
    }
    private SimulationBeanUpdateEntryValues<MoveTeamPosition,AffectedMove> affected(Fighter _f) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_TRACKING_MOVES));
        getBean().initGrid();
        getBean().getBuilder().colCount(5);
        DictionaryComparator<MoveTeamPositionFighterName, AffectedMove> map_ = DictionaryComparatorUtil.buildMoveTeamPositionAffectedMove();
        for (EntryCust<MoveTeamPosition, AffectedMove> e: _f.getTrackingMoves().entryList()) {
            String move_ = getBean().getDataBase().getTranslatedMoves().getVal(getBean().getLanguage()).getVal(e.getKey().getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, e.getKey().getTeamPosition());
            map_.put(new MoveTeamPositionFighterName(m_,e.getKey().getMove()), e.getValue());
        }
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getBean().getDataBase(), getBean().getLanguage());
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TARGET);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TRACKING_MOVES_ENABLED);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TRACKING_MOVES_NB_ROUND);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TRACKING_MOVES_V);
        MoveTeamPositions<IntBeanChgValue<AffectedMove>> o_ = new MoveTeamPositions<IntBeanChgValue<AffectedMove>>();
        for (EntryCust<MoveTeamPositionFighterName, AffectedMove> e:map_.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getMoveTeamPosition().getMove());
            getBean().formatMessageDirCts(e.getKey().getMoveTeamPosition().getTeamPosition().display());
            IntBeanChgAffectedMove chgPl_ = getBean().getBuilder().getGenInput().newAff(mv_);
            chgPl_.valAff(e.getValue());
            o_.addEntry(new MoveTeamPosition(e.getKey().getName(),e.getKey().getMoveTeamPosition().getTeamPosition()),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<MoveTeamPosition,AffectedMove>(_f.getTrackingMoves(),o_);
    }
    private SimulationBeanUpdateEntryValues<MoveTeamPosition,ActivityOfMove> trap(Fighter _f) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_TRAPPING_MOVES));
        getBean().initGrid();
        getBean().getBuilder().colCount(4);
        DictionaryComparator<MoveTeamPositionFighterName, ActivityOfMoveStill> map_ = DictionaryComparatorUtil.buildMoveTeamPositionActivityOfMove();
        for (EntryCust<MoveTeamPosition, ActivityOfMove> e: _f.getTrappingMoves().entryList()) {
            String move_ = getBean().getDataBase().getTranslatedMoves().getVal(getBean().getLanguage()).getVal(e.getKey().getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, e.getKey().getTeamPosition());
            map_.put(new MoveTeamPositionFighterName(m_,e.getKey().getMove()), new ActivityOfMoveStill(e.getValue()));
        }
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TARGET);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_ENABLED);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_NB_ROUND);
        MoveTeamPositions<IntBeanChgValue<ActivityOfMove>> o_ = new MoveTeamPositions<IntBeanChgValue<ActivityOfMove>>();
        for (EntryCust<MoveTeamPositionFighterName, ActivityOfMoveStill> e:map_.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getMoveTeamPosition().getMove());
            getBean().formatMessageDirCts(e.getKey().getMoveTeamPosition().getTeamPosition().display());
            IntBeanChgActivityOfMove chgPl_ = getBean().getBuilder().getGenInput().newAc();
            chgPl_.valueActivity(e.getValue().getActivity());
            o_.addEntry(new MoveTeamPosition(e.getKey().getName(),e.getKey().getMoveTeamPosition().getTeamPosition()),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<MoveTeamPosition,ActivityOfMove>(_f.getTrappingMoves(),o_);
    }
    private SimulationBeanUpdateEntryValues<MoveTeamPosition,CustList<String>> privateMoves(Fighter _f) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_PRIVATE_MOVES));
        getBean().initGrid();
        getBean().getBuilder().colCount(3);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TARGET);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_PRIVATE_MOVES_MV);
        DictionaryComparator<MoveTeamPositionFighterName, CustList<String>> map_ = new DictionaryComparator<MoveTeamPositionFighterName, CustList<String>>(new ComparatorMoveTeamPosition());
        for (EntryCust<MoveTeamPosition, CustList<String>> e: _f.getPrivateMoves().entryList()) {
            String move_ = getBean().getDataBase().getTranslatedMoves().getVal(getBean().getLanguage()).getVal(e.getKey().getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, e.getKey().getTeamPosition());
            map_.put(new MoveTeamPositionFighterName(m_,e.getKey().getMove()), e.getValue());
        }
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getBean().getDataBase(), getBean().getLanguage());
        MoveTeamPositions<IntBeanChgValue<CustList<String>>> o_ = new MoveTeamPositions<IntBeanChgValue<CustList<String>>>();
        for (EntryCust<MoveTeamPositionFighterName, CustList<String>> e:map_.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getMoveTeamPosition().getMove());
            getBean().formatMessageDirCts(e.getKey().getMoveTeamPosition().getTeamPosition().display());
            IntBeanChgList<String> chgPl_ = getBean().getBuilder().getGenInput().newStringList(mv_);
            chgPl_.setupValue(e.getValue());
            o_.addEntry(new MoveTeamPosition(e.getKey().getName(),e.getKey().getMoveTeamPosition().getTeamPosition()),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<MoveTeamPosition,CustList<String>>(_f.getPrivateMoves(),o_);
    }
    private SimulationBeanUpdateEntryValues<String,CopiedMove> cpMoves(Fighter _f) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_COPIED_MOVES));
        getBean().initGrid();
        getBean().getBuilder().colCount(3);
        DictionaryComparator<TranslatedKey, CopiedMove> map_ = new DictionaryComparator<TranslatedKey, CopiedMove>(new ComparingTranslatedKey());
        for (EntryCust<String, CopiedMove> e: _f.getCopiedMoves().entryList()) {
            map_.put(CommonBean.buildMv(getBean().getFacade(),e.getKey()), e.getValue());
        }
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_COPIED_MOVES_PP);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_COPIED_MOVES_NEW);
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getBean().getDataBase(), getBean().getLanguage());
        StringMap<IntBeanChgValue<CopiedMove>> o_ = new StringMap<IntBeanChgValue<CopiedMove>>();
        for (EntryCust<TranslatedKey, CopiedMove> e:map_.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getTranslation());
            IntBeanChgCopiedMove chgPl_ = getBean().getBuilder().getGenInput().newCp(mv_);
            chgPl_.valCp(e.getValue());
            o_.addEntry(e.getKey().getKey(),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<String, CopiedMove>(_f.getCopiedMoves(), o_);
    }

    private void evos(Fighter _f) {
        PageFormSimu formLocal_ = getBean().getBuilder().initPageForm(getBean());
//        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMULATION,MessagesDataSimulation.MOVES));
        evosContent(_f, formLocal_);
        getBean().getBuilder().feedParentForm();
    }

    public void evosContent(Fighter _f, PageFormSimu _form) {
        getBean().initGrid();
        getBean().getBuilder().colCount(4);
        DictionaryComparator<TranslatedKey,MovesAbilities> st_ = new DictionaryComparator<TranslatedKey, MovesAbilities>(new ComparingTranslatedKey());
        for (EntryCust<String, MovesAbilities> e: _f.getMovesAbilitiesEvos().entryList()) {
            st_.put(CommonBean.buildPk(getBean().getFacade(),e.getKey()),e.getValue());
        }
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EVO_PK);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EVO_PK_MOVES);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EVO_PK_ABILITIES);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ACTION);
        DictionaryComparator<String, String> ab_ = DictionaryComparatorUtil.buildAbStrElts(getBean().getDataBase(), getBean().getLanguage());
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getBean().getDataBase(), getBean().getLanguage());
        int len_ = _f.getMovesAbilitiesEvos().size();
        DictionaryComparator<String, String> map_ = DictionaryComparatorUtil.buildMvStr(getBean().getDataBase(), getBean().getLanguage());
        StringMap<String> fill_ = new StringMap<String>();
        fill_.putAllMap(getBean().getDataBase().getTranslatedPokemon().getVal(getBean().getLanguage()));
        for (int i = 0; i < len_; i++) {
            fill_.removeKey(_f.getMovesAbilitiesEvos().getKey(i));
        }
        map_.putAllMap(fill_);
        if (!map_.isEmpty()) {
            IntBeanChgString key_ = getBean().getBuilder().getGenInput().newString(map_);
            IntBeanChgMovesAbilities value_ = getBean().getBuilder().getGenInput().newEvo(mv_,ab_);
            getBean().getBuilder().button("+").addEvt(new SimulationBeanAddEntry<String,MovesAbilities>(_f.getMovesAbilitiesEvos(),key_,value_, new UpdateFormEvosFighter(getBean(),this,_f), _form));
            getBean().getBuilder().nextPart();
        }
        StringMap<IntBeanChgValue<MovesAbilities>> o_ = new StringMap<IntBeanChgValue<MovesAbilities>>();
        for (EntryCust<TranslatedKey,MovesAbilities> e:st_.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getTranslation());
            getBean().initLine();
            IntBeanChgMovesAbilities chgPl_ = getBean().getBuilder().getGenInput().newEvo(mv_,ab_);
            chgPl_.valEvo(e.getValue());
            getBean().feedParentsCts();
            getBean().initLine();
            o_.addEntry(e.getKey().getKey(),chgPl_);
            getBean().getBuilder().button("-").addEvt(new SimulationBeanRemoveEntry<String,MovesAbilities>(_f.getMovesAbilitiesEvos(),e.getKey().getKey(), new UpdateFormEvosFighter(getBean(),this,_f), _form));
            getBean().feedParentsCts();
        }
        getBean().feedParents();
        movesAbilitiesEvos = new SimulationBeanUpdateEntryValues<String, MovesAbilities>(_f.getMovesAbilitiesEvos(), o_);
    }

    private SimulationBeanUpdateEntryValues<String,ActivityOfMove> enFighter(StringMap<ActivityOfMove> _moves, CustList<String> _incr) {
        DictionaryComparator<TranslatedKey, ActivityOfMove> sorted_ = sortedAc(_moves);
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU, MessagesFightFighter.M_P_91_ENBALED_MOVES));
        getBean().initGrid();
        getBean().getBuilder().colCount(3);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_ENABLED);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_NB_ROUND);
        StringMap<IntBeanChgValue<ActivityOfMove>> o_ = new StringMap<IntBeanChgValue<ActivityOfMove>>();
        for (EntryCust<TranslatedKey,ActivityOfMove> e: sorted_.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getTranslation());
            IntBeanChgActivityOfMove chgPl_ = getBean().getBuilder().getGenInput().newAc(StringUtil.contains(_incr,e.getKey().getKey()));
            chgPl_.valueActivity(e.getValue());
            getBean().getBuilder().nextPart();
            o_.addEntry(e.getKey().getKey(),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<String, ActivityOfMove>(_moves, o_);
    }
    private void stat(Fighter _f) {
        IdMap<Statistic, String> stat_ = getBean().getDataBase().getTranslatedStatistics().getVal(getBean().getLanguage());
        ev = longs(_f.getEv(), stat_, MessagesDataSimulation.M_P_86_STATISTICS_EV);
        iv = longs(_f.getIv(), stat_, MessagesDataSimulation.M_P_86_STATISTICS_IV);
        statisBoost = longs(_f.getStatisBoost(), stat_, MessagesDataSimulation.M_P_86_STATISTICS_BOOST);
        statisBase = rate(_f.getStatisBase(), stat_);
    }

    private SimulationBeanUpdateEntryValues<Statistic,Long> longs(IdMap<Statistic, Long> _f, IdMap<Statistic, String> _stat, String _title) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU,_title));
        getBean().initGrid();
        getBean().getBuilder().colCount(2);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_STATISTICS_KEY);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_STATISTICS_VALUE);
        IdMap<Statistic,IntBeanChgValue<Long>> o_ = new IdMap<Statistic, IntBeanChgValue<Long>>();
        for (EntryCust<Statistic,Long> e: _f.entryList()) {
            getBean().formatMessageDirCts(_stat.getVal(e.getKey()));
            IntBeanChgLong chgPl_ = getBean().getBuilder().getGenInput().newLong();
            chgPl_.valueLong(e.getValue());
            getBean().getBuilder().nextPart();
            o_.addEntry(e.getKey(),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<Statistic, Long>(_f, o_);
    }

    private SimulationBeanUpdateEntryValues<Statistic,Rate> rate(IdMap<Statistic, Rate> _f, IdMap<Statistic, String> _stat) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_STATISTICS_BASE));
        getBean().initGrid();
        getBean().getBuilder().colCount(2);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_STATISTICS_KEY);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_STATISTICS_VALUE);
        IdMap<Statistic,IntBeanChgValue<Rate>> o_ = new IdMap<Statistic, IntBeanChgValue<Rate>>();
        for (EntryCust<Statistic,Rate> e: _f.entryList()) {
            getBean().formatMessageDirCts(_stat.getVal(e.getKey()));
            IntBeanChgRate chgPl_ = getBean().getBuilder().getGenInput().newRate();
            chgPl_.valueRate(e.getValue());
            getBean().getBuilder().nextPart();
            o_.addEntry(e.getKey(),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<Statistic,Rate>(_f, o_);
    }

    private IntBeanChgFighter1 one(Fighter _f, IntBeanGeneInput _inputGene) {
        AbsMap<Gender, String> translatedGenders_ = getBean().getDataBase().getTranslatedGenders().getVal(getBean().getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> pk_ = DictionaryComparatorUtil.buildPkStrElts(getBean().getDataBase(), getBean().getLanguage());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NAME);
        IntBeanChgString name_ = _inputGene.newString(pk_);
        name_.setupValue(_f.getName());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NICKNAME);
        IntBeanChgString nickname_ = _inputGene.newText();
        nickname_.setupValue(_f.getNickname());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_GENDER);
        IntBeanChgGender gender_ = _inputGene.newGender(genders_);
        gender_.valGender(_f.getGender());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_WEIGHT);
        IntBeanChgRate weight_ = _inputGene.newRate();
        weight_.valueRate(_f.getWeight());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_HEIGHT);
        IntBeanChgRate height_ = _inputGene.newRate();
        height_.valueRate(_f.getHeight());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_CURRENT_NAME);
        IntBeanChgString currentName_ = _inputGene.newString(pk_);
        currentName_.setupValue(_f.getCurrentName());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_CURRENT_GENDER);
        IntBeanChgGender currentGender_ = _inputGene.newGender(genders_);
        currentGender_.valGender(_f.getCurrentGender());
        return new IntBeanChgFighter1(name_, nickname_, gender_, weight_, height_, currentName_, currentGender_);
    }

    private IntBeanChgFighter2 two(Fighter _f, IntBeanGeneInput _inputGene) {
        AbsMap<Gender, String> translatedGenders_ = getBean().getDataBase().getTranslatedGenders().getVal(getBean().getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> ab_ = DictionaryComparatorUtil.buildAbStrElts(getBean().getDataBase(), getBean().getLanguage());
        ab_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DictionaryComparator<String, String> it_ = DictionaryComparatorUtil.buildItemsStrElts(getBean().getDataBase(), getBean().getLanguage());
        it_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DictionaryComparator<String, String> ty_ = DictionaryComparatorUtil.buildTyStrElts(getBean().getDataBase(), getBean().getLanguage());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_LAST_USED_ITEM);
        IntBeanChgString lastUsedItem_ = _inputGene.newString(it_);
        lastUsedItem_.setupValue(_f.getLastUsedItem());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_ITEM);
        IntBeanChgString item_ = _inputGene.newString(it_);
        item_.setupValue(_f.getItem());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_CURRENT_ABILITY);
        IntBeanChgString currentAbility_ = _inputGene.newString(ab_);
        currentAbility_.setupValue(_f.getCurrentAbility());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NB_ROUNDS);
        IntBeanChgLgInt nbRounds_ = _inputGene.newLgInt();
        nbRounds_.valueLgInt(_f.getNbRounds());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_TYPES);
        IntBeanChgList<String> types_ = _inputGene.newStringList(ty_);
        types_.setupValue(_f.getTypes());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_ABILITY);
        IntBeanChgString ability_ = _inputGene.newString(ab_);
        ability_.setupValue(_f.getAbility());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_REMAIN_HP);
        IntBeanChgRate remainingHp_ = _inputGene.newRate();
        remainingHp_.valueRate(_f.getRemainingHp());
        return new IntBeanChgFighter2(lastUsedItem_, item_, currentAbility_, nbRounds_, types_, ability_, remainingHp_);
    }

    private IntBeanChgFighter3 three(Fighter _f, IntBeanGeneInput _inputGene) {
        AbsMap<Gender, String> translatedGenders_ = getBean().getDataBase().getTranslatedGenders().getVal(getBean().getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> ty_ = DictionaryComparatorUtil.buildTyStrElts(getBean().getDataBase(), getBean().getLanguage());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_ACTED);
        IntBeanChgBool acted_ = _inputGene.newBool();
        acted_.setSelected(_f.isActed());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_GROUND_PLACE);
        IntMap<String> ids_ = SimulationCommonForm.ids(getBean().getSimulation().getGame().getFight().getMult());
        IntBeanChgInt groundPlace_ = _inputGene.newInt(ids_);
        groundPlace_.valueInt(_f.getGroundPlace());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_GROUND_SUBSTIT);
        IntBeanChgInt groundPlaceSubst_ = _inputGene.newInt(ids_);
        groundPlaceSubst_.valueInt(_f.getGroundPlaceSubst());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_WON_EXP);
        IntBeanChgRate wonExp_ = _inputGene.newRate();
        wonExp_.valueRate(_f.getWonExp());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_TYPES_PROTECTED);
        IntBeanChgList<String> protectedAgainstMoveTypes_ = _inputGene.newStringList(ty_);
        protectedAgainstMoveTypes_.setupValue(_f.getProtectedAgainstMoveTypes());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_WON_EXP_LAST_LEVEL);
        IntBeanChgRate wonExpSinceLastLevel_ = _inputGene.newRate();
        wonExpSinceLastLevel_.valueRate(_f.getWonExpSinceLastLevel());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_CLONE);
        IntBeanChgRate clone_ = _inputGene.newRate();
        clone_.valueRate(_f.getClone());
        return new IntBeanChgFighter3(acted_, groundPlace_, groundPlaceSubst_, wonExp_, protectedAgainstMoveTypes_, wonExpSinceLastLevel_, clone_);
    }

    private IntBeanChgFighter4 four(Fighter _f, IntBeanGeneInput _inputGene) {
        AbsMap<Gender, String> translatedGenders_ = getBean().getDataBase().getTranslatedGenders().getVal(getBean().getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> it_ = DictionaryComparatorUtil.buildItemsStrElts(getBean().getDataBase(), getBean().getLanguage());
        it_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getBean().getDataBase(), getBean().getLanguage());
        mv_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_USED_BALL_CATCHING);
        IntBeanChgString usedBallCatching_ = _inputGene.newString(it_);
        usedBallCatching_.setupValue(_f.getUsedBallCatching());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_LEVEL);
        IntBeanChgLong level_ = _inputGene.newLong();
        level_.valueLong(_f.getLevel());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_HAPPINESS);
        IntBeanChgLong happiness_ = _inputGene.newLong();
        happiness_.valueLong(_f.getHappiness());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NB_ROUND_PREPA);
        IntBeanChgLong nbPrepaRound_ = _inputGene.newLong();
        nbPrepaRound_.valueLong(_f.getNbPrepaRound());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_DISAPPEARED);
        IntBeanChgBool disappeared_ = _inputGene.newBool();
        disappeared_.setSelected(_f.isDisappeared());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NEEDING_RECHARGE);
        IntBeanChgBool needingToRecharge_ = _inputGene.newBool();
        needingToRecharge_.setSelected(_f.isNeedingToRecharge());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_LAST_SUFFERED_MOVE);
        IntBeanChgString lastSufferedMove_ = _inputGene.newString(mv_);
        lastSufferedMove_.setupValue(_f.getLastSufferedMove());
        return new IntBeanChgFighter4(usedBallCatching_, level_, happiness_, nbPrepaRound_, disappeared_, needingToRecharge_, lastSufferedMove_);
    }

    private IntBeanChgFighter5 five(Fighter _f, IntBeanGeneInput _inputGene) {
        AbsMap<Gender, String> translatedGenders_ = getBean().getDataBase().getTranslatedGenders().getVal(getBean().getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getBean().getDataBase(), getBean().getLanguage());
        mv_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DictionaryComparator<String, String> ty_ = DictionaryComparatorUtil.buildTyStrElts(getBean().getDataBase(), getBean().getLanguage());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_LAST_SUFFERED_MOVE_TYPES);
        IntBeanChgList<String> lastSufferedMoveTypes_ = _inputGene.newStringList(ty_);
        lastSufferedMoveTypes_.setupValue(_f.getLastSufferedMoveTypes());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_INVOKED_MOVES);
        IntBeanChgList<String> alreadyInvokedMovesRound_ = _inputGene.newStringList(DictionaryComparatorUtil.buildMvStrElts(getBean().getDataBase(), getBean().getLanguage()));
        alreadyInvokedMovesRound_.setupValue(_f.getAlreadyInvokedMovesRound());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_LAST_SUCCESSFUL_MOVE);
        IntBeanChgString lastSuccessfulMove_ = _inputGene.newString(mv_);
        lastSuccessfulMove_.setupValue(_f.getLastSuccessfulMove());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NB_SUCCESS_MOVES);
        IntBeanChgLgInt nbRepeatingSuccessfulMoves_ = _inputGene.newLgInt();
        nbRepeatingSuccessfulMoves_.valueLgInt(_f.getNbRepeatingSuccessfulMoves());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_USING_ITEM);
        IntBeanChgBool usingItem_ = _inputGene.newBool();
        usingItem_.setSelected(_f.isUsingItem());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_USED_MOVE_LAST_ROUND);
        IntBeanChgString usedMoveLastRound_ = _inputGene.newString(mv_);
        usedMoveLastRound_.setupValue(_f.getUsedMoveLastRound());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_LAST_USED_MOVE);
        IntBeanChgString lastUsedMove_ = _inputGene.newString(mv_);
        lastUsedMove_.setupValue(_f.getLastUsedMove());
        return new IntBeanChgFighter5(lastSufferedMoveTypes_, alreadyInvokedMovesRound_, lastSuccessfulMove_, nbRepeatingSuccessfulMoves_, usingItem_, usedMoveLastRound_, lastUsedMove_);
    }

    private IntBeanChgFighter6 six(Fighter _f, IntBeanGeneInput _inputGene, int _max) {
        AbsMap<Gender, String> translatedGenders_ = getBean().getDataBase().getTranslatedGenders().getVal(getBean().getLanguage());
        DictionaryComparator<Gender, String> genders_ = new DictionaryComparator<Gender, String>(translatedGenders_);
        genders_.putAllMap(translatedGenders_);
        DictionaryComparator<String, String> mvList_ = DictionaryComparatorUtil.buildMvStrElts(getBean().getDataBase(), getBean().getLanguage());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_EVO_PK_MOVES);
        IntBeanChgList<String> movesToBeLearnt_ = _inputGene.newStringList(mvList_);
        movesToBeLearnt_.setupValue(_f.getMovesToBeLearnt());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_SUCCESSFUL_MOVE);
        IntBeanChgBool successfulMove_ = _inputGene.newBool();
        successfulMove_.setSelected(_f.isSuccessfulMove());
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_CHANGED);
        IntBeanChgBool changed_ = _inputGene.newBool();
        changed_.setSelected(_f.isChanged());
        IdMap<KindAction,String> ka_ = new IdMap<KindAction, String>();
        ka_.addEntry(KindAction.NO,getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KIND_ACTION_NO));
        ka_.addEntry(KindAction.MOVE,getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KIND_ACTION_MOVE));
        ka_.addEntry(KindAction.SWITCH,getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KIND_ACTION_SWITCH));
        ka_.addEntry(KindAction.HEAL,getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KIND_ACTION_HEAL));
        ka_.addEntry(KindAction.HEAL_MOVE,getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KIND_ACTION_HEAL_MOVE));
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION);
        IntBeanChgKindAction chgAc_ = getBean().getBuilder().getGenInput().newKa(ka_);
        chgAc_.valueKa(kindAction(_f.getAction()));
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getBean().getDataBase(), getBean().getLanguage());
        mv_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DictionaryComparator<String, String> it_ = DictionaryComparatorUtil.buildItemsStr(getBean().getDataBase(), getBean().getLanguage());
        it_.put(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_FIRST);
        IntBeanChgString first_ = getBean().getBuilder().getGenInput().newString(mv_);
        first_.setupValue(first(_f.getAction()));
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_FINAL);
        IntBeanChgString last_ = getBean().getBuilder().getGenInput().newString(mv_);
        last_.setupValue(last(_f.getAction()));
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_ITEM);
        IntBeanChgString healIt_ = getBean().getBuilder().getGenInput().newString(it_);
        healIt_.setupValue(item(_f.getAction()));
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_SUF);
        IntBeanChgInt sub_ = getBean().getBuilder().getGenInput().newInt(SimulationCommonForm.ids(_max));
        sub_.valueInt(sub(_f.getAction()));
        CustList<TargetCoords> targetCoords_ = allValuesTarget();
        AbsMap<TargetCoords,String> valuesMap_ = new TargetCoordssString();
        int lenVal_ = targetCoords_.size();
        for (int i = 0; i < lenVal_; i++) {
            TargetCoords k_ = targetCoords_.get(i);
            valuesMap_.addEntry(k_,k_.display());
        }
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_TAR);
        IntBeanChgTargetCoords targets_ = getBean().getBuilder().getGenInput().newTc(valuesMap_);
        targets_.valueTc(targetChoice(_f.getAction()));
        DifficultyBeanForm.formatMessage(getBean(),MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_KIND_ACTION_FIELD_TEAM);
        IntBeanChgBool team_ = getBean().getBuilder().getGenInput().newBool();
        team_.setSelected(team(_f.getAction()));
        IntBeanChgAction action_ = new IntBeanChgAction(chgAc_, first_, last_, healIt_, sub_, targets_, team_);
        return new IntBeanChgFighter6(movesToBeLearnt_, action_, successfulMove_, changed_);
    }

    private KindAction kindAction(AbstractAction _a) {
        if (_a == null) {
            return KindAction.NO;
        }
        return _a.getKindAction();
    }

    private TargetCoords targetChoice(AbstractAction _a) {
        TargetCoordsList tl_ = new TargetCoordsList();
        if (_a instanceof ActionMove) {
            tl_ = ((ActionMove)_a).getChosenTargets();
        }
        if (tl_.size() != 1) {
            return TargetCoords.def();
        }
        return tl_.first();
    }

    private String item(AbstractAction _a) {
        if (!(_a instanceof ChosenHealing)) {
            return DataBase.EMPTY_STRING;
        }
        return ((ChosenHealing)_a).getChosenHealingItem();
    }

    private String first(AbstractAction _a) {
        if (!(_a instanceof ChosenMove)) {
            return DataBase.EMPTY_STRING;
        }
        return ((ChosenMove)_a).getFirstChosenMove();
    }

    private String last(AbstractAction _a) {
        if (!(_a instanceof ActionMove)) {
            return DataBase.EMPTY_STRING;
        }
        return ((ActionMove)_a).getFinalChosenMove();
    }

    private int sub(AbstractAction _a) {
        if (!(_a instanceof ChosenReplacing)) {
            return Fighter.BACK;
        }
        return ((ChosenReplacing)_a).getSubstitute();
    }

    private boolean team(AbstractAction _a) {
        if (!(_a instanceof ActionHeal)) {
            return false;
        }
        return ((ActionHeal)_a).isTeam();
    }
    public void update() {
        coreAction.actionBean();
        status.actionBean();
        nbUsesMoves.actionBean();
        damageRateInflictedByType.actionBean();
        damageRateSufferedByType.actionBean();
        damageSufferedCateg.actionBean();
        damageSufferedCategRound.actionBean();
        enabledMovesForAlly.actionBean();
        for (SimulationBeanUpdateEntryValues<String,UsesOfMove> v:moves.values()) {
            v.actionBean();
        }
        statusRelat.actionBean();
        incrUserAccuracy.actionBean();
        trackingMoves.actionBean();
        trappingMoves.actionBean();
        privateMoves.actionBean();
        copiedMoves.actionBean();
        movesAbilitiesEvos.actionBean();
        enabledMoves.actionBean();
        enabledMovesProt.actionBean();
        enabledMovesUnprot.actionBean();
        enabledMovesEndRound.actionBean();
        enabledMovesConstChoices.actionBean();
        enabledChangingTypesMoves.actionBean();
        enabledCounteringMoves.actionBean();
        ev.actionBean();
        iv.actionBean();
        statisBoost.actionBean();
        statisBase.actionBean();
    }

    public SimulationBeanUpdateEntryValues<MoveTeamPosition, BoolVal> getIncrUserAccuracy() {
        return incrUserAccuracy;
    }

    public SimulationBeanUpdateEntryValues<String, BoolVal> getEnabledMovesForAlly() {
        return enabledMovesForAlly;
    }
}
