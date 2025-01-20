package aiki.map.pokemon;
import aiki.db.DataBase;
import aiki.facade.CriteriaForSearching;
import aiki.fight.items.Item;
import code.maths.LgInt;
import aiki.facade.enums.SearchingMode;
import code.util.core.StringUtil;

public class CriteriaForSearchingItem extends CriteriaForSearching {

    private SearchingMode searchModeName;

    private String contentOfName;

    private SearchingMode searchModeDescription;

    private String contentOfDescription;

    private Long minPrice;

    private Long maxPrice;

    private LgInt minNumber;

    private LgInt maxNumber;

    private String selectedClass = DataBase.EMPTY_STRING;

    public boolean matchName(String _name) {
        return match(searchModeName, contentOfName, _name);
    }

    public boolean matchDescription(String _description) {
        return match(searchModeDescription, contentOfDescription, _description);
    }

    public boolean matchPrice(long _price) {
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
        if (StringUtil.quickEq(selectedClass, DataBase.EMPTY_STRING)) {
            return true;
        }
        if (StringUtil.quickEq(selectedClass, Item.HEALING_ITEM)) {
            if (StringUtil.quickEq(Item.HEALING_PP, _item.getItemType())) {
                return true;
            }
            if (StringUtil.quickEq(Item.HEALING_HP, _item.getItemType())) {
                return true;
            }
            if (StringUtil.quickEq(Item.HEALING_STATUS, _item.getItemType())) {
                return true;
            }
            if (StringUtil.quickEq(Item.HEALING_HP_STATUS, _item.getItemType())) {
                return true;
            }
        }
        return StringUtil.quickEq(selectedClass, _item.getItemType());
    }

    public void setSearchModeName(SearchingMode _searchModeName) {
        searchModeName = _searchModeName;
    }

    public void setContentOfName(String _contentOfName) {
        contentOfName = _contentOfName;
    }

    public void setSearchModeDescription(SearchingMode _searchModeDescription) {
        searchModeDescription = _searchModeDescription;
    }

    public void setContentOfDescription(String _contentOfDescription) {
        contentOfDescription = _contentOfDescription;
    }

    public void setMinPrice(Long _minPrice) {
        minPrice = _minPrice;
    }

    public void setMaxPrice(Long _maxPrice) {
        maxPrice = _maxPrice;
    }

    public void setMinNumber(LgInt _minNumber) {
        minNumber = _minNumber;
    }

    public void setMaxNumber(LgInt _maxNumber) {
        maxNumber = _maxNumber;
    }

    public void setSelectedClass(String _selectedClass) {
        selectedClass = _selectedClass;
    }
}
