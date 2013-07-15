package rd.util.widget.plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Vector;

import rd.util.GeneralUtil;
import rd.util.StringUtil;
import rd.util.config.RDProperties;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Class that caches all plugin information and makes them available for the
 * application to load when required.
 * 
 * @author ruben
 */
public class PluginFactory {

	private static HashMap<String, Plugin> pluginsPerWidget = new HashMap<String, Plugin>();
	private static HashMap<String, Vector<Plugin>> pluginsPerTrigger = new HashMap<String, Vector<Plugin>>();

//	private static Vector<Plugin> plugins = new Vector<Plugin>();
	static {
		// plugins are not mandatory so neither is the config ...
		String tmp = RDProperties.getString(RDProperties.PLUGIN_DIR);
		if (!StringUtil.isNull(tmp)) {
			File pluginDir = new File(tmp);
			if (pluginDir.exists()) {
				Gson gson = GeneralUtil.getGSON();
				File[] files = pluginDir.listFiles();
				for (int i = 0; i < files.length; i++) {
					File cur = files[i];
					if (cur.isDirectory()) {
						File pluginFile = new File(cur.getAbsolutePath()+"/plugin.json");
						if(pluginFile.exists()){
							try {
								String s = new String(Files.readAllBytes(Paths.get(pluginFile.toURI())));
								Plugin p = gson.fromJson(s, Plugin.class);
								pluginsPerWidget.put(p.getName(), p);
								TriggerConfig[] triggers = p.getTriggers();
								for (int j = 0; j < triggers.length; j++) {
									String key = triggers[i].key();
									Vector<Plugin> v = pluginsPerTrigger.get(key);
									if(v==null){
										v = new Vector<Plugin>();
										pluginsPerTrigger.put(key, v);	
									}
									v.add(p);
								}
							} catch (JsonSyntaxException e) {
								System.out.println("The plugin file is not in the correct json format : " + pluginFile.getAbsolutePath());
							} catch (IOException e) {
								System.out.println("The plugin file cannot be read, make sure it is present : " + pluginFile.getAbsolutePath());
							}
						}
					}
				}
			} else {
				System.out
						.println("Plugins are not configured because plugin directory is not configured correct: "
								+ pluginDir.getAbsolutePath());
			}
		}
	}

	public static Plugin getPluginForWidget(String name) {
		return PluginFactory.pluginsPerWidget.get(name);
	}

	public static Vector<Plugin> getPluginForTrigger(String trigger) {
		return PluginFactory.pluginsPerTrigger.get(trigger);
	}
}
