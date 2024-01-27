package code.scripts.messages.cards;

import code.sml.util.*;

public final class MessagesGuiCards {
    public static final String DIAL_NICK_CST_NICKNAME = "0";
    public static final String DIAL_NICK_NICKNAME_PLAYER = "1";
    public static final String DIAL_NICK_VALIDATE = "2";
    private MessagesGuiCards() {}
    public static TranslationsFile enNickname(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DIAL_NICK_CST_NICKNAME,"Your nickname");
        e_.add(DIAL_NICK_NICKNAME_PLAYER,"Player {0}''s nickname");
        e_.add(DIAL_NICK_VALIDATE,"Validate");
        return e_;
    }

    public static TranslationsFile frNickname(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DIAL_NICK_CST_NICKNAME,"Votre pseudo");
        f_.add(DIAL_NICK_NICKNAME_PLAYER,"Pseudo du joueur {0}");
        f_.add(DIAL_NICK_VALIDATE,"Valider");
        return f_;
    }
}
