package aiki.facade;

import aiki.comparators.ComparatorEgg;
import aiki.db.DataBase;
import aiki.map.pokemon.CriteriaForSearchingEgg;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.UsablePokemon;
import aiki.util.SortingEgg;
import code.util.CustList;
import code.util.Ints;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.core.IndexConstants;
import code.util.ints.Listable;

public final class PaginationEgg extends
        Pagination {

    public static final int NB_COMPARATORS = 2;

    private final LongFieldComparator cmpSteps = new LongFieldComparator();

    private final StringFieldComparator cmpName = new StringFieldComparator();

    private StringMap<String> translatedPokemon;

    private TreeMap<SortingEgg, Egg> eggs = new TreeMap<SortingEgg, Egg>(
            new ComparatorEgg());

    private final CustList<SortingEgg> rendered = new CustList<SortingEgg>();

    private final CriteriaForSearchingEgg criteria;

    public PaginationEgg() {
        criteria = new CriteriaForSearchingEgg();
    }

    public void setTranslation(DataBase _data, String _language) {
        translatedPokemon = _data.getTranslatedPokemon().getVal(_language);
    }

    public void search(CustList<UsablePokemon> _eggs) {
        eggs.clear();
        int len_ = _eggs.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            UsablePokemon us_ = _eggs.get(i);
            if (!(us_ instanceof Egg)) {
                continue;
            }
            Egg pk_ = (Egg) us_;
            if (match(pk_)) {
                SortingEgg s_ = new SortingEgg();
                s_.setIndex(i);
                s_.setKeyName(pk_.getName());
                s_.setName(translatedPokemon.getVal(pk_.getName()));
                s_.setSteps(pk_.getSteps());
                eggs.put(s_, pk_);
            }
        }
        if (!eggs.isEmpty()) {
            setNumberPage(IndexConstants.FIRST_INDEX);
        } else {
            setLine(IndexConstants.INDEX_NOT_FOUND_ELT);
            setNumberPage(IndexConstants.INDEX_NOT_FOUND_ELT);
            getRendered().clear();
            return;
        }
        setLine(IndexConstants.INDEX_NOT_FOUND_ELT);
        if (sortable()) {
            sort();
        }
        calculateRendered();
    }

    boolean match(Egg _egg) {
        if (!getCriteria().matchName(translatedPokemon.getVal(_egg.getName()))) {
            return false;
        }
        return getCriteria().matchSteps(_egg.getSteps());
    }

    public CriteriaForSearchingEgg getCriteria() {
        return criteria;
    }

    protected void clearRendered() {
        getRendered().clear();
    }
    @Override
    protected boolean sortable() {
        Ints priorities_;
        priorities_ = new Ints();
        if (cmpSteps.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpSteps.getPriority());
        }
        if (cmpName.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpName.getPriority());
        }
        return !priorities_.hasDuplicates();
    }

    @Override
    protected void sort() {
        TreeMap<SortingEgg, Egg> eggs_ = new TreeMap<SortingEgg, Egg>(
                new ComparatorEgg(cmpSteps, cmpName, NB_COMPARATORS));
        eggs_.putAllMap(eggs);
        eggs = eggs_;
    }

    public Egg currentObject() {
        int index_ = getIndex();
        if (!getEggs().getKeys().isValidIndex(index_)) {
            return null;
        }
        return getEggs().getValue(index_);
    }
    public LongFieldComparator getCmpSteps() {
        return cmpSteps;
    }

    public StringFieldComparator getCmpName() {
        return cmpName;
    }
    protected void excludeResults() {
        Listable<SortingEgg> list_ = getEggs().getKeys();
        for (SortingEgg k: list_) {
            Egg value_ = getEggs().getVal(k);
            if (match(value_)) {
                continue;
            }
            getEggs().removeKey(k);
        }
    }

    @Override
    protected boolean hasNoRendered() {
        return getRendered().isEmpty();
    }

    protected boolean hasNoResult() {
        return getEggs().isEmpty();
    }
    protected void updateRendered(int _end) {
        getRendered().addAllElts(getEggs().getKeys().sub(getFullCount(), _end));
    }
    protected void clearResults() {
        getEggs().clear();
    }
    protected int getResultsSize() {
        return getEggs().size();
    }

    CustList<SortingEgg> getRendered() {
        return rendered;
    }

    protected int getIndex(int _index) {
        return getEggs().getKey(_index).getIndex();
    }

    protected boolean isValidIndex(int _index) {
        return getEggs().getKeys().isValidIndex(_index);
    }

    TreeMap<SortingEgg, Egg> getEggs() {
        return eggs;
    }

}
