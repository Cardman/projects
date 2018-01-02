package pokecards.main;
import java.awt.Image;
import java.io.File;

import pokecards.gui.MainWindow;
import aiki.game.Game;
import aiki.game.params.LoadingGame;
import aiki.main.LaunchingPokemon;
import cards.belote.GameBelote;
import cards.main.LaunchingCards;
import cards.president.GamePresident;
import cards.tarot.GameTarot;
import code.gui.LoadLanguage;
import code.gui.SoftApplication;
import code.gui.ThreadInvoker;
import code.gui.TopLeftFrame;
import code.serialize.exceptions.BadObjectException;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstFiles;

public class LaunchingPokecards extends SoftApplication {

    public static final String COORDS = "pkcards.coords";

    private static final String TEMP_FOLDER = "pokecards";

    protected static void loadLaungage(String[] _args) {
        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), new LaunchingPokecards(), _args, null));
    }

    private static MainWindow getWindow() {
        return new MainWindow();
    }

    @Override
    protected final void launchWithoutLanguage(String _language, StringMap<Object> _args) {
        launch(_language, _args);
    }

    @Override
    protected final void launch(String _language, StringMap<Object> _args) {
        TopLeftFrame topLeft_;
        try {
            topLeft_ = loadCoords(getTempFolder(),COORDS);
            if (topLeft_ == null) {
                topLeft_ = new TopLeftFrame();
            }
        } catch (ClassCastException _0) {
            topLeft_ = new TopLeftFrame();
        } catch (BadObjectException _0) {
            topLeft_ = new TopLeftFrame();
        }
        MainWindow w_ = getWindow();
        setLocation(w_, topLeft_);
        if (!_args.isEmpty()) {
            try {
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
                    LaunchingCards launch_ = new LaunchingCards();
                    //w_.hideRadioButtons();
                    launch_.launchWithoutLanguage(_language, _args);
                } else if (readObject_ instanceof LoadingGame || readObject_ instanceof Game) {
                    LaunchingPokemon launch_ = new LaunchingPokemon();
                    //w_.hideRadioButtons();
                    launch_.launchWithoutLanguage(_language, _args);
                }
            } catch (RuntimeException _0) {
                _0.printStackTrace();
            }
        }
    }

    public static String getTempFolder() {
        new File(StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER)).mkdirs();
        return StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER);
    }

    @Override
    protected final Image getImageIcon() {
        return null;
    }
}
