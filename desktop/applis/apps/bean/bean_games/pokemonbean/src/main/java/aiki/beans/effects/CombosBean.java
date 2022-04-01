package aiki.beans.effects;

import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorStringList;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.util.ListEffectCombo;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public class CombosBean extends CommonBean {
    static final String COMBO="web/html/combo/combo.html";
    private ComboDto combos;

    @Override
    public void beforeDisplaying() {
        combos = new ComboDto(getLocCombos());
    }

    private TreeMap<StringList, EffectCombo> getLocCombos() {
        DataBase data_ = (DataBase) getDataBase();
        TreeMap<StringList,EffectCombo> combos_;
        combos_ = new TreeMap<StringList, EffectCombo>(new ComparatorStringList(data_, getLanguage(), false));
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        for (ListEffectCombo l: data_.getCombos().getEffects()) {
            StringList key_ = new StringList(l.getList());
            key_.sortElts(new ComparatorTrStrings(translatedMoves_));
            combos_.put(key_, l.getCombo());
        }
        return combos_;
    }
    public CustList<StringList> getCombosKey() {
        DataBase data_ = (DataBase) getDataBase();
        CustList<StringList> combos_;
        combos_ = new CustList<StringList>();
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        for (ListEffectCombo l: data_.getCombos().getEffects()) {
            StringList key_ = new StringList(l.getList());
            key_.sortElts(new ComparatorTrStrings(translatedMoves_));
            combos_.add(key_);
        }
        combos_.sortElts(new ComparatorStringList(data_, getLanguage(), false));
        return combos_;
    }

    public ComboDto getCombos() {
        return combos;
    }
}