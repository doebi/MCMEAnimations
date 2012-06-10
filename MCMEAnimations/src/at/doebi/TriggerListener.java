package at.doebi;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TriggerListener implements Listener {

	public TriggerListener(MCMEAnimations instance) {
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(final PlayerInteractEvent event) {
		if(event.getAction().toString() == "LEFT_CLICK_BLOCK"){
			Location c = event.getClickedBlock().getLocation();
			MCMEAnimations.HelmsDeep.check(c);
			MCMEAnimations.HelmsDeepPalast.check(c);
			MCMEAnimations.Edoras.check(c);
			MCMEAnimations.Bridge.check(c);
			MCMEAnimations.BreeWest.check(c);
			MCMEAnimations.BreeSouth.check(c);
			MCMEAnimations.BreeNorth.check(c);
			MCMEAnimations.Isengard1.check(c);
			MCMEAnimations.Isengard2.check(c);
			MCMEAnimations.GateBridgeVillage1.check(c);
			MCMEAnimations.GateBridgeVillage2.check(c);
			MCMEAnimations.Bagend1.check(c);
			MCMEAnimations.Bagend2.check(c);
			MCMEAnimations.Bagshot1.check(c);
			MCMEAnimations.Bagshot2.check(c);
			MCMEAnimations.Bagshot3.check(c);
			MCMEAnimations.Meduseld.check(c);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerChat(final PlayerChatEvent event) {
		if(event.getMessage().toLowerCase().indexOf("mellon") != -1){
			Location c = event.getPlayer().getLocation();
			if(MCMEAnimations.Moria.check(c) == true){
				event.setCancelled(true);
			}
		}
	}
}