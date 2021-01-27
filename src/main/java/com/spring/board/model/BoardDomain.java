package com.spring.board.model;

import org.apache.ibatis.type.Alias;

@Alias("BoardDomain")
public class BoardDomain extends BoardEntity{

	private String nm; // 작성자 이름
	private int is_favorite; // 좋아요 
	private int favorite_cnt;
	private String profile_img; // 프로필 사진
	
	public int getFavorite_cnt() {
		return favorite_cnt;
	}

	public void setFavorite_cnt(int favorite_cnt) {
		this.favorite_cnt = favorite_cnt;
	}

	public int getIs_favorite() {
		return is_favorite;
	}

	public void setIs_favorite(int is_favorite) {
		this.is_favorite = is_favorite;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	
	
}