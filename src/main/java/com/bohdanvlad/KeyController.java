package com.bohdanvlad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.HashMap;
import java.util.Objects;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class KeyController extends KeyAdapter implements Command
{
	private Presentation presentation; // Commands are given to the presentation
	private HashMap<Object, Command> commands;

	public KeyController(Presentation p)
	{
		this.presentation = p;
		this.commands = new HashMap<>();
	}

	public void keyPressed(KeyEvent keyEvent) //TODO: add multiple keys(keyevents as objects) to one command
	{
		execute(keyEvent.getKeyCode(), this.presentation);
//		switch(keyEvent.getKeyCode())
//		{
//			case KeyEvent.VK_PAGE_DOWN:
//			case KeyEvent.VK_DOWN:
//			case KeyEvent.VK_ENTER:
//			case '+':
//				presentation.nextSlide();
//				break;
//			case KeyEvent.VK_PAGE_UP:
//			case KeyEvent.VK_UP:
//			case '-':
//				presentation.prevSlide();
//				break;
//			case 'q':
//			case 'Q':
//				System.exit(0);
//				break; // Probably never reached!!
//			default:
//				break;
//		}
	}

	@Override
	public void execute(Object command, Object pres)
	{
		if (!this.commands.containsKey(command)){return;}
		this.commands.get(command).execute(null, pres);
	}

	public void addCommand(String name, Command command)
	{
		Objects.requireNonNull(command);
		this.commands.put(name, command);
	}

	public void removeCommand(String name)
	{
		if (this.commands.containsKey(name))
		{
			this.commands.remove(name);
		}
	}
}
