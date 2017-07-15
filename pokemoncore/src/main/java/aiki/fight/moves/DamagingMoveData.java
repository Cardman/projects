package aiki.fight.moves;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectBatonPass;
import aiki.fight.moves.effects.EffectCopyFighter;
import aiki.fight.moves.effects.EffectCopyMove;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectDamageRate;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectOrder;
import aiki.fight.moves.effects.EffectProtection;
import aiki.fight.moves.effects.EffectSwitchPointView;
import aiki.fight.moves.effects.EffectSwitchPosition;
import code.datacheck.CheckedData;
import code.util.CustList;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public class DamagingMoveData extends MoveData {
    public static final String MOVE = "dbpokemon.fight.moves.DamagingMoveData";

    private String category;

    private boolean direct;

    private boolean cannotKo;

    private boolean stoppableMoveKoSingle;

    @Override
    public String getMoveType() {
        return MOVE;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        int index_ = indexOfPrimaryEffect();
        if (!(getEffet(index_) instanceof EffectDamage)) {
            throw new DataException();
        }
        int nbDamages_ = CustList.SIZE_EMPTY;
        for (Effect effect_: getEffects()) {
            if (effect_ instanceof EffectDamage) {
                nbDamages_++;
            }
            if (effect_ instanceof EffectInvoke) {
                throw new DataException();
            }
            if (effect_ instanceof EffectSwitchPosition) {
                throw new DataException();
            }
            if (effect_ instanceof EffectOrder) {
                throw new DataException();
            }
            if (effect_ instanceof EffectProtection) {
                throw new DataException();
            }
            if (effect_ instanceof EffectBatonPass) {
                throw new DataException();
            }
            if (effect_ instanceof EffectSwitchPointView) {
                throw new DataException();
            }
            if (effect_ instanceof EffectCopyMove) {
                throw new DataException();
            }
            if (effect_ instanceof EffectCopyFighter) {
                throw new DataException();
            }
        }
        if (nbDamages_ != DataBase.ONE_POSSIBLE_CHOICE) {
            throw new DataException();
        }
        for (Effect effect_: getEffects()) {
            if (effect_ instanceof EffectDamage) {
                break;
            }
            if (effect_ instanceof EffectDamageRate) {
                throw new DataException();
            }
        }
        for (Effect e: getEffects()) {
            if (!(e instanceof EffectDamageRate)) {
                continue;
            }
            EffectDamageRate eff_ = (EffectDamageRate) e;
            if (!eff_.getRequiredSuccessfulEffects().containsObj(index_)) {
                throw new DataException();
            }
        }
//        if (category == null) {
//            throw new DataException();
//        }
    }

    @Override
    public boolean canBoostAllies() {
        return false;
    }

    @Override
    public String getCategory() {
        return category;
    }

    public void setCategory(String _category) {
        category = _category;
    }

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean _direct) {
        direct = _direct;
    }

    public boolean getCannotKo() {
        return cannotKo;
    }

    public void setCannotKo(boolean _cannotKo) {
        cannotKo = _cannotKo;
    }

    public boolean getStoppableMoveKoSingle() {
        return stoppableMoveKoSingle;
    }

    public void setStoppableMoveKoSingle(boolean _stoppableMoveKoSingle) {
        stoppableMoveKoSingle = _stoppableMoveKoSingle;
    }
}
