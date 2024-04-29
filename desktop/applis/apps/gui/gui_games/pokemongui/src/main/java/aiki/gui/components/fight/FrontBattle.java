package aiki.gui.components.fight;



import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.*;
import aiki.game.fight.animations.*;
import aiki.gui.WindowAiki;
import aiki.gui.components.AbsMetaLabelPk;
import aiki.gui.dialogs.FrameHtmlData;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaDimension;
import code.maths.LgInt;
import code.util.CustList;
import code.util.*;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class FrontBattle extends AbsMetaLabelPk {

    private static final int NB_IMAGES = 128;

    private static final int NB_IMAGES_SWITCH = 64;

//    private CustList<TargetLabel> foeTargets = new CustList<>();
    private final ByteTreeMap<TargetLabel> foeTargets = new ByteTreeMap<TargetLabel>();

//    private CustList<TargetLabel> playerTargets = new CustList<>();
    private final ByteTreeMap<TargetLabel> playerTargets = new ByteTreeMap<TargetLabel>();

    private int maxWidth;

    private int maxHeight;

    private int imageNumber;

    private final PointBattle ini = new PointBattle();
//    private int xIni;
//
//    private int yIni;

    private final PointBattle end = new PointBattle();
//    private int xEnd;

//    private int yEnd;

    private final PointBattle player = new PointBattle();

//    private int xPlayer;

//    private int yPlayer;

    private final CustList<PointBattle> othersPt = new CustList<PointBattle>();
    private final PointBattle otherPt = new PointBattle();

//    private int xOther;

//    private int yOther;

    private final FacadeGame facade;

    private boolean drawImage;

    private AbstractImage image;

    private boolean drawImages;

    private AbstractImage heros;

    private AbstractImage herosSexOpposite;

    private boolean paintTwoHeros;

    private final CustList<AbstractImage> others = new CustList<AbstractImage>();
    private AbstractImage other;

    private boolean keepAnimation;

    private int mult;

    private String damage = "";

    private boolean paintDefaultEffect;

    private boolean drawBlueRect;

    private boolean playerUser;

    private short groundPlace;

    private boolean heal;

    private boolean recoil;

    private boolean paintBallMove;

    private boolean caught;

    private BoolVal trainer = BoolVal.FALSE;
    private final Battle battle;

    private int index;
    private int countAnim;
    private ByteTreeMap<FighterPosition> currentFoe = new ByteTreeMap<FighterPosition>();

    public FrontBattle(WindowAiki _window, FacadeGame _facade) {
        super(_window.getCompoFactory());
        facade = _facade;
        battle = new Battle(_window, _facade, this);
    }

    public void setTargets() {
        setTargets(facade.getUnionFrontTeam(),facade.getFoeFrontTeam());
    }
    public void setTargets(ByteTreeMap<FighterPosition> _player, ByteTreeMap<FighterPosition> _foe) {
        drawImage = false;
        //wild = false;
        drawBlueRect = false;
        byte mult_ = facade.getFight().getMult();
        mult = mult_;
//        xCoords.clear();
//        yCoords.clear();
//        xCoordsFoe.clear();
//        yCoordsFoe.clear();
        foeTargets.clear();
        playerTargets.clear();
        maxWidth = facade.getMaxWidthPk();
        maxHeight = facade.getMaxHeightPk();
//        ByteTreeMap<FighterPosition> teamPl_ = new ByteTreeMap< FighterPosition>();
//        teamPl_.putAllMap(_player);
        for (EntryCust<Byte, FighterPosition> k: _player.entryList()) {
            TargetLabel target_ = new TargetLabel();
            Fighter fighter_ = k.getValue().getFighter();
            target_.setLevel(fighter_.getLevel());
            target_.setPercentHp(fighter_.rateRemainHp());
            target_.setPercentExp(fighter_.wonExpRate(facade.getData()));
            target_.set(this, true, facade, fighter_.getName());
            if (target_.getFinalWidth() > maxWidth) {
                maxWidth = target_.getFinalWidth();
            }
            if (target_.getFinalHeight() > maxHeight) {
                maxHeight = target_.getFinalHeight();
            }
            playerTargets.put(k.getKey(), target_);
        }
        for (EntryCust<Byte, FighterPosition> k: _foe.entryList()) {
            TargetLabel target_ = new TargetLabel();
            Fighter fighter_ = k.getValue().getFighter();
            target_.setLevel(fighter_.getLevel());
            target_.setPercentHp(fighter_.rateRemainHp());
            target_.setPercentExp(fighter_.wonExpRate(facade.getData()));
            boolean caught_ = facade.getPlayer().estAttrape(fighter_.getName());
            if (caught_) {

                target_.setBall(facade.getData().getBallDef());
            }
            target_.set(this, false, facade, fighter_.getName());
            if (target_.getFinalWidth() > maxWidth) {
                maxWidth = target_.getFinalWidth();
            }
            if (target_.getFinalHeight() > maxHeight) {
                maxHeight = target_.getFinalHeight();
            }
            foeTargets.put(k.getKey(), target_);
        }
        int i_ = IndexConstants.FIRST_INDEX;
        for (TargetLabel t: playerTargets.values()) {
            if (mult_ == 1) {
                t.getPoint().setxPoint(0);
                t.getPoint().setyPoint(maxHeight);
            } else if (mult_ == 2) {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.getPoint().setxPoint(0);
                    t.getPoint().setyPoint(maxHeight);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.getPoint().setxPoint(maxWidth);
                    t.getPoint().setyPoint(maxHeight);
                }
            } else if (mult_ == 3) {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.getPoint().setxPoint(0);
                    t.getPoint().setyPoint(maxHeight * 2);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.getPoint().setxPoint(maxWidth);
                    t.getPoint().setyPoint(maxHeight * 2);
                } else {
                    t.getPoint().setxPoint(maxWidth);
                    t.getPoint().setyPoint(maxHeight * 3);
                }
            } else {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.getPoint().setxPoint(0);
                    t.getPoint().setyPoint(maxHeight * 2);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.getPoint().setxPoint(maxWidth);
                    t.getPoint().setyPoint(maxHeight * 2);
                } else if (i_ == IndexConstants.FIRST_INDEX + 2) {
                    t.getPoint().setxPoint(0);
                    t.getPoint().setyPoint(maxHeight * 3);
                } else {
                    t.getPoint().setxPoint(maxWidth);
                    t.getPoint().setyPoint(maxHeight * 3);
                }
            }
            i_++;
        }
//        for (Byte k: teamPl_.getKeys()) {
//            TargetLabel target_ = new TargetLabel();
//            Fighter fighter_ = teamPl_.getVal(k);
//            target_.setLevel(fighter_.getLevel());
//            target_.setPercentHp(fighter_.rateRemainHp());
//            target_.set(true, _facade, fighter_.getName());
//            maxWidth = target_.getFinalWidth();
//            maxHeight = target_.getFinalHeight();
//            if (mult_ == 1) {
//                target_.setxPoint(0);
//                target_.setyPoint(maxHeight);
////                xCoords.put(k, 0);
////                yCoords.put(k, maxHeight);
//            } else if (mult_ == 2) {
//                if (i_ == CustList.FIRST_INDEX) {
//                    target_.setxPoint(0);
//                    target_.setyPoint(maxHeight);
////                    xCoords.put(k, 0);
////                    yCoords.put(k, maxHeight);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    target_.setxPoint(maxWidth);
//                    target_.setyPoint(maxHeight);
////                    xCoords.put(k, maxWidth);
////                    yCoords.put(k, maxHeight);
//                }
//            } else if (mult_ == 3) {
//                if (i_ == CustList.FIRST_INDEX) {
//                    target_.setxPoint(0);
//                    target_.setyPoint(maxHeight * 2);
////                    xCoords.put(k, 0);
////                    yCoords.put(k, maxHeight * 2);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    target_.setxPoint(maxWidth);
//                    target_.setyPoint(maxHeight * 2);
////                    xCoords.put(k, maxWidth);
////                    yCoords.put(k, maxHeight * 2);
//                } else {
//                    target_.setxPoint(maxWidth);
//                    target_.setyPoint(maxHeight * 3);
////                    xCoords.put(k, maxWidth);
////                    yCoords.put(k, maxHeight * 3);
//                }
//            } else {
//                if (i_ == CustList.FIRST_INDEX) {
//                    target_.setxPoint(0);
//                    target_.setyPoint(maxHeight * 2);
////                    xCoords.put(k, 0);
////                    yCoords.put(k, maxHeight * 2);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    target_.setxPoint(maxWidth);
//                    target_.setyPoint(maxHeight * 2);
////                    xCoords.put(k, maxWidth);
////                    yCoords.put(k, maxHeight * 2);
//                } else if (i_ == CustList.FIRST_INDEX + 2) {
//                    target_.setxPoint(0);
//                    target_.setyPoint(maxHeight * 3);
////                    xCoords.put(k, 0);
////                    yCoords.put(k, maxHeight * 3);
//                } else {
//                    target_.setxPoint(maxWidth);
//                    target_.setyPoint(maxHeight * 3);
////                    xCoords.put(k, maxWidth);
////                    yCoords.put(k, maxHeight * 3);
//                }
//            }
//            playerTargets.put(k, target_);
//            i_++;
//        }
        i_ = IndexConstants.FIRST_INDEX;
        for (TargetLabel t: foeTargets.values()) {
//            if (mult_ == 1) {
//                t.setxPoint(0);
//                t.setyPoint(maxHeight);
//            } else if (mult_ == 2) {
//                if (i_ == CustList.FIRST_INDEX) {
//                    t.setxPoint(0);
//                    t.setyPoint(maxHeight);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    t.setxPoint(maxWidth);
//                    t.setyPoint(maxHeight);
//                }
//            } else if (mult_ == 3) {
//                if (i_ == CustList.FIRST_INDEX) {
//                    t.setxPoint(0);
//                    t.setyPoint(maxHeight * 2);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    t.setxPoint(maxWidth);
//                    t.setyPoint(maxHeight * 2);
//                } else {
//                    t.setxPoint(maxWidth);
//                    t.setyPoint(maxHeight * 3);
//                }
//            } else {
//                if (i_ == CustList.FIRST_INDEX) {
//                    t.setxPoint(0);
//                    t.setyPoint(maxHeight * 2);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    t.setxPoint(maxWidth);
//                    t.setyPoint(maxHeight * 2);
//                } else if (i_ == CustList.FIRST_INDEX + 2) {
//                    t.setxPoint(0);
//                    t.setyPoint(maxHeight * 3);
//                } else {
//                    t.setxPoint(maxWidth);
//                    t.setyPoint(maxHeight * 3);
//                }
//            }
            if (mult_ == 1) {
                t.getPoint().setxPoint(maxWidth);
                t.getPoint().setyPoint(0);
            } else if (mult_ == 2) {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.getPoint().setxPoint(maxWidth * 2);
                    t.getPoint().setyPoint(0);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.getPoint().setxPoint(maxWidth * 3);
                    t.getPoint().setyPoint(0);
                }
            } else if (mult_ == 3) {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.getPoint().setxPoint(maxWidth * 2);
                    t.getPoint().setyPoint(0);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.getPoint().setxPoint(maxWidth * 2);
                    t.getPoint().setyPoint(maxHeight);
                } else {
                    t.getPoint().setxPoint(maxWidth * 3);
                    t.getPoint().setyPoint(maxHeight);
                }
            } else {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.getPoint().setxPoint(maxWidth * 2);
                    t.getPoint().setyPoint(0);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.getPoint().setxPoint(maxWidth * 3);
                    t.getPoint().setyPoint(0);
                } else if (i_ == IndexConstants.FIRST_INDEX + 2) {
                    t.getPoint().setxPoint(maxWidth * 2);
                    t.getPoint().setyPoint(maxHeight);
                } else {
                    t.getPoint().setxPoint(maxWidth * 3);
                    t.getPoint().setyPoint(maxHeight);
                }
            }
            i_++;
        }
//        for (Byte k: teamFoe_.getKeys()) {
//            TargetLabel target_ = new TargetLabel();
//            Fighter fighter_ = teamFoe_.getVal(k);
//            target_.setLevel(fighter_.getLevel());
//            target_.setPercentHp(fighter_.rateRemainHp());
//            target_.set(false, _facade, fighter_.getName());
//            maxWidth = target_.getFinalWidth();
//            maxHeight = target_.getFinalHeight();
//            if (mult_ == 1) {
//                target_.setxPoint(0);
//                target_.setyPoint(maxHeight);
////                xCoords.put(k, 0);
////                yCoords.put(k, maxHeight);
//            } else if (mult_ == 2) {
//                if (i_ == CustList.FIRST_INDEX) {
//                    target_.setxPoint(0);
//                    target_.setyPoint(maxHeight);
////                    xCoords.put(k, 0);
////                    yCoords.put(k, maxHeight);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    target_.setxPoint(maxWidth);
//                    target_.setyPoint(maxHeight);
////                    xCoords.put(k, maxWidth);
////                    yCoords.put(k, maxHeight);
//                }
//            } else if (mult_ == 3) {
//                if (i_ == CustList.FIRST_INDEX) {
//                    target_.setxPoint(0);
//                    target_.setyPoint(maxHeight * 2);
////                    xCoords.put(k, 0);
////                    yCoords.put(k, maxHeight * 2);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    target_.setxPoint(maxWidth);
//                    target_.setyPoint(maxHeight * 2);
////                    xCoords.put(k, maxWidth);
////                    yCoords.put(k, maxHeight * 2);
//                } else {
//                    target_.setxPoint(maxWidth);
//                    target_.setyPoint(maxHeight * 3);
////                    xCoords.put(k, maxWidth);
////                    yCoords.put(k, maxHeight * 3);
//                }
//            } else {
//                if (i_ == CustList.FIRST_INDEX) {
//                    target_.setxPoint(0);
//                    target_.setyPoint(maxHeight * 2);
////                    xCoords.put(k, 0);
////                    yCoords.put(k, maxHeight * 2);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    target_.setxPoint(maxWidth);
//                    target_.setyPoint(maxHeight * 2);
////                    xCoords.put(k, maxWidth);
////                    yCoords.put(k, maxHeight * 2);
//                } else if (i_ == CustList.FIRST_INDEX + 2) {
//                    target_.setxPoint(0);
//                    target_.setyPoint(maxHeight * 3);
////                    xCoords.put(k, 0);
////                    yCoords.put(k, maxHeight * 3);
//                } else {
//                    target_.setxPoint(maxWidth);
//                    target_.setyPoint(maxHeight * 3);
////                    xCoords.put(k, maxWidth);
////                    yCoords.put(k, maxHeight * 3);
//                }
//            }
//            if (mult_ == 1) {
//                xCoordsFoe.put(k, maxWidth);
//                yCoordsFoe.put(k, 0);
//            } else if (mult_ == 2) {
//                if (i_ == CustList.FIRST_INDEX) {
//                    xCoordsFoe.put(k, maxWidth * 2);
//                    yCoordsFoe.put(k, 0);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    xCoordsFoe.put(k, maxWidth * 3);
//                    yCoordsFoe.put(k, 0);
//                }
//            } else if (mult_ == 3) {
//                if (i_ == CustList.FIRST_INDEX) {
//                    xCoordsFoe.put(k, maxWidth * 2);
//                    yCoordsFoe.put(k, 0);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    xCoordsFoe.put(k, maxWidth * 2);
//                    yCoordsFoe.put(k, maxHeight);
//                } else {
//                    xCoordsFoe.put(k, maxWidth * 3);
//                    yCoordsFoe.put(k, maxHeight);
//                }
//            } else {
//                if (i_ == CustList.FIRST_INDEX) {
//                    xCoordsFoe.put(k, maxWidth * 2);
//                    yCoordsFoe.put(k, 0);
//                } else if (i_ == CustList.FIRST_INDEX + 1) {
//                    xCoordsFoe.put(k, maxWidth * 3);
//                    yCoordsFoe.put(k, 0);
//                } else if (i_ == CustList.FIRST_INDEX + 2) {
//                    xCoordsFoe.put(k, maxWidth * 2);
//                    yCoordsFoe.put(k, maxHeight);
//                } else {
//                    xCoordsFoe.put(k, maxWidth * 3);
//                    yCoordsFoe.put(k, maxHeight);
//                }
//            }
//            foeTargets.put(k, target_);
//            i_++;
//        }
        if (mult_ == 1) {
            setPreferredSize(new MetaDimension(maxWidth * 2, maxHeight * 2));
        } else if (mult_ == 2) {
            setPreferredSize(new MetaDimension(maxWidth * 4, maxHeight * 2));
        } else {
            setPreferredSize(new MetaDimension(maxWidth * 4, maxHeight * 4));
        }
        //placeLabels(mult_);
    }

//    void initRoundAnimation() {
//        koFoeTargets.clear();
//        koPlayerTargets.clear();
//    }


    void setCountAnim(int _c) {
        this.countAnim = _c;
    }

    void drawAnimationInstantInitial(AnimationInt _animation) {
        paintBallMove = false;
        keepAnimation = true;
        paintDefaultEffect = false;
        drawBlueRect = false;
        heal = false;
        recoil = false;
        damage = DataBase.EMPTY_STRING;
        if (_animation instanceof AnimationEffect) {
            AnimationEffect animation_ = (AnimationEffect) _animation;
            index = animation_.getIndex();
            if (animation_.getEffectKind() == EffectKind.CHANGED_PLACE) {
                if (animation_.isPlayerFromFighter()) {
                    TargetLabel user_ = playerTargets.getVal((byte) animation_.getFromFighter().getPosition());
                    TargetLabel target_ = playerTargets.getVal((byte) animation_.getToFighter().getPosition());
                    String name_ = user_.getFighterName();
                    short level_ = user_.getLevel();
                    LgInt rateRemaingHp_ = user_.getPercentHp();
                    LgInt percentExp_ = user_.getPercentExp();
                    user_.setLevel(target_.getLevel());
                    user_.setPercentHp(target_.getPercentHp());
                    user_.setPercentExp(target_.getPercentExp());
                    user_.set(this, true, facade, target_.getFighterName());
                    target_.setLevel(level_);
                    target_.setPercentHp(rateRemaingHp_);
                    target_.setPercentExp(percentExp_);
                    target_.set(this, true, facade, name_);
                } else {
                    TargetLabel user_ = foeTargets.getVal((byte) animation_.getFromFighter().getPosition());
                    TargetLabel target_ = foeTargets.getVal((byte) animation_.getToFighter().getPosition());
                    String name_ = user_.getFighterName();
                    short level_ = user_.getLevel();
                    LgInt rateRemaingHp_ = user_.getPercentHp();
                    LgInt percentExp_ = user_.getPercentExp();
                    user_.setLevel(target_.getLevel());
                    user_.setPercentHp(target_.getPercentHp());
                    user_.setPercentExp(target_.getPercentExp());
                    user_.set(this, false, facade, target_.getFighterName());
                    target_.setLevel(level_);
                    target_.setPercentHp(rateRemaingHp_);
                    target_.setPercentExp(percentExp_);
                    target_.set(this, false, facade, name_);
                }
//            if (!animation_.getSubstitute().isEmpty()) {
//                TargetLabel label_;
//                if (animation_.isPlayerFromFighter()) {
//                    label_ = playerTargets.get(animation_.getFromFighter().getPosition());
//                } else {
//                    label_ = foeTargets.get(animation_.getFromFighter().getPosition());
//                }
//                label_.set(animation_.isPlayerFromFighter(), facade, animation_.getSubstitute());
//            }
//            if (animation_.isPlayerTarget()) {
//                xCoords.put(arg0, arg1)
//                xIni_ = xCoords.getVal((byte) animation_.getUser().getPosition());
//                yIni_ = yCoords.getVal((byte) animation_.getUser().getPosition());
//            } else {
//                xIni_ = xCoordsFoe.getVal((byte) animation_.getUser().getPosition());
//                yIni_ = yCoordsFoe.getVal((byte) animation_.getUser().getPosition());
//            }
                //refresh fighters
                //setTargets(facade);
                keepAnimation = false;
            } else {
                if (animation_.isPlayerFromFighter()) {
                    TargetLabel tar_ = playerTargets.getVal((byte) animation_.getFromFighter().getPosition());
                    ini.setxPoint(tar_.getPoint().getxPoint());
                    ini.setyPoint(tar_.getPoint().getyPoint());
//                xIni = xCoords.getVal((byte) animation_.getFromFighter().getPosition());
//                yIni = yCoords.getVal((byte) animation_.getFromFighter().getPosition());
                } else {
                    TargetLabel tar_ = foeTargets.getVal((byte) animation_.getFromFighter().getPosition());
                    ini.setxPoint(tar_.getPoint().getxPoint());
                    ini.setyPoint(tar_.getPoint().getyPoint());
//                xIni = xCoordsFoe.getVal((byte) animation_.getFromFighter().getPosition());
//                yIni = yCoordsFoe.getVal((byte) animation_.getFromFighter().getPosition());
                }
            }
        } else if (_animation instanceof AnimationSwitch) {
            AnimationSwitch animation_ = (AnimationSwitch) _animation;
            index = animation_.getIndex();
            if (animation_.isPlayer()) {
                TargetLabel tar_ = playerTargets.getVal((byte) animation_.getSubstituted().getPosition());
                ini.setxPoint(tar_.getPoint().getxPoint());
                ini.setyPoint(tar_.getPoint().getyPoint());
//                xIni = xCoords.getVal((byte) animation_.getSubstituted().getPosition());
//                yIni = yCoords.getVal((byte) animation_.getSubstituted().getPosition());
            } else {
                TargetLabel tar_ = foeTargets.getVal((byte) animation_.getSubstituted().getPosition());
                ini.setxPoint(tar_.getPoint().getxPoint());
                ini.setyPoint(tar_.getPoint().getyPoint());
//                xIni = xCoordsFoe.getVal((byte) animation_.getSubstituted().getPosition());
//                yIni = yCoordsFoe.getVal((byte) animation_.getSubstituted().getPosition());
            }
        } else if (_animation instanceof AnimationHealing) {
            heal = true;
            AnimationHealing animation_ = (AnimationHealing) _animation;
            if (animation_.isBackOrTeam()) {
                heal = false;
            } else if (animation_.isPlayer()) {
                TargetLabel tar_ = playerTargets.getVal((byte) animation_.getHealed().getPosition());
                ini.setxPoint(tar_.getPoint().getxPoint());
                ini.setyPoint(tar_.getPoint().getyPoint());
//                xIni = xCoords.getVal((byte) animation_.getHealed().getPosition());
//                yIni = yCoords.getVal((byte) animation_.getHealed().getPosition());
            } else {
                TargetLabel tar_ = foeTargets.getVal((byte) animation_.getHealed().getPosition());
                ini.setxPoint(tar_.getPoint().getxPoint());
                ini.setyPoint(tar_.getPoint().getyPoint());
//                xIni = xCoordsFoe.getVal((byte) animation_.getHealed().getPosition());
//                yIni = yCoordsFoe.getVal((byte) animation_.getHealed().getPosition());
            }
        } else if (_animation instanceof AnimationAutoEffect) {
            index = _animation.getIndex();
            recoil = ((AnimationAutoEffect) _animation).getAutoEffectKind() == AutoEffectKind.RECOIL;
            AnimationAutoEffect animation_ = (AnimationAutoEffect) _animation;
            if (animation_.isPlayer()) {
                TargetLabel tar_ = playerTargets.getVal((byte) animation_.getUser().getPosition());
                ini.setxPoint(tar_.getPoint().getxPoint());
                ini.setyPoint(tar_.getPoint().getyPoint());
//                xIni = xCoords.getVal((byte) animation_.getUser().getPosition());
//                yIni = yCoords.getVal((byte) animation_.getUser().getPosition());
            } else {
                TargetLabel tar_ = foeTargets.getVal((byte) animation_.getUser().getPosition());
                ini.setxPoint(tar_.getPoint().getxPoint());
                ini.setyPoint(tar_.getPoint().getyPoint());
//                xIni = xCoordsFoe.getVal((byte) animation_.getUser().getPosition());
//                yIni = yCoordsFoe.getVal((byte) animation_.getUser().getPosition());
            }
        } else {
            index = 0;
        }
        imageNumber = 0;
        ini.addx(maxWidth / 2);
        ini.addy(maxHeight / 2);
        AbsMetaLabelPk.paintPk(battle.getWindow().getImageFactory(), this);
    }

    void drawAnimationInstant(AbstractImageFactory _fact, AnimationInt _animation) {
        paintBallMove = false;
        drawImage = true;
        imageNumber++;
        int xEnd_;
        int yEnd_;
        if (_animation instanceof AnimationEffect) {
            AnimationEffect animation_ = (AnimationEffect) _animation;
            TargetLabel tar_ = target(animation_.isPlayerToFighter(), playerTargets, foeTargets, animation_.getToFighter().getPosition());
            xEnd_ = tar_.getPoint().getxPoint();
            yEnd_ = tar_.getPoint().getyPoint();
            end.setxPoint(xEnd_);
            end.setyPoint(yEnd_);
            xEnd_ += maxWidth / 2;
            yEnd_ += maxHeight / 2;
            int remainImages_ = NB_IMAGES - imageNumber;
            if (remainImages_ > 0) {
                int xDelta_ = (xEnd_ - ini.getxPoint()) / remainImages_;
                int yDelta_ = (yEnd_ - ini.getyPoint()) / remainImages_;
                ini.addx(xDelta_);
                ini.addy(yDelta_);
            } else {
                //draw red cross if ko target
                if (animation_.isKoToFighter()) {
                    if (animation_.isPlayerToFighter()) {
                        playerTargets.getVal((byte) animation_.getToFighter().getPosition()).setKo(true);
                        playerTargets.getVal((byte) animation_.getToFighter().getPosition()).apply(this, facade);
                        //koPlayerTargets.add((byte) animation_.getToFighter().getPosition());
                    } else {
                        foeTargets.getVal((byte) animation_.getToFighter().getPosition()).setKo(true);
                        foeTargets.getVal((byte) animation_.getToFighter().getPosition()).apply(this, facade);
                        //koFoeTargets.add((byte) animation_.getToFighter().getPosition());
                    }
                }
                if (animation_.isKoFromFighter()) {
                    if (animation_.isPlayerFromFighter()) {
                        playerTargets.getVal((byte) animation_.getFromFighter().getPosition()).setKo(true);
                        playerTargets.getVal((byte) animation_.getFromFighter().getPosition()).apply(this, facade);
                        //koPlayerTargets.add((byte) animation_.getFromFighter().getPosition());
                    } else {
                        foeTargets.getVal((byte) animation_.getFromFighter().getPosition()).setKo(true);
                        foeTargets.getVal((byte) animation_.getFromFighter().getPosition()).apply(this, facade);
                        //koFoeTargets.add((byte) animation_.getFromFighter().getPosition());
                    }
                }
                keepAnimation = false;
            }
        } else if (_animation instanceof AnimationSwitch) {
            AnimationSwitch animation_ = (AnimationSwitch) _animation;
            int remainImages_ = NB_IMAGES_SWITCH - imageNumber;
            if (remainImages_ > 0) {
                if (remainImages_ < NB_IMAGES_SWITCH * 3/4) {
                    if (remainImages_ >= NB_IMAGES_SWITCH / 4) {
                        //nothing
                        TargetLabel label_ = target(animation_.isPlayer(), playerTargets, foeTargets, animation_.getSubstituted().getPosition());
                        label_.setLevel(DataBase.INVALID_LEVEL);
                        label_.setBall(DataBase.EMPTY_STRING);
                        label_.setPercentHp(LgInt.zero());
                        label_.setPercentExp(LgInt.zero());
                        label_.setKo(false);
                        label_.set(this, animation_.isPlayer(), facade, DataBase.EMPTY_STRING);
                    } else {
                        //substitute
                        TargetLabel label_;
                        if (animation_.isPlayer()) {
                            label_ = playerTargets.getVal((byte) animation_.getSubstituted().getPosition());
                        } else {
                            label_ = foeTargets.getVal((byte) animation_.getSubstituted().getPosition());
                            boolean caught_ = facade.getPlayer().estAttrape(animation_.getSubstituteName());
                            if (caught_) {

                                label_.setBall(facade.getData().getBallDef());
                            }
                        }
                        label_.setLevel(animation_.getLevel());
                        label_.setPercentHp(animation_.getRateRemainHp());
                        label_.setPercentExp(animation_.getWonExpRate());
                        label_.setKo(animation_.isKo());
                        label_.set(this, animation_.isPlayer(), facade, animation_.getSubstituteName());
                    }
                }
            } else {
                keepAnimation = false;
                //ko substitute
                if (animation_.isKo()) {
                    //draw red cross
                    if (animation_.isPlayer()) {
                        //koPlayerTargets.add((byte) animation_.getSubstituted().getPosition());
                        playerTargets.getVal((byte) animation_.getSubstituted().getPosition()).setKo(true);
                        playerTargets.getVal((byte) animation_.getSubstituted().getPosition()).apply(this, facade);
                    } else {
                        //koFoeTargets.add((byte) animation_.getSubstituted().getPosition());
                        foeTargets.getVal((byte) animation_.getSubstituted().getPosition()).setKo(true);
                        foeTargets.getVal((byte) animation_.getSubstituted().getPosition()).apply(this, facade);
                    }
                }
            }
        } else if (_animation instanceof AnimationHealing) {
            int remainImages_ = NB_IMAGES_SWITCH - imageNumber;
            if (remainImages_ <= 0) {
                keepAnimation = false;
                heal = false;
            }
        } else if (_animation instanceof AnimationAutoEffect) {
            int remainImages_ = NB_IMAGES_SWITCH - imageNumber;
            if (remainImages_ <= 0) {
                keepAnimation = false;
                recoil = false;
            }
        }
        if (_animation instanceof AnimationEffectDamage) {
            AnimationEffectDamage damage_ = (AnimationEffectDamage) _animation;
            StringMap<AbstractImage> types_ = new StringMap<AbstractImage>();
            for (String t: damage_.getTypes()) {
                int[][] type_ = facade.getData().getTypesImages().getVal(t);
                AbstractImage t_ = battle.getWindow().getTileRender().render(battle.getWindow().getImageFactory(), type_,1,1);
                types_.put(t, t_);
            }
            int hMax_ = 0;
            int width_ = 0;
            for (AbstractImage i: types_.values()) {
                if (i.getHeight() > hMax_) {
                    hMax_ = i.getHeight();
                }
                width_ += i.getWidth();
            }
            hMax_ += heightFont();
            damage = damage_.getDamage().evaluate(4);
            int strWidth_ = stringWidth(damage);
            if (strWidth_ > width_) {
                width_ = strWidth_;
            }
            image = _fact.newImageArgb(width_, hMax_);
            image.setFont(getMetaFont());
//            CustGraphics gr_ = image.getGraphics();
            image.setColor(GuiConstants.WHITE);
            image.fillRect(0, 0, width_, hMax_);
            int x_ = 0;
            for (AbstractImage i: types_.values()) {
                image.drawImage(i, x_, 0);
                x_ += i.getWidth();
            }
            image.setColor(GuiConstants.BLACK);
            image.drawString(damage, 0, hMax_);
        } else if (_animation instanceof AnimationEffectStatistic) {
            AnimationEffectStatistic statis_ = (AnimationEffectStatistic) _animation;
            CustList<AbstractImage> types_ = new CustList<AbstractImage>();
            int h_ = heightFont();
            int statSide_ = facade.getMap().getSideLength();
            for (InfosAnimationStatistic t: statis_.getInfos()) {
                int[][] type_ = facade.getData().getAnimStatis().getVal(t.getStatistic().name());
                AbstractImage t_ = battle.getWindow().getTileRender().render(battle.getWindow().getImageFactory(), type_,statSide_,statSide_);
                String var_ = Long.toString(t.getVariation());
                int widthVar_ = stringWidth(var_);
                if (widthVar_ < statSide_) {
                    widthVar_ = statSide_;
                }
                AbstractImage varStat_ = _fact.newImageArgb(widthVar_, h_ + statSide_);
                varStat_.setFont(getMetaFont());
//                CustGraphics g_ = varStat_.createGraphics();
                varStat_.drawImage(t_, 0, 0);
                varStat_.setColor(GuiConstants.BLACK);
                varStat_.drawString(var_, 0, statSide_ + h_);
                varStat_.dispose();
                types_.add(varStat_);
            }
            if (TargetCoords.eq(statis_.getFromFighter(), statis_.getToFighter())) {
                TargetLabel label_ = target(statis_.isPlayerFromFighter(), playerTargets, foeTargets, statis_.getFromFighter().getPosition());
                if (keepAnimation) {
                    label_.setStatistics(types_);
                } else {
                    label_.setStatistics(new CustList<AbstractImage>());
                }
                label_.apply(this, facade);
                drawImage = false;
                imageNumber++;
                imageNumber++;
                imageNumber++;
                //decrease nb images
            } else {
                int hMax_ = 0;
                int width_ = 0;
                for (AbstractImage i: types_) {
                    if (i.getHeight() > hMax_) {
                        hMax_ = i.getHeight();
                    }
                    width_ += i.getWidth();
                }
                if (width_ > 0) {
                    image = _fact.newImageArgb(width_, hMax_);
                    image.setFont(getMetaFont());
//                    CustGraphics g_ = image.createGraphics();
                    image.setColor(GuiConstants.WHITE);
                    image.fillRect(0, 0, width_, hMax_);
                    int x_ = 0;
                    for (AbstractImage i: types_) {
                        image.drawImage(i, x_, 0);
                        x_ += i.getWidth();
                    }
                    image.dispose();
                } else {
                    paintDefaultEffect = true;
                }
            }
        } else if (_animation instanceof AnimationEffectStatus) {
            AnimationEffectStatus status_ = (AnimationEffectStatus) _animation;
            if (!status_.getStatus().isEmpty()) {
                int statSide_ = facade.getMap().getSideLength();
                if (TargetCoords.eq(status_.getFromFighter(), status_.getToFighter())) {
                    int[][] stTxt_ = facade.getData().getAnimStatus().getVal(status_.getStatus());
                    AbstractImage image_ = battle.getWindow().getTileRender().render(battle.getWindow().getImageFactory(), stTxt_, statSide_, statSide_);
                    TargetLabel label_ = target(status_.isPlayerFromFighter(), playerTargets, foeTargets, status_.getFromFighter().getPosition());
                    if (keepAnimation) {
                        label_.setStatistics(new CustList<AbstractImage>(image_));
                    } else {
                        label_.setStatistics(new CustList<AbstractImage>());
                    }
                    label_.apply(this, facade);
                    drawImage = false;
                    imageNumber++;
                    imageNumber++;
                    imageNumber++;
                } else {
                    int[][] stTxt_ = facade.getData().getAnimStatus().getVal(status_.getStatus());
                    image = battle.getWindow().getTileRender().render(battle.getWindow().getImageFactory(), stTxt_, statSide_, statSide_);
                }
            } else {
                paintDefaultEffect = true;
            }
        } else if (_animation instanceof AnimationEffect) {
            AnimationEffect e_ = (AnimationEffect) _animation;
            if (e_.getEffectKind() == EffectKind.ABSORB) {
                int statSide_ = facade.getMap().getSideLength();
                int[][] stTxt_ = facade.getData().getAnimAbsorb();
                image = battle.getWindow().getTileRender().render(battle.getWindow().getImageFactory(), stTxt_, statSide_, statSide_);
            } else {
                if (TargetCoords.eq(e_.getFromFighter(), e_.getToFighter())) {
                    drawBlueRect = true;
                    playerUser = e_.isPlayerFromFighter();
                    groundPlace = e_.getFromFighter().getPosition();
                    drawImage = false;
                    imageNumber++;
                    imageNumber++;
                    imageNumber++;
                } else {
                    paintDefaultEffect = true;
                }
//            image = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
//            image.getGraphics().setColor(Color.WHITE);
//            image.getGraphics().fillRect(0, 0, 20, 20);
//            image.getGraphics().setColor(new Color(Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue(), 255));
//            image.getGraphics().drawRect(0, 0, 20, 20);
            }
        } else if (_animation instanceof AnimationAutoEffect) {
            AnimationAutoEffect animation_ = (AnimationAutoEffect) _animation;
            if (animation_.isKoUser()) {
                //draw red cross
                recoil = false;
                if (animation_.isPlayer()) {
                    //koPlayerTargets.add((byte) animation_.getUser().getPosition());
                    playerTargets.getVal((byte) animation_.getUser().getPosition()).setKo(true);
                    playerTargets.getVal((byte) animation_.getUser().getPosition()).apply(this, facade);
                } else {
                    //koFoeTargets.add((byte) animation_.getUser().getPosition());
                    foeTargets.getVal((byte) animation_.getUser().getPosition()).setKo(true);
                    foeTargets.getVal((byte) animation_.getUser().getPosition()).apply(this, facade);
                }
            }
        }
//        koPlayerTargets.removeDuplicates();
//        koFoeTargets.removeDuplicates();
        AbsMetaLabelPk.paintPk(battle.getWindow().getImageFactory(), this);
        /*if (_animation instanceof AnimationEffectDamage) {
            getGraphics().setColor(new Color(Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue(), 255));
            getGraphics().drawString(number_, xIni, hMax_ + yIni);
        }*/
        //xIni, yIni
    }

    void translate() {
        for (TargetLabel t: foeTargets.values()) {
            t.apply(this, facade);
        }
        for (TargetLabel t: playerTargets.values()) {
            t.apply(this, facade);
        }
        AbsMetaLabelPk.paintPk(battle.getWindow().getImageFactory(), this);
    }

    void setHerosOppositeSex(AbstractImage _oppositeSex, boolean _paintTwoHeros) {
        herosSexOpposite = _oppositeSex;
        paintTwoHeros = _paintTwoHeros;
    }

    void drawAnimationFightIni(AbstractImage _heros, CustList<AbstractImage> _other) {
        trainer = BoolVal.FALSE;
        others.clear();
        others.addAllElts(_other);
        other = null;
        drawAnimationFightIni(_heros);
    }

    void drawAnimationFightIni(AbstractImage _heros, AbstractImage _other) {
        trainer = BoolVal.TRUE;
        others.clear();
        other = _other;
        drawAnimationFightIni(_heros);
    }

    void drawAnimationFightIni(AbstractImage _heros) {
        heros = _heros;
        paintBallMove = false;
        keepAnimation = true;
        paintDefaultEffect = false;
        drawBlueRect = false;
        heal = false;
        recoil = false;
        initOthersPt();
//        xOther = 0;
//        yOther = 0;
        if (mult == 1) {
            player.setxPoint(maxWidth);
            player.setyPoint(maxHeight);
        } else if (mult == 2) {
            player.setxPoint(maxWidth * 3);
            player.setyPoint(maxHeight);
        } else {
            player.setxPoint(maxWidth * 3);
            player.setyPoint(maxHeight * 3);
        }
        imageNumber = 0;
        AbsMetaLabelPk.paintPk(battle.getWindow().getImageFactory(), this);
    }

    private void initOthersPt() {
        otherPt.setxPoint(0);
        otherPt.setyPoint(0);
        othersPt.clear();
        int mult_ = others.size();
        for (int i = 0; i < mult_; i++) {
            initOtherPt(mult_, i, 0, 0, 0, 0);
        }
    }

    private void initOtherPt(int _mult, int _i, int _one, int _two, int _three, int _four) {
        if (_mult == 1) {
            PointBattle pt_ = new PointBattle();
            pt_.setxPoint(_one);
            othersPt.add(pt_);
            return;
        }
        if (_mult == 2) {
            PointBattle pt_ = new PointBattle();
            if (_i == IndexConstants.FIRST_INDEX) {
                pt_.setxPoint(_two);
            } else {
                pt_.setxPoint(maxWidth+_two);
            }
            othersPt.add(pt_);
            return;
        }
        if (_mult == 3) {
            if (_i == IndexConstants.FIRST_INDEX) {
                PointBattle pt_ = new PointBattle();
                pt_.setxPoint(_three);
                othersPt.add(pt_);
            } else if (_i == IndexConstants.FIRST_INDEX + 1) {
                PointBattle pt_ = new PointBattle();
                pt_.setxPoint(_three);
                pt_.setyPoint(maxHeight);
                othersPt.add(pt_);
            } else {
                PointBattle pt_ = new PointBattle();
                pt_.setxPoint(_three+maxWidth);
                pt_.setyPoint(maxHeight);
                othersPt.add(pt_);
            }
            return;
        }
        if (_i == IndexConstants.FIRST_INDEX) {
            PointBattle pt_ = new PointBattle();
            pt_.setxPoint(_four);
            othersPt.add(pt_);
        } else if (_i == IndexConstants.FIRST_INDEX + 1) {
            PointBattle pt_ = new PointBattle();
            pt_.setxPoint(_four+maxWidth);
            othersPt.add(pt_);
        } else if (_i == IndexConstants.FIRST_INDEX + 2) {
            PointBattle pt_ = new PointBattle();
            pt_.setxPoint(_four);
            pt_.setyPoint(maxHeight);
            othersPt.add(pt_);
        } else {
            PointBattle pt_ = new PointBattle();
            pt_.setxPoint(_four+maxWidth);
            pt_.setyPoint(maxHeight);
            othersPt.add(pt_);
        }
    }

    void drawAnimationFightIniInst() {
        paintBallMove = false;
        drawImages = true;
        imageNumber++;
        int xEndPlayer_ = 0;
        int remainImages_ = NB_IMAGES - imageNumber;
        if (remainImages_ <= 0) {
            keepAnimation = false;
            drawImages = false;
            AbsMetaLabelPk.paintPk(battle.getWindow().getImageFactory(), this);
            return;
        }
        int xDelta_ = (xEndPlayer_ - player.getxPoint()) / remainImages_;
        player.addx(xDelta_);
        if (trainer == BoolVal.TRUE) {
            int xEndOther_;
            if (mult == 1) {
                xEndOther_ = maxWidth;
            } else {
                xEndOther_ = maxWidth * 3;
            }
            otherPt.addx((xEndOther_ - otherPt.getxPoint()) / remainImages_);
            AbsMetaLabelPk.paintPk(battle.getWindow().getImageFactory(), this);
            return;
        }
        int s_ = othersPt.size();
        for (int i = 0; i < s_; i++) {
            moveHorizontally(remainImages_, i);
        }

        AbsMetaLabelPk.paintPk(battle.getWindow().getImageFactory(), this);
    }

    private void moveHorizontally(int _remainImages, int _i) {
        int s_ = othersPt.size();
        if (s_ == 1) {
            othersPt.get(_i).addx((maxWidth - othersPt.get(_i).getxPoint()) / _remainImages);
        } else if (s_ == 2){
            if (_i == 0) {
                othersPt.get(_i).addx((2 * maxWidth - othersPt.get(_i).getxPoint()) / _remainImages);
            } else {
                othersPt.get(_i).addx((3 * maxWidth - othersPt.get(_i).getxPoint()) / _remainImages);
            }
        } else if (s_ == 3){
            if (_i <= IndexConstants.FIRST_INDEX + 1) {
                othersPt.get(_i).addx((2 * maxWidth - othersPt.get(_i).getxPoint()) / _remainImages);
            } else {
                othersPt.get(_i).addx((3 * maxWidth - othersPt.get(_i).getxPoint()) / _remainImages);
            }
        } else {
            if (_i % 2 == 0) {
                othersPt.get(_i).addx((2 * maxWidth - othersPt.get(_i).getxPoint()) / _remainImages);
            } else {
                othersPt.get(_i).addx((3 * maxWidth - othersPt.get(_i).getxPoint()) / _remainImages);
            }
        }
    }

    public void initBall(ByteTreeMap<FighterPosition> _playerFighters,ByteTreeMap<FighterPosition> _foeFighters) {
        currentFoe = _foeFighters;
        setTargets(_playerFighters,_foeFighters);
        paintBallMove = true;
        keepAnimation = true;
        drawBlueRect = false;
//        xIni = xCoords.getVal((byte) CustList.FIRST_INDEX);
//        yIni = yCoords.getVal((byte) CustList.FIRST_INDEX);
//        TargetLabel val_ = playerTargets.getValue(IndexConstants.FIRST_INDEX);
        ini.setxPoint(0);
        ini.setyPoint(2 * maxHeight);
        imageNumber = 0;
//        ini.addx(maxWidth / 2);
//        ini.addy(maxHeight / 2);
        AbsMetaLabelPk.paintPk(battle.getWindow().getImageFactory(), this);
    }

    /**
     * @param _no
     * @param _ball
     * @param _caught
     */
    public void moveBall(AbstractImageFactory _fact, int _no, String _ball, boolean _caught) {
        paintBallMove = true;
        drawImage = true;
        drawBlueRect = false;
        imageNumber++;
        int xEnd_;
        int yEnd_;
        byte groundPlace_ = FightFacade.retrieve((byte) _no,currentFoe);
//        xEnd_ = xCoordsFoe.getVal((byte) CustList.FIRST_INDEX);
//        yEnd_ = yCoordsFoe.getVal((byte) CustList.FIRST_INDEX);
        TargetLabel foe_ = foeTargets.getVal(groundPlace_);
//        if (foe_ == null) {
//            keepAnimation = false;
//            caught = true;
//            AbsMetaLabelPk.paintPk(battle.getWindow().getImageFactory(), this);
//            return;
//        }
        xEnd_ = foe_.getPoint().getxPoint();
        yEnd_ = foe_.getPoint().getyPoint();
        xEnd_ += maxWidth / 2;
        yEnd_ += maxHeight / 2;
        int[][] img_ = facade.getData().getMiniItems().getVal(_ball);
        image = battle.getWindow().getTileRender().centerImage(_fact,img_, facade.getMap().getSideLength());
        int remainImages_ = NB_IMAGES - imageNumber;
        if (remainImages_ > 0) {
            int xDelta_ = (xEnd_ - ini.getxPoint()) / remainImages_;
            int yDelta_ = (yEnd_ - ini.getyPoint()) / remainImages_;
            ini.addx(xDelta_);
            ini.addy(yDelta_);
        } else {
            keepAnimation = false;
            caught = _caught;
        }
        AbsMetaLabelPk.paintPk(battle.getWindow().getImageFactory(), this);
    }

    public void setPaintBallMove(boolean _wild) {
        paintBallMove = _wild;
    }

    boolean isKeepAnimation() {
        return keepAnimation;
    }

    /*private void placeLabels(byte _mult) {
        removeAll();
        if (_mult == 1) {
            setLayout(new GridLayout(0, 2));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(foeTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(playerTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
        } else if (_mult == 2) {
            setLayout(new GridLayout(0, 4));
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(foeTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX+1)) {
                add(foeTargets.get(CustList.FIRST_INDEX+1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(playerTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX+1)) {
                add(playerTargets.get(CustList.FIRST_INDEX+1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
        } else if (_mult == 3) {
            setLayout(new GridLayout(0, 4));
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(foeTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX+1)) {
                add(foeTargets.get(CustList.FIRST_INDEX+1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX+2)) {
                add(foeTargets.get(CustList.FIRST_INDEX+2));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(playerTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX+1)) {
                add(playerTargets.get(CustList.FIRST_INDEX+1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX+2)) {
                add(playerTargets.get(CustList.FIRST_INDEX+2));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
        } else {
            setLayout(new GridLayout(0, 4));
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(foeTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX+1)) {
                add(foeTargets.get(CustList.FIRST_INDEX+1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX+2)) {
                add(foeTargets.get(CustList.FIRST_INDEX+2));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (foeTargets.isValidIndex(CustList.FIRST_INDEX+3)) {
                add(foeTargets.get(CustList.FIRST_INDEX+3));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX)) {
                add(playerTargets.get(CustList.FIRST_INDEX));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX+1)) {
                add(playerTargets.get(CustList.FIRST_INDEX+1));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX+2)) {
                add(playerTargets.get(CustList.FIRST_INDEX+2));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            if (playerTargets.isValidIndex(CustList.FIRST_INDEX+3)) {
                add(playerTargets.get(CustList.FIRST_INDEX+3));
            } else {
                add(new JLabel(DataBase.EMPTY_STRING));
            }
            add(new JLabel(DataBase.EMPTY_STRING));
            add(new JLabel(DataBase.EMPTY_STRING));
        }
    }*/

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(index+"/"+countAnim,0,16);
        _g.setColor(GuiConstants.WHITE);
        if (drawImages) {
            _g.drawImage(heros, player.getxPoint(), player.getyPoint());
            if (paintTwoHeros) {
                _g.drawImage(herosSexOpposite, player.getxPoint() + heros.getWidth(), player.getyPoint());
            }
            drawOthers(_g);
            tryPaintBall(_g);
            return;
        }
//            int i_;
//            i_ = CustList.FIRST_INDEX;
//            for (Byte k: xCoords.getKeys()) {
//                _g.drawImage(playerTargets.get(i_).getImage(), xCoords.getVal(k), yCoords.getVal(k), null);
//                i_++;
//            }
//            i_ = CustList.FIRST_INDEX;
//            for (Byte k: xCoordsFoe.getKeys()) {
//                _g.drawImage(foeTargets.get(i_).getImage(), xCoordsFoe.getVal(k), yCoordsFoe.getVal(k), null);
//                i_++;
//            }
        for (byte k: playerTargets.getKeys()) {
            _g.drawImage(playerTargets.getVal(k).getImage(), playerTargets.getVal(k).getPoint().getxPoint(), playerTargets.getVal(k).getPoint().getyPoint());
        }
        for (byte k: foeTargets.getKeys()) {
            _g.drawImage(foeTargets.getVal(k).getImage(), foeTargets.getVal(k).getPoint().getxPoint(), foeTargets.getVal(k).getPoint().getyPoint());
        }
        if (heal) {
            _g.setColor(GuiConstants.RED);
            _g.fillRect(ini.getxPoint(), ini.getyPoint() + 8, 20, 4);
            _g.fillRect(ini.getxPoint() + 8, ini.getyPoint(), 4, 20);
        }
        if (recoil) {
            _g.setColor(GuiConstants.RED);
            _g.fillRect(maxWidth/2, maxHeight/2-2, 20, 4);
        }
        if (drawBlueRect) {
            _g.setColor(GuiConstants.BLUE);
            TargetLabel tar_ = target(playerUser, playerTargets, foeTargets, groundPlace);
            _g.drawRect(tar_.getPoint().getxPoint(), tar_.getPoint().getyPoint(), tar_.getFinalWidth(), tar_.getFinalHeight());
        }
            /*_g.setColor(Color.RED);
            for (Byte k: koPlayerTargets) {
                if (!xCoords.contains(k)) {
                    continue;
                }
                int x_ = xCoords.getVal(k);
                int y_ = yCoords.getVal(k);
                int xRight_ = xCoords.getVal(k) + maxWidth;
                int yBottom_ = yCoords.getVal(k) + maxHeight;
                _g.drawLine(x_, y_, xRight_, yBottom_);
                _g.drawLine(x_, yBottom_, xRight_, y_);
            }
            for (Byte k: koFoeTargets) {
                if (!xCoordsFoe.contains(k)) {
                    continue;
                }
                int x_ = xCoordsFoe.getVal(k);
                int y_ = yCoordsFoe.getVal(k);
                int xRight_ = xCoordsFoe.getVal(k) + maxWidth;
                int yBottom_ = yCoordsFoe.getVal(k) + maxHeight;
                _g.drawLine(x_, y_, xRight_, yBottom_);
                _g.drawLine(x_, yBottom_, xRight_, y_);
            }*/
        if (!keepAnimation) {
            _g.setColor(GuiConstants.BLACK);
            _g.drawString(damage, end.getxPoint(), end.getyPoint());
        }
        tryPaintBall(_g);
        drawEvent(_g);
    }

    private void drawEvent(AbstractImage _g) {
        if (drawImage) {
            if (paintDefaultEffect || image == null) {
                _g.setColor(GuiConstants.WHITE);
                _g.fillRect(ini.getxPoint(), ini.getyPoint(), 20, 20);
                _g.setColor(GuiConstants.BLACK);
                _g.drawRect(ini.getxPoint(), ini.getyPoint(), 20, 20);
            } else {
                ConverterGraphicBufferedImage.transparentAllWhite(image);
                _g.drawImage(image, ini.getxPoint(), ini.getyPoint());
            }
        }
    }

    private void tryPaintBall(AbstractImage _g) {
        if (!keepAnimation && paintBallMove) {
            if (caught) {
                _g.setColor(GuiConstants.GREEN);
            } else {
                _g.setColor(GuiConstants.RED);
            }
            for (byte k: foeTargets.getKeys()) {
                _g.drawRect(foeTargets.getVal(k).getPoint().getxPoint(), foeTargets.getVal(k).getPoint().getyPoint(), foeTargets.getVal(k).getFinalWidth(), foeTargets.getVal(k).getFinalHeight());
            }
        }
    }

    private TargetLabel target(boolean _cond, ByteTreeMap<TargetLabel> _player, ByteTreeMap<TargetLabel> _foe, int _g) {
        TargetLabel tar_;
        if (_cond) {
            tar_ = _player.getVal((byte) _g);
        } else {
            tar_ = _foe.getVal((byte) _g);
        }
        return tar_;
    }

    private void drawOthers(AbstractImage _g) {
        if (other != null) {
            _g.drawImage(other,otherPt.getxPoint(),otherPt.getyPoint());
        }
        int s_ = others.size();
        for (int i = 0; i < s_; i++) {
            _g.drawImage(others.get(i), othersPt.get(i).getxPoint(), othersPt.get(i).getyPoint());
        }
    }

    public void setVisibleFrontBattle(boolean _aFlag) {
        if (!_aFlag) {
            battle.setVisible(false);
        }
        setVisible(_aFlag);
    }

    public void openActions() {
        battle.pack();
        battle.setVisible(true);
    }

    public void resetWindows() {
        battle.resetWindows();
    }

    public void closeWindows() {
        battle.closeWindows();
    }

    public void enableAnimation(boolean _enable) {
        battle.enableAnimation(_enable);
    }

    public void setComments() {
        battle.setComments();
    }

    public void showFightData() {
        battle.showFightData();
    }

    public void refreshSession() {
        battle.refreshSession();
    }

    public void setMessages() {
        battle.setMessages();
    }

    public void initializeFight(boolean _display) {
        battle.initializeFight(_display);
    }

    public void display() {
        battle.display();
    }

//    public boolean isAliveThread() {
//        return battle.isAliveThread();
//    }

    public boolean isEnabledChangeLanguage() {
        return battle.isEnabledChangeLanguage();
    }

    public CustList<FrameHtmlData> getHtmlDialogs() {
        return battle.getHtmlDialogs();
    }

    public boolean openedHtmlFrames() {
        return battle.openedHtmlFrames();
    }

    public Battle getBattle() {
        return battle;
    }
}
