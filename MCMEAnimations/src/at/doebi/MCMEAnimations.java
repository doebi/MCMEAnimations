package at.doebi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MCMEAnimations extends JavaPlugin {
	
	String[] Animators = {"HelmsDeep", "HelmsDeepPalast", "Edoras", "Bridge", "BreeWestSouth", "BreeNorth", "Moria", "Isengard", "GateBridgeVillage1", "GateBridgeVillage2", "Bagend", "Meduseld"};
	static Animator HelmsDeep = null;
	static Animator HelmsDeepPalast = null;
	static Animator Edoras = null;
	static Animator Bridge = null;
	static Animator BreeWest = null;
	static Animator BreeSouth = null;
	static Animator BreeNorth = null;
	static Animator Moria = null;
	static Animator Isengard1 = null;
	static Animator Isengard2 = null;
	static Animator GateBridgeVillage1 = null;
	static Animator GateBridgeVillage2 = null;
	static Animator Bagend1 = null;
	static Animator Bagend2 = null;
	static Animator Bagshot1 = null;
	static Animator Bagshot2 = null;
	static Animator Bagshot3 = null;
	static Animator Meduseld = null;

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new TriggerListener(this), this);
		// define Animator for each Animation
		HelmsDeep = new Animator(this, 0, 6, 10, 100, 13037, 36, -9978, 4, 9, 9, 1, 0, 0, 0);
		HelmsDeepPalast = new Animator(this, 1, 5, 10, 50, 13105, 56, -9976, 3, 6, 5, 1, 0, 0, 0);
		Edoras = new Animator(this, 2, 7, 10, 50, 13093, 29, -12214, 4, 6, 7, 1, 0, 0, 0);
		Bridge = new Animator(this, 3, 11, 20, 500, 13729, 18, -16174, 10, 8, 16, 2, 13731, 23, -16147);
		BreeWest = new Animator(this, 4, 4, 10, 50, 178, 68, -873, 3, 7, 1, 1, 0, 0, 0);
		BreeSouth = new Animator(this, 4, 4, 10, 50, 513, 68, -1351, 3, 7, 1, 1, 0, 0, 0);
		BreeNorth = new Animator(this, 5, 4, 10, 50, 23, 68, -989, 1, 7, 3, 1, 0, 0, 0);
		Moria = new Animator(this, 6, 8, 10, 50, 4282, 87, -6961, 4, 6, 3, 3, 20, 0, 0);
		Isengard1 = new Animator(this, 7, 5, 10, 50, 9824, 11, -9058, 1, 13, 17, 1, 0, 0, 0);
		Isengard2 = new Animator(this, 7, 5, 10, 50, 9801, 11, -9058, 1, 13, 17, 1, 0, 0, 0);
		GateBridgeVillage1 = new Animator(this, 8, 4, 10, 50, 13693, 18, -16181, 1, 8, 4, 1, 0, 0, 0);
		GateBridgeVillage2 = new Animator(this, 9, 4, 10, 50, 13716, 18, -16225, 5, 9, 1, 1, 0, 0, 0);
		Bagend1 = new Animator(this, 10, 3, 10, 50, -175, 86, 1827, 3, 2, 4, 1, 0, 0, 0);
		Bagend2 = new Animator(this, 10, 3, 10, 50, -175, 86, 1879, 3, 2, 4, 1, 0, 0, 0);
		Bagshot1 = new Animator(this, 10, 3, 10, 50, -152, 78, 1838, 3, 2, 4, 1, 0, 0, 0);
		Bagshot2 = new Animator(this, 10, 3, 10, 50, -155, 77, 1864, 3, 2, 4, 1, 0, 0, 0);
		Bagshot3 = new Animator(this, 10, 3, 10, 50, -159, 78, 1879, 3, 2, 4, 1, 0, 0, 0);
		Meduseld = new Animator(this, 11, 7, 10, 50, 13267, 80, -12114, 11, 6, 4, 1, 0, 0, 0);
	}
	
	class Animator{
		Plugin plugin;
		int delay = 0;
		int originX;
		int originY;
		int originZ;
		int tx;
		int ty;
		int tz;
		int lenX;
		int lenY;
		int lenZ;
		int tt;	//1 = Knock, 2 = Lever, 3 = Chat
		int i;	//current frame
		int allFrames;	//number of frames
		int name;
		int pausedelay;
		Block tester;
		
		public Animator(MCMEAnimations instance, int fname, int frames, int defaultdelay, int maxdelay, int ox, int oy, int oz, int lx, int ly, int lz, int TriggerType, int TriggerX, int TriggerY, int TriggerZ) {
	        plugin = instance;
	        allFrames = frames;
	        delay = defaultdelay;
	        pausedelay = maxdelay;
	        name = fname;
	        originX = ox;
	        originY = oy;
	        originZ = oz;
	        tx = TriggerX;
	        ty = TriggerY;
	        tz = TriggerZ;
	        lenX = lx;
	        lenY = ly;
	        lenZ = lz;
	        tt = TriggerType;
	        tester = getServer().getWorld("world").getBlockAt(originX, originY-10, originZ);
	        tester.setTypeId(0);
	        render(1);
	    }
		
		public void nextFrame(int i){
			this.render(i);
			if(i < allFrames){
				final int ni = i+1;
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin,new Runnable() {
					@Override
					public void run() {
						nextFrame(ni);
					}
				}, delay);	
			}
			else{
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin,new Runnable() {
					@Override
					public void run() {
						previousFrame(allFrames);
					}
				}, pausedelay);	
			}
		}
		
		private void renderWeights(int i) {
	    	int[] originXs = {13728,13728,13738,13738};
	    	int[] originZs = {-16154,-16181,-16181,-16154};
			String[] Info = null;
			try {
				Info = this.getInfo("weight");
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] Infor = null;
			try {
				Infor = this.getInfo("weight-r");
			} catch (IOException e) {
				e.printStackTrace();
			}
			for(int n = 0; n < 4; n++){
				//Clear Area
		  		for(int ix = 0; ix < 2; ix++){
		  			for(int iy = 0; iy < 15; iy++){
			  			for(int iz = 0; iz < 3; iz++){
			  				Block target = getServer().getWorld("world").getBlockAt(originXs[n] + ix, 12 + iy, originZs[n] + iz);
			  				int Block = 0;
			  				target.setTypeId(Block);
			  			}
		  			}
		  		}
		  		//Draw Rope
	  			for(int iy = 0; iy < 10; iy++){
	  				int tempx = 0;
	  				if(originXs[n] == 13738){
	  					tempx = originXs[n];
	  				}
	  				else if(originXs[n] == 13728){
	  					tempx = originXs[n] + 1;	  					
	  				}
	  				Block target = getServer().getWorld("world").getBlockAt(tempx, 17 + iy, originZs[n] + 1);
	  				int Block = 85;
	  				target.setTypeId(Block);
	  			}
		  		//Draw Weight
		    	int j = 0;
		  		for(int ix = 0; ix < 2; ix++){
		  			for(int iy = 0; iy < 5; iy++){
			  			for(int iz = 0; iz < 3; iz++){
			  				Block target = getServer().getWorld("world").getBlockAt(originXs[n] + ix, (17 - (i/2)) + iy, originZs[n] + iz);
			  				String[] vi = null;
			  				if(originXs[n] == 13738){
			  					vi = Infor[j].split(":");
			  				}
			  				else if(originXs[n] == 13728){
			  					vi = Info[j].split(":");			  					
			  				}
			  				int Block = Integer.parseInt(vi[0]);
			  				byte DataValue = Byte.parseByte(vi[1]);
			  				target.setTypeIdAndData(Block, DataValue, true);
			  				j++;
			  			}
		  			}
		  		}
			}
		}

		public void previousFrame(int i){
			this.render(i);
			if(i > 1){
				final int ni = i-1;
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin,new Runnable() {
					@Override
					public void run() {
						previousFrame(ni);
					}
				}, delay);			
			}
			else{
				tester.setTypeId(0);
			}
		}
		
	    public void render(int i){
	    	String[] Info = null;
		    String ii = "" + i;
			try {
				Info = this.getInfo(ii);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	int j = 0;
	  		for(int ix = 0; ix < lenX; ix++){
	  			for(int iy = 0; iy < lenY; iy++){
		  			for(int iz = 0; iz < lenZ; iz++){
		  				Block target = getServer().getWorld("world").getBlockAt(originX + ix, originY + iy, originZ + iz);
		  				int Block;
		  				byte DataValue = (byte) 0;
		  				String vi [] = Info[j].split(":");
			  			Block = Integer.parseInt(vi[0]);
			  			DataValue = Byte.parseByte(vi[1]);
		  				target.setTypeIdAndData(Block, DataValue, true);
		  				j++;
		  			}
	  			}
	  		}
			//Custom Code for Weights at Bridge
			if(name == 3){
				if(i % 2 == 0){
					this.renderWeights(i);
				}
			}
	    }
	    
	    public String[] getInfo(String i) throws IOException {
	    	StringBuffer fileData = new StringBuffer(1000);
	    	BufferedReader reader = new BufferedReader(new FileReader("plugins//MCMEAnimations//Frames//" + Animators[name] + "//" + i + ".frame"));
	    	char[] buf = new char[1024];
	    	int numRead=0;
	    	while((numRead=reader.read(buf)) != -1){
		    	String readData = String.valueOf(buf, 0, numRead);
		    	fileData.append(readData);
		    	buf = new char[1024];
	    	}
	    	reader.close();
	    	return fileData.toString().split(",");
	    }

		public void trigger(){
			tester.setTypeId(1);
			nextFrame(1);
	    }
	    
	    public boolean check(Location checker){
	    	boolean ping = false;
			if(tester.getTypeId() == 0){
		    	if(tt == 1){
					if(	checker.getBlockX() >= originX && checker.getBlockX() < (originX + lenX) && checker.getBlockY() >= originY && checker.getBlockY() < (originY + lenY) && checker.getBlockZ() >= originZ && checker.getBlockZ() < (originZ + lenZ)){
						this.trigger();
						ping = true;
					}
		    	}
		    	else if(tt == 2){
		    		if(checker.getBlock().getType().toString() == "LEVER" && checker.getBlockX() == tx && checker.getBlockY() == ty && checker.getBlockZ() == tz){
		    			this.trigger();
		    			ping = true;
		    		}
		    	}
		    	else if(tt == 3){
		        	if(originX - tx < checker.getX() && checker.getX() < originX + tx && originZ - tx < checker.getZ() && checker.getZ() < originZ + tx){
			    		this.trigger();
			    		ping = true;
		        	}
		    	}
			}
			return ping;
	    }
	}
}