# TechBookFest_android

## 概要
俺得アプリ。技術書典のホームページに検索機能がなく使いにくいと感じ作成。アプリ化したが個人で使うのでUIはくそ。  
次回、技術書典に参加する際に使用したいのでそれまでにコードもUIも完成させる。

## 仕様
・[サーバーサイド ](https://github.com/hmarf/TechBookFest_serverside)
から吐かれたJsonを所得し、表示する。  
・自分が「いいね！」した本は携帯端末のDBに保存。(Room使用)   
・本の詳細はWebViewで表示。端末のDBから読み取って表示するようにしたい。~~（面倒）~~

## このアプリの残念な点
・本の画像がサークル画像になってします。これはサーバーサイド の問題（スクレーピング）  
・設計がゴミ（MVVMですらない）　←　誰かにリファクタリングを頼みたい。  

## アプリの挙動
販売されている本の一覧を表示、「いいね」した本を保存  
<img src="https://github.com/hmarf/TechBookFest_android/blob/feature/UpLoadGif/first.gif" width="250px">  
  
検索結果を表示、「いいね」した本を保存、DBからの削除  
<img src="https://github.com/hmarf/TechBookFest_android/blob/feature/UpLoadGif/second.gif" width="250px">
