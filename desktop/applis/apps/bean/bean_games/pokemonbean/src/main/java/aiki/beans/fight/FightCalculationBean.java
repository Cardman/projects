package aiki.beans.fight;

import aiki.beans.facade.fight.FighterNameId;
import aiki.beans.facade.fight.KeyHypothesis;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.*;
import aiki.game.fight.util.MoveTarget;
import aiki.util.*;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;

public class FightCalculationBean extends CommonFightBean {
    private DictionaryComparator<MoveTarget, MoveTarget> allyChoice;
    private IntTreeMap<MoveTarget> foeChoices;

    private IntTreeMap<BoolVal> foeChoicesTargets;
    private CustList<KeyHypothesis> damage;
    private TeamPositionList sortedFighters;
    private CustList<ImgMovesListTeamPositionsList> sortedFightersWildFight;

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
            sortedFightersWildFight = convert();
        } else {
            sortedFightersWildFight = new CustList<ImgMovesListTeamPositionsList>();
        }
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        Fight fight_ = dataBaseFight_.getGame().getFight();
        DictionaryComparator<MoveTarget, MoveTarget> allyChoice_;
        allyChoice_ = DictionaryComparatorUtil.buildMoveTarget();
        for (EntryCust<MoveTarget, MoveTarget> m: fight_.getAllyChoice().entryList()) {
            String move_ = m.getKey().getMove();
            if (!move_.isEmpty()) {
                move_ = translationsMoves_.getVal(move_);
            }
            MoveTarget key_ = new MoveTarget(move_, m.getKey().getTarget());
            move_ = m.getValue().getMove();
            if (!move_.isEmpty()) {
                move_ = translationsMoves_.getVal(move_);
            }
            MoveTarget value_ = new MoveTarget(move_, m.getValue().getTarget());
            allyChoice_.put(key_, value_);
        }
        allyChoice = allyChoice_;
        IntTreeMap<MoveTarget> foeChoices_;
        foeChoices_ = new IntTreeMap<MoveTarget>();
        IntTreeMap<BoolVal> foeChoicesTargets_;
        foeChoicesTargets_ = new IntTreeMap<BoolVal>();
        for (int k: fight_.getFoeTeam().getMembers().getKeys()) {
            Fighter f_ = fight_.getFoeTeam().getMembers().getVal(k);
            String move_ = f_.getFirstChosenMove();
            if (move_.isEmpty()) {
                continue;
            }
            MoveTarget value_;
            if (!f_.getChosenTargets().isEmpty()) {
                value_ = new MoveTarget(translationsMoves_.getVal(move_),f_.getChosenTargets().first());
            } else {
                value_ = new MoveTarget(translationsMoves_.getVal(move_),TargetCoords.def());
            }
            foeChoices_.put(k, value_);
            foeChoicesTargets_.put(k, ComparatorBoolean.of(!f_.getChosenTargets().isEmpty()));
        }
        foeChoices = foeChoices_;
        foeChoicesTargets = foeChoicesTargets_;
    }

    private CustList<ImgMovesListTeamPositionsList> convert() {
        FacadeGame dataBaseFight_ = facade();
        CustList<ImgMovesListTeamPositionsList> t_ = new CustList<ImgMovesListTeamPositionsList>();
        for (MovesListTeamPositionsList e: dataBaseFight_.sortedFightersBeginRoundWildFight()) {
            CustList<FighterImgPkNameMv> keys_ = new CustList<FighterImgPkNameMv>();
            for (FighterNamePkNameMv k:e.getKeyPks()) {
                FighterImgPkNameMv cp_ = new FighterImgPkNameMv();
//                cp_.setNamePk(k.getNamePk());
                cp_.setImagePk(dataBaseFight_.getData().getMaxiPkFront().getVal(k.getNamePk()).getImage());
//                cp_.setNameMv(k.getNameMv());
                cp_.setNameMvTr(k.getNameMvTr());
//                cp_.setNumber(k.getNumber());
                keys_.add(cp_);
            }
            t_.add(new ImgMovesListTeamPositionsList(keys_,e.getTeamPositions()));
        }
        return t_;
    }

    private void damageInit(FacadeGame _dataBaseFight) {
        TeamPositionsStringMapTeamPositionsRate resTh_;
        resTh_ = _dataBaseFight.remainingThrowersTargetsHp();
        DictionaryComparator<FighterNameId,DictionaryComparator<String,IdMap<FighterNameId, KeyHypothesis>>> all_;
        all_ = DictionaryComparatorUtil.buildCalcAll(_dataBaseFight.getData(),getLanguage());
        Fight fight_ = _dataBaseFight.getFight();
        damage = new CustList<KeyHypothesis>();
        for (EntryCust<TeamPosition, StringMap<TeamPositionsPairRatesPair>> p: resTh_.entryList()) {
            DictionaryComparator<String, IdMap<FighterNameId, KeyHypothesis>> moves_ = DictionaryComparatorUtil.buildCalcMoves(_dataBaseFight.getData(), getLanguage());
            String plName_ = fight_.getFighter(p.getKey()).getName();
            FighterNameId id_ = new FighterNameId(plName_, p.getKey().getPosition());
            for (EntryCust<String, TeamPositionsPairRatesPair> m: p.getValue().entryList()) {
                IdMap<FighterNameId, KeyHypothesis> group_ = new IdMap<FighterNameId, KeyHypothesis>();
                group_.addAllEntries(build(_dataBaseFight,id_, m.getKey(), false, m.getValue().getFoe(), getLanguage()));
                group_.addAllEntries(build(_dataBaseFight,id_, m.getKey(), true, m.getValue().getPlayer(), getLanguage()));
                moves_.put(m.getKey(),group_);
            }
            all_.put(id_,moves_);
        }
        for (EntryCust<FighterNameId,DictionaryComparator<String, IdMap<FighterNameId, KeyHypothesis>>> e:all_.entryList()) {
            for (EntryCust<String, IdMap<FighterNameId, KeyHypothesis>> f:e.getValue().entryList()) {
                for (EntryCust<FighterNameId, KeyHypothesis> g:f.getValue().entryList()) {
                    damage.add(g.getValue());
                }
            }
        }
    }

    private static DictionaryComparator<FighterNameId, KeyHypothesis> build(FacadeGame _dataBaseFight, FighterNameId _idPl, String _m, boolean _bel, TeamPositionsPairRates _g, String _lg) {
        Fight fight_ = _dataBaseFight.getFight();
        DictionaryComparator<FighterNameId, KeyHypothesis> player_ = DictionaryComparatorUtil.buildCalcLoc(_dataBaseFight.getData(), _lg);
        for (EntryCust<TeamPosition, PairRates> t: _g.entryList()) {
            String tarName_ = fight_.getFighter(t.getKey()).getName();
            FighterNameId idTar_ = new FighterNameId(tarName_, t.getKey().getPosition());
            player_.put(idTar_,new KeyHypothesis(_dataBaseFight, _idPl, _m, idTar_, _bel, t.getValue()));
        }
        return player_;
    }

    public String getFighterWildFight(int _indexOne, int _indexTwo) {
        TeamPosition f_ = sortedFightersWildFight.get(_indexOne).getTeamPositions().get(_indexTwo);
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
        MoveTarget mTarget_ = foeChoices.getVal(_index);
        TeamPosition key_ = fight_.getFighterKey(mTarget_.getTarget());
        Fighter fighter_ = fight_.getFighter(key_);
        return dataBaseFight_.translatePokemon(fighter_.getName());
    }
    public String getFoeFighterName(int _index) {
        FacadeGame dataBaseFight_ = facade();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        Team team_ = fight_.getFoeTeam();
        int key_ = foeChoices.getKey(_index);
        Fighter f_ = team_.refPartMembres(key_);
        return dataBaseFight_.translatePokemon(f_.getName());
    }
    public boolean isFoeTargetChTeam(int _index) {
        return foeChoices.getValue(_index).getTarget().getTeam() == Fight.CST_FOE;
    }

    public TeamPositionList getSortedFighters() {
        return sortedFighters;
    }

    public CustList<ImgMovesListTeamPositionsList> getSortedFightersWildFight() {
        return sortedFightersWildFight;
    }

    public CustList<KeyHypothesis> getDamage() {
        return damage;
    }

    public DictionaryComparator<MoveTarget,MoveTarget> getAllyChoice() {
        return allyChoice;
    }

    public IntTreeMap<MoveTarget> getFoeChoices() {
        return foeChoices;
    }
}