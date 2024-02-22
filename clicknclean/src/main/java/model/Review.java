package model;

public class Review {
	int id_review;
	String content;
	String grade;
	int id_user;
	int id_mission;

	public Review(int id_review, String content, String grade, int id_user, int id_mission) {
		super();
		this.id_review = id_review;
		this.content = content;
		this.grade = grade;
		this.id_user = id_user;
		this.id_mission = id_mission;
	}

	public int getId_review() {
		return id_review;
	}

	public void setId_review(int id_review) {
		this.id_review = id_review;
	}

	public String getContent() {
		return content;
	}

	public String getGrade() {
		return grade;
	}

	public int getId_user() {
		return id_user;
	}

	public int getId_mission() {
		return id_mission;
	}
}
