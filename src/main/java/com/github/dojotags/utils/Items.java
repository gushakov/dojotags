package com.github.dojotags.utils;

import java.io.Serializable;
import java.util.Map.Entry;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableMap;

@JsonSerialize(using=ItemsSerializer.class)
public class Items implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ImmutableList<ImmutableMap<?, ?>> list;
	
	public ImmutableList<ImmutableMap<?, ?>> getList() {
		return list;
	}
	
	public Items(ImmutableMap<?, ?> map) {
		Builder<ImmutableMap<?, ?>> builder = ImmutableList.builder();
		for (Entry<?, ?> entry : map.entrySet()) {
			builder.add(ImmutableMap.of("id", entry.getKey(), "name",
					entry.getValue()));
		}
		this.list = builder.build();
	}
	
}
