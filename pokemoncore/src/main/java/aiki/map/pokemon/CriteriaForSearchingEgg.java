package aiki.map.pokemon;
import aiki.facade.CriteriaForSearching;
import code.util.pagination.SearchingMode;

public final class CriteriaForSearchingEgg extends CriteriaForSearching {

    private SearchingMode searchModeName;

    private String contentOfName;

    private Integer minSteps;

    private Integer maxSteps;

    public boolean matchName(String _name) {
        return match(searchModeName, contentOfName, _name);
    }

    public boolean matchSteps(int _steps) {
        return CriteriaForSearching.match(minSteps, maxSteps, _steps);
    }

    public void setSearchModeName(SearchingMode _searchModeName) {
        searchModeName = _searchModeName;
    }

    public void setContentOfName(String _contentOfName) {
        contentOfName = _contentOfName;
    }

    public void setMinSteps(Integer _minSteps) {
        minSteps = _minSteps;
    }

    public void setMaxSteps(Integer _maxSteps) {
        maxSteps = _maxSteps;
    }
}
