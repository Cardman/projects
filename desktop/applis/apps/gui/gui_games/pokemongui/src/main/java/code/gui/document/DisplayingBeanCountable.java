package code.gui.document;

import aiki.beans.*;
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
    public static void headerCols(AbsBeanRender _rend,AbstractProgramInfos _api, AbsPanel _container, String _file, Countable _ls, String... _cols) {
        if (!_ls.isEmpty()) {
            for (int i = 0; i < _cols.length; i++) {
                String h_ = _cols[i];
                if (i + 1 < _cols.length) {
                    _rend.headerCol(_api, _container, _api.getCompoFactory().newGridCts(), _file, h_);
                } else {
                    _rend.headerCol(_api, _container, AbsBeanRender.remainder(_api), _file, h_);
                }
            }
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
    public static void displayBoolFull(AbsBeanRender _rend,AbstractProgramInfos _api, AbsPanel _container, String _file, int _value, String _one, String _two) {
        if (_value == CommonBean.TRUE_VALUE) {
            _rend.formatMessage(_api,_container,_file,_one);
        } else {
            _rend.formatMessage(_api,_container,_file,_two);
        }
    }
    public static void displayBoolTrue(AbsBeanRender _rend,AbstractProgramInfos _api, AbsPanel _container, String _file, int _value, String _key) {
        if (_value == CommonBean.TRUE_VALUE) {
            _rend.formatMessage(_api,_container,_file,_key);
        }
    }
}
