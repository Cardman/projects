package cards.gameresults.sml;
import cards.belote.ResultsBelote;
import cards.president.ResultsPresident;
import cards.tarot.ResultsTarot;
import code.resources.ResourceFiles;
import code.sml.DocumentBuilder;
import code.sml.maths.DocumentReaderMathUtil;
import code.sml.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringList;
import code.util.StringMap;
import code.util.opers.MessagesUtil;

public final class DocumentReaderCardsResultsUtil {

    private static final String RESULTS_BELOTE = "cards.belote.resultsbelote";
    private static final String RESULTS_PRESIDENT = "cards.president.resultspresident";
    private static final String RESULTS_TAROT = "cards.tarot.resultstarot";
    private static final String RESULTS_PAGE = "resultsPage";
    private static final String DETAIL_RESULTS_PAGE = "detailResultsPage";
    private static final String RESOURCES_CLASS_PATH = "resources_cards/classes";

    private static final String FIELD_DETAIL_RESULTS_TITLE = "detailResultsTitle";
    private static final String FIELD_GLOBAL_RESULTS_PAGE_TITLE = "globalResultsPageTitle";
    private static final String FIELD_RENDERED_PAGES = "renderedPages";
    private static final String FIELD_SCORES = "scores";
    private static final String FIELD_SIGMAS = "sigmas";
    private static final String FIELD_SUMS = "sums";
    private static final String TAB = "\t";


    public static void setMessages(ResultsBelote _r, String _loc) {
        _r.setLoc(_loc);
        StringMap<String> messages_ = new StringMap<String>();
        messages_ = getMessagesFromLocaleClass(RESOURCES_CLASS_PATH, _loc, RESULTS_BELOTE);
        _r.setGlobalResultsPageTitle(messages_.getVal(RESULTS_PAGE));
        _r.setDetailResultsTitle(messages_.getVal(DETAIL_RESULTS_PAGE));
    }

    public static void setMessages(ResultsPresident _r, String _loc) {
        _r.setLoc(_loc);
        StringMap<String> messages_ = new StringMap<String>();
        messages_ = getMessagesFromLocaleClass(RESOURCES_CLASS_PATH, _loc, RESULTS_PRESIDENT);
        _r.setGlobalResultsPageTitle(messages_.getVal(RESULTS_PAGE));
        _r.setDetailResultsTitle(messages_.getVal(DETAIL_RESULTS_PAGE));
    }

    public static void setMessages(ResultsTarot _r, String _loc) {
        _r.setLoc(_loc);
        StringMap<String> messages_ = new StringMap<String>();
        messages_ = getMessagesFromLocaleClass(RESOURCES_CLASS_PATH, _loc, RESULTS_TAROT);
        _r.setGlobalResultsPageTitle(messages_.getVal(RESULTS_PAGE));
        _r.setDetailResultsTitle(messages_.getVal(DETAIL_RESULTS_PAGE));
    }
    private static StringMap<String> getMessagesFromLocaleClass(String _folder, String _loc, String _class) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(_folder, _loc, _class);
        return getMessagesFromLocale(fileName_, _loc);
    }

    private static StringMap<String> getMessagesFromLocale(String _fileName, String _loc) {
        String loadedResourcesMessages_ = ResourceFiles.ressourceFichier(_fileName);
        if (loadedResourcesMessages_.isEmpty()) {
            return new StringMap<String>();
        }
        StringMap<String> messages_ = MessagesUtil.getMessages(loadedResourcesMessages_);
        for (String k: messages_.getKeys()) {
            if (k.startsWith(TAB)) {
                continue;
            }
            String value_ = messages_.getVal(k);
            messages_.put(k, DocumentBuilder.transformSpecialChars(value_));
        }
        return messages_;
    }
    public static void getResultsGame(ResultsBelote _object, String _fieldName, Element _element) {
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

    public static void getResultsGame(ResultsPresident _object, String _fieldName, Element _element) {
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
    public static void getResultsGame(ResultsTarot _object, String _fieldName, Element _element) {
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
