package aiki.gui.components.editor;

import aiki.facade.*;
import code.util.*;

public interface NumStrIdRetriever<K> {
    IdMap<K, String> infos(FacadeGame _fac);
}
