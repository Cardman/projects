package aiki.gui.components.editor;

import aiki.map.characters.*;

public final class GerantPersonMapper implements AbsPersonMapper<GerantPokemon> {
    @Override
    public GerantPokemon map(GerantPokemon _t) {
        GerantPokemon cp_ = new GerantPokemon();
        cp_.setGerance(_t.getGerance());
        cp_.setImageMiniFileName(_t.getImageMiniFileName());
        return cp_;
    }
}
