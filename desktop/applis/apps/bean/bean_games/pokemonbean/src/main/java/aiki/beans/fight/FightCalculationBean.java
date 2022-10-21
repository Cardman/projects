package aiki.beans.fight;

import aiki.beans.facade.fight.FighterNameId;
import aiki.beans.facade.fight.KeyHypothesis;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.*;
import aiki.game.fight.util.MoveTarget;
import aiki.util.PairRates;
import aiki.util.TeamPositionList;
import aiki.util.TeamPositionsPairRates;
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
        DictionaryComparator<FighterNameId,DictionaryComparator<String,IdMap<FighterNameId, KeyHypothesis>>> all_;
        all_ = DictionaryComparatorUtil.buildCalcAll(_dataBaseFight.getData(),getLanguage());
        Fight fight_ = _dataBaseFight.getFight();
        damage = new CustList<KeyHypothesis>();
        for (TeamPosition p: resTh_.getKeys()) {
            DictionaryComparator<String, IdMap<FighterNameId, KeyHypothesis>> moves_ = DictionaryComparatorUtil.buildCalcMoves(_dataBaseFight.getData(), getLanguage());
            String plName_ = fight_.getFighter(p).getName();
            for (String m: resTh_.getVal(p).getKeys()) {
                IdMap<FighterNameId, KeyHypothesis> group_ = new IdMap<FighterNameId, KeyHypothesis>();
                group_.addAllEntries(build(_dataBaseFight, p, plName_, m, resTh_.getVal(p).getVal(m).getFoe()));
                group_.addAllEntries(build(_dataBaseFight, p, plName_, m, resTh_.getVal(p).getVal(m).getPlayer()));
                moves_.put(m,group_);
            }
            all_.put(new FighterNameId(plName_,p.getPosition()),moves_);
        }
        for (EntryCust<FighterNameId,DictionaryComparator<String, IdMap<FighterNameId, KeyHypothesis>>> e:all_.entryList()) {
            for (EntryCust<String, IdMap<FighterNameId, KeyHypothesis>> f:e.getValue().entryList()) {
                for (EntryCust<FighterNameId, KeyHypothesis> g:f.getValue().entryList()) {
                    damage.add(g.getValue());
                }
            }
        }
    }

    private DictionaryComparator<FighterNameId, KeyHypothesis> build(FacadeGame _dataBaseFight, TeamPosition _p, String _plName, String _m, TeamPositionsPairRates _g) {
        Fight fight_ = _dataBaseFight.getFight();
        DictionaryComparator<FighterNameId, KeyHypothesis> player_ = DictionaryComparatorUtil.buildCalcLoc(_dataBaseFight.getData(), getLanguage());
        for (TeamPosition t: _g.getKeys()) {
            KeyHypothesis key_;
            String tarName_ = fight_.getFighter(t).getName();
            key_ = new KeyHypothesis(_dataBaseFight, _p, _plName, _m, t, tarName_);
            PairRates pair_ = _g.getVal(t);
            key_.setDamage(pair_.getFront());
            key_.setDamageSecond(pair_.getBack());
            player_.put(new FighterNameId(tarName_,t.getPosition()),key_);
        }
        return player_;
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