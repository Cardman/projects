package aiki.beans.effects;
import code.bean.Accessible;
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorStringList;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.effects.EffectCombo;

public class CombosBean extends CommonBean {

    @Accessible
    private final String combo="web/html/combo/combo.html";

    @Accessible
    private TreeMap<StringList, EffectCombo> combos;

    @Override
    public void beforeDisplaying() {
        combos = getCombos();
    }

    private TreeMap<StringList, EffectCombo> getCombos() {
        DataBase data_ = (DataBase) getDataBase();
        TreeMap<StringList,EffectCombo> combos_;
//        combos_ = new TreeMap<new>(new NaturalComparator<StringList>() {
//            @Override
//            public int compare(StringList _o1, StringList _o2) {
//                int min_ = Math.min(_o1.size(), _o2.size());
//                for (int i = List.FIRST_INDEX; i < min_; i++) {
//                    int res_ = _o1.get(i).compareTo(_o2.get(i));
//                    if (res_ != 0) {
//                        return res_;
//                    }
//                }
//                return _o1.size() - _o2.size();
//            }
//        });
//        combos_ = new TreeMap<new>(new ComparatorStringList(data_, getLanguage(), true));
        combos_ = new TreeMap<StringList, EffectCombo>(new ComparatorStringList(data_, getLanguage(), false));
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        for (StringList l: data_.getCombos().getEffects().getKeys()) {
            StringList key_ = new StringList(l);
            key_.sortElts(new ComparatorTrStrings(translatedMoves_));
            combos_.put(key_, data_.getCombos().getEffects().getVal(l));
        }
        return combos_;
    }

    @Accessible
    private EqList<StringList> getCombosKey() {
        DataBase data_ = (DataBase) getDataBase();
        EqList<StringList> combos_;
        combos_ = new EqList<StringList>();
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        for (StringList l: data_.getCombos().getEffects().getKeys()) {
            StringList key_ = new StringList(l);
            key_.sortElts(new ComparatorTrStrings(translatedMoves_));
            combos_.add(key_);
        }
//        combos_.sort(new NaturalComparator<StringList>() {
//            @Override
//            public int compare(StringList _o1, StringList _o2) {
//                int min_ = Math.min(_o1.size(), _o2.size());
//                for (int i = List.FIRST_INDEX; i < min_; i++) {
//                    int res_ = _o1.get(i).compareTo(_o2.get(i));
//                    if (res_ != 0) {
//                        return res_;
//                    }
//                }
//                return _o1.size() - _o2.size();
//            }
//        });
//        combos_.sort(new ComparatorStringList(data_, getLanguage(), true));
        combos_.sortElts(new ComparatorStringList(data_, getLanguage(), false));
        return combos_;
    }
}
