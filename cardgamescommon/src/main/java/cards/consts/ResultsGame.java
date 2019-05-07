package cards.consts;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringMap;


public final class ResultsGame {
    private String globalResultsPageTitle;
    private String detailResultsTitle;
    private StringMap<String> renderedPages = new StringMap<String>();
    /**Ecarts types des parties cumule&eacute;es*/
    private EqList<Rate> sigmas=new EqList<Rate>();
    /**Sommes des scores des joueurs*/
    private Numbers<Long> sums=new Numbers<Long>();
    private CustList<Numbers<Long>> scores;

    public String getGlobalResultsPageTitle() {
        return globalResultsPageTitle;
    }

    public void setGlobalResultsPageTitle(String _globalResultsPageTitle) {
        globalResultsPageTitle = _globalResultsPageTitle;
    }

    public String getDetailResultsTitle() {
        return detailResultsTitle;
    }

    public void setDetailResultsTitle(String _detailResultsTitle) {
        detailResultsTitle = _detailResultsTitle;
    }

    public StringMap<String> getRenderedPages() {
        return renderedPages;
    }

    public EqList<Rate> getSigmas() {
        return sigmas;
    }

    public Numbers<Long> getSums() {
        return sums;
    }

    public CustList<Numbers<Long>> getScores() {
        return scores;
    }

    public void setScores(CustList<Numbers<Long>> _scores) {
        scores = new CustList<Numbers<Long>>(_scores);
    }

    public void setRenderedPages(StringMap<String> _renderedPages) {
        renderedPages = _renderedPages;
    }

    public void setSigmas(EqList<Rate> _sigmas) {
        sigmas = _sigmas;
    }

    public void setSums(Numbers<Long> _sums) {
        sums = _sums;
    }
}
