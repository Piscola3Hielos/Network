package cl.bgmp.commons.Modules;

import cl.bgmp.commons.Commons;
import cl.bgmp.commons.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

// FIXME: Module is quite ambiguous. Needs rework
public class WeatherModule extends Module {

  public WeatherModule() {
    super(ModuleId.WEATHER, Config.Weather.isDisabled());
  }

  @EventHandler
  public void onWeatherChange(WeatherChangeEvent event) {
    final boolean toRain = event.toWeatherState();
    if (toRain) event.setCancelled(true);
  }

  @Override
  public void load() {
    if (enabled) Commons.get().registerEvents(this);
  }

  @Override
  public void unload() {}
}
