package cards.president.beans;

import cards.consts.LineDeal;
import cards.consts.beans.TakerResult;
import cards.president.ResultsPresident;
import code.bean.IntBeanBuilderHelperCommon;
import code.scripts.pages.cards.MessagesPresidentPage;
import code.util.CustList;
import code.util.StringList;

public final class PresidentBean {

    private StringList nicknames;

    private CustList<LineDeal> history;
    private CustList<LineDeal> linesDeal;

    private ResultsPresident dataBase;
    private IntBeanBuilderHelperCommon builder;
    public ResultsPresident db() {
        return dataBase;
    }

    public void setDataBase(ResultsPresident _dataBase) {
        dataBase = _dataBase;
    }

    public void build() {
        beforeDisplaying();
        getBuilder().formatMessage(MessagesPresidentPage.APP_BEAN,"",MessagesPresidentPage.M_RANKS);
        TakerResult.buildScores(getBuilder(),nicknames,linesDeal);
    }

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

    public IntBeanBuilderHelperCommon getBuilder() {
        return builder;
    }

    public void setBuilder(IntBeanBuilderHelperCommon _b) {
        this.builder = _b;
    }
}
