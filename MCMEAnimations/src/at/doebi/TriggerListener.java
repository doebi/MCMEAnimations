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
			MCMEAnimations.LondDaer.check(c);
			MCMEAnimations.BreeWindmill1.check(c);
			MCMEAnimations.BreeWindmill2.check(c);
			MCMEAnimations.FornostMain.check(c);
			MCMEAnimations.FornostWing11l.check(c);
			MCMEAnimations.FornostWing11r.check(c);
			MCMEAnimations.FornostWing12l.check(c);
			MCMEAnimations.FornostWing12r.check(c);
			MCMEAnimations.FornostWing21l.check(c);
			MCMEAnimations.FornostWing21r.check(c);
			MCMEAnimations.FornostWing22l.check(c);
			MCMEAnimations.FornostWing22r.check(c);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerChat(final PlayerChatEvent event) {
		if(event.getMessage().toLowerCase().indexOf("mellon") != -1){
			Location c = event.getPlayer().getLocation();
			if(MCMEAnimations.Moria.checkchat(c) == true){
				event.setCancelled(true);
			}
		}
		if(event.getMessage().toLowerCase().indexOf("curumo") != -1){
			Location c = event.getPlayer().getLocation();
			if(MCMEAnimations.Orthanc.checkchat(c) == true){
				event.setCancelled(true);
			}
		}
	}
}