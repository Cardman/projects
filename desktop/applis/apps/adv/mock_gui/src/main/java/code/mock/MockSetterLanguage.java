package code.mock;

import code.gui.AbsCommonFrame;
import code.gui.events.SetterLanguage;
import code.gui.initialize.AbstractProgramInfos;

public final class MockSetterLanguage implements SetterLanguage {
    private String langue;

    @Override
    public void setLanguage(String _language) {
        langue = _language;
    }

    @Override
    public String getLanguage() {
        return langue;
    }

    @Override
    public void init(AbsCommonFrame _owner, AbstractProgramInfos _pr, String _title) {
        setLanguage("");
    }
}
