## WEBサーバ構築  
### apacheとtomcat  
- apache:Apache HTTP Server  
テキスト、HTML、画像、音声、動画といった静的ファイルを提供するためのWEBサーバ  
- tomcat:Apache Tomcat  
Java ServletやJSPと呼ばれるプログラムを動かすためのWebコンテナ 

---

### apacheのインストール  
https://www.apachelounge.com/download/  
上記URLよりインストーラをダウンロードする。  
ダウンロードしたファイルを解凍後、「Apache24」フォルダをCドライブ配下に移動し、  
C:\Apache24\conf配下のhttpd.confを編集で開き、  
"#ServerName www.example.com:80"  
の行のコメントアウトを無効にして上書き保存する。  
その後コマンドプロンプトにてc:\Apache24\binに移動し、"httpd -k install"コマンドを実行する。  
apacheの起動、停止はそれぞれ以下のコマンドをc:\Apache24\binにて実行する。  
起動：httpd -k start  
停止：httpd -k stop  

### tomcatのインストール  
http://tomcat.apache.org/download-90.cgi  
上記URLより環境に合わせたバージョンを選択しダウンロードし、任意のフォルダに展開する。  
ユーザ変数設定にて変数名「CATALINA_HOME」を変数値をtomcatフォルダを配置した場所で作成する。  
続いて環境変数「PATH」に「%CATALINA_HOME%\bin」を追加する。  
また、システム環境変数「JAVA_HOME」がない場合、JDKフォルダを配置した場所で作成する。  
tomcatの起動、停止はそれぞれtomcatフォルダのbin配下のstartup.bat及びshutdown.batを実行する。  

---

### apacheとtomcatの連携設定  
#### ①Tomcatと連携するための拡張モジュールの読み込み設定を追加  
C:\Apache24\conf配下のhttpd.confを編集で開き下記の行を有効化する。  
#LoadModule proxy_module modules/mod_proxy.so  
#LoadModule proxy_ajp_module modules/mod_proxy_ajp.so  
#### ②Tomcatへ連携するURLパスを設定  
C:\Apache24\conf配下のhttpd.confを編集で開き、任意の行に下記を追記する。  
ProxyPass /apacheでのパス ajp://localhost:8009/tomcatでのパス  
#### ③tomcatがAJPプロトコルでのリクエストを受け付けれるようにする  
tomcatフォルダのconfフォルダ配下のserver.xmlファイルを開き、  
②で設定したポート番号:8009で検索し、下記の部分を有効にする。  
Define an AJP 1.3 Connector on port 8009  
<Connector Protocol ~ "8443"/>  
また、以下の属性を追加する。  
secretRequired-"false"  
