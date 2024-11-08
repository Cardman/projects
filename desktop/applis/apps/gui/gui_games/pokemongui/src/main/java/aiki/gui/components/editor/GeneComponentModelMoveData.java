package aiki.gui.components.editor;

import aiki.fight.moves.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;

public final class GeneComponentModelMoveData implements GeneComponentModel<MoveData> {
    private final AbstractProgramInfos compoFactory;
    private final GeneComponentModelInt pp;
    private MoveData element;


    public GeneComponentModelMoveData(AbstractProgramInfos _core) {
        this.compoFactory = _core;
        pp = new GeneComponentModelInt(_core);
    }
    @Override
    public AbsCustComponent gene() {
        element = Instances.newDamagingMoveData();
        AbsCompoFactory compoFactory_ = compoFactory.getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(pp.geneInt());
        sc_.setViewportView(form_);
        return sc_;
    }

    @Override
    public MoveData value() {
        DamagingMoveData ent_ = Instances.newDamagingMoveData();
        ent_.setPp((short) pp.valueInt());
        return ent_;
    }

    @Override
    public MoveData value(MoveData _v) {
        pp.valueInt(_v.getPp());
        return element;
    }

    public GeneComponentModelInt getPp() {
        return pp;
    }
}
