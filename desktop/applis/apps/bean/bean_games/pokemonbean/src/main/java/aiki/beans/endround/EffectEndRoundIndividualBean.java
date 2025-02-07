package aiki.beans.endround;

import aiki.beans.TranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import code.maths.Rate;
import code.util.StringMap;

public class EffectEndRoundIndividualBean extends EffectEndRoundBean {
    private Rate deleteAllStatus;
    private Rate recoilDamage;
    private Rate healHp;
    private DictionaryComparator<String,Rate> healHpByOwnerTypes;
    private DictionaryComparator<TranslatedKey,Rate> multDamageStatus;
    private TranslatedKey userStatusEndRound;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundIndividual effect_ = (EffectEndRoundIndividual) getEffect();
        deleteAllStatus = effect_.getDeleteAllStatus();
        recoilDamage = effect_.getRecoilDamage();
        healHp = effect_.getHealHp();
        DataBase data_ = getDataBase();
        userStatusEndRound = buildSt(data_.getTranslatedStatus().getVal(getLanguage()),effect_.getUserStatusEndRound());
        DictionaryComparator<TranslatedKey,Rate> multDamageStatus_;
        multDamageStatus_ = DictionaryComparatorUtil.buildStatusRate();
        for (String s: effect_.getMultDamageStatus().getKeys()) {
            multDamageStatus_.put(buildSt(data_.getTranslatedStatus().getVal(getLanguage()),s), effect_.getMultDamageStatus().getVal(s));
        }
        multDamageStatus = multDamageStatus_;
        DictionaryComparator<String,Rate> healHpByOwnerTypes_;
        healHpByOwnerTypes_ = DictionaryComparatorUtil.buildTypesRate(data_,getLanguage());
        for (String s: effect_.getHealHpByOwnerTypes().getKeys()) {
            healHpByOwnerTypes_.put(s, effect_.getHealHpByOwnerTypes().getVal(s));
        }
        healHpByOwnerTypes = healHpByOwnerTypes_;
    }
    public String getTrUserStatus() {
        return userStatusEndRound.getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_;
//        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translatedStatus_.getVal(userStatusEndRound);
    }
    public String clickUserStatus(int _index) {
        return tryRedirect(((EffectEndRoundIndividualBean)getForms().getCurrentBeanEnd().get(_index)).userStatusEndRound);
    }
    public String getTrDamageStatus(int _index) {
        return multDamageStatus.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_;
//        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translatedStatus_.getVal(multDamageStatus.getKey(_index));
    }
    public String clickDamageStatus(int _indexOne,int _indexTwo) {
        return tryRedirect(((EffectEndRoundIndividualBean)getForms().getCurrentBeanEnd().get(_indexOne)).multDamageStatus.getKey(_indexTwo));
    }
    public boolean isType(int _index) {
        return !healHpByOwnerTypes.getKey(_index).isEmpty();
    }
    public String getTrType(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_;
        translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(healHpByOwnerTypes.getKey(_index));
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

    public DictionaryComparator<String,Rate> getHealHpByOwnerTypes() {
        return healHpByOwnerTypes;
    }
}