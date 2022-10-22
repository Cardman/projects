package applications.main;

import aiki.facade.SexListImpl;
import aiki.game.Game;
import aiki.main.AikiFactory;
import aiki.main.LaunchingPokemon;
import aiki.sml.DocumentReaderAikiCoreUtil;
import aiki.sml.LoadingGame;
import applications.gui.WindowApps;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.main.CardFactories;
import cards.main.LaunchingCards;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.minirts.LaunchingDemo;
import code.player.SongList;
import code.player.main.LaunchingPlayer;
import code.renders.LaunchingRenders;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.stream.StreamBinaryFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.converterimages.main.LaunchingConverter;
import code.util.core.StringUtil;

public class LaunchingApplications extends SoftApplicationCore {

    public static final String COORDS = "applications.coords";

    private static final String TEMP_FOLDER = "applications";

    private final CardFactories cardFactories;
    private final AikiFactory aikiFactory;

    public LaunchingApplications(AbstractProgramInfos _frames, CardFactories _cardFactories, AikiFactory _aikiFactory) {
        super(_frames);
        cardFactories = _cardFactories;
        aikiFactory = _aikiFactory;
    }

    protected static void loadLaungage(String[] _args, LaunchingApplications _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    private static WindowApps getWindow(String _lg, AbstractProgramInfos _list, CardFactories _cardFactories, AikiFactory _aikiFactory) {
        return new WindowApps(_lg, _list, _cardFactories, _aikiFactory);
    }

    @Override
    protected final void launch(String _language, String[] _args) {
        StringList args_ = getFile(_args);
        if (!args_.isEmpty()) {
            byte[] bytes_ = StreamBinaryFile.loadFile(args_.first(), getFrames().getStreams());
            if (LaunchingConverter.isBinary(bytes_) && !isZip(bytes_)) {
                AbstractImage img_ = getFrames().getImageFactory().newImageFromBytes(bytes_);
                if (img_ != null) {
                    launchWindow(_language, getFrames(), cardFactories, aikiFactory);
                    LaunchingConverter launch_ = new LaunchingConverter(getFrames());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
            }
            String file_ = StreamTextFile.contentsOfFile(args_.first(), getFrames().getFileCoreStream(), getFrames().getStreams());
            if (file_ == null) {
                return;
            }
            if (DocumentReaderCardsUnionUtil.isContentObject(file_)) {
                launchWindow(_language, getFrames(), cardFactories, aikiFactory);
                LaunchingCards launch_ = new LaunchingCards(getFrames(),cardFactories);
                launch_.launchWithoutLanguage(_language, _args);
                return;
            }
            Game gameOrNull_ = DocumentReaderAikiCoreUtil.getGameOrNull(file_,new SexListImpl());
            LoadingGame loadingGameOrNull_ = DocumentReaderAikiCoreUtil.getLoadingGameOrNull(file_);
            if (loadingGameOrNull_ != null || gameOrNull_ != null) {
                launchWindow(_language, getFrames(), cardFactories, aikiFactory);
                LaunchingPokemon launch_ = new LaunchingPokemon(getFrames(), aikiFactory);
                launch_.launchWithoutLanguage(_language, _args);
                return;
            }
            Document doc_ = DocumentBuilder.parseNoTextDocument(file_);
            if (doc_ != null) {
                if (StringUtil.quickEq("smil",  doc_.getDocumentElement().getTagName())) {
                    SongList list_ = new SongList();
                    list_.addSongs(doc_);
                    launchWindow(_language, getFrames(), cardFactories, aikiFactory);
                    LaunchingPlayer launch_ = new LaunchingPlayer(getFrames());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                launchWindow(_language, getFrames(), cardFactories, aikiFactory);
                LaunchingDemo launch_ = new LaunchingDemo(getFrames());
                launch_.launchWithoutLanguage(_language, _args);
                return;
            }
            if (file_.indexOf('\n') < 0) {
                launchWindow(_language, getFrames(), cardFactories, aikiFactory);
                LaunchingConverter launch_ = new LaunchingConverter(getFrames());
                launch_.launchWithoutLanguage(_language, _args);
                return;
            }
            String readObject_ = StreamTextFile.contentsOfFile(args_.first(), getFrames().getFileCoreStream(), getFrames().getStreams());
            if (readObject_ != null) {
                StringList lines_ = StringUtil.splitStrings(readObject_, "\n", "\r\n");
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
                    launchWindow(_language, getFrames(), cardFactories, aikiFactory);
                    LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFrames());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                String possibleMethod_ = StringExpUtil.removeDottedSpaces(linesFiles_.get(2));
                if (possibleMethod_.startsWith("initDb=")) {
                    launchWindow(_language, getFrames(), cardFactories, aikiFactory);
                    LaunchingRenders launch_ = new LaunchingRenders(getFrames());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                if (possibleMethod_.startsWith("main=")) {
                    launchWindow(_language, getFrames(), cardFactories, aikiFactory);
                    LaunchingFull launch_ = new LaunchingFull(getFrames());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                launchWindow(_language, getFrames(), cardFactories, aikiFactory);
                LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFrames());
                launch_.launchWithoutLanguage(_language, _args);
            }
            return;
        }
        launchWindow(_language, getFrames(), cardFactories, aikiFactory);
    }

    protected StringList getFile(String[] _args) {
        StringList files_ = new StringList();
        if (_args.length > 0) {
            String fileName_ = getFrames().getFileCoreStream().newFile(_args[0]).getAbsolutePath();
            fileName_ = StringUtil.replaceBackSlash(fileName_);
            files_.add(fileName_);
        }
        return files_;
    }
    private static void launchWindow(String _language, AbstractProgramInfos _list, CardFactories _cardFactories, AikiFactory _aikiFactory) {
        TopLeftFrame topLeft_ = loadCoords(getTempFolder(_list),COORDS, _list.getFileCoreStream(), _list.getStreams());
        WindowApps w_ = getWindow(_language, _list, _cardFactories, _aikiFactory);
        setLocation(w_.getCommonFrame(), topLeft_);
    }
    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return getTempFolder(_tmpUserFolderSl,TEMP_FOLDER);
    }

    private static boolean isZip(byte[] _bytes) {
        return _bytes != null && _bytes.length > 3
                && _bytes[0] == (byte)0x50&& _bytes[1] == (byte)0x4b
                && _bytes[2] == (byte)0x03&& _bytes[3] == (byte)0x04;

    }

}
