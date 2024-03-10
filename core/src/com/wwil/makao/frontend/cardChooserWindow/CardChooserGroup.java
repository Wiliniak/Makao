package com.wwil.makao.frontend.cardChooserWindow;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.wwil.makao.frontend.CardActor;
import com.wwil.makao.frontend.GUIparams;
import com.wwil.makao.frontend.GameController;

import java.util.ArrayList;
import java.util.List;

public class CardChooserGroup extends Group {
    private final GameController gameController;
    private final CardChooserManager manager;
    private final WindowActor window;
    private final CardActor displayCard;
    private final List<ArrowButtonActor> arrowButtons;
    private final PutButtonActor putButton;
    private boolean visible = false;

    public CardChooserGroup(GameController gameController) {
        this.gameController = gameController;
        this.manager = new CardChooserManager(this);
        //Tworzenie obiektów okna
        this.window = new WindowActor();
        this.arrowButtons = createArrows();
        this.putButton = new PutButtonActor(this);
        this.displayCard = new CardActor(null);
        assignElementsToGroup();
        displayCard.setPosition(GUIparams.CHOOSER_CARD_X_POS, GUIparams.CHOOSER_CARD_Y_POS);
        setBounds(GUIparams.CHOOSER_WINDOW_X_POS, GUIparams.CHOOSER_WINDOW_Y_POS,
                GUIparams.CHOOSER_WINDOW_WIDTH, GUIparams.CHOOSER_WINDOW_HEIGHT);
    }

    private List<ArrowButtonActor> createArrows() {
        List<ArrowButtonActor> buttons = new ArrayList<>();
        for (CardChooserButtonTypes type : CardChooserButtonTypes.values()) {
            if (type != CardChooserButtonTypes.PUT) {
                ArrowButtonActor arrow = new ArrowButtonActor(manager, type);
                buttons.add(arrow);
                addActor(arrow);
            }
        }
        return buttons;
    }

    private void assignElementsToGroup(){
        addActor(window);
        addActor(putButton);
        addActor(displayCard);
    }

    public void setVisibility(boolean visible){
        this.setVisible(visible);
        resetArrowsVisibility();
    }

    private void resetArrowsVisibility(){
        for(ArrowButtonActor arrow : getArrowButtons()){
            arrow.setVisible(true);
        }
    }

    public GameController getGameController() {
        return gameController;
    }

    public CardActor getDisplayCard() {
        return displayCard;
    }

    public CardChooserManager getManager() {
        return manager;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public List<ArrowButtonActor> getArrowButtons() {
        return arrowButtons;
    }
}
