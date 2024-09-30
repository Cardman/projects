package code.converterimages.gui;

import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.CustList;
import code.util.core.StringUtil;

public final class DocumentImagesUtil {
    public static final String ROOT_CONF = "__";
//    public static final String ROOT_CONF1 = "_1";
//    public static final String ROOT_CONF2 = "_2";
    public static final String INFO = "_";
    public static final String INFO_IMP = "0";
    public static final String INFO_EXP = "1";
    public static final String INFO_BASE = "2";
//    public static final String READ_IMAGES = "0";
//    public static final String WRITE_IMAGES = "1";

    private DocumentImagesUtil() {
    }
    public static CustList<String> parse(String _doc) {
        return parse(DocumentBuilder.parseNoTextDocument(_doc));
    }
    public static CustList<String> parse(Document _doc) {
        if (_doc == null) {
            return new CustList<String>();
        }
        if (StringUtil.quickEq(_doc.getDocumentElement().getTagName(),ROOT_CONF)) {
            CustList<String> res_ = new CustList<String>();
//            res_.add(READ_IMAGES);
            res_.add(_doc.getDocumentElement().getAttribute(INFO_IMP));
            res_.add(_doc.getDocumentElement().getAttribute(INFO_EXP));
            res_.add(_doc.getDocumentElement().getAttribute(INFO_BASE));
            return res_;
        }
//        if (StringUtil.quickEq(doc_.getDocumentElement().getTagName(),ROOT_CONF1)) {
//            CustList<String> res_ = new CustList<String>();
//            res_.add(READ_IMAGES);
//            res_.add(doc_.getDocumentElement().getAttribute(INFO_IMP));
//            res_.add(doc_.getDocumentElement().getAttribute(INFO_EXP));
//            return res_;
//        }
//        if (StringUtil.quickEq(doc_.getDocumentElement().getTagName(),ROOT_CONF2)) {
//            CustList<String> res_ = new CustList<String>();
//            res_.add(WRITE_IMAGES);
//            res_.add(doc_.getDocumentElement().getAttribute(INFO_IMP));
//            res_.add(doc_.getDocumentElement().getAttribute(INFO_EXP));
//            return res_;
//        }
        return new CustList<String>();
    }
}
