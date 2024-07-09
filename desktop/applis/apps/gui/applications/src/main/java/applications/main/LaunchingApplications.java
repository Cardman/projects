package applications.main;

import aiki.facade.SexListImpl;
import aiki.game.Game;
import aiki.main.LaunchingPokemon;
import aiki.sml.DocumentReaderAikiCoreUtil;
import aiki.sml.LoadingGame;
import applications.gui.WindowApps;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.main.LaunchingCards;
import code.converterimages.gui.DocumentImagesUtil;
import code.converterimages.main.LaunchingConverter;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.gui.*;
import code.gui.files.FileDialog;
import code.gui.initialize.AbstractProgramInfos;
//import code.gui.initialize.LoadLanguageUtil;
import code.minirts.LaunchingDemo;
import code.player.main.LaunchingPlayer;
import code.renders.LaunchingRenders;
import code.sml.Document;
import code.stream.*;
import code.util.StringList;
import code.util.core.StringUtil;

public class LaunchingApplications extends SoftApplicationCore {

    public static final String COORDS = "applications.coords";

    public static final String TEMP_FOLDER = "applications";


    public LaunchingApplications(WithAppFactories _infos) {
        super(_infos);
    }

//    protected static void loadLaungage(String[] _args, LaunchingApplications _soft) {
//        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
//    }

    private WindowApps getWindow(String _lg, WithAppFactories _list) {
        return new WindowApps(_lg, _list);
    }

    @Override
    protected final void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
//        StringList args_ = _args.getFileNames();
        BytesInfo bytes_ = _args.getInput();
        if (bytes_.isNul()) {
            launchWindow(_language, getFactories());
            return;
        }
        if (!DocumentImagesUtil.parse(StringUtil.decode(bytes_.getBytes())).isEmpty()) {
//
//        }
//        if (FileListInfo.isBinary(bytes_) && !FileListInfo.isZip(bytes_.getBytes()) && getFrames().getImageFactory().newImageFromBytes(bytes_.getBytes()) != null) {
            AbsButton bu_ = launchWindow(_language, getFactories()).getButtonConverter();
            LaunchingConverter launch_ = new LaunchingConverter(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_);
            return;
//            AbstractImage img_ = getFrames().getImageFactory().newImageFromBytes(bytes_.getBytes());
//            if (img_ != null) {
//                launchWindow(_language, getFactories());
//                LaunchingConverter launch_ = new LaunchingConverter(getFactories());
//                launch_.launchWithoutLanguage(_language, _args);
//                return;
//            }
        }
        String file_ = _args.getText();
//        if (file_ == null) {
//            return;
//        }
        if (DocumentReaderCardsUnionUtil.isContentObject(file_)) {
            AbsButton bu_ = launchWindow(_language, getFactories()).getButtonCards();
            LaunchingCards launch_ = new LaunchingCards(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_);
            return;
        }
        Game gameOrNull_ = DocumentReaderAikiCoreUtil.getGameOrNull(file_, new SexListImpl());
        LoadingGame loadingGameOrNull_ = DocumentReaderAikiCoreUtil.getLoadingGameOrNull(file_);
        if (loadingGameOrNull_ != null || gameOrNull_ != null) {
            AbsButton bu_ = launchWindow(_language, getFactories()).getButtonPokemon();
            LaunchingPokemon launch_ = new LaunchingPokemon(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_);
            return;
        }
        Document doc_ = _args.getDocument();
        if (doc_ != null) {
            if (StringUtil.quickEq("smil", doc_.getDocumentElement().getTagName())) {
                AbsButton bu_ = launchWindow(_language, getFactories()).getButtonPlayer();
                LaunchingPlayer launch_ = new LaunchingPlayer(getFactories());
                launch_.launchWithoutLanguage(_language, _args, bu_);
                return;
            }
            AbsButton bu_ = launchWindow(_language, getFactories()).getButtonDemo();
            LaunchingDemo launch_ = new LaunchingDemo(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_);
            return;
        }
//        if (file_.indexOf('\n') < 0) {
//            launchWindow(_language, getFactories());
//            LaunchingConverter launch_ = new LaunchingConverter(getFactories());
//            launch_.launchWithoutLanguage(_language, _args);
//            return;
//        }
        StringList linesFiles_ = ExecutingOptions.lines(file_);
        if (linesFiles_.size() < 2) {
            launchWindow(_language, getFactories());
            return;
        }
        if (linesFiles_.size() < 3) {
            AbsButton bu_ = launchWindow(_language, getFactories()).getButtonTests();
            LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_);
            return;
        }
        String possibleMethod_ = StringExpUtil.removeDottedSpaces(linesFiles_.get(2));
        if (possibleMethod_.startsWith("initDb=")) {
            AbsButton bu_ = launchWindow(_language, getFactories()).getButtonRenders();
            LaunchingRenders launch_ = new LaunchingRenders(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_);
            return;
        }
        if (possibleMethod_.startsWith("main=")) {
            AbsButton bu_ = launchWindow(_language, getFactories()).getButtonApps();
            LaunchingFull launch_ = new LaunchingFull(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_);
            return;
        }
        AbsButton bu_ = launchWindow(_language, getFactories()).getButtonTests();
        LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFactories());
        launch_.launchWithoutLanguage(_language, _args, bu_);
    }

    private WindowApps launchWindow(String _language, WithAppFactories _list) {
        TopLeftFrame topLeft_ = FileDialog.loadCoords(getTempFolder(_list.getProgramInfos()),COORDS, _list.getProgramInfos().getFileCoreStream(), _list.getProgramInfos().getStreams());
        WindowApps w_ = getWindow(_language, _list);
        FileDialog.setLocation(w_.getCommonFrame(), topLeft_);
        return w_;
    }
    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return StreamFolderFile.getTempFolder(_tmpUserFolderSl,TEMP_FOLDER);
    }

}
