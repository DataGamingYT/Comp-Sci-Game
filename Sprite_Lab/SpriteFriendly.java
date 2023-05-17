PlayerSprite s1;
ArrayList<Box> boxes;

class PlayerSprite {
  float x, y;
  float velocityX, velocityY;
  float scale;
  PImage playerImg;
  
  PlayerSprite(float x, float y, float scale) {
    this.x = x;
    this.y = y;
    this.scale = scale;
    this.velocityX = 0;
    this.velocityY = 0;
    this.playerImg = loadImage("player.png");
  }
  
  void display() {
    image(playerImg, x, y, playerImg.width * scale, playerImg.height * scale);
  }
  
  void update() {
    // Check for collisions with boxes
    for (Box box : boxes) {
      if (collidesWith(box)) {
        // Handle collision here
        // Adjust player's position or velocity to prevent going behind the box
        if (y + playerImg.height * scale > box.y) {
          y = box.y - playerImg.height * scale;
        }
      }
    }
    
    // Update player's position based on velocity
    x += velocityX;
    y += velocityY;
    
    // Limit player's movement within the canvas boundaries
    x = constrain(x, 0, width);
    y = constrain(y, 0, height);
  }

  boolean collidesWith(Box box) {
    // Check for collision using bounding box collision detection
    float playerLeft = x - playerImg.width * scale / 2;
    float playerRight = x + playerImg.width * scale / 2;
    float playerTop = y - playerImg.height * scale / 2;
    float playerBottom = y + playerImg.height * scale / 2;
    
    float boxLeft = box.x - box.width / 2;
    float boxRight = box.x + box.width / 2;
    float boxTop = box.y - box.height / 2;
    float boxBottom = box.y + box.height / 2;
    
    // Check if the player sprite's bounding box overlaps with the box's bounding box
    return playerLeft < boxRight &&
           playerRight > boxLeft &&
           playerTop < boxBottom &&
           playerBottom > boxTop;
  }
}

class Box {
  float x, y;
  float width, height;
  color boxColor;
  
  Box(float x, float y, float width, float height, color boxColor) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.boxColor = boxColor;
  }
  
  void display() {
    fill(boxColor);
    rectMode(CENTER);
    rect(x, y, width, height);
  }
}

void setup() {
  size(1920, 1080);
  imageMode(CENTER);
  
  // Create player sprite with small scale (e.g. 0.5).
  s1 = new PlayerSprite(width / 2, height / 2, 0.5);
  
  // Create boxes and add them to the boxes array
  boxes = new ArrayList<Box>();
  boxes.add(new Box(200, 300, 100, 100, color(124, 124, 124)));  // Example box 1
  boxes.add(new Box(600, 400, 150, 150, color(0, 255, 0)));  // Example box 2
}

void draw() {
background(255);

// Handle keyboard input and adjust player's velocity
handleInput();

// Update and display player sprite
s1.update();
s1.display();

// Display boxes
for (Box box : boxes) {
box.display();
}
}

void handleInput() {
// Adjust player's velocity based on keyboard input
float speed = 5; // Adjust the speed value to control player's movement speed

if (keyPressed) {
if (key == 'w' || key == 'W') {
s1.velocityY = -speed; // Move up
} else if (key == 's' || key == 'S') {
s1.velocityY = speed; // Move down
} else if (key == 'a' || key == 'A') {
s1.velocityX = -speed; // Move left
} else if (key == 'd' || key == 'D') {
s1.velocityX = speed; // Move right
}
} else {
// Stop movement if no key is pressed
s1.velocityX = 0;
s1.velocityY = 0;
}
}