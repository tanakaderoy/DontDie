package com.dontdie.game.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

//This is a change awdw
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        state.setInput();
        states.push(state);

    }

    public void pop(){
        states.pop().dispose();
        if( states.size() > 0 )
            states.peek().setInput();
    }

    public void set(State state){
        states.pop().dispose();
        state.setInput();
        states.push(state);
    }


    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

}
