package aiki.facade;

import aiki.comparators.ComparatorEgg;
import aiki.db.DataBase;
import aiki.map.pokemon.CriteriaForSearchingEgg;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.UsablePokemon;
import aiki.util.SortingEgg;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringMap;
import code.util.TreeMap;

public final class PaginationEgg extends
        Pagination<SortingEgg, Egg> {

    private LongFieldComparator cmpSteps = new LongFieldComparator();

    private StringFieldComparator cmpName = new StringFieldComparator();

    private StringMap<String> translatedPokemon;

    private final int nbComparators = 2;

    private TreeMap<SortingEgg, Egg> eggs = new TreeMap<SortingEgg, Egg>(
            new ComparatorEgg());

    private EqList<SortingEgg> rendered = new EqList<SortingEgg>();

    private CriteriaForSearchingEgg criteria;

    public PaginationEgg() {
        criteria = new CriteriaForSearchingEgg();
    }

    public void setTranslation(DataBase _data, String _language) {
        translatedPokemon = _data.getTranslatedPokemon().getVal(_language);
    }

    public void search(CustList<UsablePokemon> _eggs) {
        eggs.clear();
        int len_ = _eggs.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            UsablePokemon us_ = _eggs.get(i);
            if (!(us_ instanceof Egg)) {
                continue;
            }
            Egg pk_ = (Egg) us_;
            if (!match(pk_)) {
                continue;
            }
            SortingEgg s_ = new SortingEgg();
            s_.setIndex(i);
            s_.setKeyName(pk_.getName());
            s_.setName(translatedPokemon.getVal(pk_.getName()));
            s_.setSteps(pk_.getSteps());
            eggs.put(s_, pk_);
        }
        if (!eggs.isEmpty()) {
            setNumberPage(CustList.FIRST_INDEX);
        } else {
            setLine(CustList.INDEX_NOT_FOUND_ELT);
            setNumberPage(CustList.INDEX_NOT_FOUND_ELT);
            getRendered().clear();
            return;
        }
        setLine(CustList.INDEX_NOT_FOUND_ELT);
        if (sortable()) {
            sort();
        }
        calculateRendered();
    }

    @Override
    protected boolean match(Egg _egg) {
        if (!getCriteria().matchName(translatedPokemon.getVal(_egg.getName()))) {
            return false;
        }
        if (!getCriteria().matchSteps(_egg.getSteps())) {
            return false;
        }
        return true;
    }

    @Override
    public CriteriaForSearchingEgg getCriteria() {
        return criteria;
    }
    @Override
    public boolean sortable() {
        Numbers<Integer> priorities_;
        priorities_ = new Numbers<Integer>();
        if (cmpSteps.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpSteps.getPriority());
        }
        if (cmpName.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpName.getPriority());
        }
        int size_ = priorities_.size();
        priorities_.removeDuplicates();
        return size_ == priorities_.size();
    }

    @Override
    public void sort() {
        // TreeMap<SortingEgg, Egg> eggs_ = new TreeMap<new>(new
        // Comparator<SortingEgg>() {
        // @Override
        // public int compare(SortingEgg _o1, SortingEgg _o2) {
        // for (int i = nbComparators; i >= MIN_PRIORITY; i--) {
        // if (cmpSteps.getPriority() == i) {
        // int res_ = cmpSteps.compare(_o1.getSteps(), _o2.getSteps());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpName.getPriority() == i) {
        // int res_ = cmpName.compare(_o1.getName(), _o2.getName());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // }
        // }
        // return Integer.compare(_o1.getIndex(), _o2.getIndex());
        // }
        // });
        TreeMap<SortingEgg, Egg> eggs_ = new TreeMap<SortingEgg, Egg>(
                new ComparatorEgg(cmpSteps, cmpName, nbComparators));
        eggs_.putAllTreeMap(eggs);
        eggs = eggs_;
    }

    public LongFieldComparator getCmpSteps() {
        return cmpSteps;
    }

    public void setCmpSteps(LongFieldComparator _cmpSteps) {
        cmpSteps = _cmpSteps;
    }

    public StringFieldComparator getCmpName() {
        return cmpName;
    }

    public void setCmpName(StringFieldComparator _cmpName) {
        cmpName = _cmpName;
    }

    @Override
    public EqList<SortingEgg> getRendered() {
        return rendered;
    }

    public void setRendered(EqList<SortingEgg> _rendered) {
        rendered = _rendered;
    }

    @Override
    protected TreeMap<SortingEgg, Egg> getResults() {
        return eggs;
    }

    public int getNbComparators() {
        return nbComparators;
    }
}
