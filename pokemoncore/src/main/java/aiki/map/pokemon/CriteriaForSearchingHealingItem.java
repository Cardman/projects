package aiki.map.pokemon;
import aiki.facade.CriteriaForSearching;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import code.maths.Rate;
import code.util.EnumList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;

public final class CriteriaForSearchingHealingItem extends CriteriaForSearchingItem {

    private SearchingMode searchModeStatus;

    private String contentOfStatus;

    private SelectedBoolean relativeRateHp = SelectedBoolean.YES_AND_NO;

    private Rate minHp;

    private Rate maxHp;

    private Rate minRateHp;

    private Rate maxRateHp;

    private SelectedBoolean relativeRatePp = SelectedBoolean.YES_AND_NO;

    private SelectedBoolean healOneMove = SelectedBoolean.YES_AND_NO;

    private Long minPp;

    private Long maxPp;

    private Statistic statistic;

    private SelectedBoolean ko = SelectedBoolean.YES_AND_NO;

    private StringMap<String> translatedStatus;

    public static boolean matchClasses(Item _item) {
        if (_item instanceof Berry) {
            return true;
        }
        if (_item instanceof HealingItem) {
            return true;
        }
        return false;
    }

    public boolean matchStatus(Item _item) {
        StringList status_ = getStatus(_item);
        StringList trStatus_ = new StringList();
        for (String s: status_) {
            trStatus_.add(translatedStatus.getVal(s));
        }
        return match(searchModeStatus, contentOfStatus, trStatus_);
    }

    public static StringList getStatus(Item _item) {
        StringList status_;
        if (_item instanceof Berry) {
            status_ = ((Berry)_item).getHealStatus();
            return status_;
        }
        if (_item instanceof HealingStatus) {
            status_ = ((HealingStatus)_item).getStatus();
            return status_;
        }
        status_ = new StringList();
        return status_;
    }

    public boolean matchHp(Item _item) {
        Rate hp_ = Rate.zero();
        if (_item instanceof HealingHp) {
            hp_.addNb(((HealingHp)_item).getHp());
        }
        if (_item instanceof Berry) {
            hp_.addNb(((Berry)_item).getHealHp());
        }
        if (relativeRateHp == SelectedBoolean.YES_AND_NO) {
//            return CriteriaForSearching.<Rate>match(minHp, maxHp, hp_);
            return hp_.inRange(minHp, maxHp);
        }
        if (hp_.isZero()) {
            return false;
        }
        return hp_.inRange(minHp, maxHp);
    }

    public boolean matchRateHp(Item _item) {
        Rate rateHp_ = Rate.zero();
        if (_item instanceof Berry) {
            rateHp_.addNb(((Berry)_item).getHealHpBySuperEffMove());
            rateHp_.addNb(((Berry)_item).getHealHpRate());
        }
        if (_item instanceof HealingHpStatus) {
            HealingHpStatus healingHp_ = (HealingHpStatus) _item;
            rateHp_.addNb(healingHp_.getHealedHpRate());
        }
        if (relativeRateHp == SelectedBoolean.YES_AND_NO) {
            return rateHp_.inRange(minRateHp, maxRateHp);
//            return CriteriaForSearching.<Rate>match(minRateHp, maxRateHp, rateHp_);
        }
        if (rateHp_.isZero()) {
            return false;
        }
        return rateHp_.inRange(minRateHp, maxRateHp);
//        return CriteriaForSearching.<Rate>match(minRateHp, maxRateHp, rateHp_);
    }

    public boolean matchPp(Item _item) {
        if (_item instanceof HealingPp) {
            HealingPp healing_ = (HealingPp) _item;
            if (relativeRatePp == SelectedBoolean.YES) {
                if (healOneMove == SelectedBoolean.YES_AND_NO) {
                    if (healing_.getHealingMoveFullpp()) {
                        return true;
                    }
                    if (healing_.isHealingAllMovesPp()) {
                        return true;
                    }
                    return false;
                }
                if (match(healOneMove, healing_.getHealingMoveFullpp())) {
                    return true;
                }
                return match(healOneMove.neg(), healing_.isHealingAllMovesPp());
            }
            if (relativeRatePp == SelectedBoolean.NO) {
                if (healOneMove == SelectedBoolean.YES) {
                    long pp_ = healing_.getHealedMovePp();
                    if (pp_ == 0) {
                        return false;
                    }
                    if (CriteriaForSearching.match(minPp, maxPp, pp_)) {
                        return true;
                    }
                    return false;
                }
                if (healOneMove == SelectedBoolean.NO) {
                    long pp_ = healing_.getHealingAllMovesFullpp();
                    if (pp_ == 0) {
                        return false;
                    }
                    if (CriteriaForSearching.match(minPp, maxPp, pp_)) {
                        return true;
                    }
                    return false;
                }
                Numbers<Long> values_ = new Numbers<Long>();
                values_.add(healing_.getHealedMovePp());
                values_.add(healing_.getHealingAllMovesFullpp());
                values_.removeDuplicates();
                values_.sort();
                if (values_.last() == 0) {
                    return false;
                }
                for (long v: values_) {
                    if (CriteriaForSearching.match(minPp, maxPp, v)) {
                        return true;
                    }
                }
                return false;
            }
            if (healOneMove == SelectedBoolean.YES) {
                if (healing_.getHealingMoveFullpp()) {
                    return true;
                }
                long pp_ = healing_.getHealedMovePp();
                if (pp_ == 0) {
                    return false;
                }
                if (CriteriaForSearching.match(minPp, maxPp, pp_)) {
                    return true;
                }
                return false;
            }
            if (healOneMove == SelectedBoolean.NO) {
                if (healing_.isHealingAllMovesPp()) {
                    return true;
                }
                long pp_ = healing_.getHealingAllMovesFullpp();
                if (pp_ == 0) {
                    return false;
                }
                if (CriteriaForSearching.match(minPp, maxPp, pp_)) {
                    return true;
                }
                return false;
            }
            if (healing_.getHealingMoveFullpp()) {
                return true;
            }
            if (healing_.isHealingAllMovesPp()) {
                return true;
            }
            Numbers<Long> values_ = new Numbers<Long>();
            values_.add(healing_.getHealedMovePp());
            values_.add(healing_.getHealingAllMovesFullpp());
            values_.removeDuplicates();
            values_.sort();
            for (long v: values_) {
                if (CriteriaForSearching.match(minPp, maxPp, v)) {
                    return true;
                }
            }
            return false;
        }
        if (_item instanceof Berry) {
            Berry healing_ = (Berry) _item;
            long pp_ = healing_.getHealPp();
            if (pp_ == 0) {
                if (relativeRatePp == SelectedBoolean.YES_AND_NO) {
                    return CriteriaForSearching.match(minPp, maxPp, pp_);
                }
                return false;
            }
            if (healOneMove == SelectedBoolean.NO) {
                return false;
            }
            if (relativeRatePp == SelectedBoolean.YES) {
                return false;
            }
            return CriteriaForSearching.match(minPp, maxPp, pp_);
        }
        if (relativeRatePp == SelectedBoolean.YES_AND_NO) {
            long pp_ = 0;
            return CriteriaForSearching.match(minPp, maxPp, pp_);
        }
        return false;
    }

    public boolean matchStatistic(Item _item) {
        if (statistic == null) {
            return true;
        }
        EnumList<Statistic> statistics_;
        if (_item instanceof Berry) {
            statistics_ = ((Berry)_item).getMultStat().getKeys();
            for (Statistic s: statistics_) {
                if (match(statistic,s)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean match(Statistic _enum, Statistic _element) {
        return _enum == _element;
    }

    public boolean matchKo(Item _item) {
        if (_item instanceof HealingStatus) {
            return match(ko, ((HealingStatus)_item).getHealingKo());
        }
        return match(ko, false);
    }

    public void setSearchModeStatus(SearchingMode _searchModeStatus) {
        searchModeStatus = _searchModeStatus;
    }

    public void setContentOfStatus(String _contentOfStatus) {
        contentOfStatus = _contentOfStatus;
    }

    public void setRelativeRateHp(SelectedBoolean _relativeRateHp) {
        relativeRateHp = _relativeRateHp;
    }

    public void setMinHp(Rate _minHp) {
        minHp = _minHp;
    }

    public void setMaxHp(Rate _maxHp) {
        maxHp = _maxHp;
    }

    public void setMinRateHp(Rate _minRateHp) {
        minRateHp = _minRateHp;
    }

    public void setMaxRateHp(Rate _maxRateHp) {
        maxRateHp = _maxRateHp;
    }

    public void setRelativeRatePp(SelectedBoolean _relativeRatePp) {
        relativeRatePp = _relativeRatePp;
    }

    public void setHealOneMove(SelectedBoolean _healOneMove) {
        healOneMove = _healOneMove;
    }

    public void setMinPp(Long _minPp) {
        minPp = _minPp;
    }

    public void setMaxPp(Long _maxPp) {
        maxPp = _maxPp;
    }

    public void setStatistic(Statistic _statistic) {
        statistic = _statistic;
    }

    public void setKo(SelectedBoolean _ko) {
        ko = _ko;
    }

    public void setTranslatedStatus(StringMap<String> _translatedStatus) {
        translatedStatus = _translatedStatus;
    }

    @Override
    public boolean matchClass(Item _item) {
        if (StringList.quickEq(getSelectedClass(), HealingStatus.ITEM)) {
            if (StringList.quickEq(HealingHpStatus.ITEM, _item.getItemType())) {
                return true;
            }
        }
        return super.matchClass(_item);
    }
}
