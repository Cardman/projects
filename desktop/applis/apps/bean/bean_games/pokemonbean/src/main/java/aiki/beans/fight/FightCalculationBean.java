package aiki.beans.fight;

import aiki.beans.BeanAnchorCstEvent;
import aiki.beans.BeanDisplayImgPkPlayer;
import aiki.beans.BeanDisplayList;
import aiki.beans.StringMapObject;
import aiki.beans.facade.fight.FighterNameId;
import aiki.beans.facade.fight.KeyHypothesis;
import aiki.beans.game.ImgPkPlayer;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.*;
import aiki.game.fight.util.MoveTarget;
import aiki.util.*;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.MessagesFightFight;
import code.scripts.pages.aiki.MessagesFightTeam;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class FightCalculationBean extends CommonFightBean {
    private DictionaryComparator<TrPkMoveTarget, TrPkMoveTarget> allyChoice;
    private DictionaryComparator<TrPkMoveTarget, TrPkMoveTarget> foeChoices;

    private IntTreeMap<BoolVal> foeChoicesTargets;
    private CustList<KeyHypothesis> damage;
    private StringList sortedFighters;
    private CustList<ImgMovesListTeamPositionsList> sortedFightersWildFight;

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade,_form);
        setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesFightFight.M_P_90_TITLE_DETAIL_FIGHT)));
        initPage();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,this), MessagesPkBean.TEAM, MessagesFightTeam.M_P_92_FIGHT);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHTDETAIL_HTML,this),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_REFRESH);
        feedParents();
        displayStringList(MessagesPkBean.FIGHT, getSortedFighters(), MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES);
        display(MessagesPkBean.FIGHT,getSortedFightersWildFight(),MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD);
        initPage();
        for (ImgMovesListTeamPositionsList i: getSortedFightersWildFight()) {
            initLine();
            paintMetaLabelDisk();
            initPage();
            display(MessagesPkBean.FIGHT,i.getKeyPks(),MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD_KEY);
            new BeanDisplayList<ImgPkPlayer>(new BeanDisplayImgPkPlayer()).display(this,i.getKeyPks());
            displayStringList(MessagesPkBean.FIGHT,i.getNamesPk(),MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD_VALUE);
            feedParents();
            feedParents();
        }
        feedParents();
        displayHead(getDamage(),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES, MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_KEY_ONE,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_KEY_TWO,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_KEY_THREE,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_DAMAGE);
        for(KeyHypothesis k:getDamage()) {
            formatMessageDirCts(k.getPlayerPokemon());
            formatMessageDirCts(k.getMove());
            String message_ = targetPk(k);
            formatMessageDirCts(message_);
            formatMessageDirCts(k.getDamage().toNumberString()+" - "+k.getDamageSecond().toNumberString());
        }
        feedParents();
        displayHead(getAllyChoice(),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_ALLY_CHOICES, MessagesFightFight.M_P_90_ALLY_CHOICES_KEY, MessagesFightFight.M_P_90_ALLY_CHOICES_KEY_TEAM, MessagesFightFight.M_P_90_ALLY_CHOICES_KEY_PLACE, MessagesFightFight.M_P_90_ALLY_CHOICES_KEY_NAME, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE_TEAM, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE_PLACE, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE_NAME);
        for(EntryCust<TrPkMoveTarget, TrPkMoveTarget> e:getAllyChoice().entryList()) {
            displayTrPkMoveTarget(MessagesPkBean.FIGHT,e.getKey());
            displayTrPkMoveTarget(MessagesPkBean.FIGHT,e.getValue());
        }
        feedParents();
        displayHead(getFoeChoices(),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES, MessagesFightFight.M_P_90_FOE_CHOICES_KEY, MessagesFightFight.M_P_90_FOE_CHOICES_KEY_NAME, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE_TEAM, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE_PLACE, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE_NAME);
        int len_ = getFoeChoices().size();
        for (int i = 0; i < len_; i++) {
            formatMessageDirCts(Long.toString(getFoeChoices().getKey(i).getIndex()));
            formatMessageDirCts(getFoeChoices().getKey(i).getTranslation());
            formatMessageDirCts(getFoeChoices().getValue(i).getMoveTarget().getMove());
            if (isChosenTarget(i)) {
                if (getFoeChoices().getValue(i).getMoveTarget().getTarget().getTeam() == Fight.CST_FOE) {
                    formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_FOE);
                } else {
                    formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_PLAYER);
                }
                formatMessageDirCts(Long.toString(getFoeChoices().getValue(i).getMoveTarget().getTarget().getPosition()));
                formatMessageDirCts(getFoeChoices().getValue(i).getTranslation());
            } else {
                formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
                formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
                formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
            }
        }
        feedParents();
    }

    private String targetPk(KeyHypothesis _k) {
        String message_;
        if (_k.isBelongsToUser()) {
            message_ = formatMessageRend(MessagesPkBean.FIGHT, MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_PLAYER);
        } else {
            message_ = formatMessageRend(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_FOE);
        }
        message_+="\u00A0";
        message_+= _k.getTargetPokemon();
        return message_;
    }

    public StringMap<String> file() {
        return filesFight().getVal(MessagesPkBean.FIGHT).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        FacadeGame dataBaseFight_ = facade();
        damageInit(dataBaseFight_);
        sortedFighters = new StringList();
        TeamPositionList tl_;
        if (!dataBaseFight_.getGame().getFight().getFightType().isWild()) {
            tl_ = dataBaseFight_.sortedFightersBeginRound();
        } else {
            tl_ = new TeamPositionList();
        }
        for (TeamPosition t:tl_) {
            sortedFighters.add(getFighterAtPosition(dataBaseFight_, t));
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
        DictionaryComparator<TrPkMoveTarget, TrPkMoveTarget> allyChoice_ = DictionaryComparatorUtil.buildMoveTarget();
        for (EntryCust<MoveTarget, MoveTarget> m: fight_.getAllyChoice().entryList()) {
            MoveTarget key_ = build(translationsMoves_, m.getKey());
            MoveTarget value_ = build(translationsMoves_, m.getValue());
            allyChoice_.put(new TrPkMoveTarget(key_,trPk(key_)), new TrPkMoveTarget(value_,trPk(value_)));
        }
        allyChoice = allyChoice_;
        DictionaryComparator<TrPkMoveTarget, TrPkMoveTarget> foeChoices_;
        foeChoices_ = DictionaryComparatorUtil.buildMoveTarget();
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
            MoveTarget key_ = new MoveTarget(DataBase.EMPTY_STRING, TargetCoords.toFoeTarget(k));
            foeChoices_.put(new TrPkMoveTarget(key_,trPk(key_),k), new TrPkMoveTarget(value_,trPk(value_)));
            foeChoicesTargets_.put(k, ComparatorBoolean.of(!f_.getChosenTargets().isEmpty()));
        }
        foeChoices = foeChoices_;
        foeChoicesTargets = foeChoicesTargets_;
    }

    private MoveTarget build(StringMap<String> _tr, MoveTarget _mt) {
        String move_ = tr(_tr, _mt);
        return new MoveTarget(move_, _mt.getTarget());
    }

    private String tr(StringMap<String> _tr, MoveTarget _mt) {
        String move_ = _mt.getMove();
        if (!move_.isEmpty()) {
            move_ = _tr.getVal(move_);
        }
        return move_;
    }

    private String trPk(MoveTarget _e) {
        FacadeGame dataBaseFight_ = facade();
        Fight fight_ = dataBaseFight_.getGame().getFight();
        String tr_;
        if (_e.getTarget().getPosition() != Fighter.BACK) {
            Fighter fighter_ = fight_.getFighter(fight_.getFighterKey(_e.getTarget()));
            tr_ = dataBaseFight_.translatePokemon(fighter_.getName());
        } else {
            tr_ = DataBase.EMPTY_STRING;
        }
        return tr_;
    }

    private CustList<ImgMovesListTeamPositionsList> convert() {
        FacadeGame dataBaseFight_ = facade();
        CustList<ImgMovesListTeamPositionsList> t_ = new CustList<ImgMovesListTeamPositionsList>();
        for (MovesListTeamPositionsList e: dataBaseFight_.sortedFightersBeginRoundWildFight()) {
            CustList<ImgPkPlayer> keys_ = new CustList<ImgPkPlayer>();
            for (FighterNamePkNameMv k:e.getKeyPks()) {
//                ImgPkPlayer cp_ = new ImgPkPlayer();
//                cp_.setNamePk(k.getNamePk());
//                cp_.setImagePk(dataBaseFight_.getData().getMaxiPkFront().getVal(k.getNamePk()).getImage());
//                cp_.setNameMv(k.getNameMv());
//                cp_.setNameMvTr(k.getNameMvTr());
//                cp_.setNumber(k.getNumber());
                keys_.add(new ImgPkPlayer(k.getNameMv(),k.getNameMvTr(),dataBaseFight_.getData().getMaxiPkFront().getVal(k.getNamePk()).getImage()));
            }
            ImgMovesListTeamPositionsList elt_ = new ImgMovesListTeamPositionsList(keys_, e.getTeamPositions());
            for (TeamPosition t:elt_.getTeamPositions()) {
                elt_.getNamesPk().add(getFighterAtPosition(dataBaseFight_, t));
            }
            t_.add(elt_);
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
        return sortedFightersWildFight.get(_indexOne).getNamesPk().get(_indexTwo);
    }
    public String getFighter(int _index) {
        return sortedFighters.get(_index);
    }
    public boolean isFoeTargetChoiceTeam(int _index) {
        return allyChoice.getKey(_index).getMoveTarget().getTarget().getTeam() == Fight.CST_FOE;
    }
    public boolean isFoeTargetTeam(int _index) {
        return allyChoice.getValue(_index).getMoveTarget().getTarget().getTeam() == Fight.CST_FOE;
    }
    public boolean isBackTargetChoiceTeam(int _index) {
        return allyChoice.getKey(_index).getMoveTarget().getTarget().getPosition() == Fighter.BACK;
    }
    public boolean isBackTargetTeam(int _index) {
        return allyChoice.getValue(_index).getMoveTarget().getTarget().getPosition() == Fighter.BACK;
    }
    public String getTargetNameAllyChoiceCondition(int _index) {
        return allyChoice.getKey(_index).getTranslation();
    }
    public String getTargetNameAllyChoice(int _index) {
        return allyChoice.getValue(_index).getTranslation();
    }
    public boolean isChosenTarget(int _index) {
        return foeChoicesTargets.getValue(_index) == BoolVal.TRUE;
    }
    public String getTargetNameFoeChoice(int _index) {
        return foeChoices.getValue(_index).getTranslation();
    }
    public String getFoeFighterName(int _index) {
        return foeChoices.getKey(_index).getTranslation();
    }
    public boolean isFoeTargetChTeam(int _index) {
        return foeChoices.getValue(_index).getMoveTarget().getTarget().getTeam() == Fight.CST_FOE;
    }

    public StringList getSortedFighters() {
        return sortedFighters;
    }

    public CustList<ImgMovesListTeamPositionsList> getSortedFightersWildFight() {
        return sortedFightersWildFight;
    }

    public CustList<KeyHypothesis> getDamage() {
        return damage;
    }

    public DictionaryComparator<TrPkMoveTarget,TrPkMoveTarget> getAllyChoice() {
        return allyChoice;
    }

    public DictionaryComparator<TrPkMoveTarget,TrPkMoveTarget> getFoeChoices() {
        return foeChoices;
    }
}