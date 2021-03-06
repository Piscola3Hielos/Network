package cl.bgmp.elmedievo.Commands;

import cl.bgmp.elmedievo.Config;
import cl.bgmp.elmedievo.Translations.ChatConstant;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand {
  @Command(
      aliases = {"spawn"},
      desc = "Takes you to the server spawn.",
      max = 0)
  public static void spawn(final CommandContext args, final CommandSender sender) {
    if (sender instanceof ConsoleCommandSender) {
      sender.sendMessage(ChatColor.RED + ChatConstant.NO_CONSOLE.getString());
      return;
    }

    final Player player = (Player) sender;
    player.sendMessage(
        ChatColor.GREEN + ChatConstant.SPAWN_TELEPORTED.getTranslatedTo(player.getLocale()));
    player.teleport(Config.Spawn.getLocation());
  }
}
