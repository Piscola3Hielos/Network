package cl.bgmp.elmedievo.Commands.TPA;

import cl.bgmp.elmedievo.ElMedievo;
import cl.bgmp.elmedievo.Teleport.TPA;
import cl.bgmp.elmedievo.Translations.ChatConstant;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import java.util.Optional;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TPADenyCommand {

  @Command(
      aliases = {"tpdeny", "tpadeny"},
      desc = "Denies one incoming teleport request.",
      max = 0)
  public static void tpdeny(final CommandContext args, final CommandSender sender) {
    if (sender instanceof ConsoleCommandSender) {
      sender.sendMessage(ChatColor.RED + ChatConstant.NO_CONSOLE.getString());
      return;
    }

    Player player = (Player) sender;
    Optional<TPA> tpa = ElMedievo.get().getTpaManager().getPlayerIncomingTPA(player);

    if (tpa.isPresent()) {
      tpa.get().deny();
      player.sendMessage(
          ChatColor.RED
              + ChatConstant.TPA_DENY
                  .getTranslatedTo(player.getLocale())
                  .replace("{0}", tpa.get().getPlayerTo().getDisplayName() + ChatColor.RED));
    } else
      player.sendMessage(
          ChatColor.RED + ChatConstant.TPA_NO_PENDANT.getTranslatedTo(player.getLocale()));
  }
}
