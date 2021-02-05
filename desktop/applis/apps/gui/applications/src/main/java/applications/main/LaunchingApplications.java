package applications.main;

import aiki.game.Game;
import aiki.game.fight.BallNumberRate;
import aiki.game.fight.Fighter;
import aiki.main.AikiFactory;
import aiki.main.LaunchingPokemon;
import aiki.map.pokemon.UsablePokemon;
import aiki.sml.DocumentReaderAikiCoreUtil;
import aiki.sml.LoadingGame;
import applications.gui.MainWindow;
import cards.belote.GameBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.main.CardFactories;
import cards.main.LaunchingCards;
import cards.president.GamePresident;
import cards.president.enumerations.CardPresident;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.CardTarot;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.expressionlanguage.guicompos.GuiFactroy;
import code.expressionlanguage.guicompos.LaunchingFull;
import code.expressionlanguage.structs.Struct;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.GraphicListGenerator;
import code.gui.initialize.LoadLanguageUtil;
import code.gui.initialize.ProgramInfos;
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
import code.util.core.StringUtil;

import java.awt.image.BufferedImage;

public class LaunchingApplications extends SoftApplicationCore {

    public static final String COORDS = "applications.coords";

    private static final String TEMP_FOLDER = "applications";

    private final CardFactories cardFactories;
    private final AikiFactory aikiFactory;
    private final GuiFactroy guiFactory;

    public LaunchingApplications() {
        this(new ProgramInfos(), new CardFactories(new GraphicListGenerator<CardBelote>(), new GraphicListGenerator<CardPresident>(), new GraphicListGenerator<CardTarot>(), new GraphicListGenerator<Suit>()), new AikiFactory(new GraphicListGenerator<BallNumberRate>(), new GraphicListGenerator<Fighter>(), new GraphicListGenerator<String>(), new GraphicListGenerator<String>(), new GraphicListGenerator<String>(), new GraphicListGenerator<UsablePokemon>()), new GuiFactroy(new GraphicListGenerator<Struct>()));
    }

    public LaunchingApplications(AbstractProgramInfos _frames, CardFactories _cardFactories, AikiFactory _aikiFactory, GuiFactroy _guiFactory) {
        super(_frames);
        cardFactories = _cardFactories;
        aikiFactory = _aikiFactory;
        guiFactory = _guiFactory;
    }

    protected static void loadLaungage(String[] _args) {
        LoadLanguageUtil.loadLaungage(new LaunchingApplications(), TEMP_FOLDER, _args);
    }

    private static MainWindow getWindow(String _lg, AbstractProgramInfos _list, CardFactories _cardFactories, AikiFactory _aikiFactory, GuiFactroy _guiFact) {
        return new MainWindow(_lg, _list, _cardFactories, _aikiFactory, _guiFact);
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
            } else if (readObject_ instanceof BufferedImage || readObject_ instanceof int[][]) {
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
        TopLeftFrame topLeft_ = loadCoords(getTempFolder(_list),COORDS);
        MainWindow w_ = getWindow(_language, _list, _cardFactories, _aikiFactory, _guiFact);
        setLocation(w_, topLeft_);
    }
    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return getTempFolder(_tmpUserFolderSl,TEMP_FOLDER);
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
