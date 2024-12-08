package aiki.gui.components.editor;


import code.util.*;

public final class IdMapImmutableTechnicalCopier<K,V> implements AbsTechnicalCopier<IdMap<K,V>> {
    @Override
    public IdMap<K,V> copy(IdMap<K,V> _e) {
        return new IdMap<K,V>(_e);
    }
}
