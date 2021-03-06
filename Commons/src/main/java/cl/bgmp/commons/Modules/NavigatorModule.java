package cl.bgmp.commons.Modules;

import cl.bgmp.commons.Commons;
import cl.bgmp.commons.Config;
import cl.bgmp.commons.Navigator.Navigator;
import cl.bgmp.commons.Navigator.NavigatorGUI;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;

public class NavigatorModule extends Module {
  private Navigator navigator = new Navigator();

  public NavigatorModule() {
    super(ModuleId.NAVIGATOR, Config.Navigator.isEnabled());
  }

  public Navigator getNavigator() {
    return navigator;
  }

  /**
   * Compares the given item to the navigator's item and checks if they are equal
   *
   * @param itemStack The item to compare to navigator's item
   * @return Whether or not the two items are equal
   */
  @SuppressWarnings("ConstantConditions")
  public boolean itemIsNavigator(final ItemStack itemStack) {
    final ItemStack navigatorItem = navigator.getItem();

    if (navigatorItem.getItemMeta() instanceof SkullMeta) {
      if (!itemStack.hasItemMeta() || !itemStack.getItemMeta().hasLore()) return false;

      return itemStack.getLore().equals(navigatorItem.getLore());
    } else return itemStack.equals(navigator.getItem());
  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    final ItemStack itemInHand = event.getItem();
    if (itemInHand == null || itemInHand.getType() == Material.AIR) return;
    if (itemIsNavigator(itemInHand))
      event.getPlayer().openInventory(new NavigatorGUI().getInventory());
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onBlockPlace(BlockPlaceEvent event) {
    final PlayerInventory inventory = event.getPlayer().getInventory();
    if (!itemIsNavigator(inventory.getItemInMainHand())
        && !itemIsNavigator(inventory.getItemInOffHand())) return;
    event.setCancelled(true);
  }

  @Override
  public void load() {
    if (enabled) Commons.get().registerEvents(this);
  }

  @Override
  public void unload() {}
}
