/* autogenerated by Processing revision 1292 on 2023-06-21 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class firstsand extends PApplet {

int _EMPTY = color(0);
int _SAND = color(255, 150, 50);
int _BARRIER = color(255, 0, 0);

public void setup() {
    /* size commented out by preprocessor */;
    background(_EMPTY);
    frameRate(1000);
}

public void draw() {
    loadPixels();

    if (mousePressed && mouseButton == LEFT) {
        for(int x = 0; x < 20; x++)
        for(int y = 0; y < 20; y++) {
            setCell(mouseX + x, mouseY + y, _SAND);
        }
    }

    if (mousePressed && mouseButton == RIGHT) {
        for(int x = 0; x < 20; x++)
        for(int y = 0; y < 20; y++) {
            setCell(mouseX + x, mouseY + y, _BARRIER);
        }
    }

    for(int x = 0; x < width; x++)
    for(int y = 0; y < height; y++) {
        int cell = get(x,y); //read from last frame

        if (cell == _BARRIER) {
            boolean down = isEmpty(x, y+1);
            boolean left = isEmpty(x-1, y+1);
            boolean right = isEmpty(x+1, y+1);

            setCell(x, y, _BARRIER);
        }

        if (cell == _SAND) {
            boolean down = isEmpty(x, y+1);
            boolean left = isEmpty(x-1, y+1);
            boolean right = isEmpty(x+1, y+1);

            if(left && right) {
            boolean rand = random(1) > .5f;
            left = rand ? true : false;
            right = rand ? false : true;
            }

            if(down) setCell(x, y+1, _SAND);
            else if(left) setCell(x-1,y+1, _SAND);
            else if(right) setCell(x+1,y+1, _SAND);

            if (down || left || right) {
                setCell(x, y, _EMPTY);
            }
        }
    }

    updatePixels();
}

public boolean inBounds (int x, int y) {
    return x >= 0 && y >= 0
    && x < width && y < height;
}

public boolean isEmpty(int x, int y) {
    return inBounds(x, y) && pixels[x + y * width] == _EMPTY;
}

public void setCell(int x, int y, int cell) {
    pixels[x + y * width] = cell;
}


  public void settings() { size(800, 800); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "firstsand" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
