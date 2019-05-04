package aiki.map.pokemon;
import aiki.db.DataBase;
import aiki.facade.CriteriaForSearching;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;

public final class CriteriaForSearchingMove extends CriteriaForSearching {

    private SearchingMode searchModeName;

    private String contentOfName;

    private SearchingMode searchModeType;

    private String contentOfType;

    private Integer minPrice;

    private Integer maxPrice;

    private String selectedClass = DataBase.EMPTY_STRING;

    private Integer minPp;

    private Integer maxPp;

    private Integer minPrio;

    private Integer maxPrio;

    private TargetChoice targetChoice;

    private SwitchType switchType;

    private SelectedBoolean disappearingRound = SelectedBoolean.YES_AND_NO;

    private SelectedBoolean rechargeRound = SelectedBoolean.YES_AND_NO;

    private SelectedBoolean constUserChoice = SelectedBoolean.YES_AND_NO;

    private SelectedBoolean technicalMove = SelectedBoolean.YES_AND_NO;

    public boolean matchName(String _name) {
        return match(searchModeName, contentOfName, _name);
    }

    public boolean matchTypes(StringList _types) {
        return match(searchModeType, contentOfType, _types);
    }

    public boolean matchPrice(int _price) {
        return CriteriaForSearching.match(minPrice, maxPrice, _price);
    }

    public boolean matchClass(MoveData _item) {
        if (StringList.quickEq(selectedClass, DataBase.EMPTY_STRING)) {
            return true;
        }
        return StringList.quickEq(selectedClass, _item.getMoveType());
    }

    public boolean matchPp(int _pp) {
        return CriteriaForSearching.match(minPp, maxPp, _pp);
    }

    public boolean matchTargetChoice(TargetChoice _choice) {
        return match(targetChoice, _choice);
    }

    private static boolean match(TargetChoice _enum, TargetChoice _element) {
        if (_enum == null) {
            return true;
        }
        return _enum == _element;
    }

    public boolean matchSwitchType(SwitchType _choice) {
        return match(switchType, _choice);
    }

    private static boolean match(SwitchType _enum, SwitchType _element) {
        if (_enum == null) {
            return true;
        }
        return _enum == _element;
    }

    public boolean matchPrio(int _prio) {
        return CriteriaForSearching.match(minPrio, maxPrio, _prio);
    }

    public boolean matchDisappearingRound(boolean _disparitionTour) {
        return match(disappearingRound, _disparitionTour);
    }

    public boolean matchRechargeRound(boolean _rechargeTour) {
        return match(rechargeRound, _rechargeTour);
    }

    public boolean matchConstUserChoice(boolean _blocageLanceur) {
        return match(constUserChoice, _blocageLanceur);
    }

    public boolean matchTechnicalMove(boolean _isTm) {
        return match(technicalMove, _isTm);
    }

    public void setSearchModeName(SearchingMode _searchModeName) {
        searchModeName = _searchModeName;
    }

    public void setContentOfName(String _contentOfName) {
        contentOfName = _contentOfName;
    }

    public void setSearchModeType(SearchingMode _searchModeType) {
        searchModeType = _searchModeType;
    }

    public void setContentOfType(String _contentOfType) {
        contentOfType = _contentOfType;
    }

    public void setMinPrice(Integer _minPrice) {
        minPrice = _minPrice;
    }

    public void setMaxPrice(Integer _maxPrice) {
        maxPrice = _maxPrice;
    }

    public void setSelectedClass(String _selectedClass) {
        selectedClass = _selectedClass;
    }

    public void setMinPp(Integer _minPp) {
        minPp = _minPp;
    }

    public void setMaxPp(Integer _maxPp) {
        maxPp = _maxPp;
    }

    public void setMinPrio(Integer _minPrio) {
        minPrio = _minPrio;
    }

    public void setMaxPrio(Integer _maxPrio) {
        maxPrio = _maxPrio;
    }

    public void setTargetChoice(TargetChoice _targetChoice) {
        targetChoice = _targetChoice;
    }

    public void setSwitchType(SwitchType _switchType) {
        switchType = _switchType;
    }

    public void setDisappearingRound(SelectedBoolean _disappearingRound) {
        disappearingRound = _disappearingRound;
    }

    public void setRechargeRound(SelectedBoolean _rechargeRound) {
        rechargeRound = _rechargeRound;
    }

    public void setConstUserChoice(SelectedBoolean _constUserChoice) {
        constUserChoice = _constUserChoice;
    }

    public void setTechnicalMove(SelectedBoolean _technicalMove) {
        technicalMove = _technicalMove;
    }
}
