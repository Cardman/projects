package aiki.beans.moves.effects;
import aiki.beans.facade.comparators.ComparatorTypesDuo;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectUnprotectFromTypes;
import aiki.fight.util.TypesDuo;
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;

public class EffectUnprotectFromTypesBean extends EffectBean {
    private EqList<TypesDuo> types;
    private StringList disableImmuAgainstTypes;
    private StringList disableImmuFromMoves;
    private StringList attackTargetWithTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectUnprotectFromTypes effect_ = (EffectUnprotectFromTypes) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        EqList<TypesDuo> types_;
        types_ = new EqList<TypesDuo>();
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
        types_.sortElts(new ComparatorTypesDuo(data_, getLanguage(), false));
        types = types_;
        StringList disableImmuAgainstTypes_;
        disableImmuAgainstTypes_ = new StringList();
        for (String type_: effect_.getDisableImmuAgainstTypes()) {
            disableImmuAgainstTypes_.add(type_);
        }
        disableImmuAgainstTypes_.sortElts(new ComparatorTrStrings(translatedTypes_));
        disableImmuAgainstTypes = disableImmuAgainstTypes_;
        StringList disableImmuFromMoves_ = getDisableImmuFromMoves(effect_);
        disableImmuFromMoves = disableImmuFromMoves_;
        StringList attackTargetWithTypes_;
        attackTargetWithTypes_ = new StringList();
        for (String type_: effect_.getAttackTargetWithTypes()) {
            attackTargetWithTypes_.add(type_);
        }
        attackTargetWithTypes_.sortElts(new ComparatorTrStrings(translatedTypes_));
        attackTargetWithTypes = attackTargetWithTypes_;
    }
    public String getTrDamageType(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = types.get(_index.intValue()).getDamageType();
        return translatedTypes_.getVal(type_);
    }
    public String getTrPokemonType(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = types.get(_index.intValue()).getPokemonType();
        return translatedTypes_.getVal(type_);

    }
    public String getTrDisableImmuType(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = disableImmuAgainstTypes.get(_index.intValue());
        return translatedTypes_.getVal(type_);

    }
    public String clickMove(Long _indexEffect, Long _index) {
        String type_ = getDisableImmuFromMoves(_indexEffect.intValue()).get(_index.intValue());
        getForms().put(MOVE, type_);
        return MOVE;
    }
    public String getTrDisableImmuMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String type_ = disableImmuFromMoves.get(_index.intValue());
        return translatedMoves_.getVal(type_);
    }

    private StringList getDisableImmuFromMoves(int _index) {
        EffectUnprotectFromTypes effect_ = (EffectUnprotectFromTypes) getEffect(_index);
        return getDisableImmuFromMoves(effect_);
    }

    private StringList getDisableImmuFromMoves(EffectUnprotectFromTypes _effect) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList disableImmuFromMoves_;
        disableImmuFromMoves_ = new StringList();
        for (String type_: _effect.getDisableImmuFromMoves()) {
            disableImmuFromMoves_.add(type_);
        }
        disableImmuFromMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        return disableImmuFromMoves_;
    }
    public String getTrAttackTargetType(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = attackTargetWithTypes.get(_index.intValue());
        return translatedTypes_.getVal(type_);
    }

    public EqList<TypesDuo> getTypes() {
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