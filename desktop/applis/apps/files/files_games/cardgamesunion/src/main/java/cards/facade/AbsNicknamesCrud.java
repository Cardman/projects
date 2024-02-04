package cards.facade;

import code.sml.util.TranslationsLg;

public interface AbsNicknamesCrud {
    Nicknames value();
    Nicknames value(TranslationsLg _lg);
    void value(Nicknames _n);

    boolean isValidNicknames(Nicknames _n);
}
