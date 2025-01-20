package aiki.beans.fight;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.*;
import code.maths.LgInt;
import code.scripts.confs.PkScriptPages;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class TeamBean extends CommonFightBean {
    private DictionaryComparator<StringList,ActivityOfMove> enabledMovesByGroup;
    private NatStringTreeMap<ActivityOfMove> enabledMoves;
    private NatStringTreeMap<LgInt> enabledMovesWhileSendingFoeUses;
    private NatStringTreeMap<Long> nbUsesMoves;
    private NatStringTreeMap<IntTreeMap<StacksOfUses>> healAfter;
    private NatStringTreeMap<IntTreeMap<Anticipation>> movesAnticipation;
    private IntTreeMap<Ints > playerFightersAgainstFoe;
    private boolean foeTeam;

    @Override
    public void beforeDisplaying() {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        int noTeam_ = getForms().getValInt(NO_TEAM);
        foeTeam = noTeam_ == Fight.CST_FOE;
        Team team_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_);
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        NatStringTreeMap<ActivityOfMove> enabledMoves_;
        enabledMoves_ = new NatStringTreeMap<ActivityOfMove>();
        for (String m: team_.getEnabledMoves().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), team_.getEnabledMoves().getVal(m));
        }
        enabledMoves = enabledMoves_;
        NatStringTreeMap<LgInt> enabledMovesWhileSendingFoeUses_;
        enabledMovesWhileSendingFoeUses_ = new NatStringTreeMap<LgInt>();
        for (String m: team_.getEnabledMovesWhileSendingFoeUses().getKeys()) {
            enabledMovesWhileSendingFoeUses_.put(translationsMoves_.getVal(m), team_.getEnabledMovesWhileSendingFoeUses().getVal(m));
        }
        enabledMovesWhileSendingFoeUses = enabledMovesWhileSendingFoeUses_;
        NatStringTreeMap<Long> nbUsesMoves_;
        nbUsesMoves_ = new NatStringTreeMap<Long>();
        for (String m: team_.getNbUsesMoves().getKeys()) {
            nbUsesMoves_.put(translationsMoves_.getVal(m), team_.getNbUsesMoves().getVal(m));
        }
        nbUsesMoves = nbUsesMoves_;
        DictionaryComparator<StringList,ActivityOfMove> enabledMovesByGroup_;
//        enabledMovesByGroup_ = new TreeMap<new>(new NaturalComparator<StringList>(){
//            public int compare(StringList _key1, StringList _key2) {
//                int lenOne_ = _key1.size();
//                int lenTwo_ = _key2.size();
//                int minLen_ = Math.min(lenOne_, lenTwo_);
//                int diff_ = lenOne_ - lenTwo_;
//                if (diff_ != 0) {
//                    return diff_;
//                }
//                for (int i = CustList.FIRST_INDEX; i < minLen_; i++) {
//                    int res_ = _key1.get(i).compareTo(_key2.get(i));
//                    if (res_ != 0) {
//                        return res_;
//                    }
//                }
//                return 0;
//            }
//        });
        enabledMovesByGroup_ = DictionaryComparatorUtil.buildActivities(data_, getLanguage());
        for (StringList s: team_.getEnabledMovesByGroup().getKeys()) {
            StringList key_ = new StringList();
            for (String m: s) {
                key_.add(translationsMoves_.getVal(m));
            }
            key_.sort();
            enabledMovesByGroup_.put(key_, new ActivityOfMove(team_.getEnabledMovesByGroup().getVal(s)));
        }
        enabledMovesByGroup = enabledMovesByGroup_;
        NatStringTreeMap<IntTreeMap<StacksOfUses>> healAfter_;
        healAfter_ = new NatStringTreeMap<IntTreeMap<StacksOfUses>>();
        for (String m: team_.getHealAfterSet()) {
            IntTreeMap<StacksOfUses> h_;
            h_ = new IntTreeMap<StacksOfUses>();
            for (int k: team_.getHealAfterSet(m)) {
                StacksOfUses stack_;
                stack_ = new StacksOfUses();
                StacksOfUses st_ = team_.getHealAfterVal(m, k);
                stack_.setFirstStacked(st_.isFirstStacked());
                stack_.setLastStacked(st_.isLastStacked());
                stack_.setNbRounds(st_.getNbRounds());
                h_.put(k, stack_);
            }
            healAfter_.put(translationsMoves_.getVal(m), h_);
        }
        healAfter = healAfter_;
        NatStringTreeMap<IntTreeMap<Anticipation>> movesAnticipation_;
        movesAnticipation_ = new NatStringTreeMap<IntTreeMap<Anticipation>>();
        for (String m: team_.getMovesAnticipationSet()) {
            IntTreeMap<Anticipation> a_;
            a_ = new IntTreeMap<Anticipation>();
            for (int k: team_.getMovesAnticipationSet(m)) {
                Anticipation ant_;
                ant_ = new Anticipation();
                Anticipation anticip_ = team_.getMovesAnticipationVal(m, k);
                TargetCoords tar_ = anticip_.getTargetPosition();
                ant_.setTargetPosition(new TargetCoords(tar_.getTeam(),tar_.getPosition()));
                ant_.setIncrementing(anticip_.isIncrementing());
                ant_.setDamage(anticip_.getDamage());
                ant_.setNbRounds(anticip_.getNbRounds());
                a_.put(k, ant_);
            }
            movesAnticipation_.put(translationsMoves_.getVal(m), a_);
        }
        movesAnticipation = movesAnticipation_;
        playerFightersAgainstFoe = new IntTreeMap<Ints >();
        for (int p: team_.getPlayerFightersAgainstFoeSet()) {
            Ints numbers_ = new Ints();
            numbers_.addAllElts(team_.getPlayerFightersAgainstFoeVal(p));
            numbers_.sort();
            playerFightersAgainstFoe.put(p, numbers_);
        }
    }
    public Ints getMembers() {
        FacadeGame dataBaseFight_ = facade();
        int noTeam_ = getForms().getValInt(NO_TEAM);
        return getMembers(dataBaseFight_, noTeam_);
    }
    public String getTrPokemonLink(int _index) {
        int index_ = getMembers().get(_index);
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        int noTeam_ = getForms().getValInt(NO_TEAM);
        Team team_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_);
        Fighter fighter_ = team_.getMembers().getVal(index_);
        int nb_ = number(team_, index_, _index, getMembers());
        if (nb_ == IndexConstants.FIRST_INDEX) {
            return data_.translatePokemon(fighter_.getName());
        }
        return StringUtil.concat(data_.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }
    public String clickFighter(int _index) {
        getForms().put(NO_FIGHTER, _index);
        return PkScriptPages.WEB_FIGHT_HTML_FIGHTER_HTML;
    }
    public String getKey(int _index) {
        return StringUtil.join(enabledMovesByGroup.getKey(_index), MOVES_SEPARATOR);
    }
    public boolean isFoeMovesAnticipationTeam(int _indexOne,int _indexTwo) {
        return movesAnticipation.getValue(_indexOne).getValue(_indexTwo).getTargetPosition().getTeam() == Fight.CST_FOE;
    }
    public boolean isPlayerMovesAnticipationTeam(int _indexOne,int _indexTwo) {
        return movesAnticipation.getValue(_indexOne).getValue(_indexTwo).getTargetPosition().getTeam() == Fight.CST_PLAYER;
    }
    public boolean isBackMovesAnticipationTeam(int _indexOne,int _indexTwo) {
        return !movesAnticipation.getValue(_indexOne).getValue(_indexTwo).isEnabled();
    }
    public String getPlayerFigtherAgainstFoe(int _index) {
        int key_ = playerFightersAgainstFoe.getKey(_index);
        return getDisplayName(key_, _index);
    }
    public String getFoeFigtherAgainstFoe(int _indexOne, int _indexTwo) {
        int key_ = playerFightersAgainstFoe.getValue(_indexOne).get(_indexTwo);
        return getDisplayFoeName(key_, _indexTwo);
    }

    private String getDisplayName(int _indexOne, int _index) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        Team team_ = dataBaseFight_.getGame().getFight().getUserTeam();
        Fighter fighter_ = team_.getMembers().getVal(_indexOne);
        int nb_ = number(team_, _indexOne,_index, getMembers());
        if (nb_ == IndexConstants.FIRST_INDEX) {
            return data_.translatePokemon(fighter_.getName());
        }
        return StringUtil.concat(data_.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }

    private String getDisplayFoeName(int _indexOne, int _index) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        Team team_ = dataBaseFight_.getGame().getFight().getFoeTeam();
        Fighter fighter_ = team_.getMembers().getVal(_indexOne);
        int nb_ = number(team_, _indexOne, _index, getMembers());
        if (nb_ == IndexConstants.FIRST_INDEX) {
            return data_.translatePokemon(fighter_.getName());
        }
        return StringUtil.concat(data_.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }

    public boolean getFoeTeam() {
        return foeTeam;
    }

    public NatStringTreeMap<ActivityOfMove> getEnabledMoves() {
        return enabledMoves;
    }

    public DictionaryComparator<StringList,ActivityOfMove> getEnabledMovesByGroup() {
        return enabledMovesByGroup;
    }

    public NatStringTreeMap<LgInt> getEnabledMovesWhileSendingFoeUses() {
        return enabledMovesWhileSendingFoeUses;
    }

    public NatStringTreeMap<Long> getNbUsesMoves() {
        return nbUsesMoves;
    }

    public NatStringTreeMap<IntTreeMap<StacksOfUses>> getHealAfter() {
        return healAfter;
    }

    public NatStringTreeMap<IntTreeMap<Anticipation>> getMovesAnticipation() {
        return movesAnticipation;
    }

    public IntTreeMap<Ints> getPlayerFightersAgainstFoe() {
        return playerFightersAgainstFoe;
    }
}