package missionTimer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MissionTimerPanel extends JPanel implements ActionListener, MouseListener
{
	private static final int SPACING = 45;
	private static final Color COLOR_ON = new Color(165,245,112);
	private static final Color FRAME_COLOR_ON = new Color(165,245,112);
	private static final Color COLOR_OFF = new Color(85,85,85);
	private static final Color FRAME_COLOR_OFF = new Color(85,85,85);
	private static final Point2D START_HIGHLIGHT = new Point2D.Float(0, 0);
	private static final Point2D STOP_HIGHLIGHT = new Point2D.Float(200, 500);
	private static final float[] FRACTIONS_HIGHLIGHT = {0.0f, 0.5f, 1.0f};
	private static final Color[] COLORS_HIGHLIGHT = {new Color(0,41,102), new Color(0,41,102), new Color(0,41,102)};
	private final BufferedImage[][] DIGIT_ARRAY = {{createDigit(0,0),createDigit(1,0),createDigit(2,0),createDigit(3,0),createDigit(4,0),createDigit(5,0),createDigit(6,0),createDigit(7,0),createDigit(8,0),createDigit(9,0)}
												  ,{createDigit(0,1),createDigit(1,1),createDigit(2,1),createDigit(3,1),createDigit(4,1),createDigit(5,1),createDigit(6,1),createDigit(7,1),createDigit(8,1),createDigit(9,1)}
												  ,{createDigit(0,2),createDigit(1,2),createDigit(2,2),createDigit(3,2),createDigit(4,2),createDigit(5,2)}
											      ,{createDigit(0,3),createDigit(1,3),createDigit(2,3),createDigit(3,3),createDigit(4,3),createDigit(5,3),createDigit(6,3),createDigit(7,3),createDigit(8,3),createDigit(9,3)}
											      ,{createDigit(0,4),createDigit(1,4),createDigit(2,4),createDigit(3,4),createDigit(4,4),createDigit(5,4)}
											      ,{createDigit(0,5),createDigit(1,5),createDigit(2,5),createDigit(3,5),createDigit(4,5),createDigit(5,5),createDigit(6,5),createDigit(7,5),createDigit(8,5),createDigit(9,5)}
											      ,{createDigit(0,6),createDigit(1,6),createDigit(2,6),createDigit(3,6),createDigit(4,6),createDigit(5,6),createDigit(6,6),createDigit(7,6),createDigit(8,6),createDigit(9,6)}};
	private final BufferedImage[] DOTS = {createDots(true),createDots(false)};
	private final BufferedImage DOT = createDot();
	private final BufferedImage BACKGROUND_IMAGE = createBackground(450,180);
	private Timer TIMER = new Timer(100, this);
	private int p_sec = 0;
	private int sec_right = 0;
	private int sec_left = 0;
	private int min_right = 0;
	private int min_left = 0;
	private int hour_right = 0;
	private int hour_left = 0;
	private int count = 0;
	private int dot = 1;
	
	private BufferedImage createDigit(int digit, int num)
	{
		GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage IMAGE = gfxConf.createCompatibleImage(450, 180, Transparency.TRANSLUCENT);
		Graphics2D g2 = (Graphics2D) IMAGE.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
	            RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
	            RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_DITHERING,
	            RenderingHints.VALUE_DITHER_ENABLE);
	    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
	            RenderingHints.VALUE_RENDER_QUALITY);
	    
	    	GeneralPath segment_a = new GeneralPath();
	    	segment_a.moveTo( 5+num*SPACING, 4);
	    	segment_a.lineTo( 9+num*SPACING, 0);
	    	segment_a.lineTo(21+num*SPACING, 0);
	    	segment_a.lineTo(25+num*SPACING, 4);
	    	segment_a.lineTo(21+num*SPACING, 8);
	    	segment_a.lineTo( 9+num*SPACING, 8);
	    	segment_a.closePath();
	    	GeneralPath segment_b = new GeneralPath();
	    	segment_b.moveTo(26+num*SPACING,  5);
	    	segment_b.lineTo(30+num*SPACING,  9);
	    	segment_b.lineTo(30+num*SPACING, 21);
	    	segment_b.lineTo(26+num*SPACING, 25);
	    	segment_b.lineTo(22+num*SPACING, 21);
	    	segment_b.lineTo(22+num*SPACING,  9);
	    	segment_b.closePath();
	    	GeneralPath segment_c = new GeneralPath();
	    	segment_c.moveTo(26+num*SPACING, 27);
	    	segment_c.lineTo(30+num*SPACING, 31);
	    	segment_c.lineTo(30+num*SPACING, 43);
	    	segment_c.lineTo(26+num*SPACING, 47);
	    	segment_c.lineTo(22+num*SPACING, 43);
	    	segment_c.lineTo(22+num*SPACING, 31);
	    	segment_c.closePath();
	    	GeneralPath segment_d = new GeneralPath();
	    	segment_d.moveTo( 5+num*SPACING, 48);
	    	segment_d.lineTo( 9+num*SPACING, 44);
	    	segment_d.lineTo(21+num*SPACING, 44);
	    	segment_d.lineTo(25+num*SPACING, 48);
	    	segment_d.lineTo(21+num*SPACING, 52);
	    	segment_d.lineTo( 9+num*SPACING, 52);
	    	segment_d.closePath();
	    	GeneralPath segment_e = new GeneralPath();
	    	segment_e.moveTo(4+num*SPACING, 27);
	    	segment_e.lineTo(8+num*SPACING, 31);
	    	segment_e.lineTo(8+num*SPACING, 43);
	    	segment_e.lineTo(4+num*SPACING, 47);
	    	segment_e.lineTo(0+num*SPACING, 43);
	    	segment_e.lineTo(0+num*SPACING, 31);
	    	segment_e.closePath();
	    	GeneralPath segment_f = new GeneralPath();
	    	segment_f.moveTo(4+num*SPACING,  5);
	    	segment_f.lineTo(8+num*SPACING,  9);
	    	segment_f.lineTo(8+num*SPACING, 21);
	    	segment_f.lineTo(4+num*SPACING, 25);
	    	segment_f.lineTo(0+num*SPACING, 21);
	    	segment_f.lineTo(0+num*SPACING,  9);
	    	segment_f.closePath();
	    	GeneralPath segment_g = new GeneralPath();
	    	segment_g.moveTo( 5+num*SPACING, 26);
	    	segment_g.lineTo( 9+num*SPACING, 22);
	    	segment_g.lineTo(21+num*SPACING, 22);
	    	segment_g.lineTo(25+num*SPACING, 26);
	    	segment_g.lineTo(21+num*SPACING, 30);
	    	segment_g.lineTo( 9+num*SPACING, 30);
	    	segment_g.closePath();
		
	    	switch(digit)
	    	{
	    		case 1:
	    			g2.setColor(COLOR_ON);
	    			g2.fill(segment_b);
	    			g2.fill(segment_c);
	    			g2.setColor(FRAME_COLOR_ON);
	    			g2.draw(segment_b);
	    			g2.draw(segment_c);
	    			g2.setColor(COLOR_OFF);
	    			g2.fill(segment_a);
	    			g2.fill(segment_d);
	    			g2.fill(segment_e);
	    			g2.fill(segment_f);
	    			g2.fill(segment_g);
	    			g2.setColor(FRAME_COLOR_OFF);
	    			g2.draw(segment_a);
	    			g2.draw(segment_d);
	    			g2.draw(segment_e);
	    			g2.draw(segment_f);
	    			g2.draw(segment_g);
	    			break;
	    		case 2:
	    			g2.setColor(COLOR_ON);
	    	        g2.fill(segment_a);
	    	        g2.fill(segment_b);
	    	        g2.fill(segment_d);
	    	        g2.fill(segment_e);
	    	        g2.fill(segment_g);
	    	        g2.setColor(COLOR_OFF);
	    	        g2.fill(segment_c);
	    	        g2.fill(segment_f);
	    	        g2.setColor(FRAME_COLOR_ON);
	    	        g2.draw(segment_a);
	    	        g2.draw(segment_b);
	    	        g2.draw(segment_d);
	    	        g2.draw(segment_e);
	    	        g2.draw(segment_g);
	    	        g2.setColor(FRAME_COLOR_OFF);
	    	        g2.draw(segment_c);
	    	        g2.draw(segment_f);
	    	        break;
	    		case 3:
	    			g2.setColor(COLOR_ON);
	    			g2.fill(segment_a);
	    			g2.fill(segment_b);
	    			g2.fill(segment_c);
	    			g2.fill(segment_d);
	    			g2.fill(segment_g);
	    			g2.setColor(FRAME_COLOR_ON);
	    			g2.draw(segment_a);
	    			g2.draw(segment_b);
	    			g2.draw(segment_c);
	    			g2.draw(segment_d);
	    			g2.draw(segment_g);
	    			g2.setColor(COLOR_OFF);
	    			g2.fill(segment_e);
	    			g2.fill(segment_f);
	    			g2.setColor(FRAME_COLOR_OFF);
	    			g2.draw(segment_e);
	    			g2.draw(segment_f);
	    			break;
	    		case 4:
	    			g2.setColor(COLOR_ON);
	    			g2.fill(segment_b);
	    			g2.fill(segment_c);
	    	        g2.fill(segment_f);
	    	        g2.fill(segment_g);
	    			g2.setColor(FRAME_COLOR_ON);
	    			g2.draw(segment_b);
	    			g2.draw(segment_c);
	    			g2.draw(segment_f);
	    			g2.draw(segment_g);
	    			g2.setColor(COLOR_OFF);
	    			g2.fill(segment_a);
	    			g2.fill(segment_d);
	    			g2.fill(segment_e);
	    			g2.setColor(FRAME_COLOR_OFF);
	    			g2.draw(segment_a);
	    			g2.draw(segment_d);
	    			g2.draw(segment_e);
	    			break;
	    		case 5:
	    			g2.setColor(COLOR_ON);
	    			g2.fill(segment_a);
	    			g2.fill(segment_c);
	    			g2.fill(segment_d);
	    	        g2.fill(segment_f);
	    	        g2.fill(segment_g);
	    			g2.setColor(FRAME_COLOR_ON);
	    			g2.draw(segment_a);
	    			g2.draw(segment_c);
	    			g2.draw(segment_d);
	    			g2.draw(segment_f);
	    			g2.draw(segment_g);
	    			g2.setColor(COLOR_OFF);
	    			g2.fill(segment_b);
	    			g2.fill(segment_e);
	    			g2.setColor(FRAME_COLOR_OFF);
	    			g2.draw(segment_b);
	    			g2.draw(segment_e);
	    			break;
	    		case 6:
	    			g2.setColor(COLOR_ON);
	    			g2.fill(segment_a);
	    			g2.fill(segment_c);
	    			g2.fill(segment_d);
	    			g2.fill(segment_e);
	    			g2.fill(segment_f);
	    			g2.fill(segment_g);
	    			g2.setColor(FRAME_COLOR_ON);
	    			g2.draw(segment_a);
	    			g2.draw(segment_c);
	    			g2.draw(segment_d);
	    			g2.draw(segment_e);
	    			g2.draw(segment_f);
	    			g2.draw(segment_g);
	    			g2.setColor(COLOR_OFF);
	    			g2.fill(segment_b);
	    			g2.setColor(FRAME_COLOR_OFF);
	    			g2.draw(segment_b);
	    			break;
	    		case 7:
	    			g2.setColor(COLOR_ON);
	    			g2.fill(segment_a);
	    			g2.fill(segment_b);
	    			g2.fill(segment_c);
	    			g2.setColor(FRAME_COLOR_ON);
	    			g2.draw(segment_a);
	    			g2.draw(segment_b);
	    			g2.draw(segment_c);
	    			g2.setColor(COLOR_OFF);
	    			g2.fill(segment_d);
	    			g2.fill(segment_e);
	    			g2.fill(segment_f);
	    			g2.fill(segment_g);
	    			g2.setColor(FRAME_COLOR_OFF);
	    			g2.draw(segment_d);
	    			g2.draw(segment_e);
	    			g2.draw(segment_f);
	    			g2.draw(segment_g);
	    			break;
	    		case 8:
	    			g2.setColor(COLOR_ON);
	    			g2.fill(segment_a);
	    			g2.fill(segment_b);
	    			g2.fill(segment_c);
	    			g2.fill(segment_d);
	    			g2.fill(segment_e);
	    			g2.fill(segment_f);
	    			g2.fill(segment_g);
	    			g2.setColor(FRAME_COLOR_ON);
	    			g2.draw(segment_a);
	    			g2.draw(segment_b);
	    			g2.draw(segment_c);
	    			g2.draw(segment_d);
	    			g2.draw(segment_e);
	    			g2.draw(segment_f);
	    			g2.draw(segment_g);
	    			g2.setColor(COLOR_OFF);
	    			g2.setColor(FRAME_COLOR_OFF);
	    			break;
	    		case 9:
	    			g2.setColor(COLOR_ON);
	    			g2.fill(segment_a);
	    			g2.fill(segment_b);
	    			g2.fill(segment_c);
	    			g2.fill(segment_d);
	    			g2.fill(segment_f);
	    			g2.fill(segment_g);
	    			g2.setColor(FRAME_COLOR_ON);
	    			g2.draw(segment_a);
	    			g2.draw(segment_b);
	    			g2.draw(segment_c);
	    			g2.draw(segment_d);
	    			g2.draw(segment_f);
	    			g2.draw(segment_g);
	    			g2.setColor(COLOR_OFF);
	    			g2.fill(segment_e);
	    			g2.setColor(FRAME_COLOR_OFF);
	    			g2.draw(segment_e);
	    			break;
	    		case 0:
	    			g2.setColor(COLOR_ON);
	    			g2.fill(segment_a);
	    			g2.fill(segment_b);
	    			g2.fill(segment_c);
	    			g2.fill(segment_d);
	    			g2.fill(segment_e);
	    			g2.fill(segment_f);
	    			g2.setColor(FRAME_COLOR_ON);
	    			g2.draw(segment_a);
	    			g2.draw(segment_b);
	    			g2.draw(segment_c);
	    			g2.draw(segment_d);
	    			g2.draw(segment_e);
	    			g2.draw(segment_f);
	    			g2.setColor(COLOR_OFF);
	    			g2.fill(segment_g);
	    			g2.setColor(FRAME_COLOR_OFF);
	    			g2.draw(segment_g);
	    			break;
	    	}
		return IMAGE;
	}
	 
	
	private BufferedImage createDots(boolean on)
	{
		GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage IMAGE = gfxConf.createCompatibleImage(17, 50, Transparency.TRANSLUCENT);
		Graphics2D g2 = (Graphics2D) IMAGE.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
	            RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
	            RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_DITHERING,
	            RenderingHints.VALUE_DITHER_ENABLE);
	    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
	            RenderingHints.VALUE_RENDER_QUALITY);
	    if(on)
	    {
	    	g2.setColor(COLOR_ON);
	    	g2.fillOval(8, 10, 7, 7);
	    	g2.fillOval(8, 30, 7, 7);
	    	g2.setColor(FRAME_COLOR_ON);
	    	g2.drawOval(8, 10, 7, 7);
	    	g2.drawOval(8, 30, 7, 7);
	    }
	    else
	    {
	    	g2.setColor(COLOR_OFF);
	    	g2.fillOval(8, 10, 7, 7);
	    	g2.fillOval(8, 30, 7, 7);
	    	g2.setColor(FRAME_COLOR_OFF);
	    	g2.drawOval(8, 10, 7, 7);
	    	g2.drawOval(8, 30, 7, 7);
	    }
	    return IMAGE;
	}
	
	private BufferedImage createDot()
	{
		GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage IMAGE = gfxConf.createCompatibleImage(17, 50, Transparency.TRANSLUCENT);
		Graphics2D g2 = (Graphics2D) IMAGE.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
	            RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
	            RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_DITHERING,
	            RenderingHints.VALUE_DITHER_ENABLE);
	    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
	            RenderingHints.VALUE_RENDER_QUALITY);
	  
	    	g2.setColor(COLOR_ON);
	    	g2.fillOval(8, 10, 9, 9);
	    	g2.setColor(FRAME_COLOR_ON);
	    	g2.drawOval(8, 10, 9, 9);
	    return IMAGE;
	}
	
	private BufferedImage createBackground(int width, int height)
	{
		GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage IMAGE = gfxConf.createCompatibleImage(375,173, Transparency.TRANSLUCENT);
		Graphics2D g2 = (Graphics2D) IMAGE.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
	            RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
	            RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_DITHERING,
	            RenderingHints.VALUE_DITHER_ENABLE);
	    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
	            RenderingHints.VALUE_RENDER_QUALITY);
	    final LinearGradientPaint GRADIENT_HIGHLIGHT = new LinearGradientPaint(START_HIGHLIGHT, STOP_HIGHLIGHT, FRACTIONS_HIGHLIGHT, COLORS_HIGHLIGHT);
	    g2.setColor(new Color(0,0,68));
	    g2.fillRect(0, 0, 375, 173);
	    g2.setPaint(GRADIENT_HIGHLIGHT);
	    g2.fillRect(17, 79, 105, 82);
	    g2.fillRect(122, 79, 239, 82);
	    g2.setColor(Color.BLACK);
	    g2.drawRect(17, 79, 105, 82);
	    g2.drawRect(122, 79, 239,82);
	    
	    GeneralPath segment_left = new GeneralPath();
	    segment_left.moveTo(18, 44);
	    segment_left.lineTo(18, 36);
	    segment_left.lineTo(138, 36);
	    segment_left.lineTo(138, 38);
	    segment_left.lineTo(20, 38);
	    segment_left.lineTo(20, 44);
	    
	    GeneralPath segment_right = new GeneralPath();
	    segment_right.moveTo(361, 44);
	    segment_right.lineTo(361, 36);
	    segment_right.lineTo(241, 36);
	    segment_right.lineTo(241, 38);
	    segment_right.lineTo(359, 38);
	    segment_right.lineTo(359, 44);
	    
	    g2.setColor(Color.WHITE);
	    g2.fill(segment_left);
	    g2.fill(segment_right);
	    g2.draw(segment_left);
	    g2.draw(segment_right);
	    g2.drawString("TIMER", 175 ,42);
	    g2.drawString("HOURS", 50, 65);
	    g2.drawString("MIN", 155, 65);
	    g2.drawString("SEC", 245, 65);
	    return IMAGE;
	}
	
	public MissionTimerPanel()
	{
		setPreferredSize(new Dimension(385, 200));
		setSize(new Dimension(385, 200));
		addMouseListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		count++;
		p_sec++;
		if(count%10 == 0)
		{
			sec_right++;
			count = 0;
			p_sec = 0;
		}
		if(count<5)
		{
			dot = 1;
		}
		else
		{
			dot = 0;
		}
		if(sec_right == 10)
		{
			sec_right = 0;
			sec_left++;
		}
		if(sec_left == 6)
		{
			min_right++;
			sec_left = 0;
		}
		if(min_right == 10)
		{
			min_right = 0;
			min_left++;
		}
		if(min_left == 6)
		{
			hour_right++;
			min_left = 0;
		}
		if(hour_right == 10)
		{
			hour_right = 0;
			hour_left++;
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent event) 
	{
		if(event.getButton() == MouseEvent.BUTTON1)
		{
			if(TIMER.isRunning())
			{
				stopTimer();
			}
			else
			{
				startTimer();
			}
		}
		if(event.getClickCount() == 2)
		{
			resetTimer();
			stopTimer();
		}
	}
	
	public void stopTimer() 
	{
		TIMER.stop();
	}

	public void startTimer()
	{
		TIMER.start();
	}
	
	public void resetTimer()
	{
		sec_right = 0;
		sec_left = 0;
		min_right = 0;
		min_left = 0;
		hour_right = 0;
		hour_left = 0;
		count = 0;
		p_sec = 0;
		dot = 1;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
	    		RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
	    		RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
	    		RenderingHints.VALUE_STROKE_PURE);
	    g2.setRenderingHint(RenderingHints.KEY_DITHERING,
	    		RenderingHints.VALUE_DITHER_ENABLE);
	    g2.setRenderingHint(RenderingHints.KEY_RENDERING,
	    		RenderingHints.VALUE_RENDER_QUALITY);
	    g2.drawImage(BACKGROUND_IMAGE, 0, 0, this);
	    g2.drawImage(DOTS[dot], 109, 96, this);
	    g2.drawImage(DOTS[dot], 199, 96, this);
	    g2.drawImage(DOT, 288, 126, this);
	    g2.drawImage(DIGIT_ARRAY[0][hour_left], 38, 93, this);
	    g2.drawImage(DIGIT_ARRAY[1][hour_right], 38, 93, this);
	    g2.drawImage(DIGIT_ARRAY[2][min_left], 38, 93, this);
	    g2.drawImage(DIGIT_ARRAY[3][min_right], 38, 93, this);
	    g2.drawImage(DIGIT_ARRAY[4][sec_left], 38, 93, this);
	    g2.drawImage(DIGIT_ARRAY[5][sec_right], 38, 93, this);
	    g2.drawImage(DIGIT_ARRAY[6][p_sec], 38, 93, this);
	}
}
