package code.converterimages.main;

import code.converterimages.gui.WindowConverter;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.stream.BytesInfo;
import code.stream.StreamBinaryFile;
import code.util.StringMap;
import code.converterimages.gui.CreateMainWindowConverter;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public class LaunchingConverter extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = WindowConverter.APPS_CONVERTER;

    public LaunchingConverter(AbstractProgramInfos _frames) {
        super(_frames);
    }

    protected static void loadLaungage(String[] _args, LaunchingConverter _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    public BoolVal getObject(String _fileName) {
        BytesInfo bytes_ = StreamBinaryFile.loadFile(_fileName, getFrames().getStreams());
        if (isBinary(bytes_)) {
            AbstractImage img_ = getFrames().getImageFactory().newImageFromBytes(bytes_.getBytes());
            if (img_ != null) {
                return BoolVal.TRUE;
            }
        }
        return BoolVal.FALSE;
    }

    @Override
    protected void launch(String _language, String[] _args) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowConverter(_language,getFile(_args), getFrames()), getFrames());
    }

    protected StringMap<BoolVal> getFile(String[] _args) {
        StringMap<BoolVal> files_ = new StringMap<BoolVal>();
        if (_args.length > 0) {
            String fileName_ = getFrames().getFileCoreStream().newFile(_args[0]).getAbsolutePath();
            fileName_ = StringUtil.replaceBackSlash(fileName_);
            files_.put(fileName_, getObject(_args[0]));
        }
        return files_;
    }

    public static boolean isBinary(BytesInfo _bytes) {
        if (_bytes.isNul()) {
            return false;
        }
        for (byte b: _bytes.getBytes()) {
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
        return WindowConverter.APPS_CONVERTER;
    }

}
