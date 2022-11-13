package aiki.beans.endround;

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
    private DictionaryComparator<String,Rate> multDamageStatus;
    private String userStatusEndRound;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundIndividual effect_ = (EffectEndRoundIndividual) getEffect();
        deleteAllStatus = effect_.getDeleteAllStatus();
        recoilDamage = effect_.getRecoilDamage();
        healHp = effect_.getHealHp();
        userStatusEndRound = effect_.getUserStatusEndRound();
        DataBase data_ = getDataBase();
        DictionaryComparator<String,Rate> multDamageStatus_;
        multDamageStatus_ = DictionaryComparatorUtil.buildStatusRate(data_,getLanguage());
        for (String s: effect_.getMultDamageStatus().getKeys()) {
            multDamageStatus_.put(s, effect_.getMultDamageStatus().getVal(s));
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(userStatusEndRound);
    }
    public String clickUserStatus(int _index) {
        EffectEndRoundIndividual effect_ = (EffectEndRoundIndividual) getEffect(_index);
        return tryRedirectSt(effect_.getUserStatusEndRound());
    }
    public String getTrDamageStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(multDamageStatus.getKey(_index));
    }
    public String clickDamageStatus(int _indexOne,int _indexTwo) {
        EffectEndRoundIndividual effect_ = (EffectEndRoundIndividual) getEffect(_indexOne);
        DataBase data_ = getDataBase();
        DictionaryComparator<String,Rate> multDamageStatus_;
        multDamageStatus_ = DictionaryComparatorUtil.buildStatusRate(data_,getLanguage());
        for (String s: effect_.getMultDamageStatus().getKeys()) {
            multDamageStatus_.put(s, effect_.getMultDamageStatus().getVal(s));
        }
        return tryRedirectSt(multDamageStatus_.getKey(_indexTwo));
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
        return userStatusEndRound;
    }

    public DictionaryComparator<String,Rate> getMultDamageStatus() {
        return multDamageStatus;
    }

    public DictionaryComparator<String,Rate> getHealHpByOwnerTypes() {
        return healHpByOwnerTypes;
    }
}