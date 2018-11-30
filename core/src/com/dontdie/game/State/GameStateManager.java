package com.dontdie.game.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

////CLASS THAT MANAGES GAME STATES
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

////PUSHES A STATE ONTO THE STACK
    public void push(State state){
        state.setInput();
        states.push(state);
    }

////POPS A STATE OFF OF THE TOP OF THE STACK
    public void pop(){
        states.pop().dispose();
        if( states.size() > 0 )
            states.peek().setInput();
    }

////POPS CURRENT STATE AND PUSHES NEW STATE ONTO STACK
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
