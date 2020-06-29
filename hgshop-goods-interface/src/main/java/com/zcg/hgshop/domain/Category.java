package com.zcg.hgshop.domain;

import java.io.Serializable;

/**
 * 分类实体
 * @author hp
 *
 */
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int parentId;
	@JsonProperty("text")
	private String name;
	private String path;
	@JsonProperty("nodes")
	private List<Category> children;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", parentId=" + parentId + ", name=" + name + ", path=" + path + ", children="
				+ children + "]";
	}
	
	
	
}
