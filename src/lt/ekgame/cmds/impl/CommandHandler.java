package lt.ekgame.cmds.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdHandler;
import lt.ekgame.cmds.CmdMiddleware;
import lt.ekgame.cmds.arguments.ArgumentException;
import lt.ekgame.cmds.arguments.flags.ArgFlag;
import lt.ekgame.cmds.arguments.named.NamedArgument;

public abstract class CommandHandler<Context extends CmdContext, Result extends Exception> implements CmdHandler<Context, Result> {
	
	private List<CmdMiddleware<Context, Result>> middlewares = getMiddleware();
	private List<NamedArgument> arguments = getArguments();
	private List<ArgFlag> flags = getFlags();
	
	protected abstract List<CmdMiddleware<Context, Result>> getMiddleware();
	protected abstract List<NamedArgument> getArguments();
	protected abstract List<ArgFlag> getFlags();

	@Override
	public Result handle(Context context) throws ArgumentException, Result {
		if (middlewares != null) {
			for (CmdMiddleware<Context, Result> middleware : middlewares) {
				Result result = middleware.handle(context);
				if (result != null) return result;
			}
		}

		if (arguments != null) {
			for (NamedArgument argument : arguments) {
				Object value = argument.parse(context, context.getTokenizer());
				context.set(argument.getName(), value);
			}
		}
		
		if (flags == null) 
			flags = Collections.emptyList();
		context.handleFlags(flags);
		
		throw handleCommand(context);
	}
	
	public String getUsage() {
		return arguments.stream()
			.map(o -> o.getUsage())
			.collect(Collectors.joining(" "));
	}
	
	protected abstract Result handleCommand(Context context) throws Result;
}
