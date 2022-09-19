package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;


public final class EffectSwitchAbilities extends Effect {

    private ExchangeType exchangeAbility;
    private String constAbility;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getAbilities().getKeys(),constAbility,_data);
        if (exchangeAbility == ExchangeType.GIVE_TO_TARGET
                || exchangeAbility == ExchangeType.GIVE_TO_THROWER
                || exchangeAbility == ExchangeType.EXCHANGE) {
            DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
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
