package code.converterimages.main;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.stream.StreamBinaryFile;
import code.stream.StreamTextFile;
import code.util.StringMap;
import code.converterimages.gui.CreateMainWindowConverter;

public class LaunchingConverter extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = "converter";

    public LaunchingConverter(AbstractProgramInfos _frames) {
        super(_frames);
    }

    protected static void loadLaungage(String[] _args, LaunchingConverter _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    @Override
    public Object getObject(String _fileName) {
        if (isBinary(StreamBinaryFile.loadFile(_fileName, getFrames().getFileCoreStream(), getFrames().getStreams()))) {
            AbstractImage img_ = getFrames().readImg(_fileName);
            if (img_ != null) {
                return img_;
            }
        }
        return StreamTextFile.contentsOfFile(_fileName, getFrames().getFileCoreStream(), getFrames().getStreams());
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowConverter(_language,_args, getFrames()), getFrames());
    }

    public static boolean isBinary(byte[] _bytes) {
        if (_bytes == null) {
            return false;
        }
        for (byte b: _bytes) {
            if (b < ' ') {
                if (b == '\n') {
                    continue;
                }
                if (b == '\t') {
                    continue;
                }
                if (b == '\r') {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    protected String getApplicationName() {
        return getMainWindowClass();
    }
    public static String getMainWindowClass() {
        return "converter";
    }

}
