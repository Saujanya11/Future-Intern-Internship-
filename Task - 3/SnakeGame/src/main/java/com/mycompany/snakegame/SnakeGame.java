/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.snakegame;

/**
 *
 * @author Saujanya
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
    private final int TILE_SIZE = 25;
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int TOTAL_TILES = (WIDTH * HEIGHT) / (TILE_SIZE * TILE_SIZE);

    private int[] x = new int[TOTAL_TILES];
    private int[] y = new int[TOTAL_TILES];
    private int snakeLength;
    private int score = 0;

    private int foodX;
    private int foodY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private Random random;
    private int speed = 150;

    public SnakeGame() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') direction = 'L';
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') direction = 'R';
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D') direction = 'U';
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') direction = 'D';
                        break;
                    case KeyEvent.VK_MINUS:
                        decreaseSpeed();
                        break;
                }
            }
        });
        random = new Random();
        startGame();
    }

    public void startGame() {
        snakeLength = 3;
        score = 0;
        for (int i = 0; i < snakeLength; i++) {
            x[i] = 100 - (i * TILE_SIZE);
            y[i] = 100;
        }
        spawnFood();
        running = true;
        timer = new Timer(speed, this);
        timer.start();
    }

    public void decreaseSpeed() {
        if (speed < 500) {
            speed += 50;
            timer.setDelay(speed);
        }
    }

    public void increaseSpeed() {
        if (speed > 50) {
            speed -= 5;
            timer.setDelay(speed);
        }
    }

    public void spawnFood() {
        foodX = random.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE;
        foodY = random.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
    }

    public void move() {
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U': y[0] -= TILE_SIZE; break;
            case 'D': y[0] += TILE_SIZE; break;
            case 'L': x[0] -= TILE_SIZE; break;
            case 'R': x[0] += TILE_SIZE; break;
        }
    }

    public void checkCollision() {
        for (int i = snakeLength; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
            running = false;
        }
        if (!running) timer.stop();
    }

    public void checkFood() {
        if (x[0] == foodX && y[0] == foodY) {
            snakeLength++;
            score++;
            increaseSpeed();
            spawnFood();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.RED);
            g.fillOval(foodX, foodY, TILE_SIZE, TILE_SIZE);
            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(new Color(0, 128, 0)); // Dark green head
                    g.fillOval(x[i], y[i], TILE_SIZE, TILE_SIZE);
                } else {
                    g.setColor(new Color(0, 200, 0)); // Lighter green body
                    g.fillOval(x[i], y[i], TILE_SIZE, TILE_SIZE);
                }
            }
            
            // Display score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, WIDTH - 100, 30);
        } else {
            gameOver(g);
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, (WIDTH - metrics.stringWidth("Score: " + score)) / 2, HEIGHT / 2 + 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFood();
            checkCollision();
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}