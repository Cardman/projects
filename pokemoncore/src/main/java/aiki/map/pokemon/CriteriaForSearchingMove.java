package aiki.map.pokemon;
import code.util.StringList;
import code.util.pagination.CriteriaForSearching;
import code.util.pagination.SearchingMode;
import code.util.pagination.SelectedBoolean;
import aiki.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;

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

    private Integer minPrepaRound;

    private Integer maxPrepaRound;

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
        return CriteriaForSearching.match(targetChoice, _choice);
    }

    public boolean matchSwitchType(SwitchType _choice) {
        return CriteriaForSearching.match(switchType, _choice);
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

    public SearchingMode getSearchModeType() {
        return searchModeType;
    }

    public void setSearchModeType(SearchingMode _searchModeType) {
        searchModeType = _searchModeType;
    }

    public String getContentOfType() {
        return contentOfType;
    }

    public void setContentOfType(String _contentOfType) {
        contentOfType = _contentOfType;
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

    public void setSelectedClass(String _selectedClass) {
        selectedClass = _selectedClass;
    }

    public Integer getMinPp() {
        return minPp;
    }

    public void setMinPp(Integer _minPp) {
        minPp = _minPp;
    }

    public Integer getMaxPp() {
        return maxPp;
    }

    public void setMaxPp(Integer _maxPp) {
        maxPp = _maxPp;
    }

    public Integer getMinPrio() {
        return minPrio;
    }

    public void setMinPrio(Integer _minPrio) {
        minPrio = _minPrio;
    }

    public Integer getMaxPrio() {
        return maxPrio;
    }

    public void setMaxPrio(Integer _maxPrio) {
        maxPrio = _maxPrio;
    }

    public TargetChoice getTargetChoice() {
        return targetChoice;
    }

    public void setTargetChoice(TargetChoice _targetChoice) {
        targetChoice = _targetChoice;
    }

    public SwitchType getSwitchType() {
        return switchType;
    }

    public void setSwitchType(SwitchType _switchType) {
        switchType = _switchType;
    }

    public Integer getMinPrepaRound() {
        return minPrepaRound;
    }

    public void setMinPrepaRound(Integer _minPrepaRound) {
        minPrepaRound = _minPrepaRound;
    }

    public Integer getMaxPrepaRound() {
        return maxPrepaRound;
    }

    public void setMaxPrepaRound(Integer _maxPrepaRound) {
        maxPrepaRound = _maxPrepaRound;
    }

    public SelectedBoolean getDisappearingRound() {
        return disappearingRound;
    }

    public void setDisappearingRound(SelectedBoolean _disappearingRound) {
        disappearingRound = _disappearingRound;
    }

    public SelectedBoolean getRechargeRound() {
        return rechargeRound;
    }

    public void setRechargeRound(SelectedBoolean _rechargeRound) {
        rechargeRound = _rechargeRound;
    }

    public SelectedBoolean getConstUserChoice() {
        return constUserChoice;
    }

    public void setConstUserChoice(SelectedBoolean _constUserChoice) {
        constUserChoice = _constUserChoice;
    }

    public SelectedBoolean getTechnicalMove() {
        return technicalMove;
    }

    public void setTechnicalMove(SelectedBoolean _technicalMove) {
        technicalMove = _technicalMove;
    }
}
