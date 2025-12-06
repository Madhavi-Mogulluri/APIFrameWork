package com.api.pojoClasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	private int id;
	private String title;
	private String description;
	private String category;
	private String image;
	private Double price;
	private Rating rating;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Rating{
		private Double rate;
		private int count;
	}
	

}
