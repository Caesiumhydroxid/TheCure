package com.jonas.thecuring.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.jonas.thecuring.TheCuring;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(320*4, 180*4);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new TheCuring();
        }
}