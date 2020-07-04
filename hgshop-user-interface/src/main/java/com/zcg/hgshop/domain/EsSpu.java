package com.zcg.hgshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
/**
 * ES中文档对象的实体bean
 * @author hp
 *
 */
@Document(indexName = "hgshop",type = "hg_spu")
public class EsSpu {
	/**
	 * 构造器
	 * @param spu
	 */
	public EsSpu(Spu spu) {
		super();
		// TODO Auto-generated constructor stub
		id=spu.getId();
		goodsName=spu.getGoodsName();
		caption=spu.getCaption();
		smallPic=spu.getSmallPic();
		categoryName=spu.getCategory()==null?"":spu.getCategory().getName();
		brandName=spu.getBrand()==null?"":spu.getBrand().getName();
	}
	
	public EsSpu() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	private int id;
	private String goodsName;
	private String caption;
	private String smallPic;
	private String categoryName;
	private String brandName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getSmallPic() {
		return smallPic;
	}
	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
}
