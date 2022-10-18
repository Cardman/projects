package aiki.beans.fight;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.*;
import code.maths.LgInt;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class TeamBean extends CommonFightBean {
    private DictionaryComparator<StringList,ActivityOfMove> enabledMovesByGroup;
    private NatStringTreeMap<ActivityOfMove> enabledMoves;
    private NatStringTreeMap<LgInt> enabledMovesWhileSendingFoeUses;
    private NatStringTreeMap<Integer> nbUsesMoves;
    private NatStringTreeMap<ByteTreeMap<StacksOfUses>> healAfter;
    private NatStringTreeMap<ByteTreeMap<Anticipation>> movesAnticipation;
    private ByteTreeMap<Bytes > playerFightersAgainstFoe;
    private boolean foeTeam;

    @Override
    public void beforeDisplaying() {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        int noTeam_ = getForms().getValInt(NO_TEAM);
        foeTeam = noTeam_ == Fight.CST_FOE;
        Team team_ = dataBaseFight_.getGame().getFight().getTeams().getVal((byte) noTeam_);
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
        NatStringTreeMap<Integer> nbUsesMoves_;
        nbUsesMoves_ = new NatStringTreeMap<Integer>();
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
        NatStringTreeMap<ByteTreeMap<StacksOfUses>> healAfter_;
        healAfter_ = new NatStringTreeMap<ByteTreeMap<StacksOfUses>>();
        for (String m: team_.getHealAfterSet()) {
            ByteTreeMap<StacksOfUses> h_;
            h_ = new ByteTreeMap<StacksOfUses>();
            for (byte k: team_.getHealAfterSet(m)) {
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
        NatStringTreeMap<ByteTreeMap<Anticipation>> movesAnticipation_;
        movesAnticipation_ = new NatStringTreeMap<ByteTreeMap<Anticipation>>();
        for (String m: team_.getMovesAnticipationSet()) {
            ByteTreeMap<Anticipation> a_;
            a_ = new ByteTreeMap<Anticipation>();
            for (byte k: team_.getMovesAnticipationSet(m)) {
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
        playerFightersAgainstFoe = new ByteTreeMap<Bytes >();
        for (byte p: team_.getPlayerFightersAgainstFoeSet()) {
            Bytes numbers_ = new Bytes();
            numbers_.addAllElts(team_.getPlayerFightersAgainstFoeVal(p));
            numbers_.sort();
            playerFightersAgainstFoe.put(p, numbers_);
        }
    }
    public Bytes getMembers() {
        FacadeGame dataBaseFight_ = facade();
        int noTeam_ = getForms().getValInt(NO_TEAM);
        return getMembers(dataBaseFight_, noTeam_);
    }
    public String getTrPokemonLink(int _index) {
        byte index_ = getMembers().get(_index);
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        int noTeam_ = getForms().getValInt(NO_TEAM);
        Team team_ = dataBaseFight_.getGame().getFight().getTeams().getVal((byte) noTeam_);
        Fighter fighter_ = team_.getMembers().getVal(index_);
        byte nb_ = number(team_, index_, _index, getMembers());
        if (nb_ == IndexConstants.FIRST_INDEX) {
            return data_.translatePokemon(fighter_.getName());
        }
        return StringUtil.concat(data_.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }
    public String clickFighter(int _index) {
        getForms().put(NO_FIGHTER, _index);
        return FIGHTER;
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
        byte key_ = playerFightersAgainstFoe.getKey(_index);
        return getDisplayName(key_, _index);
    }
    public String getFoeFigtherAgainstFoe(int _indexOne, int _indexTwo) {
        byte key_ = playerFightersAgainstFoe.getValue(_indexOne).get(_indexTwo);
        return getDisplayFoeName(key_, _indexTwo);
    }

    private String getDisplayName(byte _indexOne, int _index) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        Team team_ = dataBaseFight_.getGame().getFight().getUserTeam();
        Fighter fighter_ = team_.getMembers().getVal(_indexOne);
        byte nb_ = number(team_, _indexOne,_index, getMembers());
        if (nb_ == IndexConstants.FIRST_INDEX) {
            return data_.translatePokemon(fighter_.getName());
        }
        return StringUtil.concat(data_.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }

    private String getDisplayFoeName(byte _indexOne, int _index) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        Team team_ = dataBaseFight_.getGame().getFight().getFoeTeam();
        Fighter fighter_ = team_.getMembers().getVal(_indexOne);
        byte nb_ = number(team_, _indexOne, _index, getMembers());
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

    public NatStringTreeMap<Integer> getNbUsesMoves() {
        return nbUsesMoves;
    }

    public NatStringTreeMap<ByteTreeMap<StacksOfUses>> getHealAfter() {
        return healAfter;
    }

    public NatStringTreeMap<ByteTreeMap<Anticipation>> getMovesAnticipation() {
        return movesAnticipation;
    }

    public ByteTreeMap<Bytes> getPlayerFightersAgainstFoe() {
        return playerFightersAgainstFoe;
    }
}