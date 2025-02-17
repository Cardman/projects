package aiki.beans.moves.effects;
import aiki.beans.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;

public class EffectSwitchAbilitiesBean extends EffectBean {

    private ExchangeType exchangeAbility;

    private TranslatedKey constAbility;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) getEffect();
        exchangeAbility = effect_.getExchangeAbility();
        constAbility = buildAb(getFacade(),effect_.getConstAbility());
    }
    public boolean giveToTarget() {
        return exchangeAbility == ExchangeType.GIVE_TO_TARGET;
    }
    public boolean giveToUser() {
        return exchangeAbility == ExchangeType.GIVE_TO_THROWER;
    }
    public boolean giveConst() {
        return exchangeAbility == ExchangeType.GIVE_CONST;
    }
    public boolean switchAbilities() {
        return exchangeAbility == ExchangeType.EXCHANGE;
    }
    public boolean isDefAbility() {
        return !constAbility.getKey().isEmpty();
    }
    public String getTrAbility() {
        return constAbility.getTranslation();
//        DataBase data_ = getDataBase();
//        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) getEffect(_index);
//        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        return translatedAbilities_.getVal(effect_.getConstAbility());
    }
    public String clickAbility(int _index) {
        return tryRedirect(((EffectSwitchAbilitiesBean)getForms().getCurrentBean().get(_index)).constAbility);
    }

    public TranslatedKey getConstAbility() {
        return constAbility;
    }

    public ExchangeType getExchangeAbility() {
        return exchangeAbility;
    }
}