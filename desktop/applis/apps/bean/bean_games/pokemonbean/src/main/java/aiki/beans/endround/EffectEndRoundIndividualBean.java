package aiki.beans.endround;

import aiki.beans.*;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import code.maths.Rate;
import code.scripts.pages.aiki.*;

public class EffectEndRoundIndividualBean extends EffectEndRoundBean {
    private Rate deleteAllStatus;
    private Rate recoilDamage;
    private Rate healHp;
    private DictionaryComparator<TranslatedKey,Rate> healHpByOwnerTypes;
    private DictionaryComparator<TranslatedKey,Rate> multDamageStatus;
    private TranslatedKey userStatusEndRound;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundIndividual effect_ = (EffectEndRoundIndividual) getEffect();
        deleteAllStatus = effect_.getDeleteAllStatus();
        recoilDamage = effect_.getRecoilDamage();
        healHp = effect_.getHealHp();
        userStatusEndRound = buildSt(getFacade(),effect_.getUserStatusEndRound());
        DictionaryComparator<TranslatedKey,Rate> multDamageStatus_;
        multDamageStatus_ = DictionaryComparatorUtil.buildStatusRate();
        for (String s: effect_.getMultDamageStatus().getKeys()) {
            multDamageStatus_.put(buildSt(getFacade(),s), effect_.getMultDamageStatus().getVal(s));
        }
        multDamageStatus = multDamageStatus_;
        DictionaryComparator<TranslatedKey,Rate> healHpByOwnerTypes_;
        healHpByOwnerTypes_ = DictionaryComparatorUtil.buildTypesRate();
        for (String s: effect_.getHealHpByOwnerTypes().getKeys()) {
            healHpByOwnerTypes_.put(buildTy(getFacade(),s), effect_.getHealHpByOwnerTypes().getVal(s));
        }
        healHpByOwnerTypes = healHpByOwnerTypes_;
    }

    @Override
    public void buildSub() {
        super.buildSub();
        formatMessage(MessagesPkBean.ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.M_P_6_EFFECT);
        displayIntDef(deleteAllStatus,MessagesPkBean.ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.M_P_6_DELETE_ALL_STATUS);
        displayIntDef(recoilDamage,MessagesPkBean.ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.M_P_6_RECOIL_DAMAGE);
        displayIntDef(healHp,MessagesPkBean.ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.M_P_6_HEAL_HP);
        formatTrKey(userStatusEndRound,MessagesPkBean.ENDROUND_INDIVIDUAL,"",MessagesDataEndroundIndividual.M_P_6_USER_STATUS);
        new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this,multDamageStatus,MessagesPkBean.ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.M_P_6_MULT_DAMAGE_STATUS,MessagesDataEndroundIndividual.M_P_6_MULT_DAMAGE_STATUS_KEY,MessagesDataEndroundIndividual.M_P_6_MULT_DAMAGE_STATUS_VALUE);
        new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(MessagesPkBean.ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.M_P_6_HEAL_HP_BY_OWNER_TYPES_OTHER),new BeanDisplayRateAbs(MessagesPkBean.ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_L,MessagesDataEndroundIndividual.M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_W)).displayGrid(this,healHpByOwnerTypes,MessagesPkBean.ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.M_P_6_HEAL_HP_BY_OWNER_TYPES,MessagesDataEndroundIndividual.M_P_6_HEAL_HP_BY_OWNER_TYPES_KEY,MessagesDataEndroundIndividual.M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE);
    }
    public String getTrUserStatus() {
        return userStatusEndRound.getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_;
//        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translatedStatus_.getVal(userStatusEndRound);
    }
    public String clickUserStatus() {
        return tryRedirect(userStatusEndRound);
    }
    public String getTrDamageStatus(int _index) {
        return multDamageStatus.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_;
//        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translatedStatus_.getVal(multDamageStatus.getKey(_index));
    }
    public String clickDamageStatus(int _indexTwo) {
        return tryRedirect(multDamageStatus.getKey(_indexTwo));
    }
    public boolean isType(int _index) {
        return !healHpByOwnerTypes.getKey(_index).getKey().isEmpty();
    }
    public String getTrType(int _index) {
        return healHpByOwnerTypes.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_;
//        translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translatedTypes_.getVal(healHpByOwnerTypes.getKey(_index));
    }

    public Rate getDeleteAllStatus() {
        return deleteAllStatus;
    }

    public Rate getRecoilDamage() {
        return recoilDamage;
    }

    public Rate getHealHp() {
        return healHp;
    }

    public String getUserStatusEndRound() {
        return userStatusEndRound.getKey();
    }

    public DictionaryComparator<TranslatedKey,Rate> getMultDamageStatus() {
        return multDamageStatus;
    }

    public DictionaryComparator<TranslatedKey,Rate> getHealHpByOwnerTypes() {
        return healHpByOwnerTypes;
    }
}