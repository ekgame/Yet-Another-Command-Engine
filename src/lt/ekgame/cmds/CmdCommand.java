package lt.ekgame.cmds;

public interface CmdCommand<C extends CmdContext, R extends Exception> extends CmdHandler<C, R> {
	
	public String getUsage();
	
}