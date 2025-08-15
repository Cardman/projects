package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.comparators.*;
import aiki.beans.fight.*;
import aiki.beans.game.*;
import aiki.comparators.*;
import aiki.game.fight.*;
import aiki.game.fight.util.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public final class SimulationTeamForm extends SimulationCommonForm {
    private final Team team;
    private final IntBeanChgLong nbKoRound;
    private final IntBeanChgLong nbKoPreviousRound;
    private final IntBeanChgList<String> successfulMovesRound;
    private final SimulationBeanUpdateEntryValues<String, ActivityOfMove> enabledMoves;
    private final SimulationBeanUpdateEntryValues<String, BoolVal> enabledMovesWhileSendingFoe;
    private final SimulationBeanUpdateEntryValues<String, Long> nbUsesMoves;
    private final SimulationBeanUpdateEntryValues<String, Long> nbUsesMovesRound;
    private final SimulationBeanUpdateEntryValues<String, LgInt> enabledMovesWhileSendingFoeUses;
    private final StringMap<SimulationBeanUpdateEntryValues<Integer, StacksOfUses>> healAfter;
    private final StringMap<SimulationBeanUpdateEntryValues<Integer, Anticipation>> movesAnticipation;
    private final DictionaryComparator<StringList, ListActivityOfMove> group = new DictionaryComparator<StringList, ListActivityOfMove>(new ComparatorStringList());
    private final CustList<IntBeanChgActivityOfMove> enabledMovesByGroup = new CustList<IntBeanChgActivityOfMove>();
    private final CustList<SimulationFighterForm> members = new CustList<SimulationFighterForm>();
    public SimulationTeamForm(SimulationBean _b, Team _t, String _title) {
        super(_b);
        _b.initPage();
        _b.setTitledBorder(_title);
        _b.initPage();
        _b.setTitledBorder(_b.messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TEAM_GENERALITY));
        DifficultyBeanForm.formatMessage(_b,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NB_KO_ROUND);
        nbKoRound = DifficultyBeanForm.iv(_b.getBuilder().getGenInput(), _b, _t.getNbKoRound());
        DifficultyBeanForm.formatMessage(_b,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NB_KO_PREVIOUS_ROUND);
        nbKoPreviousRound = DifficultyBeanForm.iv(_b.getBuilder().getGenInput(), _b, _t.getNbKoPreviousRound());
        DifficultyBeanForm.formatMessage(_b,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_SUCCESSFUL_MOVES_ROUND);
        successfulMovesRound = DifficultyBeanForm.selectLs(_b.getBuilder().getGenInput(), _b, DictionaryComparatorUtil.buildMvStrElts(_b.getDataBase(), _b.getLanguage()), _t.getSuccessfulMovesRound());
        _b.feedParents();
        group(_t);
        enabledMoves = enMoves(sortedAc(_t.getEnabledMoves()), _t.getEnabledMoves(), MessagesDataSimulation.M_P_86_TEAM_ENABLED_MOVES);
        enabledMovesWhileSendingFoe = stillEnMoves(sorted(_t.getEnabledMovesWhileSendingFoe()), _t.getEnabledMovesWhileSendingFoe(), MessagesDataSimulation.M_P_86_ENBALED_MOVES_SEND);
        enabledMovesWhileSendingFoeUses = enabledMovesWhileSendingFoeUses(_t);
        nbUsesMoves = nbUsesMovesTeam(sortedLg(_t.getNbUsesMoves()), _t.getNbUsesMoves());
        nbUsesMovesRound = nbUsesMovesTeam(sortedLg(_t.getNbUsesMovesRound()), _t.getNbUsesMovesRound());
        healAfter = healAfter(_t);
        movesAnticipation = ant(_t);
        int max_ = -1;
        for (EntryCust<Integer,Fighter> f:_t.getMembers().entryList()) {
            max_ = NumberUtil.max(f.getKey(),max_);
        }
        for (EntryCust<Integer,Fighter> f:_t.getMembers().entryList()) {
            _b.initPage();
            _b.setTitledBorder(Long.toString(f.getKey()));
            members.add(new SimulationFighterForm(_b,f.getValue(),max_+1));
            _b.feedParents();
        }
        _b.feedParents();
        team = _t;
    }

    private void group(Team _t) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_GROUPS));
        ListActivityOfMoves gr_ = _t.getEnabledMovesByGroup();
        getBean().initGrid();
        getBean().getBuilder().colCount(3);
        int len_ = gr_.size();
        for (int i = 0; i < len_; i++) {
            StringList key_ = new StringList();
            for (String m: gr_.get(i).getList()) {
                key_.add(getBean().getDataBase().getTranslatedMoves().getVal(getBean().getLanguage()).getVal(m));
            }
            key_.sort();
            group.put(key_,gr_.get(i));
        }
        int mapLen_ = group.size();
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVES_GR);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_ENABLED);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_NB_ROUND);
        for (int i = 0; i < mapLen_; i++) {
            getBean().formatMessageDirCts(StringUtil.join(group.getKey(i), CommonFightBean.MOVES_SEPARATOR));
            IntBeanChgActivityOfMove chgPl_ = getBean().getBuilder().getGenInput().newAc();
            chgPl_.valueActivity(group.getValue(i).getCombo());
            enabledMovesByGroup.add(chgPl_);
            getBean().getBuilder().nextPart();
        }
        getBean().feedParents();
        getBean().feedParents();
    }

    private SimulationBeanUpdateEntryValues<String,LgInt> enabledMovesWhileSendingFoeUses(Team _t) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_USES));
        getBean().initGrid();
        getBean().getBuilder().colCount(2);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENBALED_MOVES_USES_NB);
        StringMap<StringMap<String>> tp_ = getBean().getDataBase().getTranslatedMoves();
        DictionaryComparator<String, LgInt> pk_ = new DictionaryComparator<String, LgInt>(tp_.getVal(getBean().getLanguage()));
        pk_.putAllMap(_t.getEnabledMovesWhileSendingFoeUses());
        StringMap<IntBeanChgValue<LgInt>> o_ = new StringMap<IntBeanChgValue<LgInt>>();
        for (EntryCust<String,LgInt> e: pk_.entryList()) {
            getBean().formatMessageDirCts(tp_.getVal(getBean().getLanguage()).getVal(e.getKey()));
            IntBeanChgLgInt choice_ = getBean().getBuilder().getGenInput().newLgInt();
            choice_.valueLgInt(e.getValue());
            getBean().getBuilder().nextPart();
            o_.addEntry(e.getKey(),choice_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<String, LgInt>(_t.getEnabledMovesWhileSendingFoeUses(),o_);
    }
    private SimulationBeanUpdateEntryValues<String,Long> nbUsesMovesTeam(AbsMap<TranslatedKey, Long> _map, StringMap<Long> _info) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_ENBALED_MOVES_USES));
        getBean().initGrid();
        getBean().getBuilder().colCount(2);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NB_USES_VALUE);
        StringMap<IntBeanChgValue<Long>> o_ = new StringMap<IntBeanChgValue<Long>>();
        for (EntryCust<TranslatedKey,Long> e: _map.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getTranslation());
            IntBeanChgLong chgPl_ = getBean().getBuilder().getGenInput().newLong();
            chgPl_.valueLong(e.getValue());
            getBean().getBuilder().nextPart();
            o_.addEntry(e.getKey().getKey(),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        return new SimulationBeanUpdateEntryValues<String,Long>(_info,o_);
    }

    private StringMap<SimulationBeanUpdateEntryValues<Integer,StacksOfUses>> healAfter(Team _t) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_HEAL_AFTER));
        getBean().initGrid();
        DictionaryComparator<TranslatedKey, IntMap<StacksOfUses>> map_ = new DictionaryComparator<TranslatedKey, IntMap<StacksOfUses>>(new ComparingTranslatedKey());
        for (EntryCust<String, IntMap<StacksOfUses>> e:_t.getHealAfter().entryList()) {
            map_.put(CommonBean.buildMv(getBean().getFacade(),e.getKey()),e.getValue());
        }
        getBean().getBuilder().colCount(5);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TARGET);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_HEAL_AFTER_KEY_USED_CURRENT);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_HEAL_AFTER_KEY_USED_LAST);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_HEAL_AFTER_KEY_RD);
        StringMap<SimulationBeanUpdateEntryValues<Integer,StacksOfUses>> g_ = new StringMap<SimulationBeanUpdateEntryValues<Integer, StacksOfUses>>();
        for (EntryCust<TranslatedKey, IntMap<StacksOfUses>> e: map_.entryList()) {
            IntMap<IntBeanChgValue<StacksOfUses>> o_ = new IntMap<IntBeanChgValue<StacksOfUses>>();
            for (EntryCust<Integer,StacksOfUses> f:e.getValue().entryList()) {
                getBean().formatMessageDirCts(e.getKey().getTranslation());
                getBean().formatMessageDirCts(Long.toString(f.getKey()));
                IntBeanChgStackOfUses chgPl_ = getBean().getBuilder().getGenInput().newStack();
                chgPl_.valueStack(f.getValue());
                getBean().getBuilder().nextPart();
                o_.addEntry(f.getKey(),chgPl_);
            }
            g_.addEntry(e.getKey().getKey(),new SimulationBeanUpdateEntryValues<Integer, StacksOfUses>(_t.getHealAfter().getVal(e.getKey().getKey()),o_));
        }
        getBean().feedParents();
        getBean().feedParents();
        return g_;
    }

    private StringMap<SimulationBeanUpdateEntryValues<Integer,Anticipation>> ant(Team _t) {
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_MOVE_ANT));
        getBean().initGrid();
        DictionaryComparator<TranslatedKey, IntMap<Anticipation>> map_ = new DictionaryComparator<TranslatedKey, IntMap<Anticipation>>(new ComparingTranslatedKey());
        for (EntryCust<String, IntMap<Anticipation>> e:_t.getMovesAnticipation().entryList()) {
            map_.put(CommonBean.buildMv(getBean().getFacade(),e.getKey()),e.getValue());
        }
        getBean().getBuilder().colCount(6);
        CustList<TargetCoords> targetCoords_ = allValuesTarget();
        AbsMap<TargetCoords,String> valuesMap_ = new TargetCoordssString();
        int lenVal_ = targetCoords_.size();
        for (int i = 0; i < lenVal_; i++) {
            TargetCoords k_ = targetCoords_.get(i);
            valuesMap_.addEntry(k_,k_.display());
        }
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TARGET);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ENABLED);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE_ANT_DAMAGE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE_ANT_NB_ROUND);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_MOVE_ANT_GROUND);
        StringMap<SimulationBeanUpdateEntryValues<Integer,Anticipation>> g_ = new StringMap<SimulationBeanUpdateEntryValues<Integer, Anticipation>>();
        for (EntryCust<TranslatedKey, IntMap<Anticipation>> e: map_.entryList()) {
            IntMap<IntBeanChgValue<Anticipation>> o_ = new IntMap<IntBeanChgValue<Anticipation>>();
            for (EntryCust<Integer,Anticipation> f:e.getValue().entryList()) {
                getBean().formatMessageDirCts(e.getKey().getTranslation());
                getBean().formatMessageDirCts(Long.toString(f.getKey()));
                IntBeanChgAnticipation chgPl_ = getBean().getBuilder().getGenInput().newAnt(valuesMap_);
                chgPl_.valueAnt(f.getValue());
                getBean().getBuilder().nextPart();
                o_.addEntry(f.getKey(),chgPl_);
            }
            g_.addEntry(e.getKey().getKey(),new SimulationBeanUpdateEntryValues<Integer, Anticipation>(_t.getMovesAnticipation().getVal(e.getKey().getKey()),o_));
        }
        getBean().feedParents();
        getBean().feedParents();
        return g_;
    }

    public void update() {
        team.setNbKoRound(nbKoRound.valueLong());
        team.setNbKoPreviousRound(nbKoPreviousRound.valueLong());
        team.setSuccessfulMovesRound(new StringList(successfulMovesRound.tryRet()));
        enabledMoves.actionBean();
        enabledMovesWhileSendingFoe.actionBean();
        enabledMovesWhileSendingFoeUses.actionBean();
        nbUsesMoves.actionBean();
        nbUsesMovesRound.actionBean();
        for (SimulationBeanUpdateEntryValues<Integer, StacksOfUses> v:healAfter.values()) {
            v.actionBean();
        }
        for (SimulationBeanUpdateEntryValues<Integer, Anticipation> v:movesAnticipation.values()) {
            v.actionBean();
        }
        int nbGr_ = enabledMovesByGroup.size();
        for (int i = 0; i < nbGr_; i++) {
            team.getEnabledMovesByGroup().set(team.getEnabledMovesByGroup().index(group.getValue(i).getList()),new ListActivityOfMove(group.getValue(i).getList(), enabledMovesByGroup.get(i).genericValue()));
        }
        for (SimulationFighterForm s: members) {
            s.update();
        }
    }

    public CustList<SimulationFighterForm> getMembers() {
        return members;
    }

    public IntBeanChgLong getNbKoPreviousRound() {
        return nbKoPreviousRound;
    }

    public IntBeanChgLong getNbKoRound() {
        return nbKoRound;
    }

    public IntBeanChgList<String> getSuccessfulMovesRound() {
        return successfulMovesRound;
    }

    public SimulationBeanUpdateEntryValues<String, Long> getNbUsesMoves() {
        return nbUsesMoves;
    }

    public SimulationBeanUpdateEntryValues<String, Long> getNbUsesMovesRound() {
        return nbUsesMovesRound;
    }

    public SimulationBeanUpdateEntryValues<String, BoolVal> getEnabledMovesWhileSendingFoe() {
        return enabledMovesWhileSendingFoe;
    }

    public SimulationBeanUpdateEntryValues<String, LgInt> getEnabledMovesWhileSendingFoeUses() {
        return enabledMovesWhileSendingFoeUses;
    }

    public SimulationBeanUpdateEntryValues<String, ActivityOfMove> getEnabledMoves() {
        return enabledMoves;
    }

    public StringMap<SimulationBeanUpdateEntryValues<Integer, StacksOfUses>> getHealAfter() {
        return healAfter;
    }

    public StringMap<SimulationBeanUpdateEntryValues<Integer, Anticipation>> getMovesAnticipation() {
        return movesAnticipation;
    }

    public CustList<IntBeanChgActivityOfMove> getEnabledMovesByGroup() {
        return enabledMovesByGroup;
    }
}
