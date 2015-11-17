package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import constants.HabConstant;
import dto.HabResultDto;
import dto.QuestionDto;

/**
 * 運営が実装するクラス
 * プレイヤーサービスが返却した、クエスチョンをもとに 1S1Bの結果を返却する
 */
public class ManagementService {

	/** 1S1Bの結果Dto */
	HabResultDto habResultDto = new HabResultDto();

	/**
	 * 引数のクエスチョンDtoをもとに1S1Bの結果を返却する
	 * 
	 * @param questionDto クエスチョンDto
	 * @param answerNum アンサーの数字
	 * @return 1S1Bの結果Dto
	 */
	public HabResultDto createHabResult(QuestionDto questionDto,
			String answerNum) {

		// 引数のクエスチョンDto.クエスチョンを返却値の「1S1B」の結果Dto.クエスチョン履歴リストに設定する
		// インスタンスがすでに設定されている場合
		if (habResultDto.getQuestionHistList() != null) {

			// クエスチョン履歴リストに追加を行う
			habResultDto.getQuestionHistList().add(questionDto.getQuestion());
		} else {

			// 上記以外の場合
			// インスタンスを生成し、クエスチョン履歴リストに追加を行う
			List<String> questionHistList = new ArrayList<String>();
			questionHistList.add(questionDto.getQuestion());
			habResultDto.setQuestionHistList(questionHistList);
		}

		// 引数のアンサーの数字と引数のクエスチョンDto.クエスチョンが同じ場合
		if (answerNum.equals(questionDto.getQuestion())) {
			// 返却値の「1S1B」の結果Dto.ストライクに「3」を設定し返却する
			habResultDto.setStrike(HabConstant.THREE_STRIKE);
			return habResultDto;
		}

		// 上記以外の場合はアンサーの数字に対しての「1S1B」の結果を生成する
		// アンサー番号を1桁づつ取得する
		String[] answerNumOneDigitArray = this.getOneDigitArray(answerNum);
		// 引数のクエスチョンDto.クエスチョンを1桁づつ取得する
		String[] questionOneDigitArray = this.getOneDigitArray(questionDto
				.getQuestion());

		// 上記で取得したアンサー番号配列とクエスチョン配列をもとに1S1Bの結果を取得する
		return this.result1S1B(answerNumOneDigitArray, questionOneDigitArray);

	}

	/**
	 * 引数のアンサー番号配列とクエスチョン配列をもとに1S1Bの結果を取得する
	 * 
	 * @param answerNumOneDigitArray アンサー番号の1桁づつの配列
	 * @param questionOneDigitArray クエスチョン番号の1桁づつの配列
	 * @return 1S1Bの結果Dto
	 */
	private HabResultDto result1S1B(String[] answerNumOneDigitArray,
			String[] questionOneDigitArray) {
		// 返却値の1S1Bの結果Dtoを生成する
		HabResultDto habResultDto = new HabResultDto();

		// ストライク数
		int strikeCnt = 0;
		// ボール数
		int bowlCnt = 0;
		// クエスチョンの文字列配列分処理を行う
		for (int i = 0; i < questionOneDigitArray.length; i++) {

			// アンサー番号のi桁目とクエスチョン番号のi桁目が同じ場合
			if (answerNumOneDigitArray[i].equals(questionOneDigitArray[i])) {
				// ストライク数をインクリメント
				strikeCnt++;
				// 次の数字へスキップ
				continue;
			}
			// アンサー番号にクエスチョン番号のi桁目が含まれている場合
			if (Arrays.asList(answerNumOneDigitArray).contains(
					questionOneDigitArray[i])) {
				// ボール数をインクリメント
				bowlCnt++;
				// 次の数字へスキップ
				continue;
			}
			// 上記以外の場合は次の数字へスキップ
			continue;
		}
		// 上記で取得したストライク数を返却値の「1S1B」の結果Dto.ストライクに設定する
		habResultDto.setStrike(String.valueOf(strikeCnt));
		// 上記で取得したボール数を返却値の「1S1B」の結果Dto.ボールに設定する
		habResultDto.setBowl(String.valueOf(bowlCnt));
		// 引数のクエスチョン番号を返却値の「1S1B」の結果Dto.クエスチョン履歴リストに設定する
		habResultDto.setQuestionHistList(Arrays.asList(questionOneDigitArray));

		return habResultDto;
	}
	/**
	 * 引数をもとに1桁づつに変換した文字列配列を返却する
	 * 
	 * @param oneDigitStr 1桁づつ取得する対象文字列
	 * @return 1桁づつにした文字列配列
	 */
	private String[] getOneDigitArray(String oneDigitStr) {

		/** 返却値の1桁づつにした文字列配列を生成する(引数の文字列の数分生成する) */
		String[] oneDigitArray = new String[oneDigitStr.length()];

		// 引数の文字列の数分処理を行う
		for (int i = 0; i < oneDigitStr.length(); i++) {

			// 引数の文字列を1桁づつ取得する
			oneDigitArray[i] = oneDigitStr.substring(i, i + 1);
		}
		return oneDigitArray;
	}
}
