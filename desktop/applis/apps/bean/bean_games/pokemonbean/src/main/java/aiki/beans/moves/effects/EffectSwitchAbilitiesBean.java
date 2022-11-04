package aiki.beans.moves.effects;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectSwitchAbilities;
import aiki.fight.moves.effects.enums.ExchangeType;
import code.util.StringMap;

public class EffectSwitchAbilitiesBean extends EffectBean {

    private ExchangeType exchangeAbility;

    private String constAbility;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) getEffect();
        exchangeAbility = effect_.getExchangeAbility();
        constAbility = effect_.getConstAbility();
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
        return !constAbility.isEmpty();
    }
    public String getTrAbility(int _index) {
        DataBase data_ = getDataBase();
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) getEffect(_index);
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(effect_.getConstAbility());
    }
    public String clickAbility(int _index) {
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) getEffect(_index);
        return tryRedirectAb(effect_.getConstAbility());
    }
}