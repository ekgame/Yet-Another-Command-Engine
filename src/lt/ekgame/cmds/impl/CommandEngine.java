package lt.ekgame.cmds.impl;

import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdEngine;
import lt.ekgame.cmds.CmdHandler;

public abstract class CommandEngine<C extends CmdContext, R extends Exception> extends CommandBranch<C, R> implements CmdEngine<C, R> {

	public CommandEngine(CmdHandler<C, R> defaultHandler) {
		super("", defaultHandler);
	}
}
