package com.jonas.thecuring;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;

public class Assets implements AssetErrorListener {
	private static Assets instance;
	private AssetManager manager;
	private HashMap<String,String> map;
	private Assets()
	{
	}
	
	public  static Assets getInstance()
	{
		if(instance==null)
			instance = new Assets();
		return instance;
	}
	public void init(AssetManager manager)
	{
		map = new HashMap<String, String>();
		manager.setErrorListener(this);
		this.manager = manager;
	}
	public <T> void load(String resourceName,String path,java.lang.Class<T> type)
	{
		map.put(resourceName, path);
		manager.load(path, type);
	}
	public Object get(String resourceName) throws RuntimeException
	{
		String path;
		path = map.get(resourceName);

		if(path==null)
			throw new RuntimeException("ResourceName:" + resourceName + " nicht vorhanden");
		return manager.get(path);
	}
	
	public Object getFile(String path)
	{
		return manager.get(path);
	}
	public void dispose()
	{
		manager.dispose();
	}
	public void finishLoading()
	{
		manager.finishLoading();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.log("Asset Manager","Error");
		
	}
	
}
