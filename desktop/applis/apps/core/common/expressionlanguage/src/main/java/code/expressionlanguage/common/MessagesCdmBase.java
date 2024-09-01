package code.expressionlanguage.common;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.linkage.RelativePathUtil;
import code.sml.util.*;
import code.util.*;

public final class MessagesCdmBase {
    public static final String FILE = "arg_parser";
    public static final String UNICODE = "0";
    public static final String NEW_LINE = "1";
    public static final String SPACE = "2";
    public static final String TAB = "3";
    public static final String CHARACTER = "4";
    public static final String DIGITS_SUPP = "5";
    public static final String EXT = ".html";
    public static final String CST_ANCHOR = "a";
    public static final String CST_BOLD = "b";
    public static final String CST_ITALIC = "i";
    public static final String CST_TABLE = "table";
    public static final String CST_SPAN = "span";
    public static final String CLASS_ERROR = "e";
    public static final String CLASS_WARNING = "w";
    public static final String CLASS_STRING = "s";
    public static final String CLASS_COMMENT = "c";
    public static final String CLASS_IMPORT = "i";
    public static final String CLASS_TYPE = "t";
    public static final String LT = "&lt;";
    public static final String GT = "&gt;";
    public static final String AMP = "&amp;";
    public static final String QUOT = "&quot;";
    public static final String TITLE = "title";
    public static final String HREF = "href";
    public static final String NAME = "name";
    public static final String CLASS = "class";
    public static final String COLOR = "color";
    public static final String BACKGROUND_COLOR = "background-" + COLOR;
    public static final String BLACK = "black";
    public static final String WHITE = "white";
    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";
    public static final String YELLOW = "yellow";
    public static final String GREY = "grey";
    public static final String ORANGE = "orange";
    public static final String GOLD = "gold";
    public static final String SILVER = "silver";
    public static final String LIGHT_GREEN = "lightgreen";
    public static final String LIGHT_YELLOW = "lightyellow";
    public static final String DARK_BLUE = "darkblue";
    public static final String AZURE = "#0080ff";
    public static final String FULL = "f";
    public static final String PARTIAL = "p";
    public static final String NONE = "n";
    public static final String FULL_INIT = "g";
    public static final String PARTIAL_INIT = "q";
    public static final String FULL_2 = "f2";
    public static final String FULL_INIT_2 = "g2";
    public static final String PARTIAL_2 = "p2";
    public static final String PARTIAL_INIT_2 = "q2";
    public static final String NONE_2 = "n2";
    public static final String HEAD = "head";
    public static final String BODY = "body";
    public static final String PRE = "pre";
    public static final String CSS = "css/style.css";
    public static final String TAG_TR = "tr";
    public static final String TAG_TD = "td";
    public static final String HTML = "html";

    private MessagesCdmBase() {
    }
    public static StringMap<String> valMessages(TranslationsAppli _a) {
        return _a.getMapping().getVal(FILE).getMapping();
    }
    public static TranslationsAppli update(TranslationsAppli _a){
        _a.getMapping().addEntry(FILE,ms());
        return _a;
    }
    public static TranslationsFile ms(){
        TranslationsFile en_ = new TranslationsFile();
        en_.add(UNICODE,"u");
        en_.add(NEW_LINE,"n");
        en_.add(SPACE,"e");
        en_.add(TAB,"t");
        en_.add(CHARACTER,"c");
        en_.add(DIGITS_SUPP,"ABCDEF");
        return en_;
    }

    public static String link(FileBlock _file) {
        return "<link"+ ExportCst.DEF_SPACE+ ExportCst.HREF_ATTR+ ExportCst.DEL_ATTR+ RelativePathUtil.relativize(_file, CSS)+ ExportCst.DEL_ATTR+ ExportCst.DEF_SPACE+"rel"+ExportCst.SEP_KEY_VALUE+ ExportCst.DEL_ATTR+"stylesheet"+ ExportCst.DEL_ATTR+ ExportCst.DEF_SPACE+"type"+ExportCst.SEP_KEY_VALUE+ ExportCst.DEL_ATTR+"text/css"+ ExportCst.DEL_ATTR+"/>";
    }

    public static String encode(boolean _encode) {
        if (_encode) {
            return "<meta"+ ExportCst.DEF_SPACE+"content"+ExportCst.SEP_KEY_VALUE+ ExportCst.DEL_ATTR+"text/html;"+ ExportCst.DEF_SPACE+"charset=UTF-8"+ ExportCst.DEL_ATTR+ ExportCst.DEF_SPACE+"http-equiv"+ExportCst.SEP_KEY_VALUE+ ExportCst.DEL_ATTR+"content-type"+ ExportCst.DEL_ATTR+"/>";
        }
        return ExportCst.EMPTY;
    }
}
