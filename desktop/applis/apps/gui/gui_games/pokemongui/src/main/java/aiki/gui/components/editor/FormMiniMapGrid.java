package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.map.util.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class FormMiniMapGrid {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final AbsScrollPane container;
    private final AbsCommonFrame frame;
    private final SubscribedTranslationList translationList;
    private final FormMiniMapTile formMiniMapTile = new FormMiniMapTile();
    private AbsSpinner rows;
    private AbsSpinner cols;
    private AbsPaintableLabel grid;
    private AbsScrollPane element;
    private AbsButton apply;
    private int rowsCount;
    private int colsCount;
    private final IdList<SubscribedTranslation> translations = new IdList<SubscribedTranslation>();
    public FormMiniMapGrid(AbstractProgramInfos _a, FacadeGame _f, AbsScrollPane _c, AbsCommonFrame _fr, SubscribedTranslationList _i) {
        api = _a;
        facadeGame = _f;
        container = _c;
        frame = _fr;
        translationList = _i;
    }
    public void setupGridDims() {
        rowsCount = NumberUtil.max(facadeGame.getMapHeight(),1);
        colsCount = NumberUtil.max(facadeGame.getMapWidth(),1);
    }

    public void addCells() {
        rowsCount += rows.getValue();
        colsCount += cols.getValue();
        setupGrid(true);
    }
    public void setupGrid(boolean _p) {
        AbsCompoFactory c_ = api.getCompoFactory();
        AbsPanel form_ = c_.newPageBox();
        rows = c_.newSpinner(0,0,Integer.MAX_VALUE,1);
        form_.add(rows);
        cols = c_.newSpinner(0,0,Integer.MAX_VALUE,1);
        form_.add(cols);
        apply = c_.newPlainButton("_");
        apply.addActionListener(new AddCellsMiniMapCoordsListEvent(this));
        form_.add(apply);
        grid = c_.newAbsPaintableLabel();
        AbstractImageFactory imgFact_ = api.getImageFactory();
        int max_ = sideTile();
        AbstractImage img_ = imgFact_.newImageArgb(colsCount * max_, rowsCount * max_);
        for (MiniMapCoordsTile e: facadeGame.getData().getMap().getMiniMap().getList()) {
            img_.drawImage(tryCenter(e.getTileMap(),max_),max_*e.getMiniMapCoords().getXcoords(),max_*e.getMiniMapCoords().getYcoords());
        }
        grid.addMouseListener(new FormTileMiniMapEvent(this));
        grid.setIcon(imgFact_,img_);
        form_.add(grid);
        element = c_.newAbsScrollPane();
        form_.add(element);
        container.setViewportView(form_);
        if (_p) {
            frame.pack();
        }
    }

    private AbstractImage tryCenter(TileMiniMap _e, int _max) {
        int[][] img_ = facadeGame.getData().getMiniMap(_e.getFile());
        if (img_.length == 0) {
            return api.getImageFactory().newImageArgb(_max, _max);
        }
        return ConverterGraphicBufferedImage.centerImage(api.getImageFactory(), img_, _max);
    }
    public void view(int _x, int _y) {
        int max_ = sideTile();
        int i_ = NumberUtil.quot(_x, max_);
        int j_ = NumberUtil.quot(_y, max_);
        formMiniMapTile.build(api,facadeGame,translationList.getImgRetrieverMiniMapSub(),this,i_,j_);
        FormDataMap.baseSelectImage(formMiniMapTile.getFile());
        IdList<SubscribedTranslation> subs_ = translationList.getSubscribedTranslations().getVal(frame);
        subs_.removeAllElements(translations);
        IdList<SubscribedTranslation> next_ = formMiniMapTile.getFile().subs();
        next_.add(new SubscribedTranslationRemovePlace(formMiniMapTile.getPlace()));
        subs_.addAllElts(next_);
        translations.addAllElts(next_);
        MiniMapCoordsTile e_ = facadeGame.getData().getMap().getMiniMap().getEntryByKey(new MiniMapCoords((short) i_, (short) j_));
        if (e_ != null) {
            formMiniMapTile.feedForm(e_.getTileMap());
            formMiniMapTile.getFile().updateValue(e_.getTileMap().getFile());
            formMiniMapTile.getHeros().setSelected(e_.getTileMap().isHeros());
            formMiniMapTile.getPlace().setValue(e_.getTileMap().getPlace());
        } else {
            formMiniMapTile.feedForm();
        }
        element.setViewportView(formMiniMapTile.getForm());
        frame.pack();
    }

    public int sideTile() {
        int max_ = facadeGame.getData().getMap().getSideLength();
        for (ImageArrayBaseSixtyFour i: facadeGame.getData().getMiniMap().values()) {
            max_ = NumberUtil.max(i.getImage().length,max_);
            max_ = NumberUtil.max(i.getImage()[0].length,max_);
        }
        return max_;
    }

    public FormMiniMapTile getFormMiniMapTile() {
        return formMiniMapTile;
    }

    public AbsSpinner getCols() {
        return cols;
    }

    public AbsSpinner getRows() {
        return rows;
    }

    public AbsButton getApply() {
        return apply;
    }

    public AbsPaintableLabel getGrid() {
        return grid;
    }

}
