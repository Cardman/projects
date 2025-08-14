package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.game.*;
import aiki.comparators.*;
import aiki.game.fight.*;
import aiki.game.fight.enums.*;
import aiki.game.fight.util.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public final class SimulationFightForm extends SimulationCommonForm {
    private final IntBeanChgLong nbFleeAttempt;
    private final IntBeanChgLgInt nbRounds;
    private final IntBeanChgRate winningMoney;
    private final IntBeanChgList<String> lostObjects;
    private final IntBeanChgList<String> caughtEvolutions;
    private final IntBeanChgInt indexUserTeam;
    private final IntBeanChgInt indexFightState;
    private final CustList<SimulationTeamForm> teamsForm = new CustList<SimulationTeamForm>();
    private final AbsMap<TeamPosition, String> currentUserList;
    private final AbsMap<FightState, String> fightState;
    private final SimulationBeanUpdateEntryValues<Integer, Integer> firstPositPlayerFighters;
    private final SimulationBeanUpdateEntryValues<Integer, Integer> firstPositFoeFighters;
    private final SimulationBeanUpdateEntryValues<String, BoolVal> stillEnabledMoves;
    private final SimulationBeanUpdateEntryValues<String, ActivityOfMove> enabledMoves;
    private final SimulationBeanUpdateEntryValues<Integer, CustList<Integer>> playerFightersAgainstFoe;
    private SimulationBeanUpdateEntryValues<String, Long> usedItemsWhileRound;
    private SimulationBeanUpdateEntryValues<Integer, ChoiceOfEvolutionAndMoves> choices;
    private SimulationBeanUpdateEntryValues<MoveTarget, MoveTarget> allyChoice;

    public SimulationFightForm(SimulationBean _s, AbsMap<TeamPosition, String> _c, AbsMap<FightState, String> _f){
        super(_s);
        currentUserList = _c;
        fightState = _f;
        _s.initPage();
        _s.setTitledBorder("");
        DifficultyBeanForm.formatMessage(_s, MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NB_FLEE_ATTEMPT);
        nbFleeAttempt = DifficultyBeanForm.iv(_s.getBuilder().getGenInput(), _s, _s.getSimulation().getGame().getFight().getNbFleeAttempt());
        DifficultyBeanForm.formatMessage(_s,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_NB_ROUND);
        nbRounds = DifficultyBeanForm.lgInt(_s.getBuilder().getGenInput(), _s, _s.getSimulation().getGame().getFight().getNbRounds());
        DifficultyBeanForm.formatMessage(_s,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_WINNING_MONEY);
        winningMoney = DifficultyBeanForm.rate(_s.getBuilder().getGenInput(), _s, _s.getSimulation().getGame().getFight().getWinningMoney());
        DifficultyBeanForm.formatMessage(_s,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_CURRENT_USER);
        indexUserTeam = DifficultyBeanForm.selectInt(_s.getBuilder().getGenInput(), _s, curUserListIndex(_c), _c.indexOfEntry(_s.getSimulation().getGame().getFight().getCurrentUser()));
        DifficultyBeanForm.formatMessage(_s,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_FIGHT_STATE);
        indexFightState = DifficultyBeanForm.selectInt(_s.getBuilder().getGenInput(), _s, fightStateListIndex(_f), _f.indexOfEntry(_s.getSimulation().getGame().getFight().getState()));
        DifficultyBeanForm.formatMessage(_s,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_LOST_OBJECTS);
        lostObjects = DifficultyBeanForm.selectLs(_s.getBuilder().getGenInput(), _s, DictionaryComparatorUtil.buildItemsStrElts(_s.getDataBase(), _s.getLanguage()), _s.getSimulation().getGame().getFight().getLostObjects());
        DifficultyBeanForm.formatMessage(_s,MessagesPkBean.SIMULATION,MessagesDataSimulation.M_P_86_CAUGHT_EVOS);
        caughtEvolutions = DifficultyBeanForm.selectLs(_s.getBuilder().getGenInput(), _s, DictionaryComparatorUtil.buildPkStrElts(_s.getDataBase(), _s.getLanguage()), _s.getSimulation().getGame().getFight().getCaughtEvolutions());
        _s.feedParents();
        firstPositPlayerFighters = posit(_s.getSimulation().getGame().getFight().getFirstPositPlayerFighters(), _s.messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_FIRST_POSIT_PLAYER));
        firstPositFoeFighters = posit(_s.getSimulation().getGame().getFight().getFirstPositFoeFighters(), _s.messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_FIRST_POSIT_FOE));
        stillEnabledMoves = stillEnMoves(sorted(_s.getSimulation().getGame().getFight().getStillEnabledMoves()), _s.getSimulation().getGame().getFight().getStillEnabledMoves(), MessagesDataSimulation.M_P_86_FIGHT_STILL_ENABLED_MOVES);
        enabledMoves = enMoves(sortedAc(_s.getSimulation().getGame().getFight().getEnabledMoves()), _s.getSimulation().getGame().getFight().getEnabledMoves(), MessagesDataSimulation.M_P_86_FIGHT_ENABLED_MOVES);
        usedItems(sortedUsedItems());
        evosChoices();
        allyChoices();
        getTeamsForm().clear();
        getTeamsForm().add(new SimulationTeamForm(_s,getBean().getSimulation().getGame().getFight().getUserTeam(),getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TITLE_PLAYER)));
        getBean().initPage();
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_PLAYER_FOE));
        getBean().initGrid();
        getBean().getBuilder().colCount(2);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_PLAYER_HEADER);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_FOE_HEADER);
        CustList<Integer> allKeys_ = getBean().getSimulation().getGame().getFight().getFoeTeam().getMembers().getKeys();
        IntMap<String> rep_ = new IntMap<String>();
        for (Integer i: allKeys_) {
            rep_.addEntry(i,Long.toString(i));
        }
        IntMap<IntBeanChgValue<CustList<Integer>>> o_ = new IntMap<IntBeanChgValue<CustList<Integer>>>();
        for (EntryCust<Integer,CustList<Integer>> e:getBean().getSimulation().getGame().getFight().getUserTeam().getPlayerFightersAgainstFoe().entryList()) {
            getBean().formatMessageDirCts(Long.toString(e.getKey()));
            IntBeanChgList<Integer> chgPl_ = DifficultyBeanForm.selectLsInts(getBean().getBuilder().getGenInput(), getBean(), rep_, e.getValue());
            o_.addEntry(e.getKey(),chgPl_);
        }
        getBean().feedParents();
        getBean().feedParents();
        playerFightersAgainstFoe = new SimulationBeanUpdateEntryValues<Integer, CustList<Integer>>(getBean().getSimulation().getGame().getFight().getUserTeam().getPlayerFightersAgainstFoe(), o_);
        getTeamsForm().add(new SimulationTeamForm(_s,getBean().getSimulation().getGame().getFight().getFoeTeam(),getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_TITLE_FOE)));
    }

    public void usedItems(AbsMap<TranslatedKey, Long> _map) {
        PageFormSimu formLocal_ = getBean().getBuilder().initPageForm(getBean());
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_USED_ITEMS_WHILE_ROUND));
        usedItemsContent(_map, formLocal_);
        getBean().getBuilder().feedParentForm();
    }

    public void usedItemsContent(AbsMap<TranslatedKey, Long> _map, PageFormSimu _form) {
        getBean().initGrid();
        getBean().getBuilder().colCount(3);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ITEM_USE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ITEM_USE_NB);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ACTION);
        int len_ = getBean().getSimulation().getGame().getFight().getUsedItemsWhileRound().size();
        DictionaryComparator<String, String> map_ = DictionaryComparatorUtil.buildItemsStr(getBean().getDataBase(), getBean().getLanguage());
        StringMap<String> fill_ = new StringMap<String>();
        fill_.putAllMap(getBean().getDataBase().getTranslatedItems().getVal(getBean().getLanguage()));
        for (int i = 0; i < len_; i++) {
            fill_.removeKey(getBean().getSimulation().getGame().getFight().getUsedItemsWhileRound().getKey(i));
        }
        map_.putAllMap(fill_);
        if (!map_.isEmpty()) {
            IntBeanChgString key_ = getBean().getBuilder().getGenInput().newString(map_);
            IntBeanChgLong value_ = getBean().getBuilder().getGenInput().newLong();
            getBean().getBuilder().button("+").addEvt(new SimulationBeanAddEntry<String,Long>(getBean().getSimulation().getGame().getFight().getUsedItemsWhileRound(),key_,value_, new UpdateFormUsedItem(getBean()), _form));
            getBean().getBuilder().nextPart();
        }
        StringMap<IntBeanChgValue<Long>> o_ = new StringMap<IntBeanChgValue<Long>>();
        for (EntryCust<TranslatedKey,Long> e: _map.entryList()) {
            getBean().formatMessageDirCts(e.getKey().getTranslation());
            IntBeanChgLong chgPl_ = getBean().getBuilder().getGenInput().newLong();
            chgPl_.valueLong(e.getValue());
            getBean().getBuilder().nextPart();
            getBean().initLine();
            getBean().getBuilder().button("-").addEvt(new SimulationBeanRemoveEntry<String,Long>(getBean().getSimulation().getGame().getFight().getUsedItemsWhileRound(),e.getKey().getKey(), new UpdateFormUsedItem(getBean()), _form));
            getBean().feedParentsCts();
            o_.addEntry(e.getKey().getKey(),chgPl_);
        }
        getBean().feedParents();
        usedItemsWhileRound = new SimulationBeanUpdateEntryValues<String, Long>(getBean().getSimulation().getGame().getFight().getUsedItemsWhileRound(), o_);
    }

    private void evosChoices() {
        PageFormSimu formLocal_ = getBean().getBuilder().initPageForm(getBean());
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EVO_CHOICES));
        evosChoicesContent(formLocal_);
        getBean().getBuilder().feedParentForm();
    }

    public void evosChoicesContent(PageFormSimu _form) {
        getBean().initGrid();
        getBean().getBuilder().colCount(5);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_KEY_FIGHTER);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EVO_PK);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EVO_PK_MOVES);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EVO_PK_ABILITIES);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ACTION);
        DictionaryComparator<String, String> pk_ = DictionaryComparatorUtil.buildPkStrElts(getBean().getDataBase(), getBean().getLanguage());
        DictionaryComparator<String, String> mv_ = DictionaryComparatorUtil.buildMvStrElts(getBean().getDataBase(), getBean().getLanguage());
        DictionaryComparator<String, String> ab_ = DictionaryComparatorUtil.buildAbStrElts(getBean().getDataBase(), getBean().getLanguage());
        int len_ = getBean().getSimulation().getGame().getFight().getUserTeam().getMembers().size();
        AbsMap<Integer,String> map_ = new IntMap<String>();
        for (int i = 0; i < len_; i++) {
            int k_ = getBean().getSimulation().getGame().getFight().getUserTeam().getMembers().getKey(i);
            if (!getBean().getSimulation().getGame().getFight().getChoices().contains(k_)) {
                map_.addEntry(k_,Long.toString(k_));
            }
        }
        if (!map_.isEmpty()) {
            IntBeanChgInt key_ = getBean().getBuilder().getGenInput().newInt(map_);
            IntBeanChgChoiceOfEvolutionAndMoves value_ = getBean().getBuilder().getGenInput().newChoice(pk_, mv_, ab_);
            getBean().getBuilder().button("+").addEvt(new SimulationBeanAddEntry<Integer,ChoiceOfEvolutionAndMoves>(getBean().getSimulation().getGame().getFight().getChoices(),key_,value_, new UpdateFormEvosChoices(getBean()), _form));
            getBean().getBuilder().nextPart();
        }
        IntMap<IntBeanChgValue<ChoiceOfEvolutionAndMoves>> o_ = new IntMap<IntBeanChgValue<ChoiceOfEvolutionAndMoves>>();
        for (EntryCust<Integer,ChoiceOfEvolutionAndMoves> e: getBean().getSimulation().getGame().getFight().getChoices().entryList()) {
            getBean().formatMessageDirCts(Long.toString(e.getKey()));
            getBean().initLine();
            IntBeanChgChoiceOfEvolutionAndMoves choice_ = getBean().getBuilder().getGenInput().newChoice(pk_, mv_, ab_);
            choice_.valueChoice(e.getValue());
            getBean().getBuilder().nextPart();
            getBean().feedParentsCts();
            getBean().initLine();
            getBean().getBuilder().button("-").addEvt(new SimulationBeanRemoveEntry<Integer,ChoiceOfEvolutionAndMoves>(getBean().getSimulation().getGame().getFight().getChoices(),e.getKey(), new UpdateFormEvosChoices(getBean()), _form));
            getBean().feedParentsCts();
            o_.addEntry(e.getKey(),choice_);
        }
        getBean().feedParents();
        choices = new SimulationBeanUpdateEntryValues<Integer, ChoiceOfEvolutionAndMoves>(getBean().getSimulation().getGame().getFight().getChoices(), o_);
    }

    private void allyChoices() {
        PageFormSimu formLocal_ = getBean().getBuilder().initPageForm(getBean());
        getBean().setTitledBorder(getBean().messageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ALLY_CHOICES));
        allyChoicesContent(formLocal_);
        getBean().getBuilder().feedParentForm();
    }

    public void allyChoicesContent(PageFormSimu _form) {
        getBean().initGrid();
        getBean().getBuilder().colCount(3);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ALLY_CHOICES_KEY);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ALLY_CHOICES_VALUE);
        getBean().headerCol(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ACTION);
        CustList<MoveTarget> values_ = allValues();
        int lenVal_ = values_.size();
        AbsMap<MoveTarget,String> valuesMap_ = new MoveTargetsString();
        for (int i = 0; i < lenVal_; i++) {
            MoveTarget k_ = values_.get(i);
            valuesMap_.addEntry(k_,k_.display());
        }
        CustList<MoveTarget> keys_ = allKeys();
        int len_ = keys_.size();
        AbsMap<MoveTarget,String> map_ = new MoveTargetsString();
        for (int i = 0; i < len_; i++) {
            MoveTarget k_ = keys_.get(i);
            if (!getBean().getSimulation().getGame().getFight().getAllyChoice().contains(k_)) {
                map_.addEntry(k_,k_.display());
            }
        }
        if (!map_.isEmpty()) {
            IntBeanChgMoveTarget key_ = getBean().getBuilder().getGenInput().newMt(map_);
            IntBeanChgMoveTarget value_ = getBean().getBuilder().getGenInput().newMt(valuesMap_);
            getBean().getBuilder().button("+").addEvt(new SimulationBeanAddEntry<MoveTarget,MoveTarget>(getBean().getSimulation().getGame().getFight().getAllyChoice(),key_,value_, new UpdateFormAllyChoices(getBean()), _form));
            getBean().getBuilder().nextPart();
        }
        MoveTargetsChgString o_ = new MoveTargetsChgString();
        for (EntryCust<MoveTarget, MoveTarget> e: getBean().getSimulation().getGame().getFight().getAllyChoice().entryList()) {
            getBean().formatMessageDirCts(e.getKey().display());
            IntBeanChgMoveTarget choice_ = getBean().getBuilder().getGenInput().newMt(valuesMap_);
            choice_.valueMt(e.getValue());
            getBean().getBuilder().nextPart();
            getBean().initLine();
            getBean().getBuilder().button("-").addEvt(new SimulationBeanRemoveEntry<MoveTarget,MoveTarget>(getBean().getSimulation().getGame().getFight().getAllyChoice(),e.getKey(), new UpdateFormAllyChoices(getBean()), _form));
            getBean().feedParentsCts();
            o_.addEntry(e.getKey(),choice_);
        }
        getBean().feedParents();
        allyChoice = new SimulationBeanUpdateEntryValues<MoveTarget, MoveTarget>(getBean().getSimulation().getGame().getFight().getAllyChoice(), o_);
    }
    private IntMap<String> curUserListIndex(AbsMap<TeamPosition,String> _id) {
        return new ConverterToIntMapUtil<TeamPosition>().convert(_id);
    }

    private IntMap<String> fightStateListIndex(AbsMap<FightState,String> _id) {
        return new ConverterToIntMapUtil<FightState>().convert(_id);
    }
    public void validateFightCoreForm() {
        getBean().getSimulation().getGame().getFight().setNbFleeAttempt(nbFleeAttempt.valueLong());
        getBean().getSimulation().getGame().getFight().setNbRounds(nbRounds.valueLgInt());
        getBean().getSimulation().getGame().getFight().setWinningMoney(winningMoney.valueRate());
        getBean().getSimulation().getGame().getFight().setLostObjects(new StringList(lostObjects.tryRet()));
        getBean().getSimulation().getGame().getFight().setCaughtEvolutions(new StringList(caughtEvolutions.tryRet()));
        getBean().getSimulation().getGame().getFight().setCurrentUser(currentUserList.getKey(indexUserTeam.valueInt()));
        getBean().getSimulation().getGame().getFight().setState(fightState.getKey(indexFightState.valueInt()));
        firstPositPlayerFighters.actionBean();
        firstPositFoeFighters.actionBean();
        stillEnabledMoves.actionBean();
        enabledMoves.actionBean();
        usedItemsWhileRound.actionBean();
        choices.actionBean();
        allyChoice.actionBean();
        playerFightersAgainstFoe.actionBean();
        for (SimulationTeamForm s: teamsForm) {
            s.update();
        }
    }
    public CustList<SimulationTeamForm> getTeamsForm() {
        return teamsForm;
    }

    public IntBeanChgLgInt getNbRounds() {
        return nbRounds;
    }

    public IntBeanChgLong getNbFleeAttempt() {
        return nbFleeAttempt;
    }

    public IntBeanChgList<String> getLostObjects() {
        return lostObjects;
    }

    public IntBeanChgList<String> getCaughtEvolutions() {
        return caughtEvolutions;
    }

    public IntBeanChgRate getWinningMoney() {
        return winningMoney;
    }
}
