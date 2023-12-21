package cards.gameresults.sml;
import cards.consts.ResultsGame;
import code.sml.maths.DocumentWriterMathUtil;
import code.sml.Document;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;

public final class DocumentWriterCardsResultsUtil {

    public static final String FIELD_DETAIL_RESULTS_TITLE = "0";
    public static final String FIELD_GAME = "1";
    public static final String FIELD_GLOBAL_RESULTS_PAGE_TITLE = "2";
    public static final String FIELD_LOC = "3";
    public static final String FIELD_NICKNAMES = "4";
    public static final String FIELD_RENDERED_PAGES = "5";
    public static final String FIELD_SCORES = "6";
    public static final String FIELD_SIGMAS = "7";
    public static final String FIELD_SUMS = "8";
    public static final String FIELD_USER = "9";
    private DocumentWriterCardsResultsUtil() {
    }

    public static void setResultsGame(Element _element, Document _document, ResultsGame _res) {
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_res.getNicknames(),FIELD_NICKNAMES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_res.getUser(),FIELD_USER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_res.getLoc(),FIELD_LOC,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_res.getGlobalResultsPageTitle(),FIELD_GLOBAL_RESULTS_PAGE_TITLE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_res.getDetailResultsTitle(),FIELD_DETAIL_RESULTS_TITLE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_res.getRenderedPages(),FIELD_RENDERED_PAGES,_document));
        _element.appendChild(DocumentWriterMathUtil.setListRate(_res.getSigmas(),FIELD_SIGMAS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListLong(_res.getSums(),FIELD_SUMS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListListLong(_res.getScores(),FIELD_SCORES,_document));
    }

}
