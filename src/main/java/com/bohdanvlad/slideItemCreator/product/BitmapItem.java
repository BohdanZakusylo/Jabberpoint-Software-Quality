package com.bohdanvlad.slideItemCreator.product;

import com.bohdanvlad.style.Style;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;


/** <p>De klasse voor een Bitmap item</p>
 * <p>Bitmap items have the responsibility to draw themselves.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class BitmapItem extends SlideItem
{
  private BufferedImage bufferedImage;
  private String imageName;

  protected static final String FILE = "File ";
  protected static final String NOTFOUND = " not found";
  protected static final String DEFAULT_LOGO = "serclogo_fc.jpg";

// level is equal to item-level; name is the name of the file with the Image
	public BitmapItem(int level, String name)
	{
		super(level);
		this.imageName = name;
		try
		{
			this.bufferedImage = ImageIO.read(new File(this.imageName));
		}
		catch (IOException e)
		{
			try
			{
				this.bufferedImage = ImageIO.read(new File(DEFAULT_LOGO));
			}
			catch (IOException exception)
			{
				throw new IllegalArgumentException("No logo provided");
			}
		}
	}

// give the filename of the image
	public String getName() {
		return this.imageName;
	}

// give the  bounding box of the image
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		return new Rectangle((int) (myStyle.getIndent() * scale), 0,
				(int) (this.bufferedImage.getWidth(observer) * scale),
				((int) (myStyle.getLeading() * scale)) +
				(int) (this.bufferedImage.getHeight(observer) * scale));
	}

// draw the image
	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
		int width = x + (int) (myStyle.getIndent() * scale);
		int height = y + (int) (myStyle.getLeading() * scale);
		g.drawImage(this.bufferedImage, width, height,(int) (this.bufferedImage.getWidth(observer)*scale),
                (int) (this.bufferedImage.getHeight(observer)*scale), observer);
	}

	public String toString() {
		return "BitmapItem[" + getLevel() + "," + this.imageName + "]";
	}
}
