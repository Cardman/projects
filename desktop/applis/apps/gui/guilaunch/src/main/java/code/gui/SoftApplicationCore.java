package code.gui;

import aiki.facade.SexListImpl;
import aiki.main.AikiFactory;
import aiki.sml.DefLoadingData;
import cards.main.CardFactories;
import code.gui.files.FileDialog;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.imgs.cards.CardImgsLoading;

public abstract class SoftApplicationCore {

    private final AbstractProgramInfos frames;

    private final AppFactories appFactories;

    protected SoftApplicationCore(AbstractProgramInfos _frames,AppFactories _fact) {
        frames = _frames;
        appFactories = _fact;
    }

    public AppFactories getAppFactories() {
        return appFactories;
    }

    protected void loadLaungage(String _dir, String[] _args, AbstractImage _icon) {
        String lg_ = prepareLanguage(_dir, _args, _icon);
        AikiFactory a_ = appFactories.getAikiFactory();
        if (a_ != null) {
            a_.submit(new DefLoadingData(getFrames().getGenerator(), getFrames().getLanguages(), getFrames().getDisplayLanguages(),new SexListImpl()));
        }
        CardFactories cf_ = appFactories.getCardFactories();
        if (cf_ != null) {
            cf_.submit(new CardImgsLoading());
        }
       if (lg_.isEmpty()) {
            return;
        }
        launchFile(_args, lg_);
    }

    protected void launchFile(String[] _args, String _lg) {
        frames.setLanguage(_lg);
        launch(_lg, _args);
    }

    protected final String prepareLanguage(String _dir, String[] _args, AbstractImage _icon) {
        String language_ = FileDialog.loadLanguage(_dir,getFrames().getFileCoreStream(), getFrames().getStreams(), getFrames().getLanguages());
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
