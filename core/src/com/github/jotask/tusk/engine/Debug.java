package com.github.jotask.tusk.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.states.AbstractState;
import com.github.jotask.tusk.states.Camera;
import com.github.jotask.tusk.util.Constants;

public class Debug extends AbstractState {


    private final float offset = 10f;
    private final float SEPARATION = 15;
    private float currentLeft = 0;
    private float currentRight = 0;

    private boolean isEnabled = Constants.DEFAULT_GAME_INFO;

    private BitmapFont font;

    public Debug(final Tusk tusk) {
        super(tusk);
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
        currentLeft = camera.viewportHeight / 2 - offset;
        currentRight = camera.viewportHeight / 2 - offset;

        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        drawFontLeft(sb, "FPS: " + Gdx.graphics.getFramesPerSecond());
        String javaHeap = "Java Heap: " + toMb(Gdx.app.getJavaHeap()) + " Mb";
        String nativeHeap = "Native Heap: " + toMb(Gdx.app.getNativeHeap()) + " Mb";
        drawFontRight(sb, "Memory Usage");
        drawFontRight(sb, javaHeap);
        drawFontRight(sb, nativeHeap);

//        if(tusk.getGsm().getState() instanceof Game)
//            renderPlay(sb);

        sb.end();

    }

//    private void renderPlay(SpriteBatch sb){
//        Game play = (Game) tusk.getGsm().getState();
//        Player player = play.getPlayer();
//
//        if(player == null)
//            return;
//
//        float playerX = player.getPosition().x;
//        final float PRECISION = 1000f;
//        playerX = Math.round(playerX * PRECISION) / PRECISION;
//        float playerY = player.getPosition().y;
//        playerY = Math.round(playerY * PRECISION) / PRECISION;
//        String playerPosition = "Player: [x: " + playerX + "] [y: " + playerY + "]";
//        drawFontLeft(sb, playerPosition);
//
//        String entities = "Entities: " + play.getWorld().getWorld().getBodyCount();
//        drawFontLeft(sb, entities);
//
//        String bullets = "Bullets: " + EntityManager.get().getBullets().size();
//        drawFontLeft(sb, bullets);
//
//        // is online
//
//        if(play instanceof Mutiplayer){
//
//            TuskClient tuskClient = ((Mutiplayer)play).getClient();
//
//            if(tuskClient != null){
//                String player_name = "Name: " + tuskClient.getCharacter().name;
//                drawFontLeft(sb, player_name);
//
//                String players_online = "Online: " + (tuskClient.getOnlinePlayers().getAllTree().size() + 1);
//                drawFontLeft(sb, players_online);
//            }
//
//        }
//
//    }

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

    private void drawFontLeft(SpriteBatch sb, String text){
        float x = offset + camera.position.x - (camera.viewportWidth / 2);
        float y = currentLeft;
        drawFont(sb, text, x, y);
        currentLeft -= SEPARATION;
    }

    private void drawFontRight(SpriteBatch sb, String text){
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, text);
        float x = (camera.position.x + (camera.viewportWidth / 2)) - offset;
        x -= glyphLayout.width;
        float y = currentRight;
        drawFont(sb, text, x, y);
        currentRight -= SEPARATION;
    }

    private void drawFont(SpriteBatch sb, String text, float x, float y){
        font.draw(sb, text, x, y);
    }

    private long toMb(long bytes){
        long kb = bytes / 1024;
        long mb = kb / 1024;
        return mb;
    }

}
