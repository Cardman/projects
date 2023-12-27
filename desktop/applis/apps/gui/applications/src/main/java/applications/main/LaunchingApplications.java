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
import code.converterimages.main.LaunchingConverter;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.AppFactories;
import code.gui.CdmFactory;
import code.gui.SoftApplicationCore;
import code.gui.TopLeftFrame;
import code.gui.files.FileDialog;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.minirts.LaunchingDemo;
import code.player.SongList;
import code.player.main.LaunchingPlayer;
import code.renders.LaunchingRenders;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.stream.BytesInfo;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.core.StringUtil;

public class LaunchingApplications extends SoftApplicationCore {

    public static final String COORDS = "applications.coords";

    private static final String TEMP_FOLDER = "applications";


    public LaunchingApplications(AbstractProgramInfos _infos, CdmFactory _cdm, CardFactories _cf, AikiFactory _a) {
        super(_infos,new AppFactories(_a,_cf, _cdm));
    }

    protected static void loadLaungage(String[] _args, LaunchingApplications _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    private WindowApps getWindow(String _lg, AbstractProgramInfos _list) {
        return new WindowApps(_lg, _list, getAppFactories());
    }

    @Override
    protected final void launch(String _language, String[] _args) {
        StringList args_ = getFile(_args);
        if (!args_.isEmpty()) {
            BytesInfo bytes_ = StreamBinaryFile.loadFile(args_.first(), getFrames().getStreams());
            if (LaunchingConverter.isBinary(bytes_) && !isZip(bytes_.getBytes())) {
                AbstractImage img_ = getFrames().getImageFactory().newImageFromBytes(bytes_.getBytes());
                if (img_ != null) {
                    launchWindow(_language, getFrames());
                    LaunchingConverter launch_ = new LaunchingConverter(getFrames(),getAppFactories());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
            }
            String file_ = StreamTextFile.contentsOfFile(args_.first(), getFrames().getFileCoreStream(), getFrames().getStreams());
            if (file_ == null) {
                return;
            }
            if (DocumentReaderCardsUnionUtil.isContentObject(file_)) {
                launchWindow(_language, getFrames());
                LaunchingCards launch_ = new LaunchingCards(getFrames(),getAppFactories());
                launch_.launchWithoutLanguage(_language, _args);
                return;
            }
            Game gameOrNull_ = DocumentReaderAikiCoreUtil.getGameOrNull(file_,new SexListImpl());
            LoadingGame loadingGameOrNull_ = DocumentReaderAikiCoreUtil.getLoadingGameOrNull(file_);
            if (loadingGameOrNull_ != null || gameOrNull_ != null) {
                launchWindow(_language, getFrames());
                LaunchingPokemon launch_ = new LaunchingPokemon(getFrames(),getAppFactories());
                launch_.launchWithoutLanguage(_language, _args);
                return;
            }
            Document doc_ = DocumentBuilder.parseNoTextDocument(file_);
            if (doc_ != null) {
                if (StringUtil.quickEq("smil",  doc_.getDocumentElement().getTagName())) {
                    SongList list_ = new SongList();
                    list_.addSongs(doc_);
                    launchWindow(_language, getFrames());
                    LaunchingPlayer launch_ = new LaunchingPlayer(getFrames(),getAppFactories());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                launchWindow(_language, getFrames());
                LaunchingDemo launch_ = new LaunchingDemo(getFrames(),getAppFactories());
                launch_.launchWithoutLanguage(_language, _args);
                return;
            }
            if (file_.indexOf('\n') < 0) {
                launchWindow(_language, getFrames());
                LaunchingConverter launch_ = new LaunchingConverter(getFrames(),getAppFactories());
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
                    launchWindow(_language, getFrames());
                    LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFrames(),getAppFactories());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                String possibleMethod_ = StringExpUtil.removeDottedSpaces(linesFiles_.get(2));
                if (possibleMethod_.startsWith("initDb=")) {
                    launchWindow(_language, getFrames());
                    LaunchingRenders launch_ = new LaunchingRenders(getFrames(),getAppFactories());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                if (possibleMethod_.startsWith("main=")) {
                    launchWindow(_language, getFrames());
                    LaunchingFull launch_ = new LaunchingFull(getFrames(),getAppFactories());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                launchWindow(_language, getFrames());
                LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFrames(),getAppFactories());
                launch_.launchWithoutLanguage(_language, _args);
            }
            return;
        }
        launchWindow(_language, getFrames());
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
    private void launchWindow(String _language, AbstractProgramInfos _list) {
        TopLeftFrame topLeft_ = FileDialog.loadCoords(getTempFolder(_list),COORDS, _list.getFileCoreStream(), _list.getStreams());
        WindowApps w_ = getWindow(_language, _list);
        FileDialog.setLocation(w_.getCommonFrame(), topLeft_);
    }
    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return StreamFolderFile.getTempFolder(_tmpUserFolderSl,TEMP_FOLDER);
    }

    private static boolean isZip(byte[] _bytes) {
        return _bytes != null && _bytes.length > 3
                && _bytes[0] == (byte)0x50&& _bytes[1] == (byte)0x4b
                && _bytes[2] == (byte)0x03&& _bytes[3] == (byte)0x04;

    }

}
