package code.stream;

import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.Node;
import code.stream.core.TechStreams;
import code.util.CustList;
import code.util.core.StringUtil;

public final class StreamLanguageUtil {
    private static final String LOCALE = "locale";

    private static final String PARAMETERS = "parameters";

    private static final String LANGUAGE = "langue.xml";

    private StreamLanguageUtil() {
    }

    public static String tryToGetXmlLanguage(String _dir, TechStreams _tech, CustList<String> _lgs) {
        Node noeud_ = StreamTextFile.contenuDocumentXmlExterne(lg(_dir), _tech);
        if (noeud_ == null) {
            return "";
        }
        for(Element e: noeud_.getChildElements()){
            if(StringUtil.quickEq(e.getTagName(),LOCALE)){
                String code_ = e.getAttribute(LOCALE);
                if (StringUtil.contains(_lgs, code_)) {
                    return StringUtil.nullToEmpty(code_);
                }
                return "";
            }
        }
        return "";
    }

    public static boolean saveLanguage(String _folder, String _locale,TechStreams _str) {
        if (_locale.isEmpty()) {
            return false;
        }
        Document document_= DocumentBuilder.newXmlDocument();
        Element info_=document_.createElement(PARAMETERS);
        Element infoPart_ = document_.createElement(LOCALE);
        infoPart_.setAttribute(LOCALE, _locale);
        info_.appendChild(infoPart_);
        document_.appendChild(info_);
        return StreamTextFile.saveTextFile(lg(_folder), document_.export(),_str);
    }

    static String lg(String _f) {
        return StringUtil.concat(_f, StreamTextFile.SEPARATEUR, LANGUAGE);
    }
}