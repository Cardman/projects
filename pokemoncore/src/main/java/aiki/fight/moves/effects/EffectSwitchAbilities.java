package aiki.fight.moves.effects;

import aiki.DataBase;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.fight.moves.enums.TargetChoice;


public final class EffectSwitchAbilities extends Effect {

    private ExchangeType exchangeAbility;
    private String constAbility;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (exchangeAbility == null) {
            _data.setError(true);
            return;

        }
        if (constAbility == null) {
            _data.setError(true);
            return;

        }
        if (!constAbility.isEmpty()) {
            if (!_data.getAbilities().contains(constAbility)) {
                _data.setError(true);
                return;

            }
        }
        if (exchangeAbility == ExchangeType.GIVE_TO_TARGET
                || exchangeAbility == ExchangeType.GIVE_TO_THROWER
                || exchangeAbility == ExchangeType.EXCHANGE) {
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                _data.setError(true);
                return;

            }
            return;
        }
        if (exchangeAbility == ExchangeType.GIVE_CONST) {
            if (!constAbility.isEmpty()) {
                if (!_data.getAbilities().contains(constAbility)) {
                    _data.setError(true);
                    return;

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
