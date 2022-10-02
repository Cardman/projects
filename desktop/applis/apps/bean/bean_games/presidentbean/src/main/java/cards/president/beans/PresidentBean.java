package cards.president.beans;

import cards.consts.LineDeal;
import cards.president.ResultsPresident;
import code.bean.Bean;
import code.util.CustList;
import code.util.StringList;

public final class PresidentBean extends Bean {

    private StringList nicknames;

    private CustList<LineDeal> history;
    private CustList<LineDeal> linesDeal;

    private ResultsPresident dataBase;
    public ResultsPresident db() {
        return dataBase;
    }

    public void setDataBase(ResultsPresident _dataBase) {
        dataBase = _dataBase;
    }

    @Override
    public void beforeDisplaying() {
        ResultsPresident res_ = getResults();
        setNicknames(res_.getRes().getNicknames());
        setHistory(res_.getRes().getHistory());
        linesDeal = LineDeal.copy(getHistory());
    }

    public StringList getNicknames() {
        return nicknames;
    }

    public void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
    }

    public CustList<LineDeal> getHistory() {
        return history;
    }

    public void setHistory(CustList<LineDeal> _h) {
        this.history = _h;
    }

    public CustList<LineDeal> getLinesDeal() {
        return linesDeal;
    }

    public ResultsPresident getResults() {
        return db();
    }
}
