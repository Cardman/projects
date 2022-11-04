package aiki.beans.moves.effects;

import aiki.beans.facade.comparators.ComparatorTypesDuo;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectUnprotectFromTypes;
import aiki.fight.util.TypesDuo;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public class EffectUnprotectFromTypesBean extends EffectBean {
    private CustList<TypesDuo> types;
    private StringList disableImmuAgainstTypes;
    private StringList disableImmuFromMoves;
    private StringList attackTargetWithTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectUnprotectFromTypes effect_ = (EffectUnprotectFromTypes) getEffect();
        DataBase data_ = getDataBase();
        CustList<TypesDuo> types_;
        types_ = new CustList<TypesDuo>();
        for (TypesDuo duo_: effect_.getTypes()) {
            types_.add(duo_);
        }
//        types_.sort(new NaturalComparator<TypesDuo>() {
//            @Override
//            public int compare(TypesDuo _arg0, TypesDuo _arg1) {
//                DataBase dataCmp_ = (DataBase) getDataBase();
//                Map<String,String> translatedTypesCmp_ = dataCmp_.getTranslatedTypes().getVal(getLanguage());
//                int res_ = ComparatorTrString.compare(translatedTypesCmp_, _arg0.getDamageType(), _arg1.getDamageType());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return ComparatorTrString.compare(translatedTypesCmp_, _arg0.getPokemonType(), _arg1.getPokemonType());
//            }
//        });
        types_.sortElts(new ComparatorTypesDuo(data_, getLanguage(), false,false));
        types = types_;
        StringList disableImmuAgainstTypes_;
        disableImmuAgainstTypes_ = new StringList();
        for (String type_: effect_.getDisableImmuAgainstTypes()) {
            disableImmuAgainstTypes_.add(type_);
        }
        disableImmuAgainstTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        disableImmuAgainstTypes = disableImmuAgainstTypes_;
        disableImmuFromMoves = getDisableImmuFromMoves(effect_);
        StringList attackTargetWithTypes_;
        attackTargetWithTypes_ = new StringList();
        for (String type_: effect_.getAttackTargetWithTypes()) {
            attackTargetWithTypes_.add(type_);
        }
        attackTargetWithTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        attackTargetWithTypes = attackTargetWithTypes_;
    }
    public String getTrDamageType(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = types.get(_index).getDamageType();
        return translatedTypes_.getVal(type_);
    }
    public String getTrPokemonType(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = types.get(_index).getPokemonType();
        return translatedTypes_.getVal(type_);

    }
    public String getTrDisableImmuType(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = disableImmuAgainstTypes.get(_index);
        return translatedTypes_.getVal(type_);

    }
    public String clickMove(int _indexEffect, int _index) {
        String type_ = getDisableImmuFromMoves(_indexEffect).get(_index);
        return tryRedirectMv(type_);
    }
    public String getTrDisableImmuMove(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String type_ = disableImmuFromMoves.get(_index);
        return translatedMoves_.getVal(type_);
    }

    private StringList getDisableImmuFromMoves(int _index) {
        EffectUnprotectFromTypes effect_ = (EffectUnprotectFromTypes) getEffect(_index);
        return getDisableImmuFromMoves(effect_);
    }

    private StringList getDisableImmuFromMoves(EffectUnprotectFromTypes _effect) {
        DataBase data_ = getDataBase();
        StringList disableImmuFromMoves_;
        disableImmuFromMoves_ = new StringList();
        for (String type_: _effect.getDisableImmuFromMoves()) {
            disableImmuFromMoves_.add(type_);
        }
        disableImmuFromMoves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return disableImmuFromMoves_;
    }
    public String getTrAttackTargetType(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = attackTargetWithTypes.get(_index);
        return translatedTypes_.getVal(type_);
    }

    public CustList<TypesDuo> getTypes() {
        return types;
    }

    public StringList getDisableImmuAgainstTypes() {
        return disableImmuAgainstTypes;
    }

    public StringList getDisableImmuFromMoves() {
        return disableImmuFromMoves;
    }

    public StringList getAttackTargetWithTypes() {
        return attackTargetWithTypes;
    }
}