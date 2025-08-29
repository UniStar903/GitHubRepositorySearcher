package com.freight.fox.dto;

import lombok.Data;

@Data
public class Request {
	private String query;
	private String language;
	private String sort;
}
