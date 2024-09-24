package cards.facade;

import cards.belote.*;
import cards.belote.sml.*;
import cards.president.*;
import cards.president.sml.*;
import cards.solitaire.AbsDealSolitaire;
import cards.solitaire.sml.DocumentReaderSolitaireUtil;
import cards.solitaire.sml.DocumentWriterSolitaireUtil;
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
    public HandBelote belote() {
        return sanitize(DocumentReaderBeloteUtil.getHandBelote(StreamTextFile.contentsOfFile(
                FacadeCards.beloteStack(getTempFolder(), getProgramInfos()), getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams())));
    }

    @Override
    public void belote(HandBelote _h) {
        setHandBelote(_h);
        StreamTextFile.saveTextFile(
                FacadeCards.beloteStack(getTempFolder(), getProgramInfos()),
                DocumentWriterBeloteUtil.setHandBelote(getHandBelote()), getProgramInfos().getStreams());
    }

    @Override
    public HandBelote belote24() {
        return sanitize(DocumentReaderBeloteUtil.getHandBelote(StreamTextFile.contentsOfFile(
                FacadeCards.beloteStack24(getTempFolder(), getProgramInfos()), getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams())));
    }

    @Override
    public void belote24(HandBelote _h) {
        setHandBeloteShort(_h);
        StreamTextFile.saveTextFile(
                FacadeCards.beloteStack24(getTempFolder(), getProgramInfos()),
                DocumentWriterBeloteUtil.setHandBelote(getHandBeloteShort()), getProgramInfos().getStreams());
    }

    @Override
    public HandPresident president(int _nbStack) {
        return sanitize(_nbStack,DocumentReaderPresidentUtil.getHandPresident(StreamTextFile.contentsOfFile(
          FacadeCards.presidentStack(getTempFolder(),_nbStack, getProgramInfos()),getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams())));
    }

    @Override
    public void president(int _nbStack, HandPresident _h) {
        getHandPresident().put(Integer.toString(_nbStack),_h);
        StreamTextFile.saveTextFile(
                FacadeCards.presidentStack(getTempFolder(),_nbStack, getProgramInfos()),
                DocumentWriterPresidentUtil.setHandPresident(_h), getProgramInfos().getStreams());
    }

    @Override
    public HandTarot tarot() {
        return sanitize(DocumentReaderTarotUtil.getHandTarot(StreamTextFile.contentsOfFile(
                FacadeCards.tarotStack(getTempFolder(), getProgramInfos()), getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams())));
    }

    @Override
    public void tarot(HandTarot _h) {
        setHandTarot(_h);
        StreamTextFile.saveTextFile(
                FacadeCards.tarotStack(getTempFolder(), getProgramInfos()),
                DocumentWriterTarotUtil.setHandTarot(getHandTarot()), getProgramInfos().getStreams());
    }

    public static HandBelote sanitize(HandBelote _h) {
        if (_h.validStack()) {
            return _h;
        }
        return HandBelote.pileBase();
    }

    public static HandPresident sanitize(int _nb,HandPresident _h) {
        if (_h.validStack(_nb)) {
            return _h;
        }
        return HandPresident.stack(_nb);
    }

    public static HandTarot sanitize(HandTarot _h) {
        if (_h.validStack()) {
            return _h;
        }
        return HandTarot.pileBase();
    }
    @Override
    public GameBelote belote(String _k) {
        return belote(_k,DocumentBuilder.parseNoTextDocument(StreamTextFile.contentsOfFile(_k,getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams())));
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
        return president(_k,DocumentBuilder.parseNoTextDocument(StreamTextFile.contentsOfFile(_k,getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams())));
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
        return tarot(_k,DocumentBuilder.parseNoTextDocument(StreamTextFile.contentsOfFile(_k,getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams())));
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
    public AbsDealSolitaire solitaire(String _k) {
        return solitaire(_k,DocumentBuilder.parseNoTextDocument(StreamTextFile.contentsOfFile(_k,getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams())));
    }

    @Override
    public AbsDealSolitaire solitaire(String _k, Document _d) {
        return DocumentReaderSolitaireUtil.getGameSolitaire(_d);
    }

    @Override
    public void solitaire(String _k, AbsDealSolitaire _n) {
        getSolitaire().clear();
        StreamTextFile.saveTextFile(_k, DocumentWriterSolitaireUtil.setGameSolitaire(_n),getProgramInfos().getStreams());
    }

    @Override
    public String read(String _k) {
        return StreamTextFile.contentsOfFile(_k, getProgramInfos().getFileCoreStream(), getProgramInfos().getStreams());
    }

    @Override
    public Document parse(String _k, String _c) {
        return DocumentBuilder.parseNoTextDocument(_c);
    }

    @Override
    public String tag(String _k, String _c,Document _d) {
        if (_d == null) {
            return "";
        }
        return _d.getDocumentElement().getTagName();
    }
}
