package com.github.jotask.tusk.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.entities.player.Player;

public class Debug extends AbstractState{

    private boolean isEnabled = true;

    private BitmapFont font;

    public Debug() {
        this.init();
    }

    @Override
    public void init() {
        this.camera = new Camera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.font = new BitmapFont();

    }

    @Override
    public void update() {
        super.update();
        if(Gdx.input.isKeyJustPressed(Input.Keys.F3))
            isEnabled = !isEnabled;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        final float offset = 10f;
        float y = camera.viewportHeight / 2 - offset;
        font.draw(sb, "FPS: " + Gdx.graphics.getFramesPerSecond(), offset + camera.position.x - (camera.viewportWidth / 2), y);
        if(GameStateManager.get().getState() instanceof Play)
            renderPlay(sb, y, offset);
        sb.end();
    }

    private void renderPlay(SpriteBatch sb, float y, float offset){
        Play play = (Play) GameStateManager.get().getState();
        Player player = play.getPlayer();

        if(player == null)
            return;

        float playerX = player.getPosition().x;
        final float PRECISION = 1000f;
        playerX = Math.round(playerX * PRECISION) / PRECISION;
        float playerY = player.getPosition().y;
        playerY = Math.round(playerY * PRECISION) / PRECISION;
        String playerPosition = "Player: [x: " + playerX + "] [y: " + playerY + "]";
        font.draw(sb, playerPosition, offset + camera.position.x - (camera.viewportWidth / 2), y - 15);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        sr.setProjectionMatrix(camera.combined);
        sr.begin();
        sr.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
