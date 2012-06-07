package at.doebi;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TriggerListener implements Listener {

	public TriggerListener(MCMEAnimations voxelAnimation) {
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(final PlayerInteractEvent event) {
		if(event.getAction().toString() == "LEFT_CLICK_BLOCK"){
			Location c = event.getClickedBlock().getLocation();
			MCMEAnimations.HelmsDeep.check(c);
			MCMEAnimations.HelmsDeepPalast.check(c);
			MCMEAnimations.Edoras.check(c);
			MCMEAnimations.Bridge.check(c);
		}
	}
}