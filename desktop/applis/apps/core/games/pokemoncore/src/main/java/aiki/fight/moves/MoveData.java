package aiki.fight.moves;

import aiki.db.DataBase;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;


public abstract class MoveData {

    private short pp;
    private StringList types;
    private StringList boostedTypes;
    private byte priority;
    private String accuracy;
    private CustList<Effect> effects;
    private short nbPrepaRound;
    private boolean disappearBeforeUse;
    private MonteCarloNumber repeatRoundLaw;
    private short rankIncrementNbRound;
    private boolean rechargeRound;
    private boolean constUserChoice;
    private boolean stoppableMoveSolo;
    private boolean stoppableMoveMulti;
    private boolean stoppableMovePrio;
    private boolean secEffectIfNoDamage;
    private StringMap<Ints> secEffectsByItem;
    private boolean ignVarAccurUserNeg;
    private boolean ignVarEvasTargetPos;
    private StringList achieveDisappearedPkUsingMove;
    private SwitchType switchType;
    private StringMap<String> typesByOwnedItem;
    private StringMap<String> typesByWeather;
    private TargetChoice targetChoice;
    private StringList deletedStatus;
    private StringList requiredStatus;

    public void validate(DataBase _data) {
        DataInfoChecker.checkEvents(repeatRoundLaw,_data);
        DataInfoChecker.checkTargetNot(TargetChoice.NOTHING,targetChoice,_data);
        DataInfoChecker.checkPositiveOrZero(nbPrepaRound,_data);
        DataInfoChecker.checkPositive(pp,_data);
        DataInfoChecker.checkGreater(_data.getMaxPp(),pp,_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),achieveDisappearedPkUsingMove,_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),types,_data);
        DataInfoChecker.checkEmptyNotStringList(types,_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),boostedTypes,_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),deletedStatus,_data);
        DataInfoChecker.checkStringListContains(_data.getStatus().getKeys(),requiredStatus,_data);
        DataInfoChecker.checkIfNotEmptyListHasDef(typesByOwnedItem, _data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getItems().getKeys(),typesByOwnedItem.getKeys(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),typesByOwnedItem.values(),_data);
        int indexOfPrimaryEffect_ = indexOfPrimaryEffect();
        for (Ints e : secEffectsByItem.values()) {
            DataInfoChecker.checkLower(indexOfPrimaryEffect_+1L,e.getMinimum(-2),_data);
        }
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getItems().getKeys(),secEffectsByItem.getKeys(),_data);
        DataInfoChecker.checkIfNotEmptyListHasDef(typesByWeather, _data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getMovesEffectGlobalWeather(),typesByWeather.getKeys(),_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),typesByWeather.values(),_data);
        DataInfoChecker.checkEmptyNotString(accuracy,_data);
        if (!repeatRoundLaw.events().isEmpty()) {
            Rate min_ = repeatRoundLaw.minimum();
            DataInfoChecker.checkPositive(min_,_data);
            DataInfoChecker.checkIntegers(repeatRoundLaw.events(),_data);
            DataInfoChecker.checkPositive(rankIncrementNbRound,_data);
        }
        validateEffects(_data);
        DataInfoChecker.checkPositiveOrZero(indexOfPrimaryEffect_,_data);
        int nbEffects_ = nbEffets();
        for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
            Effect effect_ = effects.get(i);
            if (i <= indexOfPrimaryEffect_) {
                DataInfoChecker.checkEmptyInts(effect_.getRequiredSuccessfulEffects(),_data);
            } else if (!effect_.getRequiredSuccessfulEffects().isEmpty() && effect_.getRequiredSuccessfulEffects().getMaximum(nbEffects_) >= i) {
                _data.setError(true);
            }
            DataInfoChecker.checkTargets(targetChoice, TargetChoice.LANCEUR, effect_.getTargetChoice(), _data);
        }
        for (int i = IndexConstants.FIRST_INDEX; i < nbEffects_; i++) {
            if (i < indexOfPrimaryEffect_) {
                continue;
            }
            Effect effect_ = effects.get(i);
            if (effect_.getTargetChoice() != TargetChoice.LANCEUR) {
                targetChoiceRequired(_data, effect_);
            }
        }
    }

    private void targetChoiceRequired(DataBase _data, Effect _effect) {
        for (int e : _effect.getRequiredSuccessfulEffects()) {
            if (effects.isValidIndex(e)) {
                DataInfoChecker.checkTarget(targetChoice, effects.get(e).getTargetChoice(), _data);
            }
        }
    }

    private void validateEffects(DataBase _data) {
        int nbGlobalEffects_ = 0;
        int nbEndRoudEffects_ = 0;
        for (Effect e : effects) {
            e.validate(_data);
            if (e instanceof EffectGlobal) {
                nbGlobalEffects_++;
            }
            if (e instanceof EffectEndRound) {
                nbEndRoudEffects_++;
            }
        }
        DataInfoChecker.checkGreater(DataBase.ONE_POSSIBLE_CHOICE,nbGlobalEffects_,_data);
        DataInfoChecker.checkGreater(DataBase.ONE_POSSIBLE_CHOICE,nbEndRoudEffects_,_data);
    }

    public int nbEffets() {
        return effects.size();
    }

    public Effect getEffet(int _i) {
        return effects.get(_i);
    }

    public int indexOfPrimaryEffect() {
        int i_ = IndexConstants.FIRST_INDEX;
        for (Effect e : effects) {
            if (e.getTargetChoice() == targetChoice) {
                return i_;
            }
            i_++;
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public abstract String getMoveType();

    public abstract boolean canBoostAllies();

    public short getPp() {
        return pp;
    }

    public void setPp(short _pp) {
        pp = _pp;
    }

    public StringList getTypes() {
        return types;
    }

    public void setTypes(StringList _types) {
        types = _types;
    }

    public StringList getBoostedTypes() {
        return boostedTypes;
    }

    public void setBoostedTypes(StringList _boostedTypes) {
        boostedTypes = _boostedTypes;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte _priority) {
        priority = _priority;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String _accuracy) {
        accuracy = _accuracy;
    }

    public CustList<Effect> getEffects() {
        return effects;
    }

    public void setEffects(CustList<Effect> _effects) {
        effects = _effects;
    }

    public short getNbPrepaRound() {
        return nbPrepaRound;
    }

    public void setNbPrepaRound(short _nbPrepaRound) {
        nbPrepaRound = _nbPrepaRound;
    }

    public boolean getDisappearBeforeUse() {
        return disappearBeforeUse;
    }

    public void setDisappearBeforeUse(boolean _disappearBeforeUse) {
        disappearBeforeUse = _disappearBeforeUse;
    }

    public MonteCarloNumber getRepeatRoundLaw() {
        return repeatRoundLaw;
    }

    public void setRepeatRoundLaw(MonteCarloNumber _repeatRoundLaw) {
        repeatRoundLaw = _repeatRoundLaw;
    }

    public short getRankIncrementNbRound() {
        return rankIncrementNbRound;
    }

    public void setRankIncrementNbRound(short _rankIncrementNbRound) {
        rankIncrementNbRound = _rankIncrementNbRound;
    }

    public boolean getRechargeRound() {
        return rechargeRound;
    }

    public void setRechargeRound(boolean _rechargeRound) {
        rechargeRound = _rechargeRound;
    }

    public boolean getConstUserChoice() {
        return constUserChoice;
    }

    public void setConstUserChoice(boolean _constUserChoice) {
        constUserChoice = _constUserChoice;
    }

    public boolean getStoppableMoveSolo() {
        return stoppableMoveSolo;
    }

    public void setStoppableMoveSolo(boolean _stoppableMoveSolo) {
        stoppableMoveSolo = _stoppableMoveSolo;
    }

    public boolean getStoppableMoveMulti() {
        return stoppableMoveMulti;
    }

    public void setStoppableMoveMulti(boolean _stoppableMoveMulti) {
        stoppableMoveMulti = _stoppableMoveMulti;
    }

    public boolean getStoppableMovePrio() {
        return stoppableMovePrio;
    }

    public void setStoppableMovePrio(boolean _stoppableMovePrio) {
        stoppableMovePrio = _stoppableMovePrio;
    }

    public boolean getSecEffectIfNoDamage() {
        return secEffectIfNoDamage;
    }

    public void setSecEffectIfNoDamage(boolean _secEffectIfNoDamage) {
        secEffectIfNoDamage = _secEffectIfNoDamage;
    }

    public StringMap<Ints> getSecEffectsByItem() {
        return secEffectsByItem;
    }

    public void setSecEffectsByItem(
            StringMap<Ints> _secEffectsByItem) {
        secEffectsByItem = _secEffectsByItem;
    }

    public boolean getIgnVarAccurUserNeg() {
        return ignVarAccurUserNeg;
    }

    public void setIgnVarAccurUserNeg(boolean _ignVarAccurUserNeg) {
        ignVarAccurUserNeg = _ignVarAccurUserNeg;
    }

    public boolean getIgnVarEvasTargetPos() {
        return ignVarEvasTargetPos;
    }

    public void setIgnVarEvasTargetPos(boolean _ignVarEvasTargetPos) {
        ignVarEvasTargetPos = _ignVarEvasTargetPos;
    }

    public StringList getAchieveDisappearedPkUsingMove() {
        return achieveDisappearedPkUsingMove;
    }

    public void setAchieveDisappearedPkUsingMove(
            StringList _achieveDisappearedPkUsingMove) {
        achieveDisappearedPkUsingMove = _achieveDisappearedPkUsingMove;
    }

    public SwitchType getSwitchType() {
        return switchType;
    }

    public void setSwitchType(SwitchType _switchType) {
        switchType = _switchType;
    }

    public StringMap<String> getTypesByOwnedItem() {
        return typesByOwnedItem;
    }

    public void setTypesByOwnedItem(StringMap<String> _typesByOwnedItem) {
        typesByOwnedItem = _typesByOwnedItem;
    }

    public StringMap<String> getTypesByWeather() {
        return typesByWeather;
    }

    public void setTypesByWeather(StringMap<String> _typesByWeather) {
        typesByWeather = _typesByWeather;
    }

    public TargetChoice getTargetChoice() {
        return targetChoice;
    }

    public void setTargetChoice(TargetChoice _targetChoice) {
        targetChoice = _targetChoice;
    }

    public StringList getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(StringList _deletedStatus) {
        deletedStatus = _deletedStatus;
    }

    public StringList getRequiredStatus() {
        return requiredStatus;
    }

    public void setRequiredStatus(StringList _requiredStatus) {
        requiredStatus = _requiredStatus;
    }

}
