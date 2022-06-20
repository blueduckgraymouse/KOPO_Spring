package kr.ac.ctc.kopo35.Domain;

public class ScoreItem {
	// 내부적 id 추가. - autoincrease로 설정
	private String name;
	private int studentId;		// -> studentId로 변경
	private int kor;
	private int eng;
	private int mat;
	
	public ScoreItem() { }
	
	public ScoreItem(String name, int studentId, int kor, int eng, int mat) {
		super();
		this.name = name;
		this.studentId = studentId;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	@Override
	public String toString() {
		return "ScoreItem [name=" + name + ", studentId=" + studentId + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + "]";
	}
	
	
}
