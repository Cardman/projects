package aiki.fight.moves;

import aiki.db.DataBase;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectBatonPass;
import aiki.fight.moves.effects.EffectCopyMove;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectDamageRate;
import aiki.fight.moves.effects.EffectEndRoundGlobal;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectOrder;
import aiki.fight.moves.effects.EffectProtection;
import aiki.fight.moves.effects.EffectSwitchPointView;
import aiki.fight.moves.effects.EffectSwitchPosition;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.moves.effects.EffectUnprotectFromTypes;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
import code.util.core.NumberUtil;


public final class StatusMoveData extends MoveData {
    public static final String MOVE = "aiki.fight.moves.StatusMoveData";

    private boolean thievableMove;
    private boolean counterableMove;

    @Override
    public String getMoveType() {
        return MOVE;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        checkCounterable(_data);
        checkThievable(_data);
        if (hasSpecEffect()) {
            if (nbEffets() == 1) {
                return;
            }
            _data.setError(true);
        }
        if (invalidGlobal(_data)) {
            return;
        }
        checkEffects(_data);
    }

    private boolean invalidGlobal(DataBase _data) {
        boolean invoke_ = false;
        boolean endRound_ = false;
        for (Effect effect_ : getEffects()) {
            if (effect_ instanceof EffectGlobal) {
                invoke_ = true;
            }
            if (effect_ instanceof EffectEndRoundGlobal) {
                endRound_ = true;
            }
        }
        if (!invoke_) {
            return false;
        }
        if (endRound_) {
            if (nbEffets() == 2) {
                return true;
            }
            _data.setError(true);
        }
        if (nbEffets() == 1) {
            return true;
        }
        _data.setError(true);
        return false;
    }

    private boolean hasSpecEffect() {
        boolean invoke_ = hasEffectInvoke();
        if (invoke_) {
            return true;
        }
        invoke_ = hasEffectSwitchPosition();
        if (invoke_) {
            return true;
        }
        invoke_ = hasEffectOrder();
        if (invoke_) {
            return true;
        }
        invoke_ = hasEffectProtection();
        if (invoke_) {
            return true;
        }
        invoke_ = hasEffectBatonPass();
        if (invoke_) {
            return true;
        }
        invoke_ = hasEffectCopyMove();
        if (invoke_) {
            return true;
        }
        invoke_ = hasEffectSwitchPointView();
        return invoke_;
    }

    private boolean hasEffectSwitchPointView() {
        boolean invoke_;
        invoke_ = false;
        for (Effect effect_ : getEffects()) {
            if (effect_ instanceof EffectSwitchPointView) {
                invoke_ = true;
                break;
            }
        }
        return invoke_;
    }

    private boolean hasEffectCopyMove() {
        boolean invoke_;
        invoke_ = false;
        for (Effect effect_ : getEffects()) {
            if (effect_ instanceof EffectCopyMove) {
                invoke_ = true;
                break;
            }
        }
        return invoke_;
    }

    private boolean hasEffectBatonPass() {
        boolean invoke_;
        invoke_ = false;
        for (Effect effect_ : getEffects()) {
            if (effect_ instanceof EffectBatonPass) {
                invoke_ = true;
                break;
            }
        }
        return invoke_;
    }

    private boolean hasEffectProtection() {
        boolean invoke_;
        invoke_ = false;
        for (Effect effect_ : getEffects()) {
            if (effect_ instanceof EffectProtection) {
                invoke_ = true;
                break;
            }
        }
        return invoke_;
    }

    private boolean hasEffectOrder() {
        boolean invoke_;
        invoke_ = false;
        for (Effect effect_ : getEffects()) {
            if (effect_ instanceof EffectOrder) {
                invoke_ = true;
                break;
            }
        }
        return invoke_;
    }

    private boolean hasEffectSwitchPosition() {
        boolean invoke_;
        invoke_ = false;
        for (Effect effect_ : getEffects()) {
            if (effect_ instanceof EffectSwitchPosition) {
                invoke_ = true;
                break;
            }
        }
        return invoke_;
    }

    private boolean hasEffectInvoke() {
        boolean invoke_ = false;
        for (Effect effect_ : getEffects()) {
            if (effect_ instanceof EffectInvoke) {
                invoke_ = true;
                break;
            }
        }
        return invoke_;
    }

    private void checkThievable(DataBase _data) {
        if (thievableMove) {
            DataInfoChecker.checkTargets(TargetChoice.LANCEUR,TargetChoice.ALLIES,TargetChoice.TOUS_ADV,getTargetChoice(), _data);
        }
    }

    private void checkCounterable(DataBase _data) {
        if (counterableMove && getTargetChoice() == TargetChoice.LANCEUR) {
            boolean switchin_ = true;
            int ind_ = NumberUtil.max(0, indexOfPrimaryEffect());
            for (Effect e : getEffects().mid(ind_)) {
                if (!(e instanceof EffectTeamWhileSendFoe) && !(e instanceof EffectUnprotectFromTypes) && (!(e instanceof EffectTeam) || !okEffectTeam((EffectTeam) e))) {
                    switchin_ = false;
                }
            }
            if (!switchin_) {
                _data.setError(true);
            }
        }
    }

    private boolean okEffectTeam(EffectTeam _e) {
        boolean ok_ = _e.getForbiddingHealing();
        if (!_e.getForbiddenBoost().isEmpty()) {
            ok_ = true;
        }
        if (!_e.getUnusableMoves().isEmpty()) {
            ok_ = true;
        }
        if (!_e.getMultStatisticFoe().isEmpty()) {
            ok_ = true;
        }
        if (!_e.getDisableFoeTeamEffects()
                .isEmpty()) {
            ok_ = true;
        }
        return ok_;
    }

    private void checkEffects(DataBase _data) {
        for (Effect effect_ : getEffects()) {
            checkEffect(_data, effect_);
        }
    }

    private void checkEffect(DataBase _data, Effect _effect) {
        if (_effect instanceof EffectSwitchPointView) {
            _data.setError(true);
        }
        if (_effect instanceof EffectOrder) {
            _data.setError(true);
        }
        if (_effect instanceof EffectCopyMove) {
            _data.setError(true);
        }
        if (_effect instanceof EffectSwitchPosition) {
            _data.setError(true);
        }
        if (_effect instanceof EffectGlobal) {
            _data.setError(true);
        }
        if (_effect instanceof EffectInvoke) {
            _data.setError(true);
        }
        if (_effect instanceof EffectProtection) {
            _data.setError(true);
        }
        if (_effect instanceof EffectBatonPass) {
            _data.setError(true);
        }
        if (_effect instanceof EffectDamage) {
            _data.setError(true);
        }
        if (_effect instanceof EffectDamageRate) {
            _data.setError(true);
        }
    }

    @Override
    public boolean canBoostAllies() {
        if (getTargetChoice() == TargetChoice.ALLIES) {
            return true;
        }
        return getTargetChoice() == TargetChoice.TOUS_ADV;
    }

    @Override
    public String getCategory() {
        return DataBase.AUTRE;
    }

    public boolean getThievableMove() {
        return thievableMove;
    }

    public void setThievableMove(boolean _thievableMove) {
        thievableMove = _thievableMove;
    }

    public boolean getCounterableMove() {
        return counterableMove;
    }

    public void setCounterableMove(boolean _counterableMove) {
        counterableMove = _counterableMove;
    }

}
