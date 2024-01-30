package cards.facade;

import cards.belote.*;
import cards.belote.sml.*;
import cards.president.*;
import cards.president.sml.*;
import cards.tarot.*;
import cards.tarot.sml.*;
import code.gui.initialize.*;
import code.sml.*;
import code.stream.*;

public final class DefCardGamesCrud extends AbsCardGamesCrudImpl {
    public DefCardGamesCrud(AbstractProgramInfos _p) {
        super(_p);
    }

    @Override
    public GameBelote belote(String _k) {
        return belote(_k,DocumentBuilder.parseSax(StreamTextFile.contentsOfFile(_k,getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams())));
    }

    @Override
    public GameBelote belote(String _k, Document _d) {
        return DocumentReaderBeloteUtil.getGameBelote(_d);
    }

    @Override
    public void belote(String _k, GameBelote _n) {
        getBelote().clear();
        StreamTextFile.saveTextFile(_k,DocumentWriterBeloteUtil.setGameBelote(_n),getProgramInfos().getStreams());
    }

    @Override
    public GamePresident president(String _k) {
        return president(_k,DocumentBuilder.parseSax(StreamTextFile.contentsOfFile(_k,getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams())));
    }

    @Override
    public GamePresident president(String _k, Document _d) {
        return DocumentReaderPresidentUtil.getGamePresident(_d);
    }

    @Override
    public void president(String _k, GamePresident _n) {
        getPresident().clear();
        StreamTextFile.saveTextFile(_k,DocumentWriterPresidentUtil.setGamePresident(_n),getProgramInfos().getStreams());
    }

    @Override
    public GameTarot tarot(String _k) {
        return tarot(_k,DocumentBuilder.parseSax(StreamTextFile.contentsOfFile(_k,getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams())));
    }

    @Override
    public GameTarot tarot(String _k, Document _d) {
        return DocumentReaderTarotUtil.getGameTarot(_d);
    }

    @Override
    public void tarot(String _k, GameTarot _n) {
        getTarot().clear();
        StreamTextFile.saveTextFile(_k,DocumentWriterTarotUtil.setGameTarot(_n),getProgramInfos().getStreams());
    }

    @Override
    public String read(String _k) {
        return StreamTextFile.contentsOfFile(_k, getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams());
    }

    @Override
    public Document parse(String _k, String _c) {
        return DocumentBuilder.parseSax(_c);
    }

    @Override
    public String tag(String _k, String _c,Document _d) {
        if (_d == null) {
            return "";
        }
        return _d.getDocumentElement().getTagName();
    }
}
