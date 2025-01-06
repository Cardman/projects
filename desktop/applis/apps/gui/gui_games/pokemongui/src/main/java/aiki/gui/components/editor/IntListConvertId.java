package aiki.gui.components.editor;

import aiki.db.*;
import code.gui.initialize.*;
import code.util.*;

public final class IntListConvertId<E> implements IntListConvert<E, IdList<E>> {
    @Override
    public IdList<E> fromList(CustList<E> _ls) {
        return new IdList<E>(_ls);
    }

    @Override
    public CustList<E> toList(IdList<E> _ls) {
        return _ls;
    }

    public void patchReplace(StringMap<IdMap<E,String>> _map, CustList<E> _entities, AbstractProgramInfos _api) {
        StringMap<IdMap<E,String>> bk_ = new StringMap<IdMap<E,String>>(_map);
        _map.clear();
        _map.addAllEntries(patch(bk_,_entities,_api));
    }
    public StringMap<IdMap<E,String>> patch(StringMap<IdMap<E,String>> _map, CustList<E> _entities, AbstractProgramInfos _api) {
        CustList<String> mustLgs_ = _api.getTranslations().getMapping().getKeys();
        IdList<E> allEnt_ = new IdList<E>(_entities);
        if (_map.isEmpty()) {
            for (String l: mustLgs_) {
                IdMap<E,String> map_ = new IdMap<E,String>();
                feedId(allEnt_, map_);
                _map.addEntry(l, map_);
            }
            return _map;
        }
        for (EntryCust<String,IdMap<E,String>> e: _map.entryList()) {
            allEnt_.addAllElts(e.getValue().getKeys());
        }
        allEnt_.removeDuplicates();
        for (EntryCust<String,IdMap<E,String>> e: _map.entryList()) {
            patchMap(allEnt_, e.getValue());
        }
        StringList absent_ = new StringList(mustLgs_);
        CustList<String> present_ = _map.getKeys();
        absent_.removeAllElements(present_);
        for (String l: absent_) {
            _map.addEntry(l,new IdMap<E,String>(_map.getValue(0)));
        }
        return _map;
    }

    private void patchMap(CustList<E> _allEnt, IdMap<E,String> _map) {
        IdList<E> values_ = new IdList<E>(_map.getKeys());
        boolean missing_ = false;
        for (E f: _allEnt) {
            if (!values_.containsObj(f)) {
                missing_ = true;
                break;
            }
        }
        if (missing_) {
            _map.clear();
            feedId(_allEnt, _map);
        }
    }

    private void feedId(CustList<E> _allEnt, IdMap<E,String> _map) {
        for (E f: _allEnt) {
            _map.addEntry(f,Long.toString(_map.size()));
        }
    }
    public void clean(StringMap<E> _map) {
        StringMap<E> evos_ = new StringMap<E>();
        for (EntryCust<String,E> e:_map.entryList()) {
            String ev_ = e.getKey();
            if (DataBase.isCorrectIdentifier(ev_)) {
                evos_.addEntry(ev_,e.getValue());
            }
        }
        _map.clear();
        _map.addAllEntries(evos_);
    }
}
