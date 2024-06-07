package com.bohdanvlad.controllers.keyController;

import com.bohdanvlad.presentationComponents.Presentation;
import com.bohdanvlad.controllers.Command;
import com.bohdanvlad.controllers.keyController.keyCommands.ExitCommand;
import com.bohdanvlad.controllers.keyController.keyCommands.NextSlideCommand;
import com.bohdanvlad.controllers.keyController.keyCommands.PrevSlideCommand;

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

public class KeyController extends KeyAdapter
{
	private HashMap<Object, Command> commands;

	public KeyController(Presentation p)
	{
		this.commands = new HashMap<>();
		//NextSlideCommand keys
		this.commands.put(KeyEvent.VK_PAGE_DOWN, new NextSlideCommand(p));
		this.commands.put(KeyEvent.VK_DOWN, new NextSlideCommand(p));
		this.commands.put(KeyEvent.VK_ENTER, new NextSlideCommand(p));
		this.commands.put('+', new NextSlideCommand(p));
		//PrevSlideCommand keys
		this.commands.put(KeyEvent.VK_PAGE_UP, new PrevSlideCommand(p));
		this.commands.put(KeyEvent.VK_UP, new PrevSlideCommand(p));
		this.commands.put('-', new PrevSlideCommand(p));
		//ExitCommand keys
		this.commands.put(KeyEvent.VK_Q, new ExitCommand(p));
	}

	public HashMap<Object, Command> getCommands()
	{
		return commands;
	}

	public void setCommands(HashMap<Object, Command> commands)
	{
		this.commands = commands;
	}

	public void keyPressed(KeyEvent keyEvent)
	{
		executeCommand(keyEvent.getKeyCode());
	}

	public void executeCommand(Object command)
	{
		if (!this.commands.containsKey(command)){return;}
		this.commands.get(command).execute(null);
	}

	public void addCommand(Object obj, Command command)
	{
		Objects.requireNonNull(command);
		this.commands.put(obj, command);
	}

	public void removeCommand(Object obj)
	{
		if (this.commands.containsKey(obj))
		{
			this.commands.remove(obj);
		}
	}
}
