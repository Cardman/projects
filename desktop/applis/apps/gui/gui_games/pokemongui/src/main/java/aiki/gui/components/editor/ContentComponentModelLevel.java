package aiki.gui.components.editor;

import aiki.db.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelLevel {
    private final StringMap<AbsButton> tiles = new StringMap<AbsButton>();
    private FormLevelGrid level;
    private AbsScrollPane fore;
    private final IdList<SubscribedTranslation> translations = new IdList<SubscribedTranslation>();
    private final IdList<SubscribedTranslation> translationsGrid = new IdList<SubscribedTranslation>();
    private Point selected = new Point(0, 0);
    private String key = "";
    private AbsButton removeTile;

    public void setupGridDims(Coords _coords, Place _pl, Level _wild) {
        setupTranslationsGrid(_coords, _pl, _wild);
        fore = level.getApi().getCompoFactory().newAbsScrollPane();
        level.getForm().add(fore);
    }

    public IdList<SubscribedTranslation> setupTranslationsGrid(Coords _coords, Place _pl, Level _wild) {
        Points<Block> blocks_ = ConverterCommonMapUtil.copyPointsBlock(_wild.getBlocks());
        Points<int[][]> frontTiles_ = Level.getLevelForegroundImage(level.getFacadeGame().getData(), _coords, _pl, _wild);
        level.setupGridDims(blocks_, frontTiles_);
        level.setSelectedPlace(_coords);
        IdList<SubscribedTranslation> subs_ = level.getTranslationList().getSubscribedTranslations().getVal(level.getFrame());
        subs_.removeAllElements(translationsGrid);
        IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>();
        next_.add(new RefreshGridSubscription(level.getFacadeGame(),level, _coords, _pl, _wild));
        subs_.addAllElts(next_);
        translationsGrid.addAllElts(next_);
        return subs_;
    }

    public Point viewForeground(int _x, int _y) {
        selected = level.toPt(_x, _y);
        return selected;
    }
    public void choose(String _k) {
        key = _k;
    }

    public void refreshSubs(IdList<SubscribedTranslation> _subs, ScrollCustomComboInt _select) {
        IdList<SubscribedTranslation> subs_ = level.getTranslationList().getSubscribedTranslations().getVal(level.getFrame());
        subs_.removeAllElements(translations);
        IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>(_subs);
        next_.add(new SubscribedTranslationSelectChangeEvtsText(_select));
        subs_.addAllElts(next_);
        translations.addAllElts(next_);
    }

    public void initRemove(AbsPanel _form) {
        AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
        removeTile = compoFactory_.newPlainButton("-");
        _form.add(removeTile);
    }

    public void applySelectItem() {
        level.refreshImg(level.getFormBlockTile().getEdited().getWidth(), level.getFormBlockTile().getEdited().getHeight());
    }

    public static void trySet(ImageArrayBaseSixtyFour _img, Points<int[][]> _level, Point _selected) {
        if (_img == null) {
            _level.put(_selected,new int[0][]);
        } else {
            _level.put(_selected, _img.getImage());
        }
    }

    public void applyTile() {
        key = "";
        level.refreshImg(level.getFormBlockTile().getEdited().getWidth(), level.getFormBlockTile().getEdited().getHeight());
    }

    public void validate() {
        int[][] val_ = level.getForegroundEdited().getVal(selected);
        level.getForegroundEdited().removeKey(selected);
        level.getForeground().put(selected,val_);
    }

    public void removeTile() {
        level.refreshImg(level.getFormBlockTile().getEdited().getWidth(), level.getFormBlockTile().getEdited().getHeight());
    }

    public void removeFore() {
        level.getForegroundEdited().removeKey(selected);
        level.getForeground().removeKey(selected);
    }

    public StringMap<AbsButton> getTiles() {
        return tiles;
    }

    public AbsButton getRemoveTile() {
        return removeTile;
    }

    public FormLevelGrid getLevel() {
        return level;
    }

    public Point getSelected() {
        return selected;
    }

    public AbsScrollPane getFore() {
        return fore;
    }

    public void setFore(AbsScrollPane _f) {
        this.fore = _f;
    }

    public void setLevel(FormLevelGrid _l) {
        this.level = _l;
    }

    public String getKey() {
        return key;
    }

    public IdList<SubscribedTranslation> getTranslations() {
        return translations;
    }

}
