package aiki.beans.effects;

import aiki.beans.*;
import aiki.beans.facade.comparators.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.util.ListEffectCombo;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.CustList;
import code.util.StringMap;

public final class CombosBean extends CommonBean implements BeanRenderWithAppName {
    static final String COMBO=PkScriptPages.REN_ADD_WEB_HTML_COMBO_COMBO_HTML;
    private ComboDto combos;
    private CustList<EffectComboBean> list;

    public CombosBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataCombo.M_P_2_TITLE));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,this), MessagesPkBean.COMBO,MessagesDataCombo.M_P_2_INDEX);
        for (EffectComboBean e:list) {
            e.buildSub();
        }
    }

    public CustList<EffectComboBean> getList() {
        return list;
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.COMBO).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        combos = new ComboDto(getLocCombos());
        CustList<EffectComboBean> combo_ = new CustList<EffectComboBean>();
        list = combo_;
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