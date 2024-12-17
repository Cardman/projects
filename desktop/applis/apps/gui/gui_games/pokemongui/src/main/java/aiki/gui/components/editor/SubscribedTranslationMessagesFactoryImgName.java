package aiki.gui.components.editor;

import aiki.db.ImageArrayBaseSixtyFour;
import aiki.facade.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryImgName implements SubscribedTranslationMessagesFactoryCoreMessages<String> {

    private final SubscribedTranslationMessagesFactoryCoreContainer<String> container = new SubscribedTranslationMessagesFactoryCoreContainer<String>(this);
    private final ImgRetriever imgRetriever;

    public SubscribedTranslationMessagesFactoryImgName(ImgRetriever _i) {
        this.imgRetriever = _i;
    }

    @Override
    public AbsMap<String, String> buildMessagesCopy(FacadeGame _facade, String _lg) {
        StringMap<ImageArrayBaseSixtyFour> all_ = imgRetriever.all(_facade);
        StringMap<String> res_ = new StringMap<String>();
        for (String k: all_.getKeys()) {
            res_.addEntry(k,k);
        }
        return res_;
    }

    public ImgRetriever getImgRetriever() {
        return imgRetriever;
    }

    @Override
    public SubscribedTranslationMessagesFactoryCoreContainer<String> getContainer() {
        return container;
    }
}
