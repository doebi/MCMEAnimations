package at.doebi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MCMEAnimations extends JavaPlugin {
	
	String[] Animators = {"HelmsDeep", "HelmsDeepPalast", "Edoras", "Bridge", "BreeWestSouth", "BreeNorth", "Moria", "Isengard", "GateBridgeVillage1", "GateBridgeVillage2", "Bagend", "Meduseld", "LondDaer", "Orthanc", "BreeWindmill", "FornostMain", "FornostWing1", "FornostWing2"};
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
	static Animator Meduseld = null;
	static Animator LondDaer = null;
	static Animator Orthanc = null;
	static Animator BreeWindmill1 = null;
	static Animator BreeWindmill2 = null;
	/*static Animator FornostMain = null;
	static Animator FornostWing11l = null;
	static Animator FornostWing11r = null;
	static Animator FornostWing12l = null;
	static Animator FornostWing12r = null;
	static Animator FornostWing21l = null;
	static Animator FornostWing21r = null;
	static Animator FornostWing22l = null;
	static Animator FornostWing22r = null;*/
	

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new TriggerListener(this), this);
		// define Animator for each Animation
		HelmsDeep = new Animator(this, 0, 6, 10, 100, 13037, 36, -9978, 4, 9, 9, 1, 0, 0, 0);
		HelmsDeepPalast = new Animator(this, 1, 5, 10, 50, 13105, 56, -9976, 3, 6, 5, 1, 0, 0, 0);
		Edoras = new Animator(this, 2, 7, 10, 50, 13093, 29, -12214, 4, 6, 7, 1, 0, 0, 0);
		Bridge = new Animator(this, 3, 11, 50, 500, 13729, 18, -16174, 10, 8, 16, 2, 13731, 23, -16147);
		BreeWest = new Animator(this, 4, 4, 10, 50, 178, 68, -873, 3, 7, 1, 1, 0, 0, 0);
		BreeSouth = new Animator(this, 4, 4, 10, 50, 513, 68, -1351, 3, 7, 1, 1, 0, 0, 0);
		BreeNorth = new Animator(this, 5, 4, 10, 50, 23, 68, -989, 1, 7, 3, 1, 0, 0, 0);
		Moria = new Animator(this, 6, 6, 20, 100, 4280, 87, -6959, 8, 4, 3, 3, 20, 0, 0);
		Isengard1 = new Animator(this, 7, 5, 10, 50, 9824, 11, -9058, 1, 13, 17, 1, 0, 0, 0);
		Isengard2 = new Animator(this, 7, 5, 10, 50, 9801, 11, -9058, 1, 13, 17, 1, 0, 0, 0);
		GateBridgeVillage1 = new Animator(this, 8, 4, 10, 50, 13693, 18, -16181, 1, 8, 4, 1, 0, 0, 0);
		GateBridgeVillage2 = new Animator(this, 9, 4, 10, 50, 13716, 18, -16225, 5, 9, 1, 1, 0, 0, 0);
		Bagend1 = new Animator(this, 10, 3, 10, 50, -181, 99, 1830, 3, 2, 4, 1, 0, 0, 0);
		Bagend2 = new Animator(this, 10, 3, 10, 50, -187, 99, 1884, 3, 2, 4, 1, 0, 0, 0);
		Meduseld = new Animator(this, 11, 7, 10, 50, 13267, 80, -12114, 11, 6, 4, 1, 0, 0, 0);
		LondDaer = new Animator(this, 12, 5, 10, 50, 8238, 49, 548, 1, 17, 7, 1, 0, 0, 0);
		Orthanc = new Animator(this, 13, 11, 20, 100, 9624, 61, -9034, 5, 4, 5, 3, 10, 0, 0);
		BreeWindmill1 = new Animator(this, 14, 8, 10, 1, 336, 75, -931, 11, 11, 1, 2, 345, 77, -922);
		BreeWindmill2 = new Animator(this, 14, 8, 10, 1, 465, 76, -1065, 11, 11, 1, 2, 474, 78, -1056);
		/*FornostMain = new Animator(this, 15, 6, 10, 50, -4194, 23, -799, 1, 14, 9, 1, 0, 0, 0);
		FornostWing11l = new Animator(this, 16, 4, 10, 50, -4238, 31, -745, 5, 9, 1, 1, 0, 0, 0);
		FornostWing12l = new Animator(this, 16, 4, 10, 50, -4100, 23, -677, 5, 9, 1, 1, 0, 0, 0);
		FornostWing11r = new Animator(this, 16, 4, 10, 50, -4238, 31, -845, 5, 9, 1, 1, 0, 0, 0);
		FornostWing12r = new Animator(this, 16, 4, 10, 50, -4100, 23, -913, 5, 9, 1, 1, 0, 0, 0);
		FornostWing21r = new Animator(this, 17, 5, 10, 50, -4189, 23, -970, 1, 10, 5, 1, 0, 0, 0);
		FornostWing21l = new Animator(this, 17, 5, 10, 50, -4189, 23, -624, 1, 10, 5, 1, 0, 0, 0);
		FornostWing22r = new Animator(this, 17, 5, 10, 50, -4280, 31, -894, 1, 10, 5, 1, 0, 0, 0);
		FornostWing22l = new Animator(this, 17, 5, 10, 50, -4280, 31, -702, 1, 10, 5, 1, 0, 0, 0);*/
	}
	
	class Animator{
		Plugin plugin;
		int delay;
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
		int rc;
		int state = 0;
		
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
				//Repeat if Windmill
				if(pausedelay == 1){
					if(rc < 5){
						rc++;
						nextFrame(1);
					}
					else{
						rc = 0;
						render(1);
						tester.setTypeId(0);
					}
				}
				else{
					//Stop Bridge from closing
					if(name == 3){
						state = 1;
						tester.setTypeId(0);
					}else{
						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin,new Runnable() {
							@Override
							public void run() {
								previousFrame(allFrames);
							}
						}, pausedelay);
					}
				}
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
				state = 0;
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
			//Custom Code for Moria Paintings
			if(name == 6){
				
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
			if(name == 3){
				if(state == 0){
					nextFrame(1);
				}else if(state == 1){
					previousFrame(allFrames);
				}
			}else{
				nextFrame(1);
			}
	    }
	    
	    public void check(Location checker){
		    if(tt == 1){
				if(tester.getTypeId() == 0){
					if(checker.getBlockX() >= originX){
						if(checker.getBlockX() < (originX + lenX)){
							if(checker.getBlockY() >= originY){
								if(checker.getBlockY() < (originY + lenY)){
									if(checker.getBlockZ() >= originZ){
										if(checker.getBlockZ() < (originZ + lenZ)){
											this.trigger();
										}
									}
								}
							}
						}
					}
				}
		    }
		    else if(tt == 2){
		    	if(tester.getTypeId() == 0){
		    		if(checker.getBlock().getType().toString() == "LEVER"){
		    			if(checker.getBlockX() == tx){
		    				if(checker.getBlockY() == ty){
		    					if(checker.getBlockZ() == tz){
		    			    		this.trigger();
			    				}
			    			}
			    		}
			    	}
		    	}
		    }
		}
	    
	    public boolean checkchat(Location checker){
	    	boolean ping = false;
		    if(tester.getTypeId() == 0){
		    	if(originX - tx < checker.getX()){
		    		if(checker.getX() < originX + tx){
		    			if(originZ - tx < checker.getZ()){
		    				if(checker.getZ() < originZ + tx){
		    			    	this.trigger();
		    			    	ping = true;
			    			}
			    		}
			    	}
			    }
		    }
			return ping;
	    }
	}
}