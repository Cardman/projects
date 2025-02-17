package aiki.beans.effects;

import aiki.beans.CommonBean;
import aiki.beans.TranslatedKey;
import aiki.beans.facade.comparators.*;
import aiki.db.DataBase;
import aiki.fight.util.ListEffectCombo;
import code.scripts.confs.*;
import code.util.CustList;

public class CombosBean extends CommonBean {
    static final String COMBO=PkScriptPages.REN_ADD_WEB_HTML_COMBO_COMBO_HTML;
    private ComboDto combos;

    @Override
    public void beforeDisplaying() {
        combos = new ComboDto(getLocCombos());
        CustList<EffectComboBean> combo_ = getForms().getCurrentComboBean();
        combo_.clear();
        int len_ = combos.size();
        for (int i = 0; i < len_; i++) {
            build(combo_,i,new EffectComboBean());
        }
    }

    private void build(CustList<EffectComboBean> _feed, int _i, EffectComboBean _b) {
        fwd(_b);
        _b.setCombos(combos);
        _b.setIndex(_i);
        _b.beforeDisplaying();
        _b.getForms().setCurrentComboBean(_feed);
        _feed.add(_b);
    }
    private DictionaryComparatorCombos getLocCombos() {
        DataBase data_ = getDataBase();
        DictionaryComparatorCombos combos_;
        combos_ = new DictionaryComparatorCombos();
        for (ListEffectCombo l: data_.getCombos().getEffects()) {
            combos_.put(listTrStringsMv(l.getList(),getFacade()), l.getCombo());
        }
        return combos_;
    }
    public CustList<CustList<TranslatedKey>> getCombosKey() {
        DataBase data_ = getDataBase();
        CustList<CustList<TranslatedKey>> combos_;
        combos_ = new CustList<CustList<TranslatedKey>>();
        for (ListEffectCombo l: data_.getCombos().getEffects()) {
            combos_.add(listTrStringsMv(l.getList(),getFacade()));
        }
        combos_.sortElts(new ComparatorTranslatedKeyList());
        return combos_;
    }

    public ComboDto getCombos() {
        return combos;
    }
}