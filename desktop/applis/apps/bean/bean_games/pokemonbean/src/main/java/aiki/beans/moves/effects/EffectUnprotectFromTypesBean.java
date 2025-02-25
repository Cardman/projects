package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.beans.abilities.*;
import aiki.beans.facade.comparators.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.scripts.pages.aiki.MessagesDataEffunprotectfromtypes;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;

public class EffectUnprotectFromTypesBean extends EffectBean {
    private CustList<TranslatedKeyPair> types;
    private CustList<TranslatedKey> disableImmuAgainstTypes;
    private CustList<TranslatedKey> disableImmuFromMoves;
    private CustList<TranslatedKey> attackTargetWithTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectUnprotectFromTypes effect_ = (EffectUnprotectFromTypes) getEffect();
        CustList<TranslatedKeyPair> types_;
        types_ = new CustList<TranslatedKeyPair>();
        for (TypesDuo duo_: effect_.getTypes()) {
            types_.add(new TranslatedKeyPair(buildTy(getFacade(),duo_.getDamageType()),buildTy(getFacade(),duo_.getPokemonType())));
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
        types_.sortElts(new ComparatorTranslatedKeyPair());
        types = types_;
//        StringList disableImmuAgainstTypes_;
//        disableImmuAgainstTypes_ = new StringList();
//        for (String type_: effect_.getDisableImmuAgainstTypes()) {
//            disableImmuAgainstTypes_.add(type_);
//        }
//        disableImmuAgainstTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        disableImmuAgainstTypes = listTrStringsTy(effect_.getDisableImmuAgainstTypes(),getFacade());
        disableImmuFromMoves = listTrStringsMv(effect_.getDisableImmuFromMoves(),getFacade());
//        StringList attackTargetWithTypes_;
//        attackTargetWithTypes_ = new StringList();
//        for (String type_: effect_.getAttackTargetWithTypes()) {
//            attackTargetWithTypes_.add(type_);
//        }
//        attackTargetWithTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        attackTargetWithTypes = listTrStringsTy(effect_.getAttackTargetWithTypes(),getFacade());
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_UNPROTECTFROMTYPES,MessagesDataEffunprotectfromtypes.M_P_68_EFFECT);
    }

    @Override
    public void buildSubEff() {
        new BeanDisplayListGrid<TranslatedKeyPair>(new BeanDisplayTranslatedKeyPair()).displayGrid(this,getTypes(), MessagesPkBean.EFF_UNPROTECTFROMTYPES, MessagesDataEffunprotectfromtypes.M_P_68_TYPES,MessagesDataEffunprotectfromtypes.M_P_68_TYPES_DAMAG,MessagesDataEffunprotectfromtypes.M_P_68_TYPES_PK);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getDisableImmuAgainstTypes(),MessagesPkBean.EFF_UNPROTECTFROMTYPES,MessagesDataEffunprotectfromtypes.M_P_68_DISABLE_IMMU_TYPES);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getDisableImmuFromMoves(),MessagesPkBean.EFF_UNPROTECTFROMTYPES,MessagesDataEffunprotectfromtypes.M_P_68_DISABLE_IMMU_FROM_MOVES);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getAttackTargetWithTypes(),MessagesPkBean.EFF_UNPROTECTFROMTYPES,MessagesDataEffunprotectfromtypes.M_P_68_ATTACK_TARGET_TYPES);
    }

    public String getTrDamageType(int _index) {
        return types.get(_index).getFirst().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String type_ = types.get(_index).getDamageType();
//        return translatedTypes_.getVal(type_);
    }
    public String getTrPokemonType(int _index) {
        return types.get(_index).getSecond().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String type_ = types.get(_index).getPokemonType();
//        return translatedTypes_.getVal(type_);

    }
    public String getTrDisableImmuType(int _index) {
        return disableImmuAgainstTypes.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String type_ = disableImmuAgainstTypes.get(_index);
//        return translatedTypes_.getVal(type_);

    }
    public String clickMove(int _indexEffect, int _index) {
        return tryRedirect(((EffectUnprotectFromTypesBean)getForms().getCurrentBean().get(_indexEffect)).disableImmuFromMoves.get(_index));
    }
    public String getTrDisableImmuMove(int _index) {
        return disableImmuFromMoves.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String type_ = disableImmuFromMoves.get(_index);
//        return translatedMoves_.getVal(type_);
    }

    public String getTrAttackTargetType(int _index) {
        return attackTargetWithTypes.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String type_ = attackTargetWithTypes.get(_index);
//        return translatedTypes_.getVal(type_);
    }

    public CustList<TranslatedKeyPair> getTypes() {
        return types;
    }

    public CustList<TranslatedKey> getDisableImmuAgainstTypes() {
        return disableImmuAgainstTypes;
    }

    public CustList<TranslatedKey> getDisableImmuFromMoves() {
        return disableImmuFromMoves;
    }

    public CustList<TranslatedKey> getAttackTargetWithTypes() {
        return attackTargetWithTypes;
    }
}