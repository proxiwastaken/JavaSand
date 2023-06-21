color _EMPTY = color(0);
color _SAND = color(255, 150, 50);
color _BARRIER = color(255, 0, 0);

void setup() {
    size(800, 800);
    background(_EMPTY);
    frameRate(1000);
}

void draw() {
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
        color cell = get(x,y); //read from last frame

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
            boolean rand = random(1) > .5;
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

boolean inBounds (int x, int y) {
    return x >= 0 && y >= 0
    && x < width && y < height;
}

boolean isEmpty(int x, int y) {
    return inBounds(x, y) && pixels[x + y * width] == _EMPTY;
}

void setCell(int x, int y, color cell) {
    pixels[x + y * width] = cell;
}