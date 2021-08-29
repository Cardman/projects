package aiki.gui.components.fight;



import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaDimension;

public class PokemonDataRenderer extends CustCellRender<String> {

    private final int sideLength;
    private int height;

    private final FacadeGame facade;

    private String noEvo;

    private String name;

    private boolean selected;

    private AbstractImage pkImage;
    private final AbstractImageFactory fact;

    public PokemonDataRenderer(AbstractImageFactory _fact,FacadeGame _facade, String _noEvo) {
        facade = _facade;
        sideLength = facade.getMap().getSideLength();
        noEvo = _noEvo;
        fact = _fact;
    }

    public void setNoEvo(String _noEvo) {
        noEvo = _noEvo;
    }

    @Override
    public void getListCellRendererComponent(AbsPreparedLabel _currentLab,
                                             int _index,
                                             boolean _isSelected, boolean _cellHasFocus) {
        selected = _isSelected;
        String key_ = get(_index);
        if (!key_.isEmpty()) {
            name = facade.translatePokemon(key_);
            int[][] img_ = facade.getData().getMiniPk().getVal(key_);
            pkImage = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
            height = pkImage.getHeight();
        } else {
            name = DataBase.EMPTY_STRING;
            pkImage = null;
            height = sideLength;
        }
        _currentLab.setPreferredSize(new MetaDimension(100, height));
    }

    @Override
    public AbstractImageFactory getImageFactory() {
        return fact;
    }
    @Override
    public void paintComponent(AbstractImage _g) {
        if (!name.isEmpty()) {
            _g.drawImage(pkImage, 0, 0);
            _g.drawString(name, sideLength, getHeight());
        } else {
            _g.setColor(GuiConstants.WHITE);
            _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            _g.setColor(GuiConstants.BLACK);
            _g.drawString(noEvo, 0, getHeight());
        }
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return 100;
    }
}
