package com.dontdie.game.State;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager { //// games state manager controls the which state we are in to play the game

    private Stack<State> states;


    public GameStateManager(){
        states = new Stack<State>();

    }

    public void push(State state){
        states.push(state);
    }


    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }


    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }


}
