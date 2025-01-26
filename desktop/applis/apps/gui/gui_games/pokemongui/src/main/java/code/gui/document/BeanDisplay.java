package code.gui.document;

import code.gui.*;
import code.gui.initialize.*;

public interface BeanDisplay<T> {
    int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, T _info, int _index, int _count);
}
