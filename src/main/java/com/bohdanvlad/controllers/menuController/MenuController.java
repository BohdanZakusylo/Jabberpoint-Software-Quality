package com.bohdanvlad.controllers.menuController;

import com.bohdanvlad.presentationComponents.AboutBox;
import com.bohdanvlad.presentationComponents.Presentation;
import com.bohdanvlad.controllers.Command;
import com.bohdanvlad.controllers.keyController.keyCommands.ExitCommand;
import com.bohdanvlad.controllers.keyController.keyCommands.NextSlideCommand;
import com.bohdanvlad.controllers.keyController.keyCommands.PrevSlideCommand;
import com.bohdanvlad.controllers.menuController.menuCommands.GoToCommand;
import com.bohdanvlad.controllers.menuController.menuCommands.LoadCommand;
import com.bohdanvlad.controllers.menuController.menuCommands.NewCommand;
import com.bohdanvlad.controllers.menuController.menuCommands.SaveCommand;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.JOptionPane;

/** <p>The controller for the menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar
{
	private HashMap<String, Command> commands;
	private Frame parent; // the frame, only used as parent for the Dialogs

	private static final long serialVersionUID = 227L;

	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PAGENR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";

	protected static final String TESTFILE = "test.xml";
	protected static final String SAVEFILE = "dump.xml";

	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	public MenuController(Frame frame, Presentation pres)
	{
		this.parent = frame;
		this.commands = new HashMap<>();
		this.commands.put(OPEN, new LoadCommand(pres));
		this.commands.put(SAVE, new SaveCommand(pres));
		this.commands.put(EXIT, new ExitCommand(pres));
		this.commands.put(NEXT, new NextSlideCommand(pres));
		this.commands.put(PREV, new PrevSlideCommand(pres));
		this.commands.put(GOTO, new GoToCommand(pres));
		this.commands.put(NEW, new NewCommand(pres));

		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				executeCommand(OPEN, TESTFILE);
				parent.repaint();
			}
		} );
		fileMenu.add(menuItem = mkMenuItem(NEW));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				executeCommand(NEW, null);
				parent.repaint();
			}
		});
		fileMenu.add(menuItem = mkMenuItem(SAVE));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				executeCommand(SAVE, SAVEFILE);
			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				executeCommand(EXIT, null);
			}
		});
		add(fileMenu);
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(menuItem = mkMenuItem(NEXT));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				executeCommand(NEXT, null);
			}
		});
		viewMenu.add(menuItem = mkMenuItem(PREV));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				executeCommand(PREV, null);
			}
		});
		viewMenu.add(menuItem = mkMenuItem(GOTO));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				String pageNumberStr = JOptionPane.showInputDialog((Object)PAGENR);
				executeCommand(GOTO, pageNumberStr);
			}
		});
		add(viewMenu);
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				AboutBox.show(parent);
			}
		});
		setHelpMenu(helpMenu);		// needed for portability (Motif, etc.).
	}

// create a menu item
	public MenuItem mkMenuItem(String name)
	{
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}

	public HashMap<String, Command> getCommands()
	{
		return commands;
	}

	public void setCommands(HashMap<String, Command> commands)
	{
		this.commands = commands;
	}

	@Override
	public Frame getParent()
	{
		return parent;
	}

	public void setParent(Frame parent)
	{
		this.parent = parent;
	}

	public void executeCommand(String command, String filename)
	{
		if (!this.commands.containsKey(command)){return;}
		this.commands.get(command).execute(filename);
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
