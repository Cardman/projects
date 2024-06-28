package applications.main;

import aiki.facade.SexListImpl;
import aiki.game.Game;
import aiki.main.LaunchingPokemon;
import aiki.sml.DocumentReaderAikiCoreUtil;
import aiki.sml.LoadingGame;
import applications.gui.WindowApps;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.main.LaunchingCards;
import code.converterimages.main.LaunchingConverter;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.*;
import code.gui.files.FileDialog;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.minirts.LaunchingDemo;
import code.player.main.LaunchingPlayer;
import code.renders.LaunchingRenders;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.stream.*;
import code.util.StringList;
import code.util.core.StringUtil;

public class LaunchingApplications extends SoftApplicationCore {

    public static final String COORDS = "applications.coords";

    private static final String TEMP_FOLDER = "applications";


    public LaunchingApplications(WithAppFactories _infos) {
        super(_infos);
    }

    protected static void loadLaungage(String[] _args, LaunchingApplications _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    private WindowApps getWindow(String _lg, WithAppFactories _list) {
        return new WindowApps(_lg, _list);
    }

    @Override
    protected final void launch(String _language, String[] _args, EnabledMenu _lgMenu) {
        StringList args_ = getFile(_args);
        if (!args_.isEmpty()) {
            BytesInfo bytes_ = StreamBinaryFile.loadFile(args_.first(), getFrames().getStreams());
            if (FileListInfo.isBinary(bytes_) && !FileListInfo.isZip(bytes_.getBytes())) {
                AbstractImage img_ = getFrames().getImageFactory().newImageFromBytes(bytes_.getBytes());
                if (img_ != null) {
                    launchWindow(_language, getFactories());
                    LaunchingConverter launch_ = new LaunchingConverter(getFactories());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
            }
            String file_ = StreamTextFile.contentsOfFile(args_.first(), getFrames().getFileCoreStream(), getFrames().getStreams());
            if (file_ == null) {
                return;
            }
            if (DocumentReaderCardsUnionUtil.isContentObject(file_)) {
                launchWindow(_language, getFactories());
                LaunchingCards launch_ = new LaunchingCards(getFactories());
                launch_.launchWithoutLanguage(_language, _args);
                return;
            }
            Game gameOrNull_ = DocumentReaderAikiCoreUtil.getGameOrNull(file_,new SexListImpl());
            LoadingGame loadingGameOrNull_ = DocumentReaderAikiCoreUtil.getLoadingGameOrNull(file_);
            if (loadingGameOrNull_ != null || gameOrNull_ != null) {
                launchWindow(_language, getFactories());
                LaunchingPokemon launch_ = new LaunchingPokemon(getFactories());
                launch_.launchWithoutLanguage(_language, _args);
                return;
            }
            Document doc_ = DocumentBuilder.parseNoTextDocument(file_);
            if (doc_ != null) {
                if (StringUtil.quickEq("smil",  doc_.getDocumentElement().getTagName())) {
                    launchWindow(_language, getFactories());
                    LaunchingPlayer launch_ = new LaunchingPlayer(getFactories());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                launchWindow(_language, getFactories());
                LaunchingDemo launch_ = new LaunchingDemo(getFactories());
                launch_.launchWithoutLanguage(_language, _args);
                return;
            }
            if (file_.indexOf('\n') < 0) {
                launchWindow(_language, getFactories());
                LaunchingConverter launch_ = new LaunchingConverter(getFactories());
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
                    launchWindow(_language, getFactories());
                    LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFactories());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                String possibleMethod_ = StringExpUtil.removeDottedSpaces(linesFiles_.get(2));
                if (possibleMethod_.startsWith("initDb=")) {
                    launchWindow(_language, getFactories());
                    LaunchingRenders launch_ = new LaunchingRenders(getFactories());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                if (possibleMethod_.startsWith("main=")) {
                    launchWindow(_language, getFactories());
                    LaunchingFull launch_ = new LaunchingFull(getFactories());
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                launchWindow(_language, getFactories());
                LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFactories());
                launch_.launchWithoutLanguage(_language, _args);
            }
            return;
        }
        launchWindow(_language, getFactories());
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
    private void launchWindow(String _language, WithAppFactories _list) {
        TopLeftFrame topLeft_ = FileDialog.loadCoords(getTempFolder(_list.getProgramInfos()),COORDS, _list.getProgramInfos().getFileCoreStream(), _list.getProgramInfos().getStreams());
        WindowApps w_ = getWindow(_language, _list);
        FileDialog.setLocation(w_.getCommonFrame(), topLeft_);
    }
    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return StreamFolderFile.getTempFolder(_tmpUserFolderSl,TEMP_FOLDER);
    }

}
