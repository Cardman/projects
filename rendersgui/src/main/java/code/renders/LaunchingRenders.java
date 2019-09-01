package code.renders;

import code.gui.ConstFiles;
import code.gui.LoadLanguage;
import code.gui.SoftApplicationCore;
import code.gui.ThreadInvoker;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;

import java.awt.*;
import java.io.File;

public class LaunchingRenders extends SoftApplicationCore {

    private static final String TEMP_FOLDER = "renders_sites";

    protected static void loadLaungage(String[] _args) {
        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), new LaunchingRenders(), _args, null));
    }
    @Override
    public Object getObject(String _fileName) {
        return null;
    }

    @Override
    public void launchWithoutLanguage(String _language, StringMap<Object> _args) {
        launch(_language, _args);
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        ThreadInvoker.invokeNow(new CreateMainWindow(_language));
    }

    @Override
    protected Image getImageIcon() {
        return null;
    }

    public static String getTempFolderSl() {
        return StringList.concat(getTempFolder(), StreamTextFile.SEPARATEUR);
    }

    public static String getTempFolder() {
        new File(StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER)).mkdirs();
        return StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER);
    }

}
