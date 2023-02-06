package code.gui.events;

import code.gui.AbsCommonFrame;
import code.gui.initialize.AbstractProgramInfos;

public interface SetterLanguage {

    void setLanguage(String _language);

    String getLanguage();
    void init(AbsCommonFrame _owner, AbstractProgramInfos _pr, String _title);
}
