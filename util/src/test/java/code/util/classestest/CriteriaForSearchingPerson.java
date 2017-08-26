package code.util.classestest;
import code.util.pagination.CriteriaForSearching;
import code.util.pagination.SearchingMode;

public final class CriteriaForSearchingPerson extends CriteriaForSearching {

    private SearchingMode searchModeFirstName;

    private String contentOfFirstName;

    private SearchingMode searchModeLastName;

    private String contentOfLastName;

    private Integer minAge;

    private Integer maxAge;

    private Sex sex;

    public boolean matchFirstName(String _firstName) {
        return match(searchModeFirstName, contentOfFirstName, _firstName);
    }

    public boolean matchLastName(String _lastName) {
        return match(searchModeLastName, contentOfLastName, _lastName);
    }

    public boolean matchSex(Sex _sex) {
        return match(sex, _sex);
    }

    private static boolean match(Sex _enum, Sex _element) {
        if (_enum == null) {
            return true;
        }
        return _enum == _element;
    }

    public boolean matchAge(int _age) {
        return CriteriaForSearching.match(minAge, maxAge, _age);
    }

    public SearchingMode getSearchModeFirstName() {
        return searchModeFirstName;
    }

    public String getContentOfFirstName() {
        return contentOfFirstName;
    }

    public void setContentOfFirstName(String _contentOfFirstName) {
        contentOfFirstName = _contentOfFirstName;
    }

    public SearchingMode getSearchModeLastName() {
        return searchModeLastName;
    }

    public void setSearchModeLastName(SearchingMode _searchModeLastName) {
        searchModeLastName = _searchModeLastName;
    }

    public String getContentOfLastName() {
        return contentOfLastName;
    }

    public void setContentOfLastName(String _contentOfLastName) {
        contentOfLastName = _contentOfLastName;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer _minAge) {
        minAge = _minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer _maxAge) {
        maxAge = _maxAge;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex _sex) {
        sex = _sex;
    }

    public void setSearchModeFirstName(SearchingMode _searchModeFirstName) {
        searchModeFirstName = _searchModeFirstName;
    }
}
