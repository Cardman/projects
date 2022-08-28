package aiki.facade;

import aiki.comparators.ComparatorHealingItem;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import aiki.game.player.Inventory;
import aiki.map.pokemon.CriteriaForSearchingHealingItem;
import aiki.util.SortingHealingItem;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.AbsMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.core.IndexConstants;
import code.util.ints.Listable;

public final class PaginationHealingItem extends
        Pagination {

    public static final int NB_COMPARATORS = 13;

    private ComparatorHealingItem comparatorHealingItem = new ComparatorHealingItem(0);

    private StringMap<String> translatedItem;

    private StringMap<String> translatedDescription;

    private StringMap<String> translatedStatus;

    private AbsMap<Statistic, String> translatedStatistics;

    private Inventory inventory;
    private TreeMap<SortingHealingItem, String> items = new TreeMap<SortingHealingItem, String>(
            comparatorHealingItem);

    private final CustList<SortingHealingItem> rendered = new CustList<SortingHealingItem>();

    private final CriteriaForSearchingHealingItem criteria;

    public PaginationHealingItem() {
        criteria = new CriteriaForSearchingHealingItem();
    }

    public void setTranslation(DataBase _data, String _language) {
        translatedItem = _data.getTranslatedItems().getVal(_language);
        translatedDescription = _data.getTranslatedClassesDescriptions()
                .getVal(_language);
        translatedStatus = _data.getTranslatedStatus().getVal(_language);
        translatedStatistics = _data.getTranslatedStatistics()
                .getVal(_language);
        getCriteria().setTranslatedStatus(
                _data.getTranslatedStatus().getVal(_language));
    }

    public void setInventory(Inventory _inventory) {
        inventory = _inventory;
    }

    public void search(CustList<String> _list, DataBase _data) {
        items.clear();
        Shorts pps_ = new Shorts();
        for (MoveData f : _data.getMoves().values()) {
            pps_.add(f.getPp());
        }
        long maxPp_ = pps_.getMaximum(-1);
        int len_ = _list.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            Item i_ = _data.getItem(_list.get(i));
            if (!(i_ instanceof HealingItem) && !(i_ instanceof Berry)) {
                continue;
            }
            String description_ = translatedDescription
                    .getVal(i_.getItemType());
            if (getCriteria().matchPrice(i_.getPrice()) && getCriteria().matchDescription(description_) && getCriteria().matchHp(i_) && getCriteria().matchRateHp(i_) && getCriteria().matchKo(i_) && getCriteria().matchPp(i_) && getCriteria().matchStatus(i_) && getCriteria().matchStatistic(i_) && getCriteria().matchClass(i_) && getCriteria().matchNumber(inventory.getNumber(_list.get(i))) && match(_list.get(i))) {
                SortingHealingItem s_ = sortingHealingItem(_list, maxPp_, i, i_, description_);
                items.put(s_, _list.get(i));
            }
        }
        search(new StringList(items.values()));
    }

    private SortingHealingItem sortingHealingItem(CustList<String> _list, long _maxPp, int _i, Item _item, String _description) {
        SortingHealingItem s_ = new SortingHealingItem();
        s_.setIndex(_i);
        s_.setNumber(new LgInt(inventory.getNumber(_list.get(_i))));
        s_.setKeyName(_list.get(_i));
        s_.setName(translatedItem.getVal(_list.get(_i)));
        Rate hp_ = Rate.zero();
        Rate hpRate_ = Rate.zero();
        if (_item instanceof HealingHp) {
            hp_.addNb(((HealingHp) _item).getHp());
            s_.setRelativeRateHp(false);
        }
        if (_item instanceof Berry) {
            hpRate_.addNb(((Berry) _item).getHealHpBySuperEffMove());
            hpRate_.addNb(((Berry) _item).getHealHpRate());
            hp_.addNb(((Berry) _item).getHealHp());
        }
        if (_item instanceof HealingHpStatus) {
            HealingHpStatus healingHp_ = (HealingHpStatus) _item;
            hpRate_.addNb(healingHp_.getHealedHpRate());
            s_.setRelativeRateHp(true);
        }
        s_.setPp(Rate.zero());
        pp(_maxPp, _item, s_);
        if (_item instanceof Berry) {
            Berry healing_ = (Berry) _item;
            if (healing_.getHealPp() > 0) {
                s_.setHealOneMove(true);
                s_.setRelativeRatePp(false);
                s_.setPp(new Rate(healing_.getHealPp()));
            }
        }
        s_.setHp(hp_);
        s_.setHpRate(hpRate_);
        s_.setNbHealedStatus(0);
        if (_item instanceof HealingStatus) {
            HealingStatus healing_ = (HealingStatus) _item;
            s_.setNbHealedStatus(healing_.getStatus().size());
        }
        if (_item instanceof Berry) {
            Berry healing_ = (Berry) _item;
            s_.setNbHealedStatus(healing_.getHealStatus().size());
        }
        for (String status_ : CriteriaForSearchingHealingItem.getStatus(_item)) {
            s_.getStatus().add(translatedStatus.getVal(status_));
        }
        s_.setNbStatistics(0);
        if (_item instanceof Berry) {
            Berry healing_ = (Berry) _item;
            s_.setNbStatistics(healing_.getMultStat().size());
            for (Statistic statis_ : healing_.getMultStat().getKeys()) {
                s_.getStatistics()
                        .add(translatedStatistics.getVal(statis_));
            }
        }
        s_.setKo(false);
        if (_item instanceof HealingStatus) {
            HealingStatus healing_ = (HealingStatus) _item;
            s_.setKo(healing_.getHealingKo());
        }
        s_.setPrice(_item.getPrice());
        s_.setItemClass(_description);
        return s_;
    }

    private void pp(long _maxPp, Item _item, SortingHealingItem _s) {
        if (_item instanceof HealingPp) {
            HealingPp healing_ = (HealingPp) _item;
            long pp_ = 0;
            if (healing_.getHealingMoveFullpp()) {
                pp_ = _maxPp;
                _s.setHealOneMove(true);
            }
            if (healing_.isHealingAllMovesPp()) {
                pp_ = _maxPp;
                _s.setHealOneMove(false);
            }
            if (pp_ == 0) {
                _s.setRelativeRatePp(false);
                pp_ = healing_.getHealedMovePp();
                if (pp_ == 0) {
                    _s.setHealOneMove(false);
                    pp_ = healing_.getHealingAllMovesFullpp();
                } else {
                    _s.setHealOneMove(true);
                }
            } else {
                _s.setRelativeRatePp(true);
            }
            _s.setPp(new Rate(pp_));
        }
    }

    void search(Listable<String> _items) {
        if (!_items.isEmpty()) {
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

    public CriteriaForSearchingHealingItem getCriteria() {
        return criteria;
    }

    boolean match(String _item) {
        return getCriteria().matchName(_item);
    }

    protected void clearRendered() {
        getRendered().clear();
    }
    @Override
    protected boolean sortable() {
        Ints priorities_;
        priorities_ = new Ints();
        if (comparatorHealingItem.getCmpPrice().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpPrice().getPriority());
        }
        if (comparatorHealingItem.getCmpName().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpName().getPriority());
        }
        if (comparatorHealingItem.getCmpHealOneMove().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpHealOneMove().getPriority());
        }
        if (comparatorHealingItem.getCmpHp().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpHp().getPriority());
        }
        if (comparatorHealingItem.getCmpRateHp().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpRateHp().getPriority());
        }
        if (comparatorHealingItem.getCmpStatistics().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpStatistics().getPriority());
        }
        if (comparatorHealingItem.getCmpKo().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpKo().getPriority());
        }
        if (comparatorHealingItem.getCmpNbHealedStatus().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpNbHealedStatus().getPriority());
        }
        if (comparatorHealingItem.getCmpPp().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpPp().getPriority());
        }
        if (comparatorHealingItem.getCmpRelativeRateHp().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpRelativeRateHp().getPriority());
        }
        if (comparatorHealingItem.getCmpRelativeRatePp().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpRelativeRatePp().getPriority());
        }
        if (comparatorHealingItem.getCmpDescription().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpDescription().getPriority());
        }
        if (comparatorHealingItem.getCmpNumber().getPriority() != NO_PRIORITY) {
            priorities_.add(comparatorHealingItem.getCmpNumber().getPriority());
        }
        return !priorities_.hasDuplicates();
    }

    @Override
    protected void sort() {
        comparatorHealingItem = new ComparatorHealingItem(ComparatorHealingItem.NB_COMPARATORS,comparatorHealingItem);
        TreeMap<SortingHealingItem, String> items_ = new TreeMap<SortingHealingItem, String>(
                comparatorHealingItem);
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
        return comparatorHealingItem.getCmpName();
    }

    public LgIntFieldComparator getCmpNumber() {
        return comparatorHealingItem.getCmpNumber();
    }

    public StringFieldComparator getCmpDescription() {
        return comparatorHealingItem.getCmpDescription();
    }

    public LongFieldComparator getCmpPrice() {
        return comparatorHealingItem.getCmpPrice();
    }

    public LongFieldComparator getCmpNbHealedStatus() {
        return comparatorHealingItem.getCmpNbHealedStatus();
    }

    public BooleanFieldComparator getCmpRelativeRateHp() {
        return comparatorHealingItem.getCmpRelativeRateHp();
    }

    public RateFieldComparator getCmpHp() {
        return comparatorHealingItem.getCmpHp();
    }

    public RateFieldComparator getCmpRateHp() {
        return comparatorHealingItem.getCmpRateHp();
    }

    public BooleanFieldComparator getCmpRelativeRatePp() {
        return comparatorHealingItem.getCmpRelativeRatePp();
    }

    public RateFieldComparator getCmpPp() {
        return comparatorHealingItem.getCmpPp();
    }

    public BooleanFieldComparator getCmpHealOneMove() {
        return comparatorHealingItem.getCmpHealOneMove();
    }

    public LongFieldComparator getCmpStatistics() {
        return comparatorHealingItem.getCmpStatistics();
    }

    public BooleanFieldComparator getCmpKo() {
        return comparatorHealingItem.getCmpKo();
    }
    protected void excludeResults() {
        Listable<SortingHealingItem> list_ = getItems().getKeys();
        for (SortingHealingItem k: list_) {
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

    TreeMap<SortingHealingItem, String> getItems() {
        return items;
    }

    CustList<SortingHealingItem> getRendered() {
        return rendered;
    }

}
