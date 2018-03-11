package cards.gameresults.sml;
import cards.gameresults.ResultsGame;
import code.maths.sml.DocumentReaderMathUtil;
import code.sml.DocumentReaderCoreUtil;
import code.sml.Element;
import code.util.StringList;
public final class DocumentReaderCardsResultsUtil {

    private static final String ATTR_FIELD = "field";
    private static final char DOT = '.';
    private static final String FIELD_DETAIL_RESULTS_TITLE = "detailResultsTitle";
    private static final String FIELD_GLOBAL_RESULTS_PAGE_TITLE = "globalResultsPageTitle";
    private static final String FIELD_RENDERED_PAGES = "renderedPages";
    private static final String FIELD_SCORES = "scores";
    private static final String FIELD_SIGMAS = "sigmas";
    private static final String FIELD_SUMS = "sums";


    public static void getResultsGame(ResultsGame _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_GLOBAL_RESULTS_PAGE_TITLE)) {
            _object.setGlobalResultsPageTitle(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DETAIL_RESULTS_TITLE)) {
            _object.setDetailResultsTitle(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RENDERED_PAGES)) {
            _object.setRenderedPages(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SIGMAS)) {
            _object.setSigmas(DocumentReaderMathUtil.getListRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SUMS)) {
            _object.setSums(DocumentReaderCoreUtil.getListLong(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SCORES)) {
            _object.setScores(DocumentReaderCoreUtil.getListListLong(_element));
            return;
        }
    }

}
