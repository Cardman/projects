package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.util.StringMap;
import aiki.DataBase;
import aiki.fight.moves.effects.EffectSwitchAbilities;
import aiki.fight.moves.effects.enums.ExchangeType;

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

    @Accessible
    private boolean giveToTarget() {
        return exchangeAbility == ExchangeType.GIVE_TO_TARGET;
    }

    @Accessible
    private boolean giveToUser() {
        return exchangeAbility == ExchangeType.GIVE_TO_THROWER;
    }

    @Accessible
    private boolean giveConst() {
        return exchangeAbility == ExchangeType.GIVE_CONST;
    }

    @Accessible
    private boolean switchAbilities() {
        return exchangeAbility == ExchangeType.EXCHANGE;
    }

    @Accessible
    private boolean isDefAbility() {
        return !constAbility.isEmpty();
    }

    @Accessible
    private String getTrAbility(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) getEffect(_index);
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(effect_.getConstAbility());
    }

    @Accessible
    private String clickAbility(Long _index) {
        EffectSwitchAbilities effect_ = (EffectSwitchAbilities) getEffect(_index.intValue());
        getForms().put(ABILITY, effect_.getConstAbility());
        return ABILITY;
    }
}
