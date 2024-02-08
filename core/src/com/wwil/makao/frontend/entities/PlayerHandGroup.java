package com.wwil.makao.frontend.entities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.wwil.makao.frontend.parameters.CardsAligmentParams;
import com.wwil.makao.frontend.parameters.GUIparams;

public class PlayerHandGroup extends Group {
    private CardsAligmentParams cardsAlignment;

    @Override
    public void addActor(Actor actor) {
        if (!getChildren().isEmpty()) {
            chooseWhereCardShouldBe(actor);
        }
        super.addActor(actor);
    }

    private void chooseWhereCardShouldBe(Actor actor){
        if (getChildren().size % 2 == 1) {
            placeCardAsFirst(actor);
        } else {
            placeCardAsLast(actor);
        }
    }

    private void placeCardAsLast(Actor actor){
        float lastActorX = getChildren().peek().getX();
        actor.setX(lastActorX + GUIparams.DISTANCE_BETWEEN_CARDS);
        setPosition(getX() - cardsAlignment.xMove, getY() - cardsAlignment.yMove);
    }

    private void placeCardAsFirst(Actor actor){
        float firstActorX = getChildren().first().getX();
        actor.setX(firstActorX - GUIparams.DISTANCE_BETWEEN_CARDS);
        addActorAt(0, actor);
        moveCloserToStartingPosition();
    }

    public void moveCloserToStartingPosition() {
        this.setPosition(this.getX() + cardsAlignment.xMove, this.getY() + cardsAlignment.yMove);
    }

    @Override
    public boolean removeActor(Actor actor, boolean unfocus) {
        moveActorsToFillEmptySpace(actor);
        return super.removeActor(actor, unfocus);
    }

    private void moveActorsToFillEmptySpace(Actor actor){
        int cardIndex = actor.getZIndex();
        for (int i = cardIndex + 1; i < getChildren().size; i++) {
            Actor currentActor = getChildren().get(i);
            currentActor.setZIndex(i - 1);
            currentActor.setX(currentActor.getX() - GUIparams.DISTANCE_BETWEEN_CARDS);
        }
    }

    public boolean checkIsPlayerHasNoCards(){
        return getChildren().isEmpty();
    }

    public void setCardsAlignment(CardsAligmentParams cardsAlignment) {
        this.cardsAlignment = cardsAlignment;
    }
}
