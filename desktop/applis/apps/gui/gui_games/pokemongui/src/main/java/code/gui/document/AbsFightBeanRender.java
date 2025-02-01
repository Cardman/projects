package code.gui.document;

import code.sml.util.TranslationsFile;
import code.util.StringMap;

public abstract class AbsFightBeanRender extends AbsBeanRender {

    public StringMap<TranslationsFile> filesFight() {
        return getBuilder().files(this);
    }
    @Override
    public String appName() {
        return MessagesPkBean.APP_BEAN_FIGHT;
    }
}
