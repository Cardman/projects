package cards.gameresults.sml;
import cards.belote.ResultsBelote;
import cards.president.ResultsPresident;
import cards.tarot.ResultsTarot;
import code.sml.maths.DocumentWriterMathUtil;
import code.sml.Document;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
public final class DocumentWriterCardsResultsUtil {

    private static final String FIELD_DETAIL_RESULTS_TITLE = "detailResultsTitle";
    private static final String FIELD_GLOBAL_RESULTS_PAGE_TITLE = "globalResultsPageTitle";
    private static final String FIELD_RENDERED_PAGES = "renderedPages";
    private static final String FIELD_SCORES = "scores";
    private static final String FIELD_SIGMAS = "sigmas";
    private static final String FIELD_SUMS = "sums";

    public static void setResultsGame(ResultsBelote _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getGlobalResultsPageTitle(),FIELD_GLOBAL_RESULTS_PAGE_TITLE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getDetailResultsTitle(),FIELD_DETAIL_RESULTS_TITLE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getRenderedPages(),FIELD_RENDERED_PAGES,_document));
        _element.appendChild(DocumentWriterMathUtil.setListRate(_object.getSigmas(),FIELD_SIGMAS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListLong(_object.getSums(),FIELD_SUMS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListListLong(_object.getScores(),FIELD_SCORES,_document));
    }

    public static void setResultsGame(ResultsPresident _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getGlobalResultsPageTitle(),FIELD_GLOBAL_RESULTS_PAGE_TITLE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getDetailResultsTitle(),FIELD_DETAIL_RESULTS_TITLE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getRenderedPages(),FIELD_RENDERED_PAGES,_document));
        _element.appendChild(DocumentWriterMathUtil.setListRate(_object.getSigmas(),FIELD_SIGMAS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListLong(_object.getSums(),FIELD_SUMS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListListLong(_object.getScores(),FIELD_SCORES,_document));
    }

    public static void setResultsGame(ResultsTarot _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getGlobalResultsPageTitle(),FIELD_GLOBAL_RESULTS_PAGE_TITLE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getDetailResultsTitle(),FIELD_DETAIL_RESULTS_TITLE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getRenderedPages(),FIELD_RENDERED_PAGES,_document));
        _element.appendChild(DocumentWriterMathUtil.setListRate(_object.getSigmas(),FIELD_SIGMAS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListLong(_object.getSums(),FIELD_SUMS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListListLong(_object.getScores(),FIELD_SCORES,_document));
    }
}
