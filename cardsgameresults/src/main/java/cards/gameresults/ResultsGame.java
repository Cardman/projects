package cards.gameresults;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;


public abstract class ResultsGame {

    protected static final String RESULTS_BELOTE = "cards.belote.resultsbelote";
    protected static final String RESULTS_PRESIDENT = "cards.president.resultspresident";
    protected static final String RESULTS_TAROT = "cards.tarot.resultstarot";
    protected static final String RESULTS_PAGE = "resultsPage";
    protected static final String DETAIL_RESULTS_PAGE = "detailResultsPage";
    protected static final String RESOURCES_CLASS_PATH = "resources_cards/classes";
    private String globalResultsPageTitle;
    private String detailResultsTitle;
    private StringMap<String> renderedPages = new StringMap<String>();
    /**Ecarts types des parties cumule&eacute;es*/
    private EqList<Rate> sigmas=new EqList<Rate>();
    /**Sommes des scores des joueurs*/
    private Numbers<Long> sums=new Numbers<Long>();
    private CustList<Numbers<Long>> scores;

    public final String getGlobalResultsPageTitle() {
        return globalResultsPageTitle;
    }

    public final void setGlobalResultsPageTitle(String _globalResultsPageTitle) {
        globalResultsPageTitle = _globalResultsPageTitle;
    }

    public final String getDetailResultsTitle() {
        return detailResultsTitle;
    }

    public final void setDetailResultsTitle(String _detailResultsTitle) {
        detailResultsTitle = _detailResultsTitle;
    }

    public final StringMap<String> getRenderedPages() {
        return renderedPages;
    }

    public final EqList<Rate> getSigmas() {
        return sigmas;
    }

    public final Numbers<Long> getSums() {
        return sums;
    }

    public final CustList<Numbers<Long>> getScores() {
        return scores;
    }

    public final void setScores(CustList<Numbers<Long>> _scores) {
        scores = new CustList<Numbers<Long>>(_scores);
    }

    public abstract void initialize(StringList _pseudos,
            CustList<Numbers<Long>> _scores);

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
