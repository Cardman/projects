package code.gui.events;

import code.gui.AbsTxtComponent;
import code.util.CustList;
import code.util.StringList;

public interface AfterValidateText {
    void act(AbsTxtComponent _compo, String _typed);
    StringList filter(String _look, CustList<String> _dict);
}
