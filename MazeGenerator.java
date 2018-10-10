package javaapplication2;

import java.util.Collections;
import java.util.Arrays;
import static java.awt.Color.*;
import java.util.Scanner;
import studijosKTU.ScreenKTU;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author Indrė
 */

public class MazeGenerator extends ScreenKTU{ 

        private static int x;
	private static int y;
	private static String[][] maze;
        public static ScreenKTU image;
        private static int counter;
 
         public MazeGenerator(int x, int y) {
            super(20, 20, y+10, x+10);
            Image fd =  super.createImage(y+10, 10+x);
            this.x = x;
            this.y = y;
                
            generateMaze(0,0);
         }        
	
        public void display() {
            
               // scr.refresh();
		for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        
                     //   System.out.println(maze[x][y]);
                        if(!maze[i][j].contains("w")){
                            super.drawLine(j+5, i+5, 2, 0, 3);
                        }
                       if(!maze[i][j].contains("e")){
                            super.drawLine(j+5, i+1+5, 2, 0, 3);
                        }
                        if(!maze[i][j].contains("n")){
                            super.drawLine(j+5, i+5, 0, 2, 3);
                        }
                        if(!maze[i][j].contains("s")){
                            super.drawLine(j+1+5, i+5, 0, 2, 3);
                        }
                        
                    }
                }super.setFontColor(black);
                super.drawLine(5, 5, 2, 0, 3);
                super.drawLine(y-1+5, x+5, 2, 0, 3);
                super.refresh();
        }
        
 
	private void generateMaze(int cx, int cy) {
		DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));
		for (DIR dir : dirs) {
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if (between(nx, x) && between(ny, y) 
                                && maze[nx][ny].isEmpty()) {
                                maze[cx][cy] += dir.bit;
				maze[nx][ny] += dir.opposite.bit;
                                
				generateMaze(nx, ny);
			}
		}
	}
 
	private static boolean between(int v, int upper) {
		return (v >= 0) && (v < upper);
	}

	private enum DIR {
		
                N('n', 0, -1), S('s', 0, 1), E('e', 1, 0), W('w', -1, 0);
	
                private final char bit;
		private final int dx;
		private final int dy;
		private DIR opposite;
 
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}
 
		private DIR(char bit, int dx, int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};

        public static void Saving(int x, int y) throws IOException{
        BufferedImage buffImg = new BufferedImage(y*30+60, x*30+60, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = buffImg.createGraphics();
        g2d.setColor(Color.white);
        g2d.drawString("Maze no:" + counter, 15, 15);
        for (int i = 1; i < x+1; i++) {
                    for (int j = 1; j < y+1; j++) {
                        
                     //   System.out.println(maze[x][y]);
                        if(!maze[i-1][j-1].contains("w")){
                            g2d.drawLine(j*30, i*30, j*30+30, i*30);
                        }
                       if(!maze[i-1][j-1].contains("e")){
                            g2d.drawLine(j*30, i*30+30, j*30+30, i*30+30);
                        }
                        if(!maze[i-1][j-1].contains("n")){
                            g2d.drawLine(j*30, i*30, j*30, i*30+30);
                        }
                        if(!maze[i-1][j-1].contains("s")){
                            g2d.drawLine(j*30+30, i*30, j*30+30,  i*30+30);
                        }
                        
                    }
                }
        g2d.setColor(white);
        g2d.drawLine(30, 30, 60, 30);
        g2d.drawLine(y*30+30, x*30, y*30+30, x*30+30);
              
        g2d.dispose();
        File file;
            file = new File("D:/Java/JavaApplication2/mazes/Maze" + counter + ".png");
        ImageIO.write(buffImg, "png", file);        
    }
        
	public static void main(String[] args) {
                  try {
                    Scanner S =new Scanner(System.in);
                    System.out.println("Write heigth");
                    int y = S.nextInt();
                    System.out.println("Write width");
                    int x = S.nextInt();
                    System.out.println("Write amount of maze");
                    int c = S.nextInt();
                    if(y > 85 || y < 10 || x > 40 || x < 10)
                    {
                        throw new Exception("Maze is too small or big");
                    }
                    maze = new String[x][y];
                    
                    while(counter < c){
                        counter ++;
                        for (int i = 0; i < x; i++) {
                        for (int j = 0; j < y; j++) {
                            maze[i][j] = "";
                        }
                    }
                        MazeGenerator screen = new MazeGenerator(x, y);
                        screen.print(1, x/2-4, "Maze no:" + counter);  
                        screen.display();
                        Saving(x, y);
                    }
                    System.out.println("Finished");
                }
                catch (Exception ex ){
                    System.out.println(ex);
                }
    }
        
} 
  
