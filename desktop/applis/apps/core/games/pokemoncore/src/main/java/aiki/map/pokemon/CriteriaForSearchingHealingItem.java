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
import code.util.*;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import code.util.core.StringUtil;

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

    private Statistic statistic = Statistic.NOTHING;

    private SelectedBoolean ko = SelectedBoolean.YES_AND_NO;

    private StringMap<String> translatedStatus;

    public static boolean matchClasses(Item _item) {
        if (_item instanceof Berry) {
            return true;
        }
        return _item instanceof HealingItem;
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
            return healingPp(healing_);
        }
        if (_item instanceof Berry) {
            Berry healing_ = (Berry) _item;
            return berry(healing_);
        }
        if (relativeRatePp == SelectedBoolean.YES_AND_NO) {
            long pp_ = 0;
            return CriteriaForSearching.match(minPp, maxPp, pp_);
        }
        return false;
    }

    private boolean healingPp(HealingPp _healing) {
        if (relativeRatePp == SelectedBoolean.YES) {
            return relativeRatePpYes(_healing);
        }
        if (relativeRatePp == SelectedBoolean.NO) {
            return relativeRatePpNo(_healing);
        }
        if (healOneMove == SelectedBoolean.YES) {
            if (_healing.getHealingMoveFullpp()) {
                return true;
            }
            long pp_ = _healing.getHealedMovePp();
            if (pp_ == 0) {
                return false;
            }
            return CriteriaForSearching.match(minPp, maxPp, pp_);
        }
        if (healOneMove == SelectedBoolean.NO) {
            if (_healing.isHealingAllMovesPp()) {
                return true;
            }
            long pp_ = _healing.getHealingAllMovesFullpp();
            if (pp_ == 0) {
                return false;
            }
            return CriteriaForSearching.match(minPp, maxPp, pp_);
        }
        if (_healing.getHealingMoveFullpp() || _healing.isHealingAllMovesPp()) {
            return true;
        }
        return matchAny(_healing);
    }

    private boolean matchAny(HealingPp _healing) {
        Longs values_ = new Longs();
        values_.add(_healing.getHealedMovePp());
        values_.add(_healing.getHealingAllMovesFullpp());
        values_.removeDuplicates();
        values_.sort();
        for (long v: values_) {
            if (CriteriaForSearching.match(minPp, maxPp, v)) {
                return true;
            }
        }
        return false;
    }

    private boolean relativeRatePpNo(HealingPp _healing) {
        if (healOneMove == SelectedBoolean.YES) {
            long pp_ = _healing.getHealedMovePp();
            if (pp_ == 0) {
                return false;
            }
            return CriteriaForSearching.match(minPp, maxPp, pp_);
        }
        if (healOneMove == SelectedBoolean.NO) {
            long pp_ = _healing.getHealingAllMovesFullpp();
            if (pp_ == 0) {
                return false;
            }
            return CriteriaForSearching.match(minPp, maxPp, pp_);
        }
        Longs values_ = new Longs();
        values_.add(_healing.getHealedMovePp());
        values_.add(_healing.getHealingAllMovesFullpp());
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

    private boolean relativeRatePpYes(HealingPp _healing) {
        if (healOneMove == SelectedBoolean.YES_AND_NO) {
            if (_healing.getHealingMoveFullpp()) {
                return true;
            }
            return _healing.isHealingAllMovesPp();
        }
        if (match(healOneMove, _healing.getHealingMoveFullpp())) {
            return true;
        }
        if (healOneMove == SelectedBoolean.YES) {
            return match(SelectedBoolean.NO, _healing.isHealingAllMovesPp());
        }
        return match(SelectedBoolean.YES, _healing.isHealingAllMovesPp());
    }

    private boolean berry(Berry _healing) {
        long pp_ = _healing.getHealPp();
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

    public boolean matchStatistic(Item _item) {
        if (statistic == Statistic.NOTHING) {
            return true;
        }
        CustList<Statistic> statistics_;
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
        if (StringUtil.quickEq(getSelectedClass(), Item.HEALING_STATUS) && StringUtil.quickEq(Item.HEALING_HP_STATUS, _item.getItemType())) {
            return true;
        }
        return super.matchClass(_item);
    }
}
