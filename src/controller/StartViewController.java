package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ManagementService;
import service.PlayerService;
import constants.HabConstant;
import dto.HabResultDto;
import dto.QuestionDto;

/**
 * スタート画面からのリクエストに対しての処理を行うコントローラー
 * プレイヤーが作成したプログラムを呼び出し、3ストライクになった時点で、結果画面に遷移する
 */
@WebServlet(name = "StartViewController", urlPatterns = { "/StartViewController" })
public class StartViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/** プレイヤーサービス */
	private PlayerService playerService = new PlayerService();
	/** マネジメントサービス */
	private ManagementService managementService = new ManagementService();

    /**
     * コンストラクタ
     */
    public StartViewController() {
        super();
    }

	/**
	 * Getメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * Postメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッション情報にチーム名を保持する
		request.getSession().setAttribute("teamName",
				request.getParameter("teamName"));

		// スタートタイムを生成する
		LocalDateTime startTime = LocalDateTime.now();
		
		// ランダム数字の生成
		String answerNum = this.createAnswerNum(request);

		// アンサーフラグの生成(初期値false:不正解)
		boolean answerFlg = false;
		// 1回目のプレイヤーサービスの呼び出しを行う
		HabResultDto firstHabResultDto = new HabResultDto();
		firstHabResultDto.setDigit(answerNum.length());
		QuestionDto questionDto = this.playerService
				.createQuestion(firstHabResultDto);

		HabResultDto habResultDto = new HabResultDto();
		
		// マネジメントサービスが「3S」を返却するまで繰り返し処理を行う
		while (!answerFlg) {

			// マネジメントサービスの呼び出し
			habResultDto = this.managementService.createHabResult(questionDto, answerNum);

			// マネジメントサービスからの返却値が「3」でない場合
			if (!HabConstant.THREE_STRIKE.equals(habResultDto.getStrike())) {

				// プレイヤーサービスの呼び出し
				questionDto = this.playerService.createQuestion(habResultDto);
				// 処理をスキップする
				continue;
			}
			// 上記以外の場合(マネジメントサービスからの返却値が「3S」の場合)
			// アンサーフラグを「true:正解」にする
			answerFlg = true;
		}
		// 上記の繰り返し処理を抜けた場合(アンサーフラグが「true:正解」)
		// 終了時間を生成する
		LocalDateTime endTime = LocalDateTime.now();
		
		// セッション情報にアンサーになったまでの経過時間を保持する
		long elapsedTime = endTime.toLocalTime().toNanoOfDay()
				- startTime.toLocalTime().toNanoOfDay();
		request.getSession()
				.setAttribute("elapsedTime", (elapsedTime / 100000));
		// セッション情報に回答に至までの回数を保持する
		request.getSession().setAttribute("totalAnswerCnt",
				habResultDto.getQuestionHistList().size());

		// 結果画面に遷移する
		RequestDispatcher rd = request
				.getRequestDispatcher("/view/teamTotalView.jsp");
		rd.forward(request, response);

	}

	/**
	 * スタート画面で選択した桁数をもとに生成したアンサー番号を生成する
	 * 
	 * @param request スタート画面からのリクエスト情報
	 * @return アンサー番号
	 */
	private String createAnswerNum(HttpServletRequest request) {

		// スタート画面で選択した桁数の取得
		int numberOfDigit = Integer.valueOf(request
				.getParameter("numberOfDigit"));

		// アンサーのランダム数字を保持する変数
		List<String> answerNumList = new ArrayList<String>();

		// スタート画面で選択した桁数分、ランダム数字を生成する
		for (int i = 1; i < numberOfDigit; i++) {

			// 取得した桁数のランダム数字を生成する
			String answerNum = String.valueOf((int) (Math.random() * 10));

			// 1桁目の処理の場合
			if (i == 1) {
				// 1桁目の数字はアンサーのランダム数字を保持するリストに追加する
				answerNumList.add(String.valueOf(answerNum));
			}
			// 2桁目以降の処理は生成したランダム数字がかぶらないようにする
			// 上記で生成したアンサーのランダム数字リストに生成したランダム数字がある場合
			while (answerNumList.contains(answerNum)) {
				// もう1度ランダム数字を生成する
				answerNum = String.valueOf((int) (Math.random() * 10));
			}
			// 上記繰り返し処理を抜けた場合(アンサーのランダム数字リストに生成したランダム数字がない場合)
			// アンサーのランダム数字を保持するリストに追加する
			answerNumList.add(String.valueOf(answerNum));
		}

		// 返却値のアンサー番号を生成する
		StringBuilder answerNum = new StringBuilder();
		// 上記で生成したアンサー番号リストを返却値のアンサー番号に変換する
		for (String answerNumStr : answerNumList) {
			answerNum.append(answerNumStr);
		}
		return answerNum.toString();
	}

}