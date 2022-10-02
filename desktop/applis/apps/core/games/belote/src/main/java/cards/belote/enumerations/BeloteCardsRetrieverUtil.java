package cards.belote.enumerations;

import code.util.core.StringUtil;

public final class BeloteCardsRetrieverUtil {
    private BeloteCardsRetrieverUtil() {
    }

    public static DeclaresBelote toDeclaresBelote(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"FOUR_1")) {
            return DeclaresBelote.FOUR_1;
        }
        if (StringUtil.quickEq(r_,"FOUR_KING")) {
            return DeclaresBelote.FOUR_KING;
        }
        if (StringUtil.quickEq(r_,"FOUR_QUEEN")) {
            return DeclaresBelote.FOUR_QUEEN;
        }
        if (StringUtil.quickEq(r_,"FOUR_JACK")) {
            return DeclaresBelote.FOUR_JACK;
        }
        if (StringUtil.quickEq(r_,"FOUR_10")) {
            return DeclaresBelote.FOUR_10;
        }
        if (StringUtil.quickEq(r_,"FOUR_9")) {
            return DeclaresBelote.FOUR_9;
        }
        if (StringUtil.quickEq(r_,"FOUR_8")) {
            return DeclaresBelote.FOUR_8;
        }
        if (StringUtil.quickEq(r_,"FOUR_7")) {
            return DeclaresBelote.FOUR_7;
        }
        if (StringUtil.quickEq(r_,"HUNDRED")) {
            return DeclaresBelote.HUNDRED;
        }
        if (StringUtil.quickEq(r_,"FIFTY")) {
            return DeclaresBelote.FIFTY;
        }
        if (StringUtil.quickEq(r_,"THIRTY")) {
            return DeclaresBelote.THIRTY;
        }
        return DeclaresBelote.UNDEFINED;
    }
}
