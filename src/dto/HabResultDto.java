package dto;

import java.util.List;

/**
 * 
 * 1S1Bの結果を格納するクラス
 * 
 * @author kaneshirok
 *
 */
public class HabResultDto {
	
	/** ストライク数 */
	private String strike;

	/** ボール数 */
	private String bowl;

	/** クエスチョン履歴 */
	private List<String> questionHistList;

	/** 桁数 */
	private int digit;

	/**
	 * ストライク数を取得します。
	 * @return ストライク数
	 */
	public String getStrike() {
	    return strike;
	}

	/**
	 * ストライク数を設定します。
	 * @param strike ストライク数
	 */
	public void setStrike(String strike) {
	    this.strike = strike;
	}

	/**
	 * ボール数を取得します。
	 * @return ボール数
	 */
	public String getBowl() {
	    return bowl;
	}

	/**
	 * ボール数を設定します。
	 * @param bowl ボール数
	 */
	public void setBowl(String bowl) {
	    this.bowl = bowl;
	}

	/**
	 * クエスチョン履歴を取得します。
	 * @return クエスチョン履歴
	 */
	public List<String> getQuestionHistList() {
	    return questionHistList;
	}

	/**
	 * クエスチョン履歴を設定します。
	 * @param questionHistList クエスチョン履歴
	 */
	public void setQuestionHistList(List<String> questionHistList) {
	    this.questionHistList = questionHistList;
	}

	/**
	 * 桁数を取得します。
	 * @return 桁数
	 */
	public int getDigit() {
	    return digit;
	}

	/**
	 * 桁数を設定します。
	 * @param digit 桁数
	 */
	public void setDigit(int digit) {
	    this.digit = digit;
	}
}
