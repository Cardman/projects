package cards.facade;

import cards.facade.sml.DocumentReaderCardsUnionUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamTextFile;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DefNicknamesCrud extends AbsNicknamesCrudImpl {
    public DefNicknamesCrud(AbstractProgramInfos _p) {
        super(_p);
    }

    @Override
    public Nicknames value() {
        StringMap<String> mess_ = Games.getAppliTr(getProgramInfos().currentLg()).getMapping().getVal(Games.NICK_NAMES).getMapping();
        return DocumentReaderCardsUnionUtil.getNicknames(mess_.getVal(Nicknames.USER),mess_.getVal(Nicknames.NICKNAME),StreamTextFile.contentsOfFile(StringUtil.concat(getTempFolder(),FacadeCards.PLAYERS), getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams()));
    }

    @Override
    public void value(Nicknames _n) {
        setNicknames(_n);
        getNicknames().sauvegarder(StringUtil.concat(getTempFolder(),FacadeCards.PLAYERS),getProgramInfos().getStreams());
    }
}
