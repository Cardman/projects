package applications.main;

import aiki.game.Game;
import aiki.main.AikiFactory;
import aiki.main.LaunchingPokemon;
import aiki.sml.DocumentReaderAikiCoreUtil;
import aiki.sml.LoadingGame;
import applications.gui.WindowApps;
import cards.belote.GameBelote;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.main.CardFactories;
import cards.main.LaunchingCards;
import cards.president.GamePresident;
import cards.tarot.GameTarot;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.guicompos.GuiFactroy;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.images.BaseSixtyFourUtil;
import code.minirts.LaunchingDemo;
import code.player.SongList;
import code.player.main.LaunchingPlayer;
import code.renders.LaunchingRenders;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.stream.StreamBinaryFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;
import code.converterimages.main.LaunchingConverter;
import code.util.core.StringUtil;

public class LaunchingApplications extends SoftApplicationCore {

    public static final String COORDS = "applications.coords";

    private static final String TEMP_FOLDER = "applications";

    private final CardFactories cardFactories;
    private final AikiFactory aikiFactory;
    private final GuiFactroy guiFactory;

    public LaunchingApplications(AbstractProgramInfos _frames, CardFactories _cardFactories, AikiFactory _aikiFactory, GuiFactroy _guiFactory) {
        super(_frames);
        cardFactories = _cardFactories;
        aikiFactory = _aikiFactory;
        guiFactory = _guiFactory;
    }

    protected static void loadLaungage(String[] _args, LaunchingApplications _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    private static WindowApps getWindow(String _lg, AbstractProgramInfos _list, CardFactories _cardFactories, AikiFactory _aikiFactory, GuiFactroy _guiFact) {
        return new WindowApps(_lg, _list, _cardFactories, _aikiFactory, _guiFact);
    }

    @Override
    protected final void launch(String _language, StringMap<Object> _args) {
        if (!_args.isEmpty()) {
            Object readObject_ = _args.values().first();
            boolean isCardGameSave_ = false;
            if (readObject_ instanceof GameBelote) {
                isCardGameSave_ = true;
            } else if (readObject_ instanceof GameTarot) {
                isCardGameSave_ = true;
            } else if (readObject_ instanceof GamePresident) {
                isCardGameSave_ = true;
            }
            if (isCardGameSave_) {
                launchWindow(_language, getFrames(), cardFactories, aikiFactory, guiFactory);
                LaunchingCards launch_ = new LaunchingCards(getFrames(),cardFactories);
                launch_.launchWithoutLanguage(_language, _args);
            } else if (readObject_ instanceof LoadingGame || readObject_ instanceof Game) {
                launchWindow(_language, getFrames(), cardFactories, aikiFactory, guiFactory);
                LaunchingPokemon launch_ = new LaunchingPokemon(getFrames(), aikiFactory);
                launch_.launchWithoutLanguage(_language, _args);
            } else if (readObject_ instanceof SongList) {
                launchWindow(_language, getFrames(), cardFactories, aikiFactory, guiFactory);
                LaunchingPlayer launch_ = new LaunchingPlayer(getFrames());
                launch_.launchWithoutLanguage(_language, _args);
            } else if (readObject_ instanceof Document) {
                launchWindow(_language, getFrames(), cardFactories, aikiFactory, guiFactory);
                LaunchingDemo launch_ = new LaunchingDemo(getFrames());
                launch_.launchWithoutLanguage(_language, _args);
            } else if (readObject_ instanceof AbstractImage || readObject_ instanceof int[][]) {
                launchWindow(_language, getFrames(), cardFactories, aikiFactory, guiFactory);
                LaunchingConverter launch_ = new LaunchingConverter(getFrames());
                launch_.launchWithoutLanguage(_language, _args);
            } else if (readObject_ instanceof String) {
                String fileContent_ = (String) readObject_;
                StringList lines_ = StringUtil.splitStrings(fileContent_, "\n", "\r\n");
                StringList linesFiles_ = new StringList();
                for (String s: lines_) {
                    if (s.trim().isEmpty()) {
                        continue;
                    }
                    linesFiles_.add(s.trim());
                }
                if (linesFiles_.size() < 2) {
                    return;
                }
                if (linesFiles_.size() < 3) {
                    launchWindow(_language, getFrames(), cardFactories, aikiFactory, guiFactory);
                    LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFrames());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                String possibleMethod_ = StringExpUtil.removeDottedSpaces(linesFiles_.get(2));
                if (possibleMethod_.startsWith("initDb=")) {
                    launchWindow(_language, getFrames(), cardFactories, aikiFactory, guiFactory);
                    LaunchingRenders launch_ = new LaunchingRenders(getFrames());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                if (possibleMethod_.startsWith("main=")) {
                    launchWindow(_language, getFrames(), cardFactories, aikiFactory, guiFactory);
                    LaunchingFull launch_ = new LaunchingFull(getFrames(), guiFactory);
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                launchWindow(_language, getFrames(), cardFactories, aikiFactory, guiFactory);
                LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFrames());
                launch_.launchWithoutLanguage(_language, _args);
            }
            return;
        }
        launchWindow(_language, getFrames(), cardFactories, aikiFactory, guiFactory);
    }

    private static void launchWindow(String _language, AbstractProgramInfos _list, CardFactories _cardFactories, AikiFactory _aikiFactory, GuiFactroy _guiFact) {
        TopLeftFrame topLeft_ = loadCoords(getTempFolder(_list),COORDS, _list.getFileCoreStream(), _list.getStreams());
        WindowApps w_ = getWindow(_language, _list, _cardFactories, _aikiFactory, _guiFact);
        setLocation(w_, topLeft_);
    }
    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return getTempFolder(_tmpUserFolderSl,TEMP_FOLDER);
    }

    private static boolean isZip(byte[] _bytes) {
        return _bytes != null && _bytes.length > 3
                && _bytes[0] == (byte)0x50&& _bytes[1] == (byte)0x4b
                && _bytes[2] == (byte)0x03&& _bytes[3] == (byte)0x04;

    }
    @Override
    public Object getObject(String _fileName) {
        byte[] bytes_ = StreamBinaryFile.loadFile(_fileName, getFrames().getFileCoreStream(), getFrames().getStreams());
        if (LaunchingConverter.isBinary(bytes_) && !isZip(bytes_)) {
            AbstractImage img_ = getFrames().readImg(_fileName);
            if (img_ != null) {
                return img_;
            }
        }
        String file_ = StreamTextFile.contentsOfFile(_fileName, getFrames().getFileCoreStream(), getFrames().getStreams());
        if (file_ == null) {
            return null;
        }
        Object game_ = DocumentReaderCardsUnionUtil.getContentObject(file_);
        if (game_ != null) {
            return game_;
        }
        Object o_ = DocumentReaderAikiCoreUtil.getGameOrNull(file_);
        if (o_ != null) {
            return o_;
        }
        LoadingGame loadingGame_ = DocumentReaderAikiCoreUtil.getLoadingGameOrNull(file_);
        if (loadingGame_ == null) {
            Document doc_ = DocumentBuilder.parseNoTextDocument(file_);
            if (doc_ != null) {
                if (StringUtil.quickEq("smil",  doc_.getDocumentElement().getTagName())) {
                    SongList list_ = new SongList();
                    list_.addSongs(doc_);
                    return list_;
                }
                return doc_;
            }
            if (file_.indexOf('\n') < 0) {
                return BaseSixtyFourUtil.getImageByString(file_);
            }
            return file_;
        }
        return loadingGame_;
    }
}
