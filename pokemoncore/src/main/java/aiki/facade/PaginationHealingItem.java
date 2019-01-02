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
import code.util.EnumMap;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;

public final class PaginationHealingItem extends
        Pagination<SortingHealingItem, String> {

    private StringFieldComparator cmpName = new StringFieldComparator();

    private StringFieldComparator cmpDescription = new StringFieldComparator();

    private LongFieldComparator cmpPrice = new LongFieldComparator();

    private LongFieldComparator cmpNbHealedStatus = new LongFieldComparator();

    private BooleanFieldComparator cmpRelativeRateHp = new BooleanFieldComparator();

    private FieldCustComparator<Rate> cmpHp = new FieldCustComparator<Rate>();

    private FieldCustComparator<Rate> cmpRateHp = new FieldCustComparator<Rate>();

    private BooleanFieldComparator cmpRelativeRatePp = new BooleanFieldComparator();

    private FieldCustComparator<Rate> cmpPp = new FieldCustComparator<Rate>();

    private BooleanFieldComparator cmpHealOneMove = new BooleanFieldComparator();

    private LongFieldComparator cmpStatistics = new LongFieldComparator();

    private BooleanFieldComparator cmpKo = new BooleanFieldComparator();

    private FieldCustComparator<LgInt> cmpNumber = new FieldCustComparator<LgInt>();

    private StringMap<String> translatedItem;

    private StringMap<String> translatedDescription;

    private StringMap<String> translatedStatus;

    private EnumMap<Statistic, String> translatedStatistics;

    private Inventory inventory;

    private final int nbComparators = 13;

    private TreeMap<SortingHealingItem, String> items = new TreeMap<SortingHealingItem, String>(
            new ComparatorHealingItem());

    private EqList<SortingHealingItem> rendered = new EqList<SortingHealingItem>();

    private CriteriaForSearchingHealingItem criteria;

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

    public void search(Listable<String> _list, DataBase _data) {
        items.clear();
        Numbers<Short> pps_ = new Numbers<Short>();
        for (MoveData f : _data.getMoves().values()) {
            pps_.add(f.getPp());
        }
        int maxPp_ = pps_.getMaximum();
        int len_ = _list.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            Item i_ = _data.getItem(_list.get(i));
            if (!(i_ instanceof HealingItem)) {
                if (!(i_ instanceof Berry)) {
                    continue;
                }
            }
            String description_ = translatedDescription
                    .getVal(i_.getItemType());
            if (!getCriteria().matchPrice(i_.getPrice())) {
                continue;
            }
            if (!getCriteria().matchDescription(description_)) {
                continue;
            }
            if (!getCriteria().matchHp(i_)) {
                continue;
            }
            if (!getCriteria().matchRateHp(i_)) {
                continue;
            }
            if (!getCriteria().matchKo(i_)) {
                continue;
            }
            if (!getCriteria().matchPp(i_)) {
                continue;
            }
            if (!getCriteria().matchStatus(i_)) {
                continue;
            }
            if (!getCriteria().matchStatistic(i_)) {
                continue;
            }
            if (!getCriteria().matchClass(i_)) {
                continue;
            }
            if (!getCriteria().matchNumber(inventory.getNumber(_list.get(i)))) {
                continue;
            }
            if (!match(_list.get(i))) {
                continue;
            }
            SortingHealingItem s_ = new SortingHealingItem();
            s_.setIndex(i);
            s_.setNumber(new LgInt(inventory.getNumber(_list.get(i))));
            s_.setKeyName(_list.get(i));
            s_.setName(translatedItem.getVal(_list.get(i)));
            Rate hp_ = Rate.zero();
            Rate hpRate_ = Rate.zero();
            if (i_ instanceof HealingHp) {
                hp_.addNb(((HealingHp) i_).getHp());
                s_.setRelativeRateHp(false);
            }
            if (i_ instanceof Berry) {
                hpRate_.addNb(((Berry) i_).getHealHpBySuperEffMove());
                hpRate_.addNb(((Berry) i_).getHealHpRate());
                hp_.addNb(((Berry) i_).getHealHp());
            }
            /*
             * if (i_ instanceof Berry) {
             * hp_.affect(((Berry)i_).getHealHpBySuperEffMove());
             * s_.setRelativeRateHp(true); if (hp_.isZero()) {
             * hp_.affect(((Berry)i_).getHealHp()); s_.setRelativeRateHp(false);
             * if (hp_.isZero()) { hp_.affect(((Berry)i_).getHealHpRate());
             * s_.setRelativeRateHp(true); } } }
             */
            if (i_ instanceof HealingHpStatus) {
                HealingHpStatus healingHp_ = (HealingHpStatus) i_;
                hpRate_.addNb(healingHp_.getHealedHpRate());
                s_.setRelativeRateHp(true);
            }
            s_.setPp(Rate.zero());
            long pp_ = 0;
            if (i_ instanceof HealingPp) {
                HealingPp healing_ = (HealingPp) i_;
                if (healing_.getHealingMoveFullpp()) {
                    pp_ = maxPp_;
                    s_.setHealOneMove(true);
                }
                if (healing_.isHealingAllMovesPp()) {
                    pp_ = maxPp_;
                    s_.setHealOneMove(false);
                }
                if (pp_ == 0) {
                    s_.setRelativeRatePp(false);
                    pp_ = healing_.getHealedMovePp();
                    if (pp_ == 0) {
                        s_.setHealOneMove(false);
                        pp_ = healing_.getHealingAllMovesFullpp();
                    } else {
                        s_.setHealOneMove(true);
                    }
                } else {
                    s_.setRelativeRatePp(true);
                }
                s_.setPp(new Rate(pp_));
            }
            if (i_ instanceof Berry) {
                Berry healing_ = (Berry) i_;
                if (healing_.getHealPp() > 0) {
                    s_.setHealOneMove(true);
                    s_.setRelativeRatePp(false);
                    s_.setPp(new Rate(healing_.getHealPp()));
                }
            }
            /*
             * if (!hp_.isZero()) { s_.setHp(hp_); }
             */
            s_.setHp(hp_);
            s_.setHpRate(hpRate_);
            s_.setNbHealedStatus(0);
            if (i_ instanceof HealingStatus) {
                HealingStatus healing_ = (HealingStatus) i_;
                s_.setNbHealedStatus(healing_.getStatus().size());
            }
            if (i_ instanceof Berry) {
                Berry healing_ = (Berry) i_;
                s_.setNbHealedStatus(healing_.getHealStatus().size());
            }
            for (String status_ : CriteriaForSearchingHealingItem.getStatus(i_)) {
                s_.getStatus().add(translatedStatus.getVal(status_));
            }
            s_.setNbStatistics(0);
            if (i_ instanceof Berry) {
                Berry healing_ = (Berry) i_;
                s_.setNbStatistics(healing_.getMultStat().size());
                for (Statistic statis_ : healing_.getMultStat().getKeys()) {
                    s_.getStatistics()
                            .add(translatedStatistics.getVal(statis_));
                }
            }
            s_.setKo(false);
            if (i_ instanceof HealingStatus) {
                HealingStatus healing_ = (HealingStatus) i_;
                s_.setKo(healing_.getHealingKo());
            }
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
    public CriteriaForSearchingHealingItem getCriteria() {
        return criteria;
    }
    @Override
    protected boolean match(String _item) {
        if (!getCriteria().matchName(_item)) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean sortable() {
        Numbers<Integer> priorities_;
        priorities_ = new Numbers<Integer>();
        if (cmpPrice.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpPrice.getPriority());
        }
        if (cmpName.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpName.getPriority());
        }
        if (cmpHealOneMove.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpHealOneMove.getPriority());
        }
        if (cmpHp.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpHp.getPriority());
        }
        if (cmpRateHp.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpRateHp.getPriority());
        }
        if (cmpStatistics.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpStatistics.getPriority());
        }
        if (cmpKo.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpKo.getPriority());
        }
        if (cmpNbHealedStatus.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpNbHealedStatus.getPriority());
        }
        if (cmpPp.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpPp.getPriority());
        }
        if (cmpRelativeRateHp.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpRelativeRateHp.getPriority());
        }
        if (cmpRelativeRatePp.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpRelativeRatePp.getPriority());
        }
        if (cmpDescription.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpDescription.getPriority());
        }
        if (cmpNumber.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpNumber.getPriority());
        }
        int size_ = priorities_.size();
        priorities_.removeDuplicates();
        return size_ == priorities_.size();
    }

    @Override
    protected void sort() {
        // TreeMap<SortingHealingItem, String> items_ = new TreeMap<new>(new
        // Comparator<SortingHealingItem>() {
        // @Override
        // public int compare(SortingHealingItem _o1, SortingHealingItem _o2) {
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
        // } else if (cmpHealOneMove.getPriority() == i) {
        // int res_ = cmpHealOneMove.compare(_o1.isHealOneMove(),
        // _o2.isHealOneMove());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpHp.getPriority() == i) {
        // int res_ = cmpHp.compare(_o1.getHp(), _o2.getHp());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpRateHp.getPriority() == i) {
        // int res_ = cmpRateHp.compare(_o1.getHpRate(), _o2.getHpRate());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpKo.getPriority() == i) {
        // int res_ = cmpKo.compare(_o1.isKo(), _o2.isKo());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpStatistics.getPriority() == i) {
        // int res_ = cmpStatistics.compare(_o1.getNbStatistics(),
        // _o2.getNbStatistics());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpNumber.getPriority() == i) {
        // int res_ = cmpNumber.compare(_o1.getNumber(), _o2.getNumber());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpNbHealedStatus.getPriority() == i) {
        // int res_ = cmpNbHealedStatus.compare(_o1.getNbHealedStatus(),
        // _o2.getNbHealedStatus());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpPp.getPriority() == i) {
        // int res_ = cmpPp.compare(_o1.getPp(), _o2.getPp());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpRelativeRateHp.getPriority() == i) {
        // int res_ = cmpRelativeRateHp.compare(_o1.isRelativeRateHp(),
        // _o2.isRelativeRateHp());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpRelativeRatePp.getPriority() == i) {
        // int res_ = cmpRelativeRatePp.compare(_o1.isRelativeRatePp(),
        // _o2.isRelativeRatePp());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // } else if (cmpDescription.getPriority() == i) {
        // int res_ = cmpDescription.compare(_o1.getItemClass(),
        // _o2.getItemClass());
        // if (res_ != EQUALS_ELEMENTS) {
        // return res_;
        // }
        // }
        // }
        // return Integer.compare(_o1.getIndex(), _o2.getIndex());
        // }
        // });
        TreeMap<SortingHealingItem, String> items_ = new TreeMap<SortingHealingItem, String>(
                new ComparatorHealingItem(cmpName, cmpDescription, cmpPrice,
                        cmpNbHealedStatus, cmpRelativeRateHp, cmpHp, cmpRateHp,
                        cmpRelativeRatePp, cmpPp, cmpHealOneMove,
                        cmpStatistics, cmpKo, cmpNumber, nbComparators));
        items_.putAllTreeMap(items);
        items = items_;
    }

    public StringFieldComparator getCmpName() {
        return cmpName;
    }

    public FieldCustComparator<LgInt> getCmpNumber() {
        return cmpNumber;
    }

    public StringFieldComparator getCmpDescription() {
        return cmpDescription;
    }

    public LongFieldComparator getCmpPrice() {
        return cmpPrice;
    }

    public LongFieldComparator getCmpNbHealedStatus() {
        return cmpNbHealedStatus;
    }

    public BooleanFieldComparator getCmpRelativeRateHp() {
        return cmpRelativeRateHp;
    }

    public FieldCustComparator<Rate> getCmpHp() {
        return cmpHp;
    }

    public FieldCustComparator<Rate> getCmpRateHp() {
        return cmpRateHp;
    }

    public BooleanFieldComparator getCmpRelativeRatePp() {
        return cmpRelativeRatePp;
    }

    public FieldCustComparator<Rate> getCmpPp() {
        return cmpPp;
    }

    public BooleanFieldComparator getCmpHealOneMove() {
        return cmpHealOneMove;
    }

    public LongFieldComparator getCmpStatistics() {
        return cmpStatistics;
    }

    public BooleanFieldComparator getCmpKo() {
        return cmpKo;
    }

    public int getNbComparators() {
        return nbComparators;
    }

    @Override
    protected TreeMap<SortingHealingItem, String> getResults() {
        return items;
    }

    @Override
    protected EqList<SortingHealingItem> getRendered() {
        return rendered;
    }

}
