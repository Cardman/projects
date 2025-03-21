package code.bean.nat.analyze.blocks;

import code.sml.util.*;
import code.util.*;
import code.util.opers.*;

public final class AnaRendBlockHelp {
    public static final String COMMA = ",";

    private AnaRendBlockHelp() {
    }

    public static TranslationsFile file(String _content) {
        TranslationsFile t_ = new TranslationsFile();
        for (EntryCust<String,String> e: MessagesUtil.getMessages(_content).entryList()) {
            t_.add(e.getKey(),e.getValue());
        }
        return t_;
    }

}
