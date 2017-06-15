package lt.ekgame.cmds.test;

import java.util.List;

import lt.ekgame.cmds.CmdMiddleware;
import lt.ekgame.cmds.arguments.flags.ArgFlag;
import lt.ekgame.cmds.arguments.named.NamedArgument;

import static lt.ekgame.cmds.arguments.Args.*;

public class TestCommandPing extends TestBaseCommand {

	@Override
	protected List<CmdMiddleware<TestContext, TestResult>> getMiddleware() {
		return null;
	}

	@Override
	protected List<NamedArgument> getArguments() {
		return $(
			one("message", string("message"))
		);
	}
	
	@Override
	protected List<ArgFlag> getFlags() {
		return $(
			flag($("f", "foo")),
			flag($("b", "bar"), string(""))
		);
	}

	@Override
	protected TestResult handleCommand(TestContext context) {
		String message = context.<String>get("message");
		boolean foo = context.getFlags().get("foo", false);
		String bar = context.getFlags().get("bar", "default");
		return resultOk("Pong! " + message + ", foo=" + foo + ", bar=" + bar);
	}
}
