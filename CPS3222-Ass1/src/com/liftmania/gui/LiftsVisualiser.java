package com.liftmania.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.liftmania.LiftController;

@SuppressWarnings("serial")
public class LiftsVisualiser extends JFrame implements ActionListener {

	public Shaft[] shafts;
	int numFloors;
	int numLifts;

	LiftController controller;

	public LiftsVisualiser(int numFloors, int numLifts) {
		this.numFloors = numFloors;
		this.numLifts = numLifts;
		shafts = new Shaft[numLifts];
	}
	
	public void setController(LiftController controller){
		this.controller = controller;
	}
	
	public Shaft[] getShafts(){
		return this.shafts;
	}
	
	public void setShafts(Shaft shafts[]){
		this.shafts = shafts;
	}

	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(400, 700);
		setTitle("Lifts Visualiser");

		// Create layout for shafts
		JPanel shaftsPanel = new JPanel(new GridLayout(1, numLifts));
		for (int i = 0; i < numLifts; i++) {
//			shafts[i] = new Shaft(this, numFloors, controller.getLifts()[i]);
			shaftsPanel.add(shafts[i]);
			
//			// Put each shaft in a separate thread for independent animation
//			new Thread(shafts[i]).start();
		}
		add(shaftsPanel, BorderLayout.CENTER);

		// Create Call Lift buttons
		JPanel llPanel = new JPanel(new BorderLayout());
		JPanel leftPanel = new JPanel(new GridLayout(numFloors, 1));

		for (int i = numFloors - 1; i >= 0; i--) {
			JButton btnCall = new JButton("Call");
			btnCall.setActionCommand("call" + "," + Integer.toString(i));
			btnCall.addActionListener(this);
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
			p.add(btnCall);
			leftPanel.add(p);
		}

		llPanel.add(leftPanel, BorderLayout.CENTER);
		llPanel.add(new JLabel("  "), BorderLayout.NORTH); // Spacer
		llPanel.add(new JLabel("  "), BorderLayout.SOUTH); // Spacer

		add(llPanel, BorderLayout.WEST);

		setVisible(true);
	}

	public void animateLiftMovement(int liftId, int floorNumber) {
		shafts[liftId].lift.setToFloor(floorNumber);
		
		//Delegate animation to the shaft responsible so processing can move on
		shafts[liftId].addAnimationCommand(new AnimationCommand(AnimationCommand.Command.move, floorNumber));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringTokenizer tokenizer = new StringTokenizer(e.getActionCommand(), ",");
		String cmd = tokenizer.nextToken();
		if (cmd.equalsIgnoreCase("move")) {
			int liftNumber = Integer.parseInt(tokenizer.nextToken());
			int floor = Integer.parseInt(tokenizer.nextToken());
			controller.moveLift(liftNumber, floor);
		} else if (cmd.equalsIgnoreCase("open")) {
			int liftNumber = Integer.parseInt(tokenizer.nextToken());
			controller.openLiftDoor(liftNumber);
		} else if (cmd.equalsIgnoreCase("close")) {
			int liftNumber = Integer.parseInt(tokenizer.nextToken());
			controller.closeLiftDoor(liftNumber);
		} else if (cmd.equalsIgnoreCase("call")) {
			int floorNumber = Integer.parseInt(tokenizer.nextToken());
			controller.callLiftToFloor(floorNumber);
		}

	}

}
