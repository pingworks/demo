package de.pingworks.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    Environment env;
    
    @GetMapping("/env")
	public List<Map<String, Object>> getAllEnvVars() {
		return Arrays.asList(getAllKnownProperties(env));
	}
	
	public static Map<String, Object> getAllKnownProperties(Environment env) {

		String[] ignoredKeys = {
				  "Apple_PubSub_Socket_Render",
				  "XPC_FLAGS",
				  "XPC_SERVICE_NAME",
				  "__CF_USER_TEXT_ENCODING",
				  "awt.toolkit",
				  "catalina.base",
				  "catalina.home",
				  "catalina.useNaming",
				  "com.sun.management.jmxremote",
				  "com.sun.management.jmxremote.authenticate",
				  "com.sun.management.jmxremote.port",
				  "com.sun.management.jmxremote.ssl",
				  "file.encoding",
				  "file.encoding.pkg",
				  "file.separator",
				  "ftp.nonProxyHosts",
				  "gopherProxySet",
				  "http.nonProxyHosts",
				  "java.awt.graphicsenv",
				  "java.awt.headless",
				  "java.awt.printerjob",
				  "java.class.path",
				  "java.class.version",
				  "java.endorsed.dirs",
				  "java.ext.dirs",
				  "java.home",
				  "java.io.tmpdir",
				  "java.library.path",
				  "java.rmi.server.hostname",
				  "java.rmi.server.randomIDs",
				  "java.runtime.name",
				  "java.runtime.version",
				  "java.security.egd",
				  "java.specification.name",
				  "java.specification.vendor",
				  "java.specification.version",
				  "java.vendor",
				  "java.vendor.url",
				  "java.vendor.url.bug",
				  "java.vm.info",
				  "java.vm.name",
				  "java.vm.specification.name",
				  "java.vm.specification.vendor",
				  "java.vm.specification.version",
				  "java.vm.vendor",
				  "java.vm.version",
				  "os.arch",
				  "os.name",
				  "os.version",
				  "socksNonProxyHosts",
				  "spring.application.admin.enabled",
				  "spring.beaninfo.ignore",
				  "spring.liveBeansView.mbeanDomain",
				  "spring.output.ansi.enabled",
				  "sun.arch.data.model",
				  "sun.boot.class.path",
				  "sun.cpu.endian",
				  "sun.cpu.isalist",
				  "sun.io.unicode.encoding",
				  "sun.java.command",
				  "sun.java.launcher",
				  "sun.jnu.encoding",
				  "sun.management.compiler",
				  "sun.os.patch.level",
				  "COMMAND_MODE",
				  "DISPLAY",
				  "JAVA_MAIN_CLASS_4348",
				  "JAVA_STARTED_ON_FIRST_THREAD_2181",
				  "LOGNAME",
				  "SSH_AUTH_SOCK",
				  "line.separator",
				  "path.separator",
				  "user.country",
				  "user.dir",
				  "user.home",
				  "user.language",
				  "user.name",
				  "user.timezone"
			};
		List<String> ignoredKeysList = Arrays.asList(ignoredKeys);
		
		Map<String, Object> rtn = new HashMap<>();
	    if (env instanceof ConfigurableEnvironment) {
	        for (PropertySource<?> propertySource : ((ConfigurableEnvironment) env).getPropertySources()) {
	            if (propertySource instanceof EnumerablePropertySource) {
	                for (String key : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
	                	if (!ignoredKeysList.contains(key)) {
	                		rtn.put(key, propertySource.getProperty(key));
	                	}
	                }
	            }
	        }
	    }
	    Map<String, Object> treeMap = new TreeMap<String, Object>(rtn);
	    return treeMap;
	}
}
