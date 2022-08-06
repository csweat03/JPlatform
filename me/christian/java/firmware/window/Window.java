package me.christian.java.firmware.window;

import me.christian.java.firmware.ApplicationLogic;
import me.christian.java.firmware.utilities.IProcess;
import me.christian.java.firmware.window.attrib.CloseOperation;
import me.christian.java.firmware.window.attrib.DefaultDimension;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public abstract class Window implements IProcess {

    private JFrame frame;

    private final String title;
    private final int width, height;
    
    private String lead = "src\\me\\christian\\json\\windows\\", end = ".json";

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        
        ApplicationLogic.processes.get().add(this);
    }

    public Window(String title, DefaultDimension dimension) {
        this(title, dimension.getWidth(), dimension.getHeight());
    }

    @Override
    public String[] file() {
        return new String[] {lead + "Win" + title.replace(" ", "") + end};
    }

    @Override
    public String name() {
        return "window: " + title;
    }

    public void loading() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
        }

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(setCloseOperation().getOperation());
        frame.setUndecorated(false);
        frame.setLayout(new BorderLayout());
        frame.add(new JPanel() {
            public Dimension getPreferredSize() {
                return new Dimension(width, height);
            }

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                render();

            }
        });
        frame.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {}
            public void windowClosing(WindowEvent e) {}
            public void windowClosed(WindowEvent e) {
                ApplicationLogic.processes.get().remove(Window.this);
            }
            public void windowIconified(WindowEvent e) {}
            public void windowDeiconified(WindowEvent e) {}
            public void windowActivated(WindowEvent e) {}
            public void windowDeactivated(WindowEvent e) {}
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void finalizing() {

    }



    public JFrame getFrame() {
        return frame;
    }

    public abstract void render();

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CloseOperation setCloseOperation() {
        return CloseOperation.DISPOSE;
    }

}
