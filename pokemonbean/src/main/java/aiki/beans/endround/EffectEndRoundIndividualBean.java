package aiki.beans.endround;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.effects.EffectEndRoundIndividual;

public class EffectEndRoundIndividualBean extends EffectEndRoundBean {

    @Accessible
    private Rate deleteAllStatus;

    @Accessible
    private Rate recoilDamage;

    @Accessible
    private Rate healHp;

    @Accessible
    private TreeMap<String,Rate> healHpByOwnerTypes;

    @Accessible
    private TreeMap<String,Rate> multDamageStatus;

    @Accessible
    private String userStatusEndRound;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundIndividual effect_ = (EffectEndRoundIndividual) getEffect();
        deleteAllStatus = effect_.getDeleteAllStatus();
        recoilDamage = effect_.getRecoilDamage();
        healHp = effect_.getHealHp();
        userStatusEndRound = effect_.getUserStatusEndRound();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedTypes_;
        translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        TreeMap<String,Rate> multDamageStatus_;
        multDamageStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        for (String s: effect_.getMultDamageStatus().getKeys()) {
            multDamageStatus_.put(s, effect_.getMultDamageStatus().getVal(s));
        }
        multDamageStatus = multDamageStatus_;
        TreeMap<String,Rate> healHpByOwnerTypes_;
        healHpByOwnerTypes_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedTypes_));
        for (String s: effect_.getHealHpByOwnerTypes().getKeys()) {
            healHpByOwnerTypes_.put(s, effect_.getHealHpByOwnerTypes().getVal(s));
        }
        healHpByOwnerTypes = healHpByOwnerTypes_;
    }

    @Accessible
    private String getTrUserStatus() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(userStatusEndRound);
    }

    @Accessible
    private String clickUserStatus(Long _index) {
        EffectEndRoundIndividual effect_ = (EffectEndRoundIndividual) getEffect(_index);
        getForms().put(STATUS, effect_.getUserStatusEndRound());
        return STATUS;
    }

    @Accessible
    private String getTrDamageStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(multDamageStatus.getKey(_index.intValue()));
    }

    @Accessible
    private String clickDamageStatus(Long _indexOne,Long _indexTwo) {
        EffectEndRoundIndividual effect_ = (EffectEndRoundIndividual) getEffect(_indexOne);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        TreeMap<String,Rate> multDamageStatus_;
        multDamageStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        for (String s: effect_.getMultDamageStatus().getKeys()) {
            multDamageStatus_.put(s, effect_.getMultDamageStatus().getVal(s));
        }
        getForms().put(STATUS, multDamageStatus_.getKey(_indexTwo.intValue()));
        return STATUS;
    }

    @Accessible
    private boolean isType(Long _index) {
        return !healHpByOwnerTypes.getKey(_index.intValue()).isEmpty();
    }

    @Accessible
    private String getTrType(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_;
        translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(healHpByOwnerTypes.getKey(_index.intValue()));
    }
}
