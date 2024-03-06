package serie01;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class EventsTester {

    // ATTRIBUTS D'INSTANCE
    private final JFrame mainFrame;
    private JFrame testFrame;
    private final JButton btnNewTestFrame;
    private final JButton btnRazCompteur;
    private final JTextArea[] areas;
    private final JScrollPane[] panes;
    private final String[] names_areas;
    private int compteur;
    private int razCompteur;
    

    // CONSTRUCTEURS
    
    public EventsTester(){
        // VUE
        mainFrame = createFrame();
        testFrame = createNewTestFrame();
        btnNewTestFrame = new JButton("Nouvelle fenêtre");
        btnRazCompteur = new JButton("RAZ Compteur");
        names_areas = new String[]{"MouseListener", "WindowFocusListener", "WindowListener",
        		"KeyListener", "WindowStateListener", "MouseWheelListener", "MouseMotionListener"};
        
        areas = new JTextArea[7];
        panes = new JScrollPane[7];
    
        for(int i = 0; i < 7; i++) {
        	JTextArea textArea = new JTextArea();
        	textArea.setEditable(false);
        	areas[i] = textArea;
        	JScrollPane scrollPane = new JScrollPane(textArea);
        	scrollPane.setPreferredSize(new Dimension(300, 200));
            TitledBorder titledBorder = BorderFactory.createTitledBorder(names_areas[i]);
            scrollPane.setBorder(titledBorder);      
            panes[i] = scrollPane;
        }
        compteur = 1;
        razCompteur = 1;
        placeComponents();
        // CONTROLEUR
        connectControllers();
    }
    
    // COMMANDES
    
    public void display() {
        mainFrame.pack();

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // OUTILS
    private JFrame createNewTestFrame() {
    	final int frameWidth = 200;
        final int frameHeight = 100;
        JFrame f = new JFrame("Zone de test");
        f.setPreferredSize(new Dimension(frameWidth, frameHeight));      
        return f;
    }
    
    private JFrame createFrame() {
        JFrame f = new JFrame("Tests sur les événements - ZONE D'AFFICHAGE");
        return f;
    }
    
    private void placeComponents() {
        JPanel p = new JPanel();
        { //--
            p.add(btnNewTestFrame);
            p.add(btnRazCompteur);
            
        } //--
        mainFrame.add(p, BorderLayout.NORTH);
        p = new JPanel(new GridLayout(3, 3));
        { //--
        	for(int i = 0; i < panes.length ; i++) {
        		p.add(panes[i]);
        	}
        } //--
        mainFrame.add(p, BorderLayout.SOUTH);
    }
    
    
    private void testFrameControllers() {
    	testFrame.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    	        areas[0].append(compteur + " MOUSE_CLICKED\n");
    	        compteur+=1;
    	    }
    		public void mousePressed(MouseEvent e) {
    			areas[0].append(compteur + " MOUSE_PRESSED\n");
    	        compteur+=1;
    	    }
    		@Override
		    public void mouseReleased(MouseEvent e) {
    			areas[0].append(compteur + " MOUSE_RELEASED\n");
    	        compteur+=1;
		    }
    		public void mouseEntered(MouseEvent e) {
    			areas[0].append(compteur + " MOUSE_ENTERED\n");
    	        compteur+=1;
		    }

		    public void mouseExited(MouseEvent e) {
		    	areas[0].append(compteur + " MOUSE_EXITED\n");
    	        compteur+=1;
		    }
    	});
    	testFrame.addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowOpened(WindowEvent e) {
    			areas[2].append(compteur + " WINDOW_OPENED\n");
                compteur+=1;
    	    }

    	    @Override
    	    public void windowClosing(WindowEvent e) {
    	    	areas[2].append(compteur + " WINDOW_CLOSING\n");
                compteur+=1;
    	    }
            @Override
            public void windowActivated(WindowEvent e) {
                 areas[2].append(compteur + " WINDOW_ACTIVATED\n");
                 compteur+=1;
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
                 areas[2].append(compteur + " WINDOW_DEACTIVATED\n");
                 compteur+=1;
            }
            
        });
    	testFrame.addWindowFocusListener(new WindowFocusListener() {
    		 public void windowGainedFocus(WindowEvent e) {
             	areas[1].append(compteur + " WINDOW_GAINED_FOCUS\n");
                 compteur+=1;
             }

             public void windowLostFocus(WindowEvent e) {
             	areas[1].append(compteur + " WINDOW_LOST_FOCUS\n");
                 compteur+=1;
             }
    	});
    	
    	testFrame.addWindowStateListener(new WindowStateListener() {
    		public void windowStateChanged(WindowEvent e) {
            	areas[4].append(compteur + " WINDOW_STATE_CHANGED\n");
                compteur+=1;
            }
        });
    	
    	testFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            	areas[3].append(compteur + " KEY_TYPED\n");
                compteur+=1;
            }

            @Override
            public void keyPressed(KeyEvent e) {
            	areas[3].append(compteur + " KEY_PRESSED\n");
                compteur+=1;
            }

            @Override
            public void keyReleased(KeyEvent e) {
            	areas[3].append(compteur + " KEY_RELEASED\n");
                compteur+=1;
            }
        });
    	testFrame.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
            	areas[5].append(compteur + " MOUSE_WHEEL_MOVED\n");
                compteur+=1;
            }
        });
    	
    	testFrame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            	areas[6].append(compteur + " MOUSE_MOVED\n");
                compteur+=1;
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                
            }

        });
    	
    }
    private void connectControllers() {
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	
        btnNewTestFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 testFrame.dispose();
            	 for(int i = 0; i < areas.length ; i++) {
            		 areas[i].setText("");
            	 }
                 
                 // Crée une nouvelle fenêtre de test
                 testFrame = createNewTestFrame();
                 testFrame.setLocationRelativeTo(mainFrame);
                 testFrame.setVisible(true);
                 testFrame.pack();
                 testFrameControllers();
            }
        });
        btnRazCompteur.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
            	 for(int i = 0; i < areas.length; i++) {
            		 areas[i].append("--- RAZ " + razCompteur + " ---\n");
            	 }
            	 compteur = 1;
            	 razCompteur+=1;
            }
        });
        
        
    }

    // POINT D'ENTREE
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EventsTester().display();
            }
        });
    }
}
