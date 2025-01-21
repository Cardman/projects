package code.gui.document;

import code.formathtml.render.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.ints.*;

public final class DisplayingBeanCountable {
    private DisplayingBeanCountable() {
    }
    public static void display(AbsBeanRender _rend,AbstractProgramInfos _api, AbsPanel _container, String _file, Countable _ls, String _key) {
        if (!_ls.isEmpty()) {
            _rend.formatMessage(_api,_container,_file,_key);
            _rend.getMetaSearchableContents().add(new MetaSearchableContent(null, _rend.getPartGroup(), _rend.getRowGroup()));
        }
    }
    public static void displayEmpty(AbsBeanRender _rend,AbstractProgramInfos _api, AbsPanel _container, String _file, String _value, String _key) {
        if (_value.isEmpty()) {
            _rend.formatMessage(_api,_container,_file,_key);
        }
    }
    public static void displayNotEmpty(AbsBeanRender _rend,AbstractProgramInfos _api, AbsPanel _container, String _file, String _value, String _key) {
        if (!_value.isEmpty()) {
            _rend.formatMessage(_api,_container,_file,_key,_value);
        }
    }
}
