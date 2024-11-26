package cards.facade;

import cards.facade.sml.DocumentReaderCardsUnionUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.stream.StreamTextFile;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DefNicknamesCrud extends AbsNicknamesCrudImpl {
    public DefNicknamesCrud(AbstractProgramInfos _p) {
        super(_p);
    }

    @Override
    public Nicknames value() {
        StringMap<String> mess_ = MessagesCardGames.getAppliTr(getProgramInfos().currentLg()).getMapping().getVal(MessagesCardGames.NICK_NAMES).getMapping();
        return DocumentReaderCardsUnionUtil.getNicknames(mess_.getVal(MessagesCardGames.USER),mess_.getVal(MessagesCardGames.NICKNAME),StreamTextFile.contentsOfFile(StringUtil.concat(getTempFolder(), MessagesCardGames.getAppliFilesTr(getProgramInfos().getTranslations()).val().getMapping().getVal(MessagesCardGames.PLAYERS)), getProgramInfos().getStreams()));
    }

    @Override
    public Nicknames value(TranslationsLg _lg) {
        return new Nicknames(_lg);
    }

    @Override
    public boolean isValidNicknames(Nicknames _n) {
        return _n.isValidNicknames();
    }

    @Override
    public void value(Nicknames _n) {
        setNicknames(_n);
        getNicknames().sauvegarder(StringUtil.concat(getTempFolder(), MessagesCardGames.getAppliFilesTr(getProgramInfos().getTranslations()).val().getMapping().getVal(MessagesCardGames.PLAYERS)),getProgramInfos().getStreams());
    }
}
