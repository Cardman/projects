package cards.gui.panels;

import code.gui.AbsCustComponent;
import code.gui.AbsGridConstraints;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;

/**
    */

public class Carpet {

    public static final int NO=-2;
    public static final int SIMPLE=-1;
    public static final int PL0=0;
    public static final int PL1=1;
    public static final int PL2=2;
    public static final int PL3=3;
    public static final int PL4=4;
    public static final int PL5=5;
    private CarpetBelote tapisBelote;

    private CarpetPresident tapisPresident;

    private CarpetTarot tapisTarot;

//    public static void choose(AbsPanel _sub1, AbsPanel _sub2, AbsPanel _sub3, int _i, AbsPanel _surPanneau) {
//        if (_i < 4) {
//            _sub1.add(_surPanneau);
//        } else if (_i < 8) {
//            _sub2.add(_surPanneau);
//        } else {
//            _sub3.add(_surPanneau);
//        }
//    }

    public static void retirerCartes(AbsPanel _panel) {
        _panel.removeAll();
        _panel.validate();
    }
    public static int keyFour(boolean _horaire, int _i) {
        if (_i % 2 != 1) {
            if (_i == 4) {
                return SIMPLE;
            }
            return NO;
        }
        if (_i == 1) {
            return PL2;
        } else if (_i == 3) {
            if (_horaire) {
                return PL1;
            }
            return PL3;
        } else if (_i == 5) {
            if (_horaire) {
                return PL3;
            }
            return PL1;
        }
        return PL0;
    }

    public static int keySix(boolean _horaire, int _i) {
        if (_i == 1) {
            if (_horaire) {
                return PL2;
            }
            return PL4;
        }
        if (_i == 2) {
            return PL3;
        }
        if (_i == 6) {
            if (_horaire) {
                return PL4;
            }
            return PL2;
        }
        if (_i == 4) {
            if (_horaire) {
                return PL1;
            }
            return PL5;
        }
        if (_i == 5){
            return SIMPLE;
        }
        if (_i == 10) {
            if (_horaire) {
                return PL5;
            }
            return PL1;
        }
        if (_i == 9) {
            return PL0;
        }
        return NO;
    }

    public static int keyThree(boolean _horaire, int _i) {
        if (_i == 0) {
            if (_horaire) {
                return PL1;
            }
            return PL2;
        }
        if (_i == 2) {
            if (_horaire) {
                return PL2;
            }
            return PL1;
        }
        if (_i == 7) {
            return PL0;
        }
        if (_i == 4) {
            return SIMPLE;
        }
        return NO;
    }

    public static int keyFive(boolean _horaire, int _i) {
        if (_i == 0) {
            if (_horaire) {
                return PL2;
            }
            return PL3;
        }
        if (_i == 2) {
            if (_horaire) {
                return PL3;
            }
            return PL2;
        }
        if (_i == 3) {
            if (_horaire) {
                return PL1;
            }
            return PL4;
        }
        if (_i == 5) {
            if (_horaire) {
                return PL4;
            }
            return PL1;
        }
        if (_i == 7) {
            return PL0;
        }
        if (_i == 4) {
            return SIMPLE;
        }
        return NO;
    }

    public static AbsPanel surPanneau(AbsCompoFactory _compoFactory, int _k) {
        AbsPanel surPanneau_;
        if (_k < 0) {
            surPanneau_ = _compoFactory.newLineBox();
        } else {
            surPanneau_ = _compoFactory.newGrid();
        }
        return surPanneau_;
    }

    public static void add(AbsCompoFactory _compoFactory, AbsPanel _container, AbsCustComponent _content, boolean _remainder) {
        AbsGridConstraints cts_ = _compoFactory.newGridCts();
        if (_remainder) {
            cts_.gridwidth(GuiConstants.REMAINDER);
        }
        _container.add(_content,cts_);
    }

    public static void add(AbsCompoFactory _compoFactory, AbsPanel _container, AbsCustComponent _content, int _remainder) {
        AbsGridConstraints cts_ = _compoFactory.newGridCts();
        cts_.gridwidth(_remainder);
        _container.add(_content,cts_);
    }

    public void setTapisTarot(CarpetTarot _tapisTarot) {
        supprimerTapis();
        tapisTarot = _tapisTarot;
    }

    public void setTapisPresident(CarpetPresident _tapisPresident) {
        supprimerTapis();
        tapisPresident = _tapisPresident;
    }

    public void setTapisBelote(CarpetBelote _tapisBelote) {
        supprimerTapis();
        tapisBelote = _tapisBelote;
    }

    private void supprimerTapis() {
        tapisBelote = null;
        tapisPresident = null;
        tapisTarot = null;
    }
    public static MetaDimension getMaxDimension() {
        return getDimension(false);
    }
    public static MetaDimension getDimension(boolean _small) {
        if (_small) {
            return new MetaDimension(25,150);
        }
        return new MetaDimension(100,150);
    }
    public static MetaDimension getDimensionForSeveralCards(int _number) {
        return new MetaDimension(100 + 25 * (_number - 1), 150);
    }
    public CarpetTarot getTapisTarot() {
        return tapisTarot;
    }

    public CarpetPresident getTapisPresident() {
        return tapisPresident;
    }

    public CarpetBelote getTapisBelote() {
        return tapisBelote;
    }
}
