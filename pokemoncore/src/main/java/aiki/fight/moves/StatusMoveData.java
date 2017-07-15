package aiki.fight.moves;
import aiki.DataBase;
import aiki.exceptions.DataException;
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
import code.datacheck.CheckedData;
import code.util.annot.RwXml;


@CheckedData
@RwXml
public class StatusMoveData extends MoveData {
    public static final String MOVE = "dbpokemon.fight.moves.StatusMoveData";

    private boolean thievableMove;
    private boolean counterableMove;

    @Override
    public String getMoveType() {
        return MOVE;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (counterableMove) {
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                boolean switchin_ = true;
                for (Effect e: getEffects().mid(indexOfPrimaryEffect())) {
                    if (e instanceof EffectTeamWhileSendFoe) {
                        continue;
                    }
                    if (e instanceof EffectUnprotectFromTypes) {
                        continue;
                    }
                    if (e instanceof EffectTeam) {
                        if (((EffectTeam)e).getForbiddingHealing()) {
                            continue;
                        }
                        if (!((EffectTeam)e).getForbiddenBoost().isEmpty()) {
                            continue;
                        }
                        if (!((EffectTeam)e).getUnusableMoves().isEmpty()) {
                            continue;
                        }
                        if (!((EffectTeam)e).getMultStatisticFoe().isEmpty()) {
                            continue;
                        }
                        if (!((EffectTeam)e).getDisableFoeTeamEffects().isEmpty()) {
                            continue;
                        }
                    }
                    switchin_ = false;
                }
                if (switchin_) {
                    return;
                }
                throw new DataException();
            }
        }
        if (thievableMove) {
            if (getTargetChoice() != TargetChoice.LANCEUR) {
                if (getTargetChoice() != TargetChoice.ALLIES) {
                    if (getTargetChoice() != TargetChoice.TOUS_ADV) {
                        throw new DataException();
                    }
                }
            }
        }
        boolean invoke_ = false;
        for (Effect effect_: getEffects()) {
            if (effect_ instanceof EffectInvoke) {
                invoke_ = true;
                break;
            }
        }
        if (invoke_) {
            if (nbEffets() == 1) {
                return;
            }
            throw new DataException();
        }
        for (Effect effect_: getEffects()) {
            if (effect_ instanceof EffectSwitchPosition) {
                invoke_ = true;
                break;
            }
        }
        if (invoke_) {
            if (nbEffets() == 1) {
                return;
            }
            throw new DataException();
        }
        for (Effect effect_: getEffects()) {
            if (effect_ instanceof EffectOrder) {
                invoke_ = true;
                break;
            }
        }
        if (invoke_) {
            if (nbEffets() == 1) {
                return;
            }
            throw new DataException();
        }
        for (Effect effect_: getEffects()) {
            if (effect_ instanceof EffectProtection) {
                invoke_ = true;
                break;
            }
        }
        if (invoke_) {
            if (nbEffets() == 1) {
                return;
            }
            throw new DataException();
        }
        for (Effect effect_: getEffects()) {
            if (effect_ instanceof EffectBatonPass) {
                invoke_ = true;
                break;
            }
        }
        if (invoke_) {
            if (nbEffets() == 1) {
                return;
            }
            throw new DataException();
        }
        for (Effect effect_: getEffects()) {
            if (effect_ instanceof EffectCopyMove) {
                invoke_ = true;
                break;
            }
        }
        if (invoke_) {
            if (nbEffets() == 1) {
                return;
            }
            throw new DataException();
        }
        for (Effect effect_: getEffects()) {
            if (effect_ instanceof EffectSwitchPointView) {
                invoke_ = true;
                break;
            }
        }
        if (invoke_) {
            if (nbEffets() == 1) {
                return;
            }
            throw new DataException();
        }
        boolean endRound_ = false;
        for (Effect effect_: getEffects()) {
            if (effect_ instanceof EffectGlobal) {
                invoke_ = true;
            }
            if (effect_ instanceof EffectEndRoundGlobal) {
                endRound_ = true;
            }
        }
        if (invoke_) {
            if (endRound_) {
                if (nbEffets() == 2) {
                    return;
                }
                throw new DataException();
            }
            if (nbEffets() == 1) {
                return;
            }
            throw new DataException();
        }
        for (Effect effect_: getEffects()) {
            if (effect_ instanceof EffectSwitchPointView) {
                throw new DataException();
            }
            if (effect_ instanceof EffectOrder) {
                throw new DataException();
            }
            if (effect_ instanceof EffectCopyMove) {
                throw new DataException();
            }
            if (effect_ instanceof EffectSwitchPosition) {
                throw new DataException();
            }
            if (effect_ instanceof EffectGlobal) {
                throw new DataException();
            }
            if (effect_ instanceof EffectInvoke) {
                throw new DataException();
            }
            if (effect_ instanceof EffectProtection) {
                throw new DataException();
            }
            if (effect_ instanceof EffectBatonPass) {
                throw new DataException();
            }
            if (effect_ instanceof EffectDamage) {
                throw new DataException();
            }
            if (effect_ instanceof EffectDamageRate) {
                throw new DataException();
            }
        }
    }
    @Override
    public boolean canBoostAllies() {
        if (getTargetChoice() == TargetChoice.ALLIES) {
            return true;
        }
        if (getTargetChoice() != TargetChoice.TOUS_ADV) {
            return false;
        }
        return thievableMove;
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
