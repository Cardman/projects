package aiki.beans.fight;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.SortableCustList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.facade.comparators.ComparatorMoveTarget;
import aiki.beans.facade.fight.KeyHypothesis;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.Team;
import aiki.game.fight.TeamPosition;
import aiki.game.fight.util.MoveTarget;

public class FightCalculationBean extends CommonFightBean {

    @Accessible
    private TreeMap<MoveTarget, MoveTarget> allyChoice;

    @Accessible
    private NatTreeMap<Byte,MoveTarget> foeChoices;

    private NatTreeMap<Byte,Boolean> foeChoicesTargets;

    @Accessible
    private SortableCustList<KeyHypothesis> damage;

    @Accessible
    private EqList<TeamPosition> sortedFighters;

    @Accessible
    private NatTreeMap<String,EqList<TeamPosition>> sortedFightersWildFight;

    @Override
    public void beforeDisplaying() {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
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
            sortedFightersWildFight = new NatTreeMap<String,EqList<TeamPosition>>();
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
        NatTreeMap<Byte,MoveTarget> foeChoices_;
        foeChoices_ = new NatTreeMap<Byte,MoveTarget>();
        NatTreeMap<Byte,Boolean> foeChoicesTargets_;
        foeChoicesTargets_ = new NatTreeMap<Byte,Boolean>();
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

    @Accessible
    private String getFighter(Long _indexOne, Long _indexTwo) {
        TeamPosition f_ = sortedFightersWildFight.getValue(_indexOne.intValue()).get(_indexTwo.intValue());
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        return getFighter(dataBaseFight_, f_);
    }

    @Accessible
    private String getFighter(Long _index) {
        TeamPosition f_ = sortedFighters.get(_index.intValue());
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        return getFighter(dataBaseFight_, f_);
    }

    @Accessible
    private boolean isFoeTargetChoiceTeam(Long _index) {
        return allyChoice.getKey(_index.intValue()).getTarget().getTeam() == Fight.FOE;
    }

    @Accessible
    private boolean isFoeTargetTeam(Long _index) {
        return allyChoice.getValue(_index.intValue()).getTarget().getTeam() == Fight.FOE;
    }

    @Accessible
    private boolean isBackTargetChoiceTeam(Long _index) {
        return allyChoice.getKey(_index.intValue()).getTarget().getPosition() == Fighter.BACK;
    }

    @Accessible
    private boolean isBackTargetTeam(Long _index) {
        return allyChoice.getValue(_index.intValue()).getTarget().getPosition() == Fighter.BACK;
    }

    @Accessible
    private String getTargetNameAllyChoiceCondition(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        MoveTarget mTarget_ = allyChoice.getKey(_index.byteValue());
        TeamPosition key_ = fight_.getFighterKey(mTarget_.getTarget());
        Fighter fighter_ = fight_.getFighter(key_);
        return dataBaseFight_.translatePokemon(fighter_.getName());
    }

    @Accessible
    private String getTargetNameAllyChoice(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        MoveTarget mTarget_ = allyChoice.getValue(_index.byteValue());
        TeamPosition key_ = fight_.getFighterKey(mTarget_.getTarget());
        Fighter fighter_ = fight_.getFighter(key_);
        return dataBaseFight_.translatePokemon(fighter_.getName());
    }

    @Accessible
    private boolean isChosenTarget(Long _index) {
        return foeChoicesTargets.getValue(_index.intValue());
    }

    @Accessible
    private String getTargetNameFoeChoice(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        MoveTarget mTarget_ = foeChoices.getVal(_index.byteValue());
        TeamPosition key_ = fight_.getFighterKey(mTarget_.getTarget());
        Fighter fighter_ = fight_.getFighter(key_);
        return dataBaseFight_.translatePokemon(fighter_.getName());
    }

    @Accessible
    private String getFoeFighterName(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        Team team_ = fight_.getFoeTeam();
        byte key_ = foeChoices.getKey(_index.byteValue());
        Fighter f_ = team_.refPartMembres(key_);
        return dataBaseFight_.translatePokemon(f_.getName());
    }

    @Accessible
    private boolean isFoeTargetChTeam(Long _index) {
        return foeChoices.getValue(_index.intValue()).getTarget().getTeam() == Fight.FOE;
    }
}
