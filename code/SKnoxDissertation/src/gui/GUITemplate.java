package gui;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.Control;

public class GUITemplate {
	JFrame frame;
	Control parent;
	
	public GUITemplate(Control parent){
		this.parent = parent;
	}
	
	public void setVisible(){
		frame.setVisible(true);
	}
	
	public void setInvisible(){
		frame.setVisible(false);
	}
}
