package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.GraphicListGenerator;
import code.gui.initialize.LoadLanguageUtil;
import code.gui.initialize.ProgramInfos;
import code.util.StringMap;

import java.awt.image.BufferedImage;

public class LaunchingFull extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = "launcher";

    private final GuiFactroy fact;
    public LaunchingFull() {
        this(new ProgramInfos(), new GuiFactroy(new GraphicListGenerator<Struct>()));
    }

    public LaunchingFull(AbstractProgramInfos _frames, GuiFactroy _fact) {
        super(_frames);
        fact = _fact;
    }

    protected static void loadLaungage(String[] _args) {
        LoadLanguageUtil.loadLaungage(new LaunchingFull(), TEMP_FOLDER, _args);
    }

    @Override
    public Object getObject(String _fileName) {
        return null;
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        ThreadInvoker.invokeNow(new CreateMainWindow(_language,_args, getFrames(), fact));
    }


    @Override
    protected String getApplicationName() {
        return getMainWindowClass();
    }
    public static String getMainWindowClass() {
        return "launcher";
    }
    @Override
    protected BufferedImage getImageIcon() {
        return null;
    }

}
