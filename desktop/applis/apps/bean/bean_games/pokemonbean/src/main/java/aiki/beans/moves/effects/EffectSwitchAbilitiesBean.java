package aiki.beans.moves.effects;
import aiki.beans.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import code.scripts.pages.aiki.MessagesDataEffswitchabilities;
import code.scripts.pages.aiki.MessagesPkBean;

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

    @Override
    public void buildSubEff() {
        procExchangeType(getExchangeAbility(),ExchangeType.GIVE_TO_TARGET, MessagesDataEffswitchabilities.M_P_60_GIVE_TO_TARGET);
        procExchangeType(getExchangeAbility(),ExchangeType.GIVE_TO_THROWER,MessagesDataEffswitchabilities.M_P_60_GIVE_TO_USER);
        procExchangeType(getExchangeAbility(),ExchangeType.EXCHANGE,MessagesDataEffswitchabilities.M_P_60_SWICTH_ABILITIES);
        if (giveConst()) {
//            formatMessageDir(getConstAbility());
            formatTrKey(getConstAbility(), MessagesPkBean.EFF_SWITCHABILITIES,MessagesDataEffswitchabilities.M_P_60_GIVE_CONST_EMPTY,MessagesDataEffswitchabilities.M_P_60_GIVE_CONST);
//            if (isDefAbility()) {
//                formatMessage(MessagesPkBean.EFF_SWITCHABILITIES,MessagesDataEffswitchabilities.M_P_60_GIVE_CONST);
//                formatMessageDir(getConstAbility());
//            } else {
//                formatMessage(MessagesPkBean.EFF_SWITCHABILITIES,MessagesDataEffswitchabilities.M_P_60_GIVE_CONST_EMPTY);
//            }
        }
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