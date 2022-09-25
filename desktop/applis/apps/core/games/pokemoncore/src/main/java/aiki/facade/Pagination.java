package aiki.facade;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public abstract class Pagination {

    public static final int NO_PRIORITY = 0;

    public static final int MIN_PRIORITY = 1;

    private int numberPage = IndexConstants.INDEX_NOT_FOUND_ELT;

    private int nbResultsPerPage = 1;

    private int line = IndexConstants.INDEX_NOT_FOUND_ELT;

    private int delta = 1;

    void search() {
        if (!isEmpty()) {
            setNumberPage(IndexConstants.FIRST_INDEX);
        } else {
            setLine(IndexConstants.INDEX_NOT_FOUND_ELT);
            setNumberPage(IndexConstants.INDEX_NOT_FOUND_ELT);
            clearRendered();
            return;
        }
        setLine(IndexConstants.INDEX_NOT_FOUND_ELT);
        if (sortable()) {
            sort();
        }
        calculateRendered();
    }

    protected abstract boolean isEmpty();

    public void clear() {
        clearResults();
        setLine(IndexConstants.INDEX_NOT_FOUND_ELT);
        setNumberPage(IndexConstants.INDEX_NOT_FOUND_ELT);
        clearRendered();
    }

    protected abstract void clearRendered();

    protected abstract boolean sortable();

    protected abstract void sort();

    public void newSearch(){
        excludeResults();
        afterNewSeach();
    }

    protected abstract void excludeResults();

    private void afterNewSeach() {
        checkLine(IndexConstants.INDEX_NOT_FOUND_ELT);
        if (hasResults()) {
            begin();
        } else {
            setNumberPage(IndexConstants.INDEX_NOT_FOUND_ELT);
            clearRendered();
        }
    }

    protected boolean hasResults(){
        return !hasNoResult();
    }

    protected abstract boolean hasNoResult();

    public void checkLine(int _line) {
        if (line == _line) {
            line = IndexConstants.INDEX_NOT_FOUND_ELT;
            return;
        }
        line = _line;
    }

    public int currentIndex() {
        int index_ = getIndex();
        if (!isValidIndex(index_)) {
            return index_;
        }
        return getIndex(index_);
    }

    protected abstract int getIndex(int _index);

    protected abstract boolean isValidIndex(int _index);

    protected int getIndex() {
        if (line == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return line;
        }
        return numberPage * nbResultsPerPage + line;
    }

    public void changeNbResultsPerPage(int _nbResultsPerPage) {
        nbResultsPerPage = _nbResultsPerPage;
        if (hasRendered()) {
            begin();
        }
    }

    protected boolean hasRendered() {
        return !hasNoRendered();
    }

    protected abstract boolean hasNoRendered();

    public boolean enabledPrevious() {
        return numberPage > IndexConstants.FIRST_INDEX;
    }

    public void previous() {
        changePage(numberPage - 1);
    }

    public void previousDelta() {
        changePage(NumberUtil.max(numberPage - delta, IndexConstants.FIRST_INDEX));
    }

    public void begin() {
        changePage(IndexConstants.FIRST_INDEX);
    }

    public boolean enabledNext() {
        return numberPage + 1 < pages();
    }

    public void next() {
        changePage(numberPage + 1);
    }

    public void nextDelta() {
        changePage(NumberUtil.min(numberPage + delta, pages() - 1));
    }

    public void end() {
        changePage(pages() - 1);
    }

    public void changePage(int _page) {
        if (numberPage != _page) {
            line = IndexConstants.INDEX_NOT_FOUND_ELT;
        }
        numberPage = _page;
        calculateRendered();
    }

    public void calculateRendered() {
        int size_ = getResultsSize();
        int end_ = nbResultsPerPage * (numberPage+1);
        if (end_ > size_) {
            end_ = size_;
        }
        clearRendered();
        if (end_ < getFullCount()) {
            return;
        }
        updateRendered(end_);
    }

    protected abstract void updateRendered(int _end);

    protected int getFullCount() {
        return nbResultsPerPage * numberPage;
    }

    public int pages() {
        if (getResultsSize() % nbResultsPerPage == 0) {
            return getResultsSize() / nbResultsPerPage;
        }
        return getResultsSize() / nbResultsPerPage + 1;
    }

    protected abstract void clearResults();
    protected abstract int getResultsSize();


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
