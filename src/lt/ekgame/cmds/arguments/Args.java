package lt.ekgame.cmds.arguments;

import java.util.Arrays;
import java.util.List;

import lt.ekgame.cmds.CmdArgument;
import lt.ekgame.cmds.arguments.flags.ArgFlag;
import lt.ekgame.cmds.arguments.flags.ArgFlagValue;
import lt.ekgame.cmds.arguments.named.ArgMultiple;
import lt.ekgame.cmds.arguments.named.ArgOne;
import lt.ekgame.cmds.arguments.named.ArgOptional;

public class Args {
	
	/*
	 * Utils.
	 */
	
	@SafeVarargs
	public static <T> List<T> $(T... items) {
		return Arrays.asList(items);
	}
	
	/*
	 * Type arguments.
	 */

	public static ArgToken token(String usage) {
		return new ArgToken(usage);
	}
	
	public static ArgString string(String usage) {
		return new ArgString(usage);
	}
	
	public static ArgRemainingString remainingString(String usage) {
		return new ArgRemainingString(usage);
	}
	
	public static ArgDouble number(String usage) {
		return new ArgDouble(usage);
	}
	
	public static ArgInteger integer(String usage) {
		return new ArgInteger(usage);
	}
	
	public static ArgBoolean bool() {
		return new ArgBoolean();
	}
	
	public static <T> ArgEnum<T> enums() {
		return new ArgEnum<>();
	}
	
	/*
	 * Named arguments.
	 */
	
	public static ArgOne one(String name, CmdArgument argument) {
		return new ArgOne(name, argument);
	}
	
	public static ArgOptional optional(String name, CmdArgument argument) {
		return new ArgOptional(name, argument);
	}
	
	public static ArgMultiple multiple(String name, CmdArgument argument) {
		return new ArgMultiple(name, false, argument);
	}
	
	public static ArgMultiple multiple(String name, boolean requireOne, CmdArgument argument) {
		return new ArgMultiple(name, requireOne, argument);
	}
	
	/*
	 * Flags arguments.
	 */
	
	public static ArgFlag flag(String name) {
		return new ArgFlag($(name));
	}
	
	public static ArgFlag flag(List<String> names) {
		return new ArgFlag(names);
	}
	
	public static ArgFlag flag(String name, CmdArgument argument) {
		return new ArgFlagValue($(name), argument);
	}
	
	public static ArgFlag flag(List<String> names, CmdArgument argument) {
		return new ArgFlagValue(names, argument);
	}
}
