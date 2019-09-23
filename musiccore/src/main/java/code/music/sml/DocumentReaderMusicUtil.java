package code.music.sml;
import code.music.core.EvolvedNote;
import code.music.core.EvolvedPart;
import code.music.core.EvolvedPhrase;
import code.music.core.EvolvedScore;
import code.music.enums.Gamme;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.util.CollCapacity;
import code.util.EqList;
import code.util.StringList;
public final class DocumentReaderMusicUtil {
    private static final String PARTS = "parts";
    private static final String NOTES = "notes";
    private static final String DENOMINATOR = "denominator";
    private static final String NUMERATOR = "numerator";
    private static final String NAME = "name";
    private static final String CHANNEL = "channel";
    private static final String INSTRUMENT = "instrument";
    private static final String PHRASES = "phrases";
    private static final String DYNAMIC = "dynamic";
    private static final String DURATION_DEN = "durationDen";
    private static final String DURATION_NUM = "durationNum";
    private static final String PAUSE = "pause";
    private static final String DIESE = "diese";
    private static final String LEVEL = "level";
    private static final String VALUE = "value";
    private static final String FIELD = "field";

    private static EvolvedNote getEvolvedNote(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EvolvedNote object_ = new EvolvedNote();
        object_.setValue(Gamme.SOL);
        for (Element c: childElements_) {
            getEvolvedNote(object_,c.getAttribute(FIELD),c);
        }
        object_.afterLoad();
        return object_;
    }

    private static void getEvolvedNote(EvolvedNote _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, VALUE)) {
            _object.setValue(getGamme(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, LEVEL)) {
            _object.setLevel(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, DIESE)) {
            _object.setDiese(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, PAUSE)) {
            _object.setPause(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, DURATION_NUM)) {
            _object.setDurationNum(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, DURATION_DEN)) {
            _object.setDurationDen(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, DYNAMIC)) {
            _object.setDynamic(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static EvolvedPart getEvolvedPart(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EvolvedPart object_ = new EvolvedPart();
        for (Element c: childElements_) {
            getEvolvedPart(object_,c.getAttribute(FIELD),c);
        }
        object_.afterLoad();
        return object_;
    }

    private static void getEvolvedPart(EvolvedPart _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, PHRASES)) {
            _object.setPhrases(getListEvolvedPhrase(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, INSTRUMENT)) {
            _object.setInstrument(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, CHANNEL)) {
            _object.setChannel(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, NUMERATOR)) {
            _object.setNumerator(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, DENOMINATOR)) {
            _object.setDenominator(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static EvolvedPhrase getEvolvedPhrase(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EvolvedPhrase object_ = new EvolvedPhrase();
        for (Element c: childElements_) {
            getEvolvedPhrase(object_,c.getAttribute(FIELD),c);
        }
        object_.afterLoad();
        return object_;
    }

    private static void getEvolvedPhrase(EvolvedPhrase _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, NOTES)) {
            _object.setNotes(getListEvolvedNote(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, NUMERATOR)) {
            _object.setNumerator(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, DENOMINATOR)) {
            _object.setDenominator(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    public static EvolvedScore getEvolvedScore(String _string) {
        Document doc_ = DocumentBuilder.parseSax(_string);
        return getEvolvedScore(doc_.getDocumentElement());
    }

    public static EvolvedScore getEvolvedScore(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EvolvedScore object_ = new EvolvedScore();
        for (Element c: childElements_) {
            getEvolvedScore(object_,c.getAttribute(FIELD),c);
        }
        object_.afterLoad();
        return object_;
    }

    private static void getEvolvedScore(EvolvedScore _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, PARTS)) {
            _object.setParts(getListEvolvedPart(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static Gamme getGamme(Element _elt) {
        for (Gamme e: Gamme.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(VALUE))) {
                return e;
            }
        }
        return null;
    }

    private static EqList<EvolvedNote> getListEvolvedNote(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<EvolvedNote> list_ = new EqList<EvolvedNote>(cap_);
        for (Element c: childElements_) {
            list_.add(getEvolvedNote(c));
        }
        return list_;
    }

    private static EqList<EvolvedPart> getListEvolvedPart(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<EvolvedPart> list_ = new EqList<EvolvedPart>(cap_);
        for (Element c: childElements_) {
            list_.add(getEvolvedPart(c));
        }
        return list_;
    }

    private static EqList<EvolvedPhrase> getListEvolvedPhrase(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<EvolvedPhrase> list_ = new EqList<EvolvedPhrase>(cap_);
        for (Element c: childElements_) {
            list_.add(getEvolvedPhrase(c));
        }
        return list_;
    }

}
