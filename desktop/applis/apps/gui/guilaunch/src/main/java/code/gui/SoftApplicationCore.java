package code.gui;

import code.gui.files.FileDialog;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;

public abstract class SoftApplicationCore {

    private final AbstractProgramInfos frames;

    protected SoftApplicationCore(AbstractProgramInfos _frames) {
        frames = _frames;
    }
    protected void loadLaungage(String _dir, String[] _args, AbstractImage _icon) {
        String lg_ = prepareLanguage(_dir, _args, _icon);
        if (lg_.isEmpty()) {
            return;
        }
        launchFile(_args, lg_);
    }

    void launchFile(String[] _args, String _lg) {
        frames.setLanguage(_lg);
        launch(_lg, _args);
    }

    protected final String prepareLanguage(String _dir, String[] _args, AbstractImage _icon) {
        String language_ = FileDialog.loadLanguage(_dir,getFrames().getFileCoreStream(), getFrames().getStreams());
        if (language_.isEmpty()) {
            proponeLanguage(_dir, _args, _icon);
        }
        return language_;
    }

    private LanguageFrame proponeLanguage(String _dir, String[] _args, AbstractImage _icon) {
        return new LanguageFrame(_dir, _args, this, _icon);
    }


    protected abstract void launch(String _language, String[] _args);

    public AbstractProgramInfos getFrames() {
        return frames;
    }


}
