package at.doebi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MCMEAnimations extends JavaPlugin {
	
	String[] Animators = {"HelmsDeep", "HelmsDeepPalast", "Edoras", "Bridge"};
	static Animator HelmsDeep = null;
	static Animator HelmsDeepPalast = null;
	static Animator Edoras = null;
	static Animator Bridge = null;

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new TriggerListener(this), this);
		// define Animator for each Animation
		HelmsDeep = new Animator(this, 0, 6, 10, 100, 13037, 36, -9978, 4, 9, 9, 1);
		HelmsDeepPalast = new Animator(this, 1, 5, 100, 50, 13105, 56, -9976, 3, 6, 5, 1);
		Edoras = new Animator(this, 2, 7, 10, 100, 13093, 29, -12214, 4, 6, 7, 1);
		Bridge = new Animator(this, 3, 11, 50, 500, 13729, 18, -16174, 10, 8, 16, 1);
	}
	
	class Animator{
		Plugin plugin;
		int delay = 0;
		int originX;
		int originY;
		int originZ;
		int lenX;
		int lenY;
		int lenZ;
		int tt;	//1 = Knock, 2 = Lever
		int i;	//current frame
		int allFrames;	//number of frames
		int name;
		int pausedelay;
		Block tester;
		
		public Animator(MCMEAnimations instance, int fname, int frames, int defaultdelay, int maxdelay, int ox, int oy, int oz, int lx, int ly, int lz, int TriggerType) {
	        plugin = instance;
	        allFrames = frames;
	        delay = defaultdelay;
	        pausedelay = maxdelay;
	        name = fname;
	        originX = ox;
	        originY = oy;
	        originZ = oz;
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
			try {
				Info = this.getInfo(i);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	int j = 0;
	  		for(int ix = 0; ix < lenX; ix++){
	  			for(int iy = 0; iy < lenY; iy++){
		  			for(int iz = 0; iz < lenZ; iz++){
		  				Block target = getServer().getWorld("world").getBlockAt(originX + ix, originY + iy, originZ + iz);
		  				String vi [] = Info[j].split(":");
		  				int Block = Integer.parseInt(vi[0]);
		  				byte DataValue = Byte.parseByte(vi[1]);
		  				target.setTypeIdAndData(Block, DataValue, true);
		  				j++;
		  			}
	  			}
	  		}
	    }
	    
	    public String[] getInfo(int i) throws IOException {
	    	StringBuffer fileData = new StringBuffer(1000);
	    	BufferedReader reader = new BufferedReader(new FileReader("plugins//frames//" + Animators[name] + "//" + i + ".frame"));
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
	    
	    public void check(Location checker){
	    	if(tt == 1){
				if(tester.getTypeId() == 0){
					if(	checker.getBlockX() >= originX && checker.getBlockX() < (originX + lenX) && checker.getBlockY() >= originY && checker.getBlockY() < (originY + lenY) && checker.getBlockZ() >= originZ && checker.getBlockZ() < (originZ + lenZ)){
						this.trigger();
					}
				}
	    	}
	    	else if(tt == 2){
	    		if(checker.getBlock().getType().toString() == "LEVER"){
	    			this.trigger();
	    		}
	    	}
	    }
	}
}