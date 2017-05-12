package othello;

import java.awt.Dimension;
import java.awt.geom.Ellipse2D;

class RoundButton extends RoundedCornerButton {
	  public RoundButton(String text) {
	    super(text);
	    setFocusPainted(false);
	    initShape();
	  }
	  @Override public Dimension getPreferredSize() {
	    Dimension d = super.getPreferredSize();
	    d.width = d.height = Math.max(d.width, d.height);
	    return d;
	  }
	  protected void initShape() {
	    if (!getBounds().equals(base)) {
	      base = getBounds();
	      shape = new Ellipse2D.Float(0, 0, getWidth() - 1, getHeight() - 1);
	      border = new Ellipse2D.Float(FOCUS_STROKE, FOCUS_STROKE,
	                                   getWidth()  - 1 - FOCUS_STROKE * 2,
	                                   getHeight() - 1 - FOCUS_STROKE * 2);
	    }
	  }
	}
