package aiki.gui.components.editor;

import aiki.map.characters.*;
import code.util.*;

public final class SellerPersonMapper implements AbsPersonMapper<Seller> {
    @Override
    public Seller map(Seller _t) {
        Seller cp_ = new Seller();
        cp_.setItems(new StringList(_t.getItems()));
        cp_.setTm(new Shorts(_t.getTm()));
        cp_.setSell(_t.getSell());
        cp_.setImageMiniFileName(_t.getImageMiniFileName());
        return cp_;
    }
}
