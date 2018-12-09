package aiki.beans.fight;
import aiki.DataBase;
import aiki.beans.facade.comparators.ComparatorStringList;
import aiki.facade.FacadeGame;
import aiki.game.fight.ActivityOfMove;
import aiki.game.fight.Anticipation;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.StacksOfUses;
import aiki.game.fight.TargetCoords;
import aiki.game.fight.Team;
import code.maths.LgInt;
import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public class TeamBean extends CommonFightBean {
    private TreeMap<StringList,ActivityOfMove> enabledMovesByGroup;
    private NatStringTreeMap<ActivityOfMove> enabledMoves;
    private NatStringTreeMap<LgInt> enabledMovesWhileSendingFoeUses;
    private NatStringTreeMap<Integer> nbUsesMoves;
    private NatStringTreeMap<NatTreeMap<Byte,StacksOfUses>> healAfter;
    private NatStringTreeMap<NatTreeMap<Byte,Anticipation>> movesAnticipation;
    private NatTreeMap<Byte,Numbers<Byte> > playerFightersAgainstFoe;
    private boolean foeTeam;

    @Override
    public void beforeDisplaying() {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        DataBase data_ = dataBaseFight_.getData();
        Number noTeam_ = (Number) getForms().getVal(NO_TEAM);
        foeTeam = noTeam_.byteValue() == Fight.FOE;
        Team team_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_.byteValue());
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
        TreeMap<StringList,ActivityOfMove> enabledMovesByGroup_;
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
        enabledMovesByGroup_ = new TreeMap<StringList, ActivityOfMove>(new ComparatorStringList(data_, getLanguage(), true));
        for (StringList s: team_.getEnabledMovesByGroup().getKeys()) {
            StringList key_ = new StringList();
            for (String m: s) {
                key_.add(translationsMoves_.getVal(m));
            }
            key_.sort();
            enabledMovesByGroup_.put(key_, new ActivityOfMove(team_.getEnabledMovesByGroup().getVal(s)));
        }
        enabledMovesByGroup = enabledMovesByGroup_;
        NatStringTreeMap<NatTreeMap<Byte,StacksOfUses>> healAfter_;
        healAfter_ = new NatStringTreeMap<NatTreeMap<Byte,StacksOfUses>>();
        for (String m: team_.getHealAfterSet()) {
            NatTreeMap<Byte,StacksOfUses> h_;
            h_ = new NatTreeMap<Byte,StacksOfUses>();
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
        NatStringTreeMap<NatTreeMap<Byte,Anticipation>> movesAnticipation_;
        movesAnticipation_ = new NatStringTreeMap<NatTreeMap<Byte,Anticipation>>();
        for (String m: team_.getMovesAnticipationSet()) {
            NatTreeMap<Byte,Anticipation> a_;
            a_ = new NatTreeMap<Byte,Anticipation>();
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
        playerFightersAgainstFoe = new NatTreeMap<Byte,Numbers<Byte> >();
        for (byte p: team_.getPlayerFightersAgainstFoeSet()) {
            Numbers<Byte> numbers_ = new Numbers<Byte>();
            numbers_.addAllElts(team_.getPlayerFightersAgainstFoeVal(p));
            numbers_.sort();
            playerFightersAgainstFoe.put(p, numbers_);
        }
    }
    public Numbers<Byte> getMembers() {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        Number noTeam_ = (Number) getForms().getVal(NO_TEAM);
        return getMembers(dataBaseFight_, noTeam_.byteValue());
    }
    public String getTrPokemonLink(Long _index) {
        byte index_ = getMembers().get(_index.intValue());
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        DataBase data_ = dataBaseFight_.getData();
        Number noTeam_ = (Number) getForms().getVal(NO_TEAM);
        Team team_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_.byteValue());
        Fighter fighter_ = team_.getMembers().getVal(index_);
        byte i_ = CustList.FIRST_INDEX;
        byte nb_ = CustList.FIRST_INDEX;
        while (i_ < _index.intValue()) {
            byte iTmp_ = getMembers().get(i_);
            Fighter current_ = team_.getMembers().getVal(iTmp_);
            //fighter_ != current_
            if (StringList.quickEq(fighter_.getName(), current_.getName())) {
                nb_++;
            }
            i_++;
        }
        if (nb_ == CustList.FIRST_INDEX) {
            return data_.translatePokemon(fighter_.getName());
        }
        return StringList.concat(data_.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }
    public String clickFighter(Long _index) {
        getForms().put(NO_FIGHTER, _index);
        return FIGHTER;
    }
    public String getKey(Long _index) {
        return enabledMovesByGroup.getKey(_index.intValue()).join(MOVES_SEPARATOR);
    }
    public boolean isFoeMovesAnticipationTeam(Long _indexOne,Long _indexTwo) {
        return movesAnticipation.getValue(_indexOne.intValue()).getValue(_indexTwo.intValue()).getTargetPosition().getTeam() == Fight.FOE;
    }
    public boolean isBackMovesAnticipationTeam(Long _indexOne,Long _indexTwo) {
        return movesAnticipation.getValue(_indexOne.intValue()).getValue(_indexTwo.intValue()).getTargetPosition().getPosition() == Fighter.BACK;
    }
    public String getPlayerFigtherAgainstFoe(Long _index) {
        byte key_ = playerFightersAgainstFoe.getKey(_index.intValue());
        return getDisplayName(key_, _index);
    }
    public String getFoeFigtherAgainstFoe(Long _indexOne, Long _indexTwo) {
        byte key_ = playerFightersAgainstFoe.getValue(_indexOne.intValue()).get(_indexTwo.intValue());
        return getDisplayFoeName(key_, _indexTwo);
    }

    private String getDisplayName(byte _indexOne, Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        DataBase data_ = dataBaseFight_.getData();
        Team team_ = dataBaseFight_.getGame().getFight().getUserTeam();
        Fighter fighter_ = team_.getMembers().getVal(_indexOne);
        byte i_ = CustList.FIRST_INDEX;
        byte nb_ = CustList.FIRST_INDEX;
        while (i_ < _index.intValue()) {
            byte iTmp_ = getMembers().get(i_);
            Fighter current_ = team_.getMembers().getVal(iTmp_);
            //fighter_ != current_
            if (StringList.quickEq(fighter_.getName(), current_.getName())) {
                nb_++;
            }
            i_++;
        }
        if (nb_ == CustList.FIRST_INDEX) {
            return data_.translatePokemon(fighter_.getName());
        }
        return StringList.concat(data_.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }

    private String getDisplayFoeName(byte _indexOne, Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        DataBase data_ = dataBaseFight_.getData();
        Team team_ = dataBaseFight_.getGame().getFight().getFoeTeam();
        Fighter fighter_ = team_.getMembers().getVal(_indexOne);
        byte i_ = CustList.FIRST_INDEX;
        byte nb_ = CustList.FIRST_INDEX;
        while (i_ < _index.intValue()) {
            byte iTmp_ = getMembers().get(i_);
            Fighter current_ = team_.getMembers().getVal(iTmp_);
            //fighter_ != current_
            if (StringList.quickEq(fighter_.getName(), current_.getName())) {
                nb_++;
            }
            i_++;
        }
        if (nb_ == CustList.FIRST_INDEX) {
            return data_.translatePokemon(fighter_.getName());
        }
        return StringList.concat(data_.translatePokemon(fighter_.getName()),SPACE,Long.toString(nb_));
    }

    public boolean getFoeTeam() {
        return foeTeam;
    }

    public NatStringTreeMap<ActivityOfMove> getEnabledMoves() {
        return enabledMoves;
    }

    public TreeMap<StringList,ActivityOfMove> getEnabledMovesByGroup() {
        return enabledMovesByGroup;
    }

    public NatStringTreeMap<LgInt> getEnabledMovesWhileSendingFoeUses() {
        return enabledMovesWhileSendingFoeUses;
    }

    public NatStringTreeMap<Integer> getNbUsesMoves() {
        return nbUsesMoves;
    }

    public NatStringTreeMap<NatTreeMap<Byte,StacksOfUses>> getHealAfter() {
        return healAfter;
    }

    public NatStringTreeMap<NatTreeMap<Byte,Anticipation>> getMovesAnticipation() {
        return movesAnticipation;
    }

    public NatTreeMap<Byte,Numbers<Byte>> getPlayerFightersAgainstFoe() {
        return playerFightersAgainstFoe;
    }
}