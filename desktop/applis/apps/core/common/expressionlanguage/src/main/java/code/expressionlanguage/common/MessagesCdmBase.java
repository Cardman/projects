package code.expressionlanguage.common;

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
}
