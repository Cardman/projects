package aiki.fight.moves;

import aiki.db.DataBase;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;


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
        if (!repeatRoundLaw.checkEvents()) {
            _data.setError(true);
        }
        if (targetChoice == TargetChoice.NOTHING) {
            _data.setError(true);
        }
        if (nbPrepaRound < 0) {
            _data.setError(true);
        }
        if (pp <= 0) {
            _data.setError(true);
        }
        if (pp > _data.getMaxPp()) {
            _data.setError(true);
        }
        if (!_data.getMoves().containsAllAsKeys(achieveDisappearedPkUsingMove)) {
            _data.setError(true);
        }
        if (!_data.getTypes().containsAllObj(types) || types.isEmpty()) {
            _data.setError(true);
        }
        if (!_data.getTypes().containsAllObj(boostedTypes)) {
            _data.setError(true);
        }
        if (!_data.getStatus().containsAllAsKeys(deletedStatus)) {
            _data.setError(true);
        }
        if (!_data.getStatus().containsAllAsKeys(requiredStatus)) {
            _data.setError(true);
        }
        if (!typesByOwnedItem.isEmpty()) {
            if (!typesByOwnedItem.contains(DataBase.EMPTY_STRING)) {
                _data.setError(true);
            }
            CustList<String> keys_ = typesByOwnedItem.getKeys();
            StringList.removeObj(keys_, DataBase.EMPTY_STRING);
            if (!_data.getItems().containsAllAsKeys(keys_)) {
                _data.setError(true);
            }
            if (!_data.getTypes().containsAllObj(typesByOwnedItem.values())) {
                _data.setError(true);
            }
        }
        if (!secEffectsByItem.isEmpty()) {
            CustList<String> keys_ = secEffectsByItem.getKeys();
            StringList.removeObj(keys_, DataBase.EMPTY_STRING);
            if (!_data.getItems().containsAllAsKeys(keys_)) {
                _data.setError(true);
            }
            int index_ = indexOfPrimaryEffect();
            for (Ints e : secEffectsByItem.values()) {
                if (e.getMinimum(-2) <= index_) {
                    _data.setError(true);
                }
            }
        }
        if (!typesByWeather.isEmpty()) {
            if (!typesByWeather.contains(DataBase.EMPTY_STRING)) {
                _data.setError(true);
            }
            CustList<String> keys_ = typesByWeather.getKeys();
            StringList.removeObj(keys_, DataBase.EMPTY_STRING);
            if (!_data.getMovesEffectGlobalWeather().containsAllObj(keys_)) {
                _data.setError(true);
            }
            if (!_data.getTypes().containsAllObj(typesByWeather.values())) {
                _data.setError(true);
            }
        }
        if (accuracy.isEmpty()) {
            _data.setError(true);
        }
        if (!repeatRoundLaw.events().isEmpty()) {
            Rate min_ = repeatRoundLaw.minimum();
            if (!min_.isZeroOrGt()) {
                _data.setError(true);
            }
            if (min_.isZero()) {
                _data.setError(true);
            }
            for (Rate e : repeatRoundLaw.events()) {
                if (!e.isInteger()) {
                    _data.setError(true);
                }
            }
            if (rankIncrementNbRound <= 0) {
                _data.setError(true);
            }
        }
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
        if (nbGlobalEffects_ > DataBase.ONE_POSSIBLE_CHOICE) {
            _data.setError(true);
        }
        if (nbEndRoudEffects_ > DataBase.ONE_POSSIBLE_CHOICE) {
            _data.setError(true);
        }
        int indexOfPrimaryEffect_ = indexOfPrimaryEffect();
        if (indexOfPrimaryEffect_ == CustList.INDEX_NOT_FOUND_ELT) {
            _data.setError(true);
        }
        int nbEffects_ = nbEffets();
        for (int i = CustList.FIRST_INDEX; i < nbEffects_; i++) {
            Effect effect_ = effects.get(i);
            if (i <= indexOfPrimaryEffect_) {
                if (!effect_.getRequiredSuccessfulEffects().isEmpty()) {
                    _data.setError(true);
                }
            } else {
                if (!effect_.getRequiredSuccessfulEffects().isEmpty()) {
                    if (effect_.getRequiredSuccessfulEffects().getMaximum(nbEffects_) >= i) {
                        _data.setError(true);
                    }
                }
            }
            if (i == indexOfPrimaryEffect_) {
                continue;
            }
            if (effect_.getTargetChoice() != targetChoice) {
                if (effect_.getTargetChoice() != TargetChoice.LANCEUR) {
                    _data.setError(true);
                }
            }
        }
        for (int i = CustList.FIRST_INDEX; i < nbEffects_; i++) {
            if (i < indexOfPrimaryEffect_) {
                continue;
            }
            Effect effect_ = effects.get(i);
            if (effect_.getTargetChoice() == TargetChoice.LANCEUR) {
                continue;
            }
            for (int e : effect_.getRequiredSuccessfulEffects()) {
                if (e < 0) {
                    continue;
                }
                if (e >= effects.size()) {
                    continue;
                }
                if (effects.get(e).getTargetChoice() != targetChoice) {
                    _data.setError(true);
                }
            }
        }
    }

    public int nbEffets() {
        return effects.size();
    }

    public Effect getEffet(int _i) {
        return effects.get(_i);
    }

    public int indexOfPrimaryEffect() {
        int i_ = CustList.FIRST_INDEX;
        for (Effect e : effects) {
            if (e.getTargetChoice() == targetChoice) {
                return i_;
            }
            i_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    public abstract String getMoveType();

    public abstract boolean canBoostAllies();

    public abstract String getCategory();

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
