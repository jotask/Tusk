package com.github.jotask.tusk.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.engine.online.client.TuskClient;
import com.github.jotask.tusk.states.AbstractState;
import com.github.jotask.tusk.states.Camera;
import com.github.jotask.tusk.states.GameStateManager;
import com.github.jotask.tusk.states.play.Mutiplayer;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.entities.player.Player;

public class Debug extends AbstractState {

    private boolean isEnabled = true;

    private BitmapFont font;

    public Debug() {
        this.init();
    }

    @Override
    public void init() {
        this.camera = new Camera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.font = AssetManager.get().getFont();

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

        String entities = "Entities: " + play.getWorld().getWorld().getBodyCount();
        font.draw(sb, entities, offset + camera.position.x - (camera.viewportWidth / 2), y - 15 * 2);

        String bullets = "Bullets: " + play.getEntityManager().bulletsSize();
        font.draw(sb, bullets, offset + camera.position.x - (camera.viewportWidth / 2), y - 15 * 3);

        // is online

        if(play instanceof Mutiplayer){

            TuskClient tuskClient = ((Mutiplayer)play).getClient();

            if(tuskClient != null){
                String player_name = "Name: " + tuskClient.getCharacter().name;
                font.draw(sb, player_name, offset + camera.position.x - (camera.viewportWidth / 2), y - 15 * 4);

                String players_online = "Online: " + (tuskClient.getOnlinePlayers().getAllTree().size() + 1);
                font.draw(sb, players_online, offset + camera.position.x - (camera.viewportWidth / 2), y - 15 * 5);
            }

        }

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
