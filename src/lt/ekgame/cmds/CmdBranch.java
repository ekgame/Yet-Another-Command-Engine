package lt.ekgame.cmds;

import java.util.List;
import java.util.function.Consumer;

public interface CmdBranch<C extends CmdContext, R extends Throwable> {
	
	void command(String label, CmdHandler<C, R> defaultHandler, Consumer<CmdNode<C, R>> consumer);
	
	void command(String label, CmdHandler<C, R> handler);
	
	void command(List<String> labels, CmdHandler<C, R> defaultHandler, Consumer<CmdNode<C, R>> consumer);
	
	void command(List<String> labels, CmdHandler<C, R> handler);
	
	CmdHandler<C, R> getCommand(String label);
	
	CmdHandler<C, R> getHandler(C context);
	
	String getName();

}
