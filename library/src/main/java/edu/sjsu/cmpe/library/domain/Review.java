package edu.sjsu.cmpe.library.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import edu.sjsu.cmpe.library.dto.LinksDto;


@JsonInclude(Include.NON_NULL)
public class Review extends LinksDto{
	
	private static int reviewKey;
	private int id;
	
	//@NotNull(message ="Review rating is required field.")
	private Rating ratingValue;
	
	//@NotEmpty(message ="Review comments is required field.")
	private String comments;
	
	public Review() {
		this.id = ++reviewKey;
	}
	
	public  enum Rating {
		OneStar, TwoStar, ThreeStar, FourStar, FiveStar
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("rating")
	public Rating getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(Rating ratingValue) {
		this.ratingValue = ratingValue;
	}

	@JsonProperty("comment")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
