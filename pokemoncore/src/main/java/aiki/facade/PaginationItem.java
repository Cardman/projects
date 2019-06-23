package aiki.facade;

import aiki.comparators.ComparatorItem;
import aiki.db.DataBase;
import aiki.fight.items.Item;
import aiki.game.player.Inventory;
import aiki.map.pokemon.CriteriaForSearchingItem;
import aiki.util.SortingItem;
import code.maths.LgInt;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;

public final class PaginationItem extends
        Pagination<SortingItem, String> {

    public static final int NB_CMPARATORS = 4;

    private StringFieldComparator cmpName = new StringFieldComparator();

    private LongFieldComparator cmpPrice = new LongFieldComparator();

    private StringFieldComparator cmpDescription = new StringFieldComparator();

    private FieldCustComparator<LgInt> cmpNumber = new FieldCustComparator<LgInt>();

    private StringMap<String> translatedItem;

    private StringMap<String> translatedDescription;

    private Inventory inventory;

    private TreeMap<SortingItem, String> items = new TreeMap<SortingItem, String>(
            new ComparatorItem());

    private CustList<SortingItem> rendered = new CustList<SortingItem>();

    private CriteriaForSearchingItem criteria;

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
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            Item i_ = _data.getItem(_list.get(i));
            String description_ = translatedDescription
                    .getVal(i_.getItemType());
            if (!getCriteria().matchPrice(i_.getPrice())) {
                continue;
            }
            if (!getCriteria().matchDescription(description_)) {
                continue;
            }
            if (!getCriteria().matchClass(i_)) {
                continue;
            }
            if (!getCriteria().matchNumber(inventory.getNumber(_list.get(i)))) {
                continue;
            }
            if (!match(translatedItem.getVal(_list.get(i)))) {
                continue;
            }
            SortingItem s_ = new SortingItem();
            s_.setIndex(i);
            s_.setName(translatedItem.getVal(_list.get(i)));
            s_.setKeyName(_list.get(i));
            s_.setNumber(inventory.getNumber(_list.get(i)));
            s_.setPrice(i_.getPrice());
            s_.setItemClass(description_);
            items.put(s_, _list.get(i));
        }
        search(new StringList(items.values()));
    }

    protected void search(Listable<String> _items) {
        if (!_items.isEmpty()) {
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
    protected boolean match(String _item) {
        if (!getCriteria().matchName(_item)) {
            return false;
        }
        return true;
    }

    public CriteriaForSearchingItem getCriteria() {
        return criteria;
    }
    @Override
    protected boolean sortable() {
        Ints priorities_;
        priorities_ = new Ints();
        if (cmpPrice.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpPrice.getPriority());
        }
        if (cmpNumber.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpNumber.getPriority());
        }
        if (cmpName.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpName.getPriority());
        }
        if (cmpDescription.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpDescription.getPriority());
        }
        int size_ = priorities_.size();
        priorities_.removeDuplicates();
        return size_ == priorities_.size();
    }

    @Override
    protected void sort() {
        TreeMap<SortingItem, String> items_ = new TreeMap<SortingItem, String>(
                new ComparatorItem(cmpName, cmpPrice, cmpDescription,
                        cmpNumber, NB_CMPARATORS));
        items_.putAllMap(items);
        items = items_;
    }

    public StringFieldComparator getCmpName() {
        return cmpName;
    }

    public LongFieldComparator getCmpPrice() {
        return cmpPrice;
    }

    public StringFieldComparator getCmpDescription() {
        return cmpDescription;
    }

    public FieldCustComparator<LgInt> getCmpNumber() {
        return cmpNumber;
    }

    @Override
    protected TreeMap<SortingItem, String> getResults() {
        return items;
    }

    @Override
    protected CustList<SortingItem> getRendered() {
        return rendered;
    }
}
