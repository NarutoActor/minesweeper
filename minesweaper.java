import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;


public class minesweaper{
JFrame g = new JFrame("Mineseeper J");
ImageIcon image = new ImageIcon("flag2.png");
JPanel p1 = new JPanel(new BorderLayout());
JPanel p2 = new JPanel();
JPanel p4 = new JPanel(new GridLayout(1,1));

JTextField t1 = new JTextField("000.00");
JButton b1 = new JButton("New Game");
JButton[][] box = new JButton[9][9];
LBOX[][] empt = new LBOX[9][9];
String[] sss = {"Beginner","Intermeidiate","Expert"};
JComboBox cb = new JComboBox(sss);
score s = new score();
ngame n = new ngame();
Timer time = new Timer(9, s);//Suppost to be theortically 10 but do to arithmitic lag the value is set to 9
private int maxmines = 10;
int ran1 = 0;
int ran2 = 0;
int click = 0;
int dif = 0;
int temp1 = 0;
int maxc = 71;
int size1 = 9;
int size2 = 9;
int frames1 = 340;
int frames2 = 340;
double temp2 =0;
boolean gamestate = false;
boolean changedif = false;
JPanel p3;
Click c = new Click();
public minesweaper(){
	
	
	p3 = new board();
	new fill();
	
	for(int i=0;i<empt.length;i++){
		for(int j=0;j<empt[0].length;j++){
			empt[i][j] = new LBOX(i,j);
			empt[i][j].addMouseListener(c);
		}
	}
	
	
	
	
	for(int i=0;i<box.length;i++){
		for(int j=0;j<box[0].length;j++){
			p3.add(empt[i][j]);
		}
		
	}
	t1.setEditable(false);
	b1.addActionListener(n);
	p2.add(t1);
	p2.add(b1);
	p2.add(cb);
	p4.add(p3);
	g.add(p2, BorderLayout.NORTH);
	g.add(p4, BorderLayout.SOUTH);
	g.setEnabled(true);
	g.setLocationRelativeTo(null);
	g.setDefaultCloseOperation(g.DISPOSE_ON_CLOSE);
	g.setVisible(true);
	g.setResizable(false);
	g.setSize(frames1,frames2);//340
	for(int i=0;i<box.length;i++){
		for(int j=0;j<box[0].length;j++){
			empt[i][j].setEnabled(false);
		}
		
	}
}
	public static void main(String[] args) {//------------------------------MAIN METHOD---------
		new minesweaper();
	}
	
	
	public class Click implements MouseListener {
		public void mouseClicked(MouseEvent e) {
		
			if (SwingUtilities.isRightMouseButton(e) || e.isControlDown()) {
				LBOX l = (LBOX)e.getSource();
				int i = l.getI();
				int j = l.getJ();
				boolean f = l.flag;
				boolean c = l.click;
				if(empt[i][j].isEnabled()){
				if(!f&&!c){
				empt[i][j].setIcon(image);
				empt[i][j].flag = true;
				}
				if(f){
				empt[i][j].setIcon(box[i][j].getIcon());
				empt[i][j].flag = false;
				}
				
				}
	        }
			
			if (SwingUtilities.isLeftMouseButton(e) && (!e.isControlDown())){
				LBOX l = (LBOX)e.getSource();
				int i = l.getI();
				int j = l.getJ();
				empt[i][j].setIcon(box[i][j].getIcon());
				empt[i][j].flag = false;
				if(!(empt[i][j].click)&&empt[i][j].isEnabled()){
				empt[i][j].click=true;
				System.out.println("("+(l.getJ()+1)+","+(l.getI()+1)+")");
				empt[i][j].setText(box[i][j].getText());
				if(empt[i][j].getText().equals("*")){
					time.stop();
					for(int ir=0;ir<empt.length;ir++){
						for(int jr=0;jr<empt[0].length;jr++){
							empt[ir][jr].setText(box[ir][jr].getText());
							empt[ir][jr].setEnabled(false);
							empt[ir][jr].setIcon(box[ir][jr].getIcon());
							empt[ir][jr].flag = false;
						}
					}
					JOptionPane.showMessageDialog(null, "KA-BOOM");
				}
				click = click + 1;
				if(dif==0){
					if(click==maxc){
						time.stop();
						for(int ir=0;ir<empt.length;ir++){
							for(int jr=0;jr<empt[0].length;jr++){
								empt[ir][jr].setText(box[ir][jr].getText());
								empt[ir][jr].setEnabled(false);
								empt[ir][jr].setIcon(box[ir][jr].getIcon());
								empt[ir][jr].flag = false;
							}
						}
						JOptionPane.showMessageDialog(null, "CONGRADULATIONS-YOU WON!(On easy)\nYour Score:"+t1.getText());
					}
				}
			}
			}

			
		}
		public void mouseEntered(MouseEvent arg0) {
		}
		public void mouseExited(MouseEvent arg0) {	
		}
		public void mousePressed(MouseEvent e) {
			mouseClicked(e);
		}
		public void mouseReleased(MouseEvent e) {
			mouseClicked(e);
		}
		
	}	
	public class LBOX extends JButton{
		private int i=0;
		private int j=0;
		boolean flag = false;
		boolean click = false;
		public LBOX(int i, int j){
			this.i=i;
			this.j=j;
			
		}
		public int getI() {
			return i;
		}
		public void setI(int i) {
			this.i = i;
		}
		public int getJ() {
			return j;
		}
		public void setJ(int j) {
			this.j = j;
		}

		}
	
	public class score implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			temp2 = (Double.parseDouble(t1.getText())+.01);
			temp2 = Math.round(temp2*100);
			temp2 = temp2/100.0;
			t1.setText(""+temp2);
		}
	}
	public class ngame implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			time.stop();
			t1.setText("000.00");
			p3 = new board(cb.getSelectedIndex());
			new fill();
			for(int i=0;i<box.length;i++){
				for(int j=0;j<box[0].length;j++){
					p3.add(empt[i][j]);
				}
			}
			for(int i=0;i<box.length;i++){
				for(int j=0;j<box[0].length;j++){
					empt[i][j].setText("");
					empt[i][j].click=false;
					empt[i][j].flag =false;
					empt[i][j].setIcon(box[i][j].getIcon());
					click=0;
				}
					
			}
				p4.removeAll();
				p3.revalidate();
				p4.add(p3);
				p4.revalidate();
				time.restart();
			for(int i=0;i<box.length;i++){
				for(int j=0;j<box[0].length;j++){
					empt[i][j].setEnabled(true);
				}
			}
			time.start();
			gamestate=true;
		}
	}
	public class fill{{
		for(int i=0;i<box.length;i++){
			for(int j=0;j<box[0].length;j++){
				box[i][j] = new JButton();
			}
			
		}
		for(int i=0;i<box.length;i++){
			for(int j=0;j<box[0].length;j++){
				box[i][j].setText("0");
			}
			
		}
		for(int i =0;i<maxmines;i++){
		ran1 = randInt(0,(size1-1));
		ran2 = randInt(0,(size2-1));
			box[ran1][ran2].setText("*");
		}
		for(int i=0;i<box.length;i++){
			for(int j=0;j<box[0].length;j++){
				if(box[i][j].getText().equals("*")){
					if(i>0&&j>0){//UPLEFT is posiable
						if(!(box[i-1][j-1].getText().equals("*"))){
							box[i-1][j-1].setText((Integer.parseInt(box[i-1][j-1].getText())+1)+"");
						}
					}
					if(j>0){//UP
						if(!(box[i][j-1].getText().equals("*"))){
							box[i][j-1].setText((Integer.parseInt(box[i][j-1].getText())+1)+"");
						}
					}
					if(j>0 && i<(box.length-1)){//UPRIGHT
						if(!(box[i+1][j-1].getText().equals("*"))){
							box[i+1][j-1].setText((Integer.parseInt(box[i+1][j-1].getText())+1)+"");
						}
					}
					if(i>0){//LEFT
						if(!(box[i-1][j].getText().equals("*"))){
							box[i-1][j].setText((Integer.parseInt(box[i-1][j].getText())+1)+"");
						}
					}
					if(i>0 && j<(box[0].length-1)){//DOWNLEFT
						if(!(box[i-1][j+1].getText().equals("*"))){
							box[i-1][j+1].setText((Integer.parseInt(box[i-1][j+1].getText())+1)+"");
						}
						
					}
					if(i<(box.length-1)){//RIGHT
						if(!(box[i+1][j].getText().equals("*"))){
							box[i+1][j].setText((Integer.parseInt(box[i+1][j].getText())+1)+"");
						}
						
					}
					if(j<(box[0].length-1)){//DOWN
						if(!(box[i][j+1].getText().equals("*"))){
							box[i][j+1].setText((Integer.parseInt(box[i][j+1].getText())+1)+"");
						}
					}
					if(i<(box.length-1)&&j<(box[0].length-1)){//DOWNRIGHT
						if(!(box[i+1][j+1].getText().equals("*"))){
							box[i+1][j+1].setText((Integer.parseInt(box[i+1][j+1].getText())+1)+"");
						}
					}
				}
			}
			
		}
		
	}
}
	public class board extends JPanel{
		//new GridLayout(9,9));
		
		board(){
			dif = 0;
			size1 = 9;
			size2 = 9;
			maxmines = 10;
			maxc = 71;
			setLayout(new GridLayout(size1,size2));
		}
		board(int d){
			if(d==0||d<0||d>2){//Easy Setting
				frames1 = 340;
				frames2 = 340;
				g.setSize(frames1,frames2);
				dif = 0;
				size1 = 9;
				size2 = 9;
				maxmines = 10;
				maxc = 71;
				box = new JButton[size1][size2];
				empt = new LBOX[size1][size2];
			}
			if(d==1){//Intermediate Setting
				frames1 = 620;
				frames2 = 540;
				g.setSize(frames1,frames2);
				dif = 1;
				size1 = 16;
				size2 = 16;
				maxmines = 40;
				maxc = 216;
				box = new JButton[size1][size2];
				empt = new LBOX[size1][size2];
			}
			if(d==2){//Expert Setting
				frames1 = 1180;
				frames2 = 536;
				g.setSize(frames1,frames2);
				dif = 2;
				size1 = 16;
				size2 = 30;
				maxmines = 99;
				maxc = 381;
				box = new JButton[size1][size2];
				empt = new LBOX[size1][size2];
			}
			setLayout(new GridLayout(size1,size2));
			for(int i=0;i<empt.length;i++){
				for(int j=0;j<empt[0].length;j++){
					empt[i][j] = new LBOX(i,j);
					empt[i][j].addMouseListener(c);
				}
			}
			
		}
	}
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
