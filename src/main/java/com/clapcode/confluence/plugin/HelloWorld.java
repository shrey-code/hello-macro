package com.clapcode.confluence.plugin.macro;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.setup.SetupWebResourceManager;
import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.sal.api.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Map;



public class HelloWorld implements Macro {

    @ComponentImport
    private SettingsManager settingsManager;

    @ComponentImport
    private ApplicationProperties applicationProperties;

    @ComponentImport
    private WebResourceManager webResourceManager;



    @Autowired
    public HelloWorld(SettingsManager settingsManager, ApplicationProperties applicationProperties,
                      SetupWebResourceManager webResourceManager, WebResourceManager webResourceManager1) {

        this.settingsManager = settingsManager;
        this.applicationProperties = applicationProperties;
//        this.webResourceManager = SetupWebResourceManager;
        this.webResourceManager = webResourceManager1;
    }

    @Override
    public String execute(Map<String, String> map, String s, ConversionContext conversionContext) throws MacroExecutionException {

           webResourceManager("com.clapcode.confluence.plugin.hello-macro:hello-macro-resources");

            Map contextMap = MacroUtils.defaultVelocityContext();

            contextMap.put("name", map.get("name")!=null?map.get("name"):"Shall I call you MR India");
            contextMap.put("color", map.get("color")!=null?map.get("color"):"Grey");

            String result = VelocityUtils.getRenderedTemplate("templates/macro-body.vm", contextMap);
           return result;


        }

    private void webResourceManager(String s) {
    }

    public BodyType getBodyType () {
            return BodyType.NONE;
        }

        public OutputType getOutputType () {
            return OutputType.BLOCK;
        }
    }