package kr.ac.ctc.kopo35.Dto;

import java.util.Arrays;

public class DefultRecords {
	private String[] name = {"나연", "정연", "모모", "사나", "지효", "미나", "다현", "채영", "쯔위"};
	private int[] studentId = {209901, 209902, 209903, 209904, 209905, 209906, 209907, 209908, 209909};
	private int[] kor = {95, 95, 100, 100, 80, 100, 70, 80, 78};
	private int[] eng = {100, 95, 100, 95, 100, 100, 70, 75, 79};
	private int[] mat = {95, 95, 100, 90, 70, 70, 70, 72, 82};
	
	public String[] getName() {
		return name;
	}
	public int[] getStudentId() {
		return studentId;
	}
	public int[] getKor() {
		return kor;
	}
	public int[] getEng() {
		return eng;
	}
	public int[] getMat() {
		return mat;
	}

	@Override
	public String toString() {
		return "DefultRecords [name=" + Arrays.toString(name) + ", studentId=" + Arrays.toString(studentId) + ", kor="
				+ Arrays.toString(kor) + ", eng=" + Arrays.toString(eng) + ", mat=" + Arrays.toString(mat) + "]";
	}
}
