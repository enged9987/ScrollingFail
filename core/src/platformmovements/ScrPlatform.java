package platformmovements;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ScrPlatform implements Screen, InputProcessor {

    Game game;
    SpriteBatch batch;
    Texture txDino, txPlat;
    SprDino sprDino;
    Sprite sprBack;
    SprPlatform sprPlatform, sprPlatform2;
    int nScreenWid = Gdx.graphics.getWidth(), nDinoHei, nScreenX;
    float fScreenWidth = Gdx.graphics.getWidth(), fScreenHei = Gdx.graphics.getHeight(), fDist, fVBackX;
    private float fVy;
    private float fVx;
    OrthographicCamera camBack;

    public ScrPlatform(Game _game) {
        game = _game;
        batch = new SpriteBatch();
        txDino = new Texture("Dinosaur.png");
        txPlat = new Texture("Platform.png");
        sprBack = new Sprite(new Texture(Gdx.files.internal("world.jpg")));
        sprBack.setSize(fScreenWidth, fScreenHei);
        Gdx.input.setInputProcessor((this));
        Gdx.graphics.setDisplayMode(800, 500, false);
        sprDino = new SprDino("Dinosaur.png", 0, 0);
        sprPlatform = new SprPlatform("Platform.png", 0, 0);
        sprPlatform2 = new SprPlatform("Platform.png", 0, 0);
        camBack = new OrthographicCamera(fScreenWidth /** aspectratio*/, fScreenHei);
        camBack.position.set(fScreenWidth / 2, fScreenHei / 2, 0);
        nDinoHei = txDino.getHeight();
        sprPlatform.setX(nScreenWid);
        sprPlatform2.setX(nScreenWid);
        sprPlatform.setY(nDinoHei);
        sprPlatform2.setY(nDinoHei);
    }

    @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        sprDino.update(fVx, fVy);
        sprPlatform.update(sprDino.getX());
        sprPlatform2.update(sprDino.getX());
        camBack.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if ((nScreenX < -Gdx.graphics.getWidth() || nScreenX > Gdx.graphics.getWidth())) {
            nScreenX = 0;
        }
        batch.setProjectionMatrix(camBack.combined);
        batch.draw(sprBack, sprBack.getX(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(sprBack, sprBack.getX() - Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(sprBack, sprBack.getX() + Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(sprDino.getSprite(), sprDino.getX(), sprDino.getY());
        batch.draw(sprPlatform.getSprite(), sprPlatform.getX(), sprPlatform.getY());
        batch.draw(sprPlatform2.getSprite(), sprPlatform.getX(), sprPlatform.getY());
        batch.end();
        //if (sprDino.getX() > (fScreenWidth/2)) {
            /*if (sprDino.getX() <= fScreenWidth&&sprDino.getX() >= (fScreenWidth/4)) {
         //nScreenX -= fVx;
         vBackPos.add(vBackDir);
         //camBack.translate(vBackDir);
         //nScreenX += 2;
         System.out.println("Im Here");
         //use camera.translate

         } /*else if (sprBack.getX() < 0) {
         fVx = 0;
         nScreenX = 0;
         }*/
        //nScreenX -= fVx;
        //}*/ 
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
           fVBackX -= .3; 
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            fVBackX = 0;
            fVBackX += .3;
        }
        if (fDist > 0) {
            camBack.translate(fVBackX, 0f);
        } else if (fDist < 0) {
            fVBackX = 0;
            fDist = 0;
        }

        fDist += fVBackX;
        camBack.translate(fVBackX, 0f);



    }

    @Override
    public void resize(int i, int i1) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.E) {
            System.exit(3);
        } else if (keycode == Input.Keys.UP) {
            fVy = 2;

        } else if (keycode == Input.Keys.DOWN) {
            fVy = -2;
        } else if (keycode == Input.Keys.LEFT) {
            fVx = -2;
            fVBackX = fVx;            
        } else if (keycode == Input.Keys.RIGHT) {
            fVx = 2;
            fVBackX = fVx;            
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP) {
            fVy = 0;
        } else if (keycode == Input.Keys.DOWN) {
            fVy = 0;
        } else if (keycode == Input.Keys.LEFT) {
            fVx = 0;
            fVBackX =0;
        } else if (keycode == Input.Keys.RIGHT) {
            fVx = 0;
            fVBackX =0;
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean scrolled(int i) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
