package missionTimer;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class MissionTimerTest extends JFrame
{
	public MissionTimerTest()
	{
		super("Timer");
		Container c = getContentPane();
		c.add(new MissionTimerPanel(),BorderLayout.CENTER);
	}
	
	public static void main(String args[])
	{
		MissionTimerTest mt = new MissionTimerTest();
		mt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mt.setSize(380, 200);
		mt.setResizable(false);
		mt.setVisible(true);
	}
}
