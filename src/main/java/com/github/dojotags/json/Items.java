package com.github.dojotags.json;

import java.io.Serializable;
import java.util.Map.Entry;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableMap;

/**
 * Models list of tuples of the form <code>{id: 1, name: "toto"}</code> suitable
 * to be used in the Dojo stores. Uses immutable collections from {@code guava}
 * library.
 * 
 * @author gushakov
 * 
 * @see com.google.common.collect.ImmutableMap
 * @see com.google.common.collect.ImmutableList
 * 
 */
@JsonSerialize(using = ItemsSerializer.class)
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
