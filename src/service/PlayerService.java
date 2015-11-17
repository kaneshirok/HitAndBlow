package service;

import java.util.Arrays;
import java.util.List;

import dto.HabResultDto;
import dto.QuestionDto;

/**
 * プレイヤーが実装するクラス
 * 運営サービスが返却した1S1Bの結果をもとにクエスチョンを作成し 返却を行う
 */
public class PlayerService {

	/**
	 * 引数の1S1Bの結果が格納されたDtoをもとにクエスチョン生成する
	 * 
	 * @param habResultDto1S1Bの結果が格納されたDto
	 * @return クエスチョンDto
	 */
	public QuestionDto createQuestion(HabResultDto habResultDto) {

		// 引数の「1S1B」の結果Dto.クエスチョン履歴リストがNullまたは0件の場合
		if (habResultDto.getQuestionHistList() == null
				|| habResultDto.getQuestionHistList().size() == 0) {

			// 1回目のクエスチョンDtoの生成を行う
			QuestionDto questionDto = new QuestionDto();
			questionDto.setQuestion("145");
			return questionDto;
		}

		// 上記以外の場合(2回目以降のクエスチョンの生成)
		// 返却値のクエスチョンDtoの生成
		QuestionDto questionDto = new QuestionDto();
		List<String> test = Arrays.asList(
				String.valueOf((int) (Math.random() * 10)),
				String.valueOf((int) (Math.random() * 10)),
				String.valueOf((int) (Math.random() * 10)));
		StringBuilder testStr = new StringBuilder();
		for (String str : test) {
			testStr.append(str);
		}
		questionDto.setQuestion(testStr.toString());
		return questionDto;
	}
}
