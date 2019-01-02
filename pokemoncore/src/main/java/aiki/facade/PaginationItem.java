package aiki.facade;

import aiki.comparators.ComparatorItem;
import aiki.db.DataBase;
import aiki.fight.items.Item;
import aiki.game.player.Inventory;
import aiki.map.pokemon.CriteriaForSearchingItem;
import aiki.util.SortingItem;
import code.maths.LgInt;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;

public final class PaginationItem extends
        Pagination<SortingItem, String> {

    private StringFieldComparator cmpName = new StringFieldComparator();

    private LongFieldComparator cmpPrice = new LongFieldComparator();

    private StringFieldComparator cmpDescription = new StringFieldComparator();

    private FieldCustComparator<LgInt> cmpNumber = new FieldCustComparator<LgInt>();

    private StringMap<String> translatedItem;

    private StringMap<String> translatedDescription;

    private Inventory inventory;

    private final int nbComparators = 4;

    private TreeMap<SortingItem, String> items = new TreeMap<SortingItem, String>(
            new ComparatorItem());

    private EqList<SortingItem> rendered = new EqList<SortingItem>();

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

    public void search(Listable<String> _list, DataBase _data) {
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

    // @Override
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

    @Override
    public CriteriaForSearchingItem getCriteria() {
        return criteria;
    }
    @Override
    protected boolean sortable() {
        Numbers<Integer> priorities_;
        priorities_ = new Numbers<Integer>();
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
        // TreeMap<SortingItem, String> items_ = new TreeMap<new>(new
        // Comparator<SortingItem>() {
        // @Override
        // public int compare(SortingItem _o1, SortingItem _o2) {
        // for (int i = nbComparators; i >= MIN_PRIORITY; i--) {
        // if (cmpPrice.getPriority() == i) {
        // int res_ = cmpPrice.compare(_o1.getPrice(), _o2.getPrice());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpName.getPriority() == i) {
        // int res_ = cmpName.compare(_o1.getName(), _o2.getName());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpDescription.getPriority() == i) {
        // int res_ = cmpDescription.compare(_o1.getItemClass(),
        // _o2.getItemClass());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpNumber.getPriority() == i) {
        // int res_ = cmpNumber.compare(_o1.getNumber(), _o2.getNumber());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // }
        // }
        // return Integer.compare(_o1.getIndex(), _o2.getIndex());
        // }
        // });
        TreeMap<SortingItem, String> items_ = new TreeMap<SortingItem, String>(
                new ComparatorItem(cmpName, cmpPrice, cmpDescription,
                        cmpNumber, nbComparators));
        items_.putAllTreeMap(items);
        items = items_;
    }

    public StringFieldComparator getCmpName() {
        return cmpName;
    }

    public void setCmpName(StringFieldComparator _cmpName) {
        cmpName = _cmpName;
    }

    public LongFieldComparator getCmpPrice() {
        return cmpPrice;
    }

    public void setCmpPrice(LongFieldComparator _cmpPrice) {
        cmpPrice = _cmpPrice;
    }

    public StringFieldComparator getCmpDescription() {
        return cmpDescription;
    }

    public void setCmpDescription(StringFieldComparator _cmpDescription) {
        cmpDescription = _cmpDescription;
    }

    public FieldCustComparator<LgInt> getCmpNumber() {
        return cmpNumber;
    }

    public void setCmpNumber(FieldCustComparator<LgInt> _cmpNumber) {
        cmpNumber = _cmpNumber;
    }

    public StringMap<String> getTranslatedItem() {
        return translatedItem;
    }

    public void setTranslatedItem(StringMap<String> _translatedItem) {
        translatedItem = _translatedItem;
    }

    public StringMap<String> getTranslatedDescription() {
        return translatedDescription;
    }

    public void setTranslatedDescription(
            StringMap<String> _translatedDescription) {
        translatedDescription = _translatedDescription;
    }

    public int getNbComparators() {
        return nbComparators;
    }

    public void setRendered(EqList<SortingItem> _rendered) {
        rendered = _rendered;
    }

    @Override
    protected TreeMap<SortingItem, String> getResults() {
        return items;
    }

    @Override
    protected EqList<SortingItem> getRendered() {
        return rendered;
    }
}
