# HitAndBlowゲーム

#### 第1回議事録--2015/10/13(火)--
##### 開催予定
■2015/12～2016/01

##### 場所
■会社またはGWave

##### 構成
■Webアプリケーション（java7.8 Tomcat7）　※２つ準備する

##### 仕様
■リアルタイムでスコアが反映されるようにしたい→プログラムをサーバーにデプロイした瞬間に。

■１スト１ボールのスコアをもとに別のアプリケーションにスコアを使えるようにしたい

■スコアの点数基準を設ける（何回で何点など）

##### 使用ツール
■slack(チャット)

■github(ソース・ドキュメント管理)

#### 第2回議事録--2015/10/15木(火)--

##### 仕様
■リアルタイムではなく個人またはチームごとに行う

■ランダムな数字を4桁で検証すると1000回で20秒ぐらい

■ランダムな数字は最初で一定の数をリストで保持する方向（個人またはチームで差がでないように）

##### 懸念
■メモリーリークが心配（検証が必要）

■見えない部分があるので早めに実装に入りたい
