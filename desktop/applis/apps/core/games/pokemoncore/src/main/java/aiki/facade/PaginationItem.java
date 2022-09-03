package aiki.facade;

import aiki.comparators.ComparatorItem;
import aiki.db.DataBase;
import aiki.fight.items.Item;
import aiki.game.player.Inventory;
import aiki.map.pokemon.CriteriaForSearchingItem;
import aiki.util.SortingItem;
import code.util.CustList;
import code.util.Ints;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.core.IndexConstants;
import code.util.ints.Listable;

public final class PaginationItem extends
        Pagination {

    public static final int NB_CMPARATORS = 4;

    private final ComparatorItem comparatorItem = new ComparatorItem();

    private StringMap<String> translatedItem;

    private StringMap<String> translatedDescription;

    private Inventory inventory;

    private TreeMap<SortingItem, String> items = new TreeMap<SortingItem, String>(
            comparatorItem);

    private final CustList<SortingItem> rendered = new CustList<SortingItem>();

    private final CriteriaForSearchingItem criteria;

    public PaginationItem() {
        criteria = new CriteriaForSearchingItem();
    }

    public void setInventory(Inventory _inventory) {
        inventory = _inventory;
    }

    public void setTranslation(DataBase _data, String _language) {
        translatedItem = _data.getTranslatedItems().getVal(_language);
        translatedDescription = _data.getTranslatedClassesDescriptions()
                .getVal(_language);
    }

    public void search(CustList<String> _list, DataBase _data) {
        items.clear();
        int len_ = _list.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            Item i_ = _data.getItem(_list.get(i));
            String description_ = translatedDescription
                    .getVal(i_.getItemType());
            if (!getCriteria().matchPrice(i_.getPrice()) || !getCriteria().matchDescription(description_) || !getCriteria().matchClass(i_) || !getCriteria().matchNumber(inventory.getNumber(_list.get(i))) || !match(translatedItem.getVal(_list.get(i)))) {
                continue;
            }
            SortingItem s_ = new SortingItem();
            s_.setIndex(i);
            s_.setName(translatedItem.getVal(_list.get(i)));
            s_.setKeyName(_list.get(i));
            s_.setNumber(inventory.getNumber(_list.get(i)));
            s_.setPrice(i_.getPrice());
            s_.setItemClass(description_);
            items.addEntry(s_, _list.get(i));
        }
        search();
    }

    @Override
    protected boolean isEmpty() {
        return items.isEmpty();
    }

    boolean match(String _item) {
        return getCriteria().matchName(_item);
    }

    public CriteriaForSearchingItem getCriteria() {
        return criteria;
    }

    protected void clearRendered() {
        getRendered().clear();
    }
    @Override
    protected boolean sortable() {
        Ints priorities_;
        priorities_ = new Ints();
        if (comparatorItem.getCmpPrice().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorItem.getCmpPrice().getPriority());
        }
        if (comparatorItem.getCmpNumber().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorItem.getCmpNumber().getPriority());
        }
        if (comparatorItem.getCmpName().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorItem.getCmpName().getPriority());
        }
        if (comparatorItem.getCmpDescription().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorItem.getCmpDescription().getPriority());
        }
        return !priorities_.hasDuplicates();
    }

    @Override
    protected void sort() {
        TreeMap<SortingItem, String> items_ = new TreeMap<SortingItem, String>(
                comparatorItem);
        items_.putAllMap(items);
        items = items_;
    }

    public String currentObject() {
        int index_ = getIndex();
        if (!getItems().getKeys().isValidIndex(index_)) {
            return "";
        }
        return getItems().getValue(index_);
    }
    public StringFieldComparator getCmpName() {
        return comparatorItem.getCmpName();
    }

    public LongFieldComparator getCmpPrice() {
        return comparatorItem.getCmpPrice();
    }

    public StringFieldComparator getCmpDescription() {
        return comparatorItem.getCmpDescription();
    }

    public LgIntFieldComparator getCmpNumber() {
        return comparatorItem.getCmpNumber();
    }
    protected void excludeResults() {
        Listable<SortingItem> list_ = getItems().getKeys();
        for (SortingItem k: list_) {
            String value_ = getItems().getVal(k);
            if (match(value_)) {
                continue;
            }
            getItems().removeKey(k);
        }
    }

    @Override
    protected boolean hasNoRendered() {
        return getRendered().isEmpty();
    }
    protected boolean hasNoResult() {
        return getItems().isEmpty();
    }
    protected void updateRendered(int _end) {
        getRendered().addAllElts(getItems().getKeys().sub(getFullCount(), _end));
    }
    protected void clearResults() {
        getItems().clear();
    }
    protected int getResultsSize() {
        return getItems().size();
    }

    protected int getIndex(int _index) {
        return getItems().getKey(_index).getIndex();
    }

    protected boolean isValidIndex(int _index) {
        return getItems().getKeys().isValidIndex(_index);
    }

    TreeMap<SortingItem, String> getItems() {
        return items;
    }


    CustList<SortingItem> getRendered() {
        return rendered;
    }
}
