package model;

import java.time.LocalDateTime;

public class Comment {
	private int id;
	private int author;
	private int targetArticle;
	private String contents;
	private LocalDateTime boardTime;
}
