package code.converterimages.main;

import code.converterimages.gui.*;
import code.gui.*;

public class LaunchingConverter extends AdvSoftApplicationCore {

    public static final String TEMP_FOLDER = WindowConverter.APPS_CONVERTER;

    public LaunchingConverter(WithAppFactories _infos){
        super(_infos);
    }
//
//    public BoolVal getObject(String _fileName) {
//        BytesInfo bytes_ = StreamBinaryFile.loadFile(_fileName, getFrames().getStreams());
//        if (FileListInfo.isBinary(bytes_)) {
//            AbstractImage img_ = getFrames().getImageFactory().newImageFromBytes(bytes_.getBytes());
//            if (img_ != null) {
//                return BoolVal.TRUE;
//            }
//        }
//        return BoolVal.FALSE;
//    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowConverter(_language, _args.getFileNames(), getFrames(), _main));
//        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowConverter(_language,getFile(_args), getFrames()), getFrames());
    }

//    protected StringMap<BoolVal> getFile(String[] _args) {
//        StringMap<BoolVal> files_ = new StringMap<BoolVal>();
//        if (_args.length > 0) {
//            String fileName_ = getFrames().getFileCoreStream().newFile(_args[0]).getAbsolutePath();
//            fileName_ = StringUtil.replaceBackSlash(fileName_);
//            files_.put(fileName_, getObject(_args[0]));
//        }
//        return files_;
//    }

}
