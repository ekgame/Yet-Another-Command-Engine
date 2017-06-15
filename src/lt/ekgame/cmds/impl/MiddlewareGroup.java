package lt.ekgame.cmds.impl;

import java.util.Arrays;
import java.util.List;

import lt.ekgame.cmds.CmdContext;
import lt.ekgame.cmds.CmdMiddleware;
import lt.ekgame.cmds.arguments.ArgumentException;

public class MiddlewareGroup<C extends CmdContext, R extends Exception> implements CmdMiddleware<C, R> {
	
	private List<CmdMiddleware<C, R>> middlewares;
	
	@SuppressWarnings("unchecked")
	public MiddlewareGroup(CmdMiddleware<C, R>... middlewares) {
		this.middlewares = Arrays.asList(middlewares);
	}

	@Override
	public R handle(C context) throws ArgumentException, R {
		for (CmdMiddleware<C, R> middleware : middlewares) {
			R result = middleware.handle(context);
			if (result != null) return result;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <C extends CmdContext, R extends Exception> CmdMiddleware<C, R> of(CmdMiddleware<C, R>... middlewares) {
		return new MiddlewareGroup<C, R>(middlewares);
	}
}
