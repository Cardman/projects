package applications.main;

import aiki.facade.*;
import aiki.game.*;
import applications.code.gui.*;
import aiki.sml.*;
import applications.gui.*;
import cards.facade.sml.*;
import code.converterimages.gui.*;
import applications.code.converterimages.main.*;
import code.expressionlanguage.common.*;
import applications.code.expressionlanguage.gui.unit.*;
import applications.code.expressionlanguage.guicompos.*;
import code.expressionlanguage.utilcompo.*;
import code.gui.*;
import code.gui.files.*;
import code.gui.initialize.*;
//import code.gui.initialize.LoadLanguageUtil;
import applications.code.minirts.*;
import applications.code.player.main.*;
import applications.code.renders.*;
import code.player.gui.*;
import code.sml.*;
import code.sml.util.Translations;
import code.stream.*;
import code.util.*;
import code.util.core.*;

public class LaunchingApplications extends SoftApplicationCore {

//    public static final String COORDS = "applications.coords";

//    public static final String TEMP_FOLDER = "applications";


    public LaunchingApplications(WithAppFactories _infos) {
        super(_infos);
    }

//    protected static void loadLaungage(String[] _args, LaunchingApplications _soft) {
//        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
//    }

    private WindowApps getWindow(WithAppFactories _list) {
        return new WindowApps(_list);
    }

    @Override
    protected final void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair) {
//        StringList args_ = _args.getFileNames();
        BytesInfo bytes_ = _args.getInput();
        if (bytes_.isNul()) {
            launchWindow(getFactories());
            return;
        }
        if (!DocumentImagesUtil.parse(_args.getDocument()).isEmpty()) {
//
//        }
//        if (FileListInfo.isBinary(bytes_) && !FileListInfo.isZip(bytes_.getBytes()) && getFrames().getImageFactory().newImageFromBytes(bytes_.getBytes()) != null) {
            AbsButton bu_ = launchWindow(getFactories()).getButtonConverter();
            LaunchingConverter launch_ = new LaunchingConverter(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_, new LanguagesButtonsPair(_pair.getLgMenu(),bu_,_pair.getButtons()));
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
        if (DocumentReaderCardsUnionUtil.isContentObject(_args.getDocument())) {
            AbsButton bu_ = launchWindow(getFactories()).getButtonCards();
            LaunchingCards launch_ = new LaunchingCards(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_, new LanguagesButtonsPair(_pair.getLgMenu(),bu_,_pair.getButtons()));
            return;
        }
        Game gameOrNull_ = DocumentReaderAikiCoreUtil.getGameOrNull(_args.getDocument(), new SexListImpl());
        LoadingGame loadingGameOrNull_ = DocumentReaderAikiCoreUtil.getLoadingGameOrNull(_args.getDocument());
        if (loadingGameOrNull_ != null || gameOrNull_ != null) {
            AbsButton bu_ = launchWindow(getFactories()).getButtonPokemon();
            LaunchingPokemon launch_ = new LaunchingPokemon(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_, new LanguagesButtonsPair(_pair.getLgMenu(),bu_,_pair.getButtons()));
            return;
        }
        Document doc_ = _args.getDocument();
        if (doc_ != null) {
            if (StringUtil.quickEq(WindowPlayer.SMIL, doc_.getDocumentElement().getTagName())) {
                AbsButton bu_ = launchWindow(getFactories()).getButtonPlayer();
                LaunchingPlayer launch_ = new LaunchingPlayer(getFactories());
                launch_.launchWithoutLanguage(_language, _args, bu_, new LanguagesButtonsPair(_pair.getLgMenu(),bu_,_pair.getButtons()));
                return;
            }
            AbsButton bu_ = launchWindow(getFactories()).getButtonDemo();
            LaunchingDemo launch_ = new LaunchingDemo(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_, new LanguagesButtonsPair(_pair.getLgMenu(),bu_,_pair.getButtons()));
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
            launchWindow(getFactories());
            return;
        }
        String db_ = MessagesExecutingOptions.valExecOptionsKeys(getFrames().currentLg()).getVal(MessagesExecutingOptions.EXEC_OPTIONS_KEY_INIT_DB)+ExecutingOptions.EXEC_OPTIONS_SEP;
        if (linesFiles_.get(1).startsWith(db_)) {
            AbsButton bu_ = launchWindow(getFactories()).getButtonRenders();
            LaunchingRenders launch_ = new LaunchingRenders(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_, new LanguagesButtonsPair(_pair.getLgMenu(),bu_,_pair.getButtons()));
            return;
        }
        if (linesFiles_.size() < 3) {
            AbsButton bu_ = launchWindow(getFactories()).getButtonTests();
            LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_, new LanguagesButtonsPair(_pair.getLgMenu(),bu_,_pair.getButtons()));
            return;
        }
        String possibleMethod_ = StringExpUtil.removeDottedSpaces(linesFiles_.get(2));
        String ma_ = MessagesExecutingOptions.valExecOptionsKeys(getFrames().currentLg()).getVal(MessagesExecutingOptions.EXEC_OPTIONS_KEY_MAIN)+ExecutingOptions.EXEC_OPTIONS_SEP;
        if (possibleMethod_.startsWith(ma_)) {
            AbsButton bu_ = launchWindow(getFactories()).getButtonApps();
            LaunchingFull launch_ = new LaunchingFull(getFactories());
            launch_.launchWithoutLanguage(_language, _args, bu_, new LanguagesButtonsPair(_pair.getLgMenu(),bu_,_pair.getButtons()));
            return;
        }
        AbsButton bu_ = launchWindow(getFactories()).getButtonTests();
        LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests(getFactories());
        launch_.launchWithoutLanguage(_language, _args, bu_, new LanguagesButtonsPair(_pair.getLgMenu(),bu_,_pair.getButtons()));
    }

    private WindowApps launchWindow(WithAppFactories _list) {
        TopLeftFrame topLeft_ = FileDialog.loadCoords(getTempFolder(_list.getProgramInfos()), MessagesApplications.getAppliFilesTr(_list.getProgramInfos().getTranslations()).val().getMapping().getVal(MessagesApplications.COORDS), _list.getProgramInfos().getFileCoreStream(), _list.getProgramInfos().getStreams());
        WindowApps w_ = getWindow(_list);
        FileDialog.setLocation(w_.getCommonFrame(), topLeft_, _list.getProgramInfos());
        return w_;
    }
    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return StreamFolderFile.getTempFolder(_tmpUserFolderSl, MessagesApplications.getAppliFilesTr(_tmpUserFolderSl.getTranslations()).val().getMapping().getVal(Translations.TEMP_FOLDER));
    }

}
