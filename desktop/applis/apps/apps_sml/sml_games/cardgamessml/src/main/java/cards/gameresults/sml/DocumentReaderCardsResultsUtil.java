package cards.gameresults.sml;
import cards.consts.ResultsGame;
import code.sml.maths.DocumentReaderMathUtil;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.util.core.StringUtil;

public final class DocumentReaderCardsResultsUtil {

    private DocumentReaderCardsResultsUtil() {
    }


    public static void getResultsGame(String _fieldName, Element _element, ResultsGame _res) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsResultsUtil.FIELD_NICKNAMES)) {
            _res.setNicknames(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsResultsUtil.FIELD_USER)) {
            _res.setUser(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
//        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsResultsUtil.FIELD_LOC)) {
//            _res.setLoc(DocumentReaderCoreUtil.getString(_element));
//            return;
//        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsResultsUtil.FIELD_GLOBAL_RESULTS_PAGE_TITLE)) {
            _res.setGlobalResultsPageTitle(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsResultsUtil.FIELD_DETAIL_RESULTS_TITLE)) {
            _res.setDetailResultsTitle(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsResultsUtil.FIELD_RENDERED_PAGES)) {
            _res.setRenderedPages(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsResultsUtil.FIELD_SIGMAS)) {
            _res.setSigmas(DocumentReaderMathUtil.getListRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterCardsResultsUtil.FIELD_SUMS)) {
            _res.setSums(DocumentReaderCoreUtil.getListLong(_element));
            return;
        }
        _res.setScores(DocumentReaderCoreUtil.getListListLong(_element));
    }

}
