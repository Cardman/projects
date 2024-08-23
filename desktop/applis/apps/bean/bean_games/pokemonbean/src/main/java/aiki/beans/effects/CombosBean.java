package aiki.beans.effects;

import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorStringList;
import aiki.beans.facade.comparators.DictionaryComparatorCombos;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.util.ListEffectCombo;
import code.scripts.confs.*;
import code.util.CustList;
import code.util.StringList;

public class CombosBean extends CommonBean {
    static final String COMBO=PkScriptPages.REN_ADD_WEB_HTML_COMBO_COMBO_HTML;
    private ComboDto combos;

    @Override
    public void beforeDisplaying() {
        combos = new ComboDto(getLocCombos());
    }

    private DictionaryComparatorCombos getLocCombos() {
        DataBase data_ = getDataBase();
        DictionaryComparatorCombos combos_;
        combos_ = new DictionaryComparatorCombos(data_, getLanguage());
        for (ListEffectCombo l: data_.getCombos().getEffects()) {
            StringList key_ = new StringList(l.getList());
            key_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
            combos_.put(key_, l.getCombo());
        }
        return combos_;
    }
    public CustList<StringList> getCombosKey() {
        DataBase data_ = getDataBase();
        CustList<StringList> combos_;
        combos_ = new CustList<StringList>();
        for (ListEffectCombo l: data_.getCombos().getEffects()) {
            StringList key_ = new StringList(l.getList());
            key_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
            combos_.add(key_);
        }
        combos_.sortElts(new ComparatorStringList(data_, getLanguage(), false));
        return combos_;
    }

    public ComboDto getCombos() {
        return combos;
    }
}