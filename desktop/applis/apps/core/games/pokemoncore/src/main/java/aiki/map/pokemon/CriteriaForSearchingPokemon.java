package aiki.map.pokemon;
import aiki.facade.CriteriaForSearching;
import aiki.map.pokemon.enums.Gender;
import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;

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

    private Long minLevel;

    private Long maxLevel;

    private Gender gender = Gender.NONE;

    private Long minNbPossEvols;

    private Long maxNbPossEvols;

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
        if (_enum == Gender.NONE) {
            return true;
        }
        return _enum == _element;
    }

    public boolean matchLevel(long _level) {
        return CriteriaForSearching.match(minLevel, maxLevel, _level);
    }

    public boolean matchMoves(StringList _moves) {
        return match(searchModeMove, contentOfMove, _moves);
    }

    public boolean matchNbPossEvos(long _level) {
        return CriteriaForSearching.match(minNbPossEvols, maxNbPossEvols, _level);
    }

    public void setSearchModeName(SearchingMode _searchModeName) {
        searchModeName = _searchModeName;
    }

    public void setContentOfName(String _contentOfName) {
        contentOfName = _contentOfName;
    }

    public void setSearchModeAbility(SearchingMode _searchModeAbility) {
        searchModeAbility = _searchModeAbility;
    }

    public void setContentOfAbility(String _contentOfAbility) {
        contentOfAbility = _contentOfAbility;
    }

    public void setWithItem(SelectedBoolean _withItem) {
        withItem = _withItem;
    }

    public void setSearchModeItem(SearchingMode _searchModeItem) {
        searchModeItem = _searchModeItem;
    }

    public void setContentOfItem(String _contentOfItem) {
        contentOfItem = _contentOfItem;
    }

    public void setSearchModeMove(SearchingMode _searchModeMove) {
        searchModeMove = _searchModeMove;
    }

    public void setContentOfMove(String _contentOfMove) {
        contentOfMove = _contentOfMove;
    }

    public void setMinLevel(Long _minLevel) {
        minLevel = _minLevel;
    }

    public void setMaxLevel(Long _maxLevel) {
        maxLevel = _maxLevel;
    }

    public void setGender(Gender _gender) {
        gender = _gender;
    }

    public void setMinNbPossEvols(Long _minNbPossEvols) {
        minNbPossEvols = _minNbPossEvols;
    }

    public void setMaxNbPossEvols(Long _maxNbPossEvols) {
        maxNbPossEvols = _maxNbPossEvols;
    }
}
