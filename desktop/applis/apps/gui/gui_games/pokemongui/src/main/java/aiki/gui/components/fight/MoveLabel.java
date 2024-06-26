package aiki.gui.components.fight;



import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.UsesOfMove;
import aiki.game.fight.ChosenMoveInfos;
import aiki.gui.components.AbsMetaLabelPk;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.images.ConverterBufferedImage;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MoveLabel extends AbsMetaLabelPk {

    private static final String SPACE = " ";

    private static final String RATIO = "/";

    private final ChosenMoveInfos infos;

    private boolean selected;

    private final NatStringTreeMap<Integer> colorsTypes;

    private final String text;

    public MoveLabel(ChosenMoveInfos _infos, String _move, FacadeGame _facade, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        colorsTypes = new NatStringTreeMap<Integer>();
        infos = _infos;
        UsesOfMove uses_ = infos.getUses();
        String usesStr_ = StringUtil.concat(Long.toString(uses_.getCurrent()),RATIO,Long.toString(uses_.getMax()));
        StringList types_ = new StringList();
        for (String t: infos.getTypes()) {
            String type_ = _facade.translateType(t);
            String rgb_ = _facade.getData().getTypesColors().getVal(t);
            int c_ = GuiConstants.newColor(ConverterBufferedImage.getIntColor(rgb_, DataBase.SEPARATOR_RGB));
            colorsTypes.put(type_, c_);
            types_.add(type_);
        }
        text = StringUtil.concat(_move,SPACE, StringUtil.join(types_, SPACE),SPACE,usesStr_);
        getPaintableLabel().setOpaque(true);
        setPreferredSize(new MetaDimension(150, 20));
    }

    public void setSelected(String _move) {
        selected = StringUtil.quickEq(infos.getName(), _move);
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        if (infos.isUsable()) {
            _g.setColor(GuiConstants.BLACK);
        } else {
            _g.setColor(GuiConstants.GRAY);
        }
        _g.drawString(text, 0, 10);
        int x_ = 0;
        for (String t: colorsTypes.getKeys()) {
            _g.setColor(colorsTypes.getVal(t));
            int xLeft_ = x_;
            _g.fillRect(xLeft_, 10, 20, 10);
            x_ += 20;
        }
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
