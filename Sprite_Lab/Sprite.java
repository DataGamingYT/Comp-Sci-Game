package Sprite_Lab;

public class Sprite {
  PImage image;
  float center_x, center_y;
  float change_x, change_y;
  float w, h; // width and height of Sprite (note that w and h are used because
              // width and height are reserved variables for window size.)
  
  public Sprite(String filename, float scale, float x, float y) {
    // initialize variables in this constructor
    image = loadImage(filename);
    w = image.width * scale;
    h = image.height * scale;
    center_x = x;
    center_y = y;
    change_x = 0;
    change_y = 0;
  }
  
  public Sprite(String filename, float scale) {
    // use this() to call the previous constructor
    this(filename, scale, 0, 0);
  }
  
  public void display() {
    // use image(image_file, x, y, width_image, height_image) to draw image
    image(image, center_x, center_y, w, h);
  }
  
  public void update() {
    // update position by adding velocity
    center_x += change_x;
    center_y += change_y;
  }
}
