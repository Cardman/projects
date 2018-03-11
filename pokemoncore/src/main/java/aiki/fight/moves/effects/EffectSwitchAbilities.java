package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.fight.moves.enums.TargetChoice;
import code.util.annot.RwXml;

@RwXml
public final class EffectSwitchAbilities extends Effect {

    private ExchangeType exchangeAbility;
    private String constAbility;
    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (exchangeAbility == null) {
            throw new DataException();
        }
        if (constAbility == null) {
            throw new DataException();
        }
        if (!constAbility.isEmpty()) {
            if (!_data.getAbilities().contains(constAbility)) {
                throw new DataException();
            }
        }
        if (exchangeAbility == ExchangeType.GIVE_TO_TARGET || exchangeAbility == ExchangeType.GIVE_TO_THROWER || exchangeAbility == ExchangeType.EXCHANGE) {
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                throw new DataException();
            }
            return;
        }
        if (exchangeAbility == ExchangeType.GIVE_CONST) {
            if (!constAbility.isEmpty()) {
                if (!_data.getAbilities().contains(constAbility)) {
                    throw new DataException();
                }
            }
        }
    }
    public ExchangeType getExchangeAbility() {
        return exchangeAbility;
    }
    public void setExchangeAbility(ExchangeType _exchangeAbility) {
        exchangeAbility = _exchangeAbility;
    }
    public String getConstAbility() {
        return constAbility;
    }
    public void setConstAbility(String _constAbility) {
        constAbility = _constAbility;
    }
}
