package aiki.beans.fight;

import aiki.beans.facade.comparators.ComparatorKeyHypothesis;
import aiki.beans.facade.fight.KeyHypothesis;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.*;
import aiki.game.fight.util.MoveTarget;
import aiki.util.PairRates;
import aiki.util.TeamPositionList;
import aiki.util.TeamPositionsStringMapTeamPositionsRate;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;

public class FightCalculationBean extends CommonFightBean {
    private DictionaryComparator<MoveTarget, MoveTarget> allyChoice;
    private ByteTreeMap<MoveTarget> foeChoices;

    private ByteTreeMap<BoolVal> foeChoicesTargets;
    private CustList<KeyHypothesis> damage;
    private TeamPositionList sortedFighters;
    private NatStringTreeMap<TeamPositionList> sortedFightersWildFight;

    @Override
    public void beforeDisplaying() {
        FacadeGame dataBaseFight_ = facade();
        damageInit(dataBaseFight_);
        if (!dataBaseFight_.getGame().getFight().getFightType().isWild()) {
            sortedFighters = dataBaseFight_.sortedFightersBeginRound();
        } else {
            sortedFighters = new TeamPositionList();
        }
        if (dataBaseFight_.getGame().getFight().getFightType().isWild()) {
            sortedFightersWildFight = dataBaseFight_.sortedFightersBeginRoundWildFight();
        } else {
            sortedFightersWildFight = new NatStringTreeMap<TeamPositionList>();
        }
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        Fight fight_ = dataBaseFight_.getGame().getFight();
        DictionaryComparator<MoveTarget, MoveTarget> allyChoice_;
        allyChoice_ = DictionaryComparatorUtil.buildMoveTarget();
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
        ByteTreeMap<BoolVal> foeChoicesTargets_;
        foeChoicesTargets_ = new ByteTreeMap<BoolVal>();
        for (byte k: fight_.getFoeTeam().getMembers().getKeys()) {
            Fighter f_ = fight_.getFoeTeam().getMembers().getVal(k);
            String move_ = f_.getFirstChosenMove();
            if (move_.isEmpty()) {
                continue;
            }
            MoveTarget value_ = new MoveTarget();
            value_.setMove(translationsMoves_.getVal(move_));
            if (!f_.getChosenTargets().isEmpty()) {
                value_.setTarget(f_.getChosenTargets().first());
            } else {
                value_.setTarget(new TargetCoords((short) -1,Fighter.BACK));
            }
            foeChoices_.put(k, value_);
            foeChoicesTargets_.put(k, ComparatorBoolean.of(!f_.getChosenTargets().isEmpty()));
        }
        foeChoices = foeChoices_;
        foeChoicesTargets = foeChoicesTargets_;
    }

    private void damageInit(FacadeGame _dataBaseFight) {
        TeamPositionsStringMapTeamPositionsRate resTh_;
        resTh_ = _dataBaseFight.remainingThrowersTargetsHp();
        damage = new CustList<KeyHypothesis>();
        for (TeamPosition p: resTh_.getKeys()) {
            for (String m: resTh_.getVal(p).getKeys()) {
                for (TeamPosition t: resTh_.getVal(p).getVal(m).getFoe().getKeys()) {
                    KeyHypothesis key_;
                    key_ = new KeyHypothesis(_dataBaseFight, p, m, t);
                    PairRates pair_ = resTh_.getVal(p).getVal(m).getFoe().getVal(t);
                    key_.setDamage(pair_.getFront());
                    key_.setDamageSecond(pair_.getBack());
                    damage.add(key_);
                }
                for (TeamPosition t: resTh_.getVal(p).getVal(m).getPlayer().getKeys()) {
                    KeyHypothesis key_;
                    key_ = new KeyHypothesis(_dataBaseFight, p, m, t);
                    PairRates pair_ = resTh_.getVal(p).getVal(m).getPlayer().getVal(t);
                    key_.setDamage(pair_.getFront());
                    key_.setDamageSecond(pair_.getBack());
                    damage.add(key_);
                }
            }
        }
        damage.sortElts(new ComparatorKeyHypothesis());
    }

    public String getFighterWildFight(int _indexOne, int _indexTwo) {
        TeamPosition f_ = sortedFightersWildFight.getValue(_indexOne).get(_indexTwo);
        FacadeGame dataBaseFight_ = facade();
        return getFighterAtPosition(dataBaseFight_, f_);
    }
    public String getFighter(int _index) {
        TeamPosition f_ = sortedFighters.get(_index);
        FacadeGame dataBaseFight_ = facade();
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
        FacadeGame dataBaseFight_ = facade();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        MoveTarget mTarget_ = allyChoice.getKey(_index);
        TeamPosition key_ = fight_.getFighterKey(mTarget_.getTarget());
        Fighter fighter_ = fight_.getFighter(key_);
        return dataBaseFight_.translatePokemon(fighter_.getName());
    }
    public String getTargetNameAllyChoice(int _index) {
        FacadeGame dataBaseFight_ = facade();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        MoveTarget mTarget_ = allyChoice.getValue(_index);
        TeamPosition key_ = fight_.getFighterKey(mTarget_.getTarget());
        Fighter fighter_ = fight_.getFighter(key_);
        return dataBaseFight_.translatePokemon(fighter_.getName());
    }
    public boolean isChosenTarget(int _index) {
        return foeChoicesTargets.getValue(_index) == BoolVal.TRUE;
    }
    public String getTargetNameFoeChoice(int _index) {
        FacadeGame dataBaseFight_ = facade();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        MoveTarget mTarget_ = foeChoices.getVal((byte) _index);
        TeamPosition key_ = fight_.getFighterKey(mTarget_.getTarget());
        Fighter fighter_ = fight_.getFighter(key_);
        return dataBaseFight_.translatePokemon(fighter_.getName());
    }
    public String getFoeFighterName(int _index) {
        FacadeGame dataBaseFight_ = facade();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        Team team_ = fight_.getFoeTeam();
        byte key_ = foeChoices.getKey(_index);
        Fighter f_ = team_.refPartMembres(key_);
        return dataBaseFight_.translatePokemon(f_.getName());
    }
    public boolean isFoeTargetChTeam(int _index) {
        return foeChoices.getValue(_index).getTarget().getTeam() == Fight.CST_FOE;
    }

    public TeamPositionList getSortedFighters() {
        return sortedFighters;
    }

    public NatStringTreeMap<TeamPositionList> getSortedFightersWildFight() {
        return sortedFightersWildFight;
    }

    public CustList<KeyHypothesis> getDamage() {
        return damage;
    }

    public DictionaryComparator<MoveTarget,MoveTarget> getAllyChoice() {
        return allyChoice;
    }

    public ByteTreeMap<MoveTarget> getFoeChoices() {
        return foeChoices;
    }
}