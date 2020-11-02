package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import aiki.game.fight.TargetCoords;
import aiki.game.fight.animations.*;
import aiki.game.fight.enums.FightState;
import aiki.gui.MainWindow;
import aiki.gui.dialogs.FrameHtmlData;
import code.gui.CustGraphics;
import code.gui.PaintableLabel;
import code.gui.images.ConverterGraphicBufferedImage;
import code.maths.LgInt;
import code.util.CustList;
import code.util.*;
import code.util.StringMap;
import code.util.core.IndexConstants;

public class FrontBattle extends PaintableLabel {

    private static final int NB_IMAGES = 128;

    private static final int NB_IMAGES_SWITCH = 64;

//    private CustList<TargetLabel> foeTargets = new CustList<>();
    private ByteTreeMap<TargetLabel> foeTargets = new ByteTreeMap<TargetLabel>();

//    private CustList<TargetLabel> playerTargets = new CustList<>();
    private ByteTreeMap<TargetLabel> playerTargets = new ByteTreeMap<TargetLabel>();

    private int maxWidth;

    private int maxHeight;

    private int imageNumber;

    private int xIni;

    private int yIni;

    private int xEnd;

    private int yEnd;

    private int xPlayer;

    private int yPlayer;

    private int xOther;

    private int yOther;

    private FacadeGame facade;

    private boolean drawImage;

    private BufferedImage image;

    private boolean drawImages;

    private BufferedImage heros;

    private BufferedImage herosSexOpposite;

    private boolean paintTwoHeros;

    private BufferedImage other;

    private boolean keepAnimation;

    private int mult;

    private String damage = "";

    private boolean paintDefaultEffect;

    private boolean drawBlueRect;

    private boolean playerUser;

    private short groundPlace;

    private boolean heal;

    private boolean recoil;

    private boolean wild;

    private boolean caught;

    private Battle battle;

    public FrontBattle(MainWindow _window, FacadeGame _facade) {
        facade = _facade;
        battle = new Battle(_window, _facade, this);
    }

    public void setTargets() {
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
        ByteTreeMap<Fighter> teamPl_ = new ByteTreeMap< Fighter>();
        teamPl_.putAllMap(facade.getUnionFrontTeam());
        for (byte k: teamPl_.getKeys()) {
            TargetLabel target_ = new TargetLabel();
            Fighter fighter_ = teamPl_.getVal(k);
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
            playerTargets.put(k, target_);
        }
        ByteTreeMap<Fighter> teamFoe_ = facade.getFoeFrontTeam();
        for (byte k: teamFoe_.getKeys()) {
            TargetLabel target_ = new TargetLabel();
            Fighter fighter_ = teamFoe_.getVal(k);
            target_.setLevel(fighter_.getLevel());
            target_.setPercentHp(fighter_.rateRemainHp());
            target_.setPercentExp(fighter_.wonExpRate(facade.getData()));
            boolean caught_ = facade.getPlayer().estAttrape(fighter_.getName());
            if (caught_) {
                target_.setBall(facade.getData().getDefaultBall());
            }
            target_.set(this, false, facade, fighter_.getName());
            if (target_.getFinalWidth() > maxWidth) {
                maxWidth = target_.getFinalWidth();
            }
            if (target_.getFinalHeight() > maxHeight) {
                maxHeight = target_.getFinalHeight();
            }
            foeTargets.put(k, target_);
        }
        int i_ = IndexConstants.FIRST_INDEX;
        for (TargetLabel t: playerTargets.values()) {
            if (mult_ == 1) {
                t.setxPoint(0);
                t.setyPoint(maxHeight);
            } else if (mult_ == 2) {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.setxPoint(0);
                    t.setyPoint(maxHeight);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.setxPoint(maxWidth);
                    t.setyPoint(maxHeight);
                }
            } else if (mult_ == 3) {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.setxPoint(0);
                    t.setyPoint(maxHeight * 2);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.setxPoint(maxWidth);
                    t.setyPoint(maxHeight * 2);
                } else {
                    t.setxPoint(maxWidth);
                    t.setyPoint(maxHeight * 3);
                }
            } else {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.setxPoint(0);
                    t.setyPoint(maxHeight * 2);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.setxPoint(maxWidth);
                    t.setyPoint(maxHeight * 2);
                } else if (i_ == IndexConstants.FIRST_INDEX + 2) {
                    t.setxPoint(0);
                    t.setyPoint(maxHeight * 3);
                } else {
                    t.setxPoint(maxWidth);
                    t.setyPoint(maxHeight * 3);
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
                t.setxPoint(maxWidth);
                t.setyPoint(0);
            } else if (mult_ == 2) {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.setxPoint(maxWidth * 2);
                    t.setyPoint(0);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.setxPoint(maxWidth * 3);
                    t.setyPoint(0);
                }
            } else if (mult_ == 3) {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.setxPoint(maxWidth * 2);
                    t.setyPoint(0);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.setxPoint(maxWidth * 2);
                    t.setyPoint(maxHeight);
                } else {
                    t.setxPoint(maxWidth * 3);
                    t.setyPoint(maxHeight);
                }
            } else {
                if (i_ == IndexConstants.FIRST_INDEX) {
                    t.setxPoint(maxWidth * 2);
                    t.setyPoint(0);
                } else if (i_ == IndexConstants.FIRST_INDEX + 1) {
                    t.setxPoint(maxWidth * 3);
                    t.setyPoint(0);
                } else if (i_ == IndexConstants.FIRST_INDEX + 2) {
                    t.setxPoint(maxWidth * 2);
                    t.setyPoint(maxHeight);
                } else {
                    t.setxPoint(maxWidth * 3);
                    t.setyPoint(maxHeight);
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
            setPreferredSize(new Dimension(maxWidth * 2, maxHeight * 2));
        } else if (mult_ == 2) {
            setPreferredSize(new Dimension(maxWidth * 4, maxHeight * 2));
        } else {
            setPreferredSize(new Dimension(maxWidth * 4, maxHeight * 4));
        }
        //placeLabels(mult_);
    }

//    void initRoundAnimation() {
//        koFoeTargets.clear();
//        koPlayerTargets.clear();
//    }

    void drawAnimationInstantInitial(AnimationInt _animation) {
        wild = false;
        keepAnimation = true;
        paintDefaultEffect = false;
        drawBlueRect = false;
        heal = false;
        recoil = false;
        damage = DataBase.EMPTY_STRING;
        if (_animation instanceof AnimationEffect) {
            AnimationEffect animation_ = (AnimationEffect) _animation;
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
                    xIni = tar_.getxPoint();
                    yIni = tar_.getyPoint();
//                xIni = xCoords.getVal((byte) animation_.getFromFighter().getPosition());
//                yIni = yCoords.getVal((byte) animation_.getFromFighter().getPosition());
                } else {
                    TargetLabel tar_ = foeTargets.getVal((byte) animation_.getFromFighter().getPosition());
                    xIni = tar_.getxPoint();
                    yIni = tar_.getyPoint();
//                xIni = xCoordsFoe.getVal((byte) animation_.getFromFighter().getPosition());
//                yIni = yCoordsFoe.getVal((byte) animation_.getFromFighter().getPosition());
                }
            }
        } else if (_animation instanceof AnimationSwitch) {
            AnimationSwitch animation_ = (AnimationSwitch) _animation;
            if (animation_.isPlayer()) {
                TargetLabel tar_ = playerTargets.getVal((byte) animation_.getSubstituted().getPosition());
                xIni = tar_.getxPoint();
                yIni = tar_.getyPoint();
//                xIni = xCoords.getVal((byte) animation_.getSubstituted().getPosition());
//                yIni = yCoords.getVal((byte) animation_.getSubstituted().getPosition());
            } else {
                TargetLabel tar_ = foeTargets.getVal((byte) animation_.getSubstituted().getPosition());
                xIni = tar_.getxPoint();
                yIni = tar_.getyPoint();
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
                xIni = tar_.getxPoint();
                yIni = tar_.getyPoint();
//                xIni = xCoords.getVal((byte) animation_.getHealed().getPosition());
//                yIni = yCoords.getVal((byte) animation_.getHealed().getPosition());
            } else {
                TargetLabel tar_ = foeTargets.getVal((byte) animation_.getHealed().getPosition());
                xIni = tar_.getxPoint();
                yIni = tar_.getyPoint();
//                xIni = xCoordsFoe.getVal((byte) animation_.getHealed().getPosition());
//                yIni = yCoordsFoe.getVal((byte) animation_.getHealed().getPosition());
            }
        } else if (_animation instanceof AnimationAutoEffect) {
            recoil = ((AnimationAutoEffect) _animation).getAutoEffectKind() == AutoEffectKind.RECOIL;
            AnimationAutoEffect animation_ = (AnimationAutoEffect) _animation;
            if (animation_.isPlayer()) {
                TargetLabel tar_ = playerTargets.getVal((byte) animation_.getUser().getPosition());
                xIni = tar_.getxPoint();
                yIni = tar_.getyPoint();
//                xIni = xCoords.getVal((byte) animation_.getUser().getPosition());
//                yIni = yCoords.getVal((byte) animation_.getUser().getPosition());
            } else {
                TargetLabel tar_ = foeTargets.getVal((byte) animation_.getUser().getPosition());
                xIni = tar_.getxPoint();
                yIni = tar_.getyPoint();
//                xIni = xCoordsFoe.getVal((byte) animation_.getUser().getPosition());
//                yIni = yCoordsFoe.getVal((byte) animation_.getUser().getPosition());
            }
        }
        imageNumber = 0;
        xIni += maxWidth / 2;
        yIni += maxHeight / 2;
        repaintLabel();
    }

    void drawAnimationInstant(AnimationInt _animation) {
        wild = false;
        drawImage = true;
        imageNumber++;
        int xEnd_;
        int yEnd_;
        if (_animation instanceof AnimationEffect) {
            AnimationEffect animation_ = (AnimationEffect) _animation;
            if (animation_.isPlayerToFighter()) {
                TargetLabel tar_ = playerTargets.getVal((byte) animation_.getToFighter().getPosition());
//                xEnd_ = xCoords.getVal((byte) animation_.getToFighter().getPosition());
//                yEnd_ = yCoords.getVal((byte) animation_.getToFighter().getPosition());
                xEnd_ = tar_.getxPoint();
                yEnd_ = tar_.getyPoint();
            } else {
                TargetLabel tar_ = foeTargets.getVal((byte) animation_.getToFighter().getPosition());
//                xEnd_ = xCoordsFoe.getVal((byte) animation_.getToFighter().getPosition());
//                yEnd_ = yCoordsFoe.getVal((byte) animation_.getToFighter().getPosition());
                xEnd_ = tar_.getxPoint();
                yEnd_ = tar_.getyPoint();
            }
            xEnd = xEnd_;
            yEnd = yEnd_;
            xEnd_ += maxWidth / 2;
            yEnd_ += maxHeight / 2;
            int remainImages_ = NB_IMAGES - imageNumber;
            if (remainImages_ > 0) {
                int xDelta_ = (xEnd_ - xIni) / remainImages_;
                int yDelta_ = (yEnd_ - yIni) / remainImages_;
                xIni += xDelta_;
                yIni += yDelta_;
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
                        TargetLabel label_;
                        if (animation_.isPlayer()) {
                            label_ = playerTargets.getVal((byte) animation_.getSubstituted().getPosition());
                        } else {
                            label_ = foeTargets.getVal((byte) animation_.getSubstituted().getPosition());
                        }
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
                                label_.setBall(facade.getData().getDefaultBall());
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
        int hMax_ = 0;
        if (_animation instanceof AnimationEffectDamage) {
            AnimationEffectDamage damage_ = (AnimationEffectDamage) _animation;
            StringMap<BufferedImage> types_ = new StringMap<BufferedImage>();
            for (String t: damage_.getTypes()) {
                int[][] type_ = facade.getData().getTypesImages().getVal(t);
                BufferedImage t_ = ConverterGraphicBufferedImage.decodeToImage(type_);
                types_.put(t, t_);
            }
            hMax_ = 0;
            int width_ = 0;
            for (BufferedImage i: types_.values()) {
                if (i.getHeight() > hMax_) {
                    hMax_ = i.getHeight();
                }
                width_ += i.getWidth();
            }
            hMax_ += getFontMetrics(getFont()).getHeight();
            damage = damage_.getDamage().evaluate(4);
            int strWidth_ = getFontMetrics(getFont()).stringWidth(damage);
            if (strWidth_ > width_) {
                width_ = strWidth_;
            }
            image = new BufferedImage(width_, hMax_, BufferedImage.TYPE_INT_ARGB);
            CustGraphics gr_ = new CustGraphics(image.getGraphics());
            gr_.setColor(Color.WHITE);
            gr_.fillRect(0, 0, width_, hMax_);
            int x_ = 0;
            for (BufferedImage i: types_.values()) {
                gr_.drawImage(i, x_, 0);
                x_ += i.getWidth();
            }
            gr_.setColor(new Color(Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue(), 255));
            gr_.drawString(damage, 0, hMax_);
        } else if (_animation instanceof AnimationEffectStatistic) {
            AnimationEffectStatistic statis_ = (AnimationEffectStatistic) _animation;
            CustList<BufferedImage> types_ = new CustList<BufferedImage>();
            FontMetrics fMet_ = getFontMetrics(getFont());
            int h_ = fMet_.getHeight();
            int statSide_ = facade.getMap().getSideLength();
            for (InfosAnimationStatistic t: statis_.getInfos()) {
                int[][] type_ = facade.getData().getAnimStatis().getVal(t.getStatistic().name());
                BufferedImage t_ = ConverterGraphicBufferedImage.decodeToImage(type_);
                String var_ = Long.toString(t.getVariation());
                int widthVar_ = fMet_.stringWidth(var_);
                if (widthVar_ < statSide_) {
                    widthVar_ = statSide_;
                }
                BufferedImage varStat_ = new BufferedImage(widthVar_, h_ + statSide_, BufferedImage.TYPE_INT_ARGB);
                CustGraphics g_ = new CustGraphics(varStat_.createGraphics());
                g_.drawImage(t_, 0, 0);
                g_.setColor(Color.BLACK);
                g_.drawString(var_, 0, statSide_ + h_);
                g_.dispose();
                types_.add(varStat_);
            }
            if (TargetCoords.eq(statis_.getFromFighter(), statis_.getToFighter())) {
                TargetLabel label_;
                if (statis_.isPlayerFromFighter()) {
                    label_ = playerTargets.getVal((byte) statis_.getFromFighter().getPosition());
                } else {
                    label_ = foeTargets.getVal((byte) statis_.getFromFighter().getPosition());
                }
                if (keepAnimation) {
                    label_.setStatistics(types_);
                } else {
                    label_.setStatistics(new CustList<BufferedImage>());
                }
                label_.apply(this, facade);
                drawImage = false;
                imageNumber++;
                imageNumber++;
                imageNumber++;
                //decrease nb images
            } else {
                hMax_ = 0;
                int width_ = 0;
                for (BufferedImage i: types_) {
                    if (i.getHeight() > hMax_) {
                        hMax_ = i.getHeight();
                    }
                    width_ += i.getWidth();
                }
                if (width_ > 0) {
                    image = new BufferedImage(width_, hMax_, BufferedImage.TYPE_INT_ARGB);
                    CustGraphics g_ = new CustGraphics(image.createGraphics());
                    g_.setColor(Color.WHITE);
                    g_.fillRect(0, 0, width_, hMax_);
                    int x_ = 0;
                    for (BufferedImage i: types_) {
                        g_.drawImage(i, x_, 0);
                        x_ += i.getWidth();
                    }
                    g_.dispose();
                } else {
                    paintDefaultEffect = true;
                }
            }
        } else if (_animation instanceof AnimationEffectStatus) {
            AnimationEffectStatus status_ = (AnimationEffectStatus) _animation;
            if (!status_.getStatus().isEmpty()) {
                if (TargetCoords.eq(status_.getFromFighter(), status_.getToFighter())) {
                    int[][] stTxt_ = facade.getData().getAnimStatus().getVal(status_.getStatus());
                    BufferedImage image_ = ConverterGraphicBufferedImage.decodeToImage(stTxt_);
                    TargetLabel label_;
                    if (status_.isPlayerFromFighter()) {
                        label_ = playerTargets.getVal((byte) status_.getFromFighter().getPosition());
                    } else {
                        label_ = foeTargets.getVal((byte) status_.getFromFighter().getPosition());
                    }
                    if (keepAnimation) {
                        label_.setStatistics(new CustList<BufferedImage>(image_));
                    } else {
                        label_.setStatistics(new CustList<BufferedImage>());
                    }
                    label_.apply(this, facade);
                    drawImage = false;
                    imageNumber++;
                    imageNumber++;
                    imageNumber++;
                } else {
                    int[][] stTxt_ = facade.getData().getAnimStatus().getVal(status_.getStatus());
                    image = ConverterGraphicBufferedImage.decodeToImage(stTxt_);
                }
            } else {
                paintDefaultEffect = true;
            }
        } else if (_animation instanceof AnimationEffect) {
            AnimationEffect e_ = (AnimationEffect) _animation;
            if (e_.getEffectKind() == EffectKind.ABSORB) {
                int[][] stTxt_ = facade.getData().getAnimAbsorb();
                image = ConverterGraphicBufferedImage.decodeToImage(stTxt_);
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
        repaintLabel();
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
        repaintLabel();
    }

    void setHerosOppositeSex(BufferedImage _oppositeSex, boolean _paintTwoHeros) {
        herosSexOpposite = _oppositeSex;
        paintTwoHeros = _paintTwoHeros;
    }

    void drawAnimationFightIni(BufferedImage _heros, BufferedImage _other) {
        wild = false;
        heros = _heros;
        other = _other;
        keepAnimation = true;
        paintDefaultEffect = false;
        drawBlueRect = false;
        heal = false;
        recoil = false;
        xOther = 0;
        yOther = 0;
        if (mult == 1) {
            xPlayer = maxWidth;
            yPlayer = maxHeight;
        } else if (mult == 2) {
            xPlayer = maxWidth * 3;
            yPlayer = maxHeight;
        } else {
            xPlayer = maxWidth * 3;
            yPlayer = maxHeight * 3;
        }
        imageNumber = 0;
        repaintLabel();
    }

    void drawAnimationFightIniInst() {
        wild = false;
        drawImages = true;
        imageNumber++;
        int xEndPlayer_ = 0;
        int xEndOther_;
        if (mult == 1) {
            xEndOther_ = maxWidth;
        } else {
            xEndOther_ = maxWidth * 3;
        }
        int remainImages_ = NB_IMAGES - imageNumber;
        if (remainImages_ > 0) {
            int xDelta_ = (xEndPlayer_ - xPlayer) / remainImages_;
            xPlayer += xDelta_;
            xDelta_ = (xEndOther_ - xOther) / remainImages_;
            xOther += xDelta_;
        } else {
            keepAnimation = false;
            drawImages = false;
        }
        repaintLabel();
    }

    public void initBall() {
        wild = true;
        keepAnimation = true;
        drawBlueRect = false;
//        xIni = xCoords.getVal((byte) CustList.FIRST_INDEX);
//        yIni = yCoords.getVal((byte) CustList.FIRST_INDEX);
        xIni = playerTargets.getVal(IndexConstants.FIRST_INDEX).getxPoint();
        yIni = playerTargets.getVal(IndexConstants.FIRST_INDEX).getyPoint();
        imageNumber = 0;
        xIni += maxWidth / 2;
        yIni += maxHeight / 2;
        repaintLabel();
    }

    /**
    @param _ball
    */
    public void moveBall(String _ball) {
        wild = true;
        drawImage = true;
        drawBlueRect = false;
        imageNumber++;
        int xEnd_;
        int yEnd_;
//        xEnd_ = xCoordsFoe.getVal((byte) CustList.FIRST_INDEX);
//        yEnd_ = yCoordsFoe.getVal((byte) CustList.FIRST_INDEX);
        xEnd_ = foeTargets.getVal(IndexConstants.FIRST_INDEX).getxPoint();
        yEnd_ = foeTargets.getVal(IndexConstants.FIRST_INDEX).getyPoint();
        xEnd_ += maxWidth / 2;
        yEnd_ += maxHeight / 2;
        int[][] img_ = facade.getData().getMiniItems().getVal(_ball);
        image = ConverterGraphicBufferedImage.centerImage(img_, facade.getMap().getSideLength());
        int remainImages_ = NB_IMAGES - imageNumber;
        if (remainImages_ > 0) {
            int xDelta_ = (xEnd_ - xIni) / remainImages_;
            int yDelta_ = (yEnd_ - yIni) / remainImages_;
            xIni += xDelta_;
            yIni += yDelta_;
        } else {
            keepAnimation = false;
            boolean caught_ = false;
            if (facade.getFight().getState() == FightState.CAPTURE_KO) {
                caught_ = true;
            } else if (facade.getFight().getState() == FightState.SURNOM) {
                caught_ = true;
            }
            caught = caught_;
        }
        repaintLabel();
    }

    public void setWild(boolean _wild) {
        wild = _wild;
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
    public void paintComponent(CustGraphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        if (drawImage) {
            if (paintDefaultEffect) {
                _g.setColor(Color.WHITE);
                _g.fillRect(xIni, yIni, 20, 20);
                _g.setColor(new Color(Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue(), 255));
                _g.drawRect(xIni, yIni, 20, 20);
            } else {
                _g.drawImage(image, xIni, yIni);
            }
        }
        if (heal) {
            _g.setColor(Color.RED);
            _g.fillRect(xIni, yIni + 8, 20, 4);
            _g.fillRect(xIni + 8, yIni, 4, 20);
        }
        if (recoil) {
            _g.setColor(Color.RED);
            _g.fillRect(maxWidth/2, maxHeight/2-2, 20, 4);
        }
        if (drawImages) {
            _g.drawImage(heros, xPlayer, yPlayer);
            if (paintTwoHeros) {
                _g.drawImage(herosSexOpposite, xPlayer + heros.getWidth(), yPlayer);
            }
            _g.drawImage(other, xOther, yOther);
        } else {
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
                _g.drawImage(playerTargets.getVal(k).getImage(), playerTargets.getVal(k).getxPoint(), playerTargets.getVal(k).getyPoint());
            }
            for (byte k: foeTargets.getKeys()) {
                _g.drawImage(foeTargets.getVal(k).getImage(), foeTargets.getVal(k).getxPoint(), foeTargets.getVal(k).getyPoint());
            }
            if (drawBlueRect) {
                _g.setColor(Color.BLUE);
                if (playerUser) {
                    TargetLabel tar_ = playerTargets.getVal((byte) groundPlace);
                    _g.drawRect(tar_.getxPoint(), tar_.getyPoint(), tar_.getFinalWidth(), tar_.getFinalHeight());
                } else {
                    TargetLabel tar_ = foeTargets.getVal((byte) groundPlace);
                    _g.drawRect(tar_.getxPoint(), tar_.getyPoint(), tar_.getFinalWidth(), tar_.getFinalHeight());
                }
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
                _g.setColor(Color.BLACK);
                _g.drawString(damage, xEnd, yEnd);
            }
        }
        if (!keepAnimation && wild) {
            if (caught) {
                _g.setColor(Color.GREEN);
            } else {
                _g.setColor(Color.RED);
            }
            for (byte k: foeTargets.getKeys()) {
                _g.drawRect(foeTargets.getVal(k).getxPoint(), foeTargets.getVal(k).getyPoint(), foeTargets.getVal(k).getFinalWidth(), foeTargets.getVal(k).getFinalHeight());
            }
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

    public boolean isAliveThread() {
        return battle.isAliveThread();
    }

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
