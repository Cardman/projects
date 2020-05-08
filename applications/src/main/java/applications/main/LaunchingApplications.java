package applications.main;

import aiki.game.Game;
import aiki.main.LaunchingPokemon;
import aiki.sml.DocumentReaderAikiCoreUtil;
import aiki.sml.LoadingGame;
import applications.gui.MainWindow;
import cards.belote.GameBelote;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.main.LaunchingCards;
import cards.president.GamePresident;
import cards.tarot.GameTarot;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.guicompos.GuiProcess;
import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.*;
import code.images.BaseSixtyFourUtil;
import code.minirts.LaunchingDemo;
import code.player.SongList;
import code.player.main.LaunchingPlayer;
import code.renders.LaunchingRenders;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.stream.StreamBinaryFile;
import code.stream.StreamImageFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;
import code.converterimages.main.LaunchingConverter;

import java.awt.image.BufferedImage;
import java.io.File;

public class LaunchingApplications extends SoftApplicationCore {

    public static final String COORDS = "applications.coords";

    private static final String TEMP_FOLDER = "applications";

    protected static void loadLaungage(String[] _args) {
        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), new LaunchingApplications(), _args, null));
    }

    private static MainWindow getWindow(String _lg) {
        return new MainWindow(_lg);
    }

    @Override
    public final void launchWithoutLanguage(String _language, StringMap<Object> _args) {
        launch(_language, _args);
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
                launchWindow(_language);
                LaunchingCards launch_ = new LaunchingCards();
                launch_.launchWithoutLanguage(_language, _args);
            } else if (readObject_ instanceof LoadingGame || readObject_ instanceof Game) {
                launchWindow(_language);
                LaunchingPokemon launch_ = new LaunchingPokemon();
                launch_.launchWithoutLanguage(_language, _args);
            } else if (readObject_ instanceof SongList) {
                launchWindow(_language);
                LaunchingPlayer launch_ = new LaunchingPlayer();
                launch_.launchWithoutLanguage(_language, _args);
            } else if (readObject_ instanceof Document) {
                launchWindow(_language);
                LaunchingDemo launch_ = new LaunchingDemo();
                launch_.launchWithoutLanguage(_language, _args);
            } else if (readObject_ instanceof BufferedImage || readObject_ instanceof int[][]) {
                launchWindow(_language);
                LaunchingConverter launch_ = new LaunchingConverter();
                launch_.launchWithoutLanguage(_language, _args);
            } else if (readObject_ instanceof String) {
                String fileContent_ = (String) readObject_;
                StringList lines_ = StringList.splitStrings(fileContent_, "\n", "\r\n");
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
                    launchWindow(_language);
                    LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests();
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                String possibleMethod_ = StringExpUtil.removeDottedSpaces(linesFiles_.get(2));
                if (possibleMethod_.startsWith("initDb=")) {
                    launchWindow(_language);
                    LaunchingRenders launch_ = new LaunchingRenders();
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                if (possibleMethod_.startsWith("main=")) {
                    launchWindow(_language);
                    LaunchingFull launch_ = new LaunchingFull();
                    launch_.launchWithoutLanguage(_language, _args);
                    return;
                }
                launchWindow(_language);
                LaunchingAppUnitTests launch_ = new LaunchingAppUnitTests();
                launch_.launchWithoutLanguage(_language, _args);
            }
            return;
        }
        launchWindow(_language);
    }

    private static void launchWindow(String _language) {
        TopLeftFrame topLeft_ = loadCoords(getTempFolder(),COORDS);
        MainWindow w_ = getWindow(_language);
        setLocation(w_, topLeft_);
    }

    public static String getTempFolder() {
        new File(StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER)).mkdirs();
        return StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER);
    }

    @Override
    protected final BufferedImage getImageIcon() {
        return null;
    }

    private static boolean isZip(byte[] _bytes) {
        return _bytes != null && _bytes.length > 3
                && _bytes[0] == (byte)0x50&& _bytes[1] == (byte)0x4b
                && _bytes[2] == (byte)0x03&& _bytes[3] == (byte)0x04;

    }
    @Override
    public Object getObject(String _fileName) {
        byte[] bytes_ = StreamBinaryFile.loadFile(_fileName);
        if (LaunchingConverter.isBinary(bytes_) && !isZip(bytes_)) {
            BufferedImage img_ = StreamImageFile.read(_fileName);
            if (img_ != null) {
                return img_;
            }
        }
        String file_ = StreamTextFile.contentsOfFile(_fileName);
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
                if (StringList.quickEq("smil",  doc_.getDocumentElement().getTagName())) {
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
