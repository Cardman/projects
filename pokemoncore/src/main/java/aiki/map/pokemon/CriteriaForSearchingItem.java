package aiki.map.pokemon;
import aiki.db.DataBase;
import aiki.facade.CriteriaForSearching;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import code.maths.LgInt;
import code.util.StringList;
import code.util.pagination.SearchingMode;

public class CriteriaForSearchingItem extends CriteriaForSearching {

    private SearchingMode searchModeName;

    private String contentOfName;

    private SearchingMode searchModeDescription;

    private String contentOfDescription;

    private Integer minPrice;

    private Integer maxPrice;

    private LgInt minNumber;

    private LgInt maxNumber;

    private String selectedClass = DataBase.EMPTY_STRING;

    public boolean matchName(String _name) {
        return match(searchModeName, contentOfName, _name);
    }

    public boolean matchDescription(String _description) {
        return match(searchModeDescription, contentOfDescription, _description);
    }

    public boolean matchPrice(int _price) {
        return CriteriaForSearching.match(minPrice, maxPrice, _price);
    }

    public boolean matchNumber(LgInt _number) {
//        return CriteriaForSearching.<LgInt>match(minNumber, maxNumber, _number);
        return _number.inRange(minNumber, maxNumber);
    }

    protected String getSelectedClass() {
        return selectedClass;
    }

    public boolean matchClass(Item _item) {
        if (StringList.quickEq(selectedClass, DataBase.EMPTY_STRING)) {
            return true;
        }
        if (StringList.quickEq(selectedClass, HealingItem.ITEM)) {
            if (StringList.quickEq(HealingPp.ITEM, _item.getItemType())) {
                return true;
            }
            if (StringList.quickEq(HealingHp.ITEM, _item.getItemType())) {
                return true;
            }
            if (StringList.quickEq(HealingStatus.ITEM, _item.getItemType())) {
                return true;
            }
            if (StringList.quickEq(HealingHpStatus.ITEM, _item.getItemType())) {
                return true;
            }
        }
        return StringList.quickEq(selectedClass, _item.getItemType());
    }

    public SearchingMode getSearchModeName() {
        return searchModeName;
    }

    public void setSearchModeName(SearchingMode _searchModeName) {
        searchModeName = _searchModeName;
    }

    public String getContentOfName() {
        return contentOfName;
    }

    public void setContentOfName(String _contentOfName) {
        contentOfName = _contentOfName;
    }

    public SearchingMode getSearchModeDescription() {
        return searchModeDescription;
    }

    public void setSearchModeDescription(SearchingMode _searchModeDescription) {
        searchModeDescription = _searchModeDescription;
    }

    public String getContentOfDescription() {
        return contentOfDescription;
    }

    public void setContentOfDescription(String _contentOfDescription) {
        contentOfDescription = _contentOfDescription;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer _minPrice) {
        minPrice = _minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer _maxPrice) {
        maxPrice = _maxPrice;
    }

    public LgInt getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(LgInt _minNumber) {
        minNumber = _minNumber;
    }

    public LgInt getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(LgInt _maxNumber) {
        maxNumber = _maxNumber;
    }

    public void setSelectedClass(String _selectedClass) {
        selectedClass = _selectedClass;
    }
}
