package code.gui;

import aiki.facade.SexListImpl;
import aiki.main.AikiFactory;
import aiki.sml.DefLoadingData;
import cards.gui.labels.LoadMiniCardsDef;
import cards.gui.labels.LoadMiniCardsSel;
import cards.main.CardFactories;
import code.gui.files.FileDialog;
import code.gui.images.AbstractImage;
import code.scripts.imgs.cards.CardImgsLoading;

public abstract class SoftApplicationCore {

    private final WithAppFactories frames;

    protected SoftApplicationCore(WithAppFactories _frames) {
        frames = _frames;
    }

    public AppFactories getAppFactories() {
        return frames.factories();
    }

    protected void loadLaungage(String _dir, String[] _args, AbstractImage _icon) {
        String lg_ = prepareLanguage(_dir, _args, _icon);
        AppFactories factories_ = frames.factories();
        AikiFactory a_ = factories_.getAikiFactory();
        a_.submit(new DefLoadingData(getFrames().getGenerator(), getFrames().getLanguages(), getFrames().getDisplayLanguages(),new SexListImpl()));
        CardFactories cf_ = factories_.getCardFactories();
        cf_.submit(new CardImgsLoading(),new LoadMiniCardsDef(),new LoadMiniCardsSel());
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

    public WithAppFactories getFrames() {
        return frames;
    }


}
