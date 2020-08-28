package com.icandothisallday2020.hybridapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView=findViewById(R.id.wv);

        //웹뷰가 보여줄 웹문서(.html) 로드
        //webView.loadUrl("http://soon0.dothome.co.kr/Web/artist.html");
        //but

        //하이브리드 앱은 오프라인에서도 동작해야 하므로
        //.html 안에 프로젝트 폴더안(app->new->folder->asset 폴더 생성)에  위치시킴



        //불과2달전의 웹뷰는 기본적으로 웹문서를 열때
        //새로운 브라우저를 실행하여 그 곳에 보여줌
        //이를 방지하기위해
        webView.setWebViewClient(new WebViewClient());
        //이제 버전업되어 ↑ 코드 필요 X


        //웹뷰는 기본적으로 alert(), confirm() 같은 모달 다이얼로그를 사용할 수 없기에
        //사용할 수 있도록
        webView.setWebChromeClient(new WebChromeClient());

        //웹뷰는 기본적으로 javascript 사용을 제한하기에
        //setting 에게 사용설정을 허용하도록 시키기
        WebSettings settings= webView.getSettings();
        settings.setJavaScriptEnabled(true);

        //html 문서가 위치한 asset 폴더의 파일을 웹뷰에 로드
        webView.loadUrl("file:///android_asset/index.html"); //    [/] : 최상위 폴더 지칭
    }


    //WebView 에서 뒤로 가기 버튼을 누르면 무조건 앱이 꺼짐
    //이전페이지로 가고 싶을때
    //디바이스 뒤로가기(back button) click 시 WebView 의 이전 웹페이지로 가도록
    @Override
    public void onBackPressed() {

        //웹뷰의 history 에 이전 웹문서 기록이 있을 때
        if(webView.canGoBack()) webView.goBack();
        else super.onBackPressed(); //이전 웹문서 기록이 없으면 앱끄기
    }
}
