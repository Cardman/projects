package code.gui.document;

import aiki.map.pokemon.enums.*;
import code.gui.*;

public final class GenderDefValue implements AbsDefValue<Gender> {
    @Override
    public Gender defValue() {
        return Gender.NO_GENDER;
    }
}
