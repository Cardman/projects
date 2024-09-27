package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.IntGamePkStream;
import aiki.facade.SexListInt;
import aiki.game.Game;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.*;
import code.stream.StreamTextFile;

public final class DefGamePkStream implements IntGamePkStream {
    private final AbstractProgramInfos programInfos;

    public DefGamePkStream(AbstractProgramInfos _pr) {
        this.programInfos = _pr;
    }

    @Override
    public Game load(String _fileName, SexListInt _sexList) {
        return DocumentReaderAikiCoreUtil.getGame(StreamTextFile.contentsOfFile(_fileName,programInfos.getFileCoreStream(),programInfos.getStreams()),_sexList);
    }

    @Override
    public Game loadThen(String _fileName, SexListInt _sexList) {
        String file_ = StreamTextFile.contentsOfFile(_fileName, programInfos.getFileCoreStream(), programInfos.getStreams());
        return DocumentReaderAikiCoreUtil.getGameOrNull(DocumentBuilder.parseNoTextDocument(file_),_sexList);
    }

    public static Game checkGame(DataBase _data, SexListInt _sexList, Game _game) {
        if (_game == null) {
            return null;
        }
        if (!_game.checkAndInitialize(_data, _sexList)) {
            return null;
        }
        return _game;
    }

    @Override
    public void save(String _fileName, Game _data) {
        StreamTextFile.saveTextFile(_fileName, DocumentWriterAikiCoreUtil.setGame(_data),programInfos.getStreams());
    }
}
