package aiki.map.pokemon;
import code.util.StringList;
import code.util.pagination.CriteriaForSearching;
import code.util.pagination.SearchingMode;
import code.util.pagination.SelectedBoolean;
import aiki.map.pokemon.enums.Gender;

public final class CriteriaForSearchingPokemon extends CriteriaForSearching {

    private SearchingMode searchModeName;

    private String contentOfName;

    private SearchingMode searchModeAbility;

    private String contentOfAbility;

    private SelectedBoolean withItem = SelectedBoolean.YES_AND_NO;

    private SearchingMode searchModeItem;

    private String contentOfItem;

    private SearchingMode searchModeMove;

    private String contentOfMove;

    private Short minLevel;

    private Short maxLevel;

    private Gender gender;

    private Short minNbPossEvols;

    private Short maxNbPossEvols;

    public boolean matchName(String _name) {
        return match(searchModeName, contentOfName, _name);
    }

    public boolean matchAbility(String _ability) {
        return match(searchModeAbility, contentOfAbility, _ability);
    }

    public boolean matchItem(String _object) {
        if (withItem == SelectedBoolean.YES_AND_NO) {
            return match(searchModeItem, contentOfItem, _object);
        }
        if (withItem == SelectedBoolean.NO) {
            return _object.isEmpty();
        }
        if (_object.isEmpty()) {
            return false;
        }
        return match(searchModeItem, contentOfItem, _object);
    }

    public boolean matchGender(Gender _gender) {
        return match(gender, _gender);
    }

    private static boolean match(Gender _enum, Gender _element) {
        if (_enum == null) {
            return true;
        }
        return _enum == _element;
    }

    public boolean matchLevel(long _level) {
        return CriteriaForSearching.match(minLevel, maxLevel, (short) _level);
    }

    public boolean matchMoves(StringList _moves) {
        return match(searchModeMove, contentOfMove, _moves);
    }

    public boolean matchNbPossEvos(long _level) {
        return CriteriaForSearching.match(minNbPossEvols, maxNbPossEvols, (short) _level);
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

    public SearchingMode getSearchModeAbility() {
        return searchModeAbility;
    }

    public void setSearchModeAbility(SearchingMode _searchModeAbility) {
        searchModeAbility = _searchModeAbility;
    }

    public String getContentOfAbility() {
        return contentOfAbility;
    }

    public void setContentOfAbility(String _contentOfAbility) {
        contentOfAbility = _contentOfAbility;
    }

    public SelectedBoolean getWithItem() {
        return withItem;
    }

    public void setWithItem(SelectedBoolean _withItem) {
        withItem = _withItem;
    }

    public SearchingMode getSearchModeItem() {
        return searchModeItem;
    }

    public void setSearchModeItem(SearchingMode _searchModeItem) {
        searchModeItem = _searchModeItem;
    }

    public String getContentOfItem() {
        return contentOfItem;
    }

    public void setContentOfItem(String _contentOfItem) {
        contentOfItem = _contentOfItem;
    }

    public SearchingMode getSearchModeMove() {
        return searchModeMove;
    }

    public void setSearchModeMove(SearchingMode _searchModeMove) {
        searchModeMove = _searchModeMove;
    }

    public String getContentOfMove() {
        return contentOfMove;
    }

    public void setContentOfMove(String _contentOfMove) {
        contentOfMove = _contentOfMove;
    }

    public Short getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Short _minLevel) {
        minLevel = _minLevel;
    }

    public Short getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Short _maxLevel) {
        maxLevel = _maxLevel;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender _gender) {
        gender = _gender;
    }

    public Short getMinNbPossEvols() {
        return minNbPossEvols;
    }

    public void setMinNbPossEvols(Short _minNbPossEvols) {
        minNbPossEvols = _minNbPossEvols;
    }

    public Short getMaxNbPossEvols() {
        return maxNbPossEvols;
    }

    public void setMaxNbPossEvols(Short _maxNbPossEvols) {
        maxNbPossEvols = _maxNbPossEvols;
    }
}
