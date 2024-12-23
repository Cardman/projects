package aiki.gui.components.editor;

import aiki.instances.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;

public final class FormBlockTile {
    private GeneComponentModelImgSelect tileFileName;
    private AbsSpinner indexApparition;
    private AbsTextField dims;
    private GeneComponentModelEltEnumSub<EnvironmentType> type;
    private AbsButton match;
    private AbsButton remove;
    private AbsPanel form;

    private Block edited = Instances.newBlock();

    public void build(FormLevelGrid _grid, Point _p) {
        AbsCompoFactory c_ = _grid.getApi().getCompoFactory();
        form = c_.newPageBox();
        tileFileName = new GeneComponentModelImgSelect(_grid.getApi(),_grid.getFacadeGame(),_grid.getTranslationList().getImgRetrieverBlocksSub());
        form.add(tileFileName.gene());
        indexApparition = c_.newSpinner(-1,-1,Integer.MAX_VALUE,1);
        form.add(indexApparition);
        dims = c_.newTextField();
        dims.addActionListener(new DimsBlockEvent(_grid));
        form.add(dims);
        feedForm();
        type = ConverterCommonMapUtil.buildEnvironmentType(_grid.getApi(),_grid.getFacadeGame(),_grid.getTranslationList());
        form.add(type.geneEnum());
        match = c_.newPlainButton("\u2611");
        match.setForeground(GuiConstants.GREEN);
        match.addActionListener(new ApplyTileBlockEvent(_grid,this,_p, false));
        form.add(match);
        remove = c_.newPlainButton("-");
        remove.addActionListener(new ApplyTileBlockEvent(_grid,this,_p, true));
        form.add(remove);
    }
    public void feedForm(Block _e) {
        edited = ConverterCommonMapUtil.copyBlock(_e);
        tileFileName.updateValue(edited.getTileFileName());
        indexApparition.setValue(edited.getIndexApparition());
        type.setupValue(edited.getType());
        dims.setText(edited.getWidth()+":"+edited.getHeight());
    }
    public void feedForm() {
        edited = Instances.newBlock();
        edited.setWidth((short) 1);
        edited.setHeight((short) 1);
        tileFileName.updateValue(edited.getTileFileName());
        dims.setText("1:1");
    }

    public Block buildEntity() {
        edited.setTileFileName(tileFileName.getName().tryRet());
        edited.setIndexApparition((short) indexApparition.getValue());
        edited.setType(type.tryRet());
        return edited;
    }

    public GeneComponentModelImgSelect getTileFileName() {
        return tileFileName;
    }

    public AbsTextField getDims() {
        return dims;
    }

    public Block getEdited() {
        return edited;
    }

    public AbsSpinner getIndexApparition() {
        return indexApparition;
    }

    public AbsButton getMatch() {
        return match;
    }

    public AbsButton getRemove() {
        return remove;
    }

    public AbsPanel getForm() {
        return form;
    }
}
