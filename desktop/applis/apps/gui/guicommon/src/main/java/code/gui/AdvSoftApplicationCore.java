package code.gui;

import code.util.CustList;
import code.util.StringMap;

public abstract class AdvSoftApplicationCore extends SoftApplicationCore {
    public AdvSoftApplicationCore(CustList<GroupFrame> _frames) {
        super(_frames);
    }

    public void launch(String _lg, CustList<GroupFrame> _frames) {
        launch(_lg, new StringMap<Object>());
    }
    public void launchWithoutLanguage(String _language, StringMap<Object> _args) {
        launch(_language,_args);
    }
}
