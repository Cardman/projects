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
    private AbsSpinner width;
    private AbsSpinner height;
    private AbsButton dims;
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
        width = c_.newSpinner(1,1,Integer.MAX_VALUE,1);
        form.add(width);
        height = c_.newSpinner(1,1,Integer.MAX_VALUE,1);
        form.add(height);
        dims = c_.newPlainButton("|=|");
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
        width.setValue(edited.getWidth());
        height.setValue(edited.getHeight());
    }
    public void feedForm() {
        edited = Instances.newBlock();
        edited.setWidth(1);
        edited.setHeight(1);
        tileFileName.updateValue(edited.getTileFileName());
        width.setValue(1);
        height.setValue(1);
    }

    public Block buildEntity() {
        edited.setTileFileName(tileFileName.getName().tryRet());
        edited.setIndexApparition(indexApparition.getValue());
        edited.setType(type.tryRet());
        return edited;
    }

    public GeneComponentModelImgSelect getTileFileName() {
        return tileFileName;
    }

    public AbsSpinner getWidth() {
        return width;
    }

    public AbsSpinner getHeight() {
        return height;
    }

    public AbsButton getDims() {
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
