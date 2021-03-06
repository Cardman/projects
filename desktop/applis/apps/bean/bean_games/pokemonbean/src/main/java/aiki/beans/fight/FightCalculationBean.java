package aiki.beans.fight;
import aiki.beans.facade.comparators.ComparatorMoveTarget;
import aiki.beans.facade.fight.KeyHypothesis;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.Team;
import aiki.game.fight.TeamPosition;
import aiki.game.fight.util.MoveTarget;
import code.maths.Rate;
import code.util.EqList;
import code.util.NatStringTreeMap;
import code.util.*;
import code.util.ObjectMap;
import code.util.SortableCustList;
import code.util.StringMap;
import code.util.TreeMap;

public class FightCalculationBean extends CommonFightBean {
    private TreeMap<MoveTarget, MoveTarget> allyChoice;
    private ByteTreeMap<MoveTarget> foeChoices;

    private ByteTreeMap<Boolean> foeChoicesTargets;
    private SortableCustList<KeyHypothesis> damage;
    private EqList<TeamPosition> sortedFighters;
    private NatStringTreeMap<EqList<TeamPosition>> sortedFightersWildFight;

    @Override
    public void beforeDisplaying() {
        FacadeGame dataBaseFight_ = getDataBase();
        ObjectMap<TeamPosition,StringMap<ObjectMap<TeamPosition,Rate>>> resTh_;
        resTh_ = dataBaseFight_.remainingThrowersTargetsHp();
        damage = new SortableCustList<KeyHypothesis>();
        for (TeamPosition p: resTh_.getKeys()) {
            for (String m: resTh_.getVal(p).getKeys()) {
                for (TeamPosition t: resTh_.getVal(p).getVal(m).getKeys()) {
                    KeyHypothesis key_;
                    key_ = new KeyHypothesis(dataBaseFight_, p, m, t);
                    key_.setDamage(resTh_.getVal(p).getVal(m).getVal(t));
                    damage.add(key_);
                }
            }
        }
        damage.sort();
        if (!dataBaseFight_.getGame().getFight().getFightType().isWild()) {
            sortedFighters = dataBaseFight_.sortedFightersBeginRound();
        } else {
            sortedFighters = new EqList<TeamPosition>();
        }
        if (dataBaseFight_.getGame().getFight().getFightType().isWild()) {
            sortedFightersWildFight = dataBaseFight_.sortedFightersBeginRoundWildFight();
        } else {
            sortedFightersWildFight = new NatStringTreeMap<EqList<TeamPosition>>();
        }
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        Fight fight_ = dataBaseFight_.getGame().getFight();
        TreeMap<MoveTarget, MoveTarget> allyChoice_;
        allyChoice_ = new TreeMap<MoveTarget, MoveTarget>(new ComparatorMoveTarget());
        for (MoveTarget m: fight_.getAllyChoiceSet()) {
            MoveTarget key_;
            key_ = new MoveTarget();
            String move_ = m.getMove();
            if (!move_.isEmpty()) {
                move_ = translationsMoves_.getVal(move_);
            }
            key_.setMove(move_);
            key_.setTarget(m.getTarget());
            MoveTarget value_;
            value_ = new MoveTarget();
            move_ = fight_.getAllyChoiceVal(m).getMove();
            if (!move_.isEmpty()) {
                move_ = translationsMoves_.getVal(move_);
            }
            value_.setMove(move_);
            value_.setTarget(fight_.getAllyChoiceVal(m).getTarget());
            allyChoice_.put(key_, value_);
        }
        allyChoice = allyChoice_;
        ByteTreeMap<MoveTarget> foeChoices_;
        foeChoices_ = new ByteTreeMap<MoveTarget>();
        ByteTreeMap<Boolean> foeChoicesTargets_;
        foeChoicesTargets_ = new ByteTreeMap<Boolean>();
        for (byte k: fight_.getFoeTeam().getMembers().getKeys()) {
            MoveTarget value_;
            value_ = new MoveTarget();
            Fighter f_ = fight_.getFoeTeam().getMembers().getVal(k);
            String move_ = f_.getFirstChosenMove();
            if (move_.isEmpty()) {
                continue;
            }
            value_.setMove(translationsMoves_.getVal(move_));
            if (!f_.getChosenTargets().isEmpty()) {
                value_.setTarget(f_.getChosenTargets().first());
            }
            foeChoices_.put(k, value_);
            foeChoicesTargets_.put(k, !f_.getChosenTargets().isEmpty());
        }
        foeChoices = foeChoices_;
        foeChoicesTargets = foeChoicesTargets_;
    }
    public String getFighterWildFight(int _indexOne, int _indexTwo) {
        TeamPosition f_ = sortedFightersWildFight.getValue(_indexOne).get(_indexTwo);
        FacadeGame dataBaseFight_ = getDataBase();
        return getFighterAtPosition(dataBaseFight_, f_);
    }
    public String getFighter(int _index) {
        TeamPosition f_ = sortedFighters.get(_index);
        FacadeGame dataBaseFight_ = getDataBase();
        return getFighterAtPosition(dataBaseFight_, f_);
    }
    public boolean isFoeTargetChoiceTeam(int _index) {
        return allyChoice.getKey(_index).getTarget().getTeam() == Fight.CST_FOE;
    }
    public boolean isFoeTargetTeam(int _index) {
        return allyChoice.getValue(_index).getTarget().getTeam() == Fight.CST_FOE;
    }
    public boolean isBackTargetChoiceTeam(int _index) {
        return allyChoice.getKey(_index).getTarget().getPosition() == Fighter.BACK;
    }
    public boolean isBackTargetTeam(int _index) {
        return allyChoice.getValue(_index).getTarget().getPosition() == Fighter.BACK;
    }
    public String getTargetNameAllyChoiceCondition(int _index) {
        FacadeGame dataBaseFight_ = getDataBase();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        MoveTarget mTarget_ = allyChoice.getKey(_index);
        TeamPosition key_ = fight_.getFighterKey(mTarget_.getTarget());
        Fighter fighter_ = fight_.getFighter(key_);
        return dataBaseFight_.translatePokemon(fighter_.getName());
    }
    public String getTargetNameAllyChoice(int _index) {
        FacadeGame dataBaseFight_ = getDataBase();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        MoveTarget mTarget_ = allyChoice.getValue(_index);
        TeamPosition key_ = fight_.getFighterKey(mTarget_.getTarget());
        Fighter fighter_ = fight_.getFighter(key_);
        return dataBaseFight_.translatePokemon(fighter_.getName());
    }
    public boolean isChosenTarget(int _index) {
        return foeChoicesTargets.getValue(_index);
    }
    public String getTargetNameFoeChoice(int _index) {
        FacadeGame dataBaseFight_ = getDataBase();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        MoveTarget mTarget_ = foeChoices.getVal((byte) _index);
        TeamPosition key_ = fight_.getFighterKey(mTarget_.getTarget());
        Fighter fighter_ = fight_.getFighter(key_);
        return dataBaseFight_.translatePokemon(fighter_.getName());
    }
    public String getFoeFighterName(int _index) {
        FacadeGame dataBaseFight_ = getDataBase();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        Team team_ = fight_.getFoeTeam();
        byte key_ = foeChoices.getKey(_index);
        Fighter f_ = team_.refPartMembres(key_);
        return dataBaseFight_.translatePokemon(f_.getName());
    }
    public boolean isFoeTargetChTeam(int _index) {
        return foeChoices.getValue(_index).getTarget().getTeam() == Fight.CST_FOE;
    }

    public EqList<TeamPosition> getSortedFighters() {
        return sortedFighters;
    }

    public NatStringTreeMap<EqList<TeamPosition>> getSortedFightersWildFight() {
        return sortedFightersWildFight;
    }

    public SortableCustList<KeyHypothesis> getDamage() {
        return damage;
    }

    public TreeMap<MoveTarget,MoveTarget> getAllyChoice() {
        return allyChoice;
    }

    public ByteTreeMap<MoveTarget> getFoeChoices() {
        return foeChoices;
    }
}