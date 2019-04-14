package aiki.facade;
import code.util.CustList;
import code.util.TreeMap;
import code.util.ints.Listable;

public abstract class Pagination<T extends Sorting,U> {

    public static final int NO_PRIORITY = 0;

    public static final int MIN_PRIORITY = 1;

    private int numberPage = CustList.INDEX_NOT_FOUND_ELT;

    private int nbResultsPerPage = 1;

    private int line = CustList.INDEX_NOT_FOUND_ELT;

    private int delta = 1;

    public void clear() {
        getResults().clear();
        setLine(CustList.INDEX_NOT_FOUND_ELT);
        setNumberPage(CustList.INDEX_NOT_FOUND_ELT);
        getRendered().clear();
    }

    protected abstract boolean match(U _object);

    protected abstract boolean sortable();

    protected abstract void sort();

    public void newSearch(){
        Listable<T> list_ = getResults().getKeys();
        for (T k: list_) {
            U value_ = getResults().getVal(k);
            if (match(value_)) {
                continue;
            }
            getResults().removeKey(k);
        }
        checkLine(CustList.INDEX_NOT_FOUND_ELT);
        if (!getResults().isEmpty()) {
            begin();
        } else {
            setNumberPage(CustList.INDEX_NOT_FOUND_ELT);
            getRendered().clear();
        }
    }

    public void checkLine(int _line) {
        if (line == _line) {
            line = CustList.INDEX_NOT_FOUND_ELT;
            return;
        }
        line = _line;
    }

    public int currentIndex() {
        if (line == CustList.INDEX_NOT_FOUND_ELT) {
            return line;
        }
        int index_ = numberPage * nbResultsPerPage + line;
        return getResults().getKey(index_).getIndex();
    }

    public U currentObject() {
        if (line == CustList.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        int index_ = numberPage * nbResultsPerPage + line;
        return getResults().getValue(index_);
    }

    public void changeNbResultsPerPage(int _nbResultsPerPage) {
        nbResultsPerPage = _nbResultsPerPage;
        if (!getRendered().isEmpty()) {
            begin();
        }
    }

    public boolean enabledPrevious() {
        return numberPage > CustList.FIRST_INDEX;
    }

    public void previous() {
        changePage(numberPage - 1);
    }

    public void previousDelta() {
        changePage(Math.max(numberPage - delta, CustList.FIRST_INDEX));
    }

    public void begin() {
        changePage(CustList.FIRST_INDEX);
    }

    public boolean enabledNext() {
        return numberPage + 1 < pages();
    }

    public void next() {
        changePage(numberPage + 1);
    }

    public void nextDelta() {
        changePage(Math.min(numberPage + delta, pages() - 1));
    }

    public void end() {
        changePage(pages() - 1);
    }

    public void changePage(int _page) {
        if (numberPage != _page) {
            line = CustList.INDEX_NOT_FOUND_ELT;
        }
        numberPage = _page;
        calculateRendered();
    }

    public void calculateRendered() {
        Listable<T> list_ = getResults().getKeys();
        int end_ = nbResultsPerPage * (numberPage+1);
        if (end_ > list_.size()) {
            end_ = list_.size();
        }
        getRendered().clear();
        if (end_ < nbResultsPerPage * numberPage) {
            return;
        }
        getRendered().addAllElts(list_.sub(nbResultsPerPage * numberPage, end_));
    }

    public int pages() {
        if (getResults().size() % nbResultsPerPage == 0) {
            return getResults().size() / nbResultsPerPage;
        }
        return getResults().size() / nbResultsPerPage + 1;
    }

    protected abstract TreeMap<T,U> getResults();

    protected abstract CustList<T> getRendered();

    public abstract CriteriaForSearching getCriteria();

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int _numberPage) {
        numberPage = _numberPage;
    }

    public int getNbResultsPerPage() {
        return nbResultsPerPage;
    }

    public void setNbResultsPerPage(int _nbResultsPerPage) {
        nbResultsPerPage = _nbResultsPerPage;
        begin();
    }

    public int getLine() {
        return line;
    }

    public void setLine(int _line) {
        line = _line;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int _delta) {
        delta = _delta;
    }
}
