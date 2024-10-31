package aiki.gui.components.editor;

import aiki.fight.pokemon.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEltGenderRepartition extends GeneComponentModelEltEnum<GenderRepartition> {
    public GeneComponentModelEltGenderRepartition(AbstractProgramInfos _c, AbsMap<GenderRepartition, String> _messages, CustList<GenderRepartition> _elts) {
        super(_c, _messages, _elts);
    }

    @Override
    protected GenderRepartition defValue() {
        return GenderRepartition.MIXED;
    }
}
